package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothHidDeviceCallback extends IInterface {
  void onAppStatusChanged(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException;
  
  void onConnectionStateChanged(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  void onGetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, int paramInt) throws RemoteException;
  
  void onInterruptData(BluetoothDevice paramBluetoothDevice, byte paramByte, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onSetProtocol(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException;
  
  void onSetReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onVirtualCableUnplug(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  public static class Default implements IBluetoothHidDeviceCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAppStatusChanged(BluetoothDevice param1BluetoothDevice, boolean param1Boolean) throws RemoteException {}
    
    public void onConnectionStateChanged(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {}
    
    public void onGetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, int param1Int) throws RemoteException {}
    
    public void onInterruptData(BluetoothDevice param1BluetoothDevice, byte param1Byte, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onSetProtocol(BluetoothDevice param1BluetoothDevice, byte param1Byte) throws RemoteException {}
    
    public void onSetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onVirtualCableUnplug(BluetoothDevice param1BluetoothDevice) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothHidDeviceCallback {
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
    
    public static IBluetoothHidDeviceCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothHidDeviceCallback");
      return (iInterface != null && iInterface instanceof IBluetoothHidDeviceCallback) ? (IBluetoothHidDeviceCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothHidDeviceCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothHidDeviceCallback param1IBluetoothHidDeviceCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothHidDeviceCallback != null) {
          Proxy.sDefaultImpl = param1IBluetoothHidDeviceCallback;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        BluetoothDevice bluetoothDevice;
        boolean bool;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onVirtualCableUnplug((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            onInterruptData(bluetoothDevice, param1Parcel1.readByte(), param1Parcel1.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            onSetProtocol(bluetoothDevice, param1Parcel1.readByte());
            param1Parcel2.writeNoException();
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            onSetReport(bluetoothDevice, param1Parcel1.readByte(), param1Parcel1.readByte(), param1Parcel1.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            onGetReport(bluetoothDevice, param1Parcel1.readByte(), param1Parcel1.readByte(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            onConnectionStateChanged(bluetoothDevice, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1Parcel1.readInt() != 0) {
          bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
        } else {
          bluetoothDevice = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        onAppStatusChanged(bluetoothDevice, bool);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothHidDeviceCallback");
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
  
  private static class Proxy implements IBluetoothHidDeviceCallback {
    public static IBluetoothHidDeviceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHidDeviceCallback";
    }
    
    public void onAppStatusChanged(BluetoothDevice param1BluetoothDevice, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onAppStatusChanged(param1BluetoothDevice, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onConnectionStateChanged(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onConnectionStateChanged(param1BluetoothDevice, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onGetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte1);
        parcel1.writeByte(param1Byte2);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onGetReport(param1BluetoothDevice, param1Byte1, param1Byte2, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onInterruptData(BluetoothDevice param1BluetoothDevice, byte param1Byte, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte);
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onInterruptData(param1BluetoothDevice, param1Byte, param1ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onSetProtocol(BluetoothDevice param1BluetoothDevice, byte param1Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onSetProtocol(param1BluetoothDevice, param1Byte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onSetReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte1);
        parcel1.writeByte(param1Byte2);
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onSetReport(param1BluetoothDevice, param1Byte1, param1Byte2, param1ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onVirtualCableUnplug(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDeviceCallback");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidDeviceCallback.Stub.getDefaultImpl() != null) {
          IBluetoothHidDeviceCallback.Stub.getDefaultImpl().onVirtualCableUnplug(param1BluetoothDevice);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDeviceCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */