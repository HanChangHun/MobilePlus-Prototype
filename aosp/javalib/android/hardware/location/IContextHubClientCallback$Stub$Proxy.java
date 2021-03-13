package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IContextHubClientCallback {
  public static IContextHubClientCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IContextHubClientCallback";
  }
  
  public void onHubReset() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onHubReset();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onMessageFromNanoApp(NanoAppMessage paramNanoAppMessage) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      if (paramNanoAppMessage != null) {
        parcel.writeInt(1);
        paramNanoAppMessage.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onMessageFromNanoApp(paramNanoAppMessage);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNanoAppAborted(long paramLong, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppAborted(paramLong, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNanoAppDisabled(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(7, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppDisabled(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNanoAppEnabled(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(6, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppEnabled(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNanoAppLoaded(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(4, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppLoaded(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNanoAppUnloaded(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(5, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
        IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppUnloaded(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClientCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */