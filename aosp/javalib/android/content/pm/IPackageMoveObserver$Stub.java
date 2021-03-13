package android.content.pm;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageMoveObserver {
  private static final String DESCRIPTOR = "android.content.pm.IPackageMoveObserver";
  
  static final int TRANSACTION_onCreated = 1;
  
  static final int TRANSACTION_onStatusChanged = 2;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageMoveObserver");
  }
  
  public static IPackageMoveObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageMoveObserver");
    return (iInterface != null && iInterface instanceof IPackageMoveObserver) ? (IPackageMoveObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageMoveObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onStatusChanged") : "onCreated";
  }
  
  public static boolean setDefaultImpl(IPackageMoveObserver paramIPackageMoveObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageMoveObserver != null) {
        Proxy.sDefaultImpl = paramIPackageMoveObserver;
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
        paramParcel2.writeString("android.content.pm.IPackageMoveObserver");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IPackageMoveObserver");
      onStatusChanged(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readLong());
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IPackageMoveObserver");
    paramInt1 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onCreated(paramInt1, (Bundle)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageMoveObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */