package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageDataObserver {
  private static final String DESCRIPTOR = "android.content.pm.IPackageDataObserver";
  
  static final int TRANSACTION_onRemoveCompleted = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageDataObserver");
  }
  
  public static IPackageDataObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageDataObserver");
    return (iInterface != null && iInterface instanceof IPackageDataObserver) ? (IPackageDataObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageDataObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onRemoveCompleted";
  }
  
  public static boolean setDefaultImpl(IPackageDataObserver paramIPackageDataObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageDataObserver != null) {
        Proxy.sDefaultImpl = paramIPackageDataObserver;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.pm.IPackageDataObserver");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageDataObserver");
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onRemoveCompleted(str, bool);
    return true;
  }
  
  private static class Proxy implements IPackageDataObserver {
    public static IPackageDataObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageDataObserver";
    }
    
    public void onRemoveCompleted(String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.pm.IPackageDataObserver");
        parcel.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageDataObserver.Stub.getDefaultImpl() != null) {
          IPackageDataObserver.Stub.getDefaultImpl().onRemoveCompleted(param2String, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDataObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */