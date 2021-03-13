package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageStatsObserver {
  private static final String DESCRIPTOR = "android.content.pm.IPackageStatsObserver";
  
  static final int TRANSACTION_onGetStatsCompleted = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageStatsObserver");
  }
  
  public static IPackageStatsObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageStatsObserver");
    return (iInterface != null && iInterface instanceof IPackageStatsObserver) ? (IPackageStatsObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageStatsObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onGetStatsCompleted";
  }
  
  public static boolean setDefaultImpl(IPackageStatsObserver paramIPackageStatsObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageStatsObserver != null) {
        Proxy.sDefaultImpl = paramIPackageStatsObserver;
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
      paramParcel2.writeString("android.content.pm.IPackageStatsObserver");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageStatsObserver");
    if (paramParcel1.readInt() != 0) {
      PackageStats packageStats = (PackageStats)PackageStats.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onGetStatsCompleted((PackageStats)paramParcel2, bool);
    return true;
  }
  
  private static class Proxy implements IPackageStatsObserver {
    public static IPackageStatsObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageStatsObserver";
    }
    
    public void onGetStatsCompleted(PackageStats param2PackageStats, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
        boolean bool = false;
        if (param2PackageStats != null) {
          parcel.writeInt(1);
          param2PackageStats.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageStatsObserver.Stub.getDefaultImpl() != null) {
          IPackageStatsObserver.Stub.getDefaultImpl().onGetStatsCompleted(param2PackageStats, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageStatsObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */