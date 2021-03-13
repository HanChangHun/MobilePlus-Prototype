package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothSap {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothSap";
  
  static final int TRANSACTION_connect = 3;
  
  static final int TRANSACTION_disconnect = 4;
  
  static final int TRANSACTION_getClient = 2;
  
  static final int TRANSACTION_getConnectedDevices = 6;
  
  static final int TRANSACTION_getConnectionPolicy = 10;
  
  static final int TRANSACTION_getConnectionState = 8;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 7;
  
  static final int TRANSACTION_getState = 1;
  
  static final int TRANSACTION_isConnected = 5;
  
  static final int TRANSACTION_setConnectionPolicy = 9;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothSap");
  }
  
  public static IBluetoothSap asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothSap");
    return (iInterface != null && iInterface instanceof IBluetoothSap) ? (IBluetoothSap)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothSap getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 10:
        return "getConnectionPolicy";
      case 9:
        return "setConnectionPolicy";
      case 8:
        return "getConnectionState";
      case 7:
        return "getDevicesMatchingConnectionStates";
      case 6:
        return "getConnectedDevices";
      case 5:
        return "isConnected";
      case 4:
        return "disconnect";
      case 3:
        return "connect";
      case 2:
        return "getClient";
      case 1:
        break;
    } 
    return "getState";
  }
  
  public static boolean setDefaultImpl(IBluetoothSap paramIBluetoothSap) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothSap != null) {
        Proxy.sDefaultImpl = paramIBluetoothSap;
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
      boolean bool2;
      int j;
      boolean bool1;
      List<BluetoothDevice> list;
      BluetoothDevice bluetoothDevice1;
      BluetoothDevice bluetoothDevice2;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothSap");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = getConnectionPolicy((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothSap");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice2 = null;
          } 
          bool2 = setConnectionPolicy(bluetoothDevice2, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothSap");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          j = getConnectionState((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothSap");
          list = getDevicesMatchingConnectionStates(paramParcel1.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 6:
          list.enforceInterface("android.bluetooth.IBluetoothSap");
          list = getConnectedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 5:
          list.enforceInterface("android.bluetooth.IBluetoothSap");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool1 = isConnected((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 4:
          list.enforceInterface("android.bluetooth.IBluetoothSap");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool1 = disconnect((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 3:
          list.enforceInterface("android.bluetooth.IBluetoothSap");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool1 = connect((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 2:
          list.enforceInterface("android.bluetooth.IBluetoothSap");
          bluetoothDevice1 = getClient();
          paramParcel2.writeNoException();
          if (bluetoothDevice1 != null) {
            paramParcel2.writeInt(1);
            bluetoothDevice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 1:
          break;
      } 
      bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothSap");
      int i = getState();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(i);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothSap");
    return true;
  }
  
  private static class Proxy implements IBluetoothSap {
    public static IBluetoothSap sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean connect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
    
    public boolean disconnect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
    
    public BluetoothDevice getClient() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        BluetoothDevice bluetoothDevice;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bluetoothDevice = IBluetoothSap.Stub.getDefaultImpl().getClient();
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
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getConnectedDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionPolicy(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothSap";
    }
    
    public int getState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isConnected(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().isConnected(param2BluetoothDevice);
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
    
    public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothSap$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */