package android.app.backup;

import android.os.Bundle;
import android.os.RemoteException;

class BackupManagerMonitorWrapper extends IBackupManagerMonitor.Stub {
  final BackupManagerMonitor mMonitor;
  
  BackupManagerMonitorWrapper(BackupManagerMonitor paramBackupManagerMonitor) {
    this.mMonitor = paramBackupManagerMonitor;
  }
  
  public void onEvent(Bundle paramBundle) throws RemoteException {
    this.mMonitor.onEvent(paramBundle);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupManager$BackupManagerMonitorWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */