package android.hardware.fingerprint;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IFingerprintService {
  private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintService";
  
  static final int TRANSACTION_addClientActiveCallback = 23;
  
  static final int TRANSACTION_addLockoutResetCallback = 19;
  
  static final int TRANSACTION_authenticate = 1;
  
  static final int TRANSACTION_cancelAuthentication = 5;
  
  static final int TRANSACTION_cancelAuthenticationFromService = 7;
  
  static final int TRANSACTION_cancelEnrollment = 9;
  
  static final int TRANSACTION_cancelFingerprintDetect = 6;
  
  static final int TRANSACTION_detectFingerprint = 2;
  
  static final int TRANSACTION_enroll = 8;
  
  static final int TRANSACTION_enumerate = 21;
  
  static final int TRANSACTION_getAuthenticatorId = 17;
  
  static final int TRANSACTION_getEnrolledFingerprints = 12;
  
  static final int TRANSACTION_hasEnrolledFingerprints = 16;
  
  static final int TRANSACTION_initConfiguredStrength = 25;
  
  static final int TRANSACTION_isClientActive = 22;
  
  static final int TRANSACTION_isHardwareDetected = 13;
  
  static final int TRANSACTION_postEnroll = 15;
  
  static final int TRANSACTION_preEnroll = 14;
  
  static final int TRANSACTION_prepareForAuthentication = 3;
  
  static final int TRANSACTION_remove = 10;
  
  static final int TRANSACTION_removeClientActiveCallback = 24;
  
  static final int TRANSACTION_rename = 11;
  
  static final int TRANSACTION_resetTimeout = 18;
  
  static final int TRANSACTION_setActiveUser = 20;
  
  static final int TRANSACTION_startPreparedClient = 4;
  
  public Stub() {
    attachInterface(this, "android.hardware.fingerprint.IFingerprintService");
  }
  
  public static IFingerprintService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintService");
    return (iInterface != null && iInterface instanceof IFingerprintService) ? (IFingerprintService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFingerprintService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 25:
        return "initConfiguredStrength";
      case 24:
        return "removeClientActiveCallback";
      case 23:
        return "addClientActiveCallback";
      case 22:
        return "isClientActive";
      case 21:
        return "enumerate";
      case 20:
        return "setActiveUser";
      case 19:
        return "addLockoutResetCallback";
      case 18:
        return "resetTimeout";
      case 17:
        return "getAuthenticatorId";
      case 16:
        return "hasEnrolledFingerprints";
      case 15:
        return "postEnroll";
      case 14:
        return "preEnroll";
      case 13:
        return "isHardwareDetected";
      case 12:
        return "getEnrolledFingerprints";
      case 11:
        return "rename";
      case 10:
        return "remove";
      case 9:
        return "cancelEnrollment";
      case 8:
        return "enroll";
      case 7:
        return "cancelAuthenticationFromService";
      case 6:
        return "cancelFingerprintDetect";
      case 5:
        return "cancelAuthentication";
      case 4:
        return "startPreparedClient";
      case 3:
        return "prepareForAuthentication";
      case 2:
        return "detectFingerprint";
      case 1:
        break;
    } 
    return "authenticate";
  }
  
  public static boolean setDefaultImpl(IFingerprintService paramIFingerprintService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFingerprintService != null) {
        Proxy.sDefaultImpl = paramIFingerprintService;
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
      boolean bool2;
      int j;
      boolean bool1;
      int i;
      List<Fingerprint> list;
      long l;
      IBinder iBinder;
      String str;
      int k;
      boolean bool;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 25:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          initConfiguredStrength(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 24:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          removeClientActiveCallback(IFingerprintClientActiveCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 23:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          addClientActiveCallback(IFingerprintClientActiveCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 22:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          bool2 = isClientActive();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 21:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          enumerate(paramParcel1.readStrongBinder(), paramParcel1.readInt(), IFingerprintServiceReceiver.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 20:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          setActiveUser(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 19:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          addLockoutResetCallback(IBiometricServiceLockoutResetCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 18:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          resetTimeout(paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          l = getAuthenticatorId(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 16:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          bool2 = hasEnrolledFingerprints(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 15:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          j = postEnroll(paramParcel1.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 14:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          l = preEnroll(paramParcel1.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 13:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          bool1 = isHardwareDetected(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          list = getEnrolledFingerprints(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 11:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          rename(list.readInt(), list.readInt(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 10:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          remove(list.readStrongBinder(), list.readInt(), list.readInt(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 9:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          cancelEnrollment(list.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 8:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          enroll(list.readStrongBinder(), list.createByteArray(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          iBinder = list.readStrongBinder();
          str = list.readString();
          k = list.readInt();
          i = list.readInt();
          paramInt2 = list.readInt();
          if (list.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          cancelAuthenticationFromService(iBinder, str, k, i, paramInt2, bool);
          paramParcel2.writeNoException();
          return true;
        case 6:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          cancelFingerprintDetect(list.readStrongBinder(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          cancelAuthentication(list.readStrongBinder(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          startPreparedClient(list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          prepareForAuthentication(list.readStrongBinder(), list.readLong(), list.readInt(), IBiometricServiceReceiverInternal.Stub.asInterface(list.readStrongBinder()), list.readString(), list.readInt(), list.readInt(), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
          detectFingerprint(list.readStrongBinder(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
      authenticate(list.readStrongBinder(), list.readLong(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readString());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.fingerprint.IFingerprintService");
    return true;
  }
  
  private static class Proxy implements IFingerprintService {
    public static IFingerprintService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addClientActiveCallback(IFingerprintClientActiveCallback param2IFingerprintClientActiveCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        if (param2IFingerprintClientActiveCallback != null) {
          iBinder = param2IFingerprintClientActiveCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().addClientActiveCallback(param2IFingerprintClientActiveCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback param2IBiometricServiceLockoutResetCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        if (param2IBiometricServiceLockoutResetCallback != null) {
          iBinder = param2IBiometricServiceLockoutResetCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().addLockoutResetCallback(param2IBiometricServiceLockoutResetCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void authenticate(IBinder param2IBinder, long param2Long, int param2Int1, IFingerprintServiceReceiver param2IFingerprintServiceReceiver, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param2IBinder);
          try {
            parcel1.writeLong(param2Long);
            try {
              IBinder iBinder;
              parcel1.writeInt(param2Int1);
              if (param2IFingerprintServiceReceiver != null) {
                iBinder = param2IFingerprintServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeInt(param2Int2);
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                  IFingerprintService.Stub.getDefaultImpl().authenticate(param2IBinder, param2Long, param2Int1, param2IFingerprintServiceReceiver, param2Int2, param2String);
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
      parcel2.recycle();
      parcel1.recycle();
      throw param2IBinder;
    }
    
    public void cancelAuthentication(IBinder param2IBinder, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().cancelAuthentication(param2IBinder, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelAuthenticationFromService(IBinder param2IBinder, String param2String, int param2Int1, int param2Int2, int param2Int3, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
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
                    if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                      IFingerprintService.Stub.getDefaultImpl().cancelAuthenticationFromService(param2IBinder, param2String, param2Int1, param2Int2, param2Int3, param2Boolean);
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
    
    public void cancelEnrollment(IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().cancelEnrollment(param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelFingerprintDetect(IBinder param2IBinder, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().cancelFingerprintDetect(param2IBinder, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void detectFingerprint(IBinder param2IBinder, int param2Int, IFingerprintServiceReceiver param2IFingerprintServiceReceiver, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeInt(param2Int);
        if (param2IFingerprintServiceReceiver != null) {
          iBinder = param2IFingerprintServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().detectFingerprint(param2IBinder, param2Int, param2IFingerprintServiceReceiver, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enroll(IBinder param2IBinder, byte[] param2ArrayOfbyte, int param2Int1, IFingerprintServiceReceiver param2IFingerprintServiceReceiver, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param2IBinder);
          try {
            parcel1.writeByteArray(param2ArrayOfbyte);
            try {
              IBinder iBinder;
              parcel1.writeInt(param2Int1);
              if (param2IFingerprintServiceReceiver != null) {
                iBinder = param2IFingerprintServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeInt(param2Int2);
                try {
                  parcel1.writeString(param2String);
                  try {
                    if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                      IFingerprintService.Stub.getDefaultImpl().enroll(param2IBinder, param2ArrayOfbyte, param2Int1, param2IFingerprintServiceReceiver, param2Int2, param2String);
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
    
    public void enumerate(IBinder param2IBinder, int param2Int, IFingerprintServiceReceiver param2IFingerprintServiceReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeInt(param2Int);
        if (param2IFingerprintServiceReceiver != null) {
          iBinder = param2IFingerprintServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().enumerate(param2IBinder, param2Int, param2IFingerprintServiceReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getAuthenticatorId(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().getAuthenticatorId(param2Int); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<Fingerprint> getEnrolledFingerprints(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().getEnrolledFingerprints(param2Int, param2String); 
        parcel2.readException();
        return parcel2.createTypedArrayList(Fingerprint.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.fingerprint.IFingerprintService";
    }
    
    public boolean hasEnrolledFingerprints(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(16, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          bool = IFingerprintService.Stub.getDefaultImpl().hasEnrolledFingerprints(param2Int, param2String);
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
    
    public void initConfiguredStrength(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().initConfiguredStrength(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isClientActive() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(22, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          bool = IFingerprintService.Stub.getDefaultImpl().isClientActive();
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
    
    public boolean isHardwareDetected(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          bool = IFingerprintService.Stub.getDefaultImpl().isHardwareDetected(param2String);
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
    
    public int postEnroll(IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().postEnroll(param2IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long preEnroll(IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().preEnroll(param2IBinder); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void prepareForAuthentication(IBinder param2IBinder, long param2Long, int param2Int1, IBiometricServiceReceiverInternal param2IBiometricServiceReceiverInternal, String param2String, int param2Int2, int param2Int3, int param2Int4, int param2Int5) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeLong(param2Long);
          try {
            IBinder iBinder;
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
            if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
              IFingerprintService.Stub.getDefaultImpl().prepareForAuthentication(param2IBinder, param2Long, param2Int1, param2IBiometricServiceReceiverInternal, param2String, param2Int2, param2Int3, param2Int4, param2Int5);
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
      parcel2.recycle();
      parcel1.recycle();
      throw param2IBinder;
    }
    
    public void remove(IBinder param2IBinder, int param2Int1, int param2Int2, int param2Int3, IFingerprintServiceReceiver param2IFingerprintServiceReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (param2IFingerprintServiceReceiver != null) {
          iBinder = param2IFingerprintServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().remove(param2IBinder, param2Int1, param2Int2, param2Int3, param2IFingerprintServiceReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeClientActiveCallback(IFingerprintClientActiveCallback param2IFingerprintClientActiveCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        if (param2IFingerprintClientActiveCallback != null) {
          iBinder = param2IFingerprintClientActiveCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().removeClientActiveCallback(param2IFingerprintClientActiveCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void rename(int param2Int1, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().rename(param2Int1, param2Int2, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resetTimeout(byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().resetTimeout(param2ArrayOfbyte);
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
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().setActiveUser(param2Int);
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
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().startPreparedClient(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */