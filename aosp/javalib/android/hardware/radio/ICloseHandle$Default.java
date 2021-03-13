package android.hardware.radio;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ICloseHandle {
  public IBinder asBinder() {
    return null;
  }
  
  public void close() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ICloseHandle$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */