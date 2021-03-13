package android.hardware.camera2;

import android.util.AndroidException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CameraAccessException extends AndroidException {
  public static final int CAMERA_DEPRECATED_HAL = 1000;
  
  public static final int CAMERA_DISABLED = 1;
  
  public static final int CAMERA_DISCONNECTED = 2;
  
  public static final int CAMERA_ERROR = 3;
  
  public static final int CAMERA_IN_USE = 4;
  
  public static final int MAX_CAMERAS_IN_USE = 5;
  
  private static final long serialVersionUID = 5630338637471475675L;
  
  private final int mReason;
  
  public CameraAccessException(int paramInt) {
    super(getDefaultMessage(paramInt));
    this.mReason = paramInt;
  }
  
  public CameraAccessException(int paramInt, String paramString) {
    super(getCombinedMessage(paramInt, paramString));
    this.mReason = paramInt;
  }
  
  public CameraAccessException(int paramInt, String paramString, Throwable paramThrowable) {
    super(getCombinedMessage(paramInt, paramString), paramThrowable);
    this.mReason = paramInt;
  }
  
  public CameraAccessException(int paramInt, Throwable paramThrowable) {
    super(getDefaultMessage(paramInt), paramThrowable);
    this.mReason = paramInt;
  }
  
  private static String getCombinedMessage(int paramInt, String paramString) {
    return String.format("%s (%d): %s", new Object[] { getProblemString(paramInt), Integer.valueOf(paramInt), paramString });
  }
  
  public static String getDefaultMessage(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "The system-wide limit for number of open cameras has been reached, and more camera devices cannot be opened until previous instances are closed.") : "The camera device is in use already") : "The camera device is currently in the error state; no further calls to it will succeed.") : "The camera device is removable and has been disconnected from the Android device, or the camera service has shut down the connection due to a higher-priority access request for the camera device.") : "The camera is disabled due to a device policy, and cannot be opened.";
  }
  
  private static String getProblemString(int paramInt) {
    String str;
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt != 4) {
            if (paramInt != 5) {
              if (paramInt != 1000) {
                str = "<UNKNOWN ERROR>";
              } else {
                str = "CAMERA_DEPRECATED_HAL";
              } 
            } else {
              str = "MAX_CAMERAS_IN_USE";
            } 
          } else {
            str = "CAMERA_IN_USE";
          } 
        } else {
          str = "CAMERA_ERROR";
        } 
      } else {
        str = "CAMERA_DISCONNECTED";
      } 
    } else {
      str = "CAMERA_DISABLED";
    } 
    return str;
  }
  
  public final int getReason() {
    return this.mReason;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AccessError {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraAccessException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */