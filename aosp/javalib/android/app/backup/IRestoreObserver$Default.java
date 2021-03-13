package android.app.backup;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IRestoreObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onUpdate(int paramInt, String paramString) throws RemoteException {}
  
  public void restoreFinished(int paramInt) throws RemoteException {}
  
  public void restoreSetsAvailable(RestoreSet[] paramArrayOfRestoreSet) throws RemoteException {}
  
  public void restoreStarting(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */