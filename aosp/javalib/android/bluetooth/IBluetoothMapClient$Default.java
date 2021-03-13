package android.bluetooth;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothMapClient {
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
  
  public int getSupportedFeatures(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public boolean getUnreadMessages(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean isConnected(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean sendMessage(BluetoothDevice paramBluetoothDevice, Uri[] paramArrayOfUri, String paramString, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2) throws RemoteException {
    return false;
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothMapClient$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */