package android.hardware.biometrics;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBiometricAuthenticator {
  public static IBiometricAuthenticator sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeInt(paramInt1);
            try {
              parcel1.writeInt(paramInt2);
              try {
                boolean bool;
                parcel1.writeInt(paramInt3);
                if (paramBoolean) {
                  bool = true;
                } else {
                  bool = false;
                } 
                parcel1.writeInt(bool);
                try {
                  if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
                    IBiometricAuthenticator.Stub.getDefaultImpl().cancelAuthenticationFromService(paramIBinder, paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
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
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public long getAuthenticatorId(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null)
        return IBiometricAuthenticator.Stub.getDefaultImpl().getAuthenticatorId(paramInt); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IBiometricAuthenticator";
  }
  
  public boolean hasEnrolledTemplates(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
        bool = IBiometricAuthenticator.Stub.getDefaultImpl().hasEnrolledTemplates(paramInt, paramString);
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
  
  public boolean isHardwareDetected(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
        bool = IBiometricAuthenticator.Stub.getDefaultImpl().isHardwareDetected(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void prepareForAuthentication(boolean paramBoolean, IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeLong(paramLong);
      parcel1.writeInt(paramInt1);
      if (paramIBiometricServiceReceiverInternal != null) {
        iBinder = paramIBiometricServiceReceiverInternal.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeInt(paramInt4);
      parcel1.writeInt(paramInt5);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
        IBiometricAuthenticator.Stub.getDefaultImpl().prepareForAuthentication(paramBoolean, paramIBinder, paramLong, paramInt1, paramIBiometricServiceReceiverInternal, paramString, paramInt2, paramInt3, paramInt4, paramInt5);
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
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
        IBiometricAuthenticator.Stub.getDefaultImpl().resetLockout(paramArrayOfbyte);
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
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
        IBiometricAuthenticator.Stub.getDefaultImpl().setActiveUser(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startPreparedClient(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
        IBiometricAuthenticator.Stub.getDefaultImpl().startPreparedClient(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricAuthenticator$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */