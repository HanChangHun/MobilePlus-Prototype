package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothHidDevice extends IInterface {
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  String getUserAppName() throws RemoteException;
  
  boolean registerApp(BluetoothHidDeviceAppSdpSettings paramBluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings paramBluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback paramIBluetoothHidDeviceCallback) throws RemoteException;
  
  boolean replyReport(BluetoothDevice paramBluetoothDevice, byte paramByte1, byte paramByte2, byte[] paramArrayOfbyte) throws RemoteException;
  
  boolean reportError(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException;
  
  boolean sendReport(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  boolean unplug(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean unregisterApp() throws RemoteException;
  
  public static class Default implements IBluetoothHidDevice {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      return null;
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public String getUserAppName() throws RemoteException {
      return null;
    }
    
    public boolean registerApp(BluetoothHidDeviceAppSdpSettings param1BluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings param1BluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings param1BluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback param1IBluetoothHidDeviceCallback) throws RemoteException {
      return false;
    }
    
    public boolean replyReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, byte[] param1ArrayOfbyte) throws RemoteException {
      return false;
    }
    
    public boolean reportError(BluetoothDevice param1BluetoothDevice, byte param1Byte) throws RemoteException {
      return false;
    }
    
    public boolean sendReport(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {
      return false;
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean unplug(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean unregisterApp() throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothHidDevice {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHidDevice";
    
    static final int TRANSACTION_connect = 7;
    
    static final int TRANSACTION_disconnect = 8;
    
    static final int TRANSACTION_getConnectedDevices = 9;
    
    static final int TRANSACTION_getConnectionState = 11;
    
    static final int TRANSACTION_getDevicesMatchingConnectionStates = 10;
    
    static final int TRANSACTION_getUserAppName = 12;
    
    static final int TRANSACTION_registerApp = 1;
    
    static final int TRANSACTION_replyReport = 4;
    
    static final int TRANSACTION_reportError = 5;
    
    static final int TRANSACTION_sendReport = 3;
    
    static final int TRANSACTION_setConnectionPolicy = 13;
    
    static final int TRANSACTION_unplug = 6;
    
    static final int TRANSACTION_unregisterApp = 2;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothHidDevice");
    }
    
    public static IBluetoothHidDevice asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothHidDevice");
      return (iInterface != null && iInterface instanceof IBluetoothHidDevice) ? (IBluetoothHidDevice)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothHidDevice getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 13:
          return "setConnectionPolicy";
        case 12:
          return "getUserAppName";
        case 11:
          return "getConnectionState";
        case 10:
          return "getDevicesMatchingConnectionStates";
        case 9:
          return "getConnectedDevices";
        case 8:
          return "disconnect";
        case 7:
          return "connect";
        case 6:
          return "unplug";
        case 5:
          return "reportError";
        case 4:
          return "replyReport";
        case 3:
          return "sendReport";
        case 2:
          return "unregisterApp";
        case 1:
          break;
      } 
      return "registerApp";
    }
    
    public static boolean setDefaultImpl(IBluetoothHidDevice param1IBluetoothHidDevice) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothHidDevice != null) {
          Proxy.sDefaultImpl = param1IBluetoothHidDevice;
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
        boolean bool2;
        int i;
        String str;
        List<BluetoothDevice> list;
        BluetoothDevice bluetoothDevice;
        BluetoothHidDeviceAppQosSettings bluetoothHidDeviceAppQosSettings1;
        BluetoothHidDeviceAppQosSettings bluetoothHidDeviceAppQosSettings2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 13:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            str = getUserAppName();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str);
            return true;
          case 11:
            str.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (str.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str);
            } else {
              str = null;
            } 
            i = getConnectionState((BluetoothDevice)str);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 10:
            str.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            list = getDevicesMatchingConnectionStates(str.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 9:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            list = getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 8:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = disconnect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 7:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = connect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 6:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = unplug((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 5:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (list.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              bluetoothDevice = null;
            } 
            bool1 = reportError(bluetoothDevice, list.readByte());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 4:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (list.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              bluetoothDevice = null;
            } 
            bool1 = replyReport(bluetoothDevice, list.readByte(), list.readByte(), list.createByteArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            if (list.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              bluetoothDevice = null;
            } 
            bool1 = sendReport(bluetoothDevice, list.readInt(), list.createByteArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
            bool1 = unregisterApp();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.bluetooth.IBluetoothHidDevice");
        if (list.readInt() != 0) {
          BluetoothHidDeviceAppSdpSettings bluetoothHidDeviceAppSdpSettings = (BluetoothHidDeviceAppSdpSettings)BluetoothHidDeviceAppSdpSettings.CREATOR.createFromParcel((Parcel)list);
        } else {
          bluetoothDevice = null;
        } 
        if (list.readInt() != 0) {
          bluetoothHidDeviceAppQosSettings1 = (BluetoothHidDeviceAppQosSettings)BluetoothHidDeviceAppQosSettings.CREATOR.createFromParcel((Parcel)list);
        } else {
          bluetoothHidDeviceAppQosSettings1 = null;
        } 
        if (list.readInt() != 0) {
          bluetoothHidDeviceAppQosSettings2 = (BluetoothHidDeviceAppQosSettings)BluetoothHidDeviceAppQosSettings.CREATOR.createFromParcel((Parcel)list);
        } else {
          bluetoothHidDeviceAppQosSettings2 = null;
        } 
        boolean bool1 = registerApp((BluetoothHidDeviceAppSdpSettings)bluetoothDevice, bluetoothHidDeviceAppQosSettings1, bluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback.Stub.asInterface(list.readStrongBinder()));
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothHidDevice");
      return true;
    }
    
    private static class Proxy implements IBluetoothHidDevice {
      public static IBluetoothHidDevice sDefaultImpl;
      
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
            return IBluetoothHidDevice.Stub.getDefaultImpl().getConnectedDevices(); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
            return IBluetoothHidDevice.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
            return IBluetoothHidDevice.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothHidDevice";
      }
      
      public String getUserAppName() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
            return IBluetoothHidDevice.Stub.getDefaultImpl().getUserAppName(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean registerApp(BluetoothHidDeviceAppSdpSettings param2BluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings param2BluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings param2BluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback param2IBluetoothHidDeviceCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothHidDeviceAppSdpSettings != null) {
            parcel1.writeInt(1);
            param2BluetoothHidDeviceAppSdpSettings.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2BluetoothHidDeviceAppQosSettings1 != null) {
            parcel1.writeInt(1);
            param2BluetoothHidDeviceAppQosSettings1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2BluetoothHidDeviceAppQosSettings2 != null) {
            parcel1.writeInt(1);
            param2BluetoothHidDeviceAppQosSettings2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2IBluetoothHidDeviceCallback != null) {
            iBinder = param2IBluetoothHidDeviceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().registerApp(param2BluetoothHidDeviceAppSdpSettings, param2BluetoothHidDeviceAppQosSettings1, param2BluetoothHidDeviceAppQosSettings2, param2IBluetoothHidDeviceCallback);
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
      
      public boolean replyReport(BluetoothDevice param2BluetoothDevice, byte param2Byte1, byte param2Byte2, byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeByte(param2Byte1);
          parcel1.writeByte(param2Byte2);
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().replyReport(param2BluetoothDevice, param2Byte1, param2Byte2, param2ArrayOfbyte);
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
      
      public boolean reportError(BluetoothDevice param2BluetoothDevice, byte param2Byte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeByte(param2Byte);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().reportError(param2BluetoothDevice, param2Byte);
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
      
      public boolean sendReport(BluetoothDevice param2BluetoothDevice, int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().sendReport(param2BluetoothDevice, param2Int, param2ArrayOfbyte);
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
      
      public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
      
      public boolean unplug(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().unplug(param2BluetoothDevice);
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
      
      public boolean unregisterApp() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(2, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHidDevice.Stub.getDefaultImpl().unregisterApp();
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
    }
  }
  
  private static class Proxy implements IBluetoothHidDevice {
    public static IBluetoothHidDevice sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
          return IBluetoothHidDevice.Stub.getDefaultImpl().getConnectedDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
          return IBluetoothHidDevice.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
          return IBluetoothHidDevice.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHidDevice";
    }
    
    public String getUserAppName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null)
          return IBluetoothHidDevice.Stub.getDefaultImpl().getUserAppName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean registerApp(BluetoothHidDeviceAppSdpSettings param1BluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings param1BluetoothHidDeviceAppQosSettings1, BluetoothHidDeviceAppQosSettings param1BluetoothHidDeviceAppQosSettings2, IBluetoothHidDeviceCallback param1IBluetoothHidDeviceCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothHidDeviceAppSdpSettings != null) {
          parcel1.writeInt(1);
          param1BluetoothHidDeviceAppSdpSettings.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1BluetoothHidDeviceAppQosSettings1 != null) {
          parcel1.writeInt(1);
          param1BluetoothHidDeviceAppQosSettings1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1BluetoothHidDeviceAppQosSettings2 != null) {
          parcel1.writeInt(1);
          param1BluetoothHidDeviceAppQosSettings2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IBluetoothHidDeviceCallback != null) {
          iBinder = param1IBluetoothHidDeviceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().registerApp(param1BluetoothHidDeviceAppSdpSettings, param1BluetoothHidDeviceAppQosSettings1, param1BluetoothHidDeviceAppQosSettings2, param1IBluetoothHidDeviceCallback);
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
    
    public boolean replyReport(BluetoothDevice param1BluetoothDevice, byte param1Byte1, byte param1Byte2, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte1);
        parcel1.writeByte(param1Byte2);
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().replyReport(param1BluetoothDevice, param1Byte1, param1Byte2, param1ArrayOfbyte);
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
    
    public boolean reportError(BluetoothDevice param1BluetoothDevice, byte param1Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().reportError(param1BluetoothDevice, param1Byte);
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
    
    public boolean sendReport(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().sendReport(param1BluetoothDevice, param1Int, param1ArrayOfbyte);
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
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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
    
    public boolean unplug(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().unplug(param1BluetoothDevice);
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
    
    public boolean unregisterApp() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHidDevice");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IBluetoothHidDevice.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHidDevice.Stub.getDefaultImpl().unregisterApp();
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHidDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */