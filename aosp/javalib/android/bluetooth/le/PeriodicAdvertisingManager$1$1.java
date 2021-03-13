package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;

class null implements Runnable {
  public void run() {
    callback.onSyncEstablished(syncHandle, device, advertisingSid, skip, timeout, status);
    if (status != 0)
      this.this$1.this$0.mCallbackWrappers.remove(callback); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingManager$1$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */