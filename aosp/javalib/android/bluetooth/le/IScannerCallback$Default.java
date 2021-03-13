package android.bluetooth.le;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IScannerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onBatchScanResults(List<ScanResult> paramList) throws RemoteException {}
  
  public void onFoundOrLost(boolean paramBoolean, ScanResult paramScanResult) throws RemoteException {}
  
  public void onScanManagerErrorCallback(int paramInt) throws RemoteException {}
  
  public void onScanResult(ScanResult paramScanResult) throws RemoteException {}
  
  public void onScannerRegistered(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IScannerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */