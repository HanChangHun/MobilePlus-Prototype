package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraOfflineSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.utils.TaskDrainer;
import android.hardware.camera2.utils.TaskSingleDrainer;
import android.os.Binder;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class CameraCaptureSessionImpl extends CameraCaptureSession implements CameraCaptureSessionCore {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "CameraCaptureSession";
  
  private final TaskSingleDrainer mAbortDrainer;
  
  private volatile boolean mAborting;
  
  private boolean mClosed = false;
  
  private final boolean mConfigureSuccess;
  
  private final Executor mDeviceExecutor;
  
  private final CameraDeviceImpl mDeviceImpl;
  
  private final int mId;
  
  private final String mIdString;
  
  private final TaskSingleDrainer mIdleDrainer;
  
  private final Surface mInput;
  
  private final TaskDrainer<Integer> mSequenceDrainer;
  
  private boolean mSkipUnconfigure = false;
  
  private final CameraCaptureSession.StateCallback mStateCallback;
  
  private final Executor mStateExecutor;
  
  CameraCaptureSessionImpl(int paramInt, Surface paramSurface, CameraCaptureSession.StateCallback paramStateCallback, Executor paramExecutor1, CameraDeviceImpl paramCameraDeviceImpl, Executor paramExecutor2, boolean paramBoolean) {
    if (paramStateCallback != null) {
      this.mId = paramInt;
      this.mIdString = String.format("Session %d: ", new Object[] { Integer.valueOf(paramInt) });
      this.mInput = paramSurface;
      Executor executor = (Executor)Preconditions.checkNotNull(paramExecutor1, "stateExecutor must not be null");
      this.mStateExecutor = executor;
      this.mStateCallback = createUserStateCallbackProxy(executor, paramStateCallback);
      this.mDeviceExecutor = (Executor)Preconditions.checkNotNull(paramExecutor2, "deviceStateExecutor must not be null");
      this.mDeviceImpl = (CameraDeviceImpl)Preconditions.checkNotNull(paramCameraDeviceImpl, "deviceImpl must not be null");
      this.mSequenceDrainer = new TaskDrainer(this.mDeviceExecutor, new SequenceDrainListener(), "seq");
      this.mIdleDrainer = new TaskSingleDrainer(this.mDeviceExecutor, new IdleDrainListener(), "idle");
      this.mAbortDrainer = new TaskSingleDrainer(this.mDeviceExecutor, new AbortDrainListener(), "abort");
      if (paramBoolean) {
        this.mStateCallback.onConfigured(this);
        this.mConfigureSuccess = true;
      } else {
        this.mStateCallback.onConfigureFailed(this);
        this.mClosed = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mIdString);
        stringBuilder.append("Failed to create capture session; configuration failed");
        Log.e("CameraCaptureSession", stringBuilder.toString());
        this.mConfigureSuccess = false;
      } 
      return;
    } 
    throw new IllegalArgumentException("callback must not be null");
  }
  
  private int addPendingSequence(int paramInt) {
    this.mSequenceDrainer.taskStarted(Integer.valueOf(paramInt));
    return paramInt;
  }
  
  private void checkCaptureRequest(CaptureRequest paramCaptureRequest) {
    if (paramCaptureRequest != null) {
      if (!paramCaptureRequest.isReprocess() || isReprocessable()) {
        if (!paramCaptureRequest.isReprocess() || paramCaptureRequest.getReprocessableSessionId() == this.mId)
          return; 
        throw new IllegalArgumentException("capture request was created for another session");
      } 
      throw new IllegalArgumentException("this capture session cannot handle reprocess requests");
    } 
    throw new IllegalArgumentException("request must not be null");
  }
  
  private void checkCaptureRequests(List<CaptureRequest> paramList) {
    if (paramList != null) {
      if (!paramList.isEmpty()) {
        for (CaptureRequest captureRequest : paramList) {
          if (captureRequest.isReprocess()) {
            if (isReprocessable()) {
              if (captureRequest.getReprocessableSessionId() == this.mId)
                continue; 
              throw new IllegalArgumentException("Capture request was created for another session");
            } 
            throw new IllegalArgumentException("This capture session cannot handle reprocess requests");
          } 
        } 
        return;
      } 
      throw new IllegalArgumentException("Requests must have at least one element");
    } 
    throw new IllegalArgumentException("Requests must not be null");
  }
  
  private void checkNotClosed() {
    if (!this.mClosed)
      return; 
    throw new IllegalStateException("Session has been closed; further changes are illegal.");
  }
  
  private void checkRepeatingRequest(CaptureRequest paramCaptureRequest) {
    if (paramCaptureRequest != null) {
      if (!paramCaptureRequest.isReprocess())
        return; 
      throw new IllegalArgumentException("repeating reprocess requests are not supported");
    } 
    throw new IllegalArgumentException("request must not be null");
  }
  
  private void checkRepeatingRequests(List<CaptureRequest> paramList) {
    if (paramList != null) {
      if (!paramList.isEmpty()) {
        Iterator<CaptureRequest> iterator = paramList.iterator();
        while (iterator.hasNext()) {
          if (!((CaptureRequest)iterator.next()).isReprocess())
            continue; 
          throw new IllegalArgumentException("repeating reprocess burst requests are not supported");
        } 
        return;
      } 
      throw new IllegalArgumentException("requests must have at least one element");
    } 
    throw new IllegalArgumentException("requests must not be null");
  }
  
  private CaptureCallback createCaptureCallbackProxy(Handler paramHandler, CameraCaptureSession.CaptureCallback paramCaptureCallback) {
    if (paramCaptureCallback != null) {
      Executor executor = CameraDeviceImpl.checkAndWrapHandler(paramHandler);
    } else {
      paramHandler = null;
    } 
    return createCaptureCallbackProxyWithExecutor((Executor)paramHandler, paramCaptureCallback);
  }
  
  private CaptureCallback createCaptureCallbackProxyWithExecutor(final Executor executor, final CameraCaptureSession.CaptureCallback callback) {
    return new CaptureCallback(executor, callback) {
        public void onCaptureBufferLost(CameraDevice param1CameraDevice, CaptureRequest param1CaptureRequest, Surface param1Surface, long param1Long) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE _$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE = new _$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE();
              this(this, captureCallback, param1CaptureRequest, param1Surface, param1Long);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
        }
        
        public void onCaptureCompleted(CameraDevice param1CameraDevice, CaptureRequest param1CaptureRequest, TotalCaptureResult param1TotalCaptureResult) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4 _$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4 = new _$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4();
              this(this, captureCallback, param1CaptureRequest, param1TotalCaptureResult);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
        }
        
        public void onCaptureFailed(CameraDevice param1CameraDevice, CaptureRequest param1CaptureRequest, CaptureFailure param1CaptureFailure) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s _$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s = new _$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s();
              this(this, captureCallback, param1CaptureRequest, param1CaptureFailure);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
        }
        
        public void onCapturePartial(CameraDevice param1CameraDevice, CaptureRequest param1CaptureRequest, CaptureResult param1CaptureResult) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8 _$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8 = new _$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8();
              this(this, captureCallback, param1CaptureRequest, param1CaptureResult);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
        }
        
        public void onCaptureProgressed(CameraDevice param1CameraDevice, CaptureRequest param1CaptureRequest, CaptureResult param1CaptureResult) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0 _$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0 = new _$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0();
              this(this, captureCallback, param1CaptureRequest, param1CaptureResult);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
        }
        
        public void onCaptureSequenceAborted(CameraDevice param1CameraDevice, int param1Int) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic _$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic = new _$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic();
              this(this, captureCallback, param1Int);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
          CameraCaptureSessionImpl.this.finishPendingSequence(param1Int);
        }
        
        public void onCaptureSequenceCompleted(CameraDevice param1CameraDevice, int param1Int, long param1Long) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck _$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck = new _$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck();
              this(this, captureCallback, param1Int, param1Long);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
          CameraCaptureSessionImpl.this.finishPendingSequence(param1Int);
        }
        
        public void onCaptureStarted(CameraDevice param1CameraDevice, CaptureRequest param1CaptureRequest, long param1Long1, long param1Long2) {
          if (callback != null && executor != null) {
            long l = Binder.clearCallingIdentity();
            try {
              Executor executor = executor;
              CameraCaptureSession.CaptureCallback captureCallback = callback;
              _$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA _$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA = new _$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA();
              this(this, captureCallback, param1CaptureRequest, param1Long1, param1Long2);
              executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA);
            } finally {
              Binder.restoreCallingIdentity(l);
            } 
          } 
        }
      };
  }
  
  private CameraCaptureSession.StateCallback createUserStateCallbackProxy(Executor paramExecutor, CameraCaptureSession.StateCallback paramStateCallback) {
    return new CallbackProxies.SessionStateCallbackProxy(paramExecutor, paramStateCallback);
  }
  
  private void finishPendingSequence(int paramInt) {
    try {
      this.mSequenceDrainer.taskFinished(Integer.valueOf(paramInt));
    } catch (IllegalStateException illegalStateException) {
      Log.w("CameraCaptureSession", illegalStateException.getMessage());
    } 
  }
  
  public void abortCaptures() throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      if (this.mAborting) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(this.mIdString);
        stringBuilder.append("abortCaptures - Session is already aborting; doing nothing");
        Log.w("CameraCaptureSession", stringBuilder.toString());
        return;
      } 
      this.mAborting = true;
      this.mAbortDrainer.taskStarted();
      this.mDeviceImpl.flush();
      return;
    } 
  }
  
  public int capture(CaptureRequest paramCaptureRequest, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    checkCaptureRequest(paramCaptureRequest);
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      paramHandler = CameraDeviceImpl.checkHandler(paramHandler, paramCaptureCallback);
      return addPendingSequence(this.mDeviceImpl.capture(paramCaptureRequest, createCaptureCallbackProxy(paramHandler, paramCaptureCallback), this.mDeviceExecutor));
    } 
  }
  
  public int captureBurst(List<CaptureRequest> paramList, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    checkCaptureRequests(paramList);
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      paramHandler = CameraDeviceImpl.checkHandler(paramHandler, paramCaptureCallback);
      return addPendingSequence(this.mDeviceImpl.captureBurst(paramList, createCaptureCallbackProxy(paramHandler, paramCaptureCallback), this.mDeviceExecutor));
    } 
  }
  
  public int captureBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    if (paramExecutor != null) {
      if (paramCaptureCallback != null) {
        checkCaptureRequests(paramList);
        synchronized (this.mDeviceImpl.mInterfaceLock) {
          checkNotClosed();
          paramExecutor = CameraDeviceImpl.checkExecutor(paramExecutor, paramCaptureCallback);
          return addPendingSequence(this.mDeviceImpl.captureBurst(paramList, createCaptureCallbackProxyWithExecutor(paramExecutor, paramCaptureCallback), this.mDeviceExecutor));
        } 
      } 
      throw new IllegalArgumentException("callback must not be null");
    } 
    throw new IllegalArgumentException("executor must not be null");
  }
  
  public int captureSingleRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    if (paramExecutor != null) {
      if (paramCaptureCallback != null) {
        checkCaptureRequest(paramCaptureRequest);
        synchronized (this.mDeviceImpl.mInterfaceLock) {
          checkNotClosed();
          paramExecutor = CameraDeviceImpl.checkExecutor(paramExecutor, paramCaptureCallback);
          return addPendingSequence(this.mDeviceImpl.capture(paramCaptureRequest, createCaptureCallbackProxyWithExecutor(paramExecutor, paramCaptureCallback), this.mDeviceExecutor));
        } 
      } 
      throw new IllegalArgumentException("callback must not be null");
    } 
    throw new IllegalArgumentException("executor must not be null");
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mDeviceImpl : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   4: getfield mInterfaceLock : Ljava/lang/Object;
    //   7: astore_1
    //   8: aload_1
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mClosed : Z
    //   14: ifeq -> 20
    //   17: aload_1
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: iconst_1
    //   22: putfield mClosed : Z
    //   25: aload_0
    //   26: getfield mDeviceImpl : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   29: invokevirtual stopRepeating : ()V
    //   32: goto -> 72
    //   35: astore_2
    //   36: new java/lang/StringBuilder
    //   39: astore_3
    //   40: aload_3
    //   41: invokespecial <init> : ()V
    //   44: aload_3
    //   45: aload_0
    //   46: getfield mIdString : Ljava/lang/String;
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload_3
    //   54: ldc_w 'Exception while stopping repeating: '
    //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: ldc 'CameraCaptureSession'
    //   63: aload_3
    //   64: invokevirtual toString : ()Ljava/lang/String;
    //   67: aload_2
    //   68: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   71: pop
    //   72: aload_0
    //   73: getfield mSequenceDrainer : Landroid/hardware/camera2/utils/TaskDrainer;
    //   76: invokevirtual beginDrain : ()V
    //   79: aload_1
    //   80: monitorexit
    //   81: aload_0
    //   82: getfield mInput : Landroid/view/Surface;
    //   85: astore_1
    //   86: aload_1
    //   87: ifnull -> 94
    //   90: aload_1
    //   91: invokevirtual release : ()V
    //   94: return
    //   95: astore_2
    //   96: aload_0
    //   97: getfield mStateCallback : Landroid/hardware/camera2/CameraCaptureSession$StateCallback;
    //   100: aload_0
    //   101: invokevirtual onClosed : (Landroid/hardware/camera2/CameraCaptureSession;)V
    //   104: aload_1
    //   105: monitorexit
    //   106: return
    //   107: astore_2
    //   108: aload_1
    //   109: monitorexit
    //   110: aload_2
    //   111: athrow
    // Exception table:
    //   from	to	target	type
    //   10	19	107	finally
    //   20	25	107	finally
    //   25	32	95	java/lang/IllegalStateException
    //   25	32	35	android/hardware/camera2/CameraAccessException
    //   25	32	107	finally
    //   36	72	107	finally
    //   72	81	107	finally
    //   96	106	107	finally
    //   108	110	107	finally
  }
  
  public void closeWithoutDraining() {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      if (this.mClosed)
        return; 
      this.mClosed = true;
      this.mStateCallback.onClosed(this);
      Surface surface = this.mInput;
      if (surface != null)
        surface.release(); 
      return;
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public void finalizeOutputConfigurations(List<OutputConfiguration> paramList) throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      this.mDeviceImpl.finalizeOutputConfigs(paramList);
      return;
    } 
  }
  
  public CameraDevice getDevice() {
    return this.mDeviceImpl;
  }
  
  public CameraDeviceImpl.StateCallbackKK getDeviceStateCallback() {
    return new CameraDeviceImpl.StateCallbackKK() {
        private boolean mActive = false;
        
        private boolean mBusy = false;
        
        public void onActive(CameraDevice param1CameraDevice) {
          CameraCaptureSessionImpl.this.mIdleDrainer.taskStarted();
          this.mActive = true;
          CameraCaptureSessionImpl.this.mStateCallback.onActive(session);
        }
        
        public void onBusy(CameraDevice param1CameraDevice) {
          this.mBusy = true;
        }
        
        public void onDisconnected(CameraDevice param1CameraDevice) {
          CameraCaptureSessionImpl.this.close();
        }
        
        public void onError(CameraDevice param1CameraDevice, int param1Int) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(CameraCaptureSessionImpl.this.mIdString);
          stringBuilder.append("Got device error ");
          stringBuilder.append(param1Int);
          Log.wtf("CameraCaptureSession", stringBuilder.toString());
        }
        
        public void onIdle(CameraDevice param1CameraDevice) {
          synchronized (interfaceLock) {
            boolean bool = CameraCaptureSessionImpl.this.mAborting;
            if (this.mBusy && bool) {
              CameraCaptureSessionImpl.this.mAbortDrainer.taskFinished();
              synchronized (interfaceLock) {
                CameraCaptureSessionImpl.access$702(CameraCaptureSessionImpl.this, false);
              } 
            } 
            if (this.mActive)
              CameraCaptureSessionImpl.this.mIdleDrainer.taskFinished(); 
            this.mBusy = false;
            this.mActive = false;
            CameraCaptureSessionImpl.this.mStateCallback.onReady(session);
            return;
          } 
        }
        
        public void onOpened(CameraDevice param1CameraDevice) {
          throw new AssertionError("Camera must already be open before creating a session");
        }
        
        public void onRequestQueueEmpty() {
          CameraCaptureSessionImpl.this.mStateCallback.onCaptureQueueEmpty(session);
        }
        
        public void onSurfacePrepared(Surface param1Surface) {
          CameraCaptureSessionImpl.this.mStateCallback.onSurfacePrepared(session, param1Surface);
        }
        
        public void onUnconfigured(CameraDevice param1CameraDevice) {}
      };
  }
  
  public Surface getInputSurface() {
    return this.mInput;
  }
  
  public boolean isAborting() {
    return this.mAborting;
  }
  
  public boolean isReprocessable() {
    boolean bool;
    if (this.mInput != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void prepare(int paramInt, Surface paramSurface) throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      this.mDeviceImpl.prepare(paramInt, paramSurface);
      return;
    } 
  }
  
  public void prepare(Surface paramSurface) throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      this.mDeviceImpl.prepare(paramSurface);
      return;
    } 
  }
  
  public void replaceSessionClose() {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      this.mSkipUnconfigure = true;
      close();
      return;
    } 
  }
  
  public int setRepeatingBurst(List<CaptureRequest> paramList, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    checkRepeatingRequests(paramList);
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      paramHandler = CameraDeviceImpl.checkHandler(paramHandler, paramCaptureCallback);
      return addPendingSequence(this.mDeviceImpl.setRepeatingBurst(paramList, createCaptureCallbackProxy(paramHandler, paramCaptureCallback), this.mDeviceExecutor));
    } 
  }
  
  public int setRepeatingBurstRequests(List<CaptureRequest> paramList, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    if (paramExecutor != null) {
      if (paramCaptureCallback != null) {
        checkRepeatingRequests(paramList);
        synchronized (this.mDeviceImpl.mInterfaceLock) {
          checkNotClosed();
          paramExecutor = CameraDeviceImpl.checkExecutor(paramExecutor, paramCaptureCallback);
          return addPendingSequence(this.mDeviceImpl.setRepeatingBurst(paramList, createCaptureCallbackProxyWithExecutor(paramExecutor, paramCaptureCallback), this.mDeviceExecutor));
        } 
      } 
      throw new IllegalArgumentException("callback must not be null");
    } 
    throw new IllegalArgumentException("executor must not be null");
  }
  
  public int setRepeatingRequest(CaptureRequest paramCaptureRequest, CameraCaptureSession.CaptureCallback paramCaptureCallback, Handler paramHandler) throws CameraAccessException {
    checkRepeatingRequest(paramCaptureRequest);
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      paramHandler = CameraDeviceImpl.checkHandler(paramHandler, paramCaptureCallback);
      return addPendingSequence(this.mDeviceImpl.setRepeatingRequest(paramCaptureRequest, createCaptureCallbackProxy(paramHandler, paramCaptureCallback), this.mDeviceExecutor));
    } 
  }
  
  public int setSingleRepeatingRequest(CaptureRequest paramCaptureRequest, Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) throws CameraAccessException {
    if (paramExecutor != null) {
      if (paramCaptureCallback != null) {
        checkRepeatingRequest(paramCaptureRequest);
        synchronized (this.mDeviceImpl.mInterfaceLock) {
          checkNotClosed();
          paramExecutor = CameraDeviceImpl.checkExecutor(paramExecutor, paramCaptureCallback);
          return addPendingSequence(this.mDeviceImpl.setRepeatingRequest(paramCaptureRequest, createCaptureCallbackProxyWithExecutor(paramExecutor, paramCaptureCallback), this.mDeviceExecutor));
        } 
      } 
      throw new IllegalArgumentException("callback must not be null");
    } 
    throw new IllegalArgumentException("executor must not be null");
  }
  
  public void stopRepeating() throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      this.mDeviceImpl.stopRepeating();
      return;
    } 
  }
  
  public boolean supportsOfflineProcessing(Surface paramSurface) {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      return this.mDeviceImpl.supportsOfflineProcessing(paramSurface);
    } 
  }
  
  public CameraOfflineSession switchToOffline(Collection<Surface> paramCollection, Executor paramExecutor, CameraOfflineSession.CameraOfflineSessionCallback paramCameraOfflineSessionCallback) throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      return this.mDeviceImpl.switchToOffline(paramCollection, paramExecutor, paramCameraOfflineSessionCallback);
    } 
  }
  
  public void tearDown(Surface paramSurface) throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      this.mDeviceImpl.tearDown(paramSurface);
      return;
    } 
  }
  
  public void updateOutputConfiguration(OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    synchronized (this.mDeviceImpl.mInterfaceLock) {
      checkNotClosed();
      this.mDeviceImpl.updateOutputConfiguration(paramOutputConfiguration);
      return;
    } 
  }
  
  private class AbortDrainListener implements TaskDrainer.DrainListener {
    private AbortDrainListener() {}
    
    public void onDrained() {
      synchronized (CameraCaptureSessionImpl.this.mDeviceImpl.mInterfaceLock) {
        if (CameraCaptureSessionImpl.this.mSkipUnconfigure)
          return; 
        CameraCaptureSessionImpl.this.mIdleDrainer.beginDrain();
        return;
      } 
    }
  }
  
  private class IdleDrainListener implements TaskDrainer.DrainListener {
    private IdleDrainListener() {}
    
    public void onDrained() {
      synchronized (CameraCaptureSessionImpl.this.mDeviceImpl.mInterfaceLock) {
        if (CameraCaptureSessionImpl.this.mSkipUnconfigure)
          return; 
        try {
          CameraCaptureSessionImpl.this.mDeviceImpl.configureStreamsChecked(null, null, 0, null);
        } catch (CameraAccessException cameraAccessException) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(CameraCaptureSessionImpl.this.mIdString);
          stringBuilder.append("Exception while unconfiguring outputs: ");
          Log.e("CameraCaptureSession", stringBuilder.toString(), (Throwable)cameraAccessException);
        } catch (IllegalStateException illegalStateException) {}
        return;
      } 
    }
  }
  
  private class SequenceDrainListener implements TaskDrainer.DrainListener {
    private SequenceDrainListener() {}
    
    public void onDrained() {
      CameraCaptureSessionImpl.this.mStateCallback.onClosed(CameraCaptureSessionImpl.this);
      if (CameraCaptureSessionImpl.this.mSkipUnconfigure)
        return; 
      CameraCaptureSessionImpl.this.mAbortDrainer.beginDrain();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */