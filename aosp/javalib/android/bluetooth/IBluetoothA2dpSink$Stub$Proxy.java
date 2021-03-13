package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothA2dpSink {
  public static IBluetoothA2dpSink sDefaultImpl;
  
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dpSink.Stub.getDefaultImpl().connect(paramBluetoothDevice);
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dpSink.Stub.getDefaultImpl().disconnect(paramBluetoothDevice);
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
  
  public BluetoothAudioConfig getAudioConfig(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
        return IBluetoothA2dpSink.Stub.getDefaultImpl().getAudioConfig(paramBluetoothDevice); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        BluetoothAudioConfig bluetoothAudioConfig = (BluetoothAudioConfig)BluetoothAudioConfig.CREATOR.createFromParcel(parcel2);
      } else {
        paramBluetoothDevice = null;
      } 
      return (BluetoothAudioConfig)paramBluetoothDevice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
        return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectedDevices(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
        return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectionPolicy(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
        return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectionState(paramBluetoothDevice); 
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
        return IBluetoothA2dpSink.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothA2dpSink";
  }
  
  public boolean isA2dpPlaying(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dpSink.Stub.getDefaultImpl().isA2dpPlaying(paramBluetoothDevice);
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
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dpSink.Stub.getDefaultImpl().setConnectionPolicy(paramBluetoothDevice, paramInt);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothA2dpSink$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */