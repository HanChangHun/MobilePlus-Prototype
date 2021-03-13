package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISyncServiceAdapter {
  public static ISyncServiceAdapter sDefaultImpl;
  
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
      parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
      if (paramISyncContext != null) {
        iBinder = paramISyncContext.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
        ISyncServiceAdapter.Stub.getDefaultImpl().cancelSync(paramISyncContext);
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
  
  public void startSync(ISyncContext paramISyncContext, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.content.ISyncServiceAdapter");
      if (paramISyncContext != null) {
        iBinder = paramISyncContext.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && ISyncServiceAdapter.Stub.getDefaultImpl() != null) {
        ISyncServiceAdapter.Stub.getDefaultImpl().startSync(paramISyncContext, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncServiceAdapter$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */