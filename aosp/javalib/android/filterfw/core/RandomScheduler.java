package android.filterfw.core;

import java.util.Random;
import java.util.Vector;

public class RandomScheduler extends Scheduler {
  private Random mRand = new Random();
  
  public RandomScheduler(FilterGraph paramFilterGraph) {
    super(paramFilterGraph);
  }
  
  public void reset() {}
  
  public Filter scheduleNextNode() {
    Vector<Filter> vector = new Vector();
    for (Filter filter : getGraph().getFilters()) {
      if (filter.canProcess())
        vector.add(filter); 
    } 
    return (vector.size() > 0) ? vector.elementAt(this.mRand.nextInt(vector.size())) : null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/RandomScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */