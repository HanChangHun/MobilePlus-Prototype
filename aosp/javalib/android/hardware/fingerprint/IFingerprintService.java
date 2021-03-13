package android.hardware.fingerprint;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IFingerprintService extends IInterface {
  void addClientActiveCallback(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) throws RemoteException;
  
  void addLockoutResetCallback(IBiometricServiceLockoutResetCallback paramIBiometricServiceLockoutResetCallback) throws RemoteException;
  
  void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, int paramInt2, String paramString) throws RemoteException;
  
  void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException;
  
  void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException;
  
  void cancelEnrollment(IBinder paramIBinder) throws RemoteException;
  
  void cancelFingerprintDetect(IBinder paramIBinder, String paramString) throws RemoteException;
  
  void detectFingerprint(IBinder paramIBinder, int paramInt, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, String paramString) throws RemoteException;
  
  void enroll(IBinder paramIBinder, byte[] paramArrayOfbyte, int paramInt1, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, int paramInt2, String paramString) throws RemoteException;
  
  void enumerate(IBinder paramIBinder, int paramInt, IFingerprintServiceReceiver paramIFingerprintServiceReceiver) throws RemoteException;
  
  long getAuthenticatorId(int paramInt) throws RemoteException;
  
  List<Fingerprint> getEnrolledFingerprints(int paramInt, String paramString) throws RemoteException;
  
  boolean hasEnrolledFingerprints(int paramInt, String paramString) throws RemoteException;
  
  void initConfiguredStrength(int paramInt) throws RemoteException;
  
  boolean isClientActive() throws RemoteException;
  
  boolean isHardwareDetected(String paramString) throws RemoteException;
  
  int postEnroll(IBinder paramIBinder) throws RemoteException;
  
  long preEnroll(IBinder paramIBinder) throws RemoteException;
  
  void prepareForAuthentication(IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException;
  
  void remove(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3, IFingerprintServiceReceiver paramIFingerprintServiceReceiver) throws RemoteException;
  
  void removeClientActiveCallback(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) throws RemoteException;
  
  void rename(int paramInt1, int paramInt2, String paramString) throws RemoteException;
  
  void resetTimeout(byte[] paramArrayOfbyte) throws RemoteException;
  
  void setActiveUser(int paramInt) throws RemoteException;
  
  void startPreparedClient(int paramInt) throws RemoteException;
  
  public static class Default implements IFingerprintService {
    public void addClientActiveCallback(IFingerprintClientActiveCallback param1IFingerprintClientActiveCallback) throws RemoteException {}
    
    public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback param1IBiometricServiceLockoutResetCallback) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void authenticate(IBinder param1IBinder, long param1Long, int param1Int1, IFingerprintServiceReceiver param1IFingerprintServiceReceiver, int param1Int2, String param1String) throws RemoteException {}
    
    public void cancelAuthentication(IBinder param1IBinder, String param1String) throws RemoteException {}
    
    public void cancelAuthenticationFromService(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) throws RemoteException {}
    
    public void cancelEnrollment(IBinder param1IBinder) throws RemoteException {}
    
    public void cancelFingerprintDetect(IBinder param1IBinder, String param1String) throws RemoteException {}
    
    public void detectFingerprint(IBinder param1IBinder, int param1Int, IFingerprintServiceReceiver param1IFingerprintServiceReceiver, String param1String) throws RemoteException {}
    
    public void enroll(IBinder param1IBinder, byte[] param1ArrayOfbyte, int param1Int1, IFingerprintServiceReceiver param1IFingerprintServiceReceiver, int param1Int2, String param1String) throws RemoteException {}
    
    public void enumerate(IBinder param1IBinder, int param1Int, IFingerprintServiceReceiver param1IFingerprintServiceReceiver) throws RemoteException {}
    
    public long getAuthenticatorId(int param1Int) throws RemoteException {
      return 0L;
    }
    
    public List<Fingerprint> getEnrolledFingerprints(int param1Int, String param1String) throws RemoteException {
      return null;
    }
    
    public boolean hasEnrolledFingerprints(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public void initConfiguredStrength(int param1Int) throws RemoteException {}
    
    public boolean isClientActive() throws RemoteException {
      return false;
    }
    
    public boolean isHardwareDetected(String param1String) throws RemoteException {
      return false;
    }
    
    public int postEnroll(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public long preEnroll(IBinder param1IBinder) throws RemoteException {
      return 0L;
    }
    
    public void prepareForAuthentication(IBinder param1IBinder, long param1Long, int param1Int1, IBiometricServiceReceiverInternal param1IBiometricServiceReceiverInternal, String param1String, int param1Int2, int param1Int3, int param1Int4, int param1Int5) throws RemoteException {}
    
    public void remove(IBinder param1IBinder, int param1Int1, int param1Int2, int param1Int3, IFingerprintServiceReceiver param1IFingerprintServiceReceiver) throws RemoteException {}
    
    public void removeClientActiveCallback(IFingerprintClientActiveCallback param1IFingerprintClientActiveCallback) throws RemoteException {}
    
    public void rename(int param1Int1, int param1Int2, String param1String) throws RemoteException {}
    
    public void resetTimeout(byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void setActiveUser(int param1Int) throws RemoteException {}
    
    public void startPreparedClient(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IFingerprintService {
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
    
    public static IFingerprintService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintService");
      return (iInterface != null && iInterface instanceof IFingerprintService) ? (IFingerprintService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IFingerprintService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IFingerprintService param1IFingerprintService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IFingerprintService != null) {
          Proxy.sDefaultImpl = param1IFingerprintService;
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
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 25:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            initConfiguredStrength(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 24:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            removeClientActiveCallback(IFingerprintClientActiveCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 23:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            addClientActiveCallback(IFingerprintClientActiveCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 22:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            bool2 = isClientActive();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 21:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            enumerate(param1Parcel1.readStrongBinder(), param1Parcel1.readInt(), IFingerprintServiceReceiver.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 20:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            setActiveUser(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 19:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            addLockoutResetCallback(IBiometricServiceLockoutResetCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 18:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            resetTimeout(param1Parcel1.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 17:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            l = getAuthenticatorId(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l);
            return true;
          case 16:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            bool2 = hasEnrolledFingerprints(param1Parcel1.readInt(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            j = postEnroll(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            l = preEnroll(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l);
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            bool1 = isHardwareDetected(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            list = getEnrolledFingerprints(param1Parcel1.readInt(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 11:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            rename(list.readInt(), list.readInt(), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 10:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            remove(list.readStrongBinder(), list.readInt(), list.readInt(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 9:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            cancelEnrollment(list.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 8:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            enroll(list.readStrongBinder(), list.createByteArray(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 7:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            iBinder = list.readStrongBinder();
            str = list.readString();
            k = list.readInt();
            i = list.readInt();
            param1Int2 = list.readInt();
            if (list.readInt() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            cancelAuthenticationFromService(iBinder, str, k, i, param1Int2, bool);
            param1Parcel2.writeNoException();
            return true;
          case 6:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            cancelFingerprintDetect(list.readStrongBinder(), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            cancelAuthentication(list.readStrongBinder(), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 4:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            startPreparedClient(list.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            prepareForAuthentication(list.readStrongBinder(), list.readLong(), list.readInt(), IBiometricServiceReceiverInternal.Stub.asInterface(list.readStrongBinder()), list.readString(), list.readInt(), list.readInt(), list.readInt(), list.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 2:
            list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
            detectFingerprint(list.readStrongBinder(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.hardware.fingerprint.IFingerprintService");
        authenticate(list.readStrongBinder(), list.readLong(), list.readInt(), IFingerprintServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readString());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.hardware.fingerprint.IFingerprintService");
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
  
  private static class Proxy implements IFingerprintService {
    public static IFingerprintService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void addClientActiveCallback(IFingerprintClientActiveCallback param1IFingerprintClientActiveCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        if (param1IFingerprintClientActiveCallback != null) {
          iBinder = param1IFingerprintClientActiveCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().addClientActiveCallback(param1IFingerprintClientActiveCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback param1IBiometricServiceLockoutResetCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        if (param1IBiometricServiceLockoutResetCallback != null) {
          iBinder = param1IBiometricServiceLockoutResetCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().addLockoutResetCallback(param1IBiometricServiceLockoutResetCallback);
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
    
    public void authenticate(IBinder param1IBinder, long param1Long, int param1Int1, IFingerprintServiceReceiver param1IFingerprintServiceReceiver, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeLong(param1Long);
            try {
              IBinder iBinder;
              parcel1.writeInt(param1Int1);
              if (param1IFingerprintServiceReceiver != null) {
                iBinder = param1IFingerprintServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeInt(param1Int2);
                parcel1.writeString(param1String);
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                  IFingerprintService.Stub.getDefaultImpl().authenticate(param1IBinder, param1Long, param1Int1, param1IFingerprintServiceReceiver, param1Int2, param1String);
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
      throw param1IBinder;
    }
    
    public void cancelAuthentication(IBinder param1IBinder, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().cancelAuthentication(param1IBinder, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelAuthenticationFromService(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeString(param1String);
            try {
              parcel1.writeInt(param1Int1);
              try {
                parcel1.writeInt(param1Int2);
                try {
                  boolean bool;
                  parcel1.writeInt(param1Int3);
                  if (param1Boolean) {
                    bool = true;
                  } else {
                    bool = false;
                  } 
                  parcel1.writeInt(bool);
                  try {
                    if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                      IFingerprintService.Stub.getDefaultImpl().cancelAuthenticationFromService(param1IBinder, param1String, param1Int1, param1Int2, param1Int3, param1Boolean);
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
      throw param1IBinder;
    }
    
    public void cancelEnrollment(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().cancelEnrollment(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelFingerprintDetect(IBinder param1IBinder, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().cancelFingerprintDetect(param1IBinder, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void detectFingerprint(IBinder param1IBinder, int param1Int, IFingerprintServiceReceiver param1IFingerprintServiceReceiver, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (param1IFingerprintServiceReceiver != null) {
          iBinder = param1IFingerprintServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().detectFingerprint(param1IBinder, param1Int, param1IFingerprintServiceReceiver, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enroll(IBinder param1IBinder, byte[] param1ArrayOfbyte, int param1Int1, IFingerprintServiceReceiver param1IFingerprintServiceReceiver, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          try {
            parcel1.writeByteArray(param1ArrayOfbyte);
            try {
              IBinder iBinder;
              parcel1.writeInt(param1Int1);
              if (param1IFingerprintServiceReceiver != null) {
                iBinder = param1IFingerprintServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeInt(param1Int2);
                try {
                  parcel1.writeString(param1String);
                  try {
                    if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                      IFingerprintService.Stub.getDefaultImpl().enroll(param1IBinder, param1ArrayOfbyte, param1Int1, param1IFingerprintServiceReceiver, param1Int2, param1String);
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
      throw param1IBinder;
    }
    
    public void enumerate(IBinder param1IBinder, int param1Int, IFingerprintServiceReceiver param1IFingerprintServiceReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (param1IFingerprintServiceReceiver != null) {
          iBinder = param1IFingerprintServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().enumerate(param1IBinder, param1Int, param1IFingerprintServiceReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getAuthenticatorId(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().getAuthenticatorId(param1Int); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<Fingerprint> getEnrolledFingerprints(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().getEnrolledFingerprints(param1Int, param1String); 
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
    
    public boolean hasEnrolledFingerprints(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(16, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          bool = IFingerprintService.Stub.getDefaultImpl().hasEnrolledFingerprints(param1Int, param1String);
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
    
    public void initConfiguredStrength(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().initConfiguredStrength(param1Int);
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
    
    public boolean isHardwareDetected(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          bool = IFingerprintService.Stub.getDefaultImpl().isHardwareDetected(param1String);
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
    
    public int postEnroll(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().postEnroll(param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long preEnroll(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
          return IFingerprintService.Stub.getDefaultImpl().preEnroll(param1IBinder); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void prepareForAuthentication(IBinder param1IBinder, long param1Long, int param1Int1, IBiometricServiceReceiverInternal param1IBiometricServiceReceiverInternal, String param1String, int param1Int2, int param1Int3, int param1Int4, int param1Int5) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        try {
          parcel1.writeStrongBinder(param1IBinder);
          parcel1.writeLong(param1Long);
          try {
            IBinder iBinder;
            parcel1.writeInt(param1Int1);
            if (param1IBiometricServiceReceiverInternal != null) {
              iBinder = param1IBiometricServiceReceiverInternal.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeString(param1String);
            parcel1.writeInt(param1Int2);
            parcel1.writeInt(param1Int3);
            parcel1.writeInt(param1Int4);
            parcel1.writeInt(param1Int5);
            if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
              IFingerprintService.Stub.getDefaultImpl().prepareForAuthentication(param1IBinder, param1Long, param1Int1, param1IBiometricServiceReceiverInternal, param1String, param1Int2, param1Int3, param1Int4, param1Int5);
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
      throw param1IBinder;
    }
    
    public void remove(IBinder param1IBinder, int param1Int1, int param1Int2, int param1Int3, IFingerprintServiceReceiver param1IFingerprintServiceReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (param1IFingerprintServiceReceiver != null) {
          iBinder = param1IFingerprintServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().remove(param1IBinder, param1Int1, param1Int2, param1Int3, param1IFingerprintServiceReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeClientActiveCallback(IFingerprintClientActiveCallback param1IFingerprintClientActiveCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        if (param1IFingerprintClientActiveCallback != null) {
          iBinder = param1IFingerprintClientActiveCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().removeClientActiveCallback(param1IFingerprintClientActiveCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void rename(int param1Int1, int param1Int2, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().rename(param1Int1, param1Int2, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resetTimeout(byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().resetTimeout(param1ArrayOfbyte);
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
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().setActiveUser(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startPreparedClient(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
          IFingerprintService.Stub.getDefaultImpl().startPreparedClient(param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */