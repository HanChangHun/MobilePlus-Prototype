package android.filterpacks.text;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.ObjectFormat;
import java.util.Locale;

public class ToUpperCase extends Filter {
  private FrameFormat mOutputFormat;
  
  public ToUpperCase(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    String str = (String)pullInput("mixedcase").getObjectValue();
    Frame frame = paramFilterContext.getFrameManager().newFrame(this.mOutputFormat);
    frame.setObjectValue(str.toUpperCase(Locale.getDefault()));
    pushOutput("uppercase", frame);
  }
  
  public void setupPorts() {
    MutableFrameFormat mutableFrameFormat = ObjectFormat.fromClass(String.class, 1);
    this.mOutputFormat = (FrameFormat)mutableFrameFormat;
    addMaskedInputPort("mixedcase", (FrameFormat)mutableFrameFormat);
    addOutputPort("uppercase", this.mOutputFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/text/ToUpperCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */