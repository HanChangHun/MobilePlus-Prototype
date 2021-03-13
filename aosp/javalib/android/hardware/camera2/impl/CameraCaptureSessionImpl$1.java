package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Binder;
import android.view.Surface;
import java.util.concurrent.Executor;

class null extends CaptureCallback {
  null(Executor paramExecutor1, CameraCaptureSession.CaptureCallback paramCaptureCallback1) {
    super(paramExecutor1, paramCaptureCallback1);
  }
  
  public void onCaptureBufferLost(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, Surface paramSurface, long paramLong) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE _$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE = new _$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE();
        this(this, captureCallback, paramCaptureRequest, paramSurface, paramLong);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$VuYVXvwmJMkbTnKaOD_h_DOjJpE);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void onCaptureCompleted(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, TotalCaptureResult paramTotalCaptureResult) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4 _$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4 = new _$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4();
        this(this, captureCallback, paramCaptureRequest, paramTotalCaptureResult);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$OA1Yz_YgzMO8qcV8esRjyt7ykp4);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void onCaptureFailed(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, CaptureFailure paramCaptureFailure) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s _$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s = new _$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s();
        this(this, captureCallback, paramCaptureRequest, paramCaptureFailure);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$VsKq1alEqL3XH_hLTWXgi7fSF3s);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void onCapturePartial(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, CaptureResult paramCaptureResult) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8 _$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8 = new _$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8();
        this(this, captureCallback, paramCaptureRequest, paramCaptureResult);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$HRzGZkXU2X5JDcudK0jcqdLZzV8);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void onCaptureProgressed(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, CaptureResult paramCaptureResult) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0 _$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0 = new _$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0();
        this(this, captureCallback, paramCaptureRequest, paramCaptureResult);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$7mSdNTTAoYA0D3ITDxzDJKGykz0);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void onCaptureSequenceAborted(CameraDevice paramCameraDevice, int paramInt) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic _$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic = new _$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic();
        this(this, captureCallback, paramInt);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$TIJELOXvjSbPh6mpBLfBJ5ciNic);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
    CameraCaptureSessionImpl.access$300(CameraCaptureSessionImpl.this, paramInt);
  }
  
  public void onCaptureSequenceCompleted(CameraDevice paramCameraDevice, int paramInt, long paramLong) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck _$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck = new _$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck();
        this(this, captureCallback, paramInt, paramLong);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$KZ4tthx5TnA5BizPVljsPqqdHck);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
    CameraCaptureSessionImpl.access$300(CameraCaptureSessionImpl.this, paramInt);
  }
  
  public void onCaptureStarted(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, long paramLong1, long paramLong2) {
    if (callback != null && executor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = executor;
        CameraCaptureSession.CaptureCallback captureCallback = callback;
        _$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA _$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA = new _$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA();
        this(this, captureCallback, paramCaptureRequest, paramLong1, paramLong2);
        executor.execute(_$$Lambda$CameraCaptureSessionImpl$1$uPVvNnGFdZcxxscdYQ5erNgaRWA);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionImpl$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */