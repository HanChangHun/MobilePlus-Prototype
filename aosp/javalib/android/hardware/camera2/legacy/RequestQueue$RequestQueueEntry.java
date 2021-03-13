package android.hardware.camera2.legacy;

public final class RequestQueueEntry {
  private final BurstHolder mBurstHolder;
  
  private final Long mFrameNumber;
  
  private final boolean mQueueEmpty;
  
  public RequestQueueEntry(BurstHolder paramBurstHolder, Long paramLong, boolean paramBoolean) {
    this.mBurstHolder = paramBurstHolder;
    this.mFrameNumber = paramLong;
    this.mQueueEmpty = paramBoolean;
  }
  
  public BurstHolder getBurstHolder() {
    return this.mBurstHolder;
  }
  
  public Long getFrameNumber() {
    return this.mFrameNumber;
  }
  
  public boolean isQueueEmpty() {
    return this.mQueueEmpty;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestQueue$RequestQueueEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */