package android.hardware.display;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IVirtualDisplayCallback {
  public static IVirtualDisplayCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.display.IVirtualDisplayCallback";
  }
  
  public void onPaused() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
      if (!this.mRemote.transact(1, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
        IVirtualDisplayCallback.Stub.getDefaultImpl().onPaused();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onResumed() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
      if (!this.mRemote.transact(2, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
        IVirtualDisplayCallback.Stub.getDefaultImpl().onResumed();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onStopped() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
      if (!this.mRemote.transact(3, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
        IVirtualDisplayCallback.Stub.getDefaultImpl().onStopped();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IVirtualDisplayCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */