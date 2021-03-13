package android.bluetooth;

import android.app.PropertyInvalidatedCache;
import android.os.RemoteException;

class null extends PropertyInvalidatedCache<BluetoothDevice, Integer> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected Integer recompute(BluetoothDevice paramBluetoothDevice) {
    try {
      int i = BluetoothDevice.access$000().getBondState(paramBluetoothDevice);
      return Integer.valueOf(i);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothDevice$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */