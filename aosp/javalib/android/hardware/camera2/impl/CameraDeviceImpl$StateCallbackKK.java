package android.hardware.camera2.impl;

import android.hardware.camera2.CameraDevice;
import android.view.Surface;

public abstract class StateCallbackKK extends CameraDevice.StateCallback {
  public void onActive(CameraDevice paramCameraDevice) {}
  
  public void onBusy(CameraDevice paramCameraDevice) {}
  
  public void onIdle(CameraDevice paramCameraDevice) {}
  
  public void onRequestQueueEmpty() {}
  
  public void onSurfacePrepared(Surface paramSurface) {}
  
  public void onUnconfigured(CameraDevice paramCameraDevice) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$StateCallbackKK.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */