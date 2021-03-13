package android.app.job;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IJobCallback {
  public void acknowledgeStartMessage(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void acknowledgeStopMessage(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public boolean completeWork(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public JobWorkItem dequeueWork(int paramInt) throws RemoteException {
    return null;
  }
  
  public void jobFinished(int paramInt, boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */