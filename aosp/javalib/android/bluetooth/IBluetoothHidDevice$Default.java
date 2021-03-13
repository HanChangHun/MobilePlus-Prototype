package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothHidDevice {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    return null;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public String getUserAppName() throws RemoteException {
    return null;
  }
  
  public boolean registerApp(BluetoothHidDeviceAppSdpSettings paramBluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback paramIBluetoothHidDeviceCallback) throws RemoteException {
    return false;
  }
  
  public boolean replyReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean reportError(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException {
    return false;
  }
  
  public boolean sendReport(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean unplug(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean unregisterApp() throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDevice$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */