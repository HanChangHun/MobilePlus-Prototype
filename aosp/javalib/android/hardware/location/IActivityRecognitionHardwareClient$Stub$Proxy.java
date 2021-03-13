package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IActivityRecognitionHardwareClient {
  public static IActivityRecognitionHardwareClient sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IActivityRecognitionHardwareClient";
  }
  
  public void onAvailabilityChanged(boolean paramBoolean, IActivityRecognitionHardware paramIActivityRecognitionHardware) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareClient");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramIActivityRecognitionHardware != null) {
        iBinder = paramIActivityRecognitionHardware.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IActivityRecognitionHardwareClient.Stub.getDefaultImpl() != null) {
        IActivityRecognitionHardwareClient.Stub.getDefaultImpl().onAvailabilityChanged(paramBoolean, paramIActivityRecognitionHardware);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareClient$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */