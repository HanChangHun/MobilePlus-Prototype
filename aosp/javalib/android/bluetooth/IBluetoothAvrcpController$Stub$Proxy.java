package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothAvrcpController {
  public static IBluetoothAvrcpController sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
        return IBluetoothAvrcpController.Stub.getDefaultImpl().getConnectedDevices(); 
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
        return IBluetoothAvrcpController.Stub.getDefaultImpl().getConnectionState(paramBluetoothDevice); 
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
        return IBluetoothAvrcpController.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothAvrcpController";
  }
  
  public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
        return IBluetoothAvrcpController.Stub.getDefaultImpl().getPlayerSettings(paramBluetoothDevice); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(parcel2);
      } else {
        paramBluetoothDevice = null;
      } 
      return (BluetoothAvrcpPlayerSettings)paramBluetoothDevice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendGroupNavigationCmd(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null) {
        IBluetoothAvrcpController.Stub.getDefaultImpl().sendGroupNavigationCmd(paramBluetoothDevice, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings paramBluetoothAvrcpPlayerSettings) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
      boolean bool = true;
      if (paramBluetoothAvrcpPlayerSettings != null) {
        parcel1.writeInt(1);
        paramBluetoothAvrcpPlayerSettings.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null) {
        bool = IBluetoothAvrcpController.Stub.getDefaultImpl().setPlayerApplicationSetting(paramBluetoothAvrcpPlayerSettings);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpController$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */