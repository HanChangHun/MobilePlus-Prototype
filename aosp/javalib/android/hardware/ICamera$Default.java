package android.hardware;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ICamera {
  public IBinder asBinder() {
    return null;
  }
  
  public void disconnect() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICamera$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */