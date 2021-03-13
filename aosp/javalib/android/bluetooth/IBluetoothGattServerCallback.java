package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothGattServerCallback extends IInterface {
  void onCharacteristicReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) throws RemoteException;
  
  void onCharacteristicWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException;
  
  void onDescriptorReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) throws RemoteException;
  
  void onDescriptorWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onExecuteWrite(String paramString, int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onMtuChanged(String paramString, int paramInt) throws RemoteException;
  
  void onNotificationSent(String paramString, int paramInt) throws RemoteException;
  
  void onPhyRead(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onPhyUpdate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onServerConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) throws RemoteException;
  
  void onServerRegistered(int paramInt1, int paramInt2) throws RemoteException;
  
  void onServiceAdded(int paramInt, BluetoothGattService paramBluetoothGattService) throws RemoteException;
  
  public static class Default implements IBluetoothGattServerCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCharacteristicReadRequest(String param1String, int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) throws RemoteException {}
    
    public void onCharacteristicWriteRequest(String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onConnectionUpdated(String param1String, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {}
    
    public void onDescriptorReadRequest(String param1String, int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) throws RemoteException {}
    
    public void onDescriptorWriteRequest(String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onExecuteWrite(String param1String, int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onMtuChanged(String param1String, int param1Int) throws RemoteException {}
    
    public void onNotificationSent(String param1String, int param1Int) throws RemoteException {}
    
    public void onPhyRead(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onPhyUpdate(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onServerConnectionState(int param1Int1, int param1Int2, boolean param1Boolean, String param1String) throws RemoteException {}
    
    public void onServerRegistered(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onServiceAdded(int param1Int, BluetoothGattService param1BluetoothGattService) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothGattServerCallback {
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
    
    public static IBluetoothGattServerCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothGattServerCallback");
      return (iInterface != null && iInterface instanceof IBluetoothGattServerCallback) ? (IBluetoothGattServerCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothGattServerCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothGattServerCallback param1IBluetoothGattServerCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothGattServerCallback != null) {
          Proxy.sDefaultImpl = param1IBluetoothGattServerCallback;
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
      String str;
      if (param1Int1 != 1598968902) {
        int i;
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 13:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            onConnectionUpdated(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            onPhyRead(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            onPhyUpdate(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            onMtuChanged(param1Parcel1.readString(), param1Parcel1.readInt());
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            onNotificationSent(param1Parcel1.readString(), param1Parcel1.readInt());
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            str = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onExecuteWrite(str, param1Int1, bool2);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            str = param1Parcel1.readString();
            param1Int2 = param1Parcel1.readInt();
            i = param1Parcel1.readInt();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            onDescriptorWriteRequest(str, param1Int2, i, param1Int1, bool2, bool1, param1Parcel1.readInt(), param1Parcel1.createByteArray());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            str = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            param1Int2 = param1Parcel1.readInt();
            i = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            onCharacteristicWriteRequest(str, param1Int1, param1Int2, i, bool2, bool1, param1Parcel1.readInt(), param1Parcel1.createByteArray());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            str = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            param1Int2 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            onDescriptorReadRequest(str, param1Int1, param1Int2, bool2, param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            str = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            param1Int2 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            onCharacteristicReadRequest(str, param1Int1, param1Int2, bool2, param1Parcel1.readInt());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              BluetoothGattService bluetoothGattService = (BluetoothGattService)BluetoothGattService.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onServiceAdded(param1Int1, (BluetoothGattService)param1Parcel1);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
            param1Int2 = param1Parcel1.readInt();
            param1Int1 = param1Parcel1.readInt();
            bool2 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onServerConnectionState(param1Int2, param1Int1, bool2, param1Parcel1.readString());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattServerCallback");
        onServerRegistered(param1Parcel1.readInt(), param1Parcel1.readInt());
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
  
  private static class Proxy implements IBluetoothGattServerCallback {
    public static IBluetoothGattServerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothGattServerCallback";
    }
    
    public void onCharacteristicReadRequest(String param1String, int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onCharacteristicReadRequest(param1String, param1Int1, param1Int2, param1Boolean, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCharacteristicWriteRequest(String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        try {
          parcel.writeString(param1String);
          try {
            parcel.writeInt(param1Int1);
            try {
              parcel.writeInt(param1Int2);
              try {
                parcel.writeInt(param1Int3);
                boolean bool1 = false;
                if (param1Boolean1) {
                  bool2 = true;
                } else {
                  bool2 = false;
                } 
                parcel.writeInt(bool2);
                boolean bool2 = bool1;
                if (param1Boolean2)
                  bool2 = true; 
                parcel.writeInt(bool2);
                try {
                  parcel.writeInt(param1Int4);
                  parcel.writeByteArray(param1ArrayOfbyte);
                  if (!this.mRemote.transact(6, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
                    IBluetoothGattServerCallback.Stub.getDefaultImpl().onCharacteristicWriteRequest(param1String, param1Int1, param1Int2, param1Int3, param1Boolean1, param1Boolean2, param1Int4, param1ArrayOfbyte);
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
      throw param1String;
    }
    
    public void onConnectionUpdated(String param1String, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        parcel.writeInt(param1Int4);
        if (!this.mRemote.transact(13, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onConnectionUpdated(param1String, param1Int1, param1Int2, param1Int3, param1Int4);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorReadRequest(String param1String, int param1Int1, int param1Int2, boolean param1Boolean, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onDescriptorReadRequest(param1String, param1Int1, param1Int2, param1Boolean, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorWriteRequest(String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, int param1Int4, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        try {
          parcel.writeString(param1String);
          try {
            parcel.writeInt(param1Int1);
            try {
              parcel.writeInt(param1Int2);
              try {
                parcel.writeInt(param1Int3);
                boolean bool1 = false;
                if (param1Boolean1) {
                  bool2 = true;
                } else {
                  bool2 = false;
                } 
                parcel.writeInt(bool2);
                boolean bool2 = bool1;
                if (param1Boolean2)
                  bool2 = true; 
                parcel.writeInt(bool2);
                try {
                  parcel.writeInt(param1Int4);
                  parcel.writeByteArray(param1ArrayOfbyte);
                  if (!this.mRemote.transact(7, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
                    IBluetoothGattServerCallback.Stub.getDefaultImpl().onDescriptorWriteRequest(param1String, param1Int1, param1Int2, param1Int3, param1Boolean1, param1Boolean2, param1Int4, param1ArrayOfbyte);
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
      throw param1String;
    }
    
    public void onExecuteWrite(String param1String, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onExecuteWrite(param1String, param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onMtuChanged(String param1String, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onMtuChanged(param1String, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNotificationSent(String param1String, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(9, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onNotificationSent(param1String, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPhyRead(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(12, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onPhyRead(param1String, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPhyUpdate(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onPhyUpdate(param1String, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServerConnectionState(int param1Int1, int param1Int2, boolean param1Boolean, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onServerConnectionState(param1Int1, param1Int2, param1Boolean, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServerRegistered(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onServerRegistered(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServiceAdded(int param1Int, BluetoothGattService param1BluetoothGattService) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
        parcel.writeInt(param1Int);
        if (param1BluetoothGattService != null) {
          parcel.writeInt(1);
          param1BluetoothGattService.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattServerCallback.Stub.getDefaultImpl().onServiceAdded(param1Int, param1BluetoothGattService);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattServerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */