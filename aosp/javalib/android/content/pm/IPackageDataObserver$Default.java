package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageDataObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onRemoveCompleted(String paramString, boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDataObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */