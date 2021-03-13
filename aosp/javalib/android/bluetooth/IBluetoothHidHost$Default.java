package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothHidHost {
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
  
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public boolean getIdleTime(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean getProtocolMode(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean getReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean sendData(BluetoothDevice paramBluetoothDevice, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setIdleTime(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException {
    return false;
  }
  
  public boolean setProtocolMode(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setReport(BluetoothDevice paramBluetoothDevice, byte paramByte, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean virtualUnplug(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidHost$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */