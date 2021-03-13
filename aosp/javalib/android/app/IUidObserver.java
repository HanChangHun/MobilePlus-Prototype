package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUidObserver extends IInterface {
  void onUidActive(int paramInt) throws RemoteException;
  
  void onUidCachedChanged(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onUidGone(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onUidIdle(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onUidStateChanged(int paramInt1, int paramInt2, long paramLong, int paramInt3) throws RemoteException;
  
  public static class Default implements IUidObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onUidActive(int param1Int) throws RemoteException {}
    
    public void onUidCachedChanged(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onUidGone(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onUidIdle(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onUidStateChanged(int param1Int1, int param1Int2, long param1Long, int param1Int3) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IUidObserver {
    private static final String DESCRIPTOR = "android.app.IUidObserver";
    
    static final int TRANSACTION_onUidActive = 2;
    
    static final int TRANSACTION_onUidCachedChanged = 5;
    
    static final int TRANSACTION_onUidGone = 1;
    
    static final int TRANSACTION_onUidIdle = 3;
    
    static final int TRANSACTION_onUidStateChanged = 4;
    
    public Stub() {
      attachInterface(this, "android.app.IUidObserver");
    }
    
    public static IUidObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IUidObserver");
      return (iInterface != null && iInterface instanceof IUidObserver) ? (IUidObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IUidObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "onUidCachedChanged") : "onUidStateChanged") : "onUidIdle") : "onUidActive") : "onUidGone";
    }
    
    public static boolean setDefaultImpl(IUidObserver param1IUidObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IUidObserver != null) {
          Proxy.sDefaultImpl = param1IUidObserver;
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
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.app.IUidObserver");
                return true;
              } 
              param1Parcel1.enforceInterface("android.app.IUidObserver");
              param1Int1 = param1Parcel1.readInt();
              if (param1Parcel1.readInt() != 0)
                bool3 = true; 
              onUidCachedChanged(param1Int1, bool3);
              return true;
            } 
            param1Parcel1.enforceInterface("android.app.IUidObserver");
            onUidStateChanged(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readLong(), param1Parcel1.readInt());
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.IUidObserver");
          param1Int1 = param1Parcel1.readInt();
          bool3 = bool1;
          if (param1Parcel1.readInt() != 0)
            bool3 = true; 
          onUidIdle(param1Int1, bool3);
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IUidObserver");
        onUidActive(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IUidObserver");
      param1Int1 = param1Parcel1.readInt();
      bool3 = bool2;
      if (param1Parcel1.readInt() != 0)
        bool3 = true; 
      onUidGone(param1Int1, bool3);
      return true;
    }
    
    private static class Proxy implements IUidObserver {
      public static IUidObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IUidObserver";
      }
      
      public void onUidActive(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IUidObserver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
            IUidObserver.Stub.getDefaultImpl().onUidActive(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUidCachedChanged(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IUidObserver");
          parcel.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(5, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
            IUidObserver.Stub.getDefaultImpl().onUidCachedChanged(param2Int, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUidGone(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IUidObserver");
          parcel.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
            IUidObserver.Stub.getDefaultImpl().onUidGone(param2Int, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUidIdle(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IUidObserver");
          parcel.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(3, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
            IUidObserver.Stub.getDefaultImpl().onUidIdle(param2Int, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUidStateChanged(int param2Int1, int param2Int2, long param2Long, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IUidObserver");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(4, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
            IUidObserver.Stub.getDefaultImpl().onUidStateChanged(param2Int1, param2Int2, param2Long, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IUidObserver {
    public static IUidObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IUidObserver";
    }
    
    public void onUidActive(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidActive(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidCachedChanged(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(5, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidCachedChanged(param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidGone(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidGone(param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidIdle(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidIdle(param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUidStateChanged(int param1Int1, int param1Int2, long param1Long, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUidObserver");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(4, parcel, null, 1) && IUidObserver.Stub.getDefaultImpl() != null) {
          IUidObserver.Stub.getDefaultImpl().onUidStateChanged(param1Int1, param1Int2, param1Long, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUidObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */