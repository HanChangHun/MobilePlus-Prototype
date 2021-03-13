package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPeriodicAdvertisingCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport paramPeriodicAdvertisingReport) throws RemoteException {}
  
  public void onSyncEstablished(int paramInt1, BluetoothDevice paramBluetoothDevice, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {}
  
  public void onSyncLost(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IPeriodicAdvertisingCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */