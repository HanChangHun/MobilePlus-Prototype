package android.app;

import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.RemoteException;

public class Default implements IUserSwitchObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onForegroundProfileSwitch(int paramInt) throws RemoteException {}
  
  public void onLockedBootComplete(int paramInt) throws RemoteException {}
  
  public void onUserSwitchComplete(int paramInt) throws RemoteException {}
  
  public void onUserSwitching(int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUserSwitchObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */