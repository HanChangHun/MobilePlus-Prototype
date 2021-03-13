package android.bluetooth;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class BluetoothHealth implements BluetoothProfile {
  @Deprecated
  public static final int APP_CONFIG_REGISTRATION_FAILURE = 1;
  
  @Deprecated
  public static final int APP_CONFIG_REGISTRATION_SUCCESS = 0;
  
  @Deprecated
  public static final int APP_CONFIG_UNREGISTRATION_FAILURE = 3;
  
  @Deprecated
  public static final int APP_CONFIG_UNREGISTRATION_SUCCESS = 2;
  
  @Deprecated
  public static final int CHANNEL_TYPE_RELIABLE = 10;
  
  @Deprecated
  public static final int CHANNEL_TYPE_STREAMING = 11;
  
  @Deprecated
  public static final int SINK_ROLE = 2;
  
  @Deprecated
  public static final int SOURCE_ROLE = 1;
  
  @Deprecated
  public static final int STATE_CHANNEL_CONNECTED = 2;
  
  @Deprecated
  public static final int STATE_CHANNEL_CONNECTING = 1;
  
  @Deprecated
  public static final int STATE_CHANNEL_DISCONNECTED = 0;
  
  @Deprecated
  public static final int STATE_CHANNEL_DISCONNECTING = 3;
  
  private static final String TAG = "BluetoothHealth";
  
  @Deprecated
  public boolean connectChannelToSource(BluetoothDevice paramBluetoothDevice, BluetoothHealthAppConfiguration paramBluetoothHealthAppConfiguration) {
    Log.e("BluetoothHealth", "connectChannelToSource(): BluetoothHealth is deprecated");
    return false;
  }
  
  @Deprecated
  public boolean disconnectChannel(BluetoothDevice paramBluetoothDevice, BluetoothHealthAppConfiguration paramBluetoothHealthAppConfiguration, int paramInt) {
    Log.e("BluetoothHealth", "disconnectChannel(): BluetoothHealth is deprecated");
    return false;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    Log.e("BluetoothHealth", "getConnectedDevices(): BluetoothHealth is deprecated");
    return new ArrayList<>();
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    Log.e("BluetoothHealth", "getConnectionState(): BluetoothHealth is deprecated");
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    Log.e("BluetoothHealth", "getDevicesMatchingConnectionStates(): BluetoothHealth is deprecated");
    return new ArrayList<>();
  }
  
  @Deprecated
  public ParcelFileDescriptor getMainChannelFd(BluetoothDevice paramBluetoothDevice, BluetoothHealthAppConfiguration paramBluetoothHealthAppConfiguration) {
    Log.e("BluetoothHealth", "getMainChannelFd(): BluetoothHealth is deprecated");
    return null;
  }
  
  @Deprecated
  public boolean registerSinkAppConfiguration(String paramString, int paramInt, BluetoothHealthCallback paramBluetoothHealthCallback) {
    Log.e("BluetoothHealth", "registerSinkAppConfiguration(): BluetoothHealth is deprecated");
    return false;
  }
  
  @Deprecated
  public boolean unregisterAppConfiguration(BluetoothHealthAppConfiguration paramBluetoothHealthAppConfiguration) {
    Log.e("BluetoothHealth", "unregisterAppConfiguration(): BluetoothHealth is deprecated");
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */