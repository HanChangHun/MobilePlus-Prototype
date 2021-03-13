package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothHidDeviceCallback {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHidDeviceCallback";
  
  static final int TRANSACTION_onAppStatusChanged = 1;
  
  static final int TRANSACTION_onConnectionStateChanged = 2;
  
  static final int TRANSACTION_onGetReport = 3;
  
  static final int TRANSACTION_onInterruptData = 6;
  
  static final int TRANSACTION_onSetProtocol = 5;
  
  static final int TRANSACTION_onSetReport = 4;
  
  static final int TRANSACTION_onVirtualCableUnplug = 7;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothHidDeviceCallback");
  }
  
  public static IBluetoothHidDeviceCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothHidDeviceCallback");
    return (iInterface != null && iInterface instanceof IBluetoothHidDeviceCallback) ? (IBluetoothHidDeviceCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothHidDeviceCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return "onVirtualCableUnplug";
      case 6:
        return "onInterruptData";
      case 5:
        return "onSetProtocol";
      case 4:
        return "onSetReport";
      case 3:
        return "onGetReport";
      case 2:
        return "onConnectionStateChanged";
      case 1:
        break;
    } 
    return "onAppStatusChanged";
  }
  
  public static boolean setDefaultImpl(IBluetoothHidDeviceCallback paramIBluetoothHidDeviceCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothHidDeviceCallback != null) {
        Proxy.sDefaultImpl = paramIBluetoothHidDeviceCallback;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      BluetoothDevice bluetoothDevice;
      boolean bool;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onVirtualCableUnplug((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          onInterruptData(bluetoothDevice, paramParcel1.readByte(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          onSetProtocol(bluetoothDevice, paramParcel1.readByte());
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          onSetReport(bluetoothDevice, paramParcel1.readByte(), paramParcel1.readByte(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          onGetReport(bluetoothDevice, paramParcel1.readByte(), paramParcel1.readByte(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          onConnectionStateChanged(bluetoothDevice, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
      if (paramParcel1.readInt() != 0) {
        bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
      } else {
        bluetoothDevice = null;
      } 
      if (paramParcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onAppStatusChanged(bluetoothDevice, bool);
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothHidDeviceCallback");
    return true;
  }
  
  private static class Proxy implements IBluetoothHidDeviceCallback {
    public static IBluetoothHidDeviceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHidDeviceCallback";
    }
    
    public void onAppStatusChanged(BluetoothDevice param2BluetoothDevice, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onAppStatusChanged(param2BluetoothDevice, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onConnectionStateChanged(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onConnectionStateChanged(param2BluetoothDevice, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onGetReport(BluetoothDevice param2BluetoothDevice, byte param2Byte1, byte param2Byte2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte1);
        parcel1.writeByte(param2Byte2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onGetReport(param2BluetoothDevice, param2Byte1, param2Byte2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onInterruptData(BluetoothDevice param2BluetoothDevice, byte param2Byte, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onInterruptData(param2BluetoothDevice, param2Byte, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onSetProtocol(BluetoothDevice param2BluetoothDevice, byte param2Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onSetProtocol(param2BluetoothDevice, param2Byte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onSetReport(BluetoothDevice param2BluetoothDevice, byte param2Byte1, byte param2Byte2, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte1);
        parcel1.writeByte(param2Byte2);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onSetReport(param2BluetoothDevice, param2Byte1, param2Byte2, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onVirtualCableUnplug(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onVirtualCableUnplug(param2BluetoothDevice);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDeviceCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */