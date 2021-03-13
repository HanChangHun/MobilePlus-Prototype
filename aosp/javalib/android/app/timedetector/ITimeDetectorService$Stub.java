package android.app.timedetector;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITimeDetectorService {
  private static final String DESCRIPTOR = "android.app.timedetector.ITimeDetectorService";
  
  static final int TRANSACTION_suggestManualTime = 1;
  
  static final int TRANSACTION_suggestNetworkTime = 2;
  
  static final int TRANSACTION_suggestTelephonyTime = 3;
  
  public Stub() {
    attachInterface(this, "android.app.timedetector.ITimeDetectorService");
  }
  
  public static ITimeDetectorService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.timedetector.ITimeDetectorService");
    return (iInterface != null && iInterface instanceof ITimeDetectorService) ? (ITimeDetectorService)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITimeDetectorService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "suggestTelephonyTime") : "suggestNetworkTime") : "suggestManualTime";
  }
  
  public static boolean setDefaultImpl(ITimeDetectorService paramITimeDetectorService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITimeDetectorService != null) {
        Proxy.sDefaultImpl = paramITimeDetectorService;
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
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.app.timedetector.ITimeDetectorService");
          return true;
        } 
        paramParcel1.enforceInterface("android.app.timedetector.ITimeDetectorService");
        if (paramParcel1.readInt() != 0) {
          TelephonyTimeSuggestion telephonyTimeSuggestion = (TelephonyTimeSuggestion)TelephonyTimeSuggestion.CREATOR.createFromParcel(paramParcel1);
        } else {
          paramParcel1 = null;
        } 
        suggestTelephonyTime((TelephonyTimeSuggestion)paramParcel1);
        paramParcel2.writeNoException();
        return true;
      } 
      paramParcel1.enforceInterface("android.app.timedetector.ITimeDetectorService");
      if (paramParcel1.readInt() != 0) {
        NetworkTimeSuggestion networkTimeSuggestion = (NetworkTimeSuggestion)NetworkTimeSuggestion.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      suggestNetworkTime((NetworkTimeSuggestion)paramParcel1);
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.timedetector.ITimeDetectorService");
    if (paramParcel1.readInt() != 0) {
      ManualTimeSuggestion manualTimeSuggestion = (ManualTimeSuggestion)ManualTimeSuggestion.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    suggestManualTime((ManualTimeSuggestion)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements ITimeDetectorService {
    public static ITimeDetectorService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timedetector.ITimeDetectorService";
    }
    
    public void suggestManualTime(ManualTimeSuggestion param2ManualTimeSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
        if (param2ManualTimeSuggestion != null) {
          parcel1.writeInt(1);
          param2ManualTimeSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
          ITimeDetectorService.Stub.getDefaultImpl().suggestManualTime(param2ManualTimeSuggestion);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suggestNetworkTime(NetworkTimeSuggestion param2NetworkTimeSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
        if (param2NetworkTimeSuggestion != null) {
          parcel1.writeInt(1);
          param2NetworkTimeSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
          ITimeDetectorService.Stub.getDefaultImpl().suggestNetworkTime(param2NetworkTimeSuggestion);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suggestTelephonyTime(TelephonyTimeSuggestion param2TelephonyTimeSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
        if (param2TelephonyTimeSuggestion != null) {
          parcel1.writeInt(1);
          param2TelephonyTimeSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
          ITimeDetectorService.Stub.getDefaultImpl().suggestTelephonyTime(param2TelephonyTimeSuggestion);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/ITimeDetectorService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */