package android.hardware.camera2;

class null implements Runnable {
  public void run() {
    String str = physicalId;
    if (str == null) {
      callback.onCameraUnavailable(id);
    } else {
      callback.onPhysicalCameraUnavailable(id, str);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraManager$CameraManagerGlobal$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */