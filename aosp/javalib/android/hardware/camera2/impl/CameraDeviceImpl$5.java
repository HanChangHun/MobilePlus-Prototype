package android.hardware.camera2.impl;

class null implements Runnable {
  private boolean mClosedOnce = false;
  
  public void run() {
    if (!this.mClosedOnce)
      synchronized (CameraDeviceImpl.this.mInterfaceLock) {
        CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.access$100(CameraDeviceImpl.this);
        if (stateCallbackKK != null)
          stateCallbackKK.onClosed(CameraDeviceImpl.this); 
        CameraDeviceImpl.access$200(CameraDeviceImpl.this).onClosed(CameraDeviceImpl.this);
        this.mClosedOnce = true;
        return;
      }  
    throw new AssertionError("Don't post #onClosed more than once");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */