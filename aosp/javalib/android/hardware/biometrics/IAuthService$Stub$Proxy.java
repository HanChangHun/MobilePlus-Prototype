package android.hardware.biometrics;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAuthService {
  public static IAuthService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt, IBiometricServiceReceiver paramIBiometricServiceReceiver, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeLong(paramLong);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt);
            if (paramIBiometricServiceReceiver != null) {
              iBinder = paramIBiometricServiceReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeString(paramString);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
              IAuthService.Stub.getDefaultImpl().authenticate(paramIBinder, paramLong, paramInt, paramIBiometricServiceReceiver, paramString, paramBundle);
              parcel2.recycle();
              parcel1.recycle();
              return;
            } 
            parcel2.readException();
            parcel2.recycle();
            parcel1.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public int canAuthenticate(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
        paramInt1 = IAuthService.Stub.getDefaultImpl().canAuthenticate(paramString, paramInt1, paramInt2);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
        IAuthService.Stub.getDefaultImpl().cancelAuthentication(paramIBinder, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long[] getAuthenticatorIds() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null)
        return IAuthService.Stub.getDefaultImpl().getAuthenticatorIds(); 
      parcel2.readException();
      return parcel2.createLongArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IAuthService";
  }
  
  public boolean hasEnrolledBiometrics(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
        bool = IAuthService.Stub.getDefaultImpl().hasEnrolledBiometrics(paramInt, paramString);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      if (paramIBiometricEnabledOnKeyguardCallback != null) {
        iBinder = paramIBiometricEnabledOnKeyguardCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
        IAuthService.Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(paramIBiometricEnabledOnKeyguardCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resetLockout(byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
        IAuthService.Stub.getDefaultImpl().resetLockout(paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setActiveUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
        IAuthService.Stub.getDefaultImpl().setActiveUser(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IAuthService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */