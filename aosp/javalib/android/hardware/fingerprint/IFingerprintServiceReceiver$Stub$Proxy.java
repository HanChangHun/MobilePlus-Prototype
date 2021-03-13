package android.hardware.fingerprint;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IFingerprintServiceReceiver {
  public static IFingerprintServiceReceiver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.fingerprint.IFingerprintServiceReceiver";
  }
  
  public void onAcquired(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onAcquired(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationFailed(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(5, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationSucceeded(long paramLong, Fingerprint paramFingerprint, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      boolean bool = false;
      if (paramFingerprint != null) {
        parcel.writeInt(1);
        paramFingerprint.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(3, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(paramLong, paramFingerprint, paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEnrollResult(long paramLong, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnrollResult(paramLong, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEnumerated(long paramLong, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(8, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnumerated(paramLong, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onError(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(6, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onError(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onFingerprintDetected(long paramLong, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(4, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onFingerprintDetected(paramLong, paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRemoved(long paramLong, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(7, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
        IFingerprintServiceReceiver.Stub.getDefaultImpl().onRemoved(paramLong, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintServiceReceiver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */