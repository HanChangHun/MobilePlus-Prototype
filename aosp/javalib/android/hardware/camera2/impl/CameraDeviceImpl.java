package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraOfflineSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

public class CameraDeviceImpl extends CameraDevice implements IBinder.DeathRecipient {
  private static final long NANO_PER_SECOND = 1000000000L;
  
  private static final int REQUEST_ID_NONE = -1;
  
  private final boolean DEBUG = false;
  
  private final String TAG;
  
  private final int mAppTargetSdkVersion;
  
  private final Runnable mCallOnActive = new Runnable() {
      public void run() {
        synchronized (CameraDeviceImpl.this.mInterfaceLock) {
          if (CameraDeviceImpl.this.mRemoteDevice == null)
            return; 
          CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
          if (stateCallbackKK != null)
            stateCallbackKK.onActive(CameraDeviceImpl.this); 
          return;
        } 
      }
    };
  
  private final Runnable mCallOnBusy = new Runnable() {
      public void run() {
        synchronized (CameraDeviceImpl.this.mInterfaceLock) {
          if (CameraDeviceImpl.this.mRemoteDevice == null)
            return; 
          CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
          if (stateCallbackKK != null)
            stateCallbackKK.onBusy(CameraDeviceImpl.this); 
          return;
        } 
      }
    };
  
  private final Runnable mCallOnClosed = new Runnable() {
      private boolean mClosedOnce = false;
      
      public void run() {
        if (!this.mClosedOnce)
          synchronized (CameraDeviceImpl.this.mInterfaceLock) {
            CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
            if (stateCallbackKK != null)
              stateCallbackKK.onClosed(CameraDeviceImpl.this); 
            CameraDeviceImpl.this.mDeviceCallback.onClosed(CameraDeviceImpl.this);
            this.mClosedOnce = true;
            return;
          }  
        throw new AssertionError("Don't post #onClosed more than once");
      }
    };
  
  private final Runnable mCallOnDisconnected = new Runnable() {
      public void run() {
        synchronized (CameraDeviceImpl.this.mInterfaceLock) {
          if (CameraDeviceImpl.this.mRemoteDevice == null)
            return; 
          CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
          if (stateCallbackKK != null)
            stateCallbackKK.onDisconnected(CameraDeviceImpl.this); 
          CameraDeviceImpl.this.mDeviceCallback.onDisconnected(CameraDeviceImpl.this);
          return;
        } 
      }
    };
  
  private final Runnable mCallOnIdle = new Runnable() {
      public void run() {
        synchronized (CameraDeviceImpl.this.mInterfaceLock) {
          if (CameraDeviceImpl.this.mRemoteDevice == null)
            return; 
          CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
          if (stateCallbackKK != null)
            stateCallbackKK.onIdle(CameraDeviceImpl.this); 
          return;
        } 
      }
    };
  
  private final Runnable mCallOnOpened = new Runnable() {
      public void run() {
        synchronized (CameraDeviceImpl.this.mInterfaceLock) {
          if (CameraDeviceImpl.this.mRemoteDevice == null)
            return; 
          CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
          if (stateCallbackKK != null)
            stateCallbackKK.onOpened(CameraDeviceImpl.this); 
          CameraDeviceImpl.this.mDeviceCallback.onOpened(CameraDeviceImpl.this);
          return;
        } 
      }
    };
  
  private final Runnable mCallOnUnconfigured = new Runnable() {
      public void run() {
        synchronized (CameraDeviceImpl.this.mInterfaceLock) {
          if (CameraDeviceImpl.this.mRemoteDevice == null)
            return; 
          CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
          if (stateCallbackKK != null)
            stateCallbackKK.onUnconfigured(CameraDeviceImpl.this); 
          return;
        } 
      }
    };
  
  private final CameraDeviceCallbacks mCallbacks = new CameraDeviceCallbacks();
  
  private final String mCameraId;
  
  private SparseArray<CaptureCallbackHolder> mCaptureCallbackMap = new SparseArray();
  
  private final CameraCharacteristics mCharacteristics;
  
  private final AtomicBoolean mClosing = new AtomicBoolean();
  
  private AbstractMap.SimpleEntry<Integer, InputConfiguration> mConfiguredInput = new AbstractMap.SimpleEntry<>(Integer.valueOf(-1), null);
  
  private final SparseArray<OutputConfiguration> mConfiguredOutputs = new SparseArray();
  
  private CameraCaptureSessionCore mCurrentSession;
  
  private final CameraDevice.StateCallback mDeviceCallback;
  
  private final Executor mDeviceExecutor;
  
  private FrameNumberTracker mFrameNumberTracker = new FrameNumberTracker();
  
  private boolean mIdle = true;
  
  private boolean mInError = false;
  
  final Object mInterfaceLock = new Object();
  
  private int mNextSessionId = 0;
  
  private CameraOfflineSessionImpl mOfflineSessionImpl;
  
  private final HashSet<Integer> mOfflineSupport = new HashSet<>();
  
  private ExecutorService mOfflineSwitchService;
  
  private ICameraDeviceUserWrapper mRemoteDevice;
  
  private int mRepeatingRequestId = -1;
  
  private int[] mRepeatingRequestTypes;
  
  private final List<RequestLastFrameNumbersHolder> mRequestLastFrameNumbersList = new ArrayList<>();
  
  private volatile StateCallbackKK mSessionStateCallback;
  
  private final int mTotalPartialCount;
  
  public CameraDeviceImpl(String paramString, CameraDevice.StateCallback paramStateCallback, Executor paramExecutor, CameraCharacteristics paramCameraCharacteristics, int paramInt) {
    if (paramString != null && paramStateCallback != null && paramExecutor != null && paramCameraCharacteristics != null) {
      this.mCameraId = paramString;
      this.mDeviceCallback = paramStateCallback;
      this.mDeviceExecutor = paramExecutor;
      this.mCharacteristics = paramCameraCharacteristics;
      this.mAppTargetSdkVersion = paramInt;
      String str = String.format("CameraDevice-JV-%s", new Object[] { paramString });
      paramString = str;
      if (str.length() > 23)
        paramString = str.substring(0, 23); 
      this.TAG = paramString;
      Integer integer = (Integer)this.mCharacteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
      if (integer == null) {
        this.mTotalPartialCount = 1;
      } else {
        this.mTotalPartialCount = integer.intValue();
      } 
      return;
    } 
    throw new IllegalArgumentException("Null argument given");
  }
  
  private void checkAndFireSequenceComplete() {
    long l1 = this.mFrameNumberTracker.getCompletedFrameNumber();
    long l2 = this.mFrameNumberTracker.getCompletedReprocessFrameNumber();
    long l3 = this.mFrameNumberTracker.getCompletedZslStillFrameNumber();
    Iterator<RequestLastFrameNumbersHolder> iterator = this.mRequestLastFrameNumbersList.iterator();
    while (true) {
      final RequestLastFrameNumbersHolder requestLastFrameNumbers;
      final int requestId;
      if (iterator.hasNext()) {
        requestLastFrameNumbersHolder = iterator.next();
        i = requestLastFrameNumbersHolder.getRequestId();
        if (this.mRemoteDevice == null) {
          Log.w(this.TAG, "Camera closed while checking sequences");
          return;
        } 
        if (!requestLastFrameNumbersHolder.isSequenceCompleted()) {
          final CaptureCallbackHolder holder;
          long l4 = requestLastFrameNumbersHolder.getLastRegularFrameNumber();
          long l5 = requestLastFrameNumbersHolder.getLastReprocessFrameNumber();
          long l6 = requestLastFrameNumbersHolder.getLastZslStillFrameNumber();
          if (l4 <= l1 && l5 <= l2 && l6 <= l3)
            requestLastFrameNumbersHolder.markSequenceCompleted(); 
          int j = this.mCaptureCallbackMap.indexOfKey(i);
          if (j >= 0) {
            captureCallbackHolder = (CaptureCallbackHolder)this.mCaptureCallbackMap.valueAt(j);
          } else {
            captureCallbackHolder = null;
          } 
          if (captureCallbackHolder != null && requestLastFrameNumbersHolder.isSequenceCompleted()) {
            Runnable runnable = new Runnable() {
                public void run() {
                  if (!CameraDeviceImpl.this.isClosed())
                    holder.getCallback().onCaptureSequenceCompleted(CameraDeviceImpl.this, requestId, requestLastFrameNumbers.getLastFrameNumber()); 
                }
              };
            l4 = Binder.clearCallingIdentity();
            try {
              Executor executor = captureCallbackHolder.getExecutor();
              try {
                executor.execute(runnable);
                Binder.restoreCallingIdentity(l4);
              } finally {}
            } finally {}
            Binder.restoreCallingIdentity(l4);
            throw captureCallbackHolder;
          } 
        } 
      } else {
        break;
      } 
      if (requestLastFrameNumbersHolder.isSequenceCompleted() && requestLastFrameNumbersHolder.isInflightCompleted()) {
        i = this.mCaptureCallbackMap.indexOfKey(i);
        if (i >= 0)
          this.mCaptureCallbackMap.removeAt(i); 
        iterator.remove();
      } 
    } 
  }
  
  public static Executor checkAndWrapHandler(Handler paramHandler) {
    return new CameraHandlerExecutor(checkHandler(paramHandler));
  }
  
  private void checkEarlyTriggerSequenceCompleteLocked(final int requestId, long paramLong, final int[] holder) {
    if (paramLong == -1L) {
      int i = this.mCaptureCallbackMap.indexOfKey(requestId);
      if (i >= 0) {
        CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)this.mCaptureCallbackMap.valueAt(i);
      } else {
        holder = null;
      } 
      if (holder != null)
        this.mCaptureCallbackMap.removeAt(i); 
      if (holder != null) {
        Runnable runnable = new Runnable() {
            public void run() {
              if (!CameraDeviceImpl.this.isClosed())
                holder.getCallback().onCaptureSequenceAborted(CameraDeviceImpl.this, requestId); 
            }
          };
        paramLong = Binder.clearCallingIdentity();
        try {
          holder.getExecutor().execute(runnable);
        } finally {
          Binder.restoreCallingIdentity(paramLong);
        } 
      } else {
        Log.w(this.TAG, String.format("did not register callback to request %d", new Object[] { Integer.valueOf(requestId) }));
      } 
    } else {
      this.mRequestLastFrameNumbersList.add(new RequestLastFrameNumbersHolder(requestId, paramLong, holder));
      checkAndFireSequenceComplete();
    } 
  }
  
  static Executor checkExecutor(Executor paramExecutor) {
    if (paramExecutor == null)
      paramExecutor = checkAndWrapHandler(null); 
    return paramExecutor;
  }
  
  public static <T> Executor checkExecutor(Executor paramExecutor, T paramT) {
    if (paramT != null)
      paramExecutor = checkExecutor(paramExecutor); 
    return paramExecutor;
  }
  
  static Handler checkHandler(Handler paramHandler) {
    Handler handler = paramHandler;
    if (paramHandler == null) {
      Looper looper = Looper.myLooper();
      if (looper != null) {
        handler = new Handler(looper);
      } else {
        throw new IllegalArgumentException("No handler given, and current thread has no looper!");
      } 
    } 
    return handler;
  }
  
  static <T> Handler checkHandler(Handler paramHandler, T paramT) {
    return (paramT != null) ? checkHandler(paramHandler) : paramHandler;
  }
  
  private void checkIfCameraClosedOrInError() throws CameraAccessException {
    if (this.mRemoteDevice != null) {
      if (!this.mInError)
        return; 
      throw new CameraAccessException(3, "The camera device has encountered a serious error");
    } 
    throw new IllegalStateException("CameraDevice was already closed");
  }
  
  private void checkInputConfiguration(InputConfiguration paramInputConfiguration) {
    if (paramInputConfiguration != null) {
      StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap)this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
      int[] arrayOfInt = streamConfigurationMap.getInputFormats();
      int i = 0;
      int j = arrayOfInt.length;
      boolean bool = false;
      byte b;
      for (b = 0; b < j; b++) {
        if (arrayOfInt[b] == paramInputConfiguration.getFormat())
          i = 1; 
      } 
      if (i) {
        j = 0;
        Size[] arrayOfSize = streamConfigurationMap.getInputSizes(paramInputConfiguration.getFormat());
        int k = arrayOfSize.length;
        b = bool;
        while (b < k) {
          Size size = arrayOfSize[b];
          i = j;
          if (paramInputConfiguration.getWidth() == size.getWidth()) {
            i = j;
            if (paramInputConfiguration.getHeight() == size.getHeight())
              i = 1; 
          } 
          b++;
          j = i;
        } 
        if (j == 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("input size ");
          stringBuilder.append(paramInputConfiguration.getWidth());
          stringBuilder.append("x");
          stringBuilder.append(paramInputConfiguration.getHeight());
          stringBuilder.append(" is not valid");
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("input format ");
        stringBuilder.append(paramInputConfiguration.getFormat());
        stringBuilder.append(" is not valid");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } 
  }
  
  private void createCaptureSessionInternal(InputConfiguration paramInputConfiguration, List<OutputConfiguration> paramList, CameraCaptureSession.StateCallback paramStateCallback, Executor paramExecutor, int paramInt, CaptureRequest paramCaptureRequest) throws CameraAccessException {
    // Byte code:
    //   0: aload_0
    //   1: getfield mInterfaceLock : Ljava/lang/Object;
    //   4: astore #7
    //   6: aload #7
    //   8: monitorenter
    //   9: aload_0
    //   10: invokespecial checkIfCameraClosedOrInError : ()V
    //   13: iload #5
    //   15: iconst_1
    //   16: if_icmpne -> 25
    //   19: iconst_1
    //   20: istore #8
    //   22: goto -> 28
    //   25: iconst_0
    //   26: istore #8
    //   28: iload #8
    //   30: ifeq -> 53
    //   33: aload_1
    //   34: ifnonnull -> 40
    //   37: goto -> 53
    //   40: new java/lang/IllegalArgumentException
    //   43: astore_1
    //   44: aload_1
    //   45: ldc_w 'Constrained high speed session doesn't support input configuration yet.'
    //   48: invokespecial <init> : (Ljava/lang/String;)V
    //   51: aload_1
    //   52: athrow
    //   53: aload_0
    //   54: getfield mCurrentSession : Landroid/hardware/camera2/impl/CameraCaptureSessionCore;
    //   57: ifnull -> 69
    //   60: aload_0
    //   61: getfield mCurrentSession : Landroid/hardware/camera2/impl/CameraCaptureSessionCore;
    //   64: invokeinterface replaceSessionClose : ()V
    //   69: aconst_null
    //   70: astore #9
    //   72: aload_0
    //   73: aload_1
    //   74: aload_2
    //   75: iload #5
    //   77: aload #6
    //   79: invokevirtual configureStreamsChecked : (Landroid/hardware/camera2/params/InputConfiguration;Ljava/util/List;ILandroid/hardware/camera2/CaptureRequest;)Z
    //   82: istore #10
    //   84: aload #9
    //   86: astore #6
    //   88: iload #10
    //   90: iconst_1
    //   91: if_icmpne -> 111
    //   94: aload #9
    //   96: astore #6
    //   98: aload_1
    //   99: ifnull -> 111
    //   102: aload_0
    //   103: getfield mRemoteDevice : Landroid/hardware/camera2/impl/ICameraDeviceUserWrapper;
    //   106: invokevirtual getInputSurface : ()Landroid/view/Surface;
    //   109: astore #6
    //   111: aconst_null
    //   112: astore_1
    //   113: goto -> 123
    //   116: astore_1
    //   117: iconst_0
    //   118: istore #10
    //   120: aconst_null
    //   121: astore #6
    //   123: iload #8
    //   125: ifeq -> 241
    //   128: new java/util/ArrayList
    //   131: astore #6
    //   133: aload #6
    //   135: aload_2
    //   136: invokeinterface size : ()I
    //   141: invokespecial <init> : (I)V
    //   144: aload_2
    //   145: invokeinterface iterator : ()Ljava/util/Iterator;
    //   150: astore_2
    //   151: aload_2
    //   152: invokeinterface hasNext : ()Z
    //   157: ifeq -> 181
    //   160: aload #6
    //   162: aload_2
    //   163: invokeinterface next : ()Ljava/lang/Object;
    //   168: checkcast android/hardware/camera2/params/OutputConfiguration
    //   171: invokevirtual getSurface : ()Landroid/view/Surface;
    //   174: invokevirtual add : (Ljava/lang/Object;)Z
    //   177: pop
    //   178: goto -> 151
    //   181: aload #6
    //   183: aconst_null
    //   184: aload_0
    //   185: invokespecial getCharacteristics : ()Landroid/hardware/camera2/CameraCharacteristics;
    //   188: getstatic android/hardware/camera2/CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   191: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   194: checkcast android/hardware/camera2/params/StreamConfigurationMap
    //   197: invokestatic checkConstrainedHighSpeedSurfaces : (Ljava/util/Collection;Landroid/util/Range;Landroid/hardware/camera2/params/StreamConfigurationMap;)V
    //   200: new android/hardware/camera2/impl/CameraConstrainedHighSpeedCaptureSessionImpl
    //   203: astore_2
    //   204: aload_0
    //   205: getfield mNextSessionId : I
    //   208: istore #5
    //   210: aload_0
    //   211: iload #5
    //   213: iconst_1
    //   214: iadd
    //   215: putfield mNextSessionId : I
    //   218: aload_2
    //   219: iload #5
    //   221: aload_3
    //   222: aload #4
    //   224: aload_0
    //   225: aload_0
    //   226: getfield mDeviceExecutor : Ljava/util/concurrent/Executor;
    //   229: iload #10
    //   231: aload_0
    //   232: getfield mCharacteristics : Landroid/hardware/camera2/CameraCharacteristics;
    //   235: invokespecial <init> : (ILandroid/hardware/camera2/CameraCaptureSession$StateCallback;Ljava/util/concurrent/Executor;Landroid/hardware/camera2/impl/CameraDeviceImpl;Ljava/util/concurrent/Executor;ZLandroid/hardware/camera2/CameraCharacteristics;)V
    //   238: goto -> 277
    //   241: aload_0
    //   242: getfield mNextSessionId : I
    //   245: istore #5
    //   247: aload_0
    //   248: iload #5
    //   250: iconst_1
    //   251: iadd
    //   252: putfield mNextSessionId : I
    //   255: new android/hardware/camera2/impl/CameraCaptureSessionImpl
    //   258: dup
    //   259: iload #5
    //   261: aload #6
    //   263: aload_3
    //   264: aload #4
    //   266: aload_0
    //   267: aload_0
    //   268: getfield mDeviceExecutor : Ljava/util/concurrent/Executor;
    //   271: iload #10
    //   273: invokespecial <init> : (ILandroid/view/Surface;Landroid/hardware/camera2/CameraCaptureSession$StateCallback;Ljava/util/concurrent/Executor;Landroid/hardware/camera2/impl/CameraDeviceImpl;Ljava/util/concurrent/Executor;Z)V
    //   276: astore_2
    //   277: aload_0
    //   278: aload_2
    //   279: putfield mCurrentSession : Landroid/hardware/camera2/impl/CameraCaptureSessionCore;
    //   282: aload_1
    //   283: ifnonnull -> 300
    //   286: aload_0
    //   287: aload_2
    //   288: invokeinterface getDeviceStateCallback : ()Landroid/hardware/camera2/impl/CameraDeviceImpl$StateCallbackKK;
    //   293: putfield mSessionStateCallback : Landroid/hardware/camera2/impl/CameraDeviceImpl$StateCallbackKK;
    //   296: aload #7
    //   298: monitorexit
    //   299: return
    //   300: aload_1
    //   301: athrow
    //   302: astore_1
    //   303: aload #7
    //   305: monitorexit
    //   306: aload_1
    //   307: athrow
    //   308: astore_1
    //   309: goto -> 303
    // Exception table:
    //   from	to	target	type
    //   9	13	302	finally
    //   40	53	302	finally
    //   53	69	302	finally
    //   72	84	116	android/hardware/camera2/CameraAccessException
    //   72	84	308	finally
    //   102	111	116	android/hardware/camera2/CameraAccessException
    //   102	111	308	finally
    //   128	151	308	finally
    //   151	178	308	finally
    //   181	238	308	finally
    //   241	277	308	finally
    //   277	282	308	finally
    //   286	299	308	finally
    //   300	302	308	finally
    //   303	306	308	finally
  }
  
  private CameraCharacteristics getCharacteristics() {
    return this.mCharacteristics;
  }
  
  private int[] getRequestTypes(CaptureRequest[] paramArrayOfCaptureRequest) {
    int[] arrayOfInt = new int[paramArrayOfCaptureRequest.length];
    for (byte b = 0; b < paramArrayOfCaptureRequest.length; b++)
      arrayOfInt[b] = paramArrayOfCaptureRequest[b].getRequestType(); 
    return arrayOfInt;
  }
  
  private boolean isClosed() {
    return this.mClosing.get();
  }
  
  private void notifyError(int paramInt) {
    if (!isClosed())
      this.mDeviceCallback.onError(this, paramInt); 
  }
  
  private void onCaptureErrorLocked(int paramInt, CaptureResultExtras paramCaptureResultExtras) {
    Runnable runnable;
    int i = paramCaptureResultExtras.getRequestId();
    int j = paramCaptureResultExtras.getSubsequenceId();
    final long frameNumber = paramCaptureResultExtras.getFrameNumber();
    String str = paramCaptureResultExtras.getErrorPhysicalCameraId();
    final CaptureCallbackHolder holder = (CaptureCallbackHolder)this.mCaptureCallbackMap.get(i);
    boolean bool = false;
    if (captureCallbackHolder == null) {
      Log.e(this.TAG, String.format("Receive capture error on unknown request ID %d", new Object[] { Integer.valueOf(i) }));
      return;
    } 
    final CaptureRequest request = captureCallbackHolder.getRequest(j);
    if (paramInt == 5) {
      OutputConfiguration outputConfiguration = (OutputConfiguration)this.mConfiguredOutputs.get(paramCaptureResultExtras.getErrorStreamId());
      if (outputConfiguration == null) {
        Log.v(this.TAG, String.format("Stream %d has been removed. Skipping buffer lost callback", new Object[] { Integer.valueOf(paramCaptureResultExtras.getErrorStreamId()) }));
        return;
      } 
      for (Surface surface : outputConfiguration.getSurfaces()) {
        if (!captureRequest.containsTarget(surface))
          continue; 
        runnable = new Runnable() {
            public void run() {
              if (!CameraDeviceImpl.this.isClosed())
                holder.getCallback().onCaptureBufferLost(CameraDeviceImpl.this, request, surface, frameNumber); 
            }
          };
        long l1 = Binder.clearCallingIdentity();
        try {
          captureCallbackHolder.getExecutor().execute(runnable);
        } finally {
          Binder.restoreCallingIdentity(l1);
        } 
      } 
    } else {
      boolean bool1;
      if (paramInt == 4) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      CameraCaptureSessionCore cameraCaptureSessionCore = this.mCurrentSession;
      if (cameraCaptureSessionCore != null && cameraCaptureSessionCore.isAborting()) {
        paramInt = 1;
      } else {
        paramInt = bool;
      } 
      null = new Runnable() {
          public void run() {
            if (!CameraDeviceImpl.this.isClosed())
              holder.getCallback().onCaptureFailed(CameraDeviceImpl.this, request, failure); 
          }
        };
      this.mFrameNumberTracker.updateTracker(l, true, captureRequest.getRequestType());
      checkAndFireSequenceComplete();
      l = Binder.clearCallingIdentity();
      try {
        captureCallbackHolder.getExecutor().execute(null);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  private void overrideEnableZsl(CameraMetadataNative paramCameraMetadataNative, boolean paramBoolean) {
    if ((Boolean)paramCameraMetadataNative.<Boolean>get(CaptureRequest.CONTROL_ENABLE_ZSL) == null)
      return; 
    paramCameraMetadataNative.set(CaptureRequest.CONTROL_ENABLE_ZSL, Boolean.valueOf(paramBoolean));
  }
  
  private void removeCompletedCallbackHolderLocked(long paramLong1, long paramLong2, long paramLong3) {
    Iterator<RequestLastFrameNumbersHolder> iterator = this.mRequestLastFrameNumbersList.iterator();
    while (iterator.hasNext()) {
      RequestLastFrameNumbersHolder requestLastFrameNumbersHolder = iterator.next();
      int i = requestLastFrameNumbersHolder.getRequestId();
      if (this.mRemoteDevice == null) {
        Log.w(this.TAG, "Camera closed while removing completed callback holders");
        return;
      } 
      long l1 = requestLastFrameNumbersHolder.getLastRegularFrameNumber();
      long l2 = requestLastFrameNumbersHolder.getLastReprocessFrameNumber();
      long l3 = requestLastFrameNumbersHolder.getLastZslStillFrameNumber();
      if (l1 <= paramLong1 && l2 <= paramLong2 && l3 <= paramLong3) {
        if (requestLastFrameNumbersHolder.isSequenceCompleted()) {
          i = this.mCaptureCallbackMap.indexOfKey(i);
          if (i >= 0)
            this.mCaptureCallbackMap.removeAt(i); 
          iterator.remove();
          continue;
        } 
        requestLastFrameNumbersHolder.markInflightCompleted();
      } 
    } 
  }
  
  private void scheduleNotifyError(int paramInt) {
    this.mInError = true;
    long l = Binder.clearCallingIdentity();
    try {
      this.mDeviceExecutor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$CameraDeviceImpl$oDs27OTfKFfK18rUW2nQxxkPdV0.INSTANCE, this, Integer.valueOf(paramInt)).recycleOnUse());
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  private int submitCaptureRequest(List<CaptureRequest> paramList, CaptureCallback paramCaptureCallback, Executor paramExecutor, boolean paramBoolean) throws CameraAccessException {
    Executor executor = checkExecutor(paramExecutor, paramCaptureCallback);
    synchronized (this.mInterfaceLock) {
      IllegalArgumentException illegalArgumentException;
      checkIfCameraClosedOrInError();
      for (CaptureRequest captureRequest : paramList) {
        if (!captureRequest.getTargets().isEmpty()) {
          Iterator<Surface> iterator = captureRequest.getTargets().iterator();
          while (iterator.hasNext()) {
            if ((Surface)iterator.next() != null)
              continue; 
            IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
            this("Null Surface targets are not allowed");
            throw illegalArgumentException1;
          } 
          continue;
        } 
        illegalArgumentException = new IllegalArgumentException();
        this("Each request must have at least one Surface target");
        throw illegalArgumentException;
      } 
      if (paramBoolean)
        stopRepeating(); 
      CaptureRequest[] arrayOfCaptureRequest = (CaptureRequest[])illegalArgumentException.toArray((Object[])new CaptureRequest[illegalArgumentException.size()]);
      int i = arrayOfCaptureRequest.length;
      int j;
      for (j = 0; j < i; j++)
        arrayOfCaptureRequest[j].convertSurfaceToStreamId(this.mConfiguredOutputs); 
      SubmitInfo submitInfo = this.mRemoteDevice.submitRequestList(arrayOfCaptureRequest, paramBoolean);
      i = arrayOfCaptureRequest.length;
      for (j = 0; j < i; j++)
        arrayOfCaptureRequest[j].recoverStreamIdToSurface(); 
      if (paramCaptureCallback != null) {
        SparseArray<CaptureCallbackHolder> sparseArray = this.mCaptureCallbackMap;
        j = submitInfo.getRequestId();
        CaptureCallbackHolder captureCallbackHolder = new CaptureCallbackHolder();
        this(paramCaptureCallback, (List<CaptureRequest>)illegalArgumentException, executor, paramBoolean, this.mNextSessionId - 1);
        sparseArray.put(j, captureCallbackHolder);
      } 
      if (paramBoolean) {
        if (this.mRepeatingRequestId != -1)
          checkEarlyTriggerSequenceCompleteLocked(this.mRepeatingRequestId, submitInfo.getLastFrameNumber(), this.mRepeatingRequestTypes); 
        this.mRepeatingRequestId = submitInfo.getRequestId();
        this.mRepeatingRequestTypes = getRequestTypes(arrayOfCaptureRequest);
      } else {
        List<RequestLastFrameNumbersHolder> list = this.mRequestLastFrameNumbersList;
        RequestLastFrameNumbersHolder requestLastFrameNumbersHolder = new RequestLastFrameNumbersHolder();
        this((List<CaptureRequest>)illegalArgumentException, submitInfo);
        list.add(requestLastFrameNumbersHolder);
      } 
      if (this.mIdle)
        this.mDeviceExecutor.execute(this.mCallOnActive); 
      this.mIdle = false;
      j = submitInfo.getRequestId();
      return j;
    } 
  }
  
  private void waitUntilIdle() throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      if (this.mRepeatingRequestId == -1) {
        this.mRemoteDevice.waitUntilIdle();
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Active repeating request ongoing");
      throw illegalStateException;
    } 
  }
  
  public void binderDied() {
    String str = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CameraDevice ");
    stringBuilder.append(this.mCameraId);
    stringBuilder.append(" died unexpectedly");
    Log.w(str, stringBuilder.toString());
    if (this.mRemoteDevice == null)
      return; 
    this.mInError = true;
    null = new Runnable() {
        public void run() {
          if (!CameraDeviceImpl.this.isClosed())
            CameraDeviceImpl.this.mDeviceCallback.onError(CameraDeviceImpl.this, 5); 
        }
      };
    long l = Binder.clearCallingIdentity();
    try {
      this.mDeviceExecutor.execute(null);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public int capture(CaptureRequest paramCaptureRequest, CaptureCallback paramCaptureCallback, Executor paramExecutor) throws CameraAccessException {
    ArrayList<CaptureRequest> arrayList = new ArrayList();
    arrayList.add(paramCaptureRequest);
    return submitCaptureRequest(arrayList, paramCaptureCallback, paramExecutor, false);
  }
  
  public int captureBurst(List<CaptureRequest> paramList, CaptureCallback paramCaptureCallback, Executor paramExecutor) throws CameraAccessException {
    if (paramList != null && !paramList.isEmpty())
      return submitCaptureRequest(paramList, paramCaptureCallback, paramExecutor, false); 
    throw new IllegalArgumentException("At least one request must be given");
  }
  
  public void close() {
    synchronized (this.mInterfaceLock) {
      if (this.mClosing.getAndSet(true))
        return; 
      if (this.mOfflineSwitchService != null) {
        this.mOfflineSwitchService.shutdownNow();
        this.mOfflineSwitchService = null;
      } 
      if (this.mRemoteDevice != null) {
        this.mRemoteDevice.disconnect();
        this.mRemoteDevice.unlinkToDeath(this, 0);
      } 
      if (this.mRemoteDevice != null || this.mInError)
        this.mDeviceExecutor.execute(this.mCallOnClosed); 
      this.mRemoteDevice = null;
      return;
    } 
  }
  
  public void configureOutputs(List<Surface> paramList) throws CameraAccessException {
    ArrayList<OutputConfiguration> arrayList = new ArrayList(paramList.size());
    Iterator<Surface> iterator = paramList.iterator();
    while (iterator.hasNext())
      arrayList.add(new OutputConfiguration(iterator.next())); 
    configureStreamsChecked(null, arrayList, 0, null);
  }
  
  public boolean configureStreamsChecked(InputConfiguration paramInputConfiguration, List<OutputConfiguration> paramList, int paramInt, CaptureRequest paramCaptureRequest) throws CameraAccessException {
    if (paramList == null)
      paramList = new ArrayList<>(); 
    if (paramList.size() != 0 || paramInputConfiguration == null) {
      checkInputConfiguration(paramInputConfiguration);
      synchronized (this.mInterfaceLock) {
        checkIfCameraClosedOrInError();
        HashSet hashSet = new HashSet();
        this((Collection)paramList);
        ArrayList<Integer> arrayList = new ArrayList();
        this();
        int i;
        for (i = 0; i < this.mConfiguredOutputs.size(); i++) {
          int j = this.mConfiguredOutputs.keyAt(i);
          OutputConfiguration outputConfiguration = (OutputConfiguration)this.mConfiguredOutputs.valueAt(i);
          if (!paramList.contains(outputConfiguration) || outputConfiguration.isDeferredConfiguration()) {
            arrayList.add(Integer.valueOf(j));
          } else {
            hashSet.remove(outputConfiguration);
          } 
        } 
        this.mDeviceExecutor.execute(this.mCallOnBusy);
        stopRepeating();
        try {
          int[] arrayOfInt;
          waitUntilIdle();
          this.mRemoteDevice.beginConfigure();
          InputConfiguration inputConfiguration = this.mConfiguredInput.getValue();
          if (paramInputConfiguration != inputConfiguration && (paramInputConfiguration == null || !paramInputConfiguration.equals(inputConfiguration))) {
            if (inputConfiguration != null) {
              this.mRemoteDevice.deleteStream(((Integer)this.mConfiguredInput.getKey()).intValue());
              AbstractMap.SimpleEntry<Object, Object> simpleEntry = new AbstractMap.SimpleEntry<>();
              this((K)Integer.valueOf(-1), null);
              this.mConfiguredInput = (AbstractMap.SimpleEntry)simpleEntry;
            } 
            if (paramInputConfiguration != null) {
              i = this.mRemoteDevice.createInputStream(paramInputConfiguration.getWidth(), paramInputConfiguration.getHeight(), paramInputConfiguration.getFormat());
              AbstractMap.SimpleEntry<Object, Object> simpleEntry = new AbstractMap.SimpleEntry<>();
              this((K)Integer.valueOf(i), (V)paramInputConfiguration);
              this.mConfiguredInput = (AbstractMap.SimpleEntry)simpleEntry;
            } 
          } 
          for (Integer integer : arrayList) {
            this.mRemoteDevice.deleteStream(integer.intValue());
            this.mConfiguredOutputs.delete(integer.intValue());
          } 
          for (OutputConfiguration outputConfiguration : paramList) {
            if (hashSet.contains(outputConfiguration)) {
              i = this.mRemoteDevice.createStream(outputConfiguration);
              this.mConfiguredOutputs.put(i, outputConfiguration);
            } 
          } 
          if (paramCaptureRequest != null) {
            arrayOfInt = this.mRemoteDevice.endConfigure(paramInt, paramCaptureRequest.getNativeCopy());
          } else {
            arrayOfInt = this.mRemoteDevice.endConfigure(paramInt, null);
          } 
          this.mOfflineSupport.clear();
          if (arrayOfInt != null && arrayOfInt.length > 0) {
            i = arrayOfInt.length;
            for (paramInt = 0; paramInt < i; paramInt++) {
              int j = arrayOfInt[paramInt];
              this.mOfflineSupport.add(Integer.valueOf(j));
            } 
          } 
          if (true && paramList.size() > 0) {
            this.mDeviceExecutor.execute(this.mCallOnIdle);
          } else {
            this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
          } 
          return true;
        } catch (IllegalArgumentException illegalArgumentException) {
          String str = this.TAG;
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Stream configuration failed due to: ");
          stringBuilder.append(illegalArgumentException.getMessage());
          Log.w(str, stringBuilder.toString());
          if (false && paramList.size() > 0) {
            this.mDeviceExecutor.execute(this.mCallOnIdle);
          } else {
            this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
          } 
          return false;
        } catch (CameraAccessException cameraAccessException) {
          if (cameraAccessException.getReason() == 4) {
            IllegalStateException illegalStateException = new IllegalStateException();
            this("The camera is currently busy. You must wait until the previous operation completes.", (Throwable)cameraAccessException);
            throw illegalStateException;
          } 
          throw cameraAccessException;
        } finally {}
        if (false && paramList.size() > 0) {
          this.mDeviceExecutor.execute(this.mCallOnIdle);
        } else {
          this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
        } 
        throw paramInputConfiguration;
      } 
    } 
    throw new IllegalArgumentException("cannot configure an input stream without any output streams");
  }
  
  public CaptureRequest.Builder createCaptureRequest(int paramInt) throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      CameraMetadataNative cameraMetadataNative = this.mRemoteDevice.createDefaultRequest(paramInt);
      if (this.mAppTargetSdkVersion < 26 || paramInt != 2)
        overrideEnableZsl(cameraMetadataNative, false); 
      CaptureRequest.Builder builder = new CaptureRequest.Builder();
      this(cameraMetadataNative, false, -1, getId(), null);
      return builder;
    } 
  }
  
  public CaptureRequest.Builder createCaptureRequest(int paramInt, Set<String> paramSet) throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      IllegalStateException illegalStateException;
      checkIfCameraClosedOrInError();
      Iterator<String> iterator = paramSet.iterator();
      while (iterator.hasNext()) {
        if ((String)iterator.next() != getId())
          continue; 
        illegalStateException = new IllegalStateException();
        this("Physical id matches the logical id!");
        throw illegalStateException;
      } 
      CameraMetadataNative cameraMetadataNative = this.mRemoteDevice.createDefaultRequest(paramInt);
      if (this.mAppTargetSdkVersion < 26 || paramInt != 2)
        overrideEnableZsl(cameraMetadataNative, false); 
      CaptureRequest.Builder builder = new CaptureRequest.Builder();
      this(cameraMetadataNative, false, -1, getId(), (Set)illegalStateException);
      return builder;
    } 
  }
  
  public void createCaptureSession(SessionConfiguration paramSessionConfiguration) throws CameraAccessException {
    if (paramSessionConfiguration != null) {
      List<OutputConfiguration> list = paramSessionConfiguration.getOutputConfigurations();
      if (list != null) {
        if (paramSessionConfiguration.getExecutor() != null) {
          createCaptureSessionInternal(paramSessionConfiguration.getInputConfiguration(), list, paramSessionConfiguration.getStateCallback(), paramSessionConfiguration.getExecutor(), paramSessionConfiguration.getSessionType(), paramSessionConfiguration.getSessionParameters());
          return;
        } 
        throw new IllegalArgumentException("Invalid executor");
      } 
      throw new IllegalArgumentException("Invalid output configurations");
    } 
    throw new IllegalArgumentException("Invalid session configuration");
  }
  
  public void createCaptureSession(List<Surface> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    ArrayList<OutputConfiguration> arrayList = new ArrayList(paramList.size());
    Iterator<Surface> iterator = paramList.iterator();
    while (iterator.hasNext())
      arrayList.add(new OutputConfiguration(iterator.next())); 
    createCaptureSessionInternal(null, arrayList, paramStateCallback, checkAndWrapHandler(paramHandler), 0, null);
  }
  
  public void createCaptureSessionByOutputConfigurations(List<OutputConfiguration> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    createCaptureSessionInternal(null, new ArrayList<>(paramList), paramStateCallback, checkAndWrapHandler(paramHandler), 0, null);
  }
  
  public void createConstrainedHighSpeedCaptureSession(List<Surface> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    if (paramList != null && paramList.size() != 0 && paramList.size() <= 2) {
      ArrayList<OutputConfiguration> arrayList = new ArrayList(paramList.size());
      Iterator<Surface> iterator = paramList.iterator();
      while (iterator.hasNext())
        arrayList.add(new OutputConfiguration(iterator.next())); 
      createCaptureSessionInternal(null, arrayList, paramStateCallback, checkAndWrapHandler(paramHandler), 1, null);
      return;
    } 
    throw new IllegalArgumentException("Output surface list must not be null and the size must be no more than 2");
  }
  
  public void createCustomCaptureSession(InputConfiguration paramInputConfiguration, List<OutputConfiguration> paramList, int paramInt, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    ArrayList<OutputConfiguration> arrayList = new ArrayList();
    Iterator<OutputConfiguration> iterator = paramList.iterator();
    while (iterator.hasNext())
      arrayList.add(new OutputConfiguration(iterator.next())); 
    createCaptureSessionInternal(paramInputConfiguration, arrayList, paramStateCallback, checkAndWrapHandler(paramHandler), paramInt, null);
  }
  
  public CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult paramTotalCaptureResult) throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
      this(paramTotalCaptureResult.getNativeCopy());
      CaptureRequest.Builder builder = new CaptureRequest.Builder();
      this(cameraMetadataNative, true, paramTotalCaptureResult.getSessionId(), getId(), null);
      return builder;
    } 
  }
  
  public void createReprocessableCaptureSession(InputConfiguration paramInputConfiguration, List<Surface> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    if (paramInputConfiguration != null) {
      ArrayList<OutputConfiguration> arrayList = new ArrayList(paramList.size());
      Iterator<Surface> iterator = paramList.iterator();
      while (iterator.hasNext())
        arrayList.add(new OutputConfiguration(iterator.next())); 
      createCaptureSessionInternal(paramInputConfiguration, arrayList, paramStateCallback, checkAndWrapHandler(paramHandler), 0, null);
      return;
    } 
    throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
  }
  
  public void createReprocessableCaptureSessionByConfigurations(InputConfiguration paramInputConfiguration, List<OutputConfiguration> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException {
    if (paramInputConfiguration != null) {
      if (paramList != null) {
        ArrayList<OutputConfiguration> arrayList = new ArrayList();
        Iterator<OutputConfiguration> iterator = paramList.iterator();
        while (iterator.hasNext())
          arrayList.add(new OutputConfiguration(iterator.next())); 
        createCaptureSessionInternal(paramInputConfiguration, arrayList, paramStateCallback, checkAndWrapHandler(paramHandler), 0, null);
        return;
      } 
      throw new IllegalArgumentException("Output configurations cannot be null when creating a reprocessable capture session");
    } 
    throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public void finalizeOutputConfigs(List<OutputConfiguration> paramList) throws CameraAccessException {
    if (paramList != null && paramList.size() != 0)
      synchronized (this.mInterfaceLock) {
        checkIfCameraClosedOrInError();
        Iterator<OutputConfiguration> iterator = paramList.iterator();
        while (iterator.hasNext()) {
          int i;
          OutputConfiguration outputConfiguration = iterator.next();
          byte b = -1;
          byte b1 = 0;
          while (true) {
            i = b;
            if (b1 < this.mConfiguredOutputs.size()) {
              if (outputConfiguration.equals(this.mConfiguredOutputs.valueAt(b1))) {
                i = this.mConfiguredOutputs.keyAt(b1);
                break;
              } 
              b1++;
              continue;
            } 
            break;
          } 
          if (i != -1) {
            if (outputConfiguration.getSurfaces().size() != 0) {
              this.mRemoteDevice.finalizeOutputConfigurations(i, outputConfiguration);
              this.mConfiguredOutputs.put(i, outputConfiguration);
              continue;
            } 
            IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("The final config for stream ");
            stringBuilder.append(i);
            stringBuilder.append(" must have at least 1 surface");
            this(stringBuilder.toString());
            throw illegalArgumentException1;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          this("Deferred config is not part of this session");
          throw illegalArgumentException;
        } 
        return;
      }  
    throw new IllegalArgumentException("deferred config is null or empty");
  }
  
  public void flush() throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      this.mDeviceExecutor.execute(this.mCallOnBusy);
      if (this.mIdle) {
        this.mDeviceExecutor.execute(this.mCallOnIdle);
        return;
      } 
      long l = this.mRemoteDevice.flush();
      if (this.mRepeatingRequestId != -1) {
        checkEarlyTriggerSequenceCompleteLocked(this.mRepeatingRequestId, l, this.mRepeatingRequestTypes);
        this.mRepeatingRequestId = -1;
        this.mRepeatingRequestTypes = null;
      } 
      return;
    } 
  }
  
  public CameraDeviceCallbacks getCallbacks() {
    return this.mCallbacks;
  }
  
  public int getCameraAudioRestriction() throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      return this.mRemoteDevice.getGlobalAudioRestriction();
    } 
  }
  
  public String getId() {
    return this.mCameraId;
  }
  
  public boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) throws CameraAccessException, UnsupportedOperationException, IllegalArgumentException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      return this.mRemoteDevice.isSessionConfigurationSupported(paramSessionConfiguration);
    } 
  }
  
  public void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) {
    synchronized (this.mInterfaceLock) {
      if (this.mRemoteDevice == null)
        return; 
      if (this.mOfflineSessionImpl != null) {
        this.mOfflineSessionImpl.getCallbacks().onDeviceError(paramInt, paramCaptureResultExtras);
        return;
      } 
      if (paramInt != 0) {
        if (paramInt != 1) {
          String str;
          if (paramInt != 3 && paramInt != 4 && paramInt != 5) {
            if (paramInt != 6) {
              str = this.TAG;
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Unknown error from camera device: ");
              stringBuilder.append(paramInt);
              Log.e(str, stringBuilder.toString());
              scheduleNotifyError(5);
            } else {
              scheduleNotifyError(3);
            } 
          } else {
            onCaptureErrorLocked(paramInt, (CaptureResultExtras)str);
          } 
        } else {
          scheduleNotifyError(4);
        } 
      } else {
        long l = Binder.clearCallingIdentity();
        try {
          this.mDeviceExecutor.execute(this.mCallOnDisconnected);
          return;
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } 
      return;
    } 
  }
  
  public void onDeviceIdle() {
    synchronized (this.mInterfaceLock) {
      if (this.mRemoteDevice == null)
        return; 
      if (this.mOfflineSessionImpl != null) {
        this.mOfflineSessionImpl.getCallbacks().onDeviceIdle();
        return;
      } 
      removeCompletedCallbackHolderLocked(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
      if (!this.mIdle) {
        long l = Binder.clearCallingIdentity();
        try {
          this.mDeviceExecutor.execute(this.mCallOnIdle);
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } 
      this.mIdle = true;
      return;
    } 
  }
  
  public void prepare(int paramInt, Surface paramSurface) throws CameraAccessException {
    if (paramSurface != null) {
      if (paramInt > 0)
        synchronized (this.mInterfaceLock) {
          int i;
          checkIfCameraClosedOrInError();
          byte b = -1;
          byte b1 = 0;
          while (true) {
            i = b;
            if (b1 < this.mConfiguredOutputs.size()) {
              if (paramSurface == ((OutputConfiguration)this.mConfiguredOutputs.valueAt(b1)).getSurface()) {
                i = this.mConfiguredOutputs.keyAt(b1);
                break;
              } 
              b1++;
              continue;
            } 
            break;
          } 
          if (i != -1) {
            this.mRemoteDevice.prepare2(paramInt, i);
            return;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          this("Surface is not part of this session");
          throw illegalArgumentException;
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid maxCount given: ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("Surface is null");
  }
  
  public void prepare(Surface paramSurface) throws CameraAccessException {
    if (paramSurface != null)
      synchronized (this.mInterfaceLock) {
        int i;
        checkIfCameraClosedOrInError();
        byte b = -1;
        byte b1 = 0;
        while (true) {
          i = b;
          if (b1 < this.mConfiguredOutputs.size()) {
            if (((OutputConfiguration)this.mConfiguredOutputs.valueAt(b1)).getSurfaces().contains(paramSurface)) {
              i = this.mConfiguredOutputs.keyAt(b1);
              break;
            } 
            b1++;
            continue;
          } 
          break;
        } 
        if (i != -1) {
          this.mRemoteDevice.prepare(i);
          return;
        } 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("Surface is not part of this session");
        throw illegalArgumentException;
      }  
    throw new IllegalArgumentException("Surface is null");
  }
  
  public void setCameraAudioRestriction(int paramInt) throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      this.mRemoteDevice.setCameraAudioRestriction(paramInt);
      return;
    } 
  }
  
  public void setRemoteDevice(ICameraDeviceUser paramICameraDeviceUser) throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      if (this.mInError)
        return; 
      ICameraDeviceUserWrapper iCameraDeviceUserWrapper = new ICameraDeviceUserWrapper();
      this(paramICameraDeviceUser);
      this.mRemoteDevice = iCameraDeviceUserWrapper;
      IBinder iBinder = paramICameraDeviceUser.asBinder();
      if (iBinder != null)
        try {
          iBinder.linkToDeath(this, 0);
        } catch (RemoteException remoteException) {
          this.mDeviceExecutor.execute(this.mCallOnDisconnected);
          CameraAccessException cameraAccessException = new CameraAccessException();
          this(2, "The camera device has encountered a serious error");
          throw cameraAccessException;
        }  
      this.mDeviceExecutor.execute(this.mCallOnOpened);
      this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
      return;
    } 
  }
  
  public void setRemoteFailure(ServiceSpecificException paramServiceSpecificException) {
    byte b = 4;
    boolean bool = true;
    int i = paramServiceSpecificException.errorCode;
    if (i != 4) {
      if (i != 10) {
        if (i != 6) {
          if (i != 7) {
            if (i != 8) {
              String str = this.TAG;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Unexpected failure in opening camera device: ");
              stringBuilder.append(paramServiceSpecificException.errorCode);
              stringBuilder.append(paramServiceSpecificException.getMessage());
              Log.e(str, stringBuilder.toString());
            } else {
              b = 2;
            } 
          } else {
            b = 1;
          } 
        } else {
          b = 3;
        } 
      } else {
        b = 4;
      } 
    } else {
      bool = false;
    } 
    synchronized (this.mInterfaceLock) {
      this.mInError = true;
      Executor executor = this.mDeviceExecutor;
      Runnable runnable = new Runnable() {
          public void run() {
            if (isError) {
              CameraDeviceImpl.this.mDeviceCallback.onError(CameraDeviceImpl.this, code);
            } else {
              CameraDeviceImpl.this.mDeviceCallback.onDisconnected(CameraDeviceImpl.this);
            } 
          }
        };
      super(this, bool, b);
      executor.execute(runnable);
      return;
    } 
  }
  
  public int setRepeatingBurst(List<CaptureRequest> paramList, CaptureCallback paramCaptureCallback, Executor paramExecutor) throws CameraAccessException {
    if (paramList != null && !paramList.isEmpty())
      return submitCaptureRequest(paramList, paramCaptureCallback, paramExecutor, true); 
    throw new IllegalArgumentException("At least one request must be given");
  }
  
  public int setRepeatingRequest(CaptureRequest paramCaptureRequest, CaptureCallback paramCaptureCallback, Executor paramExecutor) throws CameraAccessException {
    ArrayList<CaptureRequest> arrayList = new ArrayList();
    arrayList.add(paramCaptureRequest);
    return submitCaptureRequest(arrayList, paramCaptureCallback, paramExecutor, true);
  }
  
  public void setSessionListener(StateCallbackKK paramStateCallbackKK) {
    synchronized (this.mInterfaceLock) {
      this.mSessionStateCallback = paramStateCallbackKK;
      return;
    } 
  }
  
  public void stopRepeating() throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      checkIfCameraClosedOrInError();
      if (this.mRepeatingRequestId != -1) {
        int i = this.mRepeatingRequestId;
        this.mRepeatingRequestId = -1;
        int[] arrayOfInt = this.mRepeatingRequestTypes;
        this.mRepeatingRequestTypes = null;
        try {
          long l = this.mRemoteDevice.cancelRequest(i);
          checkEarlyTriggerSequenceCompleteLocked(i, l, arrayOfInt);
        } catch (IllegalArgumentException illegalArgumentException) {
          return;
        } 
      } 
      return;
    } 
  }
  
  public boolean supportsOfflineProcessing(Surface paramSurface) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 113
    //   4: aload_0
    //   5: getfield mInterfaceLock : Ljava/lang/Object;
    //   8: astore_2
    //   9: aload_2
    //   10: monitorenter
    //   11: iconst_m1
    //   12: istore_3
    //   13: iconst_0
    //   14: istore #4
    //   16: iload_3
    //   17: istore #5
    //   19: iload #4
    //   21: aload_0
    //   22: getfield mConfiguredOutputs : Landroid/util/SparseArray;
    //   25: invokevirtual size : ()I
    //   28: if_icmpge -> 70
    //   31: aload_1
    //   32: aload_0
    //   33: getfield mConfiguredOutputs : Landroid/util/SparseArray;
    //   36: iload #4
    //   38: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   41: checkcast android/hardware/camera2/params/OutputConfiguration
    //   44: invokevirtual getSurface : ()Landroid/view/Surface;
    //   47: if_acmpne -> 64
    //   50: aload_0
    //   51: getfield mConfiguredOutputs : Landroid/util/SparseArray;
    //   54: iload #4
    //   56: invokevirtual keyAt : (I)I
    //   59: istore #5
    //   61: goto -> 70
    //   64: iinc #4, 1
    //   67: goto -> 16
    //   70: iload #5
    //   72: iconst_m1
    //   73: if_icmpeq -> 95
    //   76: aload_0
    //   77: getfield mOfflineSupport : Ljava/util/HashSet;
    //   80: iload #5
    //   82: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   85: invokevirtual contains : (Ljava/lang/Object;)Z
    //   88: istore #6
    //   90: aload_2
    //   91: monitorexit
    //   92: iload #6
    //   94: ireturn
    //   95: new java/lang/IllegalArgumentException
    //   98: astore_1
    //   99: aload_1
    //   100: ldc_w 'Surface is not part of this session'
    //   103: invokespecial <init> : (Ljava/lang/String;)V
    //   106: aload_1
    //   107: athrow
    //   108: astore_1
    //   109: aload_2
    //   110: monitorexit
    //   111: aload_1
    //   112: athrow
    //   113: new java/lang/IllegalArgumentException
    //   116: dup
    //   117: ldc_w 'Surface is null'
    //   120: invokespecial <init> : (Ljava/lang/String;)V
    //   123: athrow
    // Exception table:
    //   from	to	target	type
    //   19	61	108	finally
    //   76	92	108	finally
    //   95	108	108	finally
    //   109	111	108	finally
  }
  
  public CameraOfflineSession switchToOffline(Collection<Surface> paramCollection, Executor paramExecutor, CameraOfflineSession.CameraOfflineSessionCallback paramCameraOfflineSessionCallback) throws CameraAccessException {
    if (!paramCollection.isEmpty()) {
      final HashSet<Integer> offlineStreamIds = new HashSet();
      SparseArray<OutputConfiguration> sparseArray = new SparseArray();
      synchronized (this.mInterfaceLock) {
        checkIfCameraClosedOrInError();
        if (this.mOfflineSessionImpl == null) {
          StringBuilder stringBuilder;
          IllegalArgumentException illegalArgumentException;
          for (Surface surface : paramCollection) {
            int i;
            byte b = -1;
            byte b1 = 0;
            while (true) {
              i = b;
              if (b1 < this.mConfiguredOutputs.size()) {
                if (surface == ((OutputConfiguration)this.mConfiguredOutputs.valueAt(b1)).getSurface()) {
                  i = this.mConfiguredOutputs.keyAt(b1);
                  sparseArray.append(i, this.mConfiguredOutputs.valueAt(b1));
                  break;
                } 
                b1++;
                continue;
              } 
              break;
            } 
            if (i != -1) {
              if (this.mOfflineSupport.contains(Integer.valueOf(i))) {
                hashSet.add(Integer.valueOf(i));
                continue;
              } 
              illegalArgumentException = new IllegalArgumentException();
              stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Surface: ");
              stringBuilder.append(surface);
              stringBuilder.append(" does not  support offline mode");
              this(stringBuilder.toString());
              throw illegalArgumentException;
            } 
            IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
            this("Offline surface is not part of this session");
            throw illegalArgumentException1;
          } 
          stopRepeating();
          CameraOfflineSessionImpl cameraOfflineSessionImpl = new CameraOfflineSessionImpl();
          this(this.mCameraId, this.mCharacteristics, (Executor)stringBuilder, (CameraOfflineSession.CameraOfflineSessionCallback)illegalArgumentException, sparseArray, this.mConfiguredInput, this.mConfiguredOutputs, this.mFrameNumberTracker, this.mCaptureCallbackMap, this.mRequestLastFrameNumbersList);
          this.mOfflineSessionImpl = cameraOfflineSessionImpl;
          this.mOfflineSwitchService = Executors.newSingleThreadExecutor();
          this.mConfiguredOutputs.clear();
          AbstractMap.SimpleEntry<Object, Object> simpleEntry = new AbstractMap.SimpleEntry<>();
          this((K)Integer.valueOf(-1), null);
          this.mConfiguredInput = (AbstractMap.SimpleEntry)simpleEntry;
          this.mIdle = true;
          SparseArray<CaptureCallbackHolder> sparseArray1 = new SparseArray();
          this();
          this.mCaptureCallbackMap = sparseArray1;
          FrameNumberTracker frameNumberTracker = new FrameNumberTracker();
          this();
          this.mFrameNumberTracker = frameNumberTracker;
          this.mCurrentSession.closeWithoutDraining();
          this.mCurrentSession = null;
          this.mOfflineSwitchService.execute(new Runnable() {
                public void run() {
                  try {
                    ICameraOfflineSession iCameraOfflineSession = CameraDeviceImpl.this.mRemoteDevice.switchToOffline((ICameraDeviceCallbacks)CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks(), Arrays.stream((Integer[])offlineStreamIds.toArray((Object[])new Integer[offlineStreamIds.size()])).mapToInt((ToIntFunction)_$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray());
                    CameraDeviceImpl.this.mOfflineSessionImpl.setRemoteSession(iCameraOfflineSession);
                  } catch (CameraAccessException cameraAccessException) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.notifyFailedSwitch();
                  } finally {
                    Exception exception;
                  } 
                  CameraDeviceImpl.access$302(CameraDeviceImpl.this, null);
                }
              });
          return cameraOfflineSessionImpl;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Switch to offline mode already in progress");
        throw illegalStateException;
      } 
    } 
    throw new IllegalArgumentException("Invalid offline surfaces!");
  }
  
  public void tearDown(Surface paramSurface) throws CameraAccessException {
    if (paramSurface != null)
      synchronized (this.mInterfaceLock) {
        int i;
        checkIfCameraClosedOrInError();
        byte b = -1;
        byte b1 = 0;
        while (true) {
          i = b;
          if (b1 < this.mConfiguredOutputs.size()) {
            if (paramSurface == ((OutputConfiguration)this.mConfiguredOutputs.valueAt(b1)).getSurface()) {
              i = this.mConfiguredOutputs.keyAt(b1);
              break;
            } 
            b1++;
            continue;
          } 
          break;
        } 
        if (i != -1) {
          this.mRemoteDevice.tearDown(i);
          return;
        } 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("Surface is not part of this session");
        throw illegalArgumentException;
      }  
    throw new IllegalArgumentException("Surface is null");
  }
  
  public void updateOutputConfiguration(OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    synchronized (this.mInterfaceLock) {
      int i;
      checkIfCameraClosedOrInError();
      byte b = -1;
      byte b1 = 0;
      while (true) {
        i = b;
        if (b1 < this.mConfiguredOutputs.size()) {
          if (paramOutputConfiguration.getSurface() == ((OutputConfiguration)this.mConfiguredOutputs.valueAt(b1)).getSurface()) {
            i = this.mConfiguredOutputs.keyAt(b1);
            break;
          } 
          b1++;
          continue;
        } 
        break;
      } 
      if (i != -1) {
        this.mRemoteDevice.updateOutputConfiguration(i, paramOutputConfiguration);
        this.mConfiguredOutputs.put(i, paramOutputConfiguration);
        return;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Invalid output configuration");
      throw illegalArgumentException;
    } 
  }
  
  public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public void onCaptureStarted(CaptureResultExtras param1CaptureResultExtras, long param1Long) {
      int i = param1CaptureResultExtras.getRequestId();
      long l1 = param1CaptureResultExtras.getFrameNumber();
      long l2 = param1CaptureResultExtras.getLastCompletedRegularFrameNumber();
      long l3 = param1CaptureResultExtras.getLastCompletedReprocessFrameNumber();
      long l4 = param1CaptureResultExtras.getLastCompletedZslFrameNumber();
      Object object2 = CameraDeviceImpl.this.mInterfaceLock;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      try {
        if (CameraDeviceImpl.this.mRemoteDevice == null) {
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
          return;
        } 
        CameraOfflineSessionImpl cameraOfflineSessionImpl = CameraDeviceImpl.this.mOfflineSessionImpl;
        if (cameraOfflineSessionImpl != null) {
          try {
            CameraOfflineSessionImpl.CameraDeviceCallbacks cameraDeviceCallbacks = CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks();
            cameraDeviceCallbacks.onCaptureStarted(param1CaptureResultExtras, param1Long);
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
            return;
          } finally {}
        } else {
          CameraDeviceImpl.this.removeCompletedCallbackHolderLocked(l2, l3, l4);
          CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)CameraDeviceImpl.this.mCaptureCallbackMap.get(i);
          if (captureCallbackHolder == null) {
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
            return;
          } 
          if (CameraDeviceImpl.this.isClosed()) {
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
            return;
          } 
          l2 = Binder.clearCallingIdentity();
          try {
            Executor executor = captureCallbackHolder.getExecutor();
            Runnable runnable = new Runnable() {
                public void run() {
                  if (!CameraDeviceImpl.this.isClosed()) {
                    int i = resultExtras.getSubsequenceId();
                    CaptureRequest captureRequest = holder.getRequest(i);
                    if (holder.hasBatchedOutputs()) {
                      Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                      for (byte b = 0; b < holder.getRequestCount(); b++)
                        holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(b), timestamp - (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue(), frameNumber - (i - b)); 
                    } else {
                      holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
                    } 
                  } 
                }
              };
            Object object3 = object2;
            try {
              super(this, param1CaptureResultExtras, captureCallbackHolder, param1Long, l1);
              executor.execute(runnable);
              Object object4 = object3;
              try {
                Binder.restoreCallingIdentity(l2);
                object4 = object3;
                return;
              } finally {
                object3 = null;
              } 
            } finally {}
          } finally {}
          Object object = object2;
          Binder.restoreCallingIdentity(l2);
          object = object2;
          throw cameraOfflineSessionImpl;
        } 
      } finally {}
      CaptureResultExtras captureResultExtras = param1CaptureResultExtras;
      Object object1 = object2;
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      throw captureResultExtras;
    }
    
    public void onDeviceError(int param1Int, CaptureResultExtras param1CaptureResultExtras) {
      CameraDeviceImpl.this.onDeviceError(param1Int, param1CaptureResultExtras);
    }
    
    public void onDeviceIdle() {
      CameraDeviceImpl.this.onDeviceIdle();
    }
    
    public void onPrepared(int param1Int) {
      synchronized (CameraDeviceImpl.this.mInterfaceLock) {
        if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
          CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onPrepared(param1Int);
          return;
        } 
        OutputConfiguration outputConfiguration = (OutputConfiguration)CameraDeviceImpl.this.mConfiguredOutputs.get(param1Int);
        CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
        if (stateCallbackKK == null)
          return; 
        if (outputConfiguration == null) {
          Log.w(CameraDeviceImpl.this.TAG, "onPrepared invoked for unknown output Surface");
          return;
        } 
        null = outputConfiguration.getSurfaces().iterator();
        while (null.hasNext())
          stateCallbackKK.onSurfacePrepared(null.next()); 
        return;
      } 
    }
    
    public void onRepeatingRequestError(long param1Long, int param1Int) {
      synchronized (CameraDeviceImpl.this.mInterfaceLock) {
        if (CameraDeviceImpl.this.mRemoteDevice == null || CameraDeviceImpl.this.mRepeatingRequestId == -1)
          return; 
        if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
          CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onRepeatingRequestError(param1Long, param1Int);
          return;
        } 
        CameraDeviceImpl.this.checkEarlyTriggerSequenceCompleteLocked(CameraDeviceImpl.this.mRepeatingRequestId, param1Long, CameraDeviceImpl.this.mRepeatingRequestTypes);
        if (CameraDeviceImpl.this.mRepeatingRequestId == param1Int) {
          CameraDeviceImpl.access$502(CameraDeviceImpl.this, -1);
          CameraDeviceImpl.access$602(CameraDeviceImpl.this, null);
        } 
        return;
      } 
    }
    
    public void onRequestQueueEmpty() {
      synchronized (CameraDeviceImpl.this.mInterfaceLock) {
        if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
          CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onRequestQueueEmpty();
          return;
        } 
        CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
        if (stateCallbackKK == null)
          return; 
        stateCallbackKK.onRequestQueueEmpty();
        return;
      } 
    }
    
    public void onResultReceived(CameraMetadataNative param1CameraMetadataNative, CaptureResultExtras param1CaptureResultExtras, PhysicalCaptureResultInfo[] param1ArrayOfPhysicalCaptureResultInfo) throws RemoteException {
      // Byte code:
      //   0: aload_2
      //   1: invokevirtual getRequestId : ()I
      //   4: istore #4
      //   6: aload_2
      //   7: invokevirtual getFrameNumber : ()J
      //   10: lstore #5
      //   12: aload_0
      //   13: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   16: getfield mInterfaceLock : Ljava/lang/Object;
      //   19: astore #7
      //   21: aload #7
      //   23: monitorenter
      //   24: aload_0
      //   25: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   28: invokestatic access$000 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/ICameraDeviceUserWrapper;
      //   31: astore #8
      //   33: aload #8
      //   35: ifnonnull -> 49
      //   38: aload #7
      //   40: monitorexit
      //   41: return
      //   42: astore_1
      //   43: aload #7
      //   45: astore_2
      //   46: goto -> 535
      //   49: aload_0
      //   50: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   53: invokestatic access$300 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   56: astore #8
      //   58: aload #8
      //   60: ifnull -> 94
      //   63: aload_0
      //   64: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   67: invokestatic access$300 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   70: invokevirtual getCallbacks : ()Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;
      //   73: astore #8
      //   75: aload #8
      //   77: aload_1
      //   78: aload_2
      //   79: aload_3
      //   80: invokevirtual onResultReceived : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CaptureResultExtras;[Landroid/hardware/camera2/impl/PhysicalCaptureResultInfo;)V
      //   83: aload #7
      //   85: monitorexit
      //   86: return
      //   87: astore_1
      //   88: aload #7
      //   90: astore_2
      //   91: goto -> 535
      //   94: aload_1
      //   95: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
      //   98: aload_0
      //   99: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   102: invokestatic access$1000 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/CameraCharacteristics;
      //   105: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
      //   108: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
      //   111: checkcast android/util/Size
      //   114: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
      //   117: aload_0
      //   118: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   121: invokestatic access$900 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/util/SparseArray;
      //   124: iload #4
      //   126: invokevirtual get : (I)Ljava/lang/Object;
      //   129: checkcast android/hardware/camera2/impl/CaptureCallbackHolder
      //   132: astore #9
      //   134: aload #9
      //   136: aload_2
      //   137: invokevirtual getSubsequenceId : ()I
      //   140: invokevirtual getRequest : (I)Landroid/hardware/camera2/CaptureRequest;
      //   143: astore #10
      //   145: aload_2
      //   146: invokevirtual getPartialResultCount : ()I
      //   149: aload_0
      //   150: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   153: invokestatic access$1100 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)I
      //   156: if_icmpge -> 165
      //   159: iconst_1
      //   160: istore #11
      //   162: goto -> 168
      //   165: iconst_0
      //   166: istore #11
      //   168: aload #10
      //   170: invokevirtual getRequestType : ()I
      //   173: istore #4
      //   175: aload #9
      //   177: ifnonnull -> 201
      //   180: aload_0
      //   181: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   184: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   187: lload #5
      //   189: aconst_null
      //   190: iload #11
      //   192: iload #4
      //   194: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
      //   197: aload #7
      //   199: monitorexit
      //   200: return
      //   201: aload_0
      //   202: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   205: invokestatic access$400 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Z
      //   208: istore #12
      //   210: iload #12
      //   212: ifeq -> 245
      //   215: aload_0
      //   216: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   219: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   222: astore_1
      //   223: aload_1
      //   224: lload #5
      //   226: aconst_null
      //   227: iload #11
      //   229: iload #4
      //   231: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
      //   234: aload #7
      //   236: monitorexit
      //   237: return
      //   238: astore_1
      //   239: aload #7
      //   241: astore_2
      //   242: goto -> 535
      //   245: aload #9
      //   247: invokevirtual hasBatchedOutputs : ()Z
      //   250: istore #12
      //   252: iload #12
      //   254: ifeq -> 278
      //   257: new android/hardware/camera2/impl/CameraMetadataNative
      //   260: astore #8
      //   262: aload #8
      //   264: aload_1
      //   265: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;)V
      //   268: goto -> 281
      //   271: astore_1
      //   272: aload #7
      //   274: astore_2
      //   275: goto -> 535
      //   278: aconst_null
      //   279: astore #8
      //   281: iload #11
      //   283: ifeq -> 320
      //   286: new android/hardware/camera2/CaptureResult
      //   289: astore_3
      //   290: aload_3
      //   291: aload_1
      //   292: aload #10
      //   294: aload_2
      //   295: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;)V
      //   298: new android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$2
      //   301: dup
      //   302: aload_0
      //   303: aload #9
      //   305: aload #8
      //   307: aload_2
      //   308: aload #10
      //   310: aload_3
      //   311: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CaptureResultExtras;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CaptureResult;)V
      //   314: astore_1
      //   315: aload_3
      //   316: astore_2
      //   317: goto -> 430
      //   320: aload #9
      //   322: astore #13
      //   324: aload_0
      //   325: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   328: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   331: lload #5
      //   333: invokevirtual popPartialResults : (J)Ljava/util/List;
      //   336: astore #14
      //   338: aload_1
      //   339: getstatic android/hardware/camera2/CaptureResult.SENSOR_TIMESTAMP : Landroid/hardware/camera2/CaptureResult$Key;
      //   342: invokevirtual get : (Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;
      //   345: checkcast java/lang/Long
      //   348: invokevirtual longValue : ()J
      //   351: lstore #15
      //   353: aload #10
      //   355: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE : Landroid/hardware/camera2/CaptureRequest$Key;
      //   358: invokevirtual get : (Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;
      //   361: checkcast android/util/Range
      //   364: astore #17
      //   366: aload_2
      //   367: invokevirtual getSubsequenceId : ()I
      //   370: istore #18
      //   372: new android/hardware/camera2/TotalCaptureResult
      //   375: astore #19
      //   377: aload #13
      //   379: invokevirtual getSessionId : ()I
      //   382: istore #20
      //   384: aload #19
      //   386: aload_1
      //   387: aload #10
      //   389: aload_2
      //   390: aload #14
      //   392: iload #20
      //   394: aload_3
      //   395: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;I[Landroid/hardware/camera2/impl/PhysicalCaptureResultInfo;)V
      //   398: aload #7
      //   400: astore_3
      //   401: new android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$3
      //   404: dup
      //   405: aload_0
      //   406: aload #13
      //   408: aload #8
      //   410: lload #15
      //   412: iload #18
      //   414: aload #17
      //   416: aload_2
      //   417: aload #14
      //   419: aload #10
      //   421: aload #19
      //   423: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;JILandroid/util/Range;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
      //   426: astore_1
      //   427: aload #19
      //   429: astore_2
      //   430: aload #7
      //   432: astore_3
      //   433: invokestatic clearCallingIdentity : ()J
      //   436: lstore #15
      //   438: aload #9
      //   440: invokevirtual getExecutor : ()Ljava/util/concurrent/Executor;
      //   443: aload_1
      //   444: invokeinterface execute : (Ljava/lang/Runnable;)V
      //   449: aload #7
      //   451: astore_3
      //   452: lload #15
      //   454: invokestatic restoreCallingIdentity : (J)V
      //   457: aload #7
      //   459: astore_3
      //   460: aload_0
      //   461: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   464: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   467: lload #5
      //   469: aload_2
      //   470: iload #11
      //   472: iload #4
      //   474: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
      //   477: iload #11
      //   479: ifne -> 492
      //   482: aload #7
      //   484: astore_3
      //   485: aload_0
      //   486: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
      //   489: invokestatic access$1300 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)V
      //   492: aload #7
      //   494: astore_3
      //   495: aload #7
      //   497: monitorexit
      //   498: return
      //   499: astore_1
      //   500: aload #7
      //   502: astore_3
      //   503: lload #15
      //   505: invokestatic restoreCallingIdentity : (J)V
      //   508: aload #7
      //   510: astore_3
      //   511: aload_1
      //   512: athrow
      //   513: astore_1
      //   514: goto -> 518
      //   517: astore_1
      //   518: aload #7
      //   520: astore_2
      //   521: goto -> 535
      //   524: astore_1
      //   525: aload #7
      //   527: astore_2
      //   528: goto -> 535
      //   531: astore_1
      //   532: aload #7
      //   534: astore_2
      //   535: aload_2
      //   536: astore_3
      //   537: aload_2
      //   538: monitorexit
      //   539: aload_1
      //   540: athrow
      //   541: astore_1
      //   542: aload_3
      //   543: astore_2
      //   544: goto -> 535
      // Exception table:
      //   from	to	target	type
      //   24	33	531	finally
      //   38	41	42	finally
      //   49	58	531	finally
      //   63	75	87	finally
      //   75	86	42	finally
      //   94	145	531	finally
      //   145	159	531	finally
      //   168	175	531	finally
      //   180	200	42	finally
      //   201	210	531	finally
      //   215	223	238	finally
      //   223	237	271	finally
      //   245	252	524	finally
      //   257	268	271	finally
      //   286	315	271	finally
      //   324	384	524	finally
      //   384	398	517	finally
      //   401	427	541	finally
      //   433	438	541	finally
      //   438	449	499	finally
      //   452	457	541	finally
      //   460	477	541	finally
      //   485	492	541	finally
      //   495	498	541	finally
      //   503	508	541	finally
      //   511	513	541	finally
      //   537	539	541	finally
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (!CameraDeviceImpl.this.isClosed()) {
        int i = resultExtras.getSubsequenceId();
        CaptureRequest captureRequest = holder.getRequest(i);
        if (holder.hasBatchedOutputs()) {
          Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
          for (byte b = 0; b < holder.getRequestCount(); b++)
            holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(b), timestamp - (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue(), frameNumber - (i - b)); 
        } else {
          holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
        } 
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (!CameraDeviceImpl.this.isClosed())
        if (holder.hasBatchedOutputs()) {
          for (byte b = 0; b < holder.getRequestCount(); b++) {
            CaptureResult captureResult = new CaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras);
            holder.getCallback().onCaptureProgressed(CameraDeviceImpl.this, holder.getRequest(b), captureResult);
          } 
        } else {
          holder.getCallback().onCaptureProgressed(CameraDeviceImpl.this, request, resultAsCapture);
        }  
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (!CameraDeviceImpl.this.isClosed())
        if (holder.hasBatchedOutputs()) {
          for (byte b = 0; b < holder.getRequestCount(); b++) {
            resultCopy.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(sensorTimestamp - (subsequenceId - b) * 1000000000L / ((Integer)fpsRange.getUpper()).intValue()));
            TotalCaptureResult totalCaptureResult = new TotalCaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras, partialResults, holder.getSessionId(), new PhysicalCaptureResultInfo[0]);
            holder.getCallback().onCaptureCompleted(CameraDeviceImpl.this, holder.getRequest(b), totalCaptureResult);
          } 
        } else {
          holder.getCallback().onCaptureCompleted(CameraDeviceImpl.this, request, resultAsCapture);
        }  
    }
  }
  
  private static class CameraHandlerExecutor implements Executor {
    private final Handler mHandler;
    
    public CameraHandlerExecutor(Handler param1Handler) {
      Objects.requireNonNull(param1Handler);
      this.mHandler = param1Handler;
    }
    
    public void execute(Runnable param1Runnable) {
      this.mHandler.post(param1Runnable);
    }
  }
  
  public static abstract class StateCallbackKK extends CameraDevice.StateCallback {
    public void onActive(CameraDevice param1CameraDevice) {}
    
    public void onBusy(CameraDevice param1CameraDevice) {}
    
    public void onIdle(CameraDevice param1CameraDevice) {}
    
    public void onRequestQueueEmpty() {}
    
    public void onSurfacePrepared(Surface param1Surface) {}
    
    public void onUnconfigured(CameraDevice param1CameraDevice) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */