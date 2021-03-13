package android.accounts;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAccountManagerResponse {
  public static IAccountManagerResponse sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.accounts.IAccountManagerResponse";
  }
  
  public void onError(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accounts.IAccountManagerResponse");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(2, parcel, null, 1) && IAccountManagerResponse.Stub.getDefaultImpl() != null) {
        IAccountManagerResponse.Stub.getDefaultImpl().onError(paramInt, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onResult(Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accounts.IAccountManagerResponse");
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IAccountManagerResponse.Stub.getDefaultImpl() != null) {
        IAccountManagerResponse.Stub.getDefaultImpl().onResult(paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/IAccountManagerResponse$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */