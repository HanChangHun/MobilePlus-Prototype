package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IActivityRecognitionHardwareWatcher extends IInterface {
  void onInstanceChanged(IActivityRecognitionHardware paramIActivityRecognitionHardware) throws RemoteException;
  
  public static class Default implements IActivityRecognitionHardwareWatcher {
    public IBinder asBinder() {
      return null;
    }
    
    public void onInstanceChanged(IActivityRecognitionHardware param1IActivityRecognitionHardware) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IActivityRecognitionHardwareWatcher {
    private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareWatcher";
    
    static final int TRANSACTION_onInstanceChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareWatcher");
    }
    
    public static IActivityRecognitionHardwareWatcher asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareWatcher");
      return (iInterface != null && iInterface instanceof IActivityRecognitionHardwareWatcher) ? (IActivityRecognitionHardwareWatcher)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityRecognitionHardwareWatcher getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onInstanceChanged";
    }
    
    public static boolean setDefaultImpl(IActivityRecognitionHardwareWatcher param1IActivityRecognitionHardwareWatcher) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityRecognitionHardwareWatcher != null) {
          Proxy.sDefaultImpl = param1IActivityRecognitionHardwareWatcher;
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
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.location.IActivityRecognitionHardwareWatcher");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardwareWatcher");
      onInstanceChanged(IActivityRecognitionHardware.Stub.asInterface(param1Parcel1.readStrongBinder()));
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IActivityRecognitionHardwareWatcher {
      public static IActivityRecognitionHardwareWatcher sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IActivityRecognitionHardwareWatcher";
      }
      
      public void onInstanceChanged(IActivityRecognitionHardware param2IActivityRecognitionHardware) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareWatcher");
          if (param2IActivityRecognitionHardware != null) {
            iBinder = param2IActivityRecognitionHardware.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl() != null) {
            IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl().onInstanceChanged(param2IActivityRecognitionHardware);
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
  
  private static class Proxy implements IActivityRecognitionHardwareWatcher {
    public static IActivityRecognitionHardwareWatcher sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardwareWatcher";
    }
    
    public void onInstanceChanged(IActivityRecognitionHardware param1IActivityRecognitionHardware) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareWatcher");
        if (param1IActivityRecognitionHardware != null) {
          iBinder = param1IActivityRecognitionHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl() != null) {
          IActivityRecognitionHardwareWatcher.Stub.getDefaultImpl().onInstanceChanged(param1IActivityRecognitionHardware);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */