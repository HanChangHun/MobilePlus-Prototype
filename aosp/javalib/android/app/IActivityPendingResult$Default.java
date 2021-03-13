package android.app;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IActivityPendingResult {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean sendResult(int paramInt, String paramString, Bundle paramBundle) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityPendingResult$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */