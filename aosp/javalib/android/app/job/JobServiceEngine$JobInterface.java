package android.app.job;

import android.os.Message;
import android.os.RemoteException;
import java.lang.ref.WeakReference;

final class JobInterface extends IJobService.Stub {
  final WeakReference<JobServiceEngine> mService;
  
  JobInterface(JobServiceEngine paramJobServiceEngine) {
    this.mService = new WeakReference<>(paramJobServiceEngine);
  }
  
  public void startJob(JobParameters paramJobParameters) throws RemoteException {
    JobServiceEngine jobServiceEngine = this.mService.get();
    if (jobServiceEngine != null)
      Message.obtain(jobServiceEngine.mHandler, 0, paramJobParameters).sendToTarget(); 
  }
  
  public void stopJob(JobParameters paramJobParameters) throws RemoteException {
    JobServiceEngine jobServiceEngine = this.mService.get();
    if (jobServiceEngine != null)
      Message.obtain(jobServiceEngine.mHandler, 1, paramJobParameters).sendToTarget(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobServiceEngine$JobInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */