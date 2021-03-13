package android.app.admin;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements StartInstallingUpdateCallback {
  public static StartInstallingUpdateCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.admin.StartInstallingUpdateCallback";
  }
  
  public void onStartInstallingUpdateError(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.admin.StartInstallingUpdateCallback");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(1, parcel, null, 1) && StartInstallingUpdateCallback.Stub.getDefaultImpl() != null) {
        StartInstallingUpdateCallback.Stub.getDefaultImpl().onStartInstallingUpdateError(paramInt, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/StartInstallingUpdateCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */