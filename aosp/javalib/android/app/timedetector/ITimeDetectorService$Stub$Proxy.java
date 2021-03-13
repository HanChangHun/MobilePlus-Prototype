package android.app.timedetector;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITimeDetectorService {
  public static ITimeDetectorService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.timedetector.ITimeDetectorService";
  }
  
  public void suggestManualTime(ManualTimeSuggestion paramManualTimeSuggestion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
      if (paramManualTimeSuggestion != null) {
        parcel1.writeInt(1);
        paramManualTimeSuggestion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
        ITimeDetectorService.Stub.getDefaultImpl().suggestManualTime(paramManualTimeSuggestion);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void suggestNetworkTime(NetworkTimeSuggestion paramNetworkTimeSuggestion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
      if (paramNetworkTimeSuggestion != null) {
        parcel1.writeInt(1);
        paramNetworkTimeSuggestion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
        ITimeDetectorService.Stub.getDefaultImpl().suggestNetworkTime(paramNetworkTimeSuggestion);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void suggestTelephonyTime(TelephonyTimeSuggestion paramTelephonyTimeSuggestion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
      if (paramTelephonyTimeSuggestion != null) {
        parcel1.writeInt(1);
        paramTelephonyTimeSuggestion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
        ITimeDetectorService.Stub.getDefaultImpl().suggestTelephonyTime(paramTelephonyTimeSuggestion);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/ITimeDetectorService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */