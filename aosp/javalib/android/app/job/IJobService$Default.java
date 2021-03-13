package android.app.job;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IJobService {
  public IBinder asBinder() {
    return null;
  }
  
  public void startJob(JobParameters paramJobParameters) throws RemoteException {}
  
  public void stopJob(JobParameters paramJobParameters) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */