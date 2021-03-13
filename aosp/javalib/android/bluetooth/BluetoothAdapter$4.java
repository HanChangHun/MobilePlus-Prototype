package android.bluetooth;

import android.app.PropertyInvalidatedCache;
import android.os.RemoteException;
import android.util.Log;

class null extends PropertyInvalidatedCache<Integer, Integer> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  public String queryToString(Integer paramInteger) {
    return String.format("getProfileConnectionState(profile=\"%d\")", new Object[] { paramInteger });
  }
  
  protected Integer recompute(Integer paramInteger) {
    try {
      BluetoothAdapter.access$200(BluetoothAdapter.this).readLock().lock();
      if (BluetoothAdapter.access$100(BluetoothAdapter.this) != null) {
        int i = BluetoothAdapter.access$100(BluetoothAdapter.this).getProfileConnectionState(paramInteger.intValue());
        BluetoothAdapter.access$200(BluetoothAdapter.this).readLock().unlock();
        return Integer.valueOf(i);
      } 
    } catch (RemoteException remoteException) {
      Log.e("BluetoothAdapter", "getProfileConnectionState:", (Throwable)remoteException);
    } finally {}
    BluetoothAdapter.access$200(BluetoothAdapter.this).readLock().unlock();
    return Integer.valueOf(0);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */