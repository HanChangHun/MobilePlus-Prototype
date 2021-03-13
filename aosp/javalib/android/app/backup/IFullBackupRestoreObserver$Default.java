package android.app.backup;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IFullBackupRestoreObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onBackupPackage(String paramString) throws RemoteException {}
  
  public void onEndBackup() throws RemoteException {}
  
  public void onEndRestore() throws RemoteException {}
  
  public void onRestorePackage(String paramString) throws RemoteException {}
  
  public void onStartBackup() throws RemoteException {}
  
  public void onStartRestore() throws RemoteException {}
  
  public void onTimeout() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IFullBackupRestoreObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */