package android.bluetooth.le;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAdvertisingSetCallback extends IInterface {
  void onAdvertisingDataSet(int paramInt1, int paramInt2) throws RemoteException;
  
  void onAdvertisingEnabled(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException;
  
  void onAdvertisingParametersUpdated(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onAdvertisingSetStarted(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onAdvertisingSetStopped(int paramInt) throws RemoteException;
  
  void onOwnAddressRead(int paramInt1, int paramInt2, String paramString) throws RemoteException;
  
  void onPeriodicAdvertisingDataSet(int paramInt1, int paramInt2) throws RemoteException;
  
  void onPeriodicAdvertisingEnabled(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException;
  
  void onPeriodicAdvertisingParametersUpdated(int paramInt1, int paramInt2) throws RemoteException;
  
  void onScanResponseDataSet(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IAdvertisingSetCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAdvertisingDataSet(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onAdvertisingEnabled(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {}
    
    public void onAdvertisingParametersUpdated(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onAdvertisingSetStarted(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onAdvertisingSetStopped(int param1Int) throws RemoteException {}
    
    public void onOwnAddressRead(int param1Int1, int param1Int2, String param1String) throws RemoteException {}
    
    public void onPeriodicAdvertisingDataSet(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onPeriodicAdvertisingEnabled(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {}
    
    public void onPeriodicAdvertisingParametersUpdated(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onScanResponseDataSet(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAdvertisingSetCallback {
    private static final String DESCRIPTOR = "android.bluetooth.le.IAdvertisingSetCallback";
    
    static final int TRANSACTION_onAdvertisingDataSet = 5;
    
    static final int TRANSACTION_onAdvertisingEnabled = 4;
    
    static final int TRANSACTION_onAdvertisingParametersUpdated = 7;
    
    static final int TRANSACTION_onAdvertisingSetStarted = 1;
    
    static final int TRANSACTION_onAdvertisingSetStopped = 3;
    
    static final int TRANSACTION_onOwnAddressRead = 2;
    
    static final int TRANSACTION_onPeriodicAdvertisingDataSet = 9;
    
    static final int TRANSACTION_onPeriodicAdvertisingEnabled = 10;
    
    static final int TRANSACTION_onPeriodicAdvertisingParametersUpdated = 8;
    
    static final int TRANSACTION_onScanResponseDataSet = 6;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.le.IAdvertisingSetCallback");
    }
    
    public static IAdvertisingSetCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.le.IAdvertisingSetCallback");
      return (iInterface != null && iInterface instanceof IAdvertisingSetCallback) ? (IAdvertisingSetCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAdvertisingSetCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 10:
          return "onPeriodicAdvertisingEnabled";
        case 9:
          return "onPeriodicAdvertisingDataSet";
        case 8:
          return "onPeriodicAdvertisingParametersUpdated";
        case 7:
          return "onAdvertisingParametersUpdated";
        case 6:
          return "onScanResponseDataSet";
        case 5:
          return "onAdvertisingDataSet";
        case 4:
          return "onAdvertisingEnabled";
        case 3:
          return "onAdvertisingSetStopped";
        case 2:
          return "onOwnAddressRead";
        case 1:
          break;
      } 
      return "onAdvertisingSetStarted";
    }
    
    public static boolean setDefaultImpl(IAdvertisingSetCallback param1IAdvertisingSetCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAdvertisingSetCallback != null) {
          Proxy.sDefaultImpl = param1IAdvertisingSetCallback;
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
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 10:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onPeriodicAdvertisingEnabled(param1Int1, bool2, param1Parcel1.readInt());
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onPeriodicAdvertisingDataSet(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onPeriodicAdvertisingParametersUpdated(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onAdvertisingParametersUpdated(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onScanResponseDataSet(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onAdvertisingDataSet(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            param1Int1 = param1Parcel1.readInt();
            bool2 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onAdvertisingEnabled(param1Int1, bool2, param1Parcel1.readInt());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onAdvertisingSetStopped(param1Parcel1.readInt());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
            onOwnAddressRead(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readString());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
        onAdvertisingSetStarted(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.le.IAdvertisingSetCallback");
      return true;
    }
    
    private static class Proxy implements IAdvertisingSetCallback {
      public static IAdvertisingSetCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.le.IAdvertisingSetCallback";
      }
      
      public void onAdvertisingDataSet(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(5, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingDataSet(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAdvertisingEnabled(int param2Int1, boolean param2Boolean, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(4, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingEnabled(param2Int1, param2Boolean, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAdvertisingParametersUpdated(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(7, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingParametersUpdated(param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAdvertisingSetStarted(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(1, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStarted(param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAdvertisingSetStopped(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStopped(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onOwnAddressRead(int param2Int1, int param2Int2, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(2, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onOwnAddressRead(param2Int1, param2Int2, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onPeriodicAdvertisingDataSet(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(9, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingDataSet(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onPeriodicAdvertisingEnabled(int param2Int1, boolean param2Boolean, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(10, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingEnabled(param2Int1, param2Boolean, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onPeriodicAdvertisingParametersUpdated(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(8, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingParametersUpdated(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onScanResponseDataSet(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(6, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
            IAdvertisingSetCallback.Stub.getDefaultImpl().onScanResponseDataSet(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IAdvertisingSetCallback {
    public static IAdvertisingSetCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.le.IAdvertisingSetCallback";
    }
    
    public void onAdvertisingDataSet(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingDataSet(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingEnabled(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(4, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingEnabled(param1Int1, param1Boolean, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingParametersUpdated(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(7, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingParametersUpdated(param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingSetStarted(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStarted(param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingSetStopped(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStopped(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onOwnAddressRead(int param1Int1, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onOwnAddressRead(param1Int1, param1Int2, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPeriodicAdvertisingDataSet(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(9, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingDataSet(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPeriodicAdvertisingEnabled(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(10, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingEnabled(param1Int1, param1Boolean, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPeriodicAdvertisingParametersUpdated(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(8, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingParametersUpdated(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onScanResponseDataSet(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(6, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onScanResponseDataSet(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IAdvertisingSetCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */