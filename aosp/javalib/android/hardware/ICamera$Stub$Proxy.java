package android.hardware;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ICamera {
  public static ICamera sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void disconnect() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICamera");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICamera.Stub.getDefaultImpl() != null) {
        ICamera.Stub.getDefaultImpl().disconnect();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.ICamera";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICamera$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */