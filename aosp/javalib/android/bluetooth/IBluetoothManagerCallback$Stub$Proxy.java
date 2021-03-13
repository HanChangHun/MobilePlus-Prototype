package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothManagerCallback {
  public static IBluetoothManagerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothManagerCallback";
  }
  
  public void onBluetoothServiceDown() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
      if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothManagerCallback.Stub.getDefaultImpl().onBluetoothServiceDown();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onBluetoothServiceUp(IBluetooth paramIBluetooth) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
      if (paramIBluetooth != null) {
        iBinder = paramIBluetooth.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothManagerCallback.Stub.getDefaultImpl().onBluetoothServiceUp(paramIBluetooth);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onBrEdrDown() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
      if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothManagerCallback.Stub.getDefaultImpl().onBrEdrDown();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManagerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */