package android.filterfw;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterFactory;
import android.filterfw.core.FilterFunction;
import android.filterfw.core.FrameManager;

public class FilterFunctionEnvironment extends MffEnvironment {
  public FilterFunctionEnvironment() {
    super(null);
  }
  
  public FilterFunctionEnvironment(FrameManager paramFrameManager) {
    super(paramFrameManager);
  }
  
  public FilterFunction createFunction(Class paramClass, Object... paramVarArgs) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FilterFunction(");
    stringBuilder.append(paramClass.getSimpleName());
    stringBuilder.append(")");
    String str = stringBuilder.toString();
    Filter filter = FilterFactory.sharedFactory().createFilterByClass(paramClass, str);
    filter.initWithAssignmentList(paramVarArgs);
    return new FilterFunction(getContext(), filter);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/FilterFunctionEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */