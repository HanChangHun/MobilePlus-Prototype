package android.content;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISyncAdapter {
  public static ISyncAdapter sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancelSync(ISyncContext paramISyncContext) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.content.ISyncAdapter");
      if (paramISyncContext != null) {
        iBinder = paramISyncContext.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
        ISyncAdapter.Stub.getDefaultImpl().cancelSync(paramISyncContext);
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
  
  public void onUnsyncableAccount(ISyncAdapterUnsyncableAccountCallback paramISyncAdapterUnsyncableAccountCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.content.ISyncAdapter");
      if (paramISyncAdapterUnsyncableAccountCallback != null) {
        iBinder = paramISyncAdapterUnsyncableAccountCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
        ISyncAdapter.Stub.getDefaultImpl().onUnsyncableAccount(paramISyncAdapterUnsyncableAccountCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void startSync(ISyncContext paramISyncContext, String paramString, Account paramAccount, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.content.ISyncAdapter");
      if (paramISyncContext != null) {
        iBinder = paramISyncContext.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString);
      if (paramAccount != null) {
        parcel.writeInt(1);
        paramAccount.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && ISyncAdapter.Stub.getDefaultImpl() != null) {
        ISyncAdapter.Stub.getDefaultImpl().startSync(paramISyncContext, paramString, paramAccount, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapter$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */