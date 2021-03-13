package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFinalPort;

public class FrameBranch extends Filter {
  @GenerateFinalPort(hasDefault = true, name = "outputs")
  private int mNumberOfOutputs = 2;
  
  public FrameBranch(String paramString) {
    super(paramString);
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = pullInput("in");
    for (byte b = 0; b < this.mNumberOfOutputs; b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("out");
      stringBuilder.append(b);
      pushOutput(stringBuilder.toString(), frame);
    } 
  }
  
  public void setupPorts() {
    addInputPort("in");
    for (byte b = 0; b < this.mNumberOfOutputs; b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("out");
      stringBuilder.append(b);
      addOutputBasedOnInput(stringBuilder.toString(), "in");
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/FrameBranch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */