package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.utils.TaskDrainer;
import android.util.Log;

class IdleDrainListener implements TaskDrainer.DrainListener {
  private IdleDrainListener() {}
  
  public void onDrained() {
    synchronized ((CameraCaptureSessionImpl.access$1000(CameraCaptureSessionImpl.this)).mInterfaceLock) {
      if (CameraCaptureSessionImpl.access$900(CameraCaptureSessionImpl.this))
        return; 
      try {
        CameraCaptureSessionImpl.access$1000(CameraCaptureSessionImpl.this).configureStreamsChecked(null, null, 0, null);
      } catch (CameraAccessException cameraAccessException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(CameraCaptureSessionImpl.access$400(CameraCaptureSessionImpl.this));
        stringBuilder.append("Exception while unconfiguring outputs: ");
        Log.e("CameraCaptureSession", stringBuilder.toString(), (Throwable)cameraAccessException);
      } catch (IllegalStateException illegalStateException) {}
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraCaptureSessionImpl$IdleDrainListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */