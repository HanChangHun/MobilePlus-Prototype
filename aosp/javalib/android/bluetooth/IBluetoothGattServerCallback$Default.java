package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothGattServerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCharacteristicReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) throws RemoteException {}
  
  public void onCharacteristicWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public void onDescriptorReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) throws RemoteException {}
  
  public void onDescriptorWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onExecuteWrite(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onMtuChanged(String paramString, int paramInt) throws RemoteException {}
  
  public void onNotificationSent(String paramString, int paramInt) throws RemoteException {}
  
  public void onPhyRead(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onPhyUpdate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onServerConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) throws RemoteException {}
  
  public void onServerRegistered(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onServiceAdded(int paramInt, BluetoothGattService paramBluetoothGattService) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattServerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */