package android.app.backup;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBackupObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void backupFinished(int paramInt) throws RemoteException {}
  
  public void onResult(String paramString, int paramInt) throws RemoteException {}
  
  public void onUpdate(String paramString, BackupProgress paramBackupProgress) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */