package android.bluetooth;

import android.util.Log;

public abstract class Callback {
  private static final String TAG = "BluetoothHidDevCallback";
  
  public void onAppStatusChanged(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onAppStatusChanged: pluggedDevice=");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" registered=");
    stringBuilder.append(paramBoolean);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
  
  public void onConnectionStateChanged(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onConnectionStateChanged: device=");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" state=");
    stringBuilder.append(paramInt);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
  
  public void onGetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onGetReport: device=");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" type=");
    stringBuilder.append(paramByte1);
    stringBuilder.append(" id=");
    stringBuilder.append(paramByte2);
    stringBuilder.append(" bufferSize=");
    stringBuilder.append(paramInt);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
  
  public void onInterruptData(BluetoothDevice paramBluetoothDevice, byte paramByte, byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onInterruptData: device=");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" reportId=");
    stringBuilder.append(paramByte);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
  
  public void onSetProtocol(BluetoothDevice paramBluetoothDevice, byte paramByte) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onSetProtocol: device=");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" protocol=");
    stringBuilder.append(paramByte);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
  
  public void onSetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onSetReport: device=");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(" type=");
    stringBuilder.append(paramByte1);
    stringBuilder.append(" id=");
    stringBuilder.append(paramByte2);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
  
  public void onVirtualCableUnplug(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onVirtualCableUnplug: device=");
    stringBuilder.append(paramBluetoothDevice);
    Log.d("BluetoothHidDevCallback", stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDevice$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */