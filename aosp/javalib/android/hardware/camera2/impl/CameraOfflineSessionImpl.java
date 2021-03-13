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
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Range;
import android.util.SparseArray;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class CameraOfflineSessionImpl extends CameraOfflineSession implements IBinder.DeathRecipient {
  private static final long NANO_PER_SECOND = 1000000000L;
  
  private static final int REQUEST_ID_NONE = -1;
  
  private static final String TAG = "CameraOfflineSessionImpl";
  
  private final boolean DEBUG = false;
  
  private final CameraDeviceCallbacks mCallbacks = new CameraDeviceCallbacks();
  
  private final String mCameraId;
  
  private SparseArray<CaptureCallbackHolder> mCaptureCallbackMap = new SparseArray();
  
  private final CameraCharacteristics mCharacteristics;
  
  private final AtomicBoolean mClosing = new AtomicBoolean();
  
  private SparseArray<OutputConfiguration> mConfiguredOutputs = new SparseArray();
  
  private FrameNumberTracker mFrameNumberTracker = new FrameNumberTracker();
  
  final Object mInterfaceLock = new Object();
  
  private final CameraOfflineSession.CameraOfflineSessionCallback mOfflineCallback;
  
  private final Executor mOfflineExecutor;
  
  private AbstractMap.SimpleEntry<Integer, InputConfiguration> mOfflineInput = new AbstractMap.SimpleEntry<>(Integer.valueOf(-1), null);
  
  private SparseArray<OutputConfiguration> mOfflineOutputs = new SparseArray();
  
  private List<RequestLastFrameNumbersHolder> mOfflineRequestLastFrameNumbersList = new ArrayList<>();
  
  private ICameraOfflineSession mRemoteSession;
  
  private final int mTotalPartialCount;
  
  public CameraOfflineSessionImpl(String paramString, CameraCharacteristics paramCameraCharacteristics, Executor paramExecutor, CameraOfflineSession.CameraOfflineSessionCallback paramCameraOfflineSessionCallback, SparseArray<OutputConfiguration> paramSparseArray1, AbstractMap.SimpleEntry<Integer, InputConfiguration> paramSimpleEntry, SparseArray<OutputConfiguration> paramSparseArray2, FrameNumberTracker paramFrameNumberTracker, SparseArray<CaptureCallbackHolder> paramSparseArray, List<RequestLastFrameNumbersHolder> paramList) {
    if (paramString != null && paramCameraCharacteristics != null) {
      this.mCameraId = paramString;
      this.mCharacteristics = paramCameraCharacteristics;
      Integer integer = (Integer)paramCameraCharacteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
      if (integer == null) {
        this.mTotalPartialCount = 1;
      } else {
        this.mTotalPartialCount = integer.intValue();
      } 
      this.mOfflineRequestLastFrameNumbersList.addAll(paramList);
      this.mFrameNumberTracker = paramFrameNumberTracker;
      this.mCaptureCallbackMap = paramSparseArray;
      this.mConfiguredOutputs = paramSparseArray2;
      this.mOfflineOutputs = paramSparseArray1;
      this.mOfflineInput = paramSimpleEntry;
      this.mOfflineExecutor = (Executor)Preconditions.checkNotNull(paramExecutor, "offline executor must not be null");
      this.mOfflineCallback = (CameraOfflineSession.CameraOfflineSessionCallback)Preconditions.checkNotNull(paramCameraOfflineSessionCallback, "offline callback must not be null");
      return;
    } 
    throw new IllegalArgumentException("Null argument given");
  }
  
  private void checkAndFireSequenceComplete() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mFrameNumberTracker : Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   4: invokevirtual getCompletedFrameNumber : ()J
    //   7: lstore_1
    //   8: aload_0
    //   9: getfield mFrameNumberTracker : Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   12: invokevirtual getCompletedReprocessFrameNumber : ()J
    //   15: lstore_3
    //   16: aload_0
    //   17: getfield mFrameNumberTracker : Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   20: invokevirtual getCompletedZslStillFrameNumber : ()J
    //   23: lstore #5
    //   25: aload_0
    //   26: getfield mOfflineRequestLastFrameNumbersList : Ljava/util/List;
    //   29: invokeinterface iterator : ()Ljava/util/Iterator;
    //   34: astore #7
    //   36: aload #7
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 333
    //   46: aload #7
    //   48: invokeinterface next : ()Ljava/lang/Object;
    //   53: checkcast android/hardware/camera2/impl/RequestLastFrameNumbersHolder
    //   56: astore #8
    //   58: iconst_0
    //   59: istore #9
    //   61: iconst_0
    //   62: istore #10
    //   64: aload #8
    //   66: invokevirtual getRequestId : ()I
    //   69: istore #11
    //   71: aload_0
    //   72: getfield mInterfaceLock : Ljava/lang/Object;
    //   75: astore #12
    //   77: aload #12
    //   79: monitorenter
    //   80: aload_0
    //   81: getfield mCaptureCallbackMap : Landroid/util/SparseArray;
    //   84: iload #11
    //   86: invokevirtual indexOfKey : (I)I
    //   89: istore #13
    //   91: iload #13
    //   93: iflt -> 118
    //   96: aload_0
    //   97: getfield mCaptureCallbackMap : Landroid/util/SparseArray;
    //   100: iload #13
    //   102: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   105: checkcast android/hardware/camera2/impl/CaptureCallbackHolder
    //   108: astore #14
    //   110: goto -> 121
    //   113: astore #14
    //   115: goto -> 322
    //   118: aconst_null
    //   119: astore #14
    //   121: aload #14
    //   123: ifnull -> 207
    //   126: aload #8
    //   128: invokevirtual getLastRegularFrameNumber : ()J
    //   131: lstore #15
    //   133: aload #8
    //   135: invokevirtual getLastReprocessFrameNumber : ()J
    //   138: lstore #17
    //   140: aload #8
    //   142: invokevirtual getLastZslStillFrameNumber : ()J
    //   145: lstore #19
    //   147: aload #14
    //   149: invokevirtual getCallback : ()Landroid/hardware/camera2/impl/CaptureCallback;
    //   152: invokevirtual getExecutor : ()Ljava/util/concurrent/Executor;
    //   155: astore #21
    //   157: aload #14
    //   159: invokevirtual getCallback : ()Landroid/hardware/camera2/impl/CaptureCallback;
    //   162: invokevirtual getSessionCallback : ()Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;
    //   165: astore #22
    //   167: lload #15
    //   169: lload_1
    //   170: lcmp
    //   171: ifgt -> 204
    //   174: lload #17
    //   176: lload_3
    //   177: lcmp
    //   178: ifgt -> 204
    //   181: lload #19
    //   183: lload #5
    //   185: lcmp
    //   186: ifgt -> 204
    //   189: iconst_1
    //   190: istore #10
    //   192: aload_0
    //   193: getfield mCaptureCallbackMap : Landroid/util/SparseArray;
    //   196: iload #13
    //   198: invokevirtual removeAt : (I)V
    //   201: goto -> 204
    //   204: goto -> 217
    //   207: aconst_null
    //   208: astore #21
    //   210: aconst_null
    //   211: astore #22
    //   213: iload #9
    //   215: istore #10
    //   217: aload #12
    //   219: monitorexit
    //   220: aload #14
    //   222: ifnull -> 230
    //   225: iload #10
    //   227: ifeq -> 237
    //   230: aload #7
    //   232: invokeinterface remove : ()V
    //   237: iload #10
    //   239: ifeq -> 317
    //   242: aload #22
    //   244: ifnull -> 317
    //   247: aload #21
    //   249: ifnull -> 317
    //   252: new android/hardware/camera2/impl/CameraOfflineSessionImpl$1
    //   255: dup
    //   256: aload_0
    //   257: aload #22
    //   259: iload #11
    //   261: aload #8
    //   263: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;ILandroid/hardware/camera2/impl/RequestLastFrameNumbersHolder;)V
    //   266: astore #14
    //   268: invokestatic clearCallingIdentity : ()J
    //   271: lstore #19
    //   273: aload #21
    //   275: aload #14
    //   277: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   282: lload #19
    //   284: invokestatic restoreCallingIdentity : (J)V
    //   287: aload_0
    //   288: getfield mCaptureCallbackMap : Landroid/util/SparseArray;
    //   291: invokevirtual size : ()I
    //   294: ifne -> 317
    //   297: aload_0
    //   298: invokevirtual getCallbacks : ()Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;
    //   301: invokevirtual onDeviceIdle : ()V
    //   304: goto -> 317
    //   307: astore #14
    //   309: lload #19
    //   311: invokestatic restoreCallingIdentity : (J)V
    //   314: aload #14
    //   316: athrow
    //   317: goto -> 36
    //   320: astore #14
    //   322: aload #12
    //   324: monitorexit
    //   325: aload #14
    //   327: athrow
    //   328: astore #14
    //   330: goto -> 322
    //   333: return
    // Exception table:
    //   from	to	target	type
    //   80	91	320	finally
    //   96	110	113	finally
    //   126	133	320	finally
    //   133	140	320	finally
    //   140	167	320	finally
    //   192	201	328	finally
    //   217	220	328	finally
    //   273	282	307	finally
    //   322	325	328	finally
  }
  
  private void disconnect() {
    synchronized (this.mInterfaceLock) {
      if (this.mClosing.getAndSet(true))
        return; 
      if (this.mRemoteSession != null) {
        this.mRemoteSession.asBinder().unlinkToDeath(this, 0);
        try {
          this.mRemoteSession.disconnect();
        } catch (RemoteException remoteException) {
          Log.e("CameraOfflineSessionImpl", "Exception while disconnecting from offline session: ", (Throwable)remoteException);
        } 
        this.mRemoteSession = null;
        null = new Runnable() {
            public void run() {
              CameraOfflineSessionImpl.this.mOfflineCallback.onClosed(CameraOfflineSessionImpl.this);
            }
          };
        super(this);
        long l = Binder.clearCallingIdentity();
        try {
          this.mOfflineExecutor.execute(null);
          return;
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Offline session is not yet ready");
      throw illegalStateException;
    } 
  }
  
  private boolean isClosed() {
    return this.mClosing.get();
  }
  
  private void removeCompletedCallbackHolderLocked(long paramLong1, long paramLong2, long paramLong3) {
    Iterator<RequestLastFrameNumbersHolder> iterator = this.mOfflineRequestLastFrameNumbersList.iterator();
    while (iterator.hasNext()) {
      StringBuilder stringBuilder;
      RequestLastFrameNumbersHolder requestLastFrameNumbersHolder = iterator.next();
      int i = requestLastFrameNumbersHolder.getRequestId();
      int j = this.mCaptureCallbackMap.indexOfKey(i);
      if (j >= 0) {
        stringBuilder = (StringBuilder)this.mCaptureCallbackMap.valueAt(j);
      } else {
        stringBuilder = null;
      } 
      if (stringBuilder != null) {
        long l1 = requestLastFrameNumbersHolder.getLastRegularFrameNumber();
        long l2 = requestLastFrameNumbersHolder.getLastReprocessFrameNumber();
        long l3 = requestLastFrameNumbersHolder.getLastZslStillFrameNumber();
        if (l1 <= paramLong1 && l2 <= paramLong2 && l3 <= paramLong3) {
          if (requestLastFrameNumbersHolder.isSequenceCompleted()) {
            this.mCaptureCallbackMap.removeAt(j);
            iterator.remove();
            continue;
          } 
          stringBuilder = new StringBuilder();
          stringBuilder.append("Sequence not yet completed for request id ");
          stringBuilder.append(i);
          Log.e("CameraOfflineSessionImpl", stringBuilder.toString());
        } 
      } 
    } 
  }
  
  public void abortCaptures() throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void binderDied() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CameraOfflineSession on device ");
    stringBuilder.append(this.mCameraId);
    stringBuilder.append(" died unexpectedly");
    Log.w("CameraOfflineSessionImpl", stringBuilder.toString());
    disconnect();
  }
  
  public int capture(CaptureRequest paramCaptureRequest, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public int captureBurst(List<CaptureRequest> paramList, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public int captureBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public int captureSingleRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void close() {
    disconnect();
  }
  
  protected void finalize() throws Throwable {
    try {
      disconnect();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public void finalizeOutputConfigurations(List<OutputConfiguration> paramList) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public CameraDeviceCallbacks getCallbacks() {
    return this.mCallbacks;
  }
  
  public CameraDevice getDevice() {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public Surface getInputSurface() {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public boolean isReprocessable() {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void notifyFailedSwitch() {
    synchronized (this.mInterfaceLock) {
      null = new Runnable() {
          public void run() {
            CameraOfflineSessionImpl.this.mOfflineCallback.onSwitchFailed(CameraOfflineSessionImpl.this);
          }
        };
      super(this);
      long l = Binder.clearCallingIdentity();
      try {
        this.mOfflineExecutor.execute(null);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void prepare(int paramInt, Surface paramSurface) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void prepare(Surface paramSurface) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void setRemoteSession(ICameraOfflineSession paramICameraOfflineSession) throws CameraAccessException {
    // Byte code:
    //   0: aload_0
    //   1: getfield mInterfaceLock : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnonnull -> 18
    //   11: aload_0
    //   12: invokevirtual notifyFailedSwitch : ()V
    //   15: aload_2
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: aload_1
    //   20: putfield mRemoteSession : Landroid/hardware/camera2/ICameraOfflineSession;
    //   23: aload_1
    //   24: invokeinterface asBinder : ()Landroid/os/IBinder;
    //   29: astore_1
    //   30: aload_1
    //   31: ifnull -> 94
    //   34: aload_1
    //   35: aload_0
    //   36: iconst_0
    //   37: invokeinterface linkToDeath : (Landroid/os/IBinder$DeathRecipient;I)V
    //   42: new android/hardware/camera2/impl/CameraOfflineSessionImpl$3
    //   45: astore_1
    //   46: aload_1
    //   47: aload_0
    //   48: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)V
    //   51: invokestatic clearCallingIdentity : ()J
    //   54: lstore_3
    //   55: aload_0
    //   56: getfield mOfflineExecutor : Ljava/util/concurrent/Executor;
    //   59: aload_1
    //   60: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   65: lload_3
    //   66: invokestatic restoreCallingIdentity : (J)V
    //   69: aload_2
    //   70: monitorexit
    //   71: return
    //   72: astore_1
    //   73: lload_3
    //   74: invokestatic restoreCallingIdentity : (J)V
    //   77: aload_1
    //   78: athrow
    //   79: astore_1
    //   80: new android/hardware/camera2/CameraAccessException
    //   83: astore_1
    //   84: aload_1
    //   85: iconst_2
    //   86: ldc_w 'The camera offline session has encountered a serious error'
    //   89: invokespecial <init> : (ILjava/lang/String;)V
    //   92: aload_1
    //   93: athrow
    //   94: new android/hardware/camera2/CameraAccessException
    //   97: astore_1
    //   98: aload_1
    //   99: iconst_2
    //   100: ldc_w 'The camera offline session has encountered a serious error'
    //   103: invokespecial <init> : (ILjava/lang/String;)V
    //   106: aload_1
    //   107: athrow
    //   108: astore_1
    //   109: aload_2
    //   110: monitorexit
    //   111: aload_1
    //   112: athrow
    // Exception table:
    //   from	to	target	type
    //   11	17	108	finally
    //   18	30	108	finally
    //   34	42	79	android/os/RemoteException
    //   34	42	108	finally
    //   42	55	108	finally
    //   55	65	72	finally
    //   65	69	108	finally
    //   69	71	108	finally
    //   73	77	108	finally
    //   77	79	108	finally
    //   80	94	108	finally
    //   94	108	108	finally
    //   109	111	108	finally
  }
  
  public int setRepeatingBurst(List<CaptureRequest> paramList, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public int setRepeatingBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public int setRepeatingRequest(CaptureRequest paramCaptureRequest, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public int setSingleRepeatingRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void stopRepeating() throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public boolean supportsOfflineProcessing(Surface paramSurface) {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public CameraOfflineSession switchToOffline(Collection<Surface> paramCollection, Executor paramExecutor, CameraOfflineSession.CameraOfflineSessionCallback paramCameraOfflineSessionCallback) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void tearDown(Surface paramSurface) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public void updateOutputConfiguration(OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    throw new UnsupportedOperationException("Operation not supported in offline mode");
  }
  
  public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
    private void onCaptureErrorLocked(int param1Int, CaptureResultExtras param1CaptureResultExtras) {
      Iterator<Surface> iterator;
      final Executor holder;
      int i = param1CaptureResultExtras.getRequestId();
      int j = param1CaptureResultExtras.getSubsequenceId();
      final long frameNumber = param1CaptureResultExtras.getFrameNumber();
      String str = param1CaptureResultExtras.getErrorPhysicalCameraId();
      CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)CameraOfflineSessionImpl.this.mCaptureCallbackMap.get(i);
      boolean bool = false;
      if (captureCallbackHolder == null) {
        Log.e("CameraOfflineSessionImpl", String.format("Receive capture error on unknown request ID %d", new Object[] { Integer.valueOf(i) }));
        return;
      } 
      final CaptureRequest request = captureCallbackHolder.getRequest(j);
      if (param1Int == 5) {
        OutputConfiguration outputConfiguration;
        if (CameraOfflineSessionImpl.this.mRemoteSession == null && !CameraOfflineSessionImpl.this.isClosed()) {
          outputConfiguration = (OutputConfiguration)CameraOfflineSessionImpl.this.mConfiguredOutputs.get(param1CaptureResultExtras.getErrorStreamId());
        } else {
          outputConfiguration = (OutputConfiguration)CameraOfflineSessionImpl.this.mOfflineOutputs.get(param1CaptureResultExtras.getErrorStreamId());
        } 
        if (outputConfiguration == null) {
          Log.v("CameraOfflineSessionImpl", String.format("Stream %d has been removed. Skipping buffer lost callback", new Object[] { Integer.valueOf(param1CaptureResultExtras.getErrorStreamId()) }));
          return;
        } 
        iterator = outputConfiguration.getSurfaces().iterator();
        final CaptureCallbackHolder holder = captureCallbackHolder;
        while (iterator.hasNext()) {
          final Surface surface = iterator.next();
          if (!captureRequest.containsTarget(surface))
            continue; 
          executor = captureCallbackHolder1.getCallback().getExecutor();
          Runnable runnable = new Runnable() {
              public void run() {
                CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
                if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null)
                  captureCallback.onCaptureBufferLost((CameraCaptureSession)CameraOfflineSessionImpl.this, request, surface, frameNumber); 
              }
            };
          if (executor != null) {
            long l1 = Binder.clearCallingIdentity();
            try {
              executor.execute(runnable);
            } finally {
              Binder.restoreCallingIdentity(l1);
            } 
          } 
        } 
      } else {
        if (param1Int == 4)
          bool = true; 
        final CaptureFailure failure = new CaptureFailure(captureRequest, 0, bool, i, l, (String)iterator);
        Executor executor1 = executor.getCallback().getExecutor();
        Runnable runnable = new Runnable() {
            public void run() {
              CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
              if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null)
                captureCallback.onCaptureFailed((CameraCaptureSession)CameraOfflineSessionImpl.this, request, failure); 
            }
          };
        CameraOfflineSessionImpl.this.mFrameNumberTracker.updateTracker(l, true, captureRequest.getRequestType());
        CameraOfflineSessionImpl.this.checkAndFireSequenceComplete();
        if (executor1 != null) {
          long l1 = Binder.clearCallingIdentity();
          try {
            executor1.execute(runnable);
          } finally {
            Binder.restoreCallingIdentity(l1);
          } 
        } 
      } 
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public void onCaptureStarted(CaptureResultExtras param1CaptureResultExtras, long param1Long) {
      Object object3;
      int i = param1CaptureResultExtras.getRequestId();
      long l1 = param1CaptureResultExtras.getFrameNumber();
      long l2 = param1CaptureResultExtras.getLastCompletedRegularFrameNumber();
      long l3 = param1CaptureResultExtras.getLastCompletedReprocessFrameNumber();
      long l4 = param1CaptureResultExtras.getLastCompletedZslFrameNumber();
      Object object2 = CameraOfflineSessionImpl.this.mInterfaceLock;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      try {
        CameraOfflineSessionImpl.this.removeCompletedCallbackHolderLocked(l2, l3, l4);
        CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)CameraOfflineSessionImpl.this.mCaptureCallbackMap.get(i);
        if (captureCallbackHolder == null)
          return; 
        Executor executor = captureCallbackHolder.getCallback().getExecutor();
        if (CameraOfflineSessionImpl.this.isClosed() || executor == null) {
          object = object2;
          return;
        } 
        l4 = Binder.clearCallingIdentity();
        try {
          Runnable runnable = new Runnable() {
              public void run() {
                CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
                if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null) {
                  int i = resultExtras.getSubsequenceId();
                  CaptureRequest captureRequest = holder.getRequest(i);
                  if (holder.hasBatchedOutputs()) {
                    Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                    for (byte b = 0; b < holder.getRequestCount(); b++) {
                      CaptureRequest captureRequest1 = holder.getRequest(b);
                      long l1 = timestamp;
                      long l2 = (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue();
                      long l3 = frameNumber;
                      long l4 = (i - b);
                      captureCallback.onCaptureStarted((CameraCaptureSession)CameraOfflineSessionImpl.this, captureRequest1, l1 - l2, l3 - l4);
                    } 
                  } else {
                    captureCallback.onCaptureStarted((CameraCaptureSession)CameraOfflineSessionImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
                  } 
                } 
              }
            };
          object3 = object2;
          try {
            super(this, captureCallbackHolder, (CaptureResultExtras)object, param1Long, l1);
            executor.execute(runnable);
            object = object3;
            try {
              Binder.restoreCallingIdentity(l4);
              return;
            } finally {
              object3 = null;
            } 
          } finally {}
        } finally {}
        Object object = object2;
        Binder.restoreCallingIdentity(l4);
        object = object2;
        throw object3;
      } finally {
        param1CaptureResultExtras = null;
      } 
      Object object1 = object2;
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      throw object3;
    }
    
    public void onDeviceError(int param1Int, CaptureResultExtras param1CaptureResultExtras) {
      // Byte code:
      //   0: aload_0
      //   1: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   4: getfield mInterfaceLock : Ljava/lang/Object;
      //   7: astore_3
      //   8: aload_3
      //   9: monitorenter
      //   10: iload_1
      //   11: iconst_3
      //   12: if_icmpeq -> 68
      //   15: iload_1
      //   16: iconst_4
      //   17: if_icmpeq -> 68
      //   20: iload_1
      //   21: iconst_5
      //   22: if_icmpeq -> 68
      //   25: new android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$1
      //   28: astore_2
      //   29: aload_2
      //   30: aload_0
      //   31: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;)V
      //   34: invokestatic clearCallingIdentity : ()J
      //   37: lstore #4
      //   39: aload_0
      //   40: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   43: invokestatic access$200 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Ljava/util/concurrent/Executor;
      //   46: aload_2
      //   47: invokeinterface execute : (Ljava/lang/Runnable;)V
      //   52: lload #4
      //   54: invokestatic restoreCallingIdentity : (J)V
      //   57: goto -> 74
      //   60: astore_2
      //   61: lload #4
      //   63: invokestatic restoreCallingIdentity : (J)V
      //   66: aload_2
      //   67: athrow
      //   68: aload_0
      //   69: iload_1
      //   70: aload_2
      //   71: invokespecial onCaptureErrorLocked : (ILandroid/hardware/camera2/impl/CaptureResultExtras;)V
      //   74: aload_3
      //   75: monitorexit
      //   76: return
      //   77: astore_2
      //   78: aload_3
      //   79: monitorexit
      //   80: aload_2
      //   81: athrow
      // Exception table:
      //   from	to	target	type
      //   25	39	77	finally
      //   39	52	60	finally
      //   52	57	77	finally
      //   61	66	77	finally
      //   66	68	77	finally
      //   68	74	77	finally
      //   74	76	77	finally
      //   78	80	77	finally
    }
    
    public void onDeviceIdle() {
      synchronized (CameraOfflineSessionImpl.this.mInterfaceLock) {
        if (CameraOfflineSessionImpl.this.mRemoteSession == null) {
          Log.v("CameraOfflineSessionImpl", "Ignoring idle state notifications during offline switches");
          return;
        } 
        CameraOfflineSessionImpl.this.removeCompletedCallbackHolderLocked(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
        null = new Runnable() {
            public void run() {
              if (!CameraOfflineSessionImpl.this.isClosed())
                CameraOfflineSessionImpl.this.mOfflineCallback.onIdle(CameraOfflineSessionImpl.this); 
            }
          };
        super(this);
        long l = Binder.clearCallingIdentity();
        try {
          CameraOfflineSessionImpl.this.mOfflineExecutor.execute(null);
          return;
        } finally {
          Binder.restoreCallingIdentity(l);
        } 
      } 
    }
    
    public void onPrepared(int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected stream ");
      stringBuilder.append(param1Int);
      stringBuilder.append(" is prepared");
      Log.e("CameraOfflineSessionImpl", stringBuilder.toString());
    }
    
    public void onRepeatingRequestError(long param1Long, int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected repeating request error received. Last frame number is ");
      stringBuilder.append(param1Long);
      Log.e("CameraOfflineSessionImpl", stringBuilder.toString());
    }
    
    public void onRequestQueueEmpty() {
      Log.v("CameraOfflineSessionImpl", "onRequestQueueEmpty");
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
      //   13: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   16: getfield mInterfaceLock : Ljava/lang/Object;
      //   19: astore #7
      //   21: aload #7
      //   23: monitorenter
      //   24: aload_1
      //   25: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
      //   28: aload_0
      //   29: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   32: invokestatic access$600 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/CameraCharacteristics;
      //   35: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
      //   38: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
      //   41: checkcast android/util/Size
      //   44: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
      //   47: aload_0
      //   48: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   51: invokestatic access$500 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/util/SparseArray;
      //   54: iload #4
      //   56: invokevirtual get : (I)Ljava/lang/Object;
      //   59: checkcast android/hardware/camera2/impl/CaptureCallbackHolder
      //   62: astore #8
      //   64: aload #8
      //   66: aload_2
      //   67: invokevirtual getSubsequenceId : ()I
      //   70: invokevirtual getRequest : (I)Landroid/hardware/camera2/CaptureRequest;
      //   73: astore #9
      //   75: aload_2
      //   76: invokevirtual getPartialResultCount : ()I
      //   79: aload_0
      //   80: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   83: invokestatic access$700 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)I
      //   86: if_icmpge -> 95
      //   89: iconst_1
      //   90: istore #10
      //   92: goto -> 98
      //   95: iconst_0
      //   96: istore #10
      //   98: aload #9
      //   100: invokevirtual getRequestType : ()I
      //   103: istore #11
      //   105: aload #8
      //   107: ifnonnull -> 138
      //   110: aload_0
      //   111: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   114: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   117: lload #5
      //   119: aconst_null
      //   120: iload #10
      //   122: iload #11
      //   124: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
      //   127: aload #7
      //   129: monitorexit
      //   130: return
      //   131: astore_1
      //   132: aload #7
      //   134: astore_2
      //   135: goto -> 456
      //   138: aload_0
      //   139: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   142: invokestatic access$000 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Z
      //   145: istore #12
      //   147: iload #12
      //   149: ifeq -> 173
      //   152: aload_0
      //   153: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   156: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   159: lload #5
      //   161: aconst_null
      //   162: iload #10
      //   164: iload #11
      //   166: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
      //   169: aload #7
      //   171: monitorexit
      //   172: return
      //   173: aload #8
      //   175: invokevirtual hasBatchedOutputs : ()Z
      //   178: istore #12
      //   180: iload #12
      //   182: ifeq -> 199
      //   185: new android/hardware/camera2/impl/CameraMetadataNative
      //   188: astore #13
      //   190: aload #13
      //   192: aload_1
      //   193: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;)V
      //   196: goto -> 202
      //   199: aconst_null
      //   200: astore #13
      //   202: aload #8
      //   204: invokevirtual getCallback : ()Landroid/hardware/camera2/impl/CaptureCallback;
      //   207: invokevirtual getExecutor : ()Ljava/util/concurrent/Executor;
      //   210: astore #14
      //   212: iload #10
      //   214: ifeq -> 251
      //   217: new android/hardware/camera2/CaptureResult
      //   220: astore_3
      //   221: aload_3
      //   222: aload_1
      //   223: aload #9
      //   225: aload_2
      //   226: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;)V
      //   229: new android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$4
      //   232: dup
      //   233: aload_0
      //   234: aload #8
      //   236: aload #13
      //   238: aload_2
      //   239: aload #9
      //   241: aload_3
      //   242: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CaptureResultExtras;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CaptureResult;)V
      //   245: astore_1
      //   246: aload_3
      //   247: astore_2
      //   248: goto -> 357
      //   251: aload_0
      //   252: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   255: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   258: lload #5
      //   260: invokevirtual popPartialResults : (J)Ljava/util/List;
      //   263: astore #15
      //   265: aload_1
      //   266: getstatic android/hardware/camera2/CaptureResult.SENSOR_TIMESTAMP : Landroid/hardware/camera2/CaptureResult$Key;
      //   269: invokevirtual get : (Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;
      //   272: checkcast java/lang/Long
      //   275: invokevirtual longValue : ()J
      //   278: lstore #16
      //   280: aload #9
      //   282: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE : Landroid/hardware/camera2/CaptureRequest$Key;
      //   285: invokevirtual get : (Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;
      //   288: checkcast android/util/Range
      //   291: astore #18
      //   293: aload_2
      //   294: invokevirtual getSubsequenceId : ()I
      //   297: istore #4
      //   299: new android/hardware/camera2/TotalCaptureResult
      //   302: astore #19
      //   304: aload #8
      //   306: invokevirtual getSessionId : ()I
      //   309: istore #20
      //   311: aload #19
      //   313: aload_1
      //   314: aload #9
      //   316: aload_2
      //   317: aload #15
      //   319: iload #20
      //   321: aload_3
      //   322: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;I[Landroid/hardware/camera2/impl/PhysicalCaptureResultInfo;)V
      //   325: aload #7
      //   327: astore_3
      //   328: new android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$5
      //   331: dup
      //   332: aload_0
      //   333: aload #8
      //   335: aload #13
      //   337: lload #16
      //   339: iload #4
      //   341: aload #18
      //   343: aload_2
      //   344: aload #15
      //   346: aload #9
      //   348: aload #19
      //   350: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;JILandroid/util/Range;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
      //   353: astore_1
      //   354: aload #19
      //   356: astore_2
      //   357: aload #14
      //   359: ifnull -> 403
      //   362: aload #7
      //   364: astore_3
      //   365: invokestatic clearCallingIdentity : ()J
      //   368: lstore #16
      //   370: aload #14
      //   372: aload_1
      //   373: invokeinterface execute : (Ljava/lang/Runnable;)V
      //   378: aload #7
      //   380: astore_3
      //   381: lload #16
      //   383: invokestatic restoreCallingIdentity : (J)V
      //   386: goto -> 403
      //   389: astore_1
      //   390: aload #7
      //   392: astore_3
      //   393: lload #16
      //   395: invokestatic restoreCallingIdentity : (J)V
      //   398: aload #7
      //   400: astore_3
      //   401: aload_1
      //   402: athrow
      //   403: aload #7
      //   405: astore_3
      //   406: aload_0
      //   407: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   410: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
      //   413: lload #5
      //   415: aload_2
      //   416: iload #10
      //   418: iload #11
      //   420: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
      //   423: iload #10
      //   425: ifne -> 438
      //   428: aload #7
      //   430: astore_3
      //   431: aload_0
      //   432: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
      //   435: invokestatic access$900 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)V
      //   438: aload #7
      //   440: astore_3
      //   441: aload #7
      //   443: monitorexit
      //   444: return
      //   445: astore_1
      //   446: aload #7
      //   448: astore_2
      //   449: goto -> 456
      //   452: astore_1
      //   453: aload #7
      //   455: astore_2
      //   456: aload_2
      //   457: astore_3
      //   458: aload_2
      //   459: monitorexit
      //   460: aload_1
      //   461: athrow
      //   462: astore_1
      //   463: aload_3
      //   464: astore_2
      //   465: goto -> 456
      // Exception table:
      //   from	to	target	type
      //   24	75	452	finally
      //   75	89	452	finally
      //   98	105	452	finally
      //   110	130	131	finally
      //   138	147	452	finally
      //   152	172	131	finally
      //   173	180	452	finally
      //   185	196	131	finally
      //   202	212	452	finally
      //   217	246	131	finally
      //   251	311	452	finally
      //   311	325	445	finally
      //   328	354	462	finally
      //   365	370	462	finally
      //   370	378	389	finally
      //   381	386	462	finally
      //   393	398	462	finally
      //   401	403	462	finally
      //   406	423	462	finally
      //   431	438	462	finally
      //   441	444	462	finally
      //   458	460	462	finally
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (!CameraOfflineSessionImpl.this.isClosed())
        CameraOfflineSessionImpl.this.mOfflineCallback.onError(CameraOfflineSessionImpl.this, 0); 
    }
  }
  
  class null implements Runnable {
    public void run() {
      if (!CameraOfflineSessionImpl.this.isClosed())
        CameraOfflineSessionImpl.this.mOfflineCallback.onIdle(CameraOfflineSessionImpl.this); 
    }
  }
  
  class null implements Runnable {
    public void run() {
      CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
      if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null) {
        int i = resultExtras.getSubsequenceId();
        CaptureRequest captureRequest = holder.getRequest(i);
        if (holder.hasBatchedOutputs()) {
          Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
          for (byte b = 0; b < holder.getRequestCount(); b++) {
            CaptureRequest captureRequest1 = holder.getRequest(b);
            long l1 = timestamp;
            long l2 = (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue();
            long l3 = frameNumber;
            long l4 = (i - b);
            captureCallback.onCaptureStarted((CameraCaptureSession)CameraOfflineSessionImpl.this, captureRequest1, l1 - l2, l3 - l4);
          } 
        } else {
          captureCallback.onCaptureStarted((CameraCaptureSession)CameraOfflineSessionImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
        } 
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
      if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null)
        if (holder.hasBatchedOutputs()) {
          for (byte b = 0; b < holder.getRequestCount(); b++) {
            CaptureResult captureResult = new CaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras);
            CaptureRequest captureRequest = holder.getRequest(b);
            captureCallback.onCaptureProgressed((CameraCaptureSession)CameraOfflineSessionImpl.this, captureRequest, captureResult);
          } 
        } else {
          captureCallback.onCaptureProgressed((CameraCaptureSession)CameraOfflineSessionImpl.this, request, resultAsCapture);
        }  
    }
  }
  
  class null implements Runnable {
    public void run() {
      CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
      if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null)
        if (holder.hasBatchedOutputs()) {
          for (byte b = 0; b < holder.getRequestCount(); b++) {
            resultCopy.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(sensorTimestamp - (subsequenceId - b) * 1000000000L / ((Integer)fpsRange.getUpper()).intValue()));
            TotalCaptureResult totalCaptureResult = new TotalCaptureResult(new CameraMetadataNative(resultCopy), holder.getRequest(b), resultExtras, partialResults, holder.getSessionId(), new PhysicalCaptureResultInfo[0]);
            CaptureRequest captureRequest = holder.getRequest(b);
            captureCallback.onCaptureCompleted((CameraCaptureSession)CameraOfflineSessionImpl.this, captureRequest, totalCaptureResult);
          } 
        } else {
          captureCallback.onCaptureCompleted((CameraCaptureSession)CameraOfflineSessionImpl.this, request, resultAsCapture);
        }  
    }
  }
  
  class null implements Runnable {
    public void run() {
      CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
      if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null)
        captureCallback.onCaptureBufferLost((CameraCaptureSession)CameraOfflineSessionImpl.this, request, surface, frameNumber); 
    }
  }
  
  class null implements Runnable {
    public void run() {
      CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
      if (!CameraOfflineSessionImpl.this.isClosed() && captureCallback != null)
        captureCallback.onCaptureFailed((CameraCaptureSession)CameraOfflineSessionImpl.this, request, failure); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */