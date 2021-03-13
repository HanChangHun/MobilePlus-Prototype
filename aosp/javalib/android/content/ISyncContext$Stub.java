package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISyncContext {
  private static final String DESCRIPTOR = "android.content.ISyncContext";
  
  static final int TRANSACTION_onFinished = 2;
  
  static final int TRANSACTION_sendHeartbeat = 1;
  
  public Stub() {
    attachInterface(this, "android.content.ISyncContext");
  }
  
  public static ISyncContext asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.ISyncContext");
    return (iInterface != null && iInterface instanceof ISyncContext) ? (ISyncContext)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISyncContext getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onFinished") : "sendHeartbeat";
  }
  
  public static boolean setDefaultImpl(ISyncContext paramISyncContext) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISyncContext != null) {
        Proxy.sDefaultImpl = paramISyncContext;
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
        paramParcel2.writeString("android.content.ISyncContext");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.ISyncContext");
      if (paramParcel1.readInt() != 0) {
        SyncResult syncResult = (SyncResult)SyncResult.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onFinished((SyncResult)paramParcel1);
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.content.ISyncContext");
    sendHeartbeat();
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements ISyncContext {
    public static ISyncContext sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncContext";
    }
    
    public void onFinished(SyncResult param2SyncResult) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.ISyncContext");
        if (param2SyncResult != null) {
          parcel1.writeInt(1);
          param2SyncResult.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISyncContext.Stub.getDefaultImpl() != null) {
          ISyncContext.Stub.getDefaultImpl().onFinished(param2SyncResult);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendHeartbeat() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.ISyncContext");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISyncContext.Stub.getDefaultImpl() != null) {
          ISyncContext.Stub.getDefaultImpl().sendHeartbeat();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncContext$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */