package android.filterpacks.text;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FrameFormat;
import android.filterfw.format.ObjectFormat;
import android.util.Log;

public class StringLogger extends Filter {
  public StringLogger(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    Log.i("StringLogger", pullInput("string").getObjectValue().toString());
  }
  
  public void setupPorts() {
    addMaskedInputPort("string", (FrameFormat)ObjectFormat.fromClass(Object.class, 1));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/text/StringLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */