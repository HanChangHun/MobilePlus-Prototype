package android.content.pm;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageDeleteObserver2 {
  private static final String DESCRIPTOR = "android.content.pm.IPackageDeleteObserver2";
  
  static final int TRANSACTION_onPackageDeleted = 2;
  
  static final int TRANSACTION_onUserActionRequired = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageDeleteObserver2");
  }
  
  public static IPackageDeleteObserver2 asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageDeleteObserver2");
    return (iInterface != null && iInterface instanceof IPackageDeleteObserver2) ? (IPackageDeleteObserver2)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageDeleteObserver2 getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onPackageDeleted") : "onUserActionRequired";
  }
  
  public static boolean setDefaultImpl(IPackageDeleteObserver2 paramIPackageDeleteObserver2) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageDeleteObserver2 != null) {
        Proxy.sDefaultImpl = paramIPackageDeleteObserver2;
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
        paramParcel2.writeString("android.content.pm.IPackageDeleteObserver2");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IPackageDeleteObserver2");
      onPackageDeleted(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readString());
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageDeleteObserver2");
    if (paramParcel1.readInt() != 0) {
      Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onUserActionRequired((Intent)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver2$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */