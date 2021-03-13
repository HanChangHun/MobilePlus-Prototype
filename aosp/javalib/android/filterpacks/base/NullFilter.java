package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;

public class NullFilter extends Filter {
  public NullFilter(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    pullInput("frame");
  }
  
  public void setupPorts() {
    addInputPort("frame");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/NullFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */