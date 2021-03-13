package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothPan extends IInterface {
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  boolean isTetheringOn() throws RemoteException;
  
  void setBluetoothTethering(boolean paramBoolean, String paramString) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  public static class Default implements IBluetoothPan {
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
    
    public boolean isTetheringOn() throws RemoteException {
      return false;
    }
    
    public void setBluetoothTethering(boolean param1Boolean, String param1String) throws RemoteException {}
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothPan {
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
    
    public static IBluetoothPan asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothPan");
      return (iInterface != null && iInterface instanceof IBluetoothPan) ? (IBluetoothPan)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothPan getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothPan param1IBluetoothPan) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothPan != null) {
          Proxy.sDefaultImpl = param1IBluetoothPan;
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
        List<BluetoothDevice> list;
        BluetoothDevice bluetoothDevice;
        boolean bool;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPan");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPan");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            i = getConnectionState((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothPan");
            list = getDevicesMatchingConnectionStates(param1Parcel1.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 5:
            list.enforceInterface("android.bluetooth.IBluetoothPan");
            list = getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 4:
            list.enforceInterface("android.bluetooth.IBluetoothPan");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = disconnect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothPan");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = connect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothPan");
            if (list.readInt() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            setBluetoothTethering(bool, list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.bluetooth.IBluetoothPan");
        boolean bool1 = isTetheringOn();
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothPan");
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
  
  private static class Proxy implements IBluetoothPan {
    public static IBluetoothPan sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null)
          return IBluetoothPan.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null)
          return IBluetoothPan.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
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
    
    public void setBluetoothTethering(boolean param1Boolean, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          IBluetoothPan.Stub.getDefaultImpl().setBluetoothTethering(param1Boolean, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothPan");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothPan.Stub.getDefaultImpl() != null) {
          bool = IBluetoothPan.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothPan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */