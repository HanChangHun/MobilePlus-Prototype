package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageDeleteObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void packageDeleted(String paramString, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */