package android.bluetooth;

import android.os.ParcelFileDescriptor;
import android.util.Log;

@Deprecated
public abstract class BluetoothHealthCallback {
  private static final String TAG = "BluetoothHealthCallback";
  
  @Deprecated
  public void onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration paramBluetoothHealthAppConfiguration, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onHealthAppConfigurationStatusChange: ");
    stringBuilder.append(paramBluetoothHealthAppConfiguration);
    stringBuilder.append("Status: ");
    stringBuilder.append(paramInt);
    Log.d("BluetoothHealthCallback", stringBuilder.toString());
  }
  
  @Deprecated
  public void onHealthChannelStateChange(BluetoothHealthAppConfiguration paramBluetoothHealthAppConfiguration, BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt3) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onHealthChannelStateChange: ");
    stringBuilder.append(paramBluetoothHealthAppConfiguration);
    stringBuilder.append("Device: ");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append("prevState:");
    stringBuilder.append(paramInt1);
    stringBuilder.append("newState:");
    stringBuilder.append(paramInt2);
    stringBuilder.append("ParcelFd:");
    stringBuilder.append(paramParcelFileDescriptor);
    stringBuilder.append("ChannelId:");
    stringBuilder.append(paramInt3);
    Log.d("BluetoothHealthCallback", stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHealthCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */