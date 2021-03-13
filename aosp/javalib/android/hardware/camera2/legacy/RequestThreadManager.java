package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.SizeAreaComparator;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RequestThreadManager {
  private static final float ASPECT_RATIO_TOLERANCE = 0.01F;
  
  private static final boolean DEBUG = false;
  
  private static final int JPEG_FRAME_TIMEOUT = 4000;
  
  private static final int MAX_IN_FLIGHT_REQUESTS = 2;
  
  private static final int MSG_CLEANUP = 3;
  
  private static final int MSG_CONFIGURE_OUTPUTS = 1;
  
  private static final int MSG_SUBMIT_CAPTURE_REQUEST = 2;
  
  private static final int PREVIEW_FRAME_TIMEOUT = 1000;
  
  private static final int REQUEST_COMPLETE_TIMEOUT = 4000;
  
  private static final boolean USE_BLOB_FORMAT_OVERRIDE = true;
  
  private static final boolean VERBOSE = false;
  
  private final String TAG;
  
  private final List<Surface> mCallbackOutputs = new ArrayList<>();
  
  private Camera mCamera;
  
  private final int mCameraId;
  
  private final CaptureCollector mCaptureCollector;
  
  private final CameraCharacteristics mCharacteristics;
  
  private final CameraDeviceState mDeviceState;
  
  private Surface mDummySurface;
  
  private SurfaceTexture mDummyTexture;
  
  private final Camera.ErrorCallback mErrorCallback;
  
  private final LegacyFaceDetectMapper mFaceDetectMapper;
  
  private final LegacyFocusStateMapper mFocusStateMapper;
  
  private GLThreadManager mGLThreadManager;
  
  private final Object mIdleLock;
  
  private Size mIntermediateBufferSize;
  
  private final Camera.PictureCallback mJpegCallback;
  
  private final Camera.ShutterCallback mJpegShutterCallback;
  
  private final List<Long> mJpegSurfaceIds;
  
  private LegacyRequest mLastRequest;
  
  private Camera.Parameters mParams;
  
  private final FpsCounter mPrevCounter;
  
  private final SurfaceTexture.OnFrameAvailableListener mPreviewCallback;
  
  private final List<Surface> mPreviewOutputs = new ArrayList<>();
  
  private boolean mPreviewRunning = false;
  
  private SurfaceTexture mPreviewTexture;
  
  private final AtomicBoolean mQuit;
  
  private final ConditionVariable mReceivedJpeg;
  
  private final FpsCounter mRequestCounter;
  
  private final Handler.Callback mRequestHandlerCb;
  
  private final RequestQueue mRequestQueue;
  
  private final RequestHandlerThread mRequestThread;
  
  public RequestThreadManager(int paramInt, Camera paramCamera, CameraCharacteristics paramCameraCharacteristics, CameraDeviceState paramCameraDeviceState) {
    ArrayList<Long> arrayList = new ArrayList();
    this.mJpegSurfaceIds = arrayList;
    this.mRequestQueue = new RequestQueue(arrayList);
    this.mLastRequest = null;
    this.mIdleLock = new Object();
    this.mPrevCounter = new FpsCounter("Incoming Preview");
    this.mRequestCounter = new FpsCounter("Incoming Requests");
    this.mQuit = new AtomicBoolean(false);
    this.mErrorCallback = new Camera.ErrorCallback() {
        public void onError(int param1Int, Camera param1Camera) {
          if (param1Int != 2) {
            if (param1Int != 3) {
              String str = RequestThreadManager.this.TAG;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Received error ");
              stringBuilder.append(param1Int);
              stringBuilder.append(" from the Camera1 ErrorCallback");
              Log.e(str, stringBuilder.toString());
              RequestThreadManager.this.mDeviceState.setError(1);
            } else {
              RequestThreadManager.this.flush();
              RequestThreadManager.this.mDeviceState.setError(6);
            } 
          } else {
            RequestThreadManager.this.flush();
            RequestThreadManager.this.mDeviceState.setError(0);
          } 
        }
      };
    this.mReceivedJpeg = new ConditionVariable(false);
    this.mJpegCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] param1ArrayOfbyte, Camera param1Camera) {
          Log.i(RequestThreadManager.this.TAG, "Received jpeg.");
          Pair<RequestHolder, Long> pair = RequestThreadManager.this.mCaptureCollector.jpegProduced();
          if (pair == null || pair.first == null) {
            Log.e(RequestThreadManager.this.TAG, "Dropping jpeg frame.");
            return;
          } 
          RequestHolder requestHolder = (RequestHolder)pair.first;
          long l = ((Long)pair.second).longValue();
          for (Surface surface : requestHolder.getHolderTargets()) {
            try {
              if (LegacyCameraDevice.containsSurfaceId(surface, RequestThreadManager.this.mJpegSurfaceIds)) {
                Log.i(RequestThreadManager.this.TAG, "Producing jpeg buffer...");
                int i = param1ArrayOfbyte.length;
                int j = LegacyCameraDevice.nativeGetJpegFooterSize();
                LegacyCameraDevice.setNextTimestamp(surface, l);
                LegacyCameraDevice.setSurfaceFormat(surface, 1);
                i = (int)Math.ceil(Math.sqrt((i + j + 3 & 0xFFFFFFFC))) + 15 & 0xFFFFFFF0;
                LegacyCameraDevice.setSurfaceDimens(surface, i, i);
                LegacyCameraDevice.produceFrame(surface, param1ArrayOfbyte, i, i, 33);
              } 
            } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
              Log.w(RequestThreadManager.this.TAG, "Surface abandoned, dropping frame. ", (Throwable)bufferQueueAbandonedException);
            } 
          } 
          RequestThreadManager.this.mReceivedJpeg.open();
        }
      };
    this.mJpegShutterCallback = new Camera.ShutterCallback() {
        public void onShutter() {
          RequestThreadManager.this.mCaptureCollector.jpegCaptured(SystemClock.elapsedRealtimeNanos());
        }
      };
    this.mPreviewCallback = new SurfaceTexture.OnFrameAvailableListener() {
        public void onFrameAvailable(SurfaceTexture param1SurfaceTexture) {
          RequestThreadManager.this.mGLThreadManager.queueNewFrame();
        }
      };
    this.mRequestHandlerCb = new Handler.Callback() {
        private boolean mCleanup = false;
        
        private final LegacyResultMapper mMapper = new LegacyResultMapper();
        
        public boolean handleMessage(Message param1Message) {
          // Byte code:
          //   0: aload_0
          //   1: getfield mCleanup : Z
          //   4: ifeq -> 9
          //   7: iconst_1
          //   8: ireturn
          //   9: lconst_0
          //   10: lstore_2
          //   11: aload_1
          //   12: getfield what : I
          //   15: istore #4
          //   17: iload #4
          //   19: iconst_m1
          //   20: if_icmpeq -> 1648
          //   23: iload #4
          //   25: iconst_1
          //   26: if_icmpeq -> 1465
          //   29: iload #4
          //   31: iconst_2
          //   32: if_icmpeq -> 236
          //   35: iload #4
          //   37: iconst_3
          //   38: if_icmpne -> 188
          //   41: aload_0
          //   42: iconst_1
          //   43: putfield mCleanup : Z
          //   46: aload_0
          //   47: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   50: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   53: ldc2_w 4000
          //   56: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
          //   59: invokevirtual waitForEmpty : (JLjava/util/concurrent/TimeUnit;)Z
          //   62: ifne -> 88
          //   65: aload_0
          //   66: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   69: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   72: ldc 'Timed out while queueing cleanup request.'
          //   74: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   77: pop
          //   78: aload_0
          //   79: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   82: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   85: invokevirtual failAll : ()V
          //   88: goto -> 117
          //   91: astore_1
          //   92: aload_0
          //   93: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   96: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   99: ldc 'Interrupted while waiting for requests to complete: '
          //   101: aload_1
          //   102: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   105: pop
          //   106: aload_0
          //   107: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   110: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   113: iconst_1
          //   114: invokevirtual setError : (I)V
          //   117: aload_0
          //   118: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   121: invokestatic access$500 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/GLThreadManager;
          //   124: ifnull -> 146
          //   127: aload_0
          //   128: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   131: invokestatic access$500 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/GLThreadManager;
          //   134: invokevirtual quit : ()V
          //   137: aload_0
          //   138: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   141: aconst_null
          //   142: invokestatic access$502 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/GLThreadManager;)Landroid/hardware/camera2/legacy/GLThreadManager;
          //   145: pop
          //   146: aload_0
          //   147: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   150: invokestatic access$1900 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)V
          //   153: aload_0
          //   154: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   157: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
          //   160: ifnull -> 185
          //   163: aload_0
          //   164: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   167: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
          //   170: invokevirtual release : ()V
          //   173: aload_0
          //   174: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   177: aconst_null
          //   178: invokestatic access$1302 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/Camera;)Landroid/hardware/Camera;
          //   181: pop
          //   182: goto -> 1648
          //   185: goto -> 1648
          //   188: new java/lang/StringBuilder
          //   191: dup
          //   192: invokespecial <init> : ()V
          //   195: astore #5
          //   197: aload #5
          //   199: ldc 'Unhandled message '
          //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   204: pop
          //   205: aload #5
          //   207: aload_1
          //   208: getfield what : I
          //   211: invokevirtual append : (I)Ljava/lang/StringBuilder;
          //   214: pop
          //   215: aload #5
          //   217: ldc ' on RequestThread.'
          //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   222: pop
          //   223: new java/lang/AssertionError
          //   226: dup
          //   227: aload #5
          //   229: invokevirtual toString : ()Ljava/lang/String;
          //   232: invokespecial <init> : (Ljava/lang/Object;)V
          //   235: athrow
          //   236: aload_0
          //   237: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   240: invokestatic access$700 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/RequestHandlerThread;
          //   243: invokevirtual getHandler : ()Landroid/os/Handler;
          //   246: astore #5
          //   248: iconst_0
          //   249: istore #6
          //   251: aload_0
          //   252: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   255: invokestatic access$800 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/RequestQueue;
          //   258: invokevirtual getNext : ()Landroid/hardware/camera2/legacy/RequestQueue$RequestQueueEntry;
          //   261: astore #7
          //   263: aload #7
          //   265: astore_1
          //   266: aload #7
          //   268: ifnonnull -> 398
          //   271: aload_0
          //   272: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   275: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   278: ldc2_w 4000
          //   281: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
          //   284: invokevirtual waitForEmpty : (JLjava/util/concurrent/TimeUnit;)Z
          //   287: ifne -> 313
          //   290: aload_0
          //   291: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   294: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   297: ldc 'Timed out while waiting for prior requests to complete.'
          //   299: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   302: pop
          //   303: aload_0
          //   304: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   307: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   310: invokevirtual failAll : ()V
          //   313: aload_0
          //   314: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   317: invokestatic access$900 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/Object;
          //   320: astore #7
          //   322: aload #7
          //   324: monitorenter
          //   325: aload_0
          //   326: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   329: invokestatic access$800 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/RequestQueue;
          //   332: invokevirtual getNext : ()Landroid/hardware/camera2/legacy/RequestQueue$RequestQueueEntry;
          //   335: astore_1
          //   336: aload_1
          //   337: ifnonnull -> 357
          //   340: aload_0
          //   341: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   344: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   347: invokevirtual setIdle : ()Z
          //   350: pop
          //   351: aload #7
          //   353: monitorexit
          //   354: goto -> 1648
          //   357: aload #7
          //   359: monitorexit
          //   360: goto -> 398
          //   363: astore_1
          //   364: aload #7
          //   366: monitorexit
          //   367: aload_1
          //   368: athrow
          //   369: astore_1
          //   370: aload_0
          //   371: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   374: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   377: ldc 'Interrupted while waiting for requests to complete: '
          //   379: aload_1
          //   380: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   383: pop
          //   384: aload_0
          //   385: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   388: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   391: iconst_1
          //   392: invokevirtual setError : (I)V
          //   395: goto -> 1648
          //   398: aload_1
          //   399: ifnull -> 426
          //   402: aload #5
          //   404: iconst_2
          //   405: invokevirtual sendEmptyMessage : (I)Z
          //   408: pop
          //   409: aload_1
          //   410: invokevirtual isQueueEmpty : ()Z
          //   413: ifeq -> 426
          //   416: aload_0
          //   417: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   420: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   423: invokevirtual setRequestQueueEmpty : ()V
          //   426: aload_1
          //   427: invokevirtual getBurstHolder : ()Landroid/hardware/camera2/legacy/BurstHolder;
          //   430: astore #7
          //   432: aload #7
          //   434: aload_1
          //   435: invokevirtual getFrameNumber : ()Ljava/lang/Long;
          //   438: invokevirtual longValue : ()J
          //   441: invokevirtual produceRequestHolders : (J)Ljava/util/List;
          //   444: invokeinterface iterator : ()Ljava/util/Iterator;
          //   449: astore #8
          //   451: aload #5
          //   453: astore_1
          //   454: aload #8
          //   456: invokeinterface hasNext : ()Z
          //   461: ifeq -> 1353
          //   464: aload #8
          //   466: invokeinterface next : ()Ljava/lang/Object;
          //   471: checkcast android/hardware/camera2/legacy/RequestHolder
          //   474: astore #5
          //   476: aload #5
          //   478: invokevirtual getRequest : ()Landroid/hardware/camera2/CaptureRequest;
          //   481: astore #9
          //   483: iconst_0
          //   484: istore #4
          //   486: aload_0
          //   487: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   490: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   493: ifnull -> 520
          //   496: aload_0
          //   497: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   500: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   503: getfield captureRequest : Landroid/hardware/camera2/CaptureRequest;
          //   506: aload #9
          //   508: if_acmpeq -> 514
          //   511: goto -> 520
          //   514: iconst_0
          //   515: istore #4
          //   517: goto -> 670
          //   520: aload_0
          //   521: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   524: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
          //   527: invokevirtual getPreviewSize : ()Landroid/hardware/Camera$Size;
          //   530: invokestatic convertSize : (Landroid/hardware/Camera$Size;)Landroid/util/Size;
          //   533: astore #10
          //   535: new android/hardware/camera2/legacy/LegacyRequest
          //   538: dup
          //   539: aload_0
          //   540: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   543: invokestatic access$1200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/CameraCharacteristics;
          //   546: aload #9
          //   548: aload #10
          //   550: aload_0
          //   551: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   554: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
          //   557: invokespecial <init> : (Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/CaptureRequest;Landroid/util/Size;Landroid/hardware/Camera$Parameters;)V
          //   560: astore #10
          //   562: aload #10
          //   564: invokestatic convertRequestMetadata : (Landroid/hardware/camera2/legacy/LegacyRequest;)V
          //   567: aload_0
          //   568: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   571: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
          //   574: aload #10
          //   576: getfield parameters : Landroid/hardware/Camera$Parameters;
          //   579: invokevirtual same : (Landroid/hardware/Camera$Parameters;)Z
          //   582: ifne -> 660
          //   585: aload_0
          //   586: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   589: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
          //   592: aload #10
          //   594: getfield parameters : Landroid/hardware/Camera$Parameters;
          //   597: invokevirtual setParameters : (Landroid/hardware/Camera$Parameters;)V
          //   600: iconst_1
          //   601: istore #4
          //   603: aload_0
          //   604: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   607: aload #10
          //   609: getfield parameters : Landroid/hardware/Camera$Parameters;
          //   612: invokestatic access$1102 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Parameters;
          //   615: pop
          //   616: goto -> 660
          //   619: astore #9
          //   621: aload_0
          //   622: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   625: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   628: ldc_w 'Exception while setting camera parameters: '
          //   631: aload #9
          //   633: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   636: pop
          //   637: aload #5
          //   639: invokevirtual failRequest : ()V
          //   642: aload_0
          //   643: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   646: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   649: aload #5
          //   651: lconst_0
          //   652: iconst_3
          //   653: invokevirtual setCaptureStart : (Landroid/hardware/camera2/legacy/RequestHolder;JI)Z
          //   656: pop
          //   657: goto -> 752
          //   660: aload_0
          //   661: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   664: aload #10
          //   666: invokestatic access$1002 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/LegacyRequest;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   669: pop
          //   670: aload_0
          //   671: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   674: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   677: astore #10
          //   679: aload_0
          //   680: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   683: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   686: astore #11
          //   688: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
          //   691: astore #12
          //   693: aload #10
          //   695: aload #5
          //   697: aload #11
          //   699: ldc2_w 4000
          //   702: aload #12
          //   704: invokevirtual queueRequest : (Landroid/hardware/camera2/legacy/RequestHolder;Landroid/hardware/camera2/legacy/LegacyRequest;JLjava/util/concurrent/TimeUnit;)Z
          //   707: istore #13
          //   709: iload #13
          //   711: ifne -> 767
          //   714: aload_0
          //   715: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   718: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   721: ldc_w 'Timed out while queueing capture request.'
          //   724: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   727: pop
          //   728: aload #5
          //   730: invokevirtual failRequest : ()V
          //   733: aload_0
          //   734: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   737: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   740: astore #9
          //   742: aload #9
          //   744: aload #5
          //   746: lconst_0
          //   747: iconst_3
          //   748: invokevirtual setCaptureStart : (Landroid/hardware/camera2/legacy/RequestHolder;JI)Z
          //   751: pop
          //   752: goto -> 454
          //   755: astore_1
          //   756: goto -> 1264
          //   759: astore_1
          //   760: goto -> 1294
          //   763: astore_1
          //   764: goto -> 1324
          //   767: aload #5
          //   769: invokevirtual hasPreviewTargets : ()Z
          //   772: istore #13
          //   774: iload #13
          //   776: ifeq -> 803
          //   779: aload_0
          //   780: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   783: aload #5
          //   785: invokestatic access$1400 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/RequestHolder;)V
          //   788: goto -> 803
          //   791: astore_1
          //   792: goto -> 1264
          //   795: astore_1
          //   796: goto -> 1294
          //   799: astore_1
          //   800: goto -> 1324
          //   803: aload #5
          //   805: invokevirtual hasJpegTargets : ()Z
          //   808: istore #13
          //   810: iload #13
          //   812: ifeq -> 880
          //   815: aload_0
          //   816: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   819: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   822: ldc2_w 1000
          //   825: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
          //   828: invokevirtual waitForPreviewsEmpty : (JLjava/util/concurrent/TimeUnit;)Z
          //   831: ifne -> 861
          //   834: aload_0
          //   835: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   838: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   841: ldc_w 'Timed out while waiting for preview requests to complete.'
          //   844: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   847: pop
          //   848: aload_0
          //   849: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   852: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   855: invokevirtual failNextPreview : ()V
          //   858: goto -> 815
          //   861: aload_0
          //   862: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   865: invokestatic access$400 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/os/ConditionVariable;
          //   868: invokevirtual close : ()V
          //   871: aload_0
          //   872: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   875: aload #5
          //   877: invokestatic access$1500 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/RequestHolder;)V
          //   880: aload_0
          //   881: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   884: invokestatic access$1600 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFaceDetectMapper;
          //   887: aload #9
          //   889: aload_0
          //   890: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   893: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
          //   896: invokevirtual processFaceDetectMode : (Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/Camera$Parameters;)V
          //   899: aload_0
          //   900: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   903: invokestatic access$1700 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFocusStateMapper;
          //   906: aload #9
          //   908: aload_0
          //   909: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   912: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
          //   915: invokevirtual processRequestTriggers : (Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/Camera$Parameters;)V
          //   918: aload #5
          //   920: invokevirtual hasJpegTargets : ()Z
          //   923: istore #13
          //   925: iload #13
          //   927: ifeq -> 979
          //   930: aload_0
          //   931: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   934: aload #5
          //   936: invokestatic access$1800 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/camera2/legacy/RequestHolder;)V
          //   939: aload_0
          //   940: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   943: invokestatic access$400 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/os/ConditionVariable;
          //   946: ldc2_w 4000
          //   949: invokevirtual block : (J)Z
          //   952: ifne -> 979
          //   955: aload_0
          //   956: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   959: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   962: ldc_w 'Hit timeout for jpeg callback!'
          //   965: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   968: pop
          //   969: aload_0
          //   970: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   973: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   976: invokevirtual failNextJpeg : ()V
          //   979: iload #4
          //   981: ifeq -> 1052
          //   984: aload_0
          //   985: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   988: aload_0
          //   989: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   992: invokestatic access$1300 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera;
          //   995: invokevirtual getParameters : ()Landroid/hardware/Camera$Parameters;
          //   998: invokestatic access$1102 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Landroid/hardware/Camera$Parameters;)Landroid/hardware/Camera$Parameters;
          //   1001: pop
          //   1002: aload_0
          //   1003: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1006: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   1009: aload_0
          //   1010: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1013: invokestatic access$1100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/Camera$Parameters;
          //   1016: invokevirtual setParameters : (Landroid/hardware/Camera$Parameters;)V
          //   1019: goto -> 1052
          //   1022: astore_1
          //   1023: aload_0
          //   1024: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1027: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1030: ldc_w 'Received device exception: '
          //   1033: aload_1
          //   1034: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   1037: pop
          //   1038: aload_0
          //   1039: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1042: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1045: iconst_1
          //   1046: invokevirtual setError : (I)V
          //   1049: goto -> 1353
          //   1052: new android/util/MutableLong
          //   1055: dup
          //   1056: lconst_0
          //   1057: invokespecial <init> : (J)V
          //   1060: astore #9
          //   1062: aload_0
          //   1063: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1066: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   1069: aload #5
          //   1071: ldc2_w 4000
          //   1074: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
          //   1077: aload #9
          //   1079: invokevirtual waitForRequestCompleted : (Landroid/hardware/camera2/legacy/RequestHolder;JLjava/util/concurrent/TimeUnit;Landroid/util/MutableLong;)Z
          //   1082: istore #13
          //   1084: iload #13
          //   1086: ifne -> 1120
          //   1089: aload_0
          //   1090: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1093: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1096: ldc_w 'Timed out while waiting for request to complete.'
          //   1099: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   1102: pop
          //   1103: aload_0
          //   1104: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1107: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   1110: invokevirtual failAll : ()V
          //   1113: goto -> 1120
          //   1116: astore_1
          //   1117: goto -> 1210
          //   1120: aload_0
          //   1121: getfield mMapper : Landroid/hardware/camera2/legacy/LegacyResultMapper;
          //   1124: aload_0
          //   1125: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1128: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   1131: aload #9
          //   1133: getfield value : J
          //   1136: invokevirtual cachedConvertResultMetadata : (Landroid/hardware/camera2/legacy/LegacyRequest;J)Landroid/hardware/camera2/impl/CameraMetadataNative;
          //   1139: astore #9
          //   1141: aload_0
          //   1142: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1145: invokestatic access$1700 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFocusStateMapper;
          //   1148: aload #9
          //   1150: invokevirtual mapResultTriggers : (Landroid/hardware/camera2/impl/CameraMetadataNative;)V
          //   1153: aload_0
          //   1154: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1157: invokestatic access$1600 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyFaceDetectMapper;
          //   1160: aload #9
          //   1162: aload_0
          //   1163: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1166: invokestatic access$1000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/LegacyRequest;
          //   1169: invokevirtual mapResultFaces : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/legacy/LegacyRequest;)V
          //   1172: aload #5
          //   1174: invokevirtual requestFailed : ()Z
          //   1177: ifne -> 1195
          //   1180: aload_0
          //   1181: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1184: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1187: aload #5
          //   1189: aload #9
          //   1191: invokevirtual setCaptureResult : (Landroid/hardware/camera2/legacy/RequestHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;)Z
          //   1194: pop
          //   1195: aload #5
          //   1197: invokevirtual isOutputAbandoned : ()Z
          //   1200: ifeq -> 1206
          //   1203: iconst_1
          //   1204: istore #6
          //   1206: goto -> 454
          //   1209: astore_1
          //   1210: aload_0
          //   1211: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1214: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1217: ldc_w 'Interrupted waiting for request completion: '
          //   1220: aload_1
          //   1221: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   1224: pop
          //   1225: aload_0
          //   1226: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1229: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1232: iconst_1
          //   1233: invokevirtual setError : (I)V
          //   1236: goto -> 1353
          //   1239: astore_1
          //   1240: goto -> 1264
          //   1243: astore_1
          //   1244: goto -> 1294
          //   1247: astore_1
          //   1248: goto -> 1324
          //   1251: astore_1
          //   1252: goto -> 1264
          //   1255: astore_1
          //   1256: goto -> 1294
          //   1259: astore_1
          //   1260: goto -> 1324
          //   1263: astore_1
          //   1264: aload_0
          //   1265: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1268: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1271: ldc_w 'Received device exception during capture call: '
          //   1274: aload_1
          //   1275: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   1278: pop
          //   1279: aload_0
          //   1280: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1283: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1286: iconst_1
          //   1287: invokevirtual setError : (I)V
          //   1290: goto -> 1353
          //   1293: astore_1
          //   1294: aload_0
          //   1295: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1298: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1301: ldc_w 'Interrupted during capture: '
          //   1304: aload_1
          //   1305: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   1308: pop
          //   1309: aload_0
          //   1310: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1313: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1316: iconst_1
          //   1317: invokevirtual setError : (I)V
          //   1320: goto -> 1353
          //   1323: astore_1
          //   1324: aload_0
          //   1325: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1328: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1331: ldc_w 'Received device exception during capture call: '
          //   1334: aload_1
          //   1335: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   1338: pop
          //   1339: aload_0
          //   1340: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1343: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1346: iconst_1
          //   1347: invokevirtual setError : (I)V
          //   1350: goto -> 1353
          //   1353: iload #6
          //   1355: ifeq -> 1462
          //   1358: aload #7
          //   1360: invokevirtual isRepeating : ()Z
          //   1363: ifeq -> 1462
          //   1366: aload_0
          //   1367: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1370: aload #7
          //   1372: invokevirtual getRequestId : ()I
          //   1375: invokevirtual cancelRepeating : (I)J
          //   1378: lstore_2
          //   1379: lload_2
          //   1380: ldc2_w -1
          //   1383: lcmp
          //   1384: ifeq -> 1406
          //   1387: aload_0
          //   1388: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1391: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1394: lload_2
          //   1395: aload #7
          //   1397: invokevirtual getRequestId : ()I
          //   1400: invokevirtual setRepeatingRequestError : (JI)V
          //   1403: goto -> 1459
          //   1406: aload_0
          //   1407: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1410: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1413: astore #5
          //   1415: new java/lang/StringBuilder
          //   1418: dup
          //   1419: invokespecial <init> : ()V
          //   1422: astore_1
          //   1423: aload_1
          //   1424: ldc_w 'Repeating request id: '
          //   1427: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1430: pop
          //   1431: aload_1
          //   1432: aload #7
          //   1434: invokevirtual getRequestId : ()I
          //   1437: invokevirtual append : (I)Ljava/lang/StringBuilder;
          //   1440: pop
          //   1441: aload_1
          //   1442: ldc_w ' already canceled!'
          //   1445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1448: pop
          //   1449: aload #5
          //   1451: aload_1
          //   1452: invokevirtual toString : ()Ljava/lang/String;
          //   1455: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   1458: pop
          //   1459: goto -> 1648
          //   1462: goto -> 1648
          //   1465: aload_1
          //   1466: getfield obj : Ljava/lang/Object;
          //   1469: checkcast android/hardware/camera2/legacy/RequestThreadManager$ConfigureHolder
          //   1472: astore #7
          //   1474: aload #7
          //   1476: getfield surfaces : Ljava/util/Collection;
          //   1479: ifnull -> 1497
          //   1482: aload #7
          //   1484: getfield surfaces : Ljava/util/Collection;
          //   1487: invokeinterface size : ()I
          //   1492: istore #4
          //   1494: goto -> 1500
          //   1497: iconst_0
          //   1498: istore #4
          //   1500: aload_0
          //   1501: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1504: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1507: astore_1
          //   1508: new java/lang/StringBuilder
          //   1511: dup
          //   1512: invokespecial <init> : ()V
          //   1515: astore #5
          //   1517: aload #5
          //   1519: ldc_w 'Configure outputs: '
          //   1522: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1525: pop
          //   1526: aload #5
          //   1528: iload #4
          //   1530: invokevirtual append : (I)Ljava/lang/StringBuilder;
          //   1533: pop
          //   1534: aload #5
          //   1536: ldc_w ' surfaces configured.'
          //   1539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1542: pop
          //   1543: aload_1
          //   1544: aload #5
          //   1546: invokevirtual toString : ()Ljava/lang/String;
          //   1549: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
          //   1552: pop
          //   1553: aload_0
          //   1554: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1557: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   1560: ldc2_w 4000
          //   1563: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
          //   1566: invokevirtual waitForEmpty : (JLjava/util/concurrent/TimeUnit;)Z
          //   1569: ifne -> 1596
          //   1572: aload_0
          //   1573: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1576: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1579: ldc_w 'Timed out while queueing configure request.'
          //   1582: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   1585: pop
          //   1586: aload_0
          //   1587: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1590: invokestatic access$200 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CaptureCollector;
          //   1593: invokevirtual failAll : ()V
          //   1596: aload_0
          //   1597: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1600: aload #7
          //   1602: getfield surfaces : Ljava/util/Collection;
          //   1605: invokestatic access$600 : (Landroid/hardware/camera2/legacy/RequestThreadManager;Ljava/util/Collection;)V
          //   1608: aload #7
          //   1610: getfield condition : Landroid/os/ConditionVariable;
          //   1613: invokevirtual open : ()V
          //   1616: goto -> 1648
          //   1619: astore_1
          //   1620: aload_0
          //   1621: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1624: invokestatic access$100 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Ljava/lang/String;
          //   1627: ldc_w 'Interrupted while waiting for requests to complete.'
          //   1630: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
          //   1633: pop
          //   1634: aload_0
          //   1635: getfield this$0 : Landroid/hardware/camera2/legacy/RequestThreadManager;
          //   1638: invokestatic access$000 : (Landroid/hardware/camera2/legacy/RequestThreadManager;)Landroid/hardware/camera2/legacy/CameraDeviceState;
          //   1641: iconst_1
          //   1642: invokevirtual setError : (I)V
          //   1645: goto -> 1648
          //   1648: iconst_1
          //   1649: ireturn
          // Exception table:
          //   from	to	target	type
          //   46	88	91	java/lang/InterruptedException
          //   271	313	369	java/lang/InterruptedException
          //   325	336	363	finally
          //   340	354	363	finally
          //   357	360	363	finally
          //   364	367	363	finally
          //   585	600	619	java/lang/RuntimeException
          //   670	693	1323	java/io/IOException
          //   670	693	1293	java/lang/InterruptedException
          //   670	693	1263	java/lang/RuntimeException
          //   693	709	1259	java/io/IOException
          //   693	709	1255	java/lang/InterruptedException
          //   693	709	1251	java/lang/RuntimeException
          //   714	742	763	java/io/IOException
          //   714	742	759	java/lang/InterruptedException
          //   714	742	755	java/lang/RuntimeException
          //   742	752	799	java/io/IOException
          //   742	752	795	java/lang/InterruptedException
          //   742	752	791	java/lang/RuntimeException
          //   767	774	1247	java/io/IOException
          //   767	774	1243	java/lang/InterruptedException
          //   767	774	1239	java/lang/RuntimeException
          //   779	788	799	java/io/IOException
          //   779	788	795	java/lang/InterruptedException
          //   779	788	791	java/lang/RuntimeException
          //   803	810	1247	java/io/IOException
          //   803	810	1243	java/lang/InterruptedException
          //   803	810	1239	java/lang/RuntimeException
          //   815	858	799	java/io/IOException
          //   815	858	795	java/lang/InterruptedException
          //   815	858	791	java/lang/RuntimeException
          //   861	880	799	java/io/IOException
          //   861	880	795	java/lang/InterruptedException
          //   861	880	791	java/lang/RuntimeException
          //   880	925	1247	java/io/IOException
          //   880	925	1243	java/lang/InterruptedException
          //   880	925	1239	java/lang/RuntimeException
          //   930	979	799	java/io/IOException
          //   930	979	795	java/lang/InterruptedException
          //   930	979	791	java/lang/RuntimeException
          //   984	1002	1022	java/lang/RuntimeException
          //   1062	1084	1209	java/lang/InterruptedException
          //   1089	1113	1116	java/lang/InterruptedException
          //   1553	1596	1619	java/lang/InterruptedException
        }
      };
    this.mCamera = (Camera)Preconditions.checkNotNull(paramCamera, "camera must not be null");
    this.mCameraId = paramInt;
    this.mCharacteristics = (CameraCharacteristics)Preconditions.checkNotNull(paramCameraCharacteristics, "characteristics must not be null");
    String str = String.format("RequestThread-%d", new Object[] { Integer.valueOf(paramInt) });
    this.TAG = str;
    this.mDeviceState = (CameraDeviceState)Preconditions.checkNotNull(paramCameraDeviceState, "deviceState must not be null");
    this.mFocusStateMapper = new LegacyFocusStateMapper(this.mCamera);
    this.mFaceDetectMapper = new LegacyFaceDetectMapper(this.mCamera, this.mCharacteristics);
    this.mCaptureCollector = new CaptureCollector(2, this.mDeviceState);
    this.mRequestThread = new RequestHandlerThread(str, this.mRequestHandlerCb);
    this.mCamera.setDetailedErrorCallback(this.mErrorCallback);
  }
  
  private Size calculatePictureSize(List<Surface> paramList, List<Size> paramList1, Camera.Parameters paramParameters) {
    if (paramList.size() == paramList1.size()) {
      ArrayList<Size> arrayList = new ArrayList();
      Iterator<Size> iterator = paramList1.iterator();
      for (Surface surface : paramList) {
        Size size = iterator.next();
        if (!LegacyCameraDevice.containsSurfaceId(surface, this.mJpegSurfaceIds))
          continue; 
        arrayList.add(size);
      } 
      if (!arrayList.isEmpty()) {
        int i = -1;
        int j = -1;
        for (Size size1 : arrayList) {
          if (size1.getWidth() > i)
            i = size1.getWidth(); 
          if (size1.getHeight() > j)
            j = size1.getHeight(); 
        } 
        Size size = new Size(i, j);
        List<Size> list = ParameterUtils.convertSizeList(paramParameters.getSupportedPictureSizes());
        ArrayList<Size> arrayList1 = new ArrayList();
        for (Size size1 : list) {
          if (size1.getWidth() >= i && size1.getHeight() >= j)
            arrayList1.add(size1); 
        } 
        if (!arrayList1.isEmpty()) {
          Size size1 = Collections.<Size>min(arrayList1, (Comparator<? super Size>)new SizeAreaComparator());
          if (!size1.equals(size))
            Log.w(this.TAG, String.format("configureOutputs - Will need to crop picture %s into smallest bound size %s", new Object[] { size1, size })); 
          return size1;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not find any supported JPEG sizes large enough to fit ");
        stringBuilder.append(size);
        throw new AssertionError(stringBuilder.toString());
      } 
      return null;
    } 
    throw new IllegalStateException("Input collections must be same length");
  }
  
  private static boolean checkAspectRatiosMatch(Size paramSize1, Size paramSize2) {
    boolean bool;
    if (Math.abs(paramSize1.getWidth() / paramSize1.getHeight() - paramSize2.getWidth() / paramSize2.getHeight()) < 0.01F) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void configureOutputs(Collection<Pair<Surface, Size>> paramCollection) {
    try {
      stopPreview();
      try {
        this.mCamera.setPreviewTexture(null);
      } catch (IOException iOException) {
        Log.w(this.TAG, "Failed to clear prior SurfaceTexture, may cause GL deadlock: ", iOException);
      } catch (RuntimeException runtimeException) {
        Log.e(this.TAG, "Received device exception in configure call: ", runtimeException);
        this.mDeviceState.setError(1);
        return;
      } 
      GLThreadManager gLThreadManager = this.mGLThreadManager;
      if (gLThreadManager != null) {
        gLThreadManager.waitUntilStarted();
        this.mGLThreadManager.ignoreNewFrames();
        this.mGLThreadManager.waitUntilIdle();
      } 
      resetJpegSurfaceFormats(this.mCallbackOutputs);
      disconnectCallbackSurfaces();
      this.mPreviewOutputs.clear();
      this.mCallbackOutputs.clear();
      this.mJpegSurfaceIds.clear();
      this.mPreviewTexture = null;
      ArrayList<Size> arrayList1 = new ArrayList();
      ArrayList<Size> arrayList2 = new ArrayList();
      int i = ((Integer)this.mCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue();
      int j = ((Integer)this.mCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
      if (runtimeException != null)
        for (Pair pair : runtimeException) {
          Surface surface = (Surface)pair.first;
          Size size = (Size)pair.second;
          try {
            int k = LegacyCameraDevice.detectSurfaceType(surface);
            LegacyCameraDevice.setSurfaceOrientation(surface, i, j);
            if (k != 33) {
              LegacyCameraDevice.setScalingMode(surface, 1);
              this.mPreviewOutputs.add(surface);
              arrayList1.add(size);
              continue;
            } 
            LegacyCameraDevice.setSurfaceFormat(surface, 1);
            this.mJpegSurfaceIds.add(Long.valueOf(LegacyCameraDevice.getSurfaceId(surface)));
            this.mCallbackOutputs.add(surface);
            arrayList2.add(size);
            LegacyCameraDevice.connectSurface(surface);
          } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
            Log.w(this.TAG, "Surface abandoned, skipping...", (Throwable)bufferQueueAbandonedException);
          } 
        }  
      try {
        Camera.Parameters parameters = this.mCamera.getParameters();
        this.mParams = parameters;
        List<int[]> list = parameters.getSupportedPreviewFpsRange();
        int[] arrayOfInt = getPhotoPreviewFpsRange(list);
        this.mParams.setPreviewFpsRange(arrayOfInt[0], arrayOfInt[1]);
        Size size = calculatePictureSize(this.mCallbackOutputs, arrayList2, this.mParams);
        if (arrayList1.size() > 0) {
          Size size2 = SizeAreaComparator.findLargestByArea(arrayList1);
          Size size1 = ParameterUtils.getLargestSupportedJpegSizeByArea(this.mParams);
          if (size != null)
            size1 = size; 
          List<Size> list1 = ParameterUtils.convertSizeList(this.mParams.getSupportedPreviewSizes());
          long l1 = size2.getHeight();
          long l2 = size2.getWidth();
          Size size3 = SizeAreaComparator.findLargestByArea(list1);
          for (Size size4 : list1) {
            long l3 = (size4.getWidth() * size4.getHeight());
            long l4 = (size3.getWidth() * size3.getHeight());
            Size size5 = size3;
            if (checkAspectRatiosMatch(size1, size4)) {
              size5 = size3;
              if (l3 < l4) {
                size5 = size3;
                if (l3 >= l1 * l2)
                  size5 = size4; 
              } 
            } 
            size3 = size5;
          } 
          this.mIntermediateBufferSize = size3;
          this.mParams.setPreviewSize(size3.getWidth(), this.mIntermediateBufferSize.getHeight());
        } else {
          this.mIntermediateBufferSize = null;
        } 
        if (size != null) {
          String str = this.TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("configureOutputs - set take picture size to ");
          stringBuilder.append(size);
          Log.i(str, stringBuilder.toString());
          this.mParams.setPictureSize(size.getWidth(), size.getHeight());
        } 
        if (this.mGLThreadManager == null) {
          GLThreadManager gLThreadManager1 = new GLThreadManager(this.mCameraId, i, this.mDeviceState);
          this.mGLThreadManager = gLThreadManager1;
          gLThreadManager1.start();
        } 
        this.mGLThreadManager.waitUntilStarted();
        ArrayList<Pair> arrayList = new ArrayList();
        Iterator<Size> iterator = arrayList1.iterator();
        Iterator<Surface> iterator1 = this.mPreviewOutputs.iterator();
        while (iterator1.hasNext())
          arrayList.add(new Pair(iterator1.next(), iterator.next())); 
        this.mGLThreadManager.setConfigurationAndWait((Collection)arrayList, this.mCaptureCollector);
        for (Surface surface : this.mPreviewOutputs) {
          try {
            LegacyCameraDevice.setSurfaceOrientation(surface, i, j);
          } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
            Log.e(this.TAG, "Surface abandoned, skipping setSurfaceOrientation()", (Throwable)bufferQueueAbandonedException);
          } 
        } 
        this.mGLThreadManager.allowNewFrames();
        SurfaceTexture surfaceTexture = this.mGLThreadManager.getCurrentSurfaceTexture();
        this.mPreviewTexture = surfaceTexture;
        if (surfaceTexture != null)
          surfaceTexture.setOnFrameAvailableListener(this.mPreviewCallback); 
        try {
          this.mCamera.setParameters(this.mParams);
        } catch (RuntimeException runtimeException1) {
          Log.e(this.TAG, "Received device exception while configuring: ", runtimeException1);
          this.mDeviceState.setError(1);
        } 
        return;
      } catch (RuntimeException runtimeException1) {
        Log.e(this.TAG, "Received device exception: ", runtimeException1);
        this.mDeviceState.setError(1);
        return;
      } 
    } catch (RuntimeException runtimeException) {
      Log.e(this.TAG, "Received device exception in configure call: ", runtimeException);
      this.mDeviceState.setError(1);
      return;
    } 
  }
  
  private void createDummySurface() {
    if (this.mDummyTexture == null || this.mDummySurface == null) {
      SurfaceTexture surfaceTexture = new SurfaceTexture(0);
      this.mDummyTexture = surfaceTexture;
      surfaceTexture.setDefaultBufferSize(640, 480);
      this.mDummySurface = new Surface(this.mDummyTexture);
    } 
  }
  
  private void disconnectCallbackSurfaces() {
    for (Surface surface : this.mCallbackOutputs) {
      try {
        LegacyCameraDevice.disconnectSurface(surface);
      } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
        Log.d(this.TAG, "Surface abandoned, skipping...", (Throwable)bufferQueueAbandonedException);
      } 
    } 
  }
  
  private void doJpegCapture(RequestHolder paramRequestHolder) {
    this.mCamera.takePicture(this.mJpegShutterCallback, null, this.mJpegCallback);
    this.mPreviewRunning = false;
  }
  
  private void doJpegCapturePrepare(RequestHolder paramRequestHolder) throws IOException {
    if (!this.mPreviewRunning) {
      createDummySurface();
      this.mCamera.setPreviewTexture(this.mDummyTexture);
      startPreview();
    } 
  }
  
  private void doPreviewCapture(RequestHolder paramRequestHolder) throws IOException {
    if (this.mPreviewRunning)
      return; 
    SurfaceTexture surfaceTexture = this.mPreviewTexture;
    if (surfaceTexture != null) {
      surfaceTexture.setDefaultBufferSize(this.mIntermediateBufferSize.getWidth(), this.mIntermediateBufferSize.getHeight());
      this.mCamera.setPreviewTexture(this.mPreviewTexture);
      startPreview();
      return;
    } 
    throw new IllegalStateException("Preview capture called with no preview surfaces configured.");
  }
  
  private int[] getPhotoPreviewFpsRange(List<int[]> paramList) {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface size : ()I
    //   6: ifne -> 22
    //   9: aload_0
    //   10: getfield TAG : Ljava/lang/String;
    //   13: ldc_w 'No supported frame rates returned!'
    //   16: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   19: pop
    //   20: aconst_null
    //   21: areturn
    //   22: iconst_0
    //   23: istore_2
    //   24: iconst_0
    //   25: istore_3
    //   26: iconst_0
    //   27: istore #4
    //   29: iconst_0
    //   30: istore #5
    //   32: aload_1
    //   33: invokeinterface iterator : ()Ljava/util/Iterator;
    //   38: astore #6
    //   40: aload #6
    //   42: invokeinterface hasNext : ()Z
    //   47: ifeq -> 140
    //   50: aload #6
    //   52: invokeinterface next : ()Ljava/lang/Object;
    //   57: checkcast [I
    //   60: astore #7
    //   62: aload #7
    //   64: iconst_0
    //   65: iaload
    //   66: istore #8
    //   68: aload #7
    //   70: iconst_1
    //   71: iaload
    //   72: istore #9
    //   74: iload #9
    //   76: iload_3
    //   77: if_icmpgt -> 112
    //   80: iload_2
    //   81: istore #10
    //   83: iload_3
    //   84: istore #11
    //   86: iload #4
    //   88: istore #12
    //   90: iload #9
    //   92: iload_3
    //   93: if_icmpne -> 124
    //   96: iload_2
    //   97: istore #10
    //   99: iload_3
    //   100: istore #11
    //   102: iload #4
    //   104: istore #12
    //   106: iload #8
    //   108: iload_2
    //   109: if_icmple -> 124
    //   112: iload #8
    //   114: istore #10
    //   116: iload #9
    //   118: istore #11
    //   120: iload #5
    //   122: istore #12
    //   124: iinc #5, 1
    //   127: iload #10
    //   129: istore_2
    //   130: iload #11
    //   132: istore_3
    //   133: iload #12
    //   135: istore #4
    //   137: goto -> 40
    //   140: aload_1
    //   141: iload #4
    //   143: invokeinterface get : (I)Ljava/lang/Object;
    //   148: checkcast [I
    //   151: areturn
  }
  
  private void resetJpegSurfaceFormats(Collection<Surface> paramCollection) {
    if (paramCollection == null)
      return; 
    for (Surface surface : paramCollection) {
      if (surface == null || !surface.isValid()) {
        Log.w(this.TAG, "Jpeg surface is invalid, skipping...");
        continue;
      } 
      try {
        LegacyCameraDevice.setSurfaceFormat(surface, 33);
      } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
        Log.w(this.TAG, "Surface abandoned, skipping...", (Throwable)bufferQueueAbandonedException);
      } 
    } 
  }
  
  private void startPreview() {
    if (!this.mPreviewRunning) {
      this.mCamera.startPreview();
      this.mPreviewRunning = true;
    } 
  }
  
  private void stopPreview() {
    if (this.mPreviewRunning) {
      this.mCamera.stopPreview();
      this.mPreviewRunning = false;
    } 
  }
  
  public long cancelRepeating(int paramInt) {
    return this.mRequestQueue.stopRepeating(paramInt);
  }
  
  public void configure(Collection<Pair<Surface, Size>> paramCollection) {
    Handler handler = this.mRequestThread.waitAndGetHandler();
    ConditionVariable conditionVariable = new ConditionVariable(false);
    handler.sendMessage(handler.obtainMessage(1, 0, 0, new ConfigureHolder(conditionVariable, paramCollection)));
    conditionVariable.block();
  }
  
  public long flush() {
    Log.i(this.TAG, "Flushing all pending requests.");
    long l = this.mRequestQueue.stopRepeating();
    this.mCaptureCollector.failAll();
    return l;
  }
  
  public int getAudioRestriction() {
    Camera camera = this.mCamera;
    if (camera != null)
      return camera.getAudioRestriction(); 
    throw new IllegalStateException("Camera has been released!");
  }
  
  public void quit() {
    if (!this.mQuit.getAndSet(true)) {
      Handler handler = this.mRequestThread.waitAndGetHandler();
      handler.sendMessageAtFrontOfQueue(handler.obtainMessage(3));
      this.mRequestThread.quitSafely();
      try {
        this.mRequestThread.join();
      } catch (InterruptedException interruptedException) {
        Log.e(this.TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] { this.mRequestThread.getName(), Long.valueOf(this.mRequestThread.getId()) }));
      } 
    } 
  }
  
  public void setAudioRestriction(int paramInt) {
    Camera camera = this.mCamera;
    if (camera != null)
      camera.setAudioRestriction(paramInt); 
    throw new IllegalStateException("Camera has been released!");
  }
  
  public void start() {
    this.mRequestThread.start();
  }
  
  public SubmitInfo submitCaptureRequests(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) {
    Handler handler = this.mRequestThread.waitAndGetHandler();
    synchronized (this.mIdleLock) {
      SubmitInfo submitInfo = this.mRequestQueue.submit(paramArrayOfCaptureRequest, paramBoolean);
      handler.sendEmptyMessage(2);
      return submitInfo;
    } 
  }
  
  private static class ConfigureHolder {
    public final ConditionVariable condition;
    
    public final Collection<Pair<Surface, Size>> surfaces;
    
    public ConfigureHolder(ConditionVariable param1ConditionVariable, Collection<Pair<Surface, Size>> param1Collection) {
      this.condition = param1ConditionVariable;
      this.surfaces = param1Collection;
    }
  }
  
  public static class FpsCounter {
    private static final long NANO_PER_SECOND = 1000000000L;
    
    private static final String TAG = "FpsCounter";
    
    private int mFrameCount = 0;
    
    private double mLastFps = 0.0D;
    
    private long mLastPrintTime = 0L;
    
    private long mLastTime = 0L;
    
    private final String mStreamType;
    
    public FpsCounter(String param1String) {
      this.mStreamType = param1String;
    }
    
    public double checkFps() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mLastFps : D
      //   6: dstore_1
      //   7: aload_0
      //   8: monitorexit
      //   9: dload_1
      //   10: dreturn
      //   11: astore_3
      //   12: aload_0
      //   13: monitorexit
      //   14: aload_3
      //   15: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	11	finally
    }
    
    public void countAndLog() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: invokevirtual countFrame : ()V
      //   6: aload_0
      //   7: invokevirtual staggeredLog : ()V
      //   10: aload_0
      //   11: monitorexit
      //   12: return
      //   13: astore_1
      //   14: aload_0
      //   15: monitorexit
      //   16: aload_1
      //   17: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	13	finally
    }
    
    public void countFrame() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: aload_0
      //   4: getfield mFrameCount : I
      //   7: iconst_1
      //   8: iadd
      //   9: putfield mFrameCount : I
      //   12: invokestatic elapsedRealtimeNanos : ()J
      //   15: lstore_1
      //   16: aload_0
      //   17: getfield mLastTime : J
      //   20: lconst_0
      //   21: lcmp
      //   22: ifne -> 30
      //   25: aload_0
      //   26: lload_1
      //   27: putfield mLastTime : J
      //   30: lload_1
      //   31: aload_0
      //   32: getfield mLastTime : J
      //   35: ldc2_w 1000000000
      //   38: ladd
      //   39: lcmp
      //   40: ifle -> 76
      //   43: aload_0
      //   44: getfield mLastTime : J
      //   47: lstore_3
      //   48: aload_0
      //   49: aload_0
      //   50: getfield mFrameCount : I
      //   53: i2d
      //   54: ldc2_w 1.0E9
      //   57: lload_1
      //   58: lload_3
      //   59: lsub
      //   60: l2d
      //   61: ddiv
      //   62: dmul
      //   63: putfield mLastFps : D
      //   66: aload_0
      //   67: iconst_0
      //   68: putfield mFrameCount : I
      //   71: aload_0
      //   72: lload_1
      //   73: putfield mLastTime : J
      //   76: aload_0
      //   77: monitorexit
      //   78: return
      //   79: astore #5
      //   81: aload_0
      //   82: monitorexit
      //   83: aload #5
      //   85: athrow
      // Exception table:
      //   from	to	target	type
      //   2	30	79	finally
      //   30	76	79	finally
    }
    
    public void staggeredLog() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mLastTime : J
      //   6: aload_0
      //   7: getfield mLastPrintTime : J
      //   10: ldc2_w 5000000000
      //   13: ladd
      //   14: lcmp
      //   15: ifle -> 76
      //   18: aload_0
      //   19: aload_0
      //   20: getfield mLastTime : J
      //   23: putfield mLastPrintTime : J
      //   26: new java/lang/StringBuilder
      //   29: astore_1
      //   30: aload_1
      //   31: invokespecial <init> : ()V
      //   34: aload_1
      //   35: ldc 'FPS for '
      //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   40: pop
      //   41: aload_1
      //   42: aload_0
      //   43: getfield mStreamType : Ljava/lang/String;
      //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   49: pop
      //   50: aload_1
      //   51: ldc ' stream: '
      //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   56: pop
      //   57: aload_1
      //   58: aload_0
      //   59: getfield mLastFps : D
      //   62: invokevirtual append : (D)Ljava/lang/StringBuilder;
      //   65: pop
      //   66: ldc 'FpsCounter'
      //   68: aload_1
      //   69: invokevirtual toString : ()Ljava/lang/String;
      //   72: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
      //   75: pop
      //   76: aload_0
      //   77: monitorexit
      //   78: return
      //   79: astore_1
      //   80: aload_0
      //   81: monitorexit
      //   82: aload_1
      //   83: athrow
      // Exception table:
      //   from	to	target	type
      //   2	76	79	finally
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */