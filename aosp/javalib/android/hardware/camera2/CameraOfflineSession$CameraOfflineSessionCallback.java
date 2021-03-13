package android.hardware.camera2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class CameraOfflineSessionCallback {
  public static final int STATUS_INTERNAL_ERROR = 0;
  
  public abstract void onClosed(CameraOfflineSession paramCameraOfflineSession);
  
  public abstract void onError(CameraOfflineSession paramCameraOfflineSession, int paramInt);
  
  public abstract void onIdle(CameraOfflineSession paramCameraOfflineSession);
  
  public abstract void onReady(CameraOfflineSession paramCameraOfflineSession);
  
  public abstract void onSwitchFailed(CameraOfflineSession paramCameraOfflineSession);
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StatusCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraOfflineSession$CameraOfflineSessionCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */