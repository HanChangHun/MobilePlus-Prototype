package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IStopUserCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void userStopAborted(int paramInt) throws RemoteException {}
  
  public void userStopped(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IStopUserCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */