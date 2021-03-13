package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothStateChangeCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onBluetoothStateChange(boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothStateChangeCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */