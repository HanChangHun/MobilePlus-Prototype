package android.bluetooth.le;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAdvertisingSetCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAdvertisingDataSet(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onAdvertisingEnabled(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {}
  
  public void onAdvertisingParametersUpdated(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onAdvertisingSetStarted(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onAdvertisingSetStopped(int paramInt) throws RemoteException {}
  
  public void onOwnAddressRead(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public void onPeriodicAdvertisingDataSet(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onPeriodicAdvertisingEnabled(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {}
  
  public void onPeriodicAdvertisingParametersUpdated(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onScanResponseDataSet(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IAdvertisingSetCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */