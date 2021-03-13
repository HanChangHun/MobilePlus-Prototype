package android.filterpacks.text;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.ObjectFormat;

public class StringSource extends Filter {
  private FrameFormat mOutputFormat;
  
  @GenerateFieldPort(name = "stringValue")
  private String mString;
  
  public StringSource(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = paramFilterContext.getFrameManager().newFrame(this.mOutputFormat);
    frame.setObjectValue(this.mString);
    frame.setTimestamp(-1L);
    pushOutput("string", frame);
    closeOutputPort("string");
  }
  
  public void setupPorts() {
    MutableFrameFormat mutableFrameFormat = ObjectFormat.fromClass(String.class, 1);
    this.mOutputFormat = (FrameFormat)mutableFrameFormat;
    addOutputPort("string", (FrameFormat)mutableFrameFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/text/StringSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */