package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothA2dp {
  public static IBluetoothA2dp sDefaultImpl;
  
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dp.Stub.getDefaultImpl().connect(paramBluetoothDevice);
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
  
  public void disableOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(16, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        IBluetoothA2dp.Stub.getDefaultImpl().disableOptionalCodecs(paramBluetoothDevice);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dp.Stub.getDefaultImpl().disconnect(paramBluetoothDevice);
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
  
  public void enableOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(15, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        IBluetoothA2dp.Stub.getDefaultImpl().enableOptionalCodecs(paramBluetoothDevice);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public BluetoothDevice getActiveDevice() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      BluetoothDevice bluetoothDevice;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bluetoothDevice = IBluetoothA2dp.Stub.getDefaultImpl().getActiveDevice();
        return bluetoothDevice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel2);
      } else {
        bluetoothDevice = null;
      } 
      return bluetoothDevice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public BluetoothCodecStatus getCodecStatus(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getCodecStatus(paramBluetoothDevice); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        BluetoothCodecStatus bluetoothCodecStatus = (BluetoothCodecStatus)BluetoothCodecStatus.CREATOR.createFromParcel(parcel2);
      } else {
        paramBluetoothDevice = null;
      } 
      return (BluetoothCodecStatus)paramBluetoothDevice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getConnectedDevices(); 
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getConnectionPolicy(paramBluetoothDevice); 
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getConnectionState(paramBluetoothDevice); 
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
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothA2dp";
  }
  
  public int getOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getOptionalCodecsEnabled(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().getPriority(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isA2dpPlaying(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dp.Stub.getDefaultImpl().isA2dpPlaying(paramBluetoothDevice);
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
  
  public boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(10, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dp.Stub.getDefaultImpl().isAvrcpAbsoluteVolumeSupported();
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
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dp.Stub.getDefaultImpl().setActiveDevice(paramBluetoothDevice);
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
  
  public void setAvrcpAbsoluteVolume(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        IBluetoothA2dp.Stub.getDefaultImpl().setAvrcpAbsoluteVolume(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setCodecConfigPreference(BluetoothDevice paramBluetoothDevice, BluetoothCodecConfig paramBluetoothCodecConfig) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBluetoothCodecConfig != null) {
        parcel.writeInt(1);
        paramBluetoothCodecConfig.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        IBluetoothA2dp.Stub.getDefaultImpl().setCodecConfigPreference(paramBluetoothDevice, paramBluetoothCodecConfig);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        bool = IBluetoothA2dp.Stub.getDefaultImpl().setConnectionPolicy(paramBluetoothDevice, paramInt);
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
  
  public void setOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(19, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
        IBluetoothA2dp.Stub.getDefaultImpl().setOptionalCodecsEnabled(paramBluetoothDevice, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public int supportsOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
        return IBluetoothA2dp.Stub.getDefaultImpl().supportsOptionalCodecs(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothA2dp$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */