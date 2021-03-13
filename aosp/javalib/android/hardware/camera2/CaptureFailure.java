package android.hardware.camera2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CaptureFailure {
  public static final int REASON_ERROR = 0;
  
  public static final int REASON_FLUSHED = 1;
  
  private final boolean mDropped;
  
  private final String mErrorPhysicalCameraId;
  
  private final long mFrameNumber;
  
  private final int mReason;
  
  private final CaptureRequest mRequest;
  
  private final int mSequenceId;
  
  public CaptureFailure(CaptureRequest paramCaptureRequest, int paramInt1, boolean paramBoolean, int paramInt2, long paramLong, String paramString) {
    this.mRequest = paramCaptureRequest;
    this.mReason = paramInt1;
    this.mDropped = paramBoolean;
    this.mSequenceId = paramInt2;
    this.mFrameNumber = paramLong;
    this.mErrorPhysicalCameraId = paramString;
  }
  
  public long getFrameNumber() {
    return this.mFrameNumber;
  }
  
  public String getPhysicalCameraId() {
    return this.mErrorPhysicalCameraId;
  }
  
  public int getReason() {
    return this.mReason;
  }
  
  public CaptureRequest getRequest() {
    return this.mRequest;
  }
  
  public int getSequenceId() {
    return this.mSequenceId;
  }
  
  public boolean wasImageCaptured() {
    return this.mDropped ^ true;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FailureReason {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CaptureFailure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */