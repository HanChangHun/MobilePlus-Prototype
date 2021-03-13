package android.app.timezonedetector;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITimeZoneDetectorService extends IInterface {
  void suggestManualTimeZone(ManualTimeZoneSuggestion paramManualTimeZoneSuggestion) throws RemoteException;
  
  void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion paramTelephonyTimeZoneSuggestion) throws RemoteException;
  
  public static class Default implements ITimeZoneDetectorService {
    public IBinder asBinder() {
      return null;
    }
    
    public void suggestManualTimeZone(ManualTimeZoneSuggestion param1ManualTimeZoneSuggestion) throws RemoteException {}
    
    public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion param1TelephonyTimeZoneSuggestion) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITimeZoneDetectorService {
    private static final String DESCRIPTOR = "android.app.timezonedetector.ITimeZoneDetectorService";
    
    static final int TRANSACTION_suggestManualTimeZone = 1;
    
    static final int TRANSACTION_suggestTelephonyTimeZone = 2;
    
    public Stub() {
      attachInterface(this, "android.app.timezonedetector.ITimeZoneDetectorService");
    }
    
    public static ITimeZoneDetectorService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.timezonedetector.ITimeZoneDetectorService");
      return (iInterface != null && iInterface instanceof ITimeZoneDetectorService) ? (ITimeZoneDetectorService)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITimeZoneDetectorService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "suggestTelephonyTimeZone") : "suggestManualTimeZone";
    }
    
    public static boolean setDefaultImpl(ITimeZoneDetectorService param1ITimeZoneDetectorService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITimeZoneDetectorService != null) {
          Proxy.sDefaultImpl = param1ITimeZoneDetectorService;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.timezonedetector.ITimeZoneDetectorService");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.timezonedetector.ITimeZoneDetectorService");
        if (param1Parcel1.readInt() != 0) {
          TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion = (TelephonyTimeZoneSuggestion)TelephonyTimeZoneSuggestion.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        suggestTelephonyTimeZone((TelephonyTimeZoneSuggestion)param1Parcel1);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.timezonedetector.ITimeZoneDetectorService");
      if (param1Parcel1.readInt() != 0) {
        ManualTimeZoneSuggestion manualTimeZoneSuggestion = (ManualTimeZoneSuggestion)ManualTimeZoneSuggestion.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      suggestManualTimeZone((ManualTimeZoneSuggestion)param1Parcel1);
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements ITimeZoneDetectorService {
    public static ITimeZoneDetectorService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timezonedetector.ITimeZoneDetectorService";
    }
    
    public void suggestManualTimeZone(ManualTimeZoneSuggestion param1ManualTimeZoneSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timezonedetector.ITimeZoneDetectorService");
        if (param1ManualTimeZoneSuggestion != null) {
          parcel1.writeInt(1);
          param1ManualTimeZoneSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITimeZoneDetectorService.Stub.getDefaultImpl() != null) {
          ITimeZoneDetectorService.Stub.getDefaultImpl().suggestManualTimeZone(param1ManualTimeZoneSuggestion);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion param1TelephonyTimeZoneSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timezonedetector.ITimeZoneDetectorService");
        if (param1TelephonyTimeZoneSuggestion != null) {
          parcel1.writeInt(1);
          param1TelephonyTimeZoneSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITimeZoneDetectorService.Stub.getDefaultImpl() != null) {
          ITimeZoneDetectorService.Stub.getDefaultImpl().suggestTelephonyTimeZone(param1TelephonyTimeZoneSuggestion);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/ITimeZoneDetectorService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */