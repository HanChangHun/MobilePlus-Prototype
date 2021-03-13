package android.hardware.biometrics;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBiometricService {
  public static IBiometricService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiver paramIBiometricServiceReceiver, String paramString, Bundle paramBundle, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      try {
        IBinder iBinder;
        parcel1.writeStrongBinder(paramIBinder);
        parcel1.writeLong(paramLong);
        parcel1.writeInt(paramInt1);
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
        parcel1.writeInt(paramInt2);
        parcel1.writeInt(paramInt3);
        parcel1.writeInt(paramInt4);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().authenticate(paramIBinder, paramLong, paramInt1, paramIBiometricServiceReceiver, paramString, paramBundle, paramInt2, paramInt3, paramInt4);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public int canAuthenticate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        paramInt1 = IBiometricService.Stub.getDefaultImpl().canAuthenticate(paramString, paramInt1, paramInt2, paramInt3);
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
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        IBiometricService.Stub.getDefaultImpl().cancelAuthentication(paramIBinder, paramString, paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long[] getAuthenticatorIds(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null)
        return IBiometricService.Stub.getDefaultImpl().getAuthenticatorIds(paramInt); 
      parcel2.readException();
      return parcel2.createLongArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IBiometricService";
  }
  
  public boolean hasEnrolledBiometrics(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        bool = IBiometricService.Stub.getDefaultImpl().hasEnrolledBiometrics(paramInt, paramString);
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
  
  public void onReadyForAuthentication(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeInt(paramInt1);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        IBiometricService.Stub.getDefaultImpl().onReadyForAuthentication(paramInt1, paramBoolean, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerAuthenticator(int paramInt1, int paramInt2, int paramInt3, IBiometricAuthenticator paramIBiometricAuthenticator) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (paramIBiometricAuthenticator != null) {
        iBinder = paramIBiometricAuthenticator.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        IBiometricService.Stub.getDefaultImpl().registerAuthenticator(paramInt1, paramInt2, paramInt3, paramIBiometricAuthenticator);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      if (paramIBiometricEnabledOnKeyguardCallback != null) {
        iBinder = paramIBiometricEnabledOnKeyguardCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        IBiometricService.Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(paramIBiometricEnabledOnKeyguardCallback, paramInt);
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
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        IBiometricService.Stub.getDefaultImpl().resetLockout(paramArrayOfbyte);
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
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
        IBiometricService.Stub.getDefaultImpl().setActiveUser(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */