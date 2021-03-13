package android.content;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISyncAdapter {
  private static final String DESCRIPTOR = "android.content.ISyncAdapter";
  
  static final int TRANSACTION_cancelSync = 3;
  
  static final int TRANSACTION_onUnsyncableAccount = 1;
  
  static final int TRANSACTION_startSync = 2;
  
  public Stub() {
    attachInterface(this, "android.content.ISyncAdapter");
  }
  
  public static ISyncAdapter asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.ISyncAdapter");
    return (iInterface != null && iInterface instanceof ISyncAdapter) ? (ISyncAdapter)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISyncAdapter getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "cancelSync") : "startSync") : "onUnsyncableAccount";
  }
  
  public static boolean setDefaultImpl(ISyncAdapter paramISyncAdapter) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISyncAdapter != null) {
        Proxy.sDefaultImpl = paramISyncAdapter;
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
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.content.ISyncAdapter");
          return true;
        } 
        paramParcel1.enforceInterface("android.content.ISyncAdapter");
        cancelSync(ISyncContext.Stub.asInterface(paramParcel1.readStrongBinder()));
        return true;
      } 
      paramParcel1.enforceInterface("android.content.ISyncAdapter");
      ISyncContext iSyncContext = ISyncContext.Stub.asInterface(paramParcel1.readStrongBinder());
      String str = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {
        Account account = (Account)Account.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel2 = null;
      } 
      if (paramParcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      startSync(iSyncContext, str, (Account)paramParcel2, (Bundle)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.content.ISyncAdapter");
    onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements ISyncAdapter {
    public static ISyncAdapter sDefaultImpl;
    
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
        parcel.writeInterfaceToken("android.content.ISyncAdapter");
        if (param2ISyncContext != null) {
          iBinder = param2ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
          ISyncAdapter.Stub.getDefaultImpl().cancelSync(param2ISyncContext);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncAdapter";
    }
    
    public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback param2ISyncAdapterUnsyncableAccountCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncAdapter");
        if (param2ISyncAdapterUnsyncableAccountCallback != null) {
          iBinder = param2ISyncAdapterUnsyncableAccountCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
          ISyncAdapter.Stub.getDefaultImpl().onUnsyncableAccount(param2ISyncAdapterUnsyncableAccountCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void startSync(ISyncContext param2ISyncContext, String param2String, Account param2Account, Bundle param2Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.ISyncAdapter");
        if (param2ISyncContext != null) {
          iBinder = param2ISyncContext.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param2String);
        if (param2Account != null) {
          parcel.writeInt(1);
          param2Account.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2Bundle != null) {
          parcel.writeInt(1);
          param2Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
          ISyncAdapter.Stub.getDefaultImpl().startSync(param2ISyncContext, param2String, param2Account, param2Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapter$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */