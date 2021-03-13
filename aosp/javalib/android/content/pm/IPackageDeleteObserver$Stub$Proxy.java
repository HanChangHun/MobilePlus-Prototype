package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageDeleteObserver {
  public static IPackageDeleteObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageDeleteObserver";
  }
  
  public void packageDeleted(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageDeleteObserver.Stub.getDefaultImpl() != null) {
        IPackageDeleteObserver.Stub.getDefaultImpl().packageDeleted(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageDeleteObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */