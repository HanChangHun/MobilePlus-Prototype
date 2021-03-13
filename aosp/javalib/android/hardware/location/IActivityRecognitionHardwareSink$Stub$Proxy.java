package android.hardware.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IActivityRecognitionHardwareSink {
  public static IActivityRecognitionHardwareSink sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IActivityRecognitionHardwareSink";
  }
  
  public void onActivityChanged(ActivityChangedEvent paramActivityChangedEvent) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareSink");
      if (paramActivityChangedEvent != null) {
        parcel1.writeInt(1);
        paramActivityChangedEvent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareSink.Stub.getDefaultImpl() != null) {
        IActivityRecognitionHardwareSink.Stub.getDefaultImpl().onActivityChanged(paramActivityChangedEvent);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareSink$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */