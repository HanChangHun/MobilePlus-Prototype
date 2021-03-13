package android.content.pm;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageInstallObserver2 {
  private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver2";
  
  static final int TRANSACTION_onPackageInstalled = 2;
  
  static final int TRANSACTION_onUserActionRequired = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageInstallObserver2");
  }
  
  public static IPackageInstallObserver2 asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstallObserver2");
    return (iInterface != null && iInterface instanceof IPackageInstallObserver2) ? (IPackageInstallObserver2)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageInstallObserver2 getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onPackageInstalled") : "onUserActionRequired";
  }
  
  public static boolean setDefaultImpl(IPackageInstallObserver2 paramIPackageInstallObserver2) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageInstallObserver2 != null) {
        Proxy.sDefaultImpl = paramIPackageInstallObserver2;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.content.pm.IPackageInstallObserver2");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IPackageInstallObserver2");
      String str2 = paramParcel1.readString();
      paramInt1 = paramParcel1.readInt();
      String str1 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onPackageInstalled(str2, paramInt1, str1, (Bundle)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageInstallObserver2");
    if (paramParcel1.readInt() != 0) {
      Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onUserActionRequired((Intent)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallObserver2$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */