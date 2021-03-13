package android.filterfw.core;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CachedFrameManager extends SimpleFrameManager {
  private SortedMap<Integer, Frame> mAvailableFrames = new TreeMap<>();
  
  private int mStorageCapacity = 25165824;
  
  private int mStorageSize = 0;
  
  private int mTimeStamp = 0;
  
  private void dropOldestFrame() {
    int i = ((Integer)this.mAvailableFrames.firstKey()).intValue();
    Frame frame = this.mAvailableFrames.get(Integer.valueOf(i));
    this.mStorageSize -= frame.getFormat().getSize();
    frame.releaseNativeAllocation();
    this.mAvailableFrames.remove(Integer.valueOf(i));
  }
  
  private Frame findAvailableFrame(FrameFormat paramFrameFormat, int paramInt, long paramLong) {
    synchronized (this.mAvailableFrames) {
      for (Map.Entry<Integer, Frame> entry : this.mAvailableFrames.entrySet()) {
        Frame frame = (Frame)entry.getValue();
        if (frame.getFormat().isReplaceableBy(paramFrameFormat) && paramInt == frame.getBindingType() && (paramInt == 0 || paramLong == frame.getBindingId())) {
          super.retainFrame(frame);
          this.mAvailableFrames.remove(entry.getKey());
          frame.onFrameFetch();
          frame.reset(paramFrameFormat);
          this.mStorageSize -= paramFrameFormat.getSize();
          return frame;
        } 
      } 
      return null;
    } 
  }
  
  private boolean storeFrame(Frame paramFrame) {
    synchronized (this.mAvailableFrames) {
      int i = paramFrame.getFormat().getSize();
      if (i > this.mStorageCapacity)
        return false; 
      int j;
      for (j = this.mStorageSize + i; j > this.mStorageCapacity; j = this.mStorageSize + i)
        dropOldestFrame(); 
      paramFrame.onFrameStore();
      this.mStorageSize = j;
      this.mAvailableFrames.put(Integer.valueOf(this.mTimeStamp), paramFrame);
      this.mTimeStamp++;
      return true;
    } 
  }
  
  public void clearCache() {
    Iterator<Frame> iterator = this.mAvailableFrames.values().iterator();
    while (iterator.hasNext())
      ((Frame)iterator.next()).releaseNativeAllocation(); 
    this.mAvailableFrames.clear();
  }
  
  public Frame newBoundFrame(FrameFormat paramFrameFormat, int paramInt, long paramLong) {
    Frame frame1 = findAvailableFrame(paramFrameFormat, paramInt, paramLong);
    Frame frame2 = frame1;
    if (frame1 == null)
      frame2 = super.newBoundFrame(paramFrameFormat, paramInt, paramLong); 
    frame2.setTimestamp(-2L);
    return frame2;
  }
  
  public Frame newFrame(FrameFormat paramFrameFormat) {
    Frame frame1 = findAvailableFrame(paramFrameFormat, 0, 0L);
    Frame frame2 = frame1;
    if (frame1 == null)
      frame2 = super.newFrame(paramFrameFormat); 
    frame2.setTimestamp(-2L);
    return frame2;
  }
  
  public Frame releaseFrame(Frame paramFrame) {
    if (paramFrame.isReusable()) {
      int i = paramFrame.decRefCount();
      if (i == 0 && paramFrame.hasNativeAllocation()) {
        if (!storeFrame(paramFrame))
          paramFrame.releaseNativeAllocation(); 
        return null;
      } 
      if (i < 0)
        throw new RuntimeException("Frame reference count dropped below 0!"); 
    } else {
      super.releaseFrame(paramFrame);
    } 
    return paramFrame;
  }
  
  public Frame retainFrame(Frame paramFrame) {
    return super.retainFrame(paramFrame);
  }
  
  public void tearDown() {
    clearCache();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/CachedFrameManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */