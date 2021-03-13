package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiInputChangeListener {
  public static IHdmiInputChangeListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiInputChangeListener";
  }
  
  public void onChanged(HdmiDeviceInfo paramHdmiDeviceInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiInputChangeListener");
      if (paramHdmiDeviceInfo != null) {
        parcel.writeInt(1);
        paramHdmiDeviceInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiInputChangeListener.Stub.getDefaultImpl() != null) {
        IHdmiInputChangeListener.Stub.getDefaultImpl().onChanged(paramHdmiDeviceInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiInputChangeListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */