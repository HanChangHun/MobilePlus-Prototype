package android.gsi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IGsiServiceCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onResult(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiServiceCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */