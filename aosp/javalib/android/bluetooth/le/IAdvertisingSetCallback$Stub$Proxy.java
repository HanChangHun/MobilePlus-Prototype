package android.bluetooth.le;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAdvertisingSetCallback {
  public static IAdvertisingSetCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.le.IAdvertisingSetCallback";
  }
  
  public void onAdvertisingDataSet(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(5, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingDataSet(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAdvertisingEnabled(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(4, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingEnabled(paramInt1, paramBoolean, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAdvertisingParametersUpdated(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(7, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingParametersUpdated(paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAdvertisingSetStarted(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(1, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStarted(paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAdvertisingSetStopped(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStopped(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onOwnAddressRead(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(2, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onOwnAddressRead(paramInt1, paramInt2, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPeriodicAdvertisingDataSet(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(9, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingDataSet(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPeriodicAdvertisingEnabled(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(10, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingEnabled(paramInt1, paramBoolean, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPeriodicAdvertisingParametersUpdated(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(8, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingParametersUpdated(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onScanResponseDataSet(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(6, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
        IAdvertisingSetCallback.Stub.getDefaultImpl().onScanResponseDataSet(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IAdvertisingSetCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */