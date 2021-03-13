package android.hardware.camera2.impl;

import android.hardware.camera2.utils.TaskDrainer;

class SequenceDrainListener implements TaskDrainer.DrainListener {
  private SequenceDrainListener() {}
  
  public void onDrained() {
    CameraCaptureSessionImpl.access$600(CameraCaptureSessionImpl.this).onClosed(CameraCaptureSessionImpl.this);
    if (CameraCaptureSessionImpl.access$900(CameraCaptureSessionImpl.this))
      return; 
    CameraCaptureSessionImpl.access$800(CameraCaptureSessionImpl.this).beginDrain();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionImpl$SequenceDrainListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */