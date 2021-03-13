package android.hardware.camera2.legacy;

import android.util.Log;
import android.view.Surface;

class CaptureHolder implements Comparable<CaptureCollector.CaptureHolder> {
  private boolean mCompleted = false;
  
  private boolean mFailedJpeg = false;
  
  private boolean mFailedPreview = false;
  
  private boolean mHasStarted = false;
  
  private final LegacyRequest mLegacy;
  
  private boolean mPreviewCompleted = false;
  
  private int mReceivedFlags = 0;
  
  private final RequestHolder mRequest;
  
  private long mTimestamp = 0L;
  
  public final boolean needsJpeg;
  
  public final boolean needsPreview;
  
  public CaptureHolder(RequestHolder paramRequestHolder, LegacyRequest paramLegacyRequest) {
    this.mRequest = paramRequestHolder;
    this.mLegacy = paramLegacyRequest;
    this.needsJpeg = paramRequestHolder.hasJpegTargets();
    this.needsPreview = paramRequestHolder.hasPreviewTargets();
  }
  
  public int compareTo(CaptureHolder paramCaptureHolder) {
    byte b;
    if (this.mRequest.getFrameNumber() > paramCaptureHolder.mRequest.getFrameNumber()) {
      b = 1;
    } else if (this.mRequest.getFrameNumber() == paramCaptureHolder.mRequest.getFrameNumber()) {
      b = 0;
    } else {
      b = -1;
    } 
    return b;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof CaptureHolder && compareTo((CaptureHolder)paramObject) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isCompleted() {
    boolean bool;
    if (this.needsJpeg == isJpegCompleted() && this.needsPreview == isPreviewCompleted()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isJpegCompleted() {
    boolean bool;
    if ((this.mReceivedFlags & 0x3) == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isPreviewCompleted() {
    boolean bool;
    if ((this.mReceivedFlags & 0xC) == 12) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setJpegFailed() {
    if (!this.needsJpeg || isJpegCompleted())
      return; 
    this.mFailedJpeg = true;
    int i = 0x1 | this.mReceivedFlags;
    this.mReceivedFlags = i;
    this.mReceivedFlags = i | 0x2;
    tryComplete();
  }
  
  public void setJpegProduced() {
    if (this.needsJpeg) {
      if (!isCompleted()) {
        this.mReceivedFlags |= 0x1;
        tryComplete();
        return;
      } 
      throw new IllegalStateException("setJpegProduced called on already completed request.");
    } 
    throw new IllegalStateException("setJpegProduced called for capture with no jpeg targets.");
  }
  
  public void setJpegTimestamp(long paramLong) {
    if (this.needsJpeg) {
      if (!isCompleted()) {
        this.mReceivedFlags |= 0x2;
        if (this.mTimestamp == 0L)
          this.mTimestamp = paramLong; 
        if (!this.mHasStarted) {
          this.mHasStarted = true;
          CaptureCollector.access$100(CaptureCollector.this).setCaptureStart(this.mRequest, this.mTimestamp, -1);
        } 
        tryComplete();
        return;
      } 
      throw new IllegalStateException("setJpegTimestamp called on already completed request.");
    } 
    throw new IllegalStateException("setJpegTimestamp called for capture with no jpeg targets.");
  }
  
  public void setPreviewFailed() {
    if (!this.needsPreview || isPreviewCompleted())
      return; 
    this.mFailedPreview = true;
    int i = this.mReceivedFlags | 0x4;
    this.mReceivedFlags = i;
    this.mReceivedFlags = i | 0x8;
    tryComplete();
  }
  
  public void setPreviewProduced() {
    if (this.needsPreview) {
      if (!isCompleted()) {
        this.mReceivedFlags |= 0x4;
        tryComplete();
        return;
      } 
      throw new IllegalStateException("setPreviewProduced called on already completed request.");
    } 
    throw new IllegalStateException("setPreviewProduced called for capture with no preview targets.");
  }
  
  public void setPreviewTimestamp(long paramLong) {
    if (this.needsPreview) {
      if (!isCompleted()) {
        this.mReceivedFlags |= 0x8;
        if (this.mTimestamp == 0L)
          this.mTimestamp = paramLong; 
        if (!this.needsJpeg && !this.mHasStarted) {
          this.mHasStarted = true;
          CaptureCollector.access$100(CaptureCollector.this).setCaptureStart(this.mRequest, this.mTimestamp, -1);
        } 
        tryComplete();
        return;
      } 
      throw new IllegalStateException("setPreviewTimestamp called on already completed request.");
    } 
    throw new IllegalStateException("setPreviewTimestamp called for capture with no preview targets.");
  }
  
  public void tryComplete() {
    if (!this.mPreviewCompleted && this.needsPreview && isPreviewCompleted()) {
      CaptureCollector.access$000(CaptureCollector.this);
      this.mPreviewCompleted = true;
    } 
    if (isCompleted() && !this.mCompleted) {
      if (this.mFailedPreview || this.mFailedJpeg)
        if (!this.mHasStarted) {
          this.mRequest.failRequest();
          CaptureCollector.access$100(CaptureCollector.this).setCaptureStart(this.mRequest, this.mTimestamp, 3);
        } else {
          for (Surface surface : this.mRequest.getRequest().getTargets()) {
            try {
              if (this.mRequest.jpegType(surface)) {
                if (this.mFailedJpeg)
                  CaptureCollector.access$100(CaptureCollector.this).setCaptureResult(this.mRequest, null, 5, surface); 
                continue;
              } 
              if (this.mFailedPreview)
                CaptureCollector.access$100(CaptureCollector.this).setCaptureResult(this.mRequest, null, 5, surface); 
            } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Unexpected exception when querying Surface: ");
              stringBuilder.append(bufferQueueAbandonedException);
              Log.e("CaptureCollector", stringBuilder.toString());
            } 
          } 
        }  
      CaptureCollector.access$200(CaptureCollector.this, this);
      this.mCompleted = true;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CaptureCollector$CaptureHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */