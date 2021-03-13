package android.hardware.camera2.impl;

import android.hardware.camera2.utils.TaskDrainer;

class AbortDrainListener implements TaskDrainer.DrainListener {
  private AbortDrainListener() {}
  
  public void onDrained() {
    synchronized ((CameraCaptureSessionImpl.access$1000(CameraCaptureSessionImpl.this)).mInterfaceLock) {
      if (CameraCaptureSessionImpl.access$900(CameraCaptureSessionImpl.this))
        return; 
      CameraCaptureSessionImpl.access$500(CameraCaptureSessionImpl.this).beginDrain();
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionImpl$AbortDrainListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */