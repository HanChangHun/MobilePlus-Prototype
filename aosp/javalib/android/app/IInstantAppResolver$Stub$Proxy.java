package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IInstantAppResolver {
  public static IInstantAppResolver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void getInstantAppIntentFilterList(InstantAppRequestInfo paramInstantAppRequestInfo, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IInstantAppResolver");
      if (paramInstantAppRequestInfo != null) {
        parcel.writeInt(1);
        paramInstantAppRequestInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramIRemoteCallback != null) {
        iBinder = paramIRemoteCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
        IInstantAppResolver.Stub.getDefaultImpl().getInstantAppIntentFilterList(paramInstantAppRequestInfo, paramIRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void getInstantAppResolveInfoList(InstantAppRequestInfo paramInstantAppRequestInfo, int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IInstantAppResolver");
      if (paramInstantAppRequestInfo != null) {
        parcel.writeInt(1);
        paramInstantAppRequestInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (paramIRemoteCallback != null) {
        iBinder = paramIRemoteCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
        IInstantAppResolver.Stub.getDefaultImpl().getInstantAppResolveInfoList(paramInstantAppRequestInfo, paramInt, paramIRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IInstantAppResolver";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstantAppResolver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */