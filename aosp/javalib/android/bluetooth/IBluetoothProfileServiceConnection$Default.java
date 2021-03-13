package android.bluetooth;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothProfileServiceConnection {
  public IBinder asBinder() {
    return null;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) throws RemoteException {}
  
  public void onServiceDisconnected(ComponentName paramComponentName) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothProfileServiceConnection$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */