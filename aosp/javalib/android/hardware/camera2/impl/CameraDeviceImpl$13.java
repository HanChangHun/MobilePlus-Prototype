package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;

class null implements Runnable {
  public void run() {
    if (!CameraDeviceImpl.access$400(CameraDeviceImpl.this))
      holder.getCallback().onCaptureFailed(CameraDeviceImpl.this, request, failure); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$13.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */