package android.filterfw.core;

import android.os.AsyncTask;
import android.util.Log;

class AsyncRunnerTask extends AsyncTask<SyncRunner, Void, AsyncRunner.RunnerResult> {
  private static final String TAG = "AsyncRunnerTask";
  
  private AsyncRunnerTask() {}
  
  protected AsyncRunner.RunnerResult doInBackground(SyncRunner... paramVarArgs) {
    AsyncRunner.RunnerResult runnerResult = new AsyncRunner.RunnerResult(AsyncRunner.this, null);
    try {
      if (paramVarArgs.length <= 1) {
        paramVarArgs[0].assertReadyToStep();
        if (AsyncRunner.access$100(AsyncRunner.this))
          Log.v("AsyncRunnerTask", "Starting background graph processing."); 
        AsyncRunner.this.activateGlContext();
        if (AsyncRunner.access$100(AsyncRunner.this))
          Log.v("AsyncRunnerTask", "Preparing filter graph for processing."); 
        paramVarArgs[0].beginProcessing();
        if (AsyncRunner.access$100(AsyncRunner.this))
          Log.v("AsyncRunnerTask", "Running graph."); 
        runnerResult.status = 1;
        while (!isCancelled() && runnerResult.status == 1) {
          if (!paramVarArgs[0].performStep()) {
            runnerResult.status = paramVarArgs[0].determinePostRunState();
            if (runnerResult.status == 3) {
              paramVarArgs[0].waitUntilWake();
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
    if (AsyncRunner.access$100(AsyncRunner.this))
      Log.v("AsyncRunnerTask", "Done with background graph processing."); 
    return runnerResult;
  }
  
  protected void onCancelled(AsyncRunner.RunnerResult paramRunnerResult) {
    onPostExecute(paramRunnerResult);
  }
  
  protected void onPostExecute(AsyncRunner.RunnerResult paramRunnerResult) {
    if (AsyncRunner.access$100(AsyncRunner.this))
      Log.v("AsyncRunnerTask", "Starting post-execute."); 
    AsyncRunner.access$200(AsyncRunner.this, false);
    AsyncRunner.RunnerResult runnerResult = paramRunnerResult;
    if (paramRunnerResult == null) {
      runnerResult = new AsyncRunner.RunnerResult(AsyncRunner.this, null);
      runnerResult.status = 5;
    } 
    AsyncRunner.access$300(AsyncRunner.this, runnerResult.exception);
    if (runnerResult.status == 5 || runnerResult.status == 6) {
      if (AsyncRunner.access$100(AsyncRunner.this))
        Log.v("AsyncRunnerTask", "Closing filters."); 
      try {
        AsyncRunner.access$400(AsyncRunner.this).close();
      } catch (Exception exception) {
        runnerResult.status = 6;
        AsyncRunner.access$300(AsyncRunner.this, exception);
      } 
    } 
    if (AsyncRunner.access$500(AsyncRunner.this) != null) {
      if (AsyncRunner.access$100(AsyncRunner.this))
        Log.v("AsyncRunnerTask", "Calling graph done callback."); 
      AsyncRunner.access$500(AsyncRunner.this).onRunnerDone(runnerResult.status);
    } 
    if (AsyncRunner.access$100(AsyncRunner.this))
      Log.v("AsyncRunnerTask", "Completed post-execute."); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/AsyncRunner$AsyncRunnerTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */