package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiControlStatusChangeListener {
  public static IHdmiControlStatusChangeListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiControlStatusChangeListener";
  }
  
  public void onStatusChange(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlStatusChangeListener");
      boolean bool1 = false;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      boolean bool2 = bool1;
      if (paramBoolean2)
        bool2 = true; 
      parcel.writeInt(bool2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiControlStatusChangeListener.Stub.getDefaultImpl() != null) {
        IHdmiControlStatusChangeListener.Stub.getDefaultImpl().onStatusChange(paramBoolean1, paramBoolean2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlStatusChangeListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */