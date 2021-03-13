package android.hardware.camera2.impl;

class null implements Runnable {
  public void run() {
    if (isError) {
      CameraDeviceImpl.access$200(CameraDeviceImpl.this).onError(CameraDeviceImpl.this, code);
    } else {
      CameraDeviceImpl.access$200(CameraDeviceImpl.this).onDisconnected(CameraDeviceImpl.this);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */