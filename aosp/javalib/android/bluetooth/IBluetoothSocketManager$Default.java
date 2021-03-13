package android.bluetooth;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.RemoteException;

public class Default implements IBluetoothSocketManager {
  public IBinder asBinder() {
    return null;
  }
  
  public ParcelFileDescriptor connectSocket(BluetoothDevice paramBluetoothDevice, int paramInt1, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor createSocketChannel(int paramInt1, String paramString, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public void requestMaximumTxDataLength(BluetoothDevice paramBluetoothDevice) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothSocketManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */