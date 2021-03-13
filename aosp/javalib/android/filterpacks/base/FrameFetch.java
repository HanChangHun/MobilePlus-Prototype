package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;

public class FrameFetch extends Filter {
  @GenerateFinalPort(hasDefault = true, name = "format")
  private FrameFormat mFormat;
  
  @GenerateFieldPort(name = "key")
  private String mKey;
  
  @GenerateFieldPort(hasDefault = true, name = "repeatFrame")
  private boolean mRepeatFrame = false;
  
  public FrameFetch(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = paramFilterContext.fetchFrame(this.mKey);
    if (frame != null) {
      pushOutput("frame", frame);
      if (!this.mRepeatFrame)
        closeOutputPort("frame"); 
    } else {
      delayNextProcess(250);
    } 
  }
  
  public void setupPorts() {
    FrameFormat frameFormat1 = this.mFormat;
    FrameFormat frameFormat2 = frameFormat1;
    if (frameFormat1 == null)
      frameFormat2 = FrameFormat.unspecified(); 
    addOutputPort("frame", frameFormat2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/FrameFetch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */