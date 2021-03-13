package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothA2dpSink extends IInterface {
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  BluetoothAudioConfig getAudioConfig(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  boolean isA2dpPlaying(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  public static class Default implements IBluetoothA2dpSink {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public BluetoothAudioConfig getAudioConfig(BluetoothDevice param1BluetoothDevice) throws RemoteException {
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
    
    public boolean isA2dpPlaying(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothA2dpSink {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothA2dpSink";
    
    static final int TRANSACTION_connect = 1;
    
    static final int TRANSACTION_disconnect = 2;
    
    static final int TRANSACTION_getAudioConfig = 6;
    
    static final int TRANSACTION_getConnectedDevices = 3;
    
    static final int TRANSACTION_getConnectionPolicy = 8;
    
    static final int TRANSACTION_getConnectionState = 5;
    
    static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
    
    static final int TRANSACTION_isA2dpPlaying = 9;
    
    static final int TRANSACTION_setConnectionPolicy = 7;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothA2dpSink");
    }
    
    public static IBluetoothA2dpSink asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothA2dpSink");
      return (iInterface != null && iInterface instanceof IBluetoothA2dpSink) ? (IBluetoothA2dpSink)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothA2dpSink getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 9:
          return "isA2dpPlaying";
        case 8:
          return "getConnectionPolicy";
        case 7:
          return "setConnectionPolicy";
        case 6:
          return "getAudioConfig";
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
    
    public static boolean setDefaultImpl(IBluetoothA2dpSink param1IBluetoothA2dpSink) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothA2dpSink != null) {
          Proxy.sDefaultImpl = param1IBluetoothA2dpSink;
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
        BluetoothAudioConfig bluetoothAudioConfig;
        List<BluetoothDevice> list;
        BluetoothDevice bluetoothDevice;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bool3 = isA2dpPlaying((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            j = getConnectionPolicy((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bluetoothAudioConfig = getAudioConfig((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            if (bluetoothAudioConfig != null) {
              param1Parcel2.writeInt(1);
              bluetoothAudioConfig.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            bluetoothAudioConfig.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            if (bluetoothAudioConfig.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothAudioConfig);
            } else {
              bluetoothAudioConfig = null;
            } 
            i = getConnectionState((BluetoothDevice)bluetoothAudioConfig);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 4:
            bluetoothAudioConfig.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            list = getDevicesMatchingConnectionStates(bluetoothAudioConfig.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            list = getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
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
        list.enforceInterface("android.bluetooth.IBluetoothA2dpSink");
        if (list.readInt() != 0) {
          BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
        } else {
          list = null;
        } 
        boolean bool1 = connect((BluetoothDevice)list);
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothA2dpSink");
      return true;
    }
    
    private static class Proxy implements IBluetoothA2dpSink {
      public static IBluetoothA2dpSink sDefaultImpl;
      
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
            bool = IBluetoothA2dpSink.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
            bool = IBluetoothA2dpSink.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
      
      public BluetoothAudioConfig getAudioConfig(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
            return IBluetoothA2dpSink.Stub.getDefaultImpl().getAudioConfig(param2BluetoothDevice); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            BluetoothAudioConfig bluetoothAudioConfig = (BluetoothAudioConfig)BluetoothAudioConfig.CREATOR.createFromParcel(parcel2);
          } else {
            param2BluetoothDevice = null;
          } 
          return (BluetoothAudioConfig)param2BluetoothDevice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
            return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectedDevices(); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
            return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
            return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
            return IBluetoothA2dpSink.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothA2dpSink";
      }
      
      public boolean isA2dpPlaying(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
            bool = IBluetoothA2dpSink.Stub.getDefaultImpl().isA2dpPlaying(param2BluetoothDevice);
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
            bool = IBluetoothA2dpSink.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
  
  private static class Proxy implements IBluetoothA2dpSink {
    public static IBluetoothA2dpSink sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dpSink.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dpSink.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public BluetoothAudioConfig getAudioConfig(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
          return IBluetoothA2dpSink.Stub.getDefaultImpl().getAudioConfig(param1BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          BluetoothAudioConfig bluetoothAudioConfig = (BluetoothAudioConfig)BluetoothAudioConfig.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (BluetoothAudioConfig)param1BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
          return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectedDevices(); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
          return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectionPolicy(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
          return IBluetoothA2dpSink.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null)
          return IBluetoothA2dpSink.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothA2dpSink";
    }
    
    public boolean isA2dpPlaying(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dpSink.Stub.getDefaultImpl().isA2dpPlaying(param1BluetoothDevice);
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
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothA2dpSink");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothA2dpSink.Stub.getDefaultImpl() != null) {
          bool = IBluetoothA2dpSink.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothA2dpSink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */