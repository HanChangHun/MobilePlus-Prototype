package android.bluetooth.le;

import android.util.Log;

class null extends AdvertisingSetCallback {
  public void onAdvertisingEnabled(AdvertisingSet paramAdvertisingSet, boolean paramBoolean, int paramInt) {
    if (paramBoolean) {
      Log.e("BluetoothLeAdvertiser", "Legacy advertiser should be only disabled on timeout, but was enabled!");
      return;
    } 
    BluetoothLeAdvertiser.this.stopAdvertising(callback);
  }
  
  public void onAdvertisingSetStarted(AdvertisingSet paramAdvertisingSet, int paramInt1, int paramInt2) {
    if (paramInt2 != 0) {
      BluetoothLeAdvertiser.access$000(BluetoothLeAdvertiser.this, callback, paramInt2);
      return;
    } 
    BluetoothLeAdvertiser.access$100(BluetoothLeAdvertiser.this, callback, settings);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeAdvertiser$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */