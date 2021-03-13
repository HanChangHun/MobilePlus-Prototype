package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothPbap {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothPbap";
  
  static final int TRANSACTION_disconnect = 4;
  
  static final int TRANSACTION_getConnectedDevices = 1;
  
  static final int TRANSACTION_getConnectionState = 3;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 2;
  
  static final int TRANSACTION_setConnectionPolicy = 5;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothPbap");
  }
  
  public static IBluetoothPbap asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothPbap");
    return (iInterface != null && iInterface instanceof IBluetoothPbap) ? (IBluetoothPbap)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothPbap getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "setConnectionPolicy") : "disconnect") : "getConnectionState") : "getDevicesMatchingConnectionStates") : "getConnectedDevices";
  }
  
  public static boolean setDefaultImpl(IBluetoothPbap paramIBluetoothPbap) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothPbap != null) {
        Proxy.sDefaultImpl = paramIBluetoothPbap;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            BluetoothDevice bluetoothDevice;
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.bluetooth.IBluetoothPbap");
              return true;
            } 
            paramParcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
            if (paramParcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
            } else {
              bluetoothDevice = null;
            } 
            boolean bool = setConnectionPolicy(bluetoothDevice, paramParcel1.readInt());
            paramParcel2.writeNoException();
            paramParcel2.writeInt(bool);
            return true;
          } 
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          disconnect((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        } 
        paramParcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
        if (paramParcel1.readInt() != 0) {
          BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
        } else {
          paramParcel1 = null;
        } 
        paramInt1 = getConnectionState((BluetoothDevice)paramParcel1);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothPbap");
      list = getDevicesMatchingConnectionStates(paramParcel1.createIntArray());
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(list);
      return true;
    } 
    list.enforceInterface("android.bluetooth.IBluetoothPbap");
    List<BluetoothDevice> list = getConnectedDevices();
    paramParcel2.writeNoException();
    paramParcel2.writeTypedList(list);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothPbap$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */