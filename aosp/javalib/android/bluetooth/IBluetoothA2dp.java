package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothA2dp extends IInterface {
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  void disableOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  void enableOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  BluetoothDevice getActiveDevice() throws RemoteException;
  
  BluetoothCodecStatus getCodecStatus(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  int getOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getPriority(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean isA2dpPlaying(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException;
  
  boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  void setAvrcpAbsoluteVolume(int paramInt) throws RemoteException;
  
  void setCodecConfigPreference(BluetoothDevice paramBluetoothDevice, BluetoothCodecConfig paramBluetoothCodecConfig) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  void setOptionalCodecsEnabled(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  int supportsOptionalCodecs(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  public static class Default implements IBluetoothA2dp {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public void disableOptionalCodecs(BluetoothDevice param1BluetoothDevice) throws RemoteException {}
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public void enableOptionalCodecs(BluetoothDevice param1BluetoothDevice) throws RemoteException {}
    
    public BluetoothDevice getActiveDevice() throws RemoteException {
      return null;
    }
    
    public BluetoothCodecStatus getCodecStatus(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return null;
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      return null;
    }
    
    public int getConnectionPolicy(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public int getOptionalCodecsEnabled(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public int getPriority(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public boolean isA2dpPlaying(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean isAvrcpAbsoluteVolumeSupported() throws RemoteException {
      return false;
    }
    
    public boolean setActiveDevice(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public void setAvrcpAbsoluteVolume(int param1Int) throws RemoteException {}
    
    public void setCodecConfigPreference(BluetoothDevice param1BluetoothDevice, BluetoothCodecConfig param1BluetoothCodecConfig) throws RemoteException {}
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public void setOptionalCodecsEnabled(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {}
    
    public int supportsOptionalCodecs(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothA2dp {
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
    
    public static IBluetoothA2dp asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothA2dp");
      return (iInterface != null && iInterface instanceof IBluetoothA2dp) ? (IBluetoothA2dp)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothA2dp getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothA2dp param1IBluetoothA2dp) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothA2dp != null) {
          Proxy.sDefaultImpl = param1IBluetoothA2dp;
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
        boolean bool3;
        int j;
        boolean bool2;
        int i;
        BluetoothCodecStatus bluetoothCodecStatus;
        BluetoothDevice bluetoothDevice1;
        List<BluetoothDevice> list;
        BluetoothDevice bluetoothDevice2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 20:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = getPriority((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 19:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            setOptionalCodecsEnabled((BluetoothDevice)param1Parcel2, param1Parcel1.readInt());
            return true;
          case 18:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = getOptionalCodecsEnabled((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 17:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = supportsOptionalCodecs((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 16:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            disableOptionalCodecs((BluetoothDevice)param1Parcel1);
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            enableOptionalCodecs((BluetoothDevice)param1Parcel1);
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              BluetoothCodecConfig bluetoothCodecConfig = (BluetoothCodecConfig)BluetoothCodecConfig.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            setCodecConfigPreference((BluetoothDevice)param1Parcel2, (BluetoothCodecConfig)param1Parcel1);
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bluetoothCodecStatus = getCodecStatus((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            if (bluetoothCodecStatus != null) {
              param1Parcel2.writeInt(1);
              bluetoothCodecStatus.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
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
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 11:
            bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
            setAvrcpAbsoluteVolume(bluetoothCodecStatus.readInt());
            return true;
          case 10:
            bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
            bool3 = isAvrcpAbsoluteVolumeSupported();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 9:
            bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (bluetoothCodecStatus.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothCodecStatus);
            } else {
              bluetoothCodecStatus = null;
            } 
            j = getConnectionPolicy((BluetoothDevice)bluetoothCodecStatus);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 8:
            bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (bluetoothCodecStatus.readInt() != 0) {
              bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothCodecStatus);
            } else {
              bluetoothDevice2 = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice2, bluetoothCodecStatus.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 7:
            bluetoothCodecStatus.enforceInterface("android.bluetooth.IBluetoothA2dp");
            bluetoothDevice1 = getActiveDevice();
            param1Parcel2.writeNoException();
            if (bluetoothDevice1 != null) {
              param1Parcel2.writeInt(1);
              bluetoothDevice1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
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
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 5:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            i = getConnectionState(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 4:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothA2dp");
            list = getDevicesMatchingConnectionStates(bluetoothDevice1.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothA2dp");
            list = getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothA2dp");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = disconnect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
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
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothA2dp");
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
  
  private static class Proxy implements IBluetoothA2dp {
    public static IBluetoothA2dp sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
    
    public void disableOptionalCodecs(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(16, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().disableOptionalCodecs(param1BluetoothDevice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public void enableOptionalCodecs(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(15, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().enableOptionalCodecs(param1BluetoothDevice);
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
    
    public BluetoothCodecStatus getCodecStatus(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getCodecStatus(param1BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          BluetoothCodecStatus bluetoothCodecStatus = (BluetoothCodecStatus)BluetoothCodecStatus.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (BluetoothCodecStatus)param1BluetoothDevice;
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
    
    public int getConnectionPolicy(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getConnectionPolicy(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
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
    
    public int getOptionalCodecsEnabled(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getOptionalCodecsEnabled(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPriority(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().getPriority(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isA2dpPlaying(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().isA2dpPlaying(param1BluetoothDevice);
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
    
    public boolean setActiveDevice(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().setActiveDevice(param1BluetoothDevice);
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
    
    public void setAvrcpAbsoluteVolume(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().setAvrcpAbsoluteVolume(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setCodecConfigPreference(BluetoothDevice param1BluetoothDevice, BluetoothCodecConfig param1BluetoothCodecConfig) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1BluetoothCodecConfig != null) {
          parcel.writeInt(1);
          param1BluetoothCodecConfig.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().setCodecConfigPreference(param1BluetoothDevice, param1BluetoothCodecConfig);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dp.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setOptionalCodecsEnabled(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(19, parcel, null, 1) && IBluetoothA2dp.Stub.getDefaultImpl() != null) {
          IBluetoothA2dp.Stub.getDefaultImpl().setOptionalCodecsEnabled(param1BluetoothDevice, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public int supportsOptionalCodecs(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dp");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothA2dp.Stub.getDefaultImpl() != null)
          return IBluetoothA2dp.Stub.getDefaultImpl().supportsOptionalCodecs(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothA2dp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */