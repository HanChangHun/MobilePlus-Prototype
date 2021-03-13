package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiDeviceEventListener {
  public static IHdmiDeviceEventListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiDeviceEventListener";
  }
  
  public void onStatusChanged(HdmiDeviceInfo paramHdmiDeviceInfo, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiDeviceEventListener");
      if (paramHdmiDeviceInfo != null) {
        parcel.writeInt(1);
        paramHdmiDeviceInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiDeviceEventListener.Stub.getDefaultImpl() != null) {
        IHdmiDeviceEventListener.Stub.getDefaultImpl().onStatusChanged(paramHdmiDeviceInfo, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiDeviceEventListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */