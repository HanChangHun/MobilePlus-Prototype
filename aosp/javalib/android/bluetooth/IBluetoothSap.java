package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothSap extends IInterface {
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  BluetoothDevice getClient() throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  int getState() throws RemoteException;
  
  boolean isConnected(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  public static class Default implements IBluetoothSap {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public BluetoothDevice getClient() throws RemoteException {
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
    
    public int getState() throws RemoteException {
      return 0;
    }
    
    public boolean isConnected(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothSap {
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
    
    public static IBluetoothSap asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothSap");
      return (iInterface != null && iInterface instanceof IBluetoothSap) ? (IBluetoothSap)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothSap getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothSap param1IBluetoothSap) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothSap != null) {
          Proxy.sDefaultImpl = param1IBluetoothSap;
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
        int j;
        boolean bool1;
        List<BluetoothDevice> list;
        BluetoothDevice bluetoothDevice1;
        BluetoothDevice bluetoothDevice2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 10:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothSap");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = getConnectionPolicy((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothSap");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice2 = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice2, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothSap");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            j = getConnectionState((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothSap");
            list = getDevicesMatchingConnectionStates(param1Parcel1.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 6:
            list.enforceInterface("android.bluetooth.IBluetoothSap");
            list = getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 5:
            list.enforceInterface("android.bluetooth.IBluetoothSap");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = isConnected((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 4:
            list.enforceInterface("android.bluetooth.IBluetoothSap");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = disconnect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothSap");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = connect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothSap");
            bluetoothDevice1 = getClient();
            param1Parcel2.writeNoException();
            if (bluetoothDevice1 != null) {
              param1Parcel2.writeInt(1);
              bluetoothDevice1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 1:
            break;
        } 
        bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothSap");
        int i = getState();
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(i);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothSap");
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
  
  private static class Proxy implements IBluetoothSap {
    public static IBluetoothSap sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public int getConnectionPolicy(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getConnectionPolicy(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null)
          return IBluetoothSap.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
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
    
    public boolean isConnected(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().isConnected(param1BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSap");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothSap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothSap.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothSap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */