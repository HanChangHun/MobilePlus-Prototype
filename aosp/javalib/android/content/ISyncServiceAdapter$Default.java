package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISyncServiceAdapter {
  public IBinder asBinder() {
    return null;
  }
  
  public void cancelSync(ISyncContext paramISyncContext) throws RemoteException {}
  
  public void startSync(ISyncContext paramISyncContext, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncServiceAdapter$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */