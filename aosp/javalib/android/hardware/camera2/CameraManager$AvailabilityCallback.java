package android.hardware.camera2;

public abstract class AvailabilityCallback {
  public void onCameraAccessPrioritiesChanged() {}
  
  public void onCameraAvailable(String paramString) {}
  
  public void onCameraClosed(String paramString) {}
  
  public void onCameraOpened(String paramString1, String paramString2) {}
  
  public void onCameraUnavailable(String paramString) {}
  
  public void onPhysicalCameraAvailable(String paramString1, String paramString2) {}
  
  public void onPhysicalCameraUnavailable(String paramString1, String paramString2) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraManager$AvailabilityCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */