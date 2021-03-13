package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUserSwitchObserver extends IInterface {
  void onForegroundProfileSwitch(int paramInt) throws RemoteException;
  
  void onLockedBootComplete(int paramInt) throws RemoteException;
  
  void onUserSwitchComplete(int paramInt) throws RemoteException;
  
  void onUserSwitching(int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException;
  
  public static class Default implements IUserSwitchObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onForegroundProfileSwitch(int param1Int) throws RemoteException {}
    
    public void onLockedBootComplete(int param1Int) throws RemoteException {}
    
    public void onUserSwitchComplete(int param1Int) throws RemoteException {}
    
    public void onUserSwitching(int param1Int, IRemoteCallback param1IRemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IUserSwitchObserver {
    private static final String DESCRIPTOR = "android.app.IUserSwitchObserver";
    
    static final int TRANSACTION_onForegroundProfileSwitch = 3;
    
    static final int TRANSACTION_onLockedBootComplete = 4;
    
    static final int TRANSACTION_onUserSwitchComplete = 2;
    
    static final int TRANSACTION_onUserSwitching = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IUserSwitchObserver");
    }
    
    public static IUserSwitchObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IUserSwitchObserver");
      return (iInterface != null && iInterface instanceof IUserSwitchObserver) ? (IUserSwitchObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IUserSwitchObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? null : "onLockedBootComplete") : "onForegroundProfileSwitch") : "onUserSwitchComplete") : "onUserSwitching";
    }
    
    public static boolean setDefaultImpl(IUserSwitchObserver param1IUserSwitchObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IUserSwitchObserver != null) {
          Proxy.sDefaultImpl = param1IUserSwitchObserver;
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
              if (param1Int1 != 1598968902)
                return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
              param1Parcel2.writeString("android.app.IUserSwitchObserver");
              return true;
            } 
            param1Parcel1.enforceInterface("android.app.IUserSwitchObserver");
            onLockedBootComplete(param1Parcel1.readInt());
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.IUserSwitchObserver");
          onForegroundProfileSwitch(param1Parcel1.readInt());
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IUserSwitchObserver");
        onUserSwitchComplete(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IUserSwitchObserver");
      onUserSwitching(param1Parcel1.readInt(), IRemoteCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
      return true;
    }
    
    private static class Proxy implements IUserSwitchObserver {
      public static IUserSwitchObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IUserSwitchObserver";
      }
      
      public void onForegroundProfileSwitch(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
            IUserSwitchObserver.Stub.getDefaultImpl().onForegroundProfileSwitch(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onLockedBootComplete(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
            IUserSwitchObserver.Stub.getDefaultImpl().onLockedBootComplete(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUserSwitchComplete(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
            IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitchComplete(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUserSwitching(int param2Int, IRemoteCallback param2IRemoteCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
          parcel.writeInt(param2Int);
          if (param2IRemoteCallback != null) {
            iBinder = param2IRemoteCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
            IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitching(param2Int, param2IRemoteCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IUserSwitchObserver {
    public static IUserSwitchObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IUserSwitchObserver";
    }
    
    public void onForegroundProfileSwitch(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onForegroundProfileSwitch(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onLockedBootComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onLockedBootComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUserSwitchComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitchComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUserSwitching(int param1Int, IRemoteCallback param1IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IUserSwitchObserver");
        parcel.writeInt(param1Int);
        if (param1IRemoteCallback != null) {
          iBinder = param1IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IUserSwitchObserver.Stub.getDefaultImpl() != null) {
          IUserSwitchObserver.Stub.getDefaultImpl().onUserSwitching(param1Int, param1IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUserSwitchObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */