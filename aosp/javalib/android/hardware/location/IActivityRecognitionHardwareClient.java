package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IActivityRecognitionHardwareClient extends IInterface {
  void onAvailabilityChanged(boolean paramBoolean, IActivityRecognitionHardware paramIActivityRecognitionHardware) throws RemoteException;
  
  public static class Default implements IActivityRecognitionHardwareClient {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAvailabilityChanged(boolean param1Boolean, IActivityRecognitionHardware param1IActivityRecognitionHardware) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IActivityRecognitionHardwareClient {
    private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareClient";
    
    static final int TRANSACTION_onAvailabilityChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareClient");
    }
    
    public static IActivityRecognitionHardwareClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareClient");
      return (iInterface != null && iInterface instanceof IActivityRecognitionHardwareClient) ? (IActivityRecognitionHardwareClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityRecognitionHardwareClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onAvailabilityChanged";
    }
    
    public static boolean setDefaultImpl(IActivityRecognitionHardwareClient param1IActivityRecognitionHardwareClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityRecognitionHardwareClient != null) {
          Proxy.sDefaultImpl = param1IActivityRecognitionHardwareClient;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.location.IActivityRecognitionHardwareClient");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardwareClient");
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onAvailabilityChanged(bool, IActivityRecognitionHardware.Stub.asInterface(param1Parcel1.readStrongBinder()));
      return true;
    }
    
    private static class Proxy implements IActivityRecognitionHardwareClient {
      public static IActivityRecognitionHardwareClient sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IActivityRecognitionHardwareClient";
      }
      
      public void onAvailabilityChanged(boolean param2Boolean, IActivityRecognitionHardware param2IActivityRecognitionHardware) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          IBinder iBinder;
          parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareClient");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2IActivityRecognitionHardware != null) {
            iBinder = param2IActivityRecognitionHardware.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && IActivityRecognitionHardwareClient.Stub.getDefaultImpl() != null) {
            IActivityRecognitionHardwareClient.Stub.getDefaultImpl().onAvailabilityChanged(param2Boolean, param2IActivityRecognitionHardware);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IActivityRecognitionHardwareClient {
    public static IActivityRecognitionHardwareClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardwareClient";
    }
    
    public void onAvailabilityChanged(boolean param1Boolean, IActivityRecognitionHardware param1IActivityRecognitionHardware) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareClient");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1IActivityRecognitionHardware != null) {
          iBinder = param1IActivityRecognitionHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IActivityRecognitionHardwareClient.Stub.getDefaultImpl() != null) {
          IActivityRecognitionHardwareClient.Stub.getDefaultImpl().onAvailabilityChanged(param1Boolean, param1IActivityRecognitionHardware);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */