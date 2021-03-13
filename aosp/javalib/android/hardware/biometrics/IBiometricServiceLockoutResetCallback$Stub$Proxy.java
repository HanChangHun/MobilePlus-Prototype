package android.hardware.biometrics;

import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBiometricServiceLockoutResetCallback {
  public static IBiometricServiceLockoutResetCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IBiometricServiceLockoutResetCallback";
  }
  
  public void onLockoutReset(long paramLong, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceLockoutResetCallback");
      parcel.writeLong(paramLong);
      if (paramIRemoteCallback != null) {
        iBinder = paramIRemoteCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceLockoutResetCallback.Stub.getDefaultImpl() != null) {
        IBiometricServiceLockoutResetCallback.Stub.getDefaultImpl().onLockoutReset(paramLong, paramIRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceLockoutResetCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */