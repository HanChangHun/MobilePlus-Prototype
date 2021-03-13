package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothHidDevice {
  public static IBluetoothHidDevice sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().connect(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().disconnect(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
        return IBluetoothHidDevice.Stub.getDefaultImpl().getConnectedDevices(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
        return IBluetoothHidDevice.Stub.getDefaultImpl().getConnectionState(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
        return IBluetoothHidDevice.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothHidDevice";
  }
  
  public String getUserAppName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
        return IBluetoothHidDevice.Stub.getDefaultImpl().getUserAppName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean registerApp(BluetoothHidDeviceAppSdpSettings paramBluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback paramIBluetoothHidDeviceCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothHidDeviceAppSdpSettings != null) {
        parcel1.writeInt(1);
        paramBluetoothHidDeviceAppSdpSettings.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBluetoothHidDeviceAppQosSettings1 != null) {
        parcel1.writeInt(1);
        paramBluetoothHidDeviceAppQosSettings1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBluetoothHidDeviceAppQosSettings2 != null) {
        parcel1.writeInt(1);
        paramBluetoothHidDeviceAppQosSettings2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIBluetoothHidDeviceCallback != null) {
        iBinder = paramIBluetoothHidDeviceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().registerApp(paramBluetoothHidDeviceAppSdpSettings, paramBluetoothHidDeviceAppQosSettings1, paramBluetoothHidDeviceAppQosSettings2, paramIBluetoothHidDeviceCallback);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean replyReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByte(paramByte1);
      parcel1.writeByte(paramByte2);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().replyReport(paramBluetoothDevice, paramByte1, paramByte2, paramArrayOfbyte);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean reportError(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByte(paramByte);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().reportError(paramBluetoothDevice, paramByte);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean sendReport(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().sendReport(paramBluetoothDevice, paramInt, paramArrayOfbyte);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().setConnectionPolicy(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean unplug(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().unplug(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean unregisterApp() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHidDevice.Stub.getDefaultImpl().unregisterApp();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDevice$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */