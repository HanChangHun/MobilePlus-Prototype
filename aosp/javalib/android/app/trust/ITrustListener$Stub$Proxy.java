package android.app.trust;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

class Proxy implements ITrustListener {
  public static ITrustListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.trust.ITrustListener";
  }
  
  public void onTrustChanged(boolean paramBoolean, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.trust.ITrustListener");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
        ITrustListener.Stub.getDefaultImpl().onTrustChanged(paramBoolean, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTrustError(CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.trust.ITrustListener");
      if (paramCharSequence != null) {
        parcel.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
        ITrustListener.Stub.getDefaultImpl().onTrustError(paramCharSequence);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTrustManagedChanged(boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.trust.ITrustListener");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
        ITrustListener.Stub.getDefaultImpl().onTrustManagedChanged(paramBoolean, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */