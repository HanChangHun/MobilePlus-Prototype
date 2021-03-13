package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBiometricServiceLockoutResetCallback extends IInterface {
  void onLockoutReset(long paramLong, IRemoteCallback paramIRemoteCallback) throws RemoteException;
  
  public static class Default implements IBiometricServiceLockoutResetCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onLockoutReset(long param1Long, IRemoteCallback param1IRemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBiometricServiceLockoutResetCallback {
    private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricServiceLockoutResetCallback";
    
    static final int TRANSACTION_onLockoutReset = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
    }
    
    public static IBiometricServiceLockoutResetCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
      return (iInterface != null && iInterface instanceof IBiometricServiceLockoutResetCallback) ? (IBiometricServiceLockoutResetCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBiometricServiceLockoutResetCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onLockoutReset";
    }
    
    public static boolean setDefaultImpl(IBiometricServiceLockoutResetCallback param1IBiometricServiceLockoutResetCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBiometricServiceLockoutResetCallback != null) {
          Proxy.sDefaultImpl = param1IBiometricServiceLockoutResetCallback;
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
        param1Parcel2.writeString("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
      onLockoutReset(param1Parcel1.readLong(), IRemoteCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
      return true;
    }
    
    private static class Proxy implements IBiometricServiceLockoutResetCallback {
      public static IBiometricServiceLockoutResetCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.biometrics.IBiometricServiceLockoutResetCallback";
      }
      
      public void onLockoutReset(long param2Long, IRemoteCallback param2IRemoteCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
          parcel.writeLong(param2Long);
          if (param2IRemoteCallback != null) {
            iBinder = param2IRemoteCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceLockoutResetCallback.Stub.getDefaultImpl() != null) {
            IBiometricServiceLockoutResetCallback.Stub.getDefaultImpl().onLockoutReset(param2Long, param2IRemoteCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBiometricServiceLockoutResetCallback {
    public static IBiometricServiceLockoutResetCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.biometrics.IBiometricServiceLockoutResetCallback";
    }
    
    public void onLockoutReset(long param1Long, IRemoteCallback param1IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
        parcel.writeLong(param1Long);
        if (param1IRemoteCallback != null) {
          iBinder = param1IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceLockoutResetCallback.Stub.getDefaultImpl() != null) {
          IBiometricServiceLockoutResetCallback.Stub.getDefaultImpl().onLockoutReset(param1Long, param1IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceLockoutResetCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */