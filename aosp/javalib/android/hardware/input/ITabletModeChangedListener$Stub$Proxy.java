package android.hardware.input;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITabletModeChangedListener {
  public static ITabletModeChangedListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.input.ITabletModeChangedListener";
  }
  
  public void onTabletModeChanged(long paramLong, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.input.ITabletModeChangedListener");
      parcel.writeLong(paramLong);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && ITabletModeChangedListener.Stub.getDefaultImpl() != null) {
        ITabletModeChangedListener.Stub.getDefaultImpl().onTabletModeChanged(paramLong, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/ITabletModeChangedListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */