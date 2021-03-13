package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothGattCallback extends IInterface {
  void onCharacteristicRead(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onCharacteristicWrite(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void onClientConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) throws RemoteException;
  
  void onClientRegistered(int paramInt1, int paramInt2) throws RemoteException;
  
  void onConfigureMTU(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException;
  
  void onDescriptorRead(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onDescriptorWrite(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void onExecuteWrite(String paramString, int paramInt) throws RemoteException;
  
  void onNotify(String paramString, int paramInt, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onPhyRead(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onPhyUpdate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onReadRemoteRssi(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void onSearchComplete(String paramString, List<BluetoothGattService> paramList, int paramInt) throws RemoteException;
  
  public static class Default implements IBluetoothGattCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCharacteristicRead(String param1String, int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onCharacteristicWrite(String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onClientConnectionState(int param1Int1, int param1Int2, boolean param1Boolean, String param1String) throws RemoteException {}
    
    public void onClientRegistered(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onConfigureMTU(String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onConnectionUpdated(String param1String, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {}
    
    public void onDescriptorRead(String param1String, int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onDescriptorWrite(String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onExecuteWrite(String param1String, int param1Int) throws RemoteException {}
    
    public void onNotify(String param1String, int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onPhyRead(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onPhyUpdate(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onReadRemoteRssi(String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onSearchComplete(String param1String, List<BluetoothGattService> param1List, int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothGattCallback {
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
    
    public static IBluetoothGattCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothGattCallback");
      return (iInterface != null && iInterface instanceof IBluetoothGattCallback) ? (IBluetoothGattCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothGattCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothGattCallback param1IBluetoothGattCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothGattCallback != null) {
          Proxy.sDefaultImpl = param1IBluetoothGattCallback;
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
        boolean bool;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 14:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onConnectionUpdated(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onConfigureMTU(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onReadRemoteRssi(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onNotify(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.createByteArray());
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onDescriptorWrite(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onDescriptorRead(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.createByteArray());
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onExecuteWrite(param1Parcel1.readString(), param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onCharacteristicWrite(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onCharacteristicRead(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.createByteArray());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onSearchComplete(param1Parcel1.readString(), param1Parcel1.createTypedArrayList(BluetoothGattService.CREATOR), param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onPhyRead(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            onPhyUpdate(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
            param1Int2 = param1Parcel1.readInt();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            onClientConnectionState(param1Int2, param1Int1, bool, param1Parcel1.readString());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothGattCallback");
        onClientRegistered(param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothGattCallback");
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
  
  private static class Proxy implements IBluetoothGattCallback {
    public static IBluetoothGattCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothGattCallback";
    }
    
    public void onCharacteristicRead(String param1String, int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(6, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onCharacteristicRead(param1String, param1Int1, param1Int2, param1ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCharacteristicWrite(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(7, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onCharacteristicWrite(param1String, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onClientConnectionState(int param1Int1, int param1Int2, boolean param1Boolean, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onClientConnectionState(param1Int1, param1Int2, param1Boolean, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onClientRegistered(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onClientRegistered(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onConfigureMTU(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(13, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onConfigureMTU(param1String, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onConnectionUpdated(String param1String, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        parcel.writeInt(param1Int4);
        if (!this.mRemote.transact(14, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onConnectionUpdated(param1String, param1Int1, param1Int2, param1Int3, param1Int4);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorRead(String param1String, int param1Int1, int param1Int2, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(9, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onDescriptorRead(param1String, param1Int1, param1Int2, param1ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDescriptorWrite(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(10, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onDescriptorWrite(param1String, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onExecuteWrite(String param1String, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onExecuteWrite(param1String, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNotify(String param1String, int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onNotify(param1String, param1Int, param1ArrayOfbyte);
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
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onPhyRead(param1String, param1Int1, param1Int2, param1Int3);
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
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onPhyUpdate(param1String, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onReadRemoteRssi(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(12, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onReadRemoteRssi(param1String, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSearchComplete(String param1String, List<BluetoothGattService> param1List, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
        parcel.writeString(param1String);
        parcel.writeTypedList(param1List);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
          IBluetoothGattCallback.Stub.getDefaultImpl().onSearchComplete(param1String, param1List, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */