package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBiometricServiceLockoutResetCallback {
  private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricServiceLockoutResetCallback";
  
  static final int TRANSACTION_onLockoutReset = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
  }
  
  public static IBiometricServiceLockoutResetCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
    return (iInterface != null && iInterface instanceof IBiometricServiceLockoutResetCallback) ? (IBiometricServiceLockoutResetCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBiometricServiceLockoutResetCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onLockoutReset";
  }
  
  public static boolean setDefaultImpl(IBiometricServiceLockoutResetCallback paramIBiometricServiceLockoutResetCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBiometricServiceLockoutResetCallback != null) {
        Proxy.sDefaultImpl = paramIBiometricServiceLockoutResetCallback;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
    onLockoutReset(paramParcel1.readLong(), IRemoteCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceLockoutResetCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */