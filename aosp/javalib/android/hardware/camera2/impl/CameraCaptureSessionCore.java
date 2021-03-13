package android.hardware.camera2.impl;

public interface CameraCaptureSessionCore {
  void closeWithoutDraining();
  
  CameraDeviceImpl.StateCallbackKK getDeviceStateCallback();
  
  boolean isAborting();
  
  void replaceSessionClose();
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */