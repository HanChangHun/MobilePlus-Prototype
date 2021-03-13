package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageStatsObserver {
  public static IPackageStatsObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageStatsObserver";
  }
  
  public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
      boolean bool = false;
      if (paramPackageStats != null) {
        parcel.writeInt(1);
        paramPackageStats.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageStatsObserver.Stub.getDefaultImpl() != null) {
        IPackageStatsObserver.Stub.getDefaultImpl().onGetStatsCompleted(paramPackageStats, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageStatsObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */