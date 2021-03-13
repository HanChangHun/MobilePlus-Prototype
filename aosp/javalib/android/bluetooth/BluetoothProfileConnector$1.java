package android.bluetooth;

class null extends IBluetoothStateChangeCallback.Stub {
  public void onBluetoothStateChange(boolean paramBoolean) {
    if (paramBoolean) {
      BluetoothProfileConnector.access$000(BluetoothProfileConnector.this);
    } else {
      BluetoothProfileConnector.access$100(BluetoothProfileConnector.this);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothProfileConnector$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */