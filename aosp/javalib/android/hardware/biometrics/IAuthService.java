package android.hardware.biometrics;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthService extends IInterface {
  void authenticate(IBinder paramIBinder, long paramLong, int paramInt, IBiometricServiceReceiver paramIBiometricServiceReceiver, String paramString, Bundle paramBundle) throws RemoteException;
  
  int canAuthenticate(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException;
  
  long[] getAuthenticatorIds() throws RemoteException;
  
  boolean hasEnrolledBiometrics(int paramInt, String paramString) throws RemoteException;
  
  void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback) throws RemoteException;
  
  void resetLockout(byte[] paramArrayOfbyte) throws RemoteException;
  
  void setActiveUser(int paramInt) throws RemoteException;
  
  public static class Default implements IAuthService {
    public IBinder asBinder() {
      return null;
    }
    
    public void authenticate(IBinder param1IBinder, long param1Long, int param1Int, IBiometricServiceReceiver param1IBiometricServiceReceiver, String param1String, Bundle param1Bundle) throws RemoteException {}
    
    public int canAuthenticate(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      return 0;
    }
    
    public void cancelAuthentication(IBinder param1IBinder, String param1String) throws RemoteException {}
    
    public long[] getAuthenticatorIds() throws RemoteException {
      return null;
    }
    
    public boolean hasEnrolledBiometrics(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback param1IBiometricEnabledOnKeyguardCallback) throws RemoteException {}
    
    public void resetLockout(byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void setActiveUser(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAuthService {
    private static final String DESCRIPTOR = "android.hardware.biometrics.IAuthService";
    
    static final int TRANSACTION_authenticate = 1;
    
    static final int TRANSACTION_canAuthenticate = 3;
    
    static final int TRANSACTION_cancelAuthentication = 2;
    
    static final int TRANSACTION_getAuthenticatorIds = 8;
    
    static final int TRANSACTION_hasEnrolledBiometrics = 4;
    
    static final int TRANSACTION_registerEnabledOnKeyguardCallback = 5;
    
    static final int TRANSACTION_resetLockout = 7;
    
    static final int TRANSACTION_setActiveUser = 6;
    
    public Stub() {
      attachInterface(this, "android.hardware.biometrics.IAuthService");
    }
    
    public static IAuthService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.biometrics.IAuthService");
      return (iInterface != null && iInterface instanceof IAuthService) ? (IAuthService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAuthService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 8:
          return "getAuthenticatorIds";
        case 7:
          return "resetLockout";
        case 6:
          return "setActiveUser";
        case 5:
          return "registerEnabledOnKeyguardCallback";
        case 4:
          return "hasEnrolledBiometrics";
        case 3:
          return "canAuthenticate";
        case 2:
          return "cancelAuthentication";
        case 1:
          break;
      } 
      return "authenticate";
    }
    
    public static boolean setDefaultImpl(IAuthService param1IAuthService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAuthService != null) {
          Proxy.sDefaultImpl = param1IAuthService;
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
        long[] arrayOfLong;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IAuthService");
            arrayOfLong = getAuthenticatorIds();
            param1Parcel2.writeNoException();
            param1Parcel2.writeLongArray(arrayOfLong);
            return true;
          case 7:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
            resetLockout(arrayOfLong.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 6:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
            setActiveUser(arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
            registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback.Stub.asInterface(arrayOfLong.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 4:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
            bool = hasEnrolledBiometrics(arrayOfLong.readInt(), arrayOfLong.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
            i = canAuthenticate(arrayOfLong.readString(), arrayOfLong.readInt(), arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 2:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
            cancelAuthentication(arrayOfLong.readStrongBinder(), arrayOfLong.readString());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
        IBinder iBinder = arrayOfLong.readStrongBinder();
        long l = arrayOfLong.readLong();
        int i = arrayOfLong.readInt();
        IBiometricServiceReceiver iBiometricServiceReceiver = IBiometricServiceReceiver.Stub.asInterface(arrayOfLong.readStrongBinder());
        String str = arrayOfLong.readString();
        if (arrayOfLong.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)arrayOfLong);
        } else {
          arrayOfLong = null;
        } 
        authenticate(iBinder, l, i, iBiometricServiceReceiver, str, (Bundle)arrayOfLong);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.hardware.biometrics.IAuthService");
      return true;
    }
    
    private static class Proxy implements IAuthService {
      public static IAuthService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void authenticate(IBinder param2IBinder, long param2Long, int param2Int, IBiometricServiceReceiver param2IBiometricServiceReceiver, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          try {
            parcel1.writeStrongBinder(param2IBinder);
            try {
              parcel1.writeLong(param2Long);
              try {
                IBinder iBinder;
                parcel1.writeInt(param2Int);
                if (param2IBiometricServiceReceiver != null) {
                  iBinder = param2IBiometricServiceReceiver.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                parcel1.writeString(param2String);
                if (param2Bundle != null) {
                  parcel1.writeInt(1);
                  param2Bundle.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
                  IAuthService.Stub.getDefaultImpl().authenticate(param2IBinder, param2Long, param2Int, param2IBiometricServiceReceiver, param2String, param2Bundle);
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
        throw param2IBinder;
      }
      
      public int canAuthenticate(String param2String, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
            param2Int1 = IAuthService.Stub.getDefaultImpl().canAuthenticate(param2String, param2Int1, param2Int2);
            return param2Int1;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          return param2Int1;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void cancelAuthentication(IBinder param2IBinder, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
            IAuthService.Stub.getDefaultImpl().cancelAuthentication(param2IBinder, param2String);
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
      
      public boolean hasEnrolledBiometrics(int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
            bool = IAuthService.Stub.getDefaultImpl().hasEnrolledBiometrics(param2Int, param2String);
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
      
      public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback param2IBiometricEnabledOnKeyguardCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          if (param2IBiometricEnabledOnKeyguardCallback != null) {
            iBinder = param2IBiometricEnabledOnKeyguardCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
            IAuthService.Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(param2IBiometricEnabledOnKeyguardCallback);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void resetLockout(byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
            IAuthService.Stub.getDefaultImpl().resetLockout(param2ArrayOfbyte);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setActiveUser(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
            IAuthService.Stub.getDefaultImpl().setActiveUser(param2Int);
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
  
  private static class Proxy implements IAuthService {
    public static IAuthService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void authenticate(IBinder param1IBinder, long param1Long, int param1Int, IBiometricServiceReceiver param1IBiometricServiceReceiver, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeLong(param1Long);
            try {
              IBinder iBinder;
              parcel1.writeInt(param1Int);
              if (param1IBiometricServiceReceiver != null) {
                iBinder = param1IBiometricServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeString(param1String);
              if (param1Bundle != null) {
                parcel1.writeInt(1);
                param1Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
                IAuthService.Stub.getDefaultImpl().authenticate(param1IBinder, param1Long, param1Int, param1IBiometricServiceReceiver, param1String, param1Bundle);
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
      throw param1IBinder;
    }
    
    public int canAuthenticate(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
          param1Int1 = IAuthService.Stub.getDefaultImpl().canAuthenticate(param1String, param1Int1, param1Int2);
          return param1Int1;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        return param1Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelAuthentication(IBinder param1IBinder, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
          IAuthService.Stub.getDefaultImpl().cancelAuthentication(param1IBinder, param1String);
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
    
    public boolean hasEnrolledBiometrics(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
          bool = IAuthService.Stub.getDefaultImpl().hasEnrolledBiometrics(param1Int, param1String);
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
    
    public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback param1IBiometricEnabledOnKeyguardCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        if (param1IBiometricEnabledOnKeyguardCallback != null) {
          iBinder = param1IBiometricEnabledOnKeyguardCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
          IAuthService.Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(param1IBiometricEnabledOnKeyguardCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resetLockout(byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
          IAuthService.Stub.getDefaultImpl().resetLockout(param1ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setActiveUser(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IAuthService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAuthService.Stub.getDefaultImpl() != null) {
          IAuthService.Stub.getDefaultImpl().setActiveUser(param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IAuthService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */