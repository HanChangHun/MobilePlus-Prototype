package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IUidObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onUidActive(int paramInt) throws RemoteException {}
  
  public void onUidCachedChanged(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onUidGone(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onUidIdle(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onUidStateChanged(int paramInt1, int paramInt2, long paramLong, int paramInt3) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUidObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */