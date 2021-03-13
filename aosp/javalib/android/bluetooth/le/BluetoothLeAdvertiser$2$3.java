package android.bluetooth.le;

class null implements Runnable {
  public void run() {
    AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(this.this$1.this$0).get(Integer.valueOf(advertiserId));
    callback.onAdvertisingSetStopped(advertisingSet);
    BluetoothLeAdvertiser.access$400(this.this$1.this$0).remove(Integer.valueOf(advertiserId));
    BluetoothLeAdvertiser.access$200(this.this$1.this$0).remove(callback);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeAdvertiser$2$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */