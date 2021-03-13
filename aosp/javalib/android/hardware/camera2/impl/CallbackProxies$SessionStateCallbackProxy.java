package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.os.Binder;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.concurrent.Executor;

public class SessionStateCallbackProxy extends CameraCaptureSession.StateCallback {
  private final CameraCaptureSession.StateCallback mCallback;
  
  private final Executor mExecutor;
  
  public SessionStateCallbackProxy(Executor paramExecutor, CameraCaptureSession.StateCallback paramStateCallback) {
    this.mExecutor = (Executor)Preconditions.checkNotNull(paramExecutor, "executor must not be null");
    this.mCallback = (CameraCaptureSession.StateCallback)Preconditions.checkNotNull(paramStateCallback, "callback must not be null");
  }
  
  public void onActive(CameraCaptureSession paramCameraCaptureSession) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$ISQyEhLUI1khcOCin3OIsRyTUoU _$$Lambda$CallbackProxies$SessionStateCallbackProxy$ISQyEhLUI1khcOCin3OIsRyTUoU = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$ISQyEhLUI1khcOCin3OIsRyTUoU();
      this(this, paramCameraCaptureSession);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$ISQyEhLUI1khcOCin3OIsRyTUoU);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public void onCaptureQueueEmpty(CameraCaptureSession paramCameraCaptureSession) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$hoQOYc189Bss2NBtrutabMRw4VU _$$Lambda$CallbackProxies$SessionStateCallbackProxy$hoQOYc189Bss2NBtrutabMRw4VU = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$hoQOYc189Bss2NBtrutabMRw4VU();
      this(this, paramCameraCaptureSession);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$hoQOYc189Bss2NBtrutabMRw4VU);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public void onClosed(CameraCaptureSession paramCameraCaptureSession) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$9H0ZdANdMrdpoq2bfIL2l3DVsKk _$$Lambda$CallbackProxies$SessionStateCallbackProxy$9H0ZdANdMrdpoq2bfIL2l3DVsKk = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$9H0ZdANdMrdpoq2bfIL2l3DVsKk();
      this(this, paramCameraCaptureSession);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$9H0ZdANdMrdpoq2bfIL2l3DVsKk);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public void onConfigureFailed(CameraCaptureSession paramCameraCaptureSession) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$gvbTsp9UPpKJAbdycdci_ZW5BeI _$$Lambda$CallbackProxies$SessionStateCallbackProxy$gvbTsp9UPpKJAbdycdci_ZW5BeI = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$gvbTsp9UPpKJAbdycdci_ZW5BeI();
      this(this, paramCameraCaptureSession);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$gvbTsp9UPpKJAbdycdci_ZW5BeI);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public void onConfigured(CameraCaptureSession paramCameraCaptureSession) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$soW0qC12Osypoky6AfL3P2_TeDw _$$Lambda$CallbackProxies$SessionStateCallbackProxy$soW0qC12Osypoky6AfL3P2_TeDw = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$soW0qC12Osypoky6AfL3P2_TeDw();
      this(this, paramCameraCaptureSession);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$soW0qC12Osypoky6AfL3P2_TeDw);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public void onReady(CameraCaptureSession paramCameraCaptureSession) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$Hoz_iT1tD_pl7sCGu4flyo_xB90 _$$Lambda$CallbackProxies$SessionStateCallbackProxy$Hoz_iT1tD_pl7sCGu4flyo_xB90 = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$Hoz_iT1tD_pl7sCGu4flyo_xB90();
      this(this, paramCameraCaptureSession);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$Hoz_iT1tD_pl7sCGu4flyo_xB90);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  public void onSurfacePrepared(CameraCaptureSession paramCameraCaptureSession, Surface paramSurface) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$CallbackProxies$SessionStateCallbackProxy$tuajQwbKz3BV5CZZJdjl97HF6Tw _$$Lambda$CallbackProxies$SessionStateCallbackProxy$tuajQwbKz3BV5CZZJdjl97HF6Tw = new _$$Lambda$CallbackProxies$SessionStateCallbackProxy$tuajQwbKz3BV5CZZJdjl97HF6Tw();
      this(this, paramCameraCaptureSession, paramSurface);
      executor.execute(_$$Lambda$CallbackProxies$SessionStateCallbackProxy$tuajQwbKz3BV5CZZJdjl97HF6Tw);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CallbackProxies$SessionStateCallbackProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */