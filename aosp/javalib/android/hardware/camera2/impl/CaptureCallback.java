package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import java.util.concurrent.Executor;

public abstract class CaptureCallback {
  private CameraCaptureSession.CaptureCallback mCallback;
  
  private Executor mExecutor;
  
  public CaptureCallback(Executor paramExecutor, CameraCaptureSession.CaptureCallback paramCaptureCallback) {
    this.mExecutor = paramExecutor;
    this.mCallback = paramCaptureCallback;
  }
  
  public Executor getExecutor() {
    return this.mExecutor;
  }
  
  public CameraCaptureSession.CaptureCallback getSessionCallback() {
    return this.mCallback;
  }
  
  public abstract void onCaptureBufferLost(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, Surface paramSurface, long paramLong);
  
  public abstract void onCaptureCompleted(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, TotalCaptureResult paramTotalCaptureResult);
  
  public abstract void onCaptureFailed(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, CaptureFailure paramCaptureFailure);
  
  public abstract void onCapturePartial(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, CaptureResult paramCaptureResult);
  
  public abstract void onCaptureProgressed(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, CaptureResult paramCaptureResult);
  
  public abstract void onCaptureSequenceAborted(CameraDevice paramCameraDevice, int paramInt);
  
  public abstract void onCaptureSequenceCompleted(CameraDevice paramCameraDevice, int paramInt, long paramLong);
  
  public abstract void onCaptureStarted(CameraDevice paramCameraDevice, CaptureRequest paramCaptureRequest, long paramLong1, long paramLong2);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CaptureCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */