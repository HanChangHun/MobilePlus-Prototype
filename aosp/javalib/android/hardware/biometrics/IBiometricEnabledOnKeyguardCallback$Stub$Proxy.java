package android.hardware.biometrics;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBiometricEnabledOnKeyguardCallback {
  public static IBiometricEnabledOnKeyguardCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback";
  }
  
  public void onChanged(BiometricSourceType paramBiometricSourceType, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback");
      boolean bool = false;
      if (paramBiometricSourceType != null) {
        parcel.writeInt(1);
        paramBiometricSourceType.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricEnabledOnKeyguardCallback.Stub.getDefaultImpl() != null) {
        IBiometricEnabledOnKeyguardCallback.Stub.getDefaultImpl().onChanged(paramBiometricSourceType, paramBoolean, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricEnabledOnKeyguardCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */