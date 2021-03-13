package android.filterfw.core;

public abstract class Scheduler {
  private FilterGraph mGraph;
  
  Scheduler(FilterGraph paramFilterGraph) {
    this.mGraph = paramFilterGraph;
  }
  
  boolean finished() {
    return true;
  }
  
  FilterGraph getGraph() {
    return this.mGraph;
  }
  
  abstract void reset();
  
  abstract Filter scheduleNextNode();
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/Scheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */