package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothManagerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onBluetoothServiceDown() throws RemoteException {}
  
  public void onBluetoothServiceUp(IBluetooth paramIBluetooth) throws RemoteException {}
  
  public void onBrEdrDown() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManagerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */