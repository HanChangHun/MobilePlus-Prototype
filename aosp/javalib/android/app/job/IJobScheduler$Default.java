package android.app.job;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IJobScheduler {
  public IBinder asBinder() {
    return null;
  }
  
  public void cancel(int paramInt) throws RemoteException {}
  
  public void cancelAll() throws RemoteException {}
  
  public int enqueue(JobInfo paramJobInfo, JobWorkItem paramJobWorkItem) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice getAllJobSnapshots() throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getAllPendingJobs() throws RemoteException {
    return null;
  }
  
  public JobInfo getPendingJob(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<JobInfo> getStartedJobs() throws RemoteException {
    return null;
  }
  
  public int schedule(JobInfo paramJobInfo) throws RemoteException {
    return 0;
  }
  
  public int scheduleAsPackage(JobInfo paramJobInfo, String paramString1, int paramInt, String paramString2) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobScheduler$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */