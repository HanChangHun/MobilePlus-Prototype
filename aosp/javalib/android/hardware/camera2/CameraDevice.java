package android.hardware.camera2;

import android.annotation.SystemApi;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Handler;
import android.view.Surface;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Set;

public abstract class CameraDevice implements AutoCloseable {
  public static final int AUDIO_RESTRICTION_NONE = 0;
  
  public static final int AUDIO_RESTRICTION_VIBRATION = 1;
  
  public static final int AUDIO_RESTRICTION_VIBRATION_SOUND = 3;
  
  @SystemApi
  public static final int SESSION_OPERATION_MODE_CONSTRAINED_HIGH_SPEED = 1;
  
  @SystemApi
  public static final int SESSION_OPERATION_MODE_NORMAL = 0;
  
  @SystemApi
  public static final int SESSION_OPERATION_MODE_VENDOR_START = 32768;
  
  public static final int TEMPLATE_MANUAL = 6;
  
  public static final int TEMPLATE_PREVIEW = 1;
  
  public static final int TEMPLATE_RECORD = 3;
  
  public static final int TEMPLATE_STILL_CAPTURE = 2;
  
  public static final int TEMPLATE_VIDEO_SNAPSHOT = 4;
  
  public static final int TEMPLATE_ZERO_SHUTTER_LAG = 5;
  
  public abstract void close();
  
  public abstract CaptureRequest.Builder createCaptureRequest(int paramInt) throws CameraAccessException;
  
  public CaptureRequest.Builder createCaptureRequest(int paramInt, Set<String> paramSet) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public void createCaptureSession(SessionConfiguration paramSessionConfiguration) throws CameraAccessException {
    throw new UnsupportedOperationException("No default implementation");
  }
  
  @Deprecated
  public abstract void createCaptureSession(List<Surface> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException;
  
  @Deprecated
  public abstract void createCaptureSessionByOutputConfigurations(List<OutputConfiguration> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException;
  
  @Deprecated
  public abstract void createConstrainedHighSpeedCaptureSession(List<Surface> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException;
  
  @SystemApi
  @Deprecated
  public abstract void createCustomCaptureSession(InputConfiguration paramInputConfiguration, List<OutputConfiguration> paramList, int paramInt, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException;
  
  public abstract CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult paramTotalCaptureResult) throws CameraAccessException;
  
  @Deprecated
  public abstract void createReprocessableCaptureSession(InputConfiguration paramInputConfiguration, List<Surface> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException;
  
  @Deprecated
  public abstract void createReprocessableCaptureSessionByConfigurations(InputConfiguration paramInputConfiguration, List<OutputConfiguration> paramList, CameraCaptureSession.StateCallback paramStateCallback, Handler paramHandler) throws CameraAccessException;
  
  public int getCameraAudioRestriction() throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public abstract String getId();
  
  public boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  public void setCameraAudioRestriction(int paramInt) throws CameraAccessException {
    throw new UnsupportedOperationException("Subclasses must override this method");
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CAMERA_AUDIO_RESTRICTION {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RequestTemplate {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SessionOperatingMode {}
  
  public static abstract class StateCallback {
    public static final int ERROR_CAMERA_DEVICE = 4;
    
    public static final int ERROR_CAMERA_DISABLED = 3;
    
    public static final int ERROR_CAMERA_IN_USE = 1;
    
    public static final int ERROR_CAMERA_SERVICE = 5;
    
    public static final int ERROR_MAX_CAMERAS_IN_USE = 2;
    
    public void onClosed(CameraDevice param1CameraDevice) {}
    
    public abstract void onDisconnected(CameraDevice param1CameraDevice);
    
    public abstract void onError(CameraDevice param1CameraDevice, int param1Int);
    
    public abstract void onOpened(CameraDevice param1CameraDevice);
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface ErrorCode {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ErrorCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */