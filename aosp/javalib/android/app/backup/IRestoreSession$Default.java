package android.app.backup;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IRestoreSession {
  public IBinder asBinder() {
    return null;
  }
  
  public void endRestoreSession() throws RemoteException {}
  
  public int getAvailableRestoreSets(IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    return 0;
  }
  
  public int restoreAll(long paramLong, IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    return 0;
  }
  
  public int restorePackage(String paramString, IRestoreObserver paramIRestoreObserver, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    return 0;
  }
  
  public int restorePackages(long paramLong, IRestoreObserver paramIRestoreObserver, String[] paramArrayOfString, IBackupManagerMonitor paramIBackupManagerMonitor) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreSession$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */