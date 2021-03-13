package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISyncStatusObserver {
  private static final String DESCRIPTOR = "android.content.ISyncStatusObserver";
  
  static final int TRANSACTION_onStatusChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.content.ISyncStatusObserver");
  }
  
  public static ISyncStatusObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.ISyncStatusObserver");
    return (iInterface != null && iInterface instanceof ISyncStatusObserver) ? (ISyncStatusObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISyncStatusObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onStatusChanged";
  }
  
  public static boolean setDefaultImpl(ISyncStatusObserver paramISyncStatusObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISyncStatusObserver != null) {
        Proxy.sDefaultImpl = paramISyncStatusObserver;
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
      paramParcel2.writeString("android.content.ISyncStatusObserver");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.ISyncStatusObserver");
    onStatusChanged(paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements ISyncStatusObserver {
    public static ISyncStatusObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncStatusObserver";
    }
    
    public void onStatusChanged(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.ISyncStatusObserver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncStatusObserver.Stub.getDefaultImpl() != null) {
          ISyncStatusObserver.Stub.getDefaultImpl().onStatusChanged(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncStatusObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */