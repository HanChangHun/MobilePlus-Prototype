package android.content.pm;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPackageDeleteObserver2 extends IInterface {
  void onPackageDeleted(String paramString1, int paramInt, String paramString2) throws RemoteException;
  
  void onUserActionRequired(Intent paramIntent) throws RemoteException;
  
  public static class Default implements IPackageDeleteObserver2 {
    public IBinder asBinder() {
      return null;
    }
    
    public void onPackageDeleted(String param1String1, int param1Int, String param1String2) throws RemoteException {}
    
    public void onUserActionRequired(Intent param1Intent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageDeleteObserver2 {
    private static final String DESCRIPTOR = "android.content.pm.IPackageDeleteObserver2";
    
    static final int TRANSACTION_onPackageDeleted = 2;
    
    static final int TRANSACTION_onUserActionRequired = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPackageDeleteObserver2");
    }
    
    public static IPackageDeleteObserver2 asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageDeleteObserver2");
      return (iInterface != null && iInterface instanceof IPackageDeleteObserver2) ? (IPackageDeleteObserver2)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageDeleteObserver2 getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onPackageDeleted") : "onUserActionRequired";
    }
    
    public static boolean setDefaultImpl(IPackageDeleteObserver2 param1IPackageDeleteObserver2) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageDeleteObserver2 != null) {
          Proxy.sDefaultImpl = param1IPackageDeleteObserver2;
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
          param1Parcel2.writeString("android.content.pm.IPackageDeleteObserver2");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IPackageDeleteObserver2");
        onPackageDeleted(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readString());
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IPackageDeleteObserver2");
      if (param1Parcel1.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onUserActionRequired((Intent)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IPackageDeleteObserver2 {
      public static IPackageDeleteObserver2 sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IPackageDeleteObserver2";
      }
      
      public void onPackageDeleted(String param2String1, int param2Int, String param2String2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
          parcel.writeString(param2String1);
          parcel.writeInt(param2Int);
          parcel.writeString(param2String2);
          if (!this.mRemote.transact(2, parcel, null, 1) && IPackageDeleteObserver2.Stub.getDefaultImpl() != null) {
            IPackageDeleteObserver2.Stub.getDefaultImpl().onPackageDeleted(param2String1, param2Int, param2String2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onUserActionRequired(Intent param2Intent) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IPackageDeleteObserver2.Stub.getDefaultImpl() != null) {
            IPackageDeleteObserver2.Stub.getDefaultImpl().onUserActionRequired(param2Intent);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPackageDeleteObserver2 {
    public static IPackageDeleteObserver2 sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageDeleteObserver2";
    }
    
    public void onPackageDeleted(String param1String1, int param1Int, String param1String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
        parcel.writeString(param1String1);
        parcel.writeInt(param1Int);
        parcel.writeString(param1String2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IPackageDeleteObserver2.Stub.getDefaultImpl() != null) {
          IPackageDeleteObserver2.Stub.getDefaultImpl().onPackageDeleted(param1String1, param1Int, param1String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onUserActionRequired(Intent param1Intent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
        if (param1Intent != null) {
          parcel.writeInt(1);
          param1Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageDeleteObserver2.Stub.getDefaultImpl() != null) {
          IPackageDeleteObserver2.Stub.getDefaultImpl().onUserActionRequired(param1Intent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */