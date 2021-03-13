package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IProcessObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onForegroundActivitiesChanged(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {}
  
  public void onForegroundServicesChanged(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onProcessDied(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IProcessObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */