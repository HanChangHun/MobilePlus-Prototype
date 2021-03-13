package android.bluetooth;

import android.app.PropertyInvalidatedCache;
import android.os.RemoteException;
import android.util.Log;

class null extends PropertyInvalidatedCache<Void, Boolean> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected Boolean recompute(Void paramVoid) {
    try {
      BluetoothAdapter.access$200(BluetoothAdapter.this).readLock().lock();
      if (BluetoothAdapter.access$100(BluetoothAdapter.this) != null) {
        boolean bool = BluetoothAdapter.access$100(BluetoothAdapter.this).isOffloadedFilteringSupported();
        BluetoothAdapter.access$200(BluetoothAdapter.this).readLock().unlock();
        return Boolean.valueOf(bool);
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "failed to get isOffloadedFilteringSupported, error: ", (Throwable)remoteException);
    } finally {}
    BluetoothAdapter.access$200(BluetoothAdapter.this).readLock().unlock();
    return Boolean.valueOf(false);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */