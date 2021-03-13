package android.content.pm.dex;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements ISnapshotRuntimeProfileCallback {
  public static ISnapshotRuntimeProfileCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.dex.ISnapshotRuntimeProfileCallback";
  }
  
  public void onError(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
        ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onError(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSuccess(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
        ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onSuccess(paramParcelFileDescriptor);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ISnapshotRuntimeProfileCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */