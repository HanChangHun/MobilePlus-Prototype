package android.app.trust;

import android.hardware.biometrics.BiometricSourceType;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITrustManager {
  private static final String DESCRIPTOR = "android.app.trust.ITrustManager";
  
  static final int TRANSACTION_clearAllBiometricRecognized = 12;
  
  static final int TRANSACTION_isDeviceLocked = 8;
  
  static final int TRANSACTION_isDeviceSecure = 9;
  
  static final int TRANSACTION_isTrustUsuallyManaged = 10;
  
  static final int TRANSACTION_registerTrustListener = 4;
  
  static final int TRANSACTION_reportEnabledTrustAgentsChanged = 3;
  
  static final int TRANSACTION_reportKeyguardShowingChanged = 6;
  
  static final int TRANSACTION_reportUnlockAttempt = 1;
  
  static final int TRANSACTION_reportUnlockLockout = 2;
  
  static final int TRANSACTION_setDeviceLockedForUser = 7;
  
  static final int TRANSACTION_unlockedByBiometricForUser = 11;
  
  static final int TRANSACTION_unregisterTrustListener = 5;
  
  public Stub() {
    attachInterface(this, "android.app.trust.ITrustManager");
  }
  
  public static ITrustManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.trust.ITrustManager");
    return (iInterface != null && iInterface instanceof ITrustManager) ? (ITrustManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITrustManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 12:
        return "clearAllBiometricRecognized";
      case 11:
        return "unlockedByBiometricForUser";
      case 10:
        return "isTrustUsuallyManaged";
      case 9:
        return "isDeviceSecure";
      case 8:
        return "isDeviceLocked";
      case 7:
        return "setDeviceLockedForUser";
      case 6:
        return "reportKeyguardShowingChanged";
      case 5:
        return "unregisterTrustListener";
      case 4:
        return "registerTrustListener";
      case 3:
        return "reportEnabledTrustAgentsChanged";
      case 2:
        return "reportUnlockLockout";
      case 1:
        break;
    } 
    return "reportUnlockAttempt";
  }
  
  public static boolean setDefaultImpl(ITrustManager paramITrustManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITrustManager != null) {
        Proxy.sDefaultImpl = paramITrustManager;
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
    if (paramInt1 != 1598968902) {
      boolean bool;
      int i;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 12:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          if (paramParcel1.readInt() != 0) {
            BiometricSourceType biometricSourceType = (BiometricSourceType)BiometricSourceType.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          clearAllBiometricRecognized((BiometricSourceType)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 11:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            BiometricSourceType biometricSourceType = (BiometricSourceType)BiometricSourceType.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          unlockedByBiometricForUser(paramInt1, (BiometricSourceType)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          bool = isTrustUsuallyManaged(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          bool = isDeviceSecure(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          bool = isDeviceLocked(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          i = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          setDeviceLockedForUser(i, bool2);
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          reportKeyguardShowingChanged();
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          unregisterTrustListener(ITrustListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          registerTrustListener(ITrustListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          reportEnabledTrustAgentsChanged(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.trust.ITrustManager");
          reportUnlockLockout(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.trust.ITrustManager");
      bool2 = bool1;
      if (paramParcel1.readInt() != 0)
        bool2 = true; 
      reportUnlockAttempt(bool2, paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.trust.ITrustManager");
    return true;
  }
  
  private static class Proxy implements ITrustManager {
    public static ITrustManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearAllBiometricRecognized(BiometricSourceType param2BiometricSourceType) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param2BiometricSourceType != null) {
          parcel1.writeInt(1);
          param2BiometricSourceType.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().clearAllBiometricRecognized(param2BiometricSourceType);
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
    
    public boolean isDeviceLocked(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          bool = ITrustManager.Stub.getDefaultImpl().isDeviceLocked(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDeviceSecure(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          bool = ITrustManager.Stub.getDefaultImpl().isDeviceSecure(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isTrustUsuallyManaged(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          bool = ITrustManager.Stub.getDefaultImpl().isTrustUsuallyManaged(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerTrustListener(ITrustListener param2ITrustListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param2ITrustListener != null) {
          iBinder = param2ITrustListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().registerTrustListener(param2ITrustListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportEnabledTrustAgentsChanged(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().reportEnabledTrustAgentsChanged(param2Int);
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
    
    public void reportUnlockAttempt(boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().reportUnlockAttempt(param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportUnlockLockout(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().reportUnlockLockout(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDeviceLockedForUser(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().setDeviceLockedForUser(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unlockedByBiometricForUser(int param2Int, BiometricSourceType param2BiometricSourceType) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param2Int);
        if (param2BiometricSourceType != null) {
          parcel1.writeInt(1);
          param2BiometricSourceType.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().unlockedByBiometricForUser(param2Int, param2BiometricSourceType);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterTrustListener(ITrustListener param2ITrustListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param2ITrustListener != null) {
          iBinder = param2ITrustListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().unregisterTrustListener(param2ITrustListener);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */