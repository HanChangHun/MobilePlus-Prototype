package android.app.admin;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

class Proxy implements IKeyguardCallback {
  public static IKeyguardCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.admin.IKeyguardCallback";
  }
  
  public void onDismiss() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.admin.IKeyguardCallback");
      if (!this.mRemote.transact(2, parcel, null, 1) && IKeyguardCallback.Stub.getDefaultImpl() != null) {
        IKeyguardCallback.Stub.getDefaultImpl().onDismiss();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage paramSurfacePackage) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.admin.IKeyguardCallback");
      if (paramSurfacePackage != null) {
        parcel.writeInt(1);
        paramSurfacePackage.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IKeyguardCallback.Stub.getDefaultImpl() != null) {
        IKeyguardCallback.Stub.getDefaultImpl().onRemoteContentReady(paramSurfacePackage);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */