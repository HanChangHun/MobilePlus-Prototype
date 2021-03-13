package android.filterfw;

import android.content.Context;
import android.filterfw.core.AsyncRunner;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.FrameManager;
import android.filterfw.core.GraphRunner;
import android.filterfw.core.RoundRobinScheduler;
import android.filterfw.core.SyncRunner;
import android.filterfw.io.GraphIOException;
import android.filterfw.io.GraphReader;
import android.filterfw.io.TextGraphReader;
import java.util.ArrayList;

public class GraphEnvironment extends MffEnvironment {
  public static final int MODE_ASYNCHRONOUS = 1;
  
  public static final int MODE_SYNCHRONOUS = 2;
  
  private GraphReader mGraphReader;
  
  private ArrayList<GraphHandle> mGraphs = new ArrayList<>();
  
  public GraphEnvironment() {
    super(null);
  }
  
  public GraphEnvironment(FrameManager paramFrameManager, GraphReader paramGraphReader) {
    super(paramFrameManager);
    this.mGraphReader = paramGraphReader;
  }
  
  public int addGraph(FilterGraph paramFilterGraph) {
    GraphHandle graphHandle = new GraphHandle(paramFilterGraph);
    this.mGraphs.add(graphHandle);
    return this.mGraphs.size() - 1;
  }
  
  public void addReferences(Object... paramVarArgs) {
    getGraphReader().addReferencesByKeysAndValues(paramVarArgs);
  }
  
  public FilterGraph getGraph(int paramInt) {
    if (paramInt >= 0 && paramInt < this.mGraphs.size())
      return ((GraphHandle)this.mGraphs.get(paramInt)).getGraph(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid graph ID ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" specified in runGraph()!");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public GraphReader getGraphReader() {
    if (this.mGraphReader == null)
      this.mGraphReader = (GraphReader)new TextGraphReader(); 
    return this.mGraphReader;
  }
  
  public GraphRunner getRunner(int paramInt1, int paramInt2) {
    if (paramInt2 != 1) {
      if (paramInt2 == 2)
        return ((GraphHandle)this.mGraphs.get(paramInt1)).getSyncRunner(getContext()); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid execution mode ");
      stringBuilder.append(paramInt2);
      stringBuilder.append(" specified in getRunner()!");
      throw new RuntimeException(stringBuilder.toString());
    } 
    return (GraphRunner)((GraphHandle)this.mGraphs.get(paramInt1)).getAsyncRunner(getContext());
  }
  
  public int loadGraph(Context paramContext, int paramInt) {
    try {
      FilterGraph filterGraph = getGraphReader().readGraphResource(paramContext, paramInt);
      return addGraph(filterGraph);
    } catch (GraphIOException graphIOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not read graph: ");
      stringBuilder.append(graphIOException.getMessage());
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  private class GraphHandle {
    private AsyncRunner mAsyncRunner;
    
    private FilterGraph mGraph;
    
    private SyncRunner mSyncRunner;
    
    public GraphHandle(FilterGraph param1FilterGraph) {
      this.mGraph = param1FilterGraph;
    }
    
    public AsyncRunner getAsyncRunner(FilterContext param1FilterContext) {
      if (this.mAsyncRunner == null) {
        AsyncRunner asyncRunner = new AsyncRunner(param1FilterContext, RoundRobinScheduler.class);
        this.mAsyncRunner = asyncRunner;
        asyncRunner.setGraph(this.mGraph);
      } 
      return this.mAsyncRunner;
    }
    
    public FilterGraph getGraph() {
      return this.mGraph;
    }
    
    public GraphRunner getSyncRunner(FilterContext param1FilterContext) {
      if (this.mSyncRunner == null)
        this.mSyncRunner = new SyncRunner(param1FilterContext, this.mGraph, RoundRobinScheduler.class); 
      return (GraphRunner)this.mSyncRunner;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/GraphEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */