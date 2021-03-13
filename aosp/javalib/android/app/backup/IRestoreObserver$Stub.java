package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRestoreObserver {
  private static final String DESCRIPTOR = "android.app.backup.IRestoreObserver";
  
  static final int TRANSACTION_onUpdate = 3;
  
  static final int TRANSACTION_restoreFinished = 4;
  
  static final int TRANSACTION_restoreSetsAvailable = 1;
  
  static final int TRANSACTION_restoreStarting = 2;
  
  public Stub() {
    attachInterface(this, "android.app.backup.IRestoreObserver");
  }
  
  public static IRestoreObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.backup.IRestoreObserver");
    return (iInterface != null && iInterface instanceof IRestoreObserver) ? (IRestoreObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRestoreObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : "restoreFinished") : "onUpdate") : "restoreStarting") : "restoreSetsAvailable";
  }
  
  public static boolean setDefaultImpl(IRestoreObserver paramIRestoreObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRestoreObserver != null) {
        Proxy.sDefaultImpl = paramIRestoreObserver;
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
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 1598968902)
              return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
            paramParcel2.writeString("android.app.backup.IRestoreObserver");
            return true;
          } 
          paramParcel1.enforceInterface("android.app.backup.IRestoreObserver");
          restoreFinished(paramParcel1.readInt());
          return true;
        } 
        paramParcel1.enforceInterface("android.app.backup.IRestoreObserver");
        onUpdate(paramParcel1.readInt(), paramParcel1.readString());
        return true;
      } 
      paramParcel1.enforceInterface("android.app.backup.IRestoreObserver");
      restoreStarting(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.backup.IRestoreObserver");
    restoreSetsAvailable((RestoreSet[])paramParcel1.createTypedArray(RestoreSet.CREATOR));
    return true;
  }
  
  private static class Proxy implements IRestoreObserver {
    public static IRestoreObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.backup.IRestoreObserver";
    }
    
    public void onUpdate(int param2Int, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(3, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().onUpdate(param2Int, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void restoreFinished(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().restoreFinished(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void restoreSetsAvailable(RestoreSet[] param2ArrayOfRestoreSet) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeTypedArray((Parcelable[])param2ArrayOfRestoreSet, 0);
        if (!this.mRemote.transact(1, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().restoreSetsAvailable(param2ArrayOfRestoreSet);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void restoreStarting(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.backup.IRestoreObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IRestoreObserver.Stub.getDefaultImpl() != null) {
          IRestoreObserver.Stub.getDefaultImpl().restoreStarting(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IRestoreObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */