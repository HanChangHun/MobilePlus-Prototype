package android.app.backup;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBackupManagerMonitor {
  public IBinder asBinder() {
    return null;
  }
  
  public void onEvent(Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupManagerMonitor$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */