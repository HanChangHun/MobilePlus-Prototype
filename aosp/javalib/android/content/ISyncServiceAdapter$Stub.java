package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISyncServiceAdapter {
  private static final String DESCRIPTOR = "android.content.ISyncServiceAdapter";
  
  static final int TRANSACTION_cancelSync = 2;
  
  static final int TRANSACTION_startSync = 1;
  
  public Stub() {
    attachInterface(this, "android.content.ISyncServiceAdapter");
  }
  
  public static ISyncServiceAdapter asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.ISyncServiceAdapter");
    return (iInterface != null && iInterface instanceof ISyncServiceAdapter) ? (ISyncServiceAdapter)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISyncServiceAdapter getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "cancelSync") : "startSync";
  }
  
  public static boolean setDefaultImpl(ISyncServiceAdapter paramISyncServiceAdapter) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISyncServiceAdapter != null) {
        Proxy.sDefaultImpl = paramISyncServiceAdapter;
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
        paramParcel2.writeString("android.content.ISyncServiceAdapter");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.ISyncServiceAdapter");
      cancelSync(ISyncContext.Stub.asInterface(paramParcel1.readStrongBinder()));
      return true;
    } 
    paramParcel1.enforceInterface("android.content.ISyncServiceAdapter");
    ISyncContext iSyncContext = ISyncContext.Stub.asInterface(paramParcel1.readStrongBinder());
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    startSync(iSyncContext, (Bundle)paramParcel1);
    return true;
  }
  
  private static class Proxy implements ISyncServiceAdapter {
    public static ISyncServiceAdapter sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancelSync(ISyncContext param2ISyncContext) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
        if (param2ISyncContext != null) {
          iBinder = param2ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
          ISyncServiceAdapter.Stub.getDefaultImpl().cancelSync(param2ISyncContext);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncServiceAdapter";
    }
    
    public void startSync(ISyncContext param2ISyncContext, Bundle param2Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
        if (param2ISyncContext != null) {
          iBinder = param2ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param2Bundle != null) {
          parcel.writeInt(1);
          param2Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
          ISyncServiceAdapter.Stub.getDefaultImpl().startSync(param2ISyncContext, param2Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncServiceAdapter$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */