package android.content.pm;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageDeleteObserver2 {
  public static IPackageDeleteObserver2 sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageDeleteObserver2";
  }
  
  public void onPackageDeleted(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
      parcel.writeString(paramString1);
      parcel.writeInt(paramInt);
      parcel.writeString(paramString2);
      if (!this.mRemote.transact(2, parcel, null, 1) && IPackageDeleteObserver2.Stub.getDefaultImpl() != null) {
        IPackageDeleteObserver2.Stub.getDefaultImpl().onPackageDeleted(paramString1, paramInt, paramString2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onUserActionRequired(Intent paramIntent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver2");
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageDeleteObserver2.Stub.getDefaultImpl() != null) {
        IPackageDeleteObserver2.Stub.getDefaultImpl().onUserActionRequired(paramIntent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver2$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */