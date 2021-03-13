package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageDeleteObserver {
  private static final String DESCRIPTOR = "android.content.pm.IPackageDeleteObserver";
  
  static final int TRANSACTION_packageDeleted = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageDeleteObserver");
  }
  
  public static IPackageDeleteObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageDeleteObserver");
    return (iInterface != null && iInterface instanceof IPackageDeleteObserver) ? (IPackageDeleteObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageDeleteObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "packageDeleted";
  }
  
  public static boolean setDefaultImpl(IPackageDeleteObserver paramIPackageDeleteObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageDeleteObserver != null) {
        Proxy.sDefaultImpl = paramIPackageDeleteObserver;
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
      paramParcel2.writeString("android.content.pm.IPackageDeleteObserver");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageDeleteObserver");
    packageDeleted(paramParcel1.readString(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IPackageDeleteObserver {
    public static IPackageDeleteObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageDeleteObserver";
    }
    
    public void packageDeleted(String param2String, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageDeleteObserver.Stub.getDefaultImpl() != null) {
          IPackageDeleteObserver.Stub.getDefaultImpl().packageDeleted(param2String, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */