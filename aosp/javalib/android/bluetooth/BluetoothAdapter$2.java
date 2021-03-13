package android.bluetooth;

import android.app.PropertyInvalidatedCache;
import android.os.RemoteException;

class null extends PropertyInvalidatedCache<Void, Integer> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected Integer recompute(Void paramVoid) {
    try {
      int i = BluetoothAdapter.access$100(BluetoothAdapter.this).getState();
      return Integer.valueOf(i);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */