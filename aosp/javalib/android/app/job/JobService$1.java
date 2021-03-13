package android.app.job;

import android.app.Service;

class null extends JobServiceEngine {
  null(Service paramService) {
    super(paramService);
  }
  
  public boolean onStartJob(JobParameters paramJobParameters) {
    return JobService.this.onStartJob(paramJobParameters);
  }
  
  public boolean onStopJob(JobParameters paramJobParameters) {
    return JobService.this.onStopJob(paramJobParameters);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */