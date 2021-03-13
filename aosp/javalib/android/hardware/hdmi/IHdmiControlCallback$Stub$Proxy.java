package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiControlCallback {
  public static IHdmiControlCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiControlCallback";
  }
  
  public void onComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiControlCallback.Stub.getDefaultImpl() != null) {
        IHdmiControlCallback.Stub.getDefaultImpl().onComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */