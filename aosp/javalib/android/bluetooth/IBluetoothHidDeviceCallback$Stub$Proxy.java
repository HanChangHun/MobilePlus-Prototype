package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothHidDeviceCallback {
  public static IBluetoothHidDeviceCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothHidDeviceCallback";
  }
  
  public void onAppStatusChanged(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onAppStatusChanged(paramBluetoothDevice, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onConnectionStateChanged(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onConnectionStateChanged(paramBluetoothDevice, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onGetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByte(paramByte1);
      parcel1.writeByte(paramByte2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onGetReport(paramBluetoothDevice, paramByte1, paramByte2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onInterruptData(BluetoothDevice paramBluetoothDevice, byte paramByte, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByte(paramByte);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onInterruptData(paramBluetoothDevice, paramByte, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onSetProtocol(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByte(paramByte);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onSetProtocol(paramBluetoothDevice, paramByte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onSetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByte(paramByte1);
      parcel1.writeByte(paramByte2);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onSetReport(paramBluetoothDevice, paramByte1, paramByte2, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onVirtualCableUnplug(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
        IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onVirtualCableUnplug(paramBluetoothDevice);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDeviceCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */