package android.app.job;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

class JobHandler extends Handler {
  JobHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  private void ackStartMessage(JobParameters paramJobParameters, boolean paramBoolean) {
    IJobCallback iJobCallback = paramJobParameters.getCallback();
    int i = paramJobParameters.getJobId();
    if (iJobCallback != null) {
      try {
        iJobCallback.acknowledgeStartMessage(i, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("JobServiceEngine", "System unreachable for starting job.");
      } 
    } else if (Log.isLoggable("JobServiceEngine", 3)) {
      Log.d("JobServiceEngine", "Attempting to ack a job that has already been processed.");
    } 
  }
  
  private void ackStopMessage(JobParameters paramJobParameters, boolean paramBoolean) {
    IJobCallback iJobCallback = paramJobParameters.getCallback();
    int i = paramJobParameters.getJobId();
    if (iJobCallback != null) {
      try {
        iJobCallback.acknowledgeStopMessage(i, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("JobServiceEngine", "System unreachable for stopping job.");
      } 
    } else if (Log.isLoggable("JobServiceEngine", 3)) {
      Log.d("JobServiceEngine", "Attempting to ack a job that has already been processed.");
    } 
  }
  
  public void handleMessage(Message paramMessage) {
    JobParameters jobParameters = (JobParameters)paramMessage.obj;
    int i = paramMessage.what;
    if (i != 0) {
      boolean bool = true;
      if (i != 1) {
        if (i != 2) {
          Log.e("JobServiceEngine", "Unrecognised message received.");
        } else {
          if (paramMessage.arg2 != 1)
            bool = false; 
          IJobCallback iJobCallback = jobParameters.getCallback();
          if (iJobCallback != null) {
            try {
              iJobCallback.jobFinished(jobParameters.getJobId(), bool);
            } catch (RemoteException remoteException) {
              Log.e("JobServiceEngine", "Error reporting job finish to system: binder has goneaway.");
            } 
          } else {
            Log.e("JobServiceEngine", "finishJob() called for a nonexistent job id.");
          } 
        } 
      } else {
        try {
          ackStopMessage(jobParameters, JobServiceEngine.this.onStopJob(jobParameters));
        } catch (Exception exception) {
          Log.e("JobServiceEngine", "Application unable to handle onStopJob.", exception);
          throw new RuntimeException(exception);
        } 
      } 
    } else {
      try {
        ackStartMessage(jobParameters, JobServiceEngine.this.onStartJob(jobParameters));
        return;
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error while executing job: ");
        stringBuilder.append(jobParameters.getJobId());
        Log.e("JobServiceEngine", stringBuilder.toString());
        throw new RuntimeException(exception);
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobServiceEngine$JobHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */