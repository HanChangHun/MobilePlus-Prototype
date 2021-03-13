package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;

public class FrameSource extends Filter {
  @GenerateFinalPort(name = "format")
  private FrameFormat mFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "frame")
  private Frame mFrame = null;
  
  @GenerateFieldPort(hasDefault = true, name = "repeatFrame")
  private boolean mRepeatFrame = false;
  
  public FrameSource(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = this.mFrame;
    if (frame != null)
      pushOutput("frame", frame); 
    if (!this.mRepeatFrame)
      closeOutputPort("frame"); 
  }
  
  public void setupPorts() {
    addOutputPort("frame", this.mFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/FrameSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */