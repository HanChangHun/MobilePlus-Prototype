package android.filterfw.core;

import android.os.ConditionVariable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SyncRunner extends GraphRunner {
  private static final String TAG = "SyncRunner";
  
  private GraphRunner.OnRunnerDoneListener mDoneListener = null;
  
  private final boolean mLogVerbose;
  
  private Scheduler mScheduler = null;
  
  private StopWatchMap mTimer = null;
  
  private ConditionVariable mWakeCondition = new ConditionVariable();
  
  private ScheduledThreadPoolExecutor mWakeExecutor = new ScheduledThreadPoolExecutor(1);
  
  public SyncRunner(FilterContext paramFilterContext, FilterGraph paramFilterGraph, Class<?> paramClass) {
    super(paramFilterContext);
    boolean bool = Log.isLoggable("SyncRunner", 2);
    this.mLogVerbose = bool;
    if (bool)
      Log.v("SyncRunner", "Initializing SyncRunner"); 
    if (Scheduler.class.isAssignableFrom(paramClass))
      try {
        this.mScheduler = paramClass.getConstructor(new Class[] { FilterGraph.class }).newInstance(new Object[] { paramFilterGraph });
        this.mFilterContext = paramFilterContext;
        this.mFilterContext.addGraph(paramFilterGraph);
        this.mTimer = new StopWatchMap();
        if (this.mLogVerbose)
          Log.v("SyncRunner", "Setting up filters"); 
        paramFilterGraph.setupFilters();
        return;
      } catch (NoSuchMethodException noSuchMethodException) {
        throw new RuntimeException("Scheduler does not have constructor <init>(FilterGraph)!", noSuchMethodException);
      } catch (InstantiationException instantiationException) {
        throw new RuntimeException("Could not instantiate the Scheduler instance!", instantiationException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new RuntimeException("Cannot access Scheduler constructor!", illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        throw new RuntimeException("Scheduler constructor threw an exception", invocationTargetException);
      } catch (Exception exception) {
        throw new RuntimeException("Could not instantiate Scheduler", exception);
      }  
    throw new IllegalArgumentException("Class provided is not a Scheduler subclass!");
  }
  
  void assertReadyToStep() {
    if (this.mScheduler != null) {
      if (getGraph() != null)
        return; 
      throw new RuntimeException("Calling step on scheduler with no graph in place!");
    } 
    throw new RuntimeException("Attempting to run schedule with no scheduler in place!");
  }
  
  public void beginProcessing() {
    this.mScheduler.reset();
    getGraph().beginProcessing();
  }
  
  public void close() {
    if (this.mLogVerbose)
      Log.v("SyncRunner", "Closing graph."); 
    getGraph().closeFilters(this.mFilterContext);
    this.mScheduler.reset();
  }
  
  protected int determinePostRunState() {
    for (Filter filter : this.mScheduler.getGraph().getFilters()) {
      if (filter.isOpen())
        return (filter.getStatus() == 4) ? 3 : 4; 
    } 
    return 2;
  }
  
  public Exception getError() {
    /* monitor enter ThisExpression{ObjectType{android/filterfw/core/SyncRunner}} */
    /* monitor exit ThisExpression{ObjectType{android/filterfw/core/SyncRunner}} */
    return null;
  }
  
  public FilterGraph getGraph() {
    Scheduler scheduler = this.mScheduler;
    if (scheduler != null) {
      FilterGraph filterGraph = scheduler.getGraph();
    } else {
      scheduler = null;
    } 
    return (FilterGraph)scheduler;
  }
  
  public boolean isRunning() {
    return false;
  }
  
  boolean performStep() {
    if (this.mLogVerbose)
      Log.v("SyncRunner", "Performing one step."); 
    Filter filter = this.mScheduler.scheduleNextNode();
    if (filter != null) {
      this.mTimer.start(filter.getName());
      processFilterNode(filter);
      this.mTimer.stop(filter.getName());
      return true;
    } 
    return false;
  }
  
  protected void processFilterNode(Filter paramFilter) {
    if (this.mLogVerbose)
      Log.v("SyncRunner", "Processing filter node"); 
    paramFilter.performProcess(this.mFilterContext);
    if (paramFilter.getStatus() != 6) {
      if (paramFilter.getStatus() == 4) {
        if (this.mLogVerbose)
          Log.v("SyncRunner", "Scheduling filter wakeup"); 
        scheduleFilterWake(paramFilter, paramFilter.getSleepDelay());
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("There was an error executing ");
    stringBuilder.append(paramFilter);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void run() {
    if (this.mLogVerbose)
      Log.v("SyncRunner", "Beginning run."); 
    assertReadyToStep();
    beginProcessing();
    boolean bool1 = activateGlContext();
    for (boolean bool2 = true; bool2; bool2 = performStep());
    if (bool1)
      deactivateGlContext(); 
    if (this.mDoneListener != null) {
      if (this.mLogVerbose)
        Log.v("SyncRunner", "Calling completion listener."); 
      this.mDoneListener.onRunnerDone(determinePostRunState());
    } 
    if (this.mLogVerbose)
      Log.v("SyncRunner", "Run complete"); 
  }
  
  protected void scheduleFilterWake(final Filter filterToSchedule, int paramInt) {
    this.mWakeCondition.close();
    final ConditionVariable conditionToWake = this.mWakeCondition;
    this.mWakeExecutor.schedule(new Runnable() {
          public void run() {
            filterToSchedule.unsetStatus(4);
            conditionToWake.open();
          }
        },  paramInt, TimeUnit.MILLISECONDS);
  }
  
  public void setDoneCallback(GraphRunner.OnRunnerDoneListener paramOnRunnerDoneListener) {
    this.mDoneListener = paramOnRunnerDoneListener;
  }
  
  public int step() {
    assertReadyToStep();
    if (getGraph().isReady()) {
      int i;
      if (performStep()) {
        i = 1;
      } else {
        i = determinePostRunState();
      } 
      return i;
    } 
    throw new RuntimeException("Trying to process graph that is not open!");
  }
  
  public void stop() {
    throw new RuntimeException("SyncRunner does not support stopping a graph!");
  }
  
  protected void waitUntilWake() {
    this.mWakeCondition.block();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SyncRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */