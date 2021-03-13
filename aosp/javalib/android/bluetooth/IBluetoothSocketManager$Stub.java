package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothSocketManager {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothSocketManager";
  
  static final int TRANSACTION_connectSocket = 1;
  
  static final int TRANSACTION_createSocketChannel = 2;
  
  static final int TRANSACTION_requestMaximumTxDataLength = 3;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothSocketManager");
  }
  
  public static IBluetoothSocketManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothSocketManager");
    return (iInterface != null && iInterface instanceof IBluetoothSocketManager) ? (IBluetoothSocketManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothSocketManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "requestMaximumTxDataLength") : "createSocketChannel") : "connectSocket";
  }
  
  public static boolean setDefaultImpl(IBluetoothSocketManager paramIBluetoothSocketManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothSocketManager != null) {
        Proxy.sDefaultImpl = paramIBluetoothSocketManager;
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
    ParcelUuid parcelUuid;
    BluetoothDevice bluetoothDevice;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.bluetooth.IBluetoothSocketManager");
          return true;
        } 
        paramParcel1.enforceInterface("android.bluetooth.IBluetoothSocketManager");
        if (paramParcel1.readInt() != 0) {
          BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
        } else {
          paramParcel1 = null;
        } 
        requestMaximumTxDataLength((BluetoothDevice)paramParcel1);
        paramParcel2.writeNoException();
        return true;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothSocketManager");
      paramInt1 = paramParcel1.readInt();
      parcelUuid = (ParcelUuid)paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {
        bluetoothDevice = (BluetoothDevice)ParcelUuid.CREATOR.createFromParcel(paramParcel1);
      } else {
        bluetoothDevice = null;
      } 
      parcelFileDescriptor = createSocketChannel(paramInt1, (String)parcelUuid, (ParcelUuid)bluetoothDevice, paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      if (parcelFileDescriptor != null) {
        paramParcel2.writeInt(1);
        parcelFileDescriptor.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    parcelFileDescriptor.enforceInterface("android.bluetooth.IBluetoothSocketManager");
    if (parcelFileDescriptor.readInt() != 0) {
      bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
    } else {
      bluetoothDevice = null;
    } 
    paramInt1 = parcelFileDescriptor.readInt();
    if (parcelFileDescriptor.readInt() != 0) {
      parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
    } else {
      parcelUuid = null;
    } 
    ParcelFileDescriptor parcelFileDescriptor = connectSocket(bluetoothDevice, paramInt1, parcelUuid, parcelFileDescriptor.readInt(), parcelFileDescriptor.readInt());
    paramParcel2.writeNoException();
    if (parcelFileDescriptor != null) {
      paramParcel2.writeInt(1);
      parcelFileDescriptor.writeToParcel(paramParcel2, 1);
    } else {
      paramParcel2.writeInt(0);
    } 
    return true;
  }
  
  private static class Proxy implements IBluetoothSocketManager {
    public static IBluetoothSocketManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public ParcelFileDescriptor connectSocket(BluetoothDevice param2BluetoothDevice, int param2Int1, ParcelUuid param2ParcelUuid, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        if (param2ParcelUuid != null) {
          parcel1.writeInt(1);
          param2ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
          return IBluetoothSocketManager.Stub.getDefaultImpl().connectSocket(param2BluetoothDevice, param2Int1, param2ParcelUuid, param2Int2, param2Int3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2BluetoothDevice = null;
        } 
        return (ParcelFileDescriptor)param2BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor createSocketChannel(int param2Int1, String param2String, ParcelUuid param2ParcelUuid, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        if (param2ParcelUuid != null) {
          parcel1.writeInt(1);
          param2ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
          return IBluetoothSocketManager.Stub.getDefaultImpl().createSocketChannel(param2Int1, param2String, param2ParcelUuid, param2Int2, param2Int3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParcelFileDescriptor)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothSocketManager";
    }
    
    public void requestMaximumTxDataLength(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null) {
          IBluetoothSocketManager.Stub.getDefaultImpl().requestMaximumTxDataLength(param2BluetoothDevice);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothSocketManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */