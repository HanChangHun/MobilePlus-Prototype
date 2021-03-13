package android.filterpacks.numeric;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.ObjectFormat;

public class SinWaveFilter extends Filter {
  private FrameFormat mOutputFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "stepSize")
  private float mStepSize = 0.05F;
  
  private float mValue = 0.0F;
  
  public SinWaveFilter(String paramString) {
    super(paramString);
  }
  
  public void open(FilterContext paramFilterContext) {
    this.mValue = 0.0F;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = paramFilterContext.getFrameManager().newFrame(this.mOutputFormat);
    frame.setObjectValue(Float.valueOf(((float)Math.sin(this.mValue) + 1.0F) / 2.0F));
    pushOutput("value", frame);
    this.mValue += this.mStepSize;
    frame.release();
  }
  
  public void setupPorts() {
    MutableFrameFormat mutableFrameFormat = ObjectFormat.fromClass(Float.class, 1);
    this.mOutputFormat = (FrameFormat)mutableFrameFormat;
    addOutputPort("value", (FrameFormat)mutableFrameFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/numeric/SinWaveFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */