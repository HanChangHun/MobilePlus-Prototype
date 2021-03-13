package android.database;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

class Proxy implements IContentObserver {
  public static IContentObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.database.IContentObserver";
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.database.IContentObserver");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramUri != null) {
        parcel.writeInt(1);
        paramUri.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IContentObserver.Stub.getDefaultImpl() != null) {
        IContentObserver.Stub.getDefaultImpl().onChange(paramBoolean, paramUri, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onChangeEtc(boolean paramBoolean, Uri[] paramArrayOfUri, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.database.IContentObserver");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeTypedArray((Parcelable[])paramArrayOfUri, 0);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel, null, 1) && IContentObserver.Stub.getDefaultImpl() != null) {
        IContentObserver.Stub.getDefaultImpl().onChangeEtc(paramBoolean, paramArrayOfUri, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/IContentObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */