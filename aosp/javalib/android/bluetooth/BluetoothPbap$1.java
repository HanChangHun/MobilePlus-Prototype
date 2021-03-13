package android.bluetooth;

class null extends IBluetoothStateChangeCallback.Stub {
  public void onBluetoothStateChange(boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onBluetoothStateChange: up=");
    stringBuilder.append(paramBoolean);
    BluetoothPbap.access$000(stringBuilder.toString());
    if (!paramBoolean) {
      BluetoothPbap.access$100(BluetoothPbap.this);
    } else {
      BluetoothPbap.this.doBind();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothPbap$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */