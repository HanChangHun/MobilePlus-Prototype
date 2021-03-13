package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothHearingAid {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHearingAid";
  
  static final int TRANSACTION_connect = 1;
  
  static final int TRANSACTION_disconnect = 2;
  
  static final int TRANSACTION_getActiveDevices = 7;
  
  static final int TRANSACTION_getConnectedDevices = 3;
  
  static final int TRANSACTION_getConnectionPolicy = 9;
  
  static final int TRANSACTION_getConnectionState = 5;
  
  static final int TRANSACTION_getDeviceMode = 13;
  
  static final int TRANSACTION_getDeviceSide = 12;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
  
  static final int TRANSACTION_getHiSyncId = 11;
  
  static final int TRANSACTION_setActiveDevice = 6;
  
  static final int TRANSACTION_setConnectionPolicy = 8;
  
  static final int TRANSACTION_setVolume = 10;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothHearingAid");
  }
  
  public static IBluetoothHearingAid asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothHearingAid");
    return (iInterface != null && iInterface instanceof IBluetoothHearingAid) ? (IBluetoothHearingAid)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothHearingAid getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 13:
        return "getDeviceMode";
      case 12:
        return "getDeviceSide";
      case 11:
        return "getHiSyncId";
      case 10:
        return "setVolume";
      case 9:
        return "getConnectionPolicy";
      case 8:
        return "setConnectionPolicy";
      case 7:
        return "getActiveDevices";
      case 6:
        return "setActiveDevice";
      case 5:
        return "getConnectionState";
      case 4:
        return "getDevicesMatchingConnectionStates";
      case 3:
        return "getConnectedDevices";
      case 2:
        return "disconnect";
      case 1:
        break;
    } 
    return "connect";
  }
  
  public static boolean setDefaultImpl(IBluetoothHearingAid paramIBluetoothHearingAid) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothHearingAid != null) {
        Proxy.sDefaultImpl = paramIBluetoothHearingAid;
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
      long l;
      BluetoothDevice bluetoothDevice;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 13:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = getDeviceMode((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = getDeviceSide((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          l = getHiSyncId((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          setVolume(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = getConnectionPolicy((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
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
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          list = getActiveDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 6:
          list.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool2 = setActiveDevice((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 5:
          list.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          i = getConnectionState((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 4:
          list.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          list = getDevicesMatchingConnectionStates(list.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 3:
          list.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          list = getConnectedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 2:
          list.enforceInterface("android.bluetooth.IBluetoothHearingAid");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool1 = disconnect((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.bluetooth.IBluetoothHearingAid");
      if (list.readInt() != 0) {
        BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
      } else {
        list = null;
      } 
      boolean bool1 = connect((BluetoothDevice)list);
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothHearingAid");
    return true;
  }
  
  private static class Proxy implements IBluetoothHearingAid {
    public static IBluetoothHearingAid sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHearingAid.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHearingAid.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
    
    public List<BluetoothDevice> getActiveDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getActiveDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getConnectedDevices(); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getDeviceMode(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getDeviceMode(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getDeviceSide(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getDeviceSide(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getHiSyncId(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null)
          return IBluetoothHearingAid.Stub.getDefaultImpl().getHiSyncId(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHearingAid";
    }
    
    public boolean setActiveDevice(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHearingAid.Stub.getDefaultImpl().setActiveDevice(param2BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHearingAid.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
    
    public void setVolume(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHearingAid");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHearingAid.Stub.getDefaultImpl() != null) {
          IBluetoothHearingAid.Stub.getDefaultImpl().setVolume(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHearingAid$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */