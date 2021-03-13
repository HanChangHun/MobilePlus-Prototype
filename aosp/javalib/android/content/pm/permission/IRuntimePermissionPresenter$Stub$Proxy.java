package android.content.pm.permission;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;

class Proxy implements IRuntimePermissionPresenter {
  public static IRuntimePermissionPresenter sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void getAppPermissions(String paramString, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.permission.IRuntimePermissionPresenter");
      parcel.writeString(paramString);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IRuntimePermissionPresenter.Stub.getDefaultImpl() != null) {
        IRuntimePermissionPresenter.Stub.getDefaultImpl().getAppPermissions(paramString, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.permission.IRuntimePermissionPresenter";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/IRuntimePermissionPresenter$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */