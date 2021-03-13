package android.app.backup;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBackupCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void operationComplete(long paramLong) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */