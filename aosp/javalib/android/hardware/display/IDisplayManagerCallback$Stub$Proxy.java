package android.hardware.display;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IDisplayManagerCallback {
  public static IDisplayManagerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.display.IDisplayManagerCallback";
  }
  
  public void onDisplayEvent(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.display.IDisplayManagerCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IDisplayManagerCallback.Stub.getDefaultImpl() != null) {
        IDisplayManagerCallback.Stub.getDefaultImpl().onDisplayEvent(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IDisplayManagerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */