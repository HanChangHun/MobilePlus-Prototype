package android.bluetooth.le;

class null implements Runnable {
  public void run() {
    if (status != 0) {
      callback.onAdvertisingSetStarted(null, 0, status);
      BluetoothLeAdvertiser.access$200(this.this$1.this$0).remove(callback);
      return;
    } 
    AdvertisingSet advertisingSet = new AdvertisingSet(advertiserId, BluetoothLeAdvertiser.access$300(this.this$1.this$0));
    BluetoothLeAdvertiser.access$400(this.this$1.this$0).put(Integer.valueOf(advertiserId), advertisingSet);
    callback.onAdvertisingSetStarted(advertisingSet, txPower, status);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeAdvertiser$2$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */