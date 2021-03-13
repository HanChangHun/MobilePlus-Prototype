package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothPbap extends IInterface {
  void disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  public static class Default implements IBluetoothPbap {
    public IBinder asBinder() {
      return null;
    }
    
    public void disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {}
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      return null;
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothPbap {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothPbap";
    
    static final int TRANSACTION_disconnect = 4;
    
    static final int TRANSACTION_getConnectedDevices = 1;
    
    static final int TRANSACTION_getConnectionState = 3;
    
    static final int TRANSACTION_getDevicesMatchingConnectionStates = 2;
    
    static final int TRANSACTION_setConnectionPolicy = 5;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothPbap");
    }
    
    public static IBluetoothPbap asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothPbap");
      return (iInterface != null && iInterface instanceof IBluetoothPbap) ? (IBluetoothPbap)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothPbap getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "setConnectionPolicy") : "disconnect") : "getConnectionState") : "getDevicesMatchingConnectionStates") : "getConnectedDevices";
    }
    
    public static boolean setDefaultImpl(IBluetoothPbap param1IBluetoothPbap) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothPbap != null) {
          Proxy.sDefaultImpl = param1IBluetoothPbap;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              BluetoothDevice bluetoothDevice;
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.bluetooth.IBluetoothPbap");
                return true;
              } 
              param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
              if (param1Parcel1.readInt() != 0) {
                bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
              } else {
                bluetoothDevice = null;
              } 
              boolean bool = setConnectionPolicy(bluetoothDevice, param1Parcel1.readInt());
              param1Parcel2.writeNoException();
              param1Parcel2.writeInt(bool);
              return true;
            } 
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            disconnect((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          } 
          param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
          if (param1Parcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
          } else {
            param1Parcel1 = null;
          } 
          param1Int1 = getConnectionState((BluetoothDevice)param1Parcel1);
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(param1Int1);
          return true;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
        list = getDevicesMatchingConnectionStates(param1Parcel1.createIntArray());
        param1Parcel2.writeNoException();
        param1Parcel2.writeTypedList(list);
        return true;
      } 
      list.enforceInterface("android.bluetooth.IBluetoothPbap");
      List<BluetoothDevice> list = getConnectedDevices();
      param1Parcel2.writeNoException();
      param1Parcel2.writeTypedList(list);
      return true;
    }
    
    private static class Proxy implements IBluetoothPbap {
      public static IBluetoothPbap sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void disconnect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null) {
            IBluetoothPbap.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null)
            return IBluetoothPbap.Stub.getDefaultImpl().getConnectedDevices(); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null)
            return IBluetoothPbap.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null)
            return IBluetoothPbap.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothPbap";
      }
      
      public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null) {
            bool = IBluetoothPbap.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
  
  private static class Proxy implements IBluetoothPbap {
    public static IBluetoothPbap sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null) {
          IBluetoothPbap.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null)
          return IBluetoothPbap.Stub.getDefaultImpl().getConnectedDevices(); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null)
          return IBluetoothPbap.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null)
          return IBluetoothPbap.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothPbap";
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPbap");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothPbap.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPbap.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothPbap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */