package android.filterfw.core;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncRunner extends GraphRunner {
  private static final String TAG = "AsyncRunner";
  
  private boolean isProcessing;
  
  private GraphRunner.OnRunnerDoneListener mDoneListener;
  
  private Exception mException;
  
  private boolean mLogVerbose = Log.isLoggable("AsyncRunner", 2);
  
  private AsyncRunnerTask mRunTask;
  
  private SyncRunner mRunner;
  
  private Class mSchedulerClass = SimpleScheduler.class;
  
  public AsyncRunner(FilterContext paramFilterContext) {
    super(paramFilterContext);
  }
  
  public AsyncRunner(FilterContext paramFilterContext, Class paramClass) {
    super(paramFilterContext);
  }
  
  private void setException(Exception paramException) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mException : Ljava/lang/Exception;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  private void setRunning(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield isProcessing : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isRunning : ()Z
    //   6: ifne -> 34
    //   9: aload_0
    //   10: getfield mLogVerbose : Z
    //   13: ifeq -> 24
    //   16: ldc 'AsyncRunner'
    //   18: ldc 'Closing filters.'
    //   20: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   23: pop
    //   24: aload_0
    //   25: getfield mRunner : Landroid/filterfw/core/SyncRunner;
    //   28: invokevirtual close : ()V
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: new java/lang/RuntimeException
    //   37: astore_1
    //   38: aload_1
    //   39: ldc 'Cannot close graph while it is running!'
    //   41: invokespecial <init> : (Ljava/lang/String;)V
    //   44: aload_1
    //   45: athrow
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	46	finally
    //   24	31	46	finally
    //   34	46	46	finally
  }
  
  public Exception getError() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mException : Ljava/lang/Exception;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public FilterGraph getGraph() {
    SyncRunner syncRunner = this.mRunner;
    if (syncRunner != null) {
      FilterGraph filterGraph = syncRunner.getGraph();
    } else {
      syncRunner = null;
    } 
    return (FilterGraph)syncRunner;
  }
  
  public boolean isRunning() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield isProcessing : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLogVerbose : Z
    //   6: ifeq -> 17
    //   9: ldc 'AsyncRunner'
    //   11: ldc 'Running graph.'
    //   13: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: aload_0
    //   18: aconst_null
    //   19: invokespecial setException : (Ljava/lang/Exception;)V
    //   22: aload_0
    //   23: invokevirtual isRunning : ()Z
    //   26: ifne -> 90
    //   29: aload_0
    //   30: getfield mRunner : Landroid/filterfw/core/SyncRunner;
    //   33: ifnull -> 78
    //   36: new android/filterfw/core/AsyncRunner$AsyncRunnerTask
    //   39: astore_1
    //   40: aload_1
    //   41: aload_0
    //   42: aconst_null
    //   43: invokespecial <init> : (Landroid/filterfw/core/AsyncRunner;Landroid/filterfw/core/AsyncRunner$1;)V
    //   46: aload_0
    //   47: aload_1
    //   48: putfield mRunTask : Landroid/filterfw/core/AsyncRunner$AsyncRunnerTask;
    //   51: aload_0
    //   52: iconst_1
    //   53: invokespecial setRunning : (Z)V
    //   56: aload_0
    //   57: getfield mRunTask : Landroid/filterfw/core/AsyncRunner$AsyncRunnerTask;
    //   60: iconst_1
    //   61: anewarray android/filterfw/core/SyncRunner
    //   64: dup
    //   65: iconst_0
    //   66: aload_0
    //   67: getfield mRunner : Landroid/filterfw/core/SyncRunner;
    //   70: aastore
    //   71: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   74: pop
    //   75: aload_0
    //   76: monitorexit
    //   77: return
    //   78: new java/lang/RuntimeException
    //   81: astore_1
    //   82: aload_1
    //   83: ldc 'Cannot run before a graph is set!'
    //   85: invokespecial <init> : (Ljava/lang/String;)V
    //   88: aload_1
    //   89: athrow
    //   90: new java/lang/RuntimeException
    //   93: astore_1
    //   94: aload_1
    //   95: ldc 'Graph is already running!'
    //   97: invokespecial <init> : (Ljava/lang/String;)V
    //   100: aload_1
    //   101: athrow
    //   102: astore_1
    //   103: aload_0
    //   104: monitorexit
    //   105: aload_1
    //   106: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	102	finally
    //   17	75	102	finally
    //   78	90	102	finally
    //   90	102	102	finally
  }
  
  public void setDoneCallback(GraphRunner.OnRunnerDoneListener paramOnRunnerDoneListener) {
    this.mDoneListener = paramOnRunnerDoneListener;
  }
  
  public void setGraph(FilterGraph paramFilterGraph) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isRunning : ()Z
    //   6: ifne -> 34
    //   9: new android/filterfw/core/SyncRunner
    //   12: astore_2
    //   13: aload_2
    //   14: aload_0
    //   15: getfield mFilterContext : Landroid/filterfw/core/FilterContext;
    //   18: aload_1
    //   19: aload_0
    //   20: getfield mSchedulerClass : Ljava/lang/Class;
    //   23: invokespecial <init> : (Landroid/filterfw/core/FilterContext;Landroid/filterfw/core/FilterGraph;Ljava/lang/Class;)V
    //   26: aload_0
    //   27: aload_2
    //   28: putfield mRunner : Landroid/filterfw/core/SyncRunner;
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: new java/lang/RuntimeException
    //   37: astore_1
    //   38: aload_1
    //   39: ldc 'Graph is already running!'
    //   41: invokespecial <init> : (Ljava/lang/String;)V
    //   44: aload_1
    //   45: athrow
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	46	finally
    //   34	46	46	finally
  }
  
  public void stop() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mRunTask : Landroid/filterfw/core/AsyncRunner$AsyncRunnerTask;
    //   6: ifnull -> 43
    //   9: aload_0
    //   10: getfield mRunTask : Landroid/filterfw/core/AsyncRunner$AsyncRunnerTask;
    //   13: invokevirtual isCancelled : ()Z
    //   16: ifne -> 43
    //   19: aload_0
    //   20: getfield mLogVerbose : Z
    //   23: ifeq -> 34
    //   26: ldc 'AsyncRunner'
    //   28: ldc 'Stopping graph.'
    //   30: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: aload_0
    //   35: getfield mRunTask : Landroid/filterfw/core/AsyncRunner$AsyncRunnerTask;
    //   38: iconst_0
    //   39: invokevirtual cancel : (Z)Z
    //   42: pop
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	46	finally
    //   34	43	46	finally
  }
  
  private class AsyncRunnerTask extends AsyncTask<SyncRunner, Void, RunnerResult> {
    private static final String TAG = "AsyncRunnerTask";
    
    private AsyncRunnerTask() {}
    
    protected AsyncRunner.RunnerResult doInBackground(SyncRunner... param1VarArgs) {
      AsyncRunner.RunnerResult runnerResult = new AsyncRunner.RunnerResult();
      try {
        if (param1VarArgs.length <= 1) {
          param1VarArgs[0].assertReadyToStep();
          if (AsyncRunner.this.mLogVerbose)
            Log.v("AsyncRunnerTask", "Starting background graph processing."); 
          AsyncRunner.this.activateGlContext();
          if (AsyncRunner.this.mLogVerbose)
            Log.v("AsyncRunnerTask", "Preparing filter graph for processing."); 
          param1VarArgs[0].beginProcessing();
          if (AsyncRunner.this.mLogVerbose)
            Log.v("AsyncRunnerTask", "Running graph."); 
          runnerResult.status = 1;
          while (!isCancelled() && runnerResult.status == 1) {
            if (!param1VarArgs[0].performStep()) {
              runnerResult.status = param1VarArgs[0].determinePostRunState();
              if (runnerResult.status == 3) {
                param1VarArgs[0].waitUntilWake();
                runnerResult.status = 1;
              } 
            } 
          } 
          if (isCancelled())
            runnerResult.status = 5; 
        } else {
          RuntimeException runtimeException = new RuntimeException();
          this("More than one runner received!");
          throw runtimeException;
        } 
      } catch (Exception exception) {
        runnerResult.exception = exception;
        runnerResult.status = 6;
      } 
      try {
        AsyncRunner.this.deactivateGlContext();
      } catch (Exception exception) {
        runnerResult.exception = exception;
        runnerResult.status = 6;
      } 
      if (AsyncRunner.this.mLogVerbose)
        Log.v("AsyncRunnerTask", "Done with background graph processing."); 
      return runnerResult;
    }
    
    protected void onCancelled(AsyncRunner.RunnerResult param1RunnerResult) {
      onPostExecute(param1RunnerResult);
    }
    
    protected void onPostExecute(AsyncRunner.RunnerResult param1RunnerResult) {
      if (AsyncRunner.this.mLogVerbose)
        Log.v("AsyncRunnerTask", "Starting post-execute."); 
      AsyncRunner.this.setRunning(false);
      AsyncRunner.RunnerResult runnerResult = param1RunnerResult;
      if (param1RunnerResult == null) {
        runnerResult = new AsyncRunner.RunnerResult();
        runnerResult.status = 5;
      } 
      AsyncRunner.this.setException(runnerResult.exception);
      if (runnerResult.status == 5 || runnerResult.status == 6) {
        if (AsyncRunner.this.mLogVerbose)
          Log.v("AsyncRunnerTask", "Closing filters."); 
        try {
          AsyncRunner.this.mRunner.close();
        } catch (Exception exception) {
          runnerResult.status = 6;
          AsyncRunner.this.setException(exception);
        } 
      } 
      if (AsyncRunner.this.mDoneListener != null) {
        if (AsyncRunner.this.mLogVerbose)
          Log.v("AsyncRunnerTask", "Calling graph done callback."); 
        AsyncRunner.this.mDoneListener.onRunnerDone(runnerResult.status);
      } 
      if (AsyncRunner.this.mLogVerbose)
        Log.v("AsyncRunnerTask", "Completed post-execute."); 
    }
  }
  
  private class RunnerResult {
    public Exception exception;
    
    public int status = 0;
    
    private RunnerResult() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/AsyncRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */