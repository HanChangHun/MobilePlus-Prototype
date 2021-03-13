package android.app.backup;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISelectBackupTransportCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onFailure(int paramInt) throws RemoteException {}
  
  public void onSuccess(String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/ISelectBackupTransportCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */