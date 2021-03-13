package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothAvrcpController {
  public IBinder asBinder() {
    return null;
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
  
  public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public void sendGroupNavigationCmd(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2) throws RemoteException {}
  
  public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings paramBluetoothAvrcpPlayerSettings) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpController$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */