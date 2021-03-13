package android.content.pm;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPackageMoveObserver {
  public static IPackageMoveObserver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageMoveObserver";
  }
  
  public void onCreated(int paramInt, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
      parcel.writeInt(paramInt);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IPackageMoveObserver.Stub.getDefaultImpl() != null) {
        IPackageMoveObserver.Stub.getDefaultImpl().onCreated(paramInt, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onStatusChanged(int paramInt1, int paramInt2, long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IPackageMoveObserver");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(2, parcel, null, 1) && IPackageMoveObserver.Stub.getDefaultImpl() != null) {
        IPackageMoveObserver.Stub.getDefaultImpl().onStatusChanged(paramInt1, paramInt2, paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageMoveObserver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */