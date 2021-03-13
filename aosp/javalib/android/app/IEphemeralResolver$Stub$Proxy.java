package android.app;

import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IEphemeralResolver {
  public static IEphemeralResolver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void getEphemeralIntentFilterList(IRemoteCallback paramIRemoteCallback, String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IEphemeralResolver");
      if (paramIRemoteCallback != null) {
        iBinder = paramIRemoteCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
        IEphemeralResolver.Stub.getDefaultImpl().getEphemeralIntentFilterList(paramIRemoteCallback, paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void getEphemeralResolveInfoList(IRemoteCallback paramIRemoteCallback, int[] paramArrayOfint, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IEphemeralResolver");
      if (paramIRemoteCallback != null) {
        iBinder = paramIRemoteCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeIntArray(paramArrayOfint);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
        IEphemeralResolver.Stub.getDefaultImpl().getEphemeralResolveInfoList(paramIRemoteCallback, paramArrayOfint, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IEphemeralResolver";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IEphemeralResolver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */