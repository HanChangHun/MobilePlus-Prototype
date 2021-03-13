package android.content.pm;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageDeleteObserver2 {
  public IBinder asBinder() {
    return null;
  }
  
  public void onPackageDeleted(String paramString1, int paramInt, String paramString2) throws RemoteException {}
  
  public void onUserActionRequired(Intent paramIntent) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver2$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */