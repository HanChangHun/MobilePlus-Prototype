package android.hardware.camera2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class CameraOfflineSession extends CameraCaptureSession {
  public abstract void close();
  
  public static abstract class CameraOfflineSessionCallback {
    public static final int STATUS_INTERNAL_ERROR = 0;
    
    public abstract void onClosed(CameraOfflineSession param1CameraOfflineSession);
    
    public abstract void onError(CameraOfflineSession param1CameraOfflineSession, int param1Int);
    
    public abstract void onIdle(CameraOfflineSession param1CameraOfflineSession);
    
    public abstract void onReady(CameraOfflineSession param1CameraOfflineSession);
    
    public abstract void onSwitchFailed(CameraOfflineSession param1CameraOfflineSession);
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface StatusCode {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StatusCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraOfflineSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */