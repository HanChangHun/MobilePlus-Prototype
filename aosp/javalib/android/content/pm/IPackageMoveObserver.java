package android.content.pm;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPackageMoveObserver extends IInterface {
  void onCreated(int paramInt, Bundle paramBundle) throws RemoteException;
  
  void onStatusChanged(int paramInt1, int paramInt2, long paramLong) throws RemoteException;
  
  public static class Default implements IPackageMoveObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCreated(int param1Int, Bundle param1Bundle) throws RemoteException {}
    
    public void onStatusChanged(int param1Int1, int param1Int2, long param1Long) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageMoveObserver {
    private static final String DESCRIPTOR = "android.content.pm.IPackageMoveObserver";
    
    static final int TRANSACTION_onCreated = 1;
    
    static final int TRANSACTION_onStatusChanged = 2;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPackageMoveObserver");
    }
    
    public static IPackageMoveObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageMoveObserver");
      return (iInterface != null && iInterface instanceof IPackageMoveObserver) ? (IPackageMoveObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageMoveObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onStatusChanged") : "onCreated";
    }
    
    public static boolean setDefaultImpl(IPackageMoveObserver param1IPackageMoveObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageMoveObserver != null) {
          Proxy.sDefaultImpl = param1IPackageMoveObserver;
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
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.content.pm.IPackageMoveObserver");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IPackageMoveObserver");
        onStatusChanged(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readLong());
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IPackageMoveObserver");
      param1Int1 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onCreated(param1Int1, (Bundle)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IPackageMoveObserver {
      public static IPackageMoveObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IPackageMoveObserver";
      }
      
      public void onCreated(int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
          parcel.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IPackageMoveObserver.Stub.getDefaultImpl() != null) {
            IPackageMoveObserver.Stub.getDefaultImpl().onCreated(param2Int, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onStatusChanged(int param2Int1, int param2Int2, long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(2, parcel, null, 1) && IPackageMoveObserver.Stub.getDefaultImpl() != null) {
            IPackageMoveObserver.Stub.getDefaultImpl().onStatusChanged(param2Int1, param2Int2, param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPackageMoveObserver {
    public static IPackageMoveObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageMoveObserver";
    }
    
    public void onCreated(int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
        parcel.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageMoveObserver.Stub.getDefaultImpl() != null) {
          IPackageMoveObserver.Stub.getDefaultImpl().onCreated(param1Int, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStatusChanged(int param1Int1, int param1Int2, long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(2, parcel, null, 1) && IPackageMoveObserver.Stub.getDefaultImpl() != null) {
          IPackageMoveObserver.Stub.getDefaultImpl().onStatusChanged(param1Int1, param1Int2, param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageMoveObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */