package android.hardware;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISensorPrivacyListener {
  public static ISensorPrivacyListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.ISensorPrivacyListener";
  }
  
  public void onSensorPrivacyChanged(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.ISensorPrivacyListener");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && ISensorPrivacyListener.Stub.getDefaultImpl() != null) {
        ISensorPrivacyListener.Stub.getDefaultImpl().onSensorPrivacyChanged(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */