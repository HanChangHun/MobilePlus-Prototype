package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothGattCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCharacteristicRead(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onCharacteristicWrite(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onClientConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) throws RemoteException {}
  
  public void onClientRegistered(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onConfigureMTU(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public void onDescriptorRead(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onDescriptorWrite(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onExecuteWrite(String paramString, int paramInt) throws RemoteException {}
  
  public void onNotify(String paramString, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void onPhyRead(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onPhyUpdate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onReadRemoteRssi(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onSearchComplete(String paramString, List<BluetoothGattService> paramList, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */