package android.content.pm;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageInstallObserver2 {
  public static IPackageInstallObserver2 sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageInstallObserver2";
  }
  
  public void onPackageInstalled(String paramString1, int paramInt, String paramString2, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
      parcel.writeString(paramString1);
      parcel.writeInt(paramInt);
      parcel.writeString(paramString2);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IPackageInstallObserver2.Stub.getDefaultImpl() != null) {
        IPackageInstallObserver2.Stub.getDefaultImpl().onPackageInstalled(paramString1, paramInt, paramString2, paramBundle);
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
      parcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver2");
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageInstallObserver2.Stub.getDefaultImpl() != null) {
        IPackageInstallObserver2.Stub.getDefaultImpl().onUserActionRequired(paramIntent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallObserver2$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */