package android.app.trust;

import android.hardware.biometrics.BiometricSourceType;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITrustManager extends IInterface {
  void clearAllBiometricRecognized(BiometricSourceType paramBiometricSourceType) throws RemoteException;
  
  boolean isDeviceLocked(int paramInt) throws RemoteException;
  
  boolean isDeviceSecure(int paramInt) throws RemoteException;
  
  boolean isTrustUsuallyManaged(int paramInt) throws RemoteException;
  
  void registerTrustListener(ITrustListener paramITrustListener) throws RemoteException;
  
  void reportEnabledTrustAgentsChanged(int paramInt) throws RemoteException;
  
  void reportKeyguardShowingChanged() throws RemoteException;
  
  void reportUnlockAttempt(boolean paramBoolean, int paramInt) throws RemoteException;
  
  void reportUnlockLockout(int paramInt1, int paramInt2) throws RemoteException;
  
  void setDeviceLockedForUser(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void unlockedByBiometricForUser(int paramInt, BiometricSourceType paramBiometricSourceType) throws RemoteException;
  
  void unregisterTrustListener(ITrustListener paramITrustListener) throws RemoteException;
  
  public static class Default implements ITrustManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void clearAllBiometricRecognized(BiometricSourceType param1BiometricSourceType) throws RemoteException {}
    
    public boolean isDeviceLocked(int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean isDeviceSecure(int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean isTrustUsuallyManaged(int param1Int) throws RemoteException {
      return false;
    }
    
    public void registerTrustListener(ITrustListener param1ITrustListener) throws RemoteException {}
    
    public void reportEnabledTrustAgentsChanged(int param1Int) throws RemoteException {}
    
    public void reportKeyguardShowingChanged() throws RemoteException {}
    
    public void reportUnlockAttempt(boolean param1Boolean, int param1Int) throws RemoteException {}
    
    public void reportUnlockLockout(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void setDeviceLockedForUser(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void unlockedByBiometricForUser(int param1Int, BiometricSourceType param1BiometricSourceType) throws RemoteException {}
    
    public void unregisterTrustListener(ITrustListener param1ITrustListener) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITrustManager {
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
    
    public static ITrustManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.trust.ITrustManager");
      return (iInterface != null && iInterface instanceof ITrustManager) ? (ITrustManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITrustManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(ITrustManager param1ITrustManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITrustManager != null) {
          Proxy.sDefaultImpl = param1ITrustManager;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool;
        int i;
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 12:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            if (param1Parcel1.readInt() != 0) {
              BiometricSourceType biometricSourceType = (BiometricSourceType)BiometricSourceType.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            clearAllBiometricRecognized((BiometricSourceType)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              BiometricSourceType biometricSourceType = (BiometricSourceType)BiometricSourceType.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            unlockedByBiometricForUser(param1Int1, (BiometricSourceType)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            bool = isTrustUsuallyManaged(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            bool = isDeviceSecure(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            bool = isDeviceLocked(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            i = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            setDeviceLockedForUser(i, bool2);
            param1Parcel2.writeNoException();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            reportKeyguardShowingChanged();
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            unregisterTrustListener(ITrustListener.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            registerTrustListener(ITrustListener.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            reportEnabledTrustAgentsChanged(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
            reportUnlockLockout(param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.trust.ITrustManager");
        bool2 = bool1;
        if (param1Parcel1.readInt() != 0)
          bool2 = true; 
        reportUnlockAttempt(bool2, param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.app.trust.ITrustManager");
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
  
  private static class Proxy implements ITrustManager {
    public static ITrustManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearAllBiometricRecognized(BiometricSourceType param1BiometricSourceType) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param1BiometricSourceType != null) {
          parcel1.writeInt(1);
          param1BiometricSourceType.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().clearAllBiometricRecognized(param1BiometricSourceType);
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
    
    public boolean isDeviceLocked(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          bool = ITrustManager.Stub.getDefaultImpl().isDeviceLocked(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDeviceSecure(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          bool = ITrustManager.Stub.getDefaultImpl().isDeviceSecure(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isTrustUsuallyManaged(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          bool = ITrustManager.Stub.getDefaultImpl().isTrustUsuallyManaged(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerTrustListener(ITrustListener param1ITrustListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param1ITrustListener != null) {
          iBinder = param1ITrustListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().registerTrustListener(param1ITrustListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportEnabledTrustAgentsChanged(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().reportEnabledTrustAgentsChanged(param1Int);
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
    
    public void reportUnlockAttempt(boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().reportUnlockAttempt(param1Boolean, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportUnlockLockout(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().reportUnlockLockout(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDeviceLockedForUser(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().setDeviceLockedForUser(param1Int, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unlockedByBiometricForUser(int param1Int, BiometricSourceType param1BiometricSourceType) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        parcel1.writeInt(param1Int);
        if (param1BiometricSourceType != null) {
          parcel1.writeInt(1);
          param1BiometricSourceType.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().unlockedByBiometricForUser(param1Int, param1BiometricSourceType);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterTrustListener(ITrustListener param1ITrustListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.trust.ITrustManager");
        if (param1ITrustListener != null) {
          iBinder = param1ITrustListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ITrustManager.Stub.getDefaultImpl() != null) {
          ITrustManager.Stub.getDefaultImpl().unregisterTrustListener(param1ITrustListener);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */