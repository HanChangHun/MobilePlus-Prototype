package android.hardware.camera2.impl;

class null implements Runnable {
  public void run() {
    if (!CameraDeviceImpl.access$400(CameraDeviceImpl.this))
      holder.getCallback().onCaptureSequenceCompleted(CameraDeviceImpl.this, requestId, requestLastFrameNumbers.getLastFrameNumber()); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */