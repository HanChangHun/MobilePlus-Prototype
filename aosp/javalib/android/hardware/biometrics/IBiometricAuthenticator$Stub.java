package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBiometricAuthenticator {
  private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricAuthenticator";
  
  static final int TRANSACTION_cancelAuthenticationFromService = 3;
  
  static final int TRANSACTION_getAuthenticatorId = 8;
  
  static final int TRANSACTION_hasEnrolledTemplates = 5;
  
  static final int TRANSACTION_isHardwareDetected = 4;
  
  static final int TRANSACTION_prepareForAuthentication = 1;
  
  static final int TRANSACTION_resetLockout = 6;
  
  static final int TRANSACTION_setActiveUser = 7;
  
  static final int TRANSACTION_startPreparedClient = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.biometrics.IBiometricAuthenticator");
  }
  
  public static IBiometricAuthenticator asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.biometrics.IBiometricAuthenticator");
    return (iInterface != null && iInterface instanceof IBiometricAuthenticator) ? (IBiometricAuthenticator)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBiometricAuthenticator getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 8:
        return "getAuthenticatorId";
      case 7:
        return "setActiveUser";
      case 6:
        return "resetLockout";
      case 5:
        return "hasEnrolledTemplates";
      case 4:
        return "isHardwareDetected";
      case 3:
        return "cancelAuthenticationFromService";
      case 2:
        return "startPreparedClient";
      case 1:
        break;
    } 
    return "prepareForAuthentication";
  }
  
  public static boolean setDefaultImpl(IBiometricAuthenticator paramIBiometricAuthenticator) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBiometricAuthenticator != null) {
        Proxy.sDefaultImpl = paramIBiometricAuthenticator;
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
      long l;
      IBinder iBinder;
      String str;
      int j;
      boolean bool1;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          l = getAuthenticatorId(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          setActiveUser(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          resetLockout(paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          bool = hasEnrolledTemplates(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          bool = isHardwareDetected(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          iBinder = paramParcel1.readStrongBinder();
          str = paramParcel1.readString();
          i = paramParcel1.readInt();
          paramInt2 = paramParcel1.readInt();
          j = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          cancelAuthenticationFromService(iBinder, str, i, paramInt2, j, bool1);
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
          startPreparedClient(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
      if (paramParcel1.readInt() != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      prepareForAuthentication(bool1, paramParcel1.readStrongBinder(), paramParcel1.readLong(), paramParcel1.readInt(), IBiometricServiceReceiverInternal.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.biometrics.IBiometricAuthenticator");
    return true;
  }
  
  private static class Proxy implements IBiometricAuthenticator {
    public static IBiometricAuthenticator sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancelAuthenticationFromService(IBinder param2IBinder, String param2String, int param2Int1, int param2Int2, int param2Int3, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        try {
          parcel1.writeStrongBinder(param2IBinder);
          try {
            parcel1.writeString(param2String);
            try {
              parcel1.writeInt(param2Int1);
              try {
                parcel1.writeInt(param2Int2);
                try {
                  boolean bool;
                  parcel1.writeInt(param2Int3);
                  if (param2Boolean) {
                    bool = true;
                  } else {
                    bool = false;
                  } 
                  parcel1.writeInt(bool);
                  try {
                    if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
                      IBiometricAuthenticator.Stub.getDefaultImpl().cancelAuthenticationFromService(param2IBinder, param2String, param2Int1, param2Int2, param2Int3, param2Boolean);
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
      throw param2IBinder;
    }
    
    public long getAuthenticatorId(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null)
          return IBiometricAuthenticator.Stub.getDefaultImpl().getAuthenticatorId(param2Int); 
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
    
    public boolean hasEnrolledTemplates(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          bool = IBiometricAuthenticator.Stub.getDefaultImpl().hasEnrolledTemplates(param2Int, param2String);
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
    
    public boolean isHardwareDetected(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          bool = IBiometricAuthenticator.Stub.getDefaultImpl().isHardwareDetected(param2String);
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
    
    public void prepareForAuthentication(boolean param2Boolean, IBinder param2IBinder, long param2Long, int param2Int1, IBiometricServiceReceiverInternal param2IBiometricServiceReceiverInternal, String param2String, int param2Int2, int param2Int3, int param2Int4, int param2Int5) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeLong(param2Long);
        parcel1.writeInt(param2Int1);
        if (param2IBiometricServiceReceiverInternal != null) {
          iBinder = param2IBiometricServiceReceiverInternal.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeInt(param2Int4);
        parcel1.writeInt(param2Int5);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().prepareForAuthentication(param2Boolean, param2IBinder, param2Long, param2Int1, param2IBiometricServiceReceiverInternal, param2String, param2Int2, param2Int3, param2Int4, param2Int5);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().resetLockout(param2ArrayOfbyte);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().setActiveUser(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startPreparedClient(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().startPreparedClient(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricAuthenticator$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */