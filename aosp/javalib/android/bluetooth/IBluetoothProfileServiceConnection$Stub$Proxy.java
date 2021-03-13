package android.bluetooth;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothProfileServiceConnection {
  public static IBluetoothProfileServiceConnection sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothProfileServiceConnection";
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
      if (paramComponentName != null) {
        parcel.writeInt(1);
        paramComponentName.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothProfileServiceConnection.Stub.getDefaultImpl() != null) {
        IBluetoothProfileServiceConnection.Stub.getDefaultImpl().onServiceConnected(paramComponentName, paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
      if (paramComponentName != null) {
        parcel.writeInt(1);
        paramComponentName.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothProfileServiceConnection.Stub.getDefaultImpl() != null) {
        IBluetoothProfileServiceConnection.Stub.getDefaultImpl().onServiceDisconnected(paramComponentName);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothProfileServiceConnection$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */