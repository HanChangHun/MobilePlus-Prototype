package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageChangeObserver {
  private static final String DESCRIPTOR = "android.content.pm.IPackageChangeObserver";
  
  static final int TRANSACTION_onPackageChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageChangeObserver");
  }
  
  public static IPackageChangeObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageChangeObserver");
    return (iInterface != null && iInterface instanceof IPackageChangeObserver) ? (IPackageChangeObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageChangeObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onPackageChanged";
  }
  
  public static boolean setDefaultImpl(IPackageChangeObserver paramIPackageChangeObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageChangeObserver != null) {
        Proxy.sDefaultImpl = paramIPackageChangeObserver;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.pm.IPackageChangeObserver");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageChangeObserver");
    if (paramParcel1.readInt() != 0) {
      PackageChangeEvent packageChangeEvent = (PackageChangeEvent)PackageChangeEvent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onPackageChanged((PackageChangeEvent)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IPackageChangeObserver {
    public static IPackageChangeObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageChangeObserver";
    }
    
    public void onPackageChanged(PackageChangeEvent param2PackageChangeEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageChangeObserver");
        if (param2PackageChangeEvent != null) {
          parcel.writeInt(1);
          param2PackageChangeEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageChangeObserver.Stub.getDefaultImpl() != null) {
          IPackageChangeObserver.Stub.getDefaultImpl().onPackageChanged(param2PackageChangeEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageChangeObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */