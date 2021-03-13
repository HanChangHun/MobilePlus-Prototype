package android.hardware.biometrics;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBiometricService extends IInterface {
  void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiver paramIBiometricServiceReceiver, String paramString, Bundle paramBundle, int paramInt2, int paramInt3, int paramInt4) throws RemoteException;
  
  int canAuthenticate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void cancelAuthentication(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  long[] getAuthenticatorIds(int paramInt) throws RemoteException;
  
  boolean hasEnrolledBiometrics(int paramInt, String paramString) throws RemoteException;
  
  void onReadyForAuthentication(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException;
  
  void registerAuthenticator(int paramInt1, int paramInt2, int paramInt3, IBiometricAuthenticator paramIBiometricAuthenticator) throws RemoteException;
  
  void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback, int paramInt) throws RemoteException;
  
  void resetLockout(byte[] paramArrayOfbyte) throws RemoteException;
  
  void setActiveUser(int paramInt) throws RemoteException;
  
  public static class Default implements IBiometricService {
    public IBinder asBinder() {
      return null;
    }
    
    public void authenticate(IBinder param1IBinder, long param1Long, int param1Int1, IBiometricServiceReceiver param1IBiometricServiceReceiver, String param1String, Bundle param1Bundle, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {}
    
    public int canAuthenticate(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public void cancelAuthentication(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public long[] getAuthenticatorIds(int param1Int) throws RemoteException {
      return null;
    }
    
    public boolean hasEnrolledBiometrics(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public void onReadyForAuthentication(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {}
    
    public void registerAuthenticator(int param1Int1, int param1Int2, int param1Int3, IBiometricAuthenticator param1IBiometricAuthenticator) throws RemoteException {}
    
    public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback param1IBiometricEnabledOnKeyguardCallback, int param1Int) throws RemoteException {}
    
    public void resetLockout(byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void setActiveUser(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBiometricService {
    private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricService";
    
    static final int TRANSACTION_authenticate = 1;
    
    static final int TRANSACTION_canAuthenticate = 3;
    
    static final int TRANSACTION_cancelAuthentication = 2;
    
    static final int TRANSACTION_getAuthenticatorIds = 10;
    
    static final int TRANSACTION_hasEnrolledBiometrics = 4;
    
    static final int TRANSACTION_onReadyForAuthentication = 8;
    
    static final int TRANSACTION_registerAuthenticator = 5;
    
    static final int TRANSACTION_registerEnabledOnKeyguardCallback = 6;
    
    static final int TRANSACTION_resetLockout = 9;
    
    static final int TRANSACTION_setActiveUser = 7;
    
    public Stub() {
      attachInterface(this, "android.hardware.biometrics.IBiometricService");
    }
    
    public static IBiometricService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.biometrics.IBiometricService");
      return (iInterface != null && iInterface instanceof IBiometricService) ? (IBiometricService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBiometricService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 10:
          return "getAuthenticatorIds";
        case 9:
          return "resetLockout";
        case 8:
          return "onReadyForAuthentication";
        case 7:
          return "setActiveUser";
        case 6:
          return "registerEnabledOnKeyguardCallback";
        case 5:
          return "registerAuthenticator";
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
    
    public static boolean setDefaultImpl(IBiometricService param1IBiometricService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBiometricService != null) {
          Proxy.sDefaultImpl = param1IBiometricService;
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
        boolean bool1;
        Bundle bundle;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 10:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricService");
            arrayOfLong = getAuthenticatorIds(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLongArray(arrayOfLong);
            return true;
          case 9:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            resetLockout(arrayOfLong.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 8:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            param1Int1 = arrayOfLong.readInt();
            if (arrayOfLong.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            onReadyForAuthentication(param1Int1, bool1, arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 7:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            setActiveUser(arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 6:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback.Stub.asInterface(arrayOfLong.readStrongBinder()), arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            registerAuthenticator(arrayOfLong.readInt(), arrayOfLong.readInt(), arrayOfLong.readInt(), IBiometricAuthenticator.Stub.asInterface(arrayOfLong.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 4:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            bool = hasEnrolledBiometrics(arrayOfLong.readInt(), arrayOfLong.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            i = canAuthenticate(arrayOfLong.readString(), arrayOfLong.readInt(), arrayOfLong.readInt(), arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 2:
            arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
            cancelAuthentication(arrayOfLong.readStrongBinder(), arrayOfLong.readString(), arrayOfLong.readInt(), arrayOfLong.readInt(), arrayOfLong.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        arrayOfLong.enforceInterface("android.hardware.biometrics.IBiometricService");
        IBinder iBinder = arrayOfLong.readStrongBinder();
        long l = arrayOfLong.readLong();
        int i = arrayOfLong.readInt();
        IBiometricServiceReceiver iBiometricServiceReceiver = IBiometricServiceReceiver.Stub.asInterface(arrayOfLong.readStrongBinder());
        String str = arrayOfLong.readString();
        if (arrayOfLong.readInt() != 0) {
          bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)arrayOfLong);
        } else {
          bundle = null;
        } 
        authenticate(iBinder, l, i, iBiometricServiceReceiver, str, bundle, arrayOfLong.readInt(), arrayOfLong.readInt(), arrayOfLong.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.hardware.biometrics.IBiometricService");
      return true;
    }
    
    private static class Proxy implements IBiometricService {
      public static IBiometricService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void authenticate(IBinder param2IBinder, long param2Long, int param2Int1, IBiometricServiceReceiver param2IBiometricServiceReceiver, String param2String, Bundle param2Bundle, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          try {
            IBinder iBinder;
            parcel1.writeStrongBinder(param2IBinder);
            parcel1.writeLong(param2Long);
            parcel1.writeInt(param2Int1);
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
            parcel1.writeInt(param2Int2);
            parcel1.writeInt(param2Int3);
            parcel1.writeInt(param2Int4);
            if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
              IBiometricService.Stub.getDefaultImpl().authenticate(param2IBinder, param2Long, param2Int1, param2IBiometricServiceReceiver, param2String, param2Bundle, param2Int2, param2Int3, param2Int4);
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
        throw param2IBinder;
      }
      
      public int canAuthenticate(String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            param2Int1 = IBiometricService.Stub.getDefaultImpl().canAuthenticate(param2String, param2Int1, param2Int2, param2Int3);
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
      
      public void cancelAuthentication(IBinder param2IBinder, String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().cancelAuthentication(param2IBinder, param2String, param2Int1, param2Int2, param2Int3);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public long[] getAuthenticatorIds(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null)
            return IBiometricService.Stub.getDefaultImpl().getAuthenticatorIds(param2Int); 
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
      
      public boolean hasEnrolledBiometrics(int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            bool = IBiometricService.Stub.getDefaultImpl().hasEnrolledBiometrics(param2Int, param2String);
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
      
      public void onReadyForAuthentication(int param2Int1, boolean param2Boolean, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeInt(param2Int1);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().onReadyForAuthentication(param2Int1, param2Boolean, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerAuthenticator(int param2Int1, int param2Int2, int param2Int3, IBiometricAuthenticator param2IBiometricAuthenticator) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (param2IBiometricAuthenticator != null) {
            iBinder = param2IBiometricAuthenticator.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().registerAuthenticator(param2Int1, param2Int2, param2Int3, param2IBiometricAuthenticator);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback param2IBiometricEnabledOnKeyguardCallback, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          if (param2IBiometricEnabledOnKeyguardCallback != null) {
            iBinder = param2IBiometricEnabledOnKeyguardCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(param2IBiometricEnabledOnKeyguardCallback, param2Int);
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
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().resetLockout(param2ArrayOfbyte);
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
          parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().setActiveUser(param2Int);
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
  
  private static class Proxy implements IBiometricService {
    public static IBiometricService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void authenticate(IBinder param1IBinder, long param1Long, int param1Int1, IBiometricServiceReceiver param1IBiometricServiceReceiver, String param1String, Bundle param1Bundle, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        try {
          IBinder iBinder;
          parcel1.writeStrongBinder(param1IBinder);
          parcel1.writeLong(param1Long);
          parcel1.writeInt(param1Int1);
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
          parcel1.writeInt(param1Int2);
          parcel1.writeInt(param1Int3);
          parcel1.writeInt(param1Int4);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
            IBiometricService.Stub.getDefaultImpl().authenticate(param1IBinder, param1Long, param1Int1, param1IBiometricServiceReceiver, param1String, param1Bundle, param1Int2, param1Int3, param1Int4);
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
      throw param1IBinder;
    }
    
    public int canAuthenticate(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          param1Int1 = IBiometricService.Stub.getDefaultImpl().canAuthenticate(param1String, param1Int1, param1Int2, param1Int3);
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
    
    public void cancelAuthentication(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().cancelAuthentication(param1IBinder, param1String, param1Int1, param1Int2, param1Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long[] getAuthenticatorIds(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null)
          return IBiometricService.Stub.getDefaultImpl().getAuthenticatorIds(param1Int); 
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
    
    public boolean hasEnrolledBiometrics(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          bool = IBiometricService.Stub.getDefaultImpl().hasEnrolledBiometrics(param1Int, param1String);
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
    
    public void onReadyForAuthentication(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeInt(param1Int1);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().onReadyForAuthentication(param1Int1, param1Boolean, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerAuthenticator(int param1Int1, int param1Int2, int param1Int3, IBiometricAuthenticator param1IBiometricAuthenticator) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (param1IBiometricAuthenticator != null) {
          iBinder = param1IBiometricAuthenticator.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().registerAuthenticator(param1Int1, param1Int2, param1Int3, param1IBiometricAuthenticator);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback param1IBiometricEnabledOnKeyguardCallback, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        if (param1IBiometricEnabledOnKeyguardCallback != null) {
          iBinder = param1IBiometricEnabledOnKeyguardCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().registerEnabledOnKeyguardCallback(param1IBiometricEnabledOnKeyguardCallback, param1Int);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().resetLockout(param1ArrayOfbyte);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBiometricService.Stub.getDefaultImpl() != null) {
          IBiometricService.Stub.getDefaultImpl().setActiveUser(param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */