package android.bluetooth.le;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IScannerCallback {
  public static IScannerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.le.IScannerCallback";
  }
  
  public void onBatchScanResults(List<ScanResult> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(3, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
        IScannerCallback.Stub.getDefaultImpl().onBatchScanResults(paramList);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onFoundOrLost(boolean paramBoolean, ScanResult paramScanResult) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramScanResult != null) {
        parcel.writeInt(1);
        paramScanResult.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
        IScannerCallback.Stub.getDefaultImpl().onFoundOrLost(paramBoolean, paramScanResult);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onScanManagerErrorCallback(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
        IScannerCallback.Stub.getDefaultImpl().onScanManagerErrorCallback(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onScanResult(ScanResult paramScanResult) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
      if (paramScanResult != null) {
        parcel.writeInt(1);
        paramScanResult.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
        IScannerCallback.Stub.getDefaultImpl().onScanResult(paramScanResult);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onScannerRegistered(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
        IScannerCallback.Stub.getDefaultImpl().onScannerRegistered(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IScannerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */