package android.filterfw.core;

public abstract class FrameManager {
  private FilterContext mContext;
  
  public Frame duplicateFrame(Frame paramFrame) {
    Frame frame = newFrame(paramFrame.getFormat());
    frame.setDataFromFrame(paramFrame);
    return frame;
  }
  
  public Frame duplicateFrameToTarget(Frame paramFrame, int paramInt) {
    MutableFrameFormat mutableFrameFormat = paramFrame.getFormat().mutableCopy();
    mutableFrameFormat.setTarget(paramInt);
    Frame frame = newFrame(mutableFrameFormat);
    frame.setDataFromFrame(paramFrame);
    return frame;
  }
  
  public FilterContext getContext() {
    return this.mContext;
  }
  
  public GLEnvironment getGLEnvironment() {
    FilterContext filterContext = this.mContext;
    if (filterContext != null) {
      GLEnvironment gLEnvironment = filterContext.getGLEnvironment();
    } else {
      filterContext = null;
    } 
    return (GLEnvironment)filterContext;
  }
  
  public abstract Frame newBoundFrame(FrameFormat paramFrameFormat, int paramInt, long paramLong);
  
  public abstract Frame newFrame(FrameFormat paramFrameFormat);
  
  public abstract Frame releaseFrame(Frame paramFrame);
  
  public abstract Frame retainFrame(Frame paramFrame);
  
  void setContext(FilterContext paramFilterContext) {
    this.mContext = paramFilterContext;
  }
  
  public void tearDown() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FrameManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */