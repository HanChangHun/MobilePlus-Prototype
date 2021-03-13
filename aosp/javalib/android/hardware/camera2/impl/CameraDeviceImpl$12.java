package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.view.Surface;

class null implements Runnable {
  public void run() {
    if (!CameraDeviceImpl.access$400(CameraDeviceImpl.this))
      holder.getCallback().onCaptureBufferLost(CameraDeviceImpl.this, request, surface, frameNumber); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$12.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */