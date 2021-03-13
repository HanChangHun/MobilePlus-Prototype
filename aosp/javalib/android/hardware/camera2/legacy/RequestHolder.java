package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.Collection;

public class RequestHolder {
  private static final String TAG = "RequestHolder";
  
  private volatile boolean mFailed = false;
  
  private final long mFrameNumber;
  
  private final Collection<Long> mJpegSurfaceIds;
  
  private final int mNumJpegTargets;
  
  private final int mNumPreviewTargets;
  
  private boolean mOutputAbandoned = false;
  
  private final boolean mRepeating;
  
  private final CaptureRequest mRequest;
  
  private final int mRequestId;
  
  private final int mSubsequeceId;
  
  private RequestHolder(int paramInt1, int paramInt2, CaptureRequest paramCaptureRequest, boolean paramBoolean, long paramLong, int paramInt3, int paramInt4, Collection<Long> paramCollection) {
    this.mRepeating = paramBoolean;
    this.mRequest = paramCaptureRequest;
    this.mRequestId = paramInt1;
    this.mSubsequeceId = paramInt2;
    this.mFrameNumber = paramLong;
    this.mNumJpegTargets = paramInt3;
    this.mNumPreviewTargets = paramInt4;
    this.mJpegSurfaceIds = paramCollection;
  }
  
  public void failRequest() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Capture failed for request: ");
    stringBuilder.append(getRequestId());
    Log.w("RequestHolder", stringBuilder.toString());
    this.mFailed = true;
  }
  
  public long getFrameNumber() {
    return this.mFrameNumber;
  }
  
  public Collection<Surface> getHolderTargets() {
    return getRequest().getTargets();
  }
  
  public CaptureRequest getRequest() {
    return this.mRequest;
  }
  
  public int getRequestId() {
    return this.mRequestId;
  }
  
  public int getSubsequeceId() {
    return this.mSubsequeceId;
  }
  
  public boolean hasJpegTargets() {
    boolean bool;
    if (this.mNumJpegTargets > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasPreviewTargets() {
    boolean bool;
    if (this.mNumPreviewTargets > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOutputAbandoned() {
    return this.mOutputAbandoned;
  }
  
  public boolean isRepeating() {
    return this.mRepeating;
  }
  
  public boolean jpegType(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    return LegacyCameraDevice.containsSurfaceId(paramSurface, this.mJpegSurfaceIds);
  }
  
  public int numJpegTargets() {
    return this.mNumJpegTargets;
  }
  
  public int numPreviewTargets() {
    return this.mNumPreviewTargets;
  }
  
  public boolean requestFailed() {
    return this.mFailed;
  }
  
  public void setOutputAbandoned() {
    this.mOutputAbandoned = true;
  }
  
  public static final class Builder {
    private final Collection<Long> mJpegSurfaceIds;
    
    private final int mNumJpegTargets;
    
    private final int mNumPreviewTargets;
    
    private final boolean mRepeating;
    
    private final CaptureRequest mRequest;
    
    private final int mRequestId;
    
    private final int mSubsequenceId;
    
    public Builder(int param1Int1, int param1Int2, CaptureRequest param1CaptureRequest, boolean param1Boolean, Collection<Long> param1Collection) {
      Preconditions.checkNotNull(param1CaptureRequest, "request must not be null");
      this.mRequestId = param1Int1;
      this.mSubsequenceId = param1Int2;
      this.mRequest = param1CaptureRequest;
      this.mRepeating = param1Boolean;
      this.mJpegSurfaceIds = param1Collection;
      this.mNumJpegTargets = numJpegTargets(param1CaptureRequest);
      this.mNumPreviewTargets = numPreviewTargets(this.mRequest);
    }
    
    private boolean jpegType(Surface param1Surface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
      return LegacyCameraDevice.containsSurfaceId(param1Surface, this.mJpegSurfaceIds);
    }
    
    private int numJpegTargets(CaptureRequest param1CaptureRequest) {
      byte b = 0;
      for (Surface surface : param1CaptureRequest.getTargets()) {
        byte b1;
        try {
          boolean bool = jpegType(surface);
          b1 = b;
          if (bool)
            b1 = b + 1; 
        } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
          Log.d("RequestHolder", "Surface abandoned, skipping...", (Throwable)bufferQueueAbandonedException);
          b1 = b;
        } 
        b = b1;
      } 
      return b;
    }
    
    private int numPreviewTargets(CaptureRequest param1CaptureRequest) {
      int i = 0;
      for (Surface surface : param1CaptureRequest.getTargets()) {
        try {
          boolean bool = previewType(surface);
          int j = i;
          if (bool)
            j = i + 1; 
          i = j;
        } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
          Log.d("RequestHolder", "Surface abandoned, skipping...", (Throwable)bufferQueueAbandonedException);
        } 
      } 
      return i;
    }
    
    private boolean previewType(Surface param1Surface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
      return jpegType(param1Surface) ^ true;
    }
    
    public RequestHolder build(long param1Long) {
      return new RequestHolder(this.mRequestId, this.mSubsequenceId, this.mRequest, this.mRepeating, param1Long, this.mNumJpegTargets, this.mNumPreviewTargets, this.mJpegSurfaceIds);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */