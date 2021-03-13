package android.bluetooth.le;

class null implements Runnable {
  public void run() {
    if (onFound) {
      BluetoothLeScanner.BleScanCallbackWrapper.access$400(BluetoothLeScanner.BleScanCallbackWrapper.this).onScanResult(2, scanResult);
    } else {
      BluetoothLeScanner.BleScanCallbackWrapper.access$400(BluetoothLeScanner.BleScanCallbackWrapper.this).onScanResult(4, scanResult);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeScanner$BleScanCallbackWrapper$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */