package android.content;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISyncContext {
  public IBinder asBinder() {
    return null;
  }
  
  public void onFinished(SyncResult paramSyncResult) throws RemoteException {}
  
  public void sendHeartbeat() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncContext$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */