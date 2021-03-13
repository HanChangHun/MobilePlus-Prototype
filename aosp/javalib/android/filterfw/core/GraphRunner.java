package android.filterfw.core;

public abstract class GraphRunner {
  public static final int RESULT_BLOCKED = 4;
  
  public static final int RESULT_ERROR = 6;
  
  public static final int RESULT_FINISHED = 2;
  
  public static final int RESULT_RUNNING = 1;
  
  public static final int RESULT_SLEEPING = 3;
  
  public static final int RESULT_STOPPED = 5;
  
  public static final int RESULT_UNKNOWN = 0;
  
  protected FilterContext mFilterContext = null;
  
  public GraphRunner(FilterContext paramFilterContext) {
    this.mFilterContext = paramFilterContext;
  }
  
  protected boolean activateGlContext() {
    GLEnvironment gLEnvironment = this.mFilterContext.getGLEnvironment();
    if (gLEnvironment != null && !gLEnvironment.isActive()) {
      gLEnvironment.activate();
      return true;
    } 
    return false;
  }
  
  public abstract void close();
  
  protected void deactivateGlContext() {
    GLEnvironment gLEnvironment = this.mFilterContext.getGLEnvironment();
    if (gLEnvironment != null)
      gLEnvironment.deactivate(); 
  }
  
  public FilterContext getContext() {
    return this.mFilterContext;
  }
  
  public abstract Exception getError();
  
  public abstract FilterGraph getGraph();
  
  public abstract boolean isRunning();
  
  public abstract void run();
  
  public abstract void setDoneCallback(OnRunnerDoneListener paramOnRunnerDoneListener);
  
  public abstract void stop();
  
  public static interface OnRunnerDoneListener {
    void onRunnerDone(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/GraphRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */