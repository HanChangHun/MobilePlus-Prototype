package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import android.view.Surface;
import com.android.internal.util.Preconditions;
import java.util.Collection;

public final class Builder {
  private final Collection<Long> mJpegSurfaceIds;
  
  private final int mNumJpegTargets;
  
  private final int mNumPreviewTargets;
  
  private final boolean mRepeating;
  
  private final CaptureRequest mRequest;
  
  private final int mRequestId;
  
  private final int mSubsequenceId;
  
  public Builder(int paramInt1, int paramInt2, CaptureRequest paramCaptureRequest, boolean paramBoolean, Collection<Long> paramCollection) {
    Preconditions.checkNotNull(paramCaptureRequest, "request must not be null");
    this.mRequestId = paramInt1;
    this.mSubsequenceId = paramInt2;
    this.mRequest = paramCaptureRequest;
    this.mRepeating = paramBoolean;
    this.mJpegSurfaceIds = paramCollection;
    this.mNumJpegTargets = numJpegTargets(paramCaptureRequest);
    this.mNumPreviewTargets = numPreviewTargets(this.mRequest);
  }
  
  private boolean jpegType(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    return LegacyCameraDevice.containsSurfaceId(paramSurface, this.mJpegSurfaceIds);
  }
  
  private int numJpegTargets(CaptureRequest paramCaptureRequest) {
    byte b = 0;
    for (Surface surface : paramCaptureRequest.getTargets()) {
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
  
  private int numPreviewTargets(CaptureRequest paramCaptureRequest) {
    int i = 0;
    for (Surface surface : paramCaptureRequest.getTargets()) {
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
  
  private boolean previewType(Surface paramSurface) throws LegacyExceptionUtils.BufferQueueAbandonedException {
    return jpegType(paramSurface) ^ true;
  }
  
  public RequestHolder build(long paramLong) {
    return new RequestHolder(this.mRequestId, this.mSubsequenceId, this.mRequest, this.mRepeating, paramLong, this.mNumJpegTargets, this.mNumPreviewTargets, this.mJpegSurfaceIds, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestHolder$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */