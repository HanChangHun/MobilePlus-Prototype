package android.hardware.camera2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class StateCallback {
  public static final int ERROR_CAMERA_DEVICE = 4;
  
  public static final int ERROR_CAMERA_DISABLED = 3;
  
  public static final int ERROR_CAMERA_IN_USE = 1;
  
  public static final int ERROR_CAMERA_SERVICE = 5;
  
  public static final int ERROR_MAX_CAMERAS_IN_USE = 2;
  
  public void onClosed(CameraDevice paramCameraDevice) {}
  
  public abstract void onDisconnected(CameraDevice paramCameraDevice);
  
  public abstract void onError(CameraDevice paramCameraDevice, int paramInt);
  
  public abstract void onOpened(CameraDevice paramCameraDevice);
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ErrorCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraDevice$StateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */