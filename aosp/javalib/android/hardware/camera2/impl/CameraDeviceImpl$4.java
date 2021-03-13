package android.hardware.camera2.impl;

class null implements Runnable {
  public void run() {
    synchronized (CameraDeviceImpl.this.mInterfaceLock) {
      if (CameraDeviceImpl.access$000(CameraDeviceImpl.this) == null)
        return; 
      CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.access$100(CameraDeviceImpl.this);
      if (stateCallbackKK != null)
        stateCallbackKK.onBusy(CameraDeviceImpl.this); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */