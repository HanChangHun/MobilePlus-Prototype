package android.app.trust;

import android.hardware.biometrics.BiometricSourceType;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITrustManager {
  public static ITrustManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearAllBiometricRecognized(BiometricSourceType paramBiometricSourceType) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      if (paramBiometricSourceType != null) {
        parcel1.writeInt(1);
        paramBiometricSourceType.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().clearAllBiometricRecognized(paramBiometricSourceType);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.trust.ITrustManager";
  }
  
  public boolean isDeviceLocked(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        bool = ITrustManager.Stub.getDefaultImpl().isDeviceLocked(paramInt);
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
  
  public boolean isDeviceSecure(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(9, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        bool = ITrustManager.Stub.getDefaultImpl().isDeviceSecure(paramInt);
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
  
  public boolean isTrustUsuallyManaged(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(10, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        bool = ITrustManager.Stub.getDefaultImpl().isTrustUsuallyManaged(paramInt);
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
  
  public void registerTrustListener(ITrustListener paramITrustListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      if (paramITrustListener != null) {
        iBinder = paramITrustListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().registerTrustListener(paramITrustListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportEnabledTrustAgentsChanged(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().reportEnabledTrustAgentsChanged(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportKeyguardShowingChanged() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().reportKeyguardShowingChanged();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportUnlockAttempt(boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().reportUnlockAttempt(paramBoolean, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportUnlockLockout(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().reportUnlockLockout(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDeviceLockedForUser(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().setDeviceLockedForUser(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unlockedByBiometricForUser(int paramInt, BiometricSourceType paramBiometricSourceType) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      parcel1.writeInt(paramInt);
      if (paramBiometricSourceType != null) {
        parcel1.writeInt(1);
        paramBiometricSourceType.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().unlockedByBiometricForUser(paramInt, paramBiometricSourceType);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterTrustListener(ITrustListener paramITrustListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
      if (paramITrustListener != null) {
        iBinder = paramITrustListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
        ITrustManager.Stub.getDefaultImpl().unregisterTrustListener(paramITrustListener);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */