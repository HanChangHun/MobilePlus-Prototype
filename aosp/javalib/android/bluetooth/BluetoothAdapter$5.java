package android.bluetooth;

import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import java.util.Map;

class null extends IBluetoothManagerCallback.Stub {
  public void onBluetoothServiceDown() {
    null = new StringBuilder();
    null.append("onBluetoothServiceDown: ");
    null.append(BluetoothAdapter.access$100(BluetoothAdapter.this));
    Log.d("BluetoothAdapter", null.toString());
    try {
      BluetoothAdapter.access$200(BluetoothAdapter.this).writeLock().lock();
      BluetoothAdapter.access$102(BluetoothAdapter.this, null);
      if (BluetoothAdapter.access$400(BluetoothAdapter.this) != null)
        BluetoothAdapter.access$400(BluetoothAdapter.this).clear(); 
      if (BluetoothAdapter.access$500() != null)
        BluetoothAdapter.access$500().cleanup(); 
      if (BluetoothAdapter.access$600() != null)
        BluetoothAdapter.access$600().cleanup(); 
      BluetoothAdapter.access$200(BluetoothAdapter.this).writeLock().unlock();
    } finally {
      BluetoothAdapter.access$200(BluetoothAdapter.this).writeLock().unlock();
    } 
  }
  
  public void onBluetoothServiceUp(IBluetooth paramIBluetooth) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onBluetoothServiceUp: ");
    stringBuilder.append(paramIBluetooth);
    Log.d("BluetoothAdapter", stringBuilder.toString());
    BluetoothAdapter.access$200(BluetoothAdapter.this).writeLock().lock();
    BluetoothAdapter.access$102(BluetoothAdapter.this, paramIBluetooth);
    BluetoothAdapter.access$200(BluetoothAdapter.this).writeLock().unlock();
    synchronized (BluetoothAdapter.access$300(BluetoothAdapter.this)) {
      for (IBluetoothManagerCallback iBluetoothManagerCallback : BluetoothAdapter.access$300(BluetoothAdapter.this)) {
        if (iBluetoothManagerCallback != null) {
          try {
            iBluetoothManagerCallback.onBluetoothServiceUp(paramIBluetooth);
          } catch (Exception exception) {
            Log.e("BluetoothAdapter", "", exception);
          } 
          continue;
        } 
        Log.d("BluetoothAdapter", "onBluetoothServiceUp: cb is null!");
      } 
      synchronized (BluetoothAdapter.access$000()) {
        Map map = BluetoothAdapter.access$000();
        _$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0 _$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0 = new _$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0();
        this(this);
        map.forEach(_$$Lambda$BluetoothAdapter$5$eKI2JS6EbiGZOGfQ8La27pm0gy0);
        return;
      } 
    } 
  }
  
  public void onBrEdrDown() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */