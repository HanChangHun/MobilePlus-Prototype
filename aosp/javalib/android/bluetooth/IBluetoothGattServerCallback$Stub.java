package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothGattServerCallback {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGattServerCallback";
  
  static final int TRANSACTION_onCharacteristicReadRequest = 4;
  
  static final int TRANSACTION_onCharacteristicWriteRequest = 6;
  
  static final int TRANSACTION_onConnectionUpdated = 13;
  
  static final int TRANSACTION_onDescriptorReadRequest = 5;
  
  static final int TRANSACTION_onDescriptorWriteRequest = 7;
  
  static final int TRANSACTION_onExecuteWrite = 8;
  
  static final int TRANSACTION_onMtuChanged = 10;
  
  static final int TRANSACTION_onNotificationSent = 9;
  
  static final int TRANSACTION_onPhyRead = 12;
  
  static final int TRANSACTION_onPhyUpdate = 11;
  
  static final int TRANSACTION_onServerConnectionState = 2;
  
  static final int TRANSACTION_onServerRegistered = 1;
  
  static final int TRANSACTION_onServiceAdded = 3;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothGattServerCallback");
  }
  
  public static IBluetoothGattServerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothGattServerCallback");
    return (iInterface != null && iInterface instanceof IBluetoothGattServerCallback) ? (IBluetoothGattServerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothGattServerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 13:
        return "onConnectionUpdated";
      case 12:
        return "onPhyRead";
      case 11:
        return "onPhyUpdate";
      case 10:
        return "onMtuChanged";
      case 9:
        return "onNotificationSent";
      case 8:
        return "onExecuteWrite";
      case 7:
        return "onDescriptorWriteRequest";
      case 6:
        return "onCharacteristicWriteRequest";
      case 5:
        return "onDescriptorReadRequest";
      case 4:
        return "onCharacteristicReadRequest";
      case 3:
        return "onServiceAdded";
      case 2:
        return "onServerConnectionState";
      case 1:
        break;
    } 
    return "onServerRegistered";
  }
  
  public static boolean setDefaultImpl(IBluetoothGattServerCallback paramIBluetoothGattServerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothGattServerCallback != null) {
        Proxy.sDefaultImpl = paramIBluetoothGattServerCallback;
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
    String str;
    if (paramInt1 != 1598968902) {
      int i;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 13:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          onConnectionUpdated(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 12:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          onPhyRead(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 11:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          onPhyUpdate(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          onMtuChanged(paramParcel1.readString(), paramParcel1.readInt());
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          onNotificationSent(paramParcel1.readString(), paramParcel1.readInt());
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          str = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onExecuteWrite(str, paramInt1, bool2);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          str = paramParcel1.readString();
          paramInt2 = paramParcel1.readInt();
          i = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          onDescriptorWriteRequest(str, paramInt2, i, paramInt1, bool2, bool1, paramParcel1.readInt(), paramParcel1.createByteArray());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          str = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          paramInt2 = paramParcel1.readInt();
          i = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          onCharacteristicWriteRequest(str, paramInt1, paramInt2, i, bool2, bool1, paramParcel1.readInt(), paramParcel1.createByteArray());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          str = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          paramInt2 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          onDescriptorReadRequest(str, paramInt1, paramInt2, bool2, paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          str = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          paramInt2 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          onCharacteristicReadRequest(str, paramInt1, paramInt2, bool2, paramParcel1.readInt());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            BluetoothGattService bluetoothGattService = (BluetoothGattService)BluetoothGattService.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onServiceAdded(paramInt1, (BluetoothGattService)paramParcel1);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
          paramInt2 = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          bool2 = bool1;
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onServerConnectionState(paramInt2, paramInt1, bool2, paramParcel1.readString());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
      onServerRegistered(paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    str.writeString("android.bluetooth.IBluetoothGattServerCallback");
    return true;
  }
  
  private static class Proxy implements IBluetoothGattServerCallback {
    public static IBluetoothGattServerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothGattServerCallback";
    }
    
    public void onCharacteristicReadRequest(String param2String, int param2Int1, int param2Int2, boolean param2Boolean, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onCharacteristicReadRequest(param2String, param2Int1, param2Int2, param2Boolean, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCharacteristicWriteRequest(String param2String, int param2Int1, int param2Int2, int param2Int3, boolean param2Boolean1, boolean param2Boolean2, int param2Int4, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        try {
          parcel.writeString(param2String);
          try {
            parcel.writeInt(param2Int1);
            try {
              parcel.writeInt(param2Int2);
              try {
                parcel.writeInt(param2Int3);
                boolean bool1 = false;
                if (param2Boolean1) {
                  bool2 = true;
                } else {
                  bool2 = false;
                } 
                parcel.writeInt(bool2);
                boolean bool2 = bool1;
                if (param2Boolean2)
                  bool2 = true; 
                parcel.writeInt(bool2);
                try {
                  parcel.writeInt(param2Int4);
                  parcel.writeByteArray(param2ArrayOfbyte);
                  if (!this.mRemote.transact(6, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
                    IBluetoothGattServerCallback.Stub.getDefaultImpl().onCharacteristicWriteRequest(param2String, param2Int1, param2Int2, param2Int3, param2Boolean1, param2Boolean2, param2Int4, param2ArrayOfbyte);
                    parcel.recycle();
                    return;
                  } 
                  parcel.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2String;
    }
    
    public void onConnectionUpdated(String param2String, int param2Int1, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        parcel.writeInt(param2Int4);
        if (!this.mRemote.transact(13, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onConnectionUpdated(param2String, param2Int1, param2Int2, param2Int3, param2Int4);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorReadRequest(String param2String, int param2Int1, int param2Int2, boolean param2Boolean, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onDescriptorReadRequest(param2String, param2Int1, param2Int2, param2Boolean, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorWriteRequest(String param2String, int param2Int1, int param2Int2, int param2Int3, boolean param2Boolean1, boolean param2Boolean2, int param2Int4, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        try {
          parcel.writeString(param2String);
          try {
            parcel.writeInt(param2Int1);
            try {
              parcel.writeInt(param2Int2);
              try {
                parcel.writeInt(param2Int3);
                boolean bool1 = false;
                if (param2Boolean1) {
                  bool2 = true;
                } else {
                  bool2 = false;
                } 
                parcel.writeInt(bool2);
                boolean bool2 = bool1;
                if (param2Boolean2)
                  bool2 = true; 
                parcel.writeInt(bool2);
                try {
                  parcel.writeInt(param2Int4);
                  parcel.writeByteArray(param2ArrayOfbyte);
                  if (!this.mRemote.transact(7, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
                    IBluetoothGattServerCallback.Stub.getDefaultImpl().onDescriptorWriteRequest(param2String, param2Int1, param2Int2, param2Int3, param2Boolean1, param2Boolean2, param2Int4, param2ArrayOfbyte);
                    parcel.recycle();
                    return;
                  } 
                  parcel.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2String;
    }
    
    public void onExecuteWrite(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onExecuteWrite(param2String, param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onMtuChanged(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(10, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onMtuChanged(param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNotificationSent(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(9, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onNotificationSent(param2String, param2Int);
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
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(12, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onPhyRead(param2String, param2Int1, param2Int2, param2Int3);
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
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onPhyUpdate(param2String, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServerConnectionState(int param2Int1, int param2Int2, boolean param2Boolean, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onServerConnectionState(param2Int1, param2Int2, param2Boolean, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServerRegistered(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onServerRegistered(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServiceAdded(int param2Int, BluetoothGattService param2BluetoothGattService) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeInt(param2Int);
        if (param2BluetoothGattService != null) {
          parcel.writeInt(1);
          param2BluetoothGattService.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onServiceAdded(param2Int, param2BluetoothGattService);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattServerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */