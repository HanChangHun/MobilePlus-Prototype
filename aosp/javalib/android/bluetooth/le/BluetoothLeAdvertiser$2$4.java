package android.bluetooth.le;

class null implements Runnable {
  public void run() {
    AdvertisingSet advertisingSet = (AdvertisingSet)BluetoothLeAdvertiser.access$400(this.this$1.this$0).get(Integer.valueOf(advertiserId));
    callback.onAdvertisingEnabled(advertisingSet, enabled, status);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeAdvertiser$2$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */