package android.hardware.radio;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ICloseHandle {
  public static ICloseHandle sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void close() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ICloseHandle");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICloseHandle.Stub.getDefaultImpl() != null) {
        ICloseHandle.Stub.getDefaultImpl().close();
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
    return "android.hardware.radio.ICloseHandle";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ICloseHandle$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */