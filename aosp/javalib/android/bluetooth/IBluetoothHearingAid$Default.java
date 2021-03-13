package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothHearingAid {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public List<BluetoothDevice> getActiveDevices() throws RemoteException {
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
  
  public int getDeviceMode(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getDeviceSide(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public long getHiSyncId(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0L;
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setVolume(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHearingAid$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */