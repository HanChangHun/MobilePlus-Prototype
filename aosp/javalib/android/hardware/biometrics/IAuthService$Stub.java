package android.hardware.biometrics;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAuthService {
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
  
  public static IAuthService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.biometrics.IAuthService");
    return (iInterface != null && iInterface instanceof IAuthService) ? (IAuthService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAuthService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IAuthService paramIAuthService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAuthService != null) {
        Proxy.sDefaultImpl = paramIAuthService;
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
      long[] arrayOfLong;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.hardware.biometrics.IAuthService");
          arrayOfLong = getAuthenticatorIds();
          paramParcel2.writeNoException();
          paramParcel2.writeLongArray(arrayOfLong);
          return true;
        case 7:
          arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
          resetLockout(arrayOfLong.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 6:
          arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
          setActiveUser(arrayOfLong.readInt());
          paramParcel2.writeNoException();
          return true;
        case 5:
          arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
          registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback.Stub.asInterface(arrayOfLong.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 4:
          arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
          bool = hasEnrolledBiometrics(arrayOfLong.readInt(), arrayOfLong.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 3:
          arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
          i = canAuthenticate(arrayOfLong.readString(), arrayOfLong.readInt(), arrayOfLong.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 2:
          arrayOfLong.enforceInterface("android.hardware.biometrics.IAuthService");
          cancelAuthentication(arrayOfLong.readStrongBinder(), arrayOfLong.readString());
          paramParcel2.writeNoException();
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
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.biometrics.IAuthService");
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IAuthService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */