package android.hardware.camera2.legacy;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.util.Log;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class LegacyCameraDevice implements AutoCloseable {
  private static final boolean DEBUG = false;
  
  private static final int GRALLOC_USAGE_HW_COMPOSER = 2048;
  
  private static final int GRALLOC_USAGE_HW_RENDER = 512;
  
  private static final int GRALLOC_USAGE_HW_TEXTURE = 256;
  
  private static final int GRALLOC_USAGE_HW_VIDEO_ENCODER = 65536;
  
  private static final int GRALLOC_USAGE_RENDERSCRIPT = 1048576;
  
  private static final int GRALLOC_USAGE_SW_READ_OFTEN = 3;
  
  private static final int ILLEGAL_VALUE = -1;
  
  public static final int MAX_DIMEN_FOR_ROUNDING = 1920;
  
  public static final int NATIVE_WINDOW_SCALING_MODE_SCALE_TO_WINDOW = 1;
  
  private final String TAG;
  
  private final Handler mCallbackHandler;
  
  private final HandlerThread mCallbackHandlerThread = new HandlerThread("CallbackThread");
  
  private final int mCameraId;
  
  private boolean mClosed = false;
  
  private SparseArray<Surface> mConfiguredSurfaces;
  
  private final ICameraDeviceCallbacks mDeviceCallbacks;
  
  private final CameraDeviceState mDeviceState = new CameraDeviceState();
  
  private final ConditionVariable mIdle = new ConditionVariable(true);
  
  private final RequestThreadManager mRequestThreadManager;
  
  private final Handler mResultHandler;
  
  private final HandlerThread mResultThread = new HandlerThread("ResultThread");
  
  private final CameraDeviceState.CameraDeviceStateListener mStateListener = new CameraDeviceState.CameraDeviceStateListener() {
      public void onBusy() {
        LegacyCameraDevice.this.mIdle.close();
      }
      
      public void onCaptureResult(final CameraMetadataNative result, final RequestHolder holder) {
        final CaptureResultExtras extras = LegacyCameraDevice.this.getExtrasFromRequest(holder);
        LegacyCameraDevice.this.mResultHandler.post(new Runnable() {
              public void run() {
                try {
                  LegacyCameraDevice.this.mDeviceCallbacks.onResultReceived(result, extras, new android.hardware.camera2.impl.PhysicalCaptureResultInfo[0]);
                  return;
                } catch (RemoteException remoteException) {
                  throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
                } 
              }
            });
      }
      
      public void onCaptureStarted(final RequestHolder holder, final long timestamp) {
        final CaptureResultExtras extras = LegacyCameraDevice.this.getExtrasFromRequest(holder);
        LegacyCameraDevice.this.mResultHandler.post(new Runnable() {
              public void run() {
                try {
                  LegacyCameraDevice.this.mDeviceCallbacks.onCaptureStarted(extras, timestamp);
                  return;
                } catch (RemoteException remoteException) {
                  throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
                } 
              }
            });
      }
      
      public void onConfiguring() {}
      
      public void onError(final int errorCode, final Object extras, final RequestHolder holder) {
        if (errorCode == 0 || errorCode == 1 || errorCode == 2)
          LegacyCameraDevice.this.mIdle.open(); 
        extras = LegacyCameraDevice.this.getExtrasFromRequest(holder, errorCode, extras);
        LegacyCameraDevice.this.mResultHandler.post(new Runnable() {
              public void run() {
                try {
                  LegacyCameraDevice.this.mDeviceCallbacks.onDeviceError(errorCode, extras);
                  return;
                } catch (RemoteException remoteException) {
                  throw new IllegalStateException("Received remote exception during onCameraError callback: ", remoteException);
                } 
              }
            });
      }
      
      public void onIdle() {
        LegacyCameraDevice.this.mIdle.open();
        LegacyCameraDevice.this.mResultHandler.post(new Runnable() {
              public void run() {
                try {
                  LegacyCameraDevice.this.mDeviceCallbacks.onDeviceIdle();
                  return;
                } catch (RemoteException remoteException) {
                  throw new IllegalStateException("Received remote exception during onCameraIdle callback: ", remoteException);
                } 
              }
            });
      }
      
      public void onRepeatingRequestError(final long lastFrameNumber, final int repeatingRequestId) {
        LegacyCameraDevice.this.mResultHandler.post(new Runnable() {
              public void run() {
                try {
                  LegacyCameraDevice.this.mDeviceCallbacks.onRepeatingRequestError(lastFrameNumber, repeatingRequestId);
                  return;
                } catch (RemoteException remoteException) {
                  throw new IllegalStateException("Received remote exception during onRepeatingRequestError callback: ", remoteException);
                } 
              }
            });
      }
      
      public void onRequestQueueEmpty() {
        LegacyCameraDevice.this.mResultHandler.post(new Runnable() {
              public void run() {
                try {
                  LegacyCameraDevice.this.mDeviceCallbacks.onRequestQueueEmpty();
                  return;
                } catch (RemoteException remoteException) {
                  throw new IllegalStateException("Received remote exception during onRequestQueueEmpty callback: ", remoteException);
                } 
              }
            });
      }
    };
  
  private final CameraCharacteristics mStaticCharacteristics;
  
  public LegacyCameraDevice(int paramInt, Camera paramCamera, CameraCharacteristics paramCameraCharacteristics, ICameraDeviceCallbacks paramICameraDeviceCallbacks) {
    this.mCameraId = paramInt;
    this.mDeviceCallbacks = paramICameraDeviceCallbacks;
    this.TAG = String.format("CameraDevice-%d-LE", new Object[] { Integer.valueOf(paramInt) });
    this.mResultThread.start();
    this.mResultHandler = new Handler(this.mResultThread.getLooper());
    this.mCallbackHandlerThread.start();
    Handler handler = new Handler(this.mCallbackHandlerThread.getLooper());
    this.mCallbackHandler = handler;
    this.mDeviceState.setCameraDeviceCallbacks(handler, this.mStateListener);
    this.mStaticCharacteristics = paramCameraCharacteristics;
    RequestThreadManager requestThreadManager = new RequestThreadManager(paramInt, paramCamera, paramCameraCharacteristics, this.mDeviceState);
    this.mRequestThreadManager = requestThreadManager;
    requestThreadManager.start();
  }
  
  static void connectSurface(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    LegacyExceptionUtils.throwOnError(nativeConnectSurface(paramSurface));
  }
  
  static boolean containsSurfaceId(Surface paramSurface, Collection<Long> paramCollection) {
    try {
      long l = getSurfaceId(paramSurface);
      return paramCollection.contains(Long.valueOf(l));
    } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
      return false;
    } 
  }
  
  public static int detectSurfaceDataspace(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    return LegacyExceptionUtils.throwOnError(nativeDetectSurfaceDataspace(paramSurface));
  }
  
  public static int detectSurfaceType(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    int i = nativeDetectSurfaceType(paramSurface);
    int j = i;
    if (i >= 1) {
      j = i;
      if (i <= 5)
        j = 34; 
    } 
    return LegacyExceptionUtils.throwOnError(j);
  }
  
  static int detectSurfaceUsageFlags(Surface paramSurface) {
    Preconditions.checkNotNull(paramSurface);
    return nativeDetectSurfaceUsageFlags(paramSurface);
  }
  
  static void disconnectSurface(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    if (paramSurface == null)
      return; 
    LegacyExceptionUtils.throwOnError(nativeDisconnectSurface(paramSurface));
  }
  
  static Size findClosestSize(Size paramSize, Size[] paramArrayOfSize) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 94
    //   4: aload_1
    //   5: ifnonnull -> 11
    //   8: goto -> 94
    //   11: aconst_null
    //   12: astore_2
    //   13: aload_1
    //   14: arraylength
    //   15: istore_3
    //   16: iconst_0
    //   17: istore #4
    //   19: iload #4
    //   21: iload_3
    //   22: if_icmpge -> 92
    //   25: aload_1
    //   26: iload #4
    //   28: aaload
    //   29: astore #5
    //   31: aload #5
    //   33: aload_0
    //   34: invokevirtual equals : (Ljava/lang/Object;)Z
    //   37: ifeq -> 42
    //   40: aload_0
    //   41: areturn
    //   42: aload_2
    //   43: astore #6
    //   45: aload #5
    //   47: invokevirtual getWidth : ()I
    //   50: sipush #1920
    //   53: if_icmpgt -> 83
    //   56: aload_2
    //   57: ifnull -> 79
    //   60: aload_2
    //   61: astore #6
    //   63: aload_0
    //   64: aload #5
    //   66: invokestatic findEuclidDistSquare : (Landroid/util/Size;Landroid/util/Size;)J
    //   69: aload_2
    //   70: aload #5
    //   72: invokestatic findEuclidDistSquare : (Landroid/util/Size;Landroid/util/Size;)J
    //   75: lcmp
    //   76: ifge -> 83
    //   79: aload #5
    //   81: astore #6
    //   83: iinc #4, 1
    //   86: aload #6
    //   88: astore_2
    //   89: goto -> 19
    //   92: aload_2
    //   93: areturn
    //   94: aconst_null
    //   95: areturn
  }
  
  static long findEuclidDistSquare(Size paramSize1, Size paramSize2) {
    long l1 = (paramSize1.getWidth() - paramSize2.getWidth());
    long l2 = (paramSize1.getHeight() - paramSize2.getHeight());
    return l1 * l1 + l2 * l2;
  }
  
  private CaptureResultExtras getExtrasFromRequest(RequestHolder paramRequestHolder) {
    return getExtrasFromRequest(paramRequestHolder, -1, null);
  }
  
  private CaptureResultExtras getExtrasFromRequest(RequestHolder paramRequestHolder, int paramInt, Object paramObject) {
    byte b = -1;
    int i = b;
    if (paramInt == 5) {
      paramObject = paramObject;
      paramInt = this.mConfiguredSurfaces.indexOfValue(paramObject);
      if (paramInt < 0) {
        Log.e(this.TAG, "Buffer drop error reported for unknown Surface");
        i = b;
      } else {
        i = this.mConfiguredSurfaces.keyAt(paramInt);
      } 
    } 
    return (paramRequestHolder == null) ? new CaptureResultExtras(-1, -1, -1, -1, -1L, -1, -1, null, -1L, -1L, -1L) : new CaptureResultExtras(paramRequestHolder.getRequestId(), paramRequestHolder.getSubsequeceId(), 0, 0, paramRequestHolder.getFrameNumber(), 1, i, null, paramRequestHolder.getFrameNumber(), -1L, -1L);
  }
  
  public static long getSurfaceId(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    try {
      return nativeGetSurfaceId(paramSurface);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new LegacyExceptionUtils.BufferQueueAbandonedException();
    } 
  }
  
  static List<Long> getSurfaceIds(SparseArray<Surface> paramSparseArray) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    if (paramSparseArray != null) {
      ArrayList<Long> arrayList = new ArrayList();
      int i = paramSparseArray.size();
      byte b = 0;
      while (b < i) {
        long l = getSurfaceId((Surface)paramSparseArray.valueAt(b));
        if (l != 0L) {
          arrayList.add(Long.valueOf(l));
          b++;
          continue;
        } 
        throw new IllegalStateException("Configured surface had null native GraphicBufferProducer pointer!");
      } 
      return arrayList;
    } 
    throw new NullPointerException("Null argument surfaces");
  }
  
  static List<Long> getSurfaceIds(Collection<Surface> paramCollection) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    if (paramCollection != null) {
      ArrayList<Long> arrayList = new ArrayList();
      Iterator<Surface> iterator = paramCollection.iterator();
      while (iterator.hasNext()) {
        long l = getSurfaceId(iterator.next());
        if (l != 0L) {
          arrayList.add(Long.valueOf(l));
          continue;
        } 
        throw new IllegalStateException("Configured surface had null native GraphicBufferProducer pointer!");
      } 
      return arrayList;
    } 
    throw new NullPointerException("Null argument surfaces");
  }
  
  public static Size getSurfaceSize(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    int[] arrayOfInt = new int[2];
    LegacyExceptionUtils.throwOnError(nativeDetectSurfaceDimens(paramSurface, arrayOfInt));
    return new Size(arrayOfInt[0], arrayOfInt[1]);
  }
  
  static Size getTextureSize(SurfaceTexture paramSurfaceTexture) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurfaceTexture);
    int[] arrayOfInt = new int[2];
    LegacyExceptionUtils.throwOnError(nativeDetectTextureDimens(paramSurfaceTexture, arrayOfInt));
    return new Size(arrayOfInt[0], arrayOfInt[1]);
  }
  
  public static boolean isFlexibleConsumer(Surface paramSurface) {
    boolean bool;
    int i = detectSurfaceUsageFlags(paramSurface);
    if ((i & 0x110000) == 0 && (i & 0x903) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isPreviewConsumer(Surface paramSurface) {
    boolean bool;
    int i = detectSurfaceUsageFlags(paramSurface);
    if ((i & 0x110003) == 0 && (i & 0xB00) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    try {
      detectSurfaceType(paramSurface);
      return bool;
    } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
      throw new IllegalArgumentException("Surface was abandoned", bufferQueueAbandonedException);
    } 
  }
  
  public static boolean isVideoEncoderConsumer(Surface paramSurface) {
    boolean bool;
    int i = detectSurfaceUsageFlags(paramSurface);
    if ((i & 0x100903) == 0 && (i & 0x10000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    try {
      detectSurfaceType(paramSurface);
      return bool;
    } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
      throw new IllegalArgumentException("Surface was abandoned", bufferQueueAbandonedException);
    } 
  }
  
  private static native int nativeConnectSurface(Surface paramSurface);
  
  private static native int nativeDetectSurfaceDataspace(Surface paramSurface);
  
  private static native int nativeDetectSurfaceDimens(Surface paramSurface, int[] paramArrayOfint);
  
  private static native int nativeDetectSurfaceType(Surface paramSurface);
  
  private static native int nativeDetectSurfaceUsageFlags(Surface paramSurface);
  
  private static native int nativeDetectTextureDimens(SurfaceTexture paramSurfaceTexture, int[] paramArrayOfint);
  
  private static native int nativeDisconnectSurface(Surface paramSurface);
  
  static native int nativeGetJpegFooterSize();
  
  private static native long nativeGetSurfaceId(Surface paramSurface);
  
  private static native int nativeProduceFrame(Surface paramSurface, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3);
  
  private static native int nativeSetNextTimestamp(Surface paramSurface, long paramLong);
  
  private static native int nativeSetScalingMode(Surface paramSurface, int paramInt);
  
  private static native int nativeSetSurfaceDimens(Surface paramSurface, int paramInt1, int paramInt2);
  
  private static native int nativeSetSurfaceFormat(Surface paramSurface, int paramInt);
  
  private static native int nativeSetSurfaceOrientation(Surface paramSurface, int paramInt1, int paramInt2);
  
  static boolean needsConversion(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    int i = detectSurfaceType(paramSurface);
    return (i == 35 || i == 842094169 || i == 17);
  }
  
  static void produceFrame(Surface paramSurface, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    Preconditions.checkNotNull(paramArrayOfbyte);
    Preconditions.checkArgumentPositive(paramInt1, "width must be positive.");
    Preconditions.checkArgumentPositive(paramInt2, "height must be positive.");
    LegacyExceptionUtils.throwOnError(nativeProduceFrame(paramSurface, paramArrayOfbyte, paramInt1, paramInt2, paramInt3));
  }
  
  static void setNextTimestamp(Surface paramSurface, long paramLong) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    LegacyExceptionUtils.throwOnError(nativeSetNextTimestamp(paramSurface, paramLong));
  }
  
  static void setScalingMode(Surface paramSurface, int paramInt) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    LegacyExceptionUtils.throwOnError(nativeSetScalingMode(paramSurface, paramInt));
  }
  
  static void setSurfaceDimens(Surface paramSurface, int paramInt1, int paramInt2) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    Preconditions.checkArgumentPositive(paramInt1, "width must be positive.");
    Preconditions.checkArgumentPositive(paramInt2, "height must be positive.");
    LegacyExceptionUtils.throwOnError(nativeSetSurfaceDimens(paramSurface, paramInt1, paramInt2));
  }
  
  static void setSurfaceFormat(Surface paramSurface, int paramInt) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    LegacyExceptionUtils.throwOnError(nativeSetSurfaceFormat(paramSurface, paramInt));
  }
  
  static void setSurfaceOrientation(Surface paramSurface, int paramInt1, int paramInt2) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    Preconditions.checkNotNull(paramSurface);
    LegacyExceptionUtils.throwOnError(nativeSetSurfaceOrientation(paramSurface, paramInt1, paramInt2));
  }
  
  public long cancelRequest(int paramInt) {
    return this.mRequestThreadManager.cancelRepeating(paramInt);
  }
  
  public void close() {
    this.mRequestThreadManager.quit();
    this.mCallbackHandlerThread.quitSafely();
    this.mResultThread.quitSafely();
    try {
      this.mCallbackHandlerThread.join();
    } catch (InterruptedException interruptedException) {
      Log.e(this.TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] { this.mCallbackHandlerThread.getName(), Long.valueOf(this.mCallbackHandlerThread.getId()) }));
    } 
    try {
      this.mResultThread.join();
    } catch (InterruptedException interruptedException) {
      Log.e(this.TAG, String.format("Thread %s (%d) interrupted while quitting.", new Object[] { this.mResultThread.getName(), Long.valueOf(this.mResultThread.getId()) }));
    } 
    this.mClosed = true;
  }
  
  public int configureOutputs(SparseArray<Surface> paramSparseArray) {
    return configureOutputs(paramSparseArray, false);
  }
  
  public int configureOutputs(SparseArray<Surface> paramSparseArray, boolean paramBoolean) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: aload_1
    //   9: ifnull -> 407
    //   12: aload_1
    //   13: invokevirtual size : ()I
    //   16: istore #4
    //   18: iconst_0
    //   19: istore #5
    //   21: iload #5
    //   23: iload #4
    //   25: if_icmpge -> 407
    //   28: aload_1
    //   29: iload #5
    //   31: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   34: checkcast android/view/Surface
    //   37: astore #6
    //   39: aload #6
    //   41: ifnonnull -> 59
    //   44: aload_0
    //   45: getfield TAG : Ljava/lang/String;
    //   48: ldc_w 'configureOutputs - null outputs are not allowed'
    //   51: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   54: pop
    //   55: getstatic android/hardware/camera2/legacy/LegacyExceptionUtils.BAD_VALUE : I
    //   58: ireturn
    //   59: aload #6
    //   61: invokevirtual isValid : ()Z
    //   64: ifne -> 82
    //   67: aload_0
    //   68: getfield TAG : Ljava/lang/String;
    //   71: ldc_w 'configureOutputs - invalid output surfaces are not allowed'
    //   74: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   77: pop
    //   78: getstatic android/hardware/camera2/legacy/LegacyExceptionUtils.BAD_VALUE : I
    //   81: ireturn
    //   82: aload_0
    //   83: getfield mStaticCharacteristics : Landroid/hardware/camera2/CameraCharacteristics;
    //   86: getstatic android/hardware/camera2/CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   89: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   92: checkcast android/hardware/camera2/params/StreamConfigurationMap
    //   95: astore #7
    //   97: aload #6
    //   99: invokestatic getSurfaceSize : (Landroid/view/Surface;)Landroid/util/Size;
    //   102: astore #8
    //   104: aload #6
    //   106: invokestatic detectSurfaceType : (Landroid/view/Surface;)I
    //   109: istore #9
    //   111: aload #6
    //   113: invokestatic isFlexibleConsumer : (Landroid/view/Surface;)Z
    //   116: istore #10
    //   118: aload #7
    //   120: iload #9
    //   122: invokevirtual getOutputSizes : (I)[Landroid/util/Size;
    //   125: astore #11
    //   127: aload #11
    //   129: astore #12
    //   131: aload #11
    //   133: ifnonnull -> 176
    //   136: iload #9
    //   138: bipush #34
    //   140: if_icmpne -> 155
    //   143: aload #7
    //   145: bipush #35
    //   147: invokevirtual getOutputSizes : (I)[Landroid/util/Size;
    //   150: astore #12
    //   152: goto -> 176
    //   155: aload #11
    //   157: astore #12
    //   159: iload #9
    //   161: bipush #33
    //   163: if_icmpne -> 176
    //   166: aload #7
    //   168: sipush #256
    //   171: invokevirtual getOutputSizes : (I)[Landroid/util/Size;
    //   174: astore #12
    //   176: aload #12
    //   178: aload #8
    //   180: invokestatic contains : ([Ljava/lang/Object;Ljava/lang/Object;)Z
    //   183: ifne -> 342
    //   186: aload #8
    //   188: astore #11
    //   190: iload #10
    //   192: ifeq -> 243
    //   195: aload #8
    //   197: aload #12
    //   199: invokestatic findClosestSize : (Landroid/util/Size;[Landroid/util/Size;)Landroid/util/Size;
    //   202: astore #7
    //   204: aload #7
    //   206: astore #8
    //   208: aload #8
    //   210: astore #11
    //   212: aload #7
    //   214: ifnull -> 243
    //   217: new android/util/Pair
    //   220: astore #12
    //   222: aload #12
    //   224: aload #6
    //   226: aload #8
    //   228: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   231: aload_3
    //   232: aload #12
    //   234: invokeinterface add : (Ljava/lang/Object;)Z
    //   239: pop
    //   240: goto -> 365
    //   243: aload #12
    //   245: ifnonnull -> 255
    //   248: ldc_w 'format is invalid.'
    //   251: astore_1
    //   252: goto -> 286
    //   255: new java/lang/StringBuilder
    //   258: astore_1
    //   259: aload_1
    //   260: invokespecial <init> : ()V
    //   263: aload_1
    //   264: ldc_w 'size not in valid set: '
    //   267: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: pop
    //   271: aload_1
    //   272: aload #12
    //   274: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   277: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: pop
    //   281: aload_1
    //   282: invokevirtual toString : ()Ljava/lang/String;
    //   285: astore_1
    //   286: aload_0
    //   287: getfield TAG : Ljava/lang/String;
    //   290: ldc_w 'Surface with size (w=%d, h=%d) and format 0x%x is not valid, %s'
    //   293: iconst_4
    //   294: anewarray java/lang/Object
    //   297: dup
    //   298: iconst_0
    //   299: aload #11
    //   301: invokevirtual getWidth : ()I
    //   304: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   307: aastore
    //   308: dup
    //   309: iconst_1
    //   310: aload #11
    //   312: invokevirtual getHeight : ()I
    //   315: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   318: aastore
    //   319: dup
    //   320: iconst_2
    //   321: iload #9
    //   323: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   326: aastore
    //   327: dup
    //   328: iconst_3
    //   329: aload_1
    //   330: aastore
    //   331: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   334: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   337: pop
    //   338: getstatic android/hardware/camera2/legacy/LegacyExceptionUtils.BAD_VALUE : I
    //   341: ireturn
    //   342: new android/util/Pair
    //   345: astore #12
    //   347: aload #12
    //   349: aload #6
    //   351: aload #8
    //   353: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   356: aload_3
    //   357: aload #12
    //   359: invokeinterface add : (Ljava/lang/Object;)Z
    //   364: pop
    //   365: iload_2
    //   366: ifne -> 384
    //   369: aload #6
    //   371: aload #8
    //   373: invokevirtual getWidth : ()I
    //   376: aload #8
    //   378: invokevirtual getHeight : ()I
    //   381: invokestatic setSurfaceDimens : (Landroid/view/Surface;II)V
    //   384: iinc #5, 1
    //   387: goto -> 21
    //   390: astore_1
    //   391: aload_0
    //   392: getfield TAG : Ljava/lang/String;
    //   395: ldc_w 'Surface bufferqueue is abandoned, cannot configure as output: '
    //   398: aload_1
    //   399: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   402: pop
    //   403: getstatic android/hardware/camera2/legacy/LegacyExceptionUtils.BAD_VALUE : I
    //   406: ireturn
    //   407: iload_2
    //   408: ifeq -> 413
    //   411: iconst_0
    //   412: ireturn
    //   413: iconst_0
    //   414: istore_2
    //   415: aload_0
    //   416: getfield mDeviceState : Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   419: invokevirtual setConfiguring : ()Z
    //   422: ifeq -> 441
    //   425: aload_0
    //   426: getfield mRequestThreadManager : Landroid/hardware/camera2/legacy/RequestThreadManager;
    //   429: aload_3
    //   430: invokevirtual configure : (Ljava/util/Collection;)V
    //   433: aload_0
    //   434: getfield mDeviceState : Landroid/hardware/camera2/legacy/CameraDeviceState;
    //   437: invokevirtual setIdle : ()Z
    //   440: istore_2
    //   441: iload_2
    //   442: ifeq -> 452
    //   445: aload_0
    //   446: aload_1
    //   447: putfield mConfiguredSurfaces : Landroid/util/SparseArray;
    //   450: iconst_0
    //   451: ireturn
    //   452: getstatic android/hardware/camera2/legacy/LegacyExceptionUtils.INVALID_OPERATION : I
    //   455: ireturn
    // Exception table:
    //   from	to	target	type
    //   97	127	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   143	152	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   166	176	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   176	186	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   195	204	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   217	240	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   255	286	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   286	342	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   342	365	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
    //   369	384	390	android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      super.finalize();
    } catch (ServiceSpecificException serviceSpecificException) {
      String str = this.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Got error while trying to finalize, ignoring: ");
      stringBuilder.append(serviceSpecificException.getMessage());
      Log.e(str, stringBuilder.toString());
      super.finalize();
    } finally {
      Exception exception;
    } 
  }
  
  public long flush() {
    long l = this.mRequestThreadManager.flush();
    waitUntilIdle();
    return l;
  }
  
  public int getAudioRestriction() {
    return this.mRequestThreadManager.getAudioRestriction();
  }
  
  public boolean isClosed() {
    return this.mClosed;
  }
  
  public void setAudioRestriction(int paramInt) {
    this.mRequestThreadManager.setAudioRestriction(paramInt);
  }
  
  public SubmitInfo submitRequest(CaptureRequest paramCaptureRequest, boolean paramBoolean) {
    return submitRequestList(new CaptureRequest[] { paramCaptureRequest }, paramBoolean);
  }
  
  public SubmitInfo submitRequestList(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) {
    if (paramArrayOfCaptureRequest != null && paramArrayOfCaptureRequest.length != 0)
      try {
        List<Long> list;
        if (this.mConfiguredSurfaces == null) {
          list = new ArrayList();
        } else {
          list = getSurfaceIds(this.mConfiguredSurfaces);
        } 
        int i = paramArrayOfCaptureRequest.length;
        byte b = 0;
        while (b < i) {
          CaptureRequest captureRequest = paramArrayOfCaptureRequest[b];
          if (!captureRequest.getTargets().isEmpty()) {
            for (Surface surface : captureRequest.getTargets()) {
              if (surface != null) {
                if (this.mConfiguredSurfaces != null) {
                  if (containsSurfaceId(surface, list))
                    continue; 
                  Log.e(this.TAG, "submitRequestList - cannot use a surface that wasn't configured");
                  throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - cannot use a surface that wasn't configured");
                } 
                Log.e(this.TAG, "submitRequestList - must configure  device with valid surfaces before submitting requests");
                throw new ServiceSpecificException(LegacyExceptionUtils.INVALID_OPERATION, "submitRequestList - must configure  device with valid surfaces before submitting requests");
              } 
              Log.e(this.TAG, "submitRequestList - Null Surface targets are not allowed");
              throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - Null Surface targets are not allowed");
            } 
            b++;
            continue;
          } 
          Log.e(this.TAG, "submitRequestList - Each request must have at least one Surface target");
          throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - Each request must have at least one Surface target");
        } 
        this.mIdle.close();
        return this.mRequestThreadManager.submitCaptureRequests(paramArrayOfCaptureRequest, paramBoolean);
      } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
        throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - configured surface is abandoned.");
      }  
    Log.e(this.TAG, "submitRequestList - Empty/null requests are not allowed");
    throw new ServiceSpecificException(LegacyExceptionUtils.BAD_VALUE, "submitRequestList - Empty/null requests are not allowed");
  }
  
  public void waitUntilIdle() {
    this.mIdle.block();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyCameraDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */