package android.content.pm;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageMoveObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCreated(int paramInt, Bundle paramBundle) throws RemoteException {}
  
  public void onStatusChanged(int paramInt1, int paramInt2, long paramLong) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageMoveObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */