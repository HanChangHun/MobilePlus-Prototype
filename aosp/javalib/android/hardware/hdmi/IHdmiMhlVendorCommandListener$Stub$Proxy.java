package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiMhlVendorCommandListener {
  public static IHdmiMhlVendorCommandListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
  }
  
  public void onReceived(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiMhlVendorCommandListener.Stub.getDefaultImpl() != null) {
        IHdmiMhlVendorCommandListener.Stub.getDefaultImpl().onReceived(paramInt1, paramInt2, paramInt3, paramArrayOfbyte);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiMhlVendorCommandListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */