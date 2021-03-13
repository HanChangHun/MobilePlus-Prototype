package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;

class null extends IPeriodicAdvertisingCallback.Stub {
  public void onPeriodicAdvertisingReport(final PeriodicAdvertisingReport report) {
    handler.post(new Runnable() {
          public void run() {
            callback.onPeriodicAdvertisingReport(report);
          }
        });
  }
  
  public void onSyncEstablished(final int syncHandle, final BluetoothDevice device, final int advertisingSid, final int skip, final int timeout, final int status) {
    handler.post(new Runnable() {
          public void run() {
            callback.onSyncEstablished(syncHandle, device, advertisingSid, skip, timeout, status);
            if (status != 0)
              PeriodicAdvertisingManager.this.mCallbackWrappers.remove(callback); 
          }
        });
  }
  
  public void onSyncLost(final int syncHandle) {
    handler.post(new Runnable() {
          public void run() {
            callback.onSyncLost(syncHandle);
            PeriodicAdvertisingManager.this.mCallbackWrappers.remove(callback);
          }
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */