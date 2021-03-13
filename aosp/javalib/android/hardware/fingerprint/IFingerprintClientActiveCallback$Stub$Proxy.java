package android.hardware.fingerprint;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IFingerprintClientActiveCallback {
  public static IFingerprintClientActiveCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.fingerprint.IFingerprintClientActiveCallback";
  }
  
  public void onClientActiveChanged(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintClientActiveCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintClientActiveCallback.Stub.getDefaultImpl() != null) {
        IFingerprintClientActiveCallback.Stub.getDefaultImpl().onClientActiveChanged(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintClientActiveCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */