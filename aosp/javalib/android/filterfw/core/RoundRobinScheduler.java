package android.filterfw.core;

import java.util.Set;

public class RoundRobinScheduler extends Scheduler {
  private int mLastPos = -1;
  
  public RoundRobinScheduler(FilterGraph paramFilterGraph) {
    super(paramFilterGraph);
  }
  
  public void reset() {
    this.mLastPos = -1;
  }
  
  public Filter scheduleNextNode() {
    Filter filter;
    Set<Filter> set1 = getGraph().getFilters();
    if (this.mLastPos >= set1.size())
      this.mLastPos = -1; 
    byte b = 0;
    Set<Filter> set2 = null;
    byte b1 = -1;
    for (Filter filter2 : set1) {
      Filter filter1;
      set1 = set2;
      byte b2 = b1;
      if (filter2.canProcess())
        if (b <= this.mLastPos) {
          set1 = set2;
          b2 = b1;
          if (set2 == null) {
            filter1 = filter2;
            b2 = b;
          } 
        } else {
          this.mLastPos = b;
          return filter2;
        }  
      b++;
      filter = filter1;
      b1 = b2;
    } 
    if (filter != null) {
      this.mLastPos = b1;
      return filter;
    } 
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/RoundRobinScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */