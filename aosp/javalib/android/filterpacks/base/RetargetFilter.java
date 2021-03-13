package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;

public class RetargetFilter extends Filter {
  private MutableFrameFormat mOutputFormat;
  
  private int mTarget = -1;
  
  @GenerateFinalPort(hasDefault = false, name = "target")
  private String mTargetString;
  
  public RetargetFilter(String paramString) {
    super(paramString);
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    MutableFrameFormat mutableFrameFormat = paramFrameFormat.mutableCopy();
    mutableFrameFormat.setTarget(this.mTarget);
    return (FrameFormat)mutableFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("frame");
    Frame frame1 = paramFilterContext.getFrameManager().duplicateFrameToTarget(frame2, this.mTarget);
    pushOutput("frame", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    this.mTarget = FrameFormat.readTargetString(this.mTargetString);
    addInputPort("frame");
    addOutputBasedOnInput("frame", "frame");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/RetargetFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */