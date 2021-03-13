package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothA2dp {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public void disableOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException {}
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public void enableOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException {}
  
  public BluetoothDevice getActiveDevice() throws RemoteException {
    return null;
  }
  
  public BluetoothCodecStatus getCodecStatus(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    return null;
  }
  
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public int getOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public boolean isA2dpPlaying(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException {
    return false;
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public void setAvrcpAbsoluteVolume(int paramInt) throws RemoteException {}
  
  public void setCodecConfigPreference(BluetoothDevice paramBluetoothDevice, BluetoothCodecConfig paramBluetoothCodecConfig) throws RemoteException {}
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {}
  
  public int supportsOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothA2dp$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */