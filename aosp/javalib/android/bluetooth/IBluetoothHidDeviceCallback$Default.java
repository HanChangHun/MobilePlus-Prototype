package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothHidDeviceCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAppStatusChanged(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {}
  
  public void onConnectionStateChanged(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {}
  
  public void onGetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) throws RemoteException {}
  
  public void onInterruptData(BluetoothDevice paramBluetoothDevice, byte paramByte, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onSetProtocol(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException {}
  
  public void onSetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onVirtualCableUnplug(BluetoothDevice paramBluetoothDevice) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDeviceCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */