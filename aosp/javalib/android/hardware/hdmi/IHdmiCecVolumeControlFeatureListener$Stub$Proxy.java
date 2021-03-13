package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiCecVolumeControlFeatureListener {
  public static IHdmiCecVolumeControlFeatureListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";
  }
  
  public void onHdmiCecVolumeControlFeature(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiCecVolumeControlFeatureListener.Stub.getDefaultImpl() != null) {
        IHdmiCecVolumeControlFeatureListener.Stub.getDefaultImpl().onHdmiCecVolumeControlFeature(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiCecVolumeControlFeatureListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */