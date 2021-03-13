package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.GenerateFieldPort;

public class FrameStore extends Filter {
  @GenerateFieldPort(name = "key")
  private String mKey;
  
  public FrameStore(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = pullInput("frame");
    paramFilterContext.storeFrame(this.mKey, frame);
  }
  
  public void setupPorts() {
    addInputPort("frame");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/FrameStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */