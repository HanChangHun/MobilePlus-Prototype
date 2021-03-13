package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothGattCallback {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGattCallback";
  
  static final int TRANSACTION_onCharacteristicRead = 6;
  
  static final int TRANSACTION_onCharacteristicWrite = 7;
  
  static final int TRANSACTION_onClientConnectionState = 2;
  
  static final int TRANSACTION_onClientRegistered = 1;
  
  static final int TRANSACTION_onConfigureMTU = 13;
  
  static final int TRANSACTION_onConnectionUpdated = 14;
  
  static final int TRANSACTION_onDescriptorRead = 9;
  
  static final int TRANSACTION_onDescriptorWrite = 10;
  
  static final int TRANSACTION_onExecuteWrite = 8;
  
  static final int TRANSACTION_onNotify = 11;
  
  static final int TRANSACTION_onPhyRead = 4;
  
  static final int TRANSACTION_onPhyUpdate = 3;
  
  static final int TRANSACTION_onReadRemoteRssi = 12;
  
  static final int TRANSACTION_onSearchComplete = 5;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothGattCallback");
  }
  
  public static IBluetoothGattCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothGattCallback");
    return (iInterface != null && iInterface instanceof IBluetoothGattCallback) ? (IBluetoothGattCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothGattCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 14:
        return "onConnectionUpdated";
      case 13:
        return "onConfigureMTU";
      case 12:
        return "onReadRemoteRssi";
      case 11:
        return "onNotify";
      case 10:
        return "onDescriptorWrite";
      case 9:
        return "onDescriptorRead";
      case 8:
        return "onExecuteWrite";
      case 7:
        return "onCharacteristicWrite";
      case 6:
        return "onCharacteristicRead";
      case 5:
        return "onSearchComplete";
      case 4:
        return "onPhyRead";
      case 3:
        return "onPhyUpdate";
      case 2:
        return "onClientConnectionState";
      case 1:
        break;
    } 
    return "onClientRegistered";
  }
  
  public static boolean setDefaultImpl(IBluetoothGattCallback paramIBluetoothGattCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothGattCallback != null) {
        Proxy.sDefaultImpl = paramIBluetoothGattCallback;
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
      boolean bool;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 14:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onConnectionUpdated(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 13:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onConfigureMTU(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 12:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onReadRemoteRssi(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 11:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onNotify(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.createByteArray());
          return true;
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onDescriptorWrite(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onDescriptorRead(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onExecuteWrite(paramParcel1.readString(), paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onCharacteristicWrite(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onCharacteristicRead(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onSearchComplete(paramParcel1.readString(), paramParcel1.createTypedArrayList(BluetoothGattService.CREATOR), paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onPhyRead(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          onPhyUpdate(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
          paramInt2 = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          onClientConnectionState(paramInt2, paramInt1, bool, paramParcel1.readString());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
      onClientRegistered(paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothGattCallback");
    return true;
  }
  
  private static class Proxy implements IBluetoothGattCallback {
    public static IBluetoothGattCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothGattCallback";
    }
    
    public void onCharacteristicRead(String param2String, int param2Int1, int param2Int2, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(6, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onCharacteristicRead(param2String, param2Int1, param2Int2, param2ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCharacteristicWrite(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(7, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onCharacteristicWrite(param2String, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onClientConnectionState(int param2Int1, int param2Int2, boolean param2Boolean, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onClientConnectionState(param2Int1, param2Int2, param2Boolean, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onClientRegistered(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onClientRegistered(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onConfigureMTU(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(13, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onConfigureMTU(param2String, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onConnectionUpdated(String param2String, int param2Int1, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        parcel.writeInt(param2Int4);
        if (!this.mRemote.transact(14, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onConnectionUpdated(param2String, param2Int1, param2Int2, param2Int3, param2Int4);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorRead(String param2String, int param2Int1, int param2Int2, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(9, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onDescriptorRead(param2String, param2Int1, param2Int2, param2ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorWrite(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(10, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onDescriptorWrite(param2String, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onExecuteWrite(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onExecuteWrite(param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNotify(String param2String, int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        parcel.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onNotify(param2String, param2Int, param2ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPhyRead(String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onPhyRead(param2String, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPhyUpdate(String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onPhyUpdate(param2String, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onReadRemoteRssi(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(12, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onReadRemoteRssi(param2String, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSearchComplete(String param2String, List<BluetoothGattService> param2List, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param2String);
        parcel.writeTypedList(param2List);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onSearchComplete(param2String, param2List, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */