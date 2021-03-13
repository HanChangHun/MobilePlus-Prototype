package android.hardware;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ICameraServiceProxy {
  public static ICameraServiceProxy sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.ICameraServiceProxy";
  }
  
  public void notifyCameraState(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
      parcel.writeString(paramString1);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeString(paramString2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
        ICameraServiceProxy.Stub.getDefaultImpl().notifyCameraState(paramString1, paramInt1, paramInt2, paramString2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void pingForUserUpdate() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
      if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
        ICameraServiceProxy.Stub.getDefaultImpl().pingForUserUpdate();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceProxy$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */