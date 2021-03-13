package android.app.timezonedetector;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITimeZoneDetectorService {
  public static ITimeZoneDetectorService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.timezonedetector.ITimeZoneDetectorService";
  }
  
  public void suggestManualTimeZone(ManualTimeZoneSuggestion paramManualTimeZoneSuggestion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.timezonedetector.ITimeZoneDetectorService");
      if (paramManualTimeZoneSuggestion != null) {
        parcel1.writeInt(1);
        paramManualTimeZoneSuggestion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITimeZoneDetectorService.Stub.getDefaultImpl() != null) {
        ITimeZoneDetectorService.Stub.getDefaultImpl().suggestManualTimeZone(paramManualTimeZoneSuggestion);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion paramTelephonyTimeZoneSuggestion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.timezonedetector.ITimeZoneDetectorService");
      if (paramTelephonyTimeZoneSuggestion != null) {
        parcel1.writeInt(1);
        paramTelephonyTimeZoneSuggestion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITimeZoneDetectorService.Stub.getDefaultImpl() != null) {
        ITimeZoneDetectorService.Stub.getDefaultImpl().suggestTelephonyTimeZone(paramTelephonyTimeZoneSuggestion);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/ITimeZoneDetectorService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */