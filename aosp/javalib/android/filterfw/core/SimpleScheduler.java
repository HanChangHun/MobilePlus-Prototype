package android.filterfw.core;

public class SimpleScheduler extends Scheduler {
  public SimpleScheduler(FilterGraph paramFilterGraph) {
    super(paramFilterGraph);
  }
  
  public void reset() {}
  
  public Filter scheduleNextNode() {
    for (Filter filter : getGraph().getFilters()) {
      if (filter.canProcess())
        return filter; 
    } 
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SimpleScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */