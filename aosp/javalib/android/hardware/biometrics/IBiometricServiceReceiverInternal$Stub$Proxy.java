package android.hardware.biometrics;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBiometricServiceReceiverInternal {
  public static IBiometricServiceReceiverInternal sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IBiometricServiceReceiverInternal";
  }
  
  public void onAcquired(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAcquired(paramInt, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationFailed() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAuthenticationFailed();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationSucceeded(boolean paramBoolean1, byte[] paramArrayOfbyte, boolean paramBoolean2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      boolean bool1 = false;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      parcel.writeByteArray(paramArrayOfbyte);
      boolean bool2 = bool1;
      if (paramBoolean2)
        bool2 = true; 
      parcel.writeInt(bool2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAuthenticationSucceeded(paramBoolean1, paramArrayOfbyte, paramBoolean2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDeviceCredentialPressed() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      if (!this.mRemote.transact(7, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onDeviceCredentialPressed();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDialogDismissed(int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      parcel.writeInt(paramInt);
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onDialogDismissed(paramInt, paramArrayOfbyte);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onError(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      parcel.writeInt(paramInt4);
      if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onError(paramInt1, paramInt2, paramInt3, paramInt4);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSystemEvent(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onSystemEvent(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTryAgainPressed() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onTryAgainPressed();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiverInternal$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */