package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IActivityRecognitionHardwareWatcher {
  public static IActivityRecognitionHardwareWatcher sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IActivityRecognitionHardwareWatcher";
  }
  
  public void onInstanceChanged(IActivityRecognitionHardware paramIActivityRecognitionHardware) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareWatcher");
      if (paramIActivityRecognitionHardware != null) {
        iBinder = paramIActivityRecognitionHardware.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl() != null) {
        IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl().onInstanceChanged(paramIActivityRecognitionHardware);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareWatcher$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */