package android.filterfw;

import android.filterfw.core.AsyncRunner;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.GraphRunner;
import android.filterfw.core.RoundRobinScheduler;
import android.filterfw.core.SyncRunner;

class GraphHandle {
  private AsyncRunner mAsyncRunner;
  
  private FilterGraph mGraph;
  
  private SyncRunner mSyncRunner;
  
  public GraphHandle(FilterGraph paramFilterGraph) {
    this.mGraph = paramFilterGraph;
  }
  
  public AsyncRunner getAsyncRunner(FilterContext paramFilterContext) {
    if (this.mAsyncRunner == null) {
      AsyncRunner asyncRunner = new AsyncRunner(paramFilterContext, RoundRobinScheduler.class);
      this.mAsyncRunner = asyncRunner;
      asyncRunner.setGraph(this.mGraph);
    } 
    return this.mAsyncRunner;
  }
  
  public FilterGraph getGraph() {
    return this.mGraph;
  }
  
  public GraphRunner getSyncRunner(FilterContext paramFilterContext) {
    if (this.mSyncRunner == null)
      this.mSyncRunner = new SyncRunner(paramFilterContext, this.mGraph, RoundRobinScheduler.class); 
    return (GraphRunner)this.mSyncRunner;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/GraphEnvironment$GraphHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */