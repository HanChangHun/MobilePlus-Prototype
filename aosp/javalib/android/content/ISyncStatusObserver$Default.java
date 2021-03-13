package android.content;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISyncStatusObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onStatusChanged(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncStatusObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */