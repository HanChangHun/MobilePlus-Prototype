package android.app.job;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class JobService extends Service {
  public static final String PERMISSION_BIND = "android.permission.BIND_JOB_SERVICE";
  
  private static final String TAG = "JobService";
  
  private JobServiceEngine mEngine;
  
  public final void jobFinished(JobParameters paramJobParameters, boolean paramBoolean) {
    this.mEngine.jobFinished(paramJobParameters, paramBoolean);
  }
  
  public final IBinder onBind(Intent paramIntent) {
    if (this.mEngine == null)
      this.mEngine = new JobServiceEngine(this) {
          public boolean onStartJob(JobParameters param1JobParameters) {
            return JobService.this.onStartJob(param1JobParameters);
          }
          
          public boolean onStopJob(JobParameters param1JobParameters) {
            return JobService.this.onStopJob(param1JobParameters);
          }
        }; 
    return this.mEngine.getBinder();
  }
  
  public abstract boolean onStartJob(JobParameters paramJobParameters);
  
  public abstract boolean onStopJob(JobParameters paramJobParameters);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */