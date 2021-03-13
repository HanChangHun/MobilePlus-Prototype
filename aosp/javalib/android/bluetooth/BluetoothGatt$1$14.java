package android.bluetooth;

class null implements Runnable {
  public void run() {
    BluetoothGattCallback bluetoothGattCallback = BluetoothGatt.access$100(this.this$1.this$0);
    if (bluetoothGattCallback != null)
      bluetoothGattCallback.onConnectionUpdated(this.this$1.this$0, interval, latency, timeout, status); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGatt$1$14.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */