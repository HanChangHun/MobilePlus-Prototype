package android.app.timezonedetector;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITimeZoneDetectorService {
  private static final String DESCRIPTOR = "android.app.timezonedetector.ITimeZoneDetectorService";
  
  static final int TRANSACTION_suggestManualTimeZone = 1;
  
  static final int TRANSACTION_suggestTelephonyTimeZone = 2;
  
  public Stub() {
    attachInterface(this, "android.app.timezonedetector.ITimeZoneDetectorService");
  }
  
  public static ITimeZoneDetectorService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.timezonedetector.ITimeZoneDetectorService");
    return (iInterface != null && iInterface instanceof ITimeZoneDetectorService) ? (ITimeZoneDetectorService)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITimeZoneDetectorService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "suggestTelephonyTimeZone") : "suggestManualTimeZone";
  }
  
  public static boolean setDefaultImpl(ITimeZoneDetectorService paramITimeZoneDetectorService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITimeZoneDetectorService != null) {
        Proxy.sDefaultImpl = paramITimeZoneDetectorService;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.timezonedetector.ITimeZoneDetectorService");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.timezonedetector.ITimeZoneDetectorService");
      if (paramParcel1.readInt() != 0) {
        TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion = (TelephonyTimeZoneSuggestion)TelephonyTimeZoneSuggestion.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      suggestTelephonyTimeZone((TelephonyTimeZoneSuggestion)paramParcel1);
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.timezonedetector.ITimeZoneDetectorService");
    if (paramParcel1.readInt() != 0) {
      ManualTimeZoneSuggestion manualTimeZoneSuggestion = (ManualTimeZoneSuggestion)ManualTimeZoneSuggestion.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    suggestManualTimeZone((ManualTimeZoneSuggestion)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements ITimeZoneDetectorService {
    public static ITimeZoneDetectorService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timezonedetector.ITimeZoneDetectorService";
    }
    
    public void suggestManualTimeZone(ManualTimeZoneSuggestion param2ManualTimeZoneSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timezonedetector.ITimeZoneDetectorService");
        if (param2ManualTimeZoneSuggestion != null) {
          parcel1.writeInt(1);
          param2ManualTimeZoneSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITimeZoneDetectorService.Stub.getDefaultImpl() != null) {
          ITimeZoneDetectorService.Stub.getDefaultImpl().suggestManualTimeZone(param2ManualTimeZoneSuggestion);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion param2TelephonyTimeZoneSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timezonedetector.ITimeZoneDetectorService");
        if (param2TelephonyTimeZoneSuggestion != null) {
          parcel1.writeInt(1);
          param2TelephonyTimeZoneSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITimeZoneDetectorService.Stub.getDefaultImpl() != null) {
          ITimeZoneDetectorService.Stub.getDefaultImpl().suggestTelephonyTimeZone(param2TelephonyTimeZoneSuggestion);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/ITimeZoneDetectorService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */