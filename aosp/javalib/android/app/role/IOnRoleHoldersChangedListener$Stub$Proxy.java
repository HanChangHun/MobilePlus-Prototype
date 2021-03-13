package android.app.role;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IOnRoleHoldersChangedListener {
  public static IOnRoleHoldersChangedListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.role.IOnRoleHoldersChangedListener";
  }
  
  public void onRoleHoldersChanged(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.role.IOnRoleHoldersChangedListener");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IOnRoleHoldersChangedListener.Stub.getDefaultImpl() != null) {
        IOnRoleHoldersChangedListener.Stub.getDefaultImpl().onRoleHoldersChanged(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IOnRoleHoldersChangedListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */