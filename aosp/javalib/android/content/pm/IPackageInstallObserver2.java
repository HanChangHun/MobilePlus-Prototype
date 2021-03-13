package android.content.pm;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPackageInstallObserver2 extends IInterface {
  void onPackageInstalled(String paramString1, int paramInt, String paramString2, Bundle paramBundle) throws RemoteException;
  
  void onUserActionRequired(Intent paramIntent) throws RemoteException;
  
  public static class Default implements IPackageInstallObserver2 {
    public IBinder asBinder() {
      return null;
    }
    
    public void onPackageInstalled(String param1String1, int param1Int, String param1String2, Bundle param1Bundle) throws RemoteException {}
    
    public void onUserActionRequired(Intent param1Intent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageInstallObserver2 {
    private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver2";
    
    static final int TRANSACTION_onPackageInstalled = 2;
    
    static final int TRANSACTION_onUserActionRequired = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPackageInstallObserver2");
    }
    
    public static IPackageInstallObserver2 asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageInstallObserver2");
      return (iInterface != null && iInterface instanceof IPackageInstallObserver2) ? (IPackageInstallObserver2)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageInstallObserver2 getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onPackageInstalled") : "onUserActionRequired";
    }
    
    public static boolean setDefaultImpl(IPackageInstallObserver2 param1IPackageInstallObserver2) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageInstallObserver2 != null) {
          Proxy.sDefaultImpl = param1IPackageInstallObserver2;
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
          param1Parcel2.writeString("android.content.pm.IPackageInstallObserver2");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IPackageInstallObserver2");
        String str2 = param1Parcel1.readString();
        param1Int1 = param1Parcel1.readInt();
        String str1 = param1Parcel1.readString();
        if (param1Parcel1.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onPackageInstalled(str2, param1Int1, str1, (Bundle)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IPackageInstallObserver2");
      if (param1Parcel1.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onUserActionRequired((Intent)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IPackageInstallObserver2 {
      public static IPackageInstallObserver2 sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IPackageInstallObserver2";
      }
      
      public void onPackageInstalled(String param2String1, int param2Int, String param2String2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
          parcel.writeString(param2String1);
          parcel.writeInt(param2Int);
          parcel.writeString(param2String2);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IPackageInstallObserver2.Stub.getDefaultImpl() != null) {
            IPackageInstallObserver2.Stub.getDefaultImpl().onPackageInstalled(param2String1, param2Int, param2String2, param2Bundle);
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
          parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IPackageInstallObserver2.Stub.getDefaultImpl() != null) {
            IPackageInstallObserver2.Stub.getDefaultImpl().onUserActionRequired(param2Intent);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPackageInstallObserver2 {
    public static IPackageInstallObserver2 sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageInstallObserver2";
    }
    
    public void onPackageInstalled(String param1String1, int param1Int, String param1String2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
        parcel.writeString(param1String1);
        parcel.writeInt(param1Int);
        parcel.writeString(param1String2);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IPackageInstallObserver2.Stub.getDefaultImpl() != null) {
          IPackageInstallObserver2.Stub.getDefaultImpl().onPackageInstalled(param1String1, param1Int, param1String2, param1Bundle);
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
        parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
        if (param1Intent != null) {
          parcel.writeInt(1);
          param1Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageInstallObserver2.Stub.getDefaultImpl() != null) {
          IPackageInstallObserver2.Stub.getDefaultImpl().onUserActionRequired(param1Intent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallObserver2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */