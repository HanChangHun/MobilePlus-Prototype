package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothAvrcpTarget {
  public IBinder asBinder() {
    return null;
  }
  
  public void sendVolumeChanged(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpTarget$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */