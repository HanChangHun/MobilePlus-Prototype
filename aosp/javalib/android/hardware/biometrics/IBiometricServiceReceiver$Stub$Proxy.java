package android.hardware.biometrics;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBiometricServiceReceiver {
  public static IBiometricServiceReceiver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.biometrics.IBiometricServiceReceiver";
  }
  
  public void onAcquired(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiver.Stub.getDefaultImpl().onAcquired(paramInt, paramString);
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
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
      if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationSucceeded(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDialogDismissed(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiver.Stub.getDefaultImpl().onDialogDismissed(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onError(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiver.Stub.getDefaultImpl().onError(paramInt1, paramInt2, paramInt3);
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
      parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
        IBiometricServiceReceiver.Stub.getDefaultImpl().onSystemEvent(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */