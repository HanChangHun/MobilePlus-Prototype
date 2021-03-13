package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.util.Log;
import android.view.Surface;

class null extends CameraDeviceImpl.StateCallbackKK {
  private boolean mActive = false;
  
  private boolean mBusy = false;
  
  public void onActive(CameraDevice paramCameraDevice) {
    CameraCaptureSessionImpl.access$500(CameraCaptureSessionImpl.this).taskStarted();
    this.mActive = true;
    CameraCaptureSessionImpl.access$600(CameraCaptureSessionImpl.this).onActive(session);
  }
  
  public void onBusy(CameraDevice paramCameraDevice) {
    this.mBusy = true;
  }
  
  public void onDisconnected(CameraDevice paramCameraDevice) {
    CameraCaptureSessionImpl.this.close();
  }
  
  public void onError(CameraDevice paramCameraDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(CameraCaptureSessionImpl.access$400(CameraCaptureSessionImpl.this));
    stringBuilder.append("Got device error ");
    stringBuilder.append(paramInt);
    Log.wtf("CameraCaptureSession", stringBuilder.toString());
  }
  
  public void onIdle(CameraDevice paramCameraDevice) {
    synchronized (interfaceLock) {
      boolean bool = CameraCaptureSessionImpl.access$700(CameraCaptureSessionImpl.this);
      if (this.mBusy && bool) {
        CameraCaptureSessionImpl.access$800(CameraCaptureSessionImpl.this).taskFinished();
        synchronized (interfaceLock) {
          CameraCaptureSessionImpl.access$702(CameraCaptureSessionImpl.this, false);
        } 
      } 
      if (this.mActive)
        CameraCaptureSessionImpl.access$500(CameraCaptureSessionImpl.this).taskFinished(); 
      this.mBusy = false;
      this.mActive = false;
      CameraCaptureSessionImpl.access$600(CameraCaptureSessionImpl.this).onReady(session);
      return;
    } 
  }
  
  public void onOpened(CameraDevice paramCameraDevice) {
    throw new AssertionError("Camera must already be open before creating a session");
  }
  
  public void onRequestQueueEmpty() {
    CameraCaptureSessionImpl.access$600(CameraCaptureSessionImpl.this).onCaptureQueueEmpty(session);
  }
  
  public void onSurfacePrepared(Surface paramSurface) {
    CameraCaptureSessionImpl.access$600(CameraCaptureSessionImpl.this).onSurfacePrepared(session, paramSurface);
  }
  
  public void onUnconfigured(CameraDevice paramCameraDevice) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionImpl$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */