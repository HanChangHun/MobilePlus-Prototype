package android.app.timedetector;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITimeDetectorService extends IInterface {
  void suggestManualTime(ManualTimeSuggestion paramManualTimeSuggestion) throws RemoteException;
  
  void suggestNetworkTime(NetworkTimeSuggestion paramNetworkTimeSuggestion) throws RemoteException;
  
  void suggestTelephonyTime(TelephonyTimeSuggestion paramTelephonyTimeSuggestion) throws RemoteException;
  
  public static class Default implements ITimeDetectorService {
    public IBinder asBinder() {
      return null;
    }
    
    public void suggestManualTime(ManualTimeSuggestion param1ManualTimeSuggestion) throws RemoteException {}
    
    public void suggestNetworkTime(NetworkTimeSuggestion param1NetworkTimeSuggestion) throws RemoteException {}
    
    public void suggestTelephonyTime(TelephonyTimeSuggestion param1TelephonyTimeSuggestion) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITimeDetectorService {
    private static final String DESCRIPTOR = "android.app.timedetector.ITimeDetectorService";
    
    static final int TRANSACTION_suggestManualTime = 1;
    
    static final int TRANSACTION_suggestNetworkTime = 2;
    
    static final int TRANSACTION_suggestTelephonyTime = 3;
    
    public Stub() {
      attachInterface(this, "android.app.timedetector.ITimeDetectorService");
    }
    
    public static ITimeDetectorService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.timedetector.ITimeDetectorService");
      return (iInterface != null && iInterface instanceof ITimeDetectorService) ? (ITimeDetectorService)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITimeDetectorService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "suggestTelephonyTime") : "suggestNetworkTime") : "suggestManualTime";
    }
    
    public static boolean setDefaultImpl(ITimeDetectorService param1ITimeDetectorService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITimeDetectorService != null) {
          Proxy.sDefaultImpl = param1ITimeDetectorService;
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
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.app.timedetector.ITimeDetectorService");
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.timedetector.ITimeDetectorService");
          if (param1Parcel1.readInt() != 0) {
            TelephonyTimeSuggestion telephonyTimeSuggestion = (TelephonyTimeSuggestion)TelephonyTimeSuggestion.CREATOR.createFromParcel(param1Parcel1);
          } else {
            param1Parcel1 = null;
          } 
          suggestTelephonyTime((TelephonyTimeSuggestion)param1Parcel1);
          param1Parcel2.writeNoException();
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.timedetector.ITimeDetectorService");
        if (param1Parcel1.readInt() != 0) {
          NetworkTimeSuggestion networkTimeSuggestion = (NetworkTimeSuggestion)NetworkTimeSuggestion.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        suggestNetworkTime((NetworkTimeSuggestion)param1Parcel1);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.timedetector.ITimeDetectorService");
      if (param1Parcel1.readInt() != 0) {
        ManualTimeSuggestion manualTimeSuggestion = (ManualTimeSuggestion)ManualTimeSuggestion.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      suggestManualTime((ManualTimeSuggestion)param1Parcel1);
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements ITimeDetectorService {
    public static ITimeDetectorService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timedetector.ITimeDetectorService";
    }
    
    public void suggestManualTime(ManualTimeSuggestion param1ManualTimeSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
        if (param1ManualTimeSuggestion != null) {
          parcel1.writeInt(1);
          param1ManualTimeSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
          ITimeDetectorService.Stub.getDefaultImpl().suggestManualTime(param1ManualTimeSuggestion);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suggestNetworkTime(NetworkTimeSuggestion param1NetworkTimeSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
        if (param1NetworkTimeSuggestion != null) {
          parcel1.writeInt(1);
          param1NetworkTimeSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
          ITimeDetectorService.Stub.getDefaultImpl().suggestNetworkTime(param1NetworkTimeSuggestion);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suggestTelephonyTime(TelephonyTimeSuggestion param1TelephonyTimeSuggestion) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.timedetector.ITimeDetectorService");
        if (param1TelephonyTimeSuggestion != null) {
          parcel1.writeInt(1);
          param1TelephonyTimeSuggestion.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITimeDetectorService.Stub.getDefaultImpl() != null) {
          ITimeDetectorService.Stub.getDefaultImpl().suggestTelephonyTime(param1TelephonyTimeSuggestion);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/ITimeDetectorService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */