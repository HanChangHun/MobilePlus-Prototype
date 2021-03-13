package android.app.job;

import android.app.Service;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.lang.ref.WeakReference;

public abstract class JobServiceEngine {
  private static final int MSG_EXECUTE_JOB = 0;
  
  private static final int MSG_JOB_FINISHED = 2;
  
  private static final int MSG_STOP_JOB = 1;
  
  private static final String TAG = "JobServiceEngine";
  
  private final IJobService mBinder = new JobInterface(this);
  
  JobHandler mHandler;
  
  public JobServiceEngine(Service paramService) {
    this.mHandler = new JobHandler(paramService.getMainLooper());
  }
  
  public final IBinder getBinder() {
    return this.mBinder.asBinder();
  }
  
  public void jobFinished(JobParameters paramJobParameters, boolean paramBoolean) {
    if (paramJobParameters != null) {
      Message message = Message.obtain(this.mHandler, 2, paramJobParameters);
      message.arg2 = paramBoolean;
      message.sendToTarget();
      return;
    } 
    throw new NullPointerException("params");
  }
  
  public abstract boolean onStartJob(JobParameters paramJobParameters);
  
  public abstract boolean onStopJob(JobParameters paramJobParameters);
  
  class JobHandler extends Handler {
    JobHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    private void ackStartMessage(JobParameters param1JobParameters, boolean param1Boolean) {
      IJobCallback iJobCallback = param1JobParameters.getCallback();
      int i = param1JobParameters.getJobId();
      if (iJobCallback != null) {
        try {
          iJobCallback.acknowledgeStartMessage(i, param1Boolean);
        } catch (RemoteException remoteException) {
          Log.e("JobServiceEngine", "System unreachable for starting job.");
        } 
      } else if (Log.isLoggable("JobServiceEngine", 3)) {
        Log.d("JobServiceEngine", "Attempting to ack a job that has already been processed.");
      } 
    }
    
    private void ackStopMessage(JobParameters param1JobParameters, boolean param1Boolean) {
      IJobCallback iJobCallback = param1JobParameters.getCallback();
      int i = param1JobParameters.getJobId();
      if (iJobCallback != null) {
        try {
          iJobCallback.acknowledgeStopMessage(i, param1Boolean);
        } catch (RemoteException remoteException) {
          Log.e("JobServiceEngine", "System unreachable for stopping job.");
        } 
      } else if (Log.isLoggable("JobServiceEngine", 3)) {
        Log.d("JobServiceEngine", "Attempting to ack a job that has already been processed.");
      } 
    }
    
    public void handleMessage(Message param1Message) {
      JobParameters jobParameters = (JobParameters)param1Message.obj;
      int i = param1Message.what;
      if (i != 0) {
        boolean bool = true;
        if (i != 1) {
          if (i != 2) {
            Log.e("JobServiceEngine", "Unrecognised message received.");
          } else {
            if (param1Message.arg2 != 1)
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
  
  static final class JobInterface extends IJobService.Stub {
    final WeakReference<JobServiceEngine> mService;
    
    JobInterface(JobServiceEngine param1JobServiceEngine) {
      this.mService = new WeakReference<>(param1JobServiceEngine);
    }
    
    public void startJob(JobParameters param1JobParameters) throws RemoteException {
      JobServiceEngine jobServiceEngine = this.mService.get();
      if (jobServiceEngine != null)
        Message.obtain(jobServiceEngine.mHandler, 0, param1JobParameters).sendToTarget(); 
    }
    
    public void stopJob(JobParameters param1JobParameters) throws RemoteException {
      JobServiceEngine jobServiceEngine = this.mService.get();
      if (jobServiceEngine != null)
        Message.obtain(jobServiceEngine.mHandler, 1, param1JobParameters).sendToTarget(); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobServiceEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */