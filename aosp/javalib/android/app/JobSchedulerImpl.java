package android.app;

import android.app.job.IJobScheduler;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobSnapshot;
import android.app.job.JobWorkItem;
import android.os.RemoteException;
import java.util.List;

public class JobSchedulerImpl extends JobScheduler {
  IJobScheduler mBinder;
  
  public JobSchedulerImpl(IJobScheduler paramIJobScheduler) {
    this.mBinder = paramIJobScheduler;
  }
  
  public void cancel(int paramInt) {
    try {
      this.mBinder.cancel(paramInt);
    } catch (RemoteException remoteException) {}
  }
  
  public void cancelAll() {
    try {
      this.mBinder.cancelAll();
    } catch (RemoteException remoteException) {}
  }
  
  public int enqueue(JobInfo paramJobInfo, JobWorkItem paramJobWorkItem) {
    try {
      return this.mBinder.enqueue(paramJobInfo, paramJobWorkItem);
    } catch (RemoteException remoteException) {
      return 0;
    } 
  }
  
  public List<JobSnapshot> getAllJobSnapshots() {
    try {
      return this.mBinder.getAllJobSnapshots().getList();
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public List<JobInfo> getAllPendingJobs() {
    try {
      return this.mBinder.getAllPendingJobs().getList();
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public JobInfo getPendingJob(int paramInt) {
    try {
      return this.mBinder.getPendingJob(paramInt);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public List<JobInfo> getStartedJobs() {
    try {
      return this.mBinder.getStartedJobs();
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public int schedule(JobInfo paramJobInfo) {
    try {
      return this.mBinder.schedule(paramJobInfo);
    } catch (RemoteException remoteException) {
      return 0;
    } 
  }
  
  public int scheduleAsPackage(JobInfo paramJobInfo, String paramString1, int paramInt, String paramString2) {
    try {
      return this.mBinder.scheduleAsPackage(paramJobInfo, paramString1, paramInt, paramString2);
    } catch (RemoteException remoteException) {
      return 0;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/JobSchedulerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */