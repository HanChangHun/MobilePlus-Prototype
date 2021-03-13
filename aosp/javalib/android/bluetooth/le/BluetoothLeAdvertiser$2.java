package android.bluetooth.le;

import android.os.Handler;

class null extends IAdvertisingSetCallback.Stub {
  public void onAdvertisingDataSet(final int advertiserId, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onAdvertisingDataSet(advertisingSet, status);
          }
        });
  }
  
  public void onAdvertisingEnabled(final int advertiserId, final boolean enabled, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onAdvertisingEnabled(advertisingSet, enabled, status);
          }
        });
  }
  
  public void onAdvertisingParametersUpdated(final int advertiserId, final int txPower, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onAdvertisingParametersUpdated(advertisingSet, txPower, status);
          }
        });
  }
  
  public void onAdvertisingSetStarted(final int advertiserId, final int txPower, final int status) {
    handler.post(new Runnable() {
          public void run() {
            if (status != 0) {
              callback.onAdvertisingSetStarted(null, 0, status);
              BluetoothLeAdvertiser.access$200(BluetoothLeAdvertiser.this).remove(callback);
              return;
            } 
            AdvertisingSet advertisingSet = new AdvertisingSet(advertiserId, BluetoothLeAdvertiser.access$300(BluetoothLeAdvertiser.this));
            BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).put(Integer.valueOf(advertiserId), advertisingSet);
            callback.onAdvertisingSetStarted(advertisingSet, txPower, status);
          }
        });
  }
  
  public void onAdvertisingSetStopped(final int advertiserId) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onAdvertisingSetStopped(advertisingSet);
            BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).remove(Integer.valueOf(advertiserId));
            BluetoothLeAdvertiser.access$200(BluetoothLeAdvertiser.this).remove(callback);
          }
        });
  }
  
  public void onOwnAddressRead(final int advertiserId, final int addressType, final String address) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onOwnAddressRead(advertisingSet, addressType, address);
          }
        });
  }
  
  public void onPeriodicAdvertisingDataSet(final int advertiserId, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onPeriodicAdvertisingDataSet(advertisingSet, status);
          }
        });
  }
  
  public void onPeriodicAdvertisingEnabled(final int advertiserId, final boolean enable, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onPeriodicAdvertisingEnabled(advertisingSet, enable, status);
          }
        });
  }
  
  public void onPeriodicAdvertisingParametersUpdated(final int advertiserId, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onPeriodicAdvertisingParametersUpdated(advertisingSet, status);
          }
        });
  }
  
  public void onScanResponseDataSet(final int advertiserId, final int status) {
    handler.post(new Runnable() {
          public void run() {
            AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(BluetoothLeAdvertiser.this).get(Integer.valueOf(advertiserId));
            callback.onScanResponseDataSet(advertisingSet, status);
          }
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeAdvertiser$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */