package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothPan {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothPan";
  
  static final int TRANSACTION_connect = 3;
  
  static final int TRANSACTION_disconnect = 4;
  
  static final int TRANSACTION_getConnectedDevices = 5;
  
  static final int TRANSACTION_getConnectionState = 7;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 6;
  
  static final int TRANSACTION_isTetheringOn = 1;
  
  static final int TRANSACTION_setBluetoothTethering = 2;
  
  static final int TRANSACTION_setConnectionPolicy = 8;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothPan");
  }
  
  public static IBluetoothPan asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothPan");
    return (iInterface != null && iInterface instanceof IBluetoothPan) ? (IBluetoothPan)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothPan getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 8:
        return "setConnectionPolicy";
      case 7:
        return "getConnectionState";
      case 6:
        return "getDevicesMatchingConnectionStates";
      case 5:
        return "getConnectedDevices";
      case 4:
        return "disconnect";
      case 3:
        return "connect";
      case 2:
        return "setBluetoothTethering";
      case 1:
        break;
    } 
    return "isTetheringOn";
  }
  
  public static boolean setDefaultImpl(IBluetoothPan paramIBluetoothPan) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothPan != null) {
        Proxy.sDefaultImpl = paramIBluetoothPan;
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
      int i;
      List<BluetoothDevice> list;
      BluetoothDevice bluetoothDevice;
      boolean bool;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothPan");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool2 = setConnectionPolicy(bluetoothDevice, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothPan");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          i = getConnectionState((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothPan");
          list = getDevicesMatchingConnectionStates(paramParcel1.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 5:
          list.enforceInterface("android.bluetooth.IBluetoothPan");
          list = getConnectedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 4:
          list.enforceInterface("android.bluetooth.IBluetoothPan");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool1 = disconnect((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 3:
          list.enforceInterface("android.bluetooth.IBluetoothPan");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool1 = connect((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 2:
          list.enforceInterface("android.bluetooth.IBluetoothPan");
          if (list.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          setBluetoothTethering(bool, list.readString());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.bluetooth.IBluetoothPan");
      boolean bool1 = isTetheringOn();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothPan");
    return true;
  }
  
  private static class Proxy implements IBluetoothPan {
    public static IBluetoothPan sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null)
          return IBluetoothPan.Stub.getDefaultImpl().getConnectedDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null)
          return IBluetoothPan.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null)
          return IBluetoothPan.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothPan";
    }
    
    public boolean isTetheringOn() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().isTetheringOn();
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
    
    public void setBluetoothTethering(boolean param2Boolean, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          IBluetoothPan.Stub.getDefaultImpl().setBluetoothTethering(param2Boolean, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothPan$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */