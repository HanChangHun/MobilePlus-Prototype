package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiHotplugEventListener {
  public static IHdmiHotplugEventListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiHotplugEventListener";
  }
  
  public void onReceived(HdmiHotplugEvent paramHdmiHotplugEvent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiHotplugEventListener");
      if (paramHdmiHotplugEvent != null) {
        parcel.writeInt(1);
        paramHdmiHotplugEvent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiHotplugEventListener.Stub.getDefaultImpl() != null) {
        IHdmiHotplugEventListener.Stub.getDefaultImpl().onReceived(paramHdmiHotplugEvent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiHotplugEventListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */