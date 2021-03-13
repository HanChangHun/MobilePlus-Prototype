package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothPan {
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
  
  public boolean isTetheringOn() throws RemoteException {
    return false;
  }
  
  public void setBluetoothTethering(boolean paramBoolean, String paramString) throws RemoteException {}
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothPan$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */