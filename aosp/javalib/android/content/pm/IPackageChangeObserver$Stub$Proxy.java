package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageChangeObserver {
  public static IPackageChangeObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageChangeObserver";
  }
  
  public void onPackageChanged(PackageChangeEvent paramPackageChangeEvent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageChangeObserver");
      if (paramPackageChangeEvent != null) {
        parcel.writeInt(1);
        paramPackageChangeEvent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageChangeObserver.Stub.getDefaultImpl() != null) {
        IPackageChangeObserver.Stub.getDefaultImpl().onPackageChanged(paramPackageChangeEvent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageChangeObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */