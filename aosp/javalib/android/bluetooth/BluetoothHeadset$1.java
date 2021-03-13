package android.bluetooth;

import android.util.Log;

class null extends IBluetoothStateChangeCallback.Stub {
  public void onBluetoothStateChange(boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onBluetoothStateChange: up=");
    stringBuilder.append(paramBoolean);
    Log.d("BluetoothHeadset", stringBuilder.toString());
    if (!paramBoolean) {
      BluetoothHeadset.access$000(BluetoothHeadset.this);
    } else {
      BluetoothHeadset.access$100(BluetoothHeadset.this);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadset$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */