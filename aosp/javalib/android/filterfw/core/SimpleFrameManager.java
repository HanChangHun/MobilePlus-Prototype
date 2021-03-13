package android.filterfw.core;

public class SimpleFrameManager extends FrameManager {
  private Frame createNewFrame(FrameFormat paramFrameFormat) {
    NativeFrame nativeFrame;
    SimpleFrame simpleFrame;
    int i = paramFrameFormat.getTarget();
    if (i != 1) {
      GLFrame gLFrame;
      if (i != 2) {
        VertexFrame vertexFrame;
        if (i != 3) {
          if (i == 4) {
            vertexFrame = new VertexFrame(paramFrameFormat, this);
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unsupported frame target type: ");
            stringBuilder.append(FrameFormat.targetToString(vertexFrame.getTarget()));
            stringBuilder.append("!");
            throw new RuntimeException(stringBuilder.toString());
          } 
        } else {
          gLFrame = new GLFrame((FrameFormat)vertexFrame, this);
          gLFrame.init(getGLEnvironment());
        } 
      } else {
        nativeFrame = new NativeFrame((FrameFormat)gLFrame, this);
      } 
    } else {
      simpleFrame = new SimpleFrame((FrameFormat)nativeFrame, this);
    } 
    return simpleFrame;
  }
  
  public Frame newBoundFrame(FrameFormat paramFrameFormat, int paramInt, long paramLong) {
    GLFrame gLFrame;
    if (paramFrameFormat.getTarget() == 3) {
      gLFrame = new GLFrame(paramFrameFormat, this, paramInt, paramLong);
      gLFrame.init(getGLEnvironment());
      return gLFrame;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attached frames are not supported for target type: ");
    stringBuilder.append(FrameFormat.targetToString(gLFrame.getTarget()));
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public Frame newFrame(FrameFormat paramFrameFormat) {
    return createNewFrame(paramFrameFormat);
  }
  
  public Frame releaseFrame(Frame paramFrame) {
    int i = paramFrame.decRefCount();
    if (i == 0 && paramFrame.hasNativeAllocation()) {
      paramFrame.releaseNativeAllocation();
      return null;
    } 
    if (i >= 0)
      return paramFrame; 
    throw new RuntimeException("Frame reference count dropped below 0!");
  }
  
  public Frame retainFrame(Frame paramFrame) {
    paramFrame.incRefCount();
    return paramFrame;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SimpleFrameManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */