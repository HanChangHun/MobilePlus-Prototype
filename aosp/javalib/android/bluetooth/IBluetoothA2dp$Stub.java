package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothA2dp {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothA2dp";
  
  static final int TRANSACTION_connect = 1;
  
  static final int TRANSACTION_disableOptionalCodecs = 16;
  
  static final int TRANSACTION_disconnect = 2;
  
  static final int TRANSACTION_enableOptionalCodecs = 15;
  
  static final int TRANSACTION_getActiveDevice = 7;
  
  static final int TRANSACTION_getCodecStatus = 13;
  
  static final int TRANSACTION_getConnectedDevices = 3;
  
  static final int TRANSACTION_getConnectionPolicy = 9;
  
  static final int TRANSACTION_getConnectionState = 5;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
  
  static final int TRANSACTION_getOptionalCodecsEnabled = 18;
  
  static final int TRANSACTION_getPriority = 20;
  
  static final int TRANSACTION_isA2dpPlaying = 12;
  
  static final int TRANSACTION_isAvrcpAbsoluteVolumeSupported = 10;
  
  static final int TRANSACTION_setActiveDevice = 6;
  
  static final int TRANSACTION_setAvrcpAbsoluteVolume = 11;
  
  static final int TRANSACTION_setCodecConfigPreference = 14;
  
  static final int TRANSACTION_setConnectionPolicy = 8;
  
  static final int TRANSACTION_setOptionalCodecsEnabled = 19;
  
  static final int TRANSACTION_supportsOptionalCodecs = 17;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothA2dp");
  }
  
  public static IBluetoothA2dp asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothA2dp");
    return (iInterface != null && iInterface instanceof IBluetoothA2dp) ? (IBluetoothA2dp)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothA2dp getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 20:
        return "getPriority";
      case 19:
        return "setOptionalCodecsEnabled";
      case 18:
        return "getOptionalCodecsEnabled";
      case 17:
        return "supportsOptionalCodecs";
      case 16:
        return "disableOptionalCodecs";
      case 15:
        return "enableOptionalCodecs";
      case 14:
        return "setCodecConfigPreference";
      case 13:
        return "getCodecStatus";
      case 12:
        return "isA2dpPlaying";
      case 11:
        return "setAvrcpAbsoluteVolume";
      case 10:
        return "isAvrcpAbsoluteVolumeSupported";
      case 9:
        return "getConnectionPolicy";
      case 8:
        return "setConnectionPolicy";
      case 7:
        return "getActiveDevice";
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
  
  public static boolean setDefaultImpl(IBluetoothA2dp paramIBluetoothA2dp) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothA2dp != null) {
        Proxy.sDefaultImpl = paramIBluetoothA2dp;
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
      BluetoothCodecStatus bluetoothCodecStatus;
      BluetoothDevice bluetoothDevice1;
      List<BluetoothDevice> list;
      BluetoothDevice bluetoothDevice2;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 20:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = getPriority((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 19:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          setOptionalCodecsEnabled((BluetoothDevice)paramParcel2, paramParcel1.readInt());
          return true;
        case 18:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = getOptionalCodecsEnabled((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 17:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          paramInt1 = supportsOptionalCodecs((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 16:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          disableOptionalCodecs((BluetoothDevice)paramParcel1);
          return true;
        case 15:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          enableOptionalCodecs((BluetoothDevice)paramParcel1);
          return true;
        case 14:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            BluetoothCodecConfig bluetoothCodecConfig = (BluetoothCodecConfig)BluetoothCodecConfig.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setCodecConfigPreference((BluetoothDevice)paramParcel2, (BluetoothCodecConfig)paramParcel1);
          return true;
        case 13:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bluetoothCodecStatus = getCodecStatus((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          if (bluetoothCodecStatus != null) {
            paramParcel2.writeInt(1);
            bluetoothCodecStatus.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 12:
          bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (bluetoothCodecStatus.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothCodecStatus);
          } else {
            bluetoothCodecStatus = null;
          } 
          bool3 = isA2dpPlaying((BluetoothDevice)bluetoothCodecStatus);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 11:
          bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
          setAvrcpAbsoluteVolume(bluetoothCodecStatus.readInt());
          return true;
        case 10:
          bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
          bool3 = isAvrcpAbsoluteVolumeSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 9:
          bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (bluetoothCodecStatus.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothCodecStatus);
          } else {
            bluetoothCodecStatus = null;
          } 
          j = getConnectionPolicy((BluetoothDevice)bluetoothCodecStatus);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 8:
          bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (bluetoothCodecStatus.readInt() != 0) {
            bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothCodecStatus);
          } else {
            bluetoothDevice2 = null;
          } 
          bool2 = setConnectionPolicy(bluetoothDevice2, bluetoothCodecStatus.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 7:
          bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
          bluetoothDevice1 = getActiveDevice();
          paramParcel2.writeNoException();
          if (bluetoothDevice1 != null) {
            paramParcel2.writeInt(1);
            bluetoothDevice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 6:
          bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (bluetoothDevice1.readInt() != 0) {
            bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
          } else {
            bluetoothDevice1 = null;
          } 
          bool2 = setActiveDevice(bluetoothDevice1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 5:
          bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (bluetoothDevice1.readInt() != 0) {
            bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
          } else {
            bluetoothDevice1 = null;
          } 
          i = getConnectionState(bluetoothDevice1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 4:
          bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothA2dp");
          list = getDevicesMatchingConnectionStates(bluetoothDevice1.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 3:
          list.enforceInterface("android.bluetooth.IBluetoothA2dp");
          list = getConnectedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 2:
          list.enforceInterface("android.bluetooth.IBluetoothA2dp");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
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
      list.enforceInterface("android.bluetooth.IBluetoothA2dp");
      if (list.readInt() != 0) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
      } else {
        list = null;
      } 
      boolean bool1 = connect((BluetoothDevice)list);
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothA2dp");
    return true;
  }
  
  private static class Proxy implements IBluetoothA2dp {
    public static IBluetoothA2dp sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
    
    public void disableOptionalCodecs(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(16, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().disableOptionalCodecs(param2BluetoothDevice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean disconnect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
    
    public void enableOptionalCodecs(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(15, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().enableOptionalCodecs(param2BluetoothDevice);
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
    
    public BluetoothCodecStatus getCodecStatus(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getCodecStatus(param2BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          BluetoothCodecStatus bluetoothCodecStatus = (BluetoothCodecStatus)BluetoothCodecStatus.CREATOR.createFromParcel(parcel2);
        } else {
          param2BluetoothDevice = null;
        } 
        return (BluetoothCodecStatus)param2BluetoothDevice;
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
    
    public int getConnectionPolicy(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
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
    
    public int getOptionalCodecsEnabled(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getOptionalCodecsEnabled(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPriority(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getPriority(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isA2dpPlaying(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().isA2dpPlaying(param2BluetoothDevice);
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
    
    public boolean setActiveDevice(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().setActiveDevice(param2BluetoothDevice);
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
    
    public void setAvrcpAbsoluteVolume(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().setAvrcpAbsoluteVolume(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setCodecConfigPreference(BluetoothDevice param2BluetoothDevice, BluetoothCodecConfig param2BluetoothCodecConfig) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2BluetoothCodecConfig != null) {
          parcel.writeInt(1);
          param2BluetoothCodecConfig.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().setCodecConfigPreference(param2BluetoothDevice, param2BluetoothCodecConfig);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
    
    public void setOptionalCodecsEnabled(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(19, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().setOptionalCodecsEnabled(param2BluetoothDevice, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public int supportsOptionalCodecs(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().supportsOptionalCodecs(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothA2dp$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */