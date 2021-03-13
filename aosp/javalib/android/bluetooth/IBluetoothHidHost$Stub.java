package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothHidHost {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHidHost";
  
  static final int TRANSACTION_connect = 1;
  
  static final int TRANSACTION_disconnect = 2;
  
  static final int TRANSACTION_getConnectedDevices = 3;
  
  static final int TRANSACTION_getConnectionPolicy = 7;
  
  static final int TRANSACTION_getConnectionState = 5;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
  
  static final int TRANSACTION_getIdleTime = 14;
  
  static final int TRANSACTION_getProtocolMode = 8;
  
  static final int TRANSACTION_getReport = 11;
  
  static final int TRANSACTION_sendData = 13;
  
  static final int TRANSACTION_setConnectionPolicy = 6;
  
  static final int TRANSACTION_setIdleTime = 15;
  
  static final int TRANSACTION_setProtocolMode = 10;
  
  static final int TRANSACTION_setReport = 12;
  
  static final int TRANSACTION_virtualUnplug = 9;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothHidHost");
  }
  
  public static IBluetoothHidHost asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothHidHost");
    return (iInterface != null && iInterface instanceof IBluetoothHidHost) ? (IBluetoothHidHost)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothHidHost getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 15:
        return "setIdleTime";
      case 14:
        return "getIdleTime";
      case 13:
        return "sendData";
      case 12:
        return "setReport";
      case 11:
        return "getReport";
      case 10:
        return "setProtocolMode";
      case 9:
        return "virtualUnplug";
      case 8:
        return "getProtocolMode";
      case 7:
        return "getConnectionPolicy";
      case 6:
        return "setConnectionPolicy";
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
  
  public static boolean setDefaultImpl(IBluetoothHidHost paramIBluetoothHidHost) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothHidHost != null) {
        Proxy.sDefaultImpl = paramIBluetoothHidHost;
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
      boolean bool3;
      int j;
      boolean bool2;
      int i;
      List<BluetoothDevice> list;
      BluetoothDevice bluetoothDevice;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 15:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool3 = setIdleTime(bluetoothDevice, paramParcel1.readByte());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 14:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bool3 = getIdleTime((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 13:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool3 = sendData(bluetoothDevice, paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool3 = setReport(bluetoothDevice, paramParcel1.readByte(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool3 = getReport(bluetoothDevice, paramParcel1.readByte(), paramParcel1.readByte(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool3 = setProtocolMode(bluetoothDevice, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bool3 = virtualUnplug((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bool3 = getProtocolMode((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          j = getConnectionPolicy((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          bool2 = setConnectionPolicy(bluetoothDevice, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          i = getConnectionState((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothHidHost");
          list = getDevicesMatchingConnectionStates(paramParcel1.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 3:
          list.enforceInterface("android.bluetooth.IBluetoothHidHost");
          list = getConnectedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 2:
          list.enforceInterface("android.bluetooth.IBluetoothHidHost");
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
      list.enforceInterface("android.bluetooth.IBluetoothHidHost");
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
    paramParcel2.writeString("android.bluetooth.IBluetoothHidHost");
    return true;
  }
  
  private static class Proxy implements IBluetoothHidHost {
    public static IBluetoothHidHost sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null)
          return IBluetoothHidHost.Stub.getDefaultImpl().getConnectedDevices(); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null)
          return IBluetoothHidHost.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null)
          return IBluetoothHidHost.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null)
          return IBluetoothHidHost.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getIdleTime(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().getIdleTime(param2BluetoothDevice);
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
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHidHost";
    }
    
    public boolean getProtocolMode(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().getProtocolMode(param2BluetoothDevice);
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
    
    public boolean getReport(BluetoothDevice param2BluetoothDevice, byte param2Byte1, byte param2Byte2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte1);
        parcel1.writeByte(param2Byte2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().getReport(param2BluetoothDevice, param2Byte1, param2Byte2, param2Int);
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
    
    public boolean sendData(BluetoothDevice param2BluetoothDevice, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().sendData(param2BluetoothDevice, param2String);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
    
    public boolean setIdleTime(BluetoothDevice param2BluetoothDevice, byte param2Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().setIdleTime(param2BluetoothDevice, param2Byte);
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
    
    public boolean setProtocolMode(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().setProtocolMode(param2BluetoothDevice, param2Int);
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
    
    public boolean setReport(BluetoothDevice param2BluetoothDevice, byte param2Byte, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param2Byte);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().setReport(param2BluetoothDevice, param2Byte, param2String);
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
    
    public boolean virtualUnplug(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidHost");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHidHost.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidHost.Stub.getDefaultImpl().virtualUnplug(param2BluetoothDevice);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidHost$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */