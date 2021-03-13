package android.hardware.face;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IFaceService {
  private static final String DESCRIPTOR = "android.hardware.face.IFaceService";
  
  static final int TRANSACTION_addLockoutResetCallback = 17;
  
  static final int TRANSACTION_authenticate = 1;
  
  static final int TRANSACTION_cancelAuthentication = 4;
  
  static final int TRANSACTION_cancelAuthenticationFromService = 5;
  
  static final int TRANSACTION_cancelEnrollment = 7;
  
  static final int TRANSACTION_enroll = 6;
  
  static final int TRANSACTION_enumerate = 19;
  
  static final int TRANSACTION_generateChallenge = 12;
  
  static final int TRANSACTION_getAuthenticatorId = 15;
  
  static final int TRANSACTION_getEnrolledFaces = 10;
  
  static final int TRANSACTION_getFeature = 21;
  
  static final int TRANSACTION_hasEnrolledFaces = 14;
  
  static final int TRANSACTION_initConfiguredStrength = 23;
  
  static final int TRANSACTION_isHardwareDetected = 11;
  
  static final int TRANSACTION_prepareForAuthentication = 2;
  
  static final int TRANSACTION_remove = 8;
  
  static final int TRANSACTION_rename = 9;
  
  static final int TRANSACTION_resetLockout = 16;
  
  static final int TRANSACTION_revokeChallenge = 13;
  
  static final int TRANSACTION_setActiveUser = 18;
  
  static final int TRANSACTION_setFeature = 20;
  
  static final int TRANSACTION_startPreparedClient = 3;
  
  static final int TRANSACTION_userActivity = 22;
  
  public Stub() {
    attachInterface(this, "android.hardware.face.IFaceService");
  }
  
  public static IFaceService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.face.IFaceService");
    return (iInterface != null && iInterface instanceof IFaceService) ? (IFaceService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFaceService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 23:
        return "initConfiguredStrength";
      case 22:
        return "userActivity";
      case 21:
        return "getFeature";
      case 20:
        return "setFeature";
      case 19:
        return "enumerate";
      case 18:
        return "setActiveUser";
      case 17:
        return "addLockoutResetCallback";
      case 16:
        return "resetLockout";
      case 15:
        return "getAuthenticatorId";
      case 14:
        return "hasEnrolledFaces";
      case 13:
        return "revokeChallenge";
      case 12:
        return "generateChallenge";
      case 11:
        return "isHardwareDetected";
      case 10:
        return "getEnrolledFaces";
      case 9:
        return "rename";
      case 8:
        return "remove";
      case 7:
        return "cancelEnrollment";
      case 6:
        return "enroll";
      case 5:
        return "cancelAuthenticationFromService";
      case 4:
        return "cancelAuthentication";
      case 3:
        return "startPreparedClient";
      case 2:
        return "prepareForAuthentication";
      case 1:
        break;
    } 
    return "authenticate";
  }
  
  public static boolean setDefaultImpl(IFaceService paramIFaceService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFaceService != null) {
        Proxy.sDefaultImpl = paramIFaceService;
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
      List<Face> list;
      boolean bool;
      long l;
      IBinder iBinder;
      String str;
      int k;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 23:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          initConfiguredStrength(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 22:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          userActivity();
          paramParcel2.writeNoException();
          return true;
        case 21:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          getFeature(paramParcel1.readInt(), paramParcel1.readInt(), IFaceServiceReceiver.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 20:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          paramInt2 = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          setFeature(paramInt2, paramInt1, bool, paramParcel1.createByteArray(), IFaceServiceReceiver.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 19:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          enumerate(paramParcel1.readStrongBinder(), paramParcel1.readInt(), IFaceServiceReceiver.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 18:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          setActiveUser(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          addLockoutResetCallback(IBiometricServiceLockoutResetCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 16:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          resetLockout(paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 15:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          l = getAuthenticatorId(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 14:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          bool2 = hasEnrolledFaces(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 13:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          j = revokeChallenge(paramParcel1.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          l = generateChallenge(paramParcel1.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          bool1 = isHardwareDetected(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.hardware.face.IFaceService");
          list = getEnrolledFaces(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 9:
          list.enforceInterface("android.hardware.face.IFaceService");
          rename(list.readInt(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 8:
          list.enforceInterface("android.hardware.face.IFaceService");
          remove(list.readStrongBinder(), list.readInt(), list.readInt(), IFaceServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.hardware.face.IFaceService");
          cancelEnrollment(list.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 6:
          list.enforceInterface("android.hardware.face.IFaceService");
          enroll(list.readInt(), list.readStrongBinder(), list.createByteArray(), IFaceServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readString(), list.createIntArray());
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.hardware.face.IFaceService");
          iBinder = list.readStrongBinder();
          str = list.readString();
          paramInt2 = list.readInt();
          i = list.readInt();
          k = list.readInt();
          if (list.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          cancelAuthenticationFromService(iBinder, str, paramInt2, i, k, bool);
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.hardware.face.IFaceService");
          cancelAuthentication(list.readStrongBinder(), list.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.hardware.face.IFaceService");
          startPreparedClient(list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          list.enforceInterface("android.hardware.face.IFaceService");
          if (list.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          prepareForAuthentication(bool, list.readStrongBinder(), list.readLong(), list.readInt(), IBiometricServiceReceiverInternal.Stub.asInterface(list.readStrongBinder()), list.readString(), list.readInt(), list.readInt(), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.hardware.face.IFaceService");
      authenticate(list.readStrongBinder(), list.readLong(), list.readInt(), IFaceServiceReceiver.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readString());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.face.IFaceService");
    return true;
  }
  
  private static class Proxy implements IFaceService {
    public static IFaceService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback param2IBiometricServiceLockoutResetCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        if (param2IBiometricServiceLockoutResetCallback != null) {
          iBinder = param2IBiometricServiceLockoutResetCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().addLockoutResetCallback(param2IBiometricServiceLockoutResetCallback);
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
    
    public void authenticate(IBinder param2IBinder, long param2Long, int param2Int1, IFaceServiceReceiver param2IFaceServiceReceiver, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        try {
          parcel1.writeStrongBinder(param2IBinder);
          try {
            parcel1.writeLong(param2Long);
            try {
              IBinder iBinder;
              parcel1.writeInt(param2Int1);
              if (param2IFaceServiceReceiver != null) {
                iBinder = param2IFaceServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeInt(param2Int2);
                parcel1.writeString(param2String);
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                  IFaceService.Stub.getDefaultImpl().authenticate(param2IBinder, param2Long, param2Int1, param2IFaceServiceReceiver, param2Int2, param2String);
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
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().cancelAuthentication(param2IBinder, param2String);
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
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
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
                    if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                      IFaceService.Stub.getDefaultImpl().cancelAuthenticationFromService(param2IBinder, param2String, param2Int1, param2Int2, param2Int3, param2Boolean);
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
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().cancelEnrollment(param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enroll(int param2Int, IBinder param2IBinder, byte[] param2ArrayOfbyte, IFaceServiceReceiver param2IFaceServiceReceiver, String param2String, int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeStrongBinder(param2IBinder);
            try {
              IBinder iBinder;
              parcel1.writeByteArray(param2ArrayOfbyte);
              if (param2IFaceServiceReceiver != null) {
                iBinder = param2IFaceServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeString(param2String);
                try {
                  parcel1.writeIntArray(param2ArrayOfint);
                  try {
                    if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                      IFaceService.Stub.getDefaultImpl().enroll(param2Int, param2IBinder, param2ArrayOfbyte, param2IFaceServiceReceiver, param2String, param2ArrayOfint);
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
    
    public void enumerate(IBinder param2IBinder, int param2Int, IFaceServiceReceiver param2IFaceServiceReceiver) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeInt(param2Int);
        if (param2IFaceServiceReceiver != null) {
          iBinder = param2IFaceServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().enumerate(param2IBinder, param2Int, param2IFaceServiceReceiver);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long generateChallenge(IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
          return IFaceService.Stub.getDefaultImpl().generateChallenge(param2IBinder); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getAuthenticatorId(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
          return IFaceService.Stub.getDefaultImpl().getAuthenticatorId(param2Int); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<Face> getEnrolledFaces(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
          return IFaceService.Stub.getDefaultImpl().getEnrolledFaces(param2Int, param2String); 
        parcel2.readException();
        return parcel2.createTypedArrayList(Face.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void getFeature(int param2Int1, int param2Int2, IFaceServiceReceiver param2IFaceServiceReceiver, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2IFaceServiceReceiver != null) {
          iBinder = param2IFaceServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().getFeature(param2Int1, param2Int2, param2IFaceServiceReceiver, param2String);
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
      return "android.hardware.face.IFaceService";
    }
    
    public boolean hasEnrolledFaces(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(14, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          bool = IFaceService.Stub.getDefaultImpl().hasEnrolledFaces(param2Int, param2String);
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
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().initConfiguredStrength(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isHardwareDetected(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(11, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          bool = IFaceService.Stub.getDefaultImpl().isHardwareDetected(param2String);
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
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        try {
          IBinder iBinder;
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
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
            IFaceService.Stub.getDefaultImpl().prepareForAuthentication(param2Boolean, param2IBinder, param2Long, param2Int1, param2IBiometricServiceReceiverInternal, param2String, param2Int2, param2Int3, param2Int4, param2Int5);
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
    
    public void remove(IBinder param2IBinder, int param2Int1, int param2Int2, IFaceServiceReceiver param2IFaceServiceReceiver, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeStrongBinder(param2IBinder);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2IFaceServiceReceiver != null) {
          iBinder = param2IFaceServiceReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().remove(param2IBinder, param2Int1, param2Int2, param2IFaceServiceReceiver, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void rename(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().rename(param2Int, param2String);
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
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().resetLockout(param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int revokeChallenge(IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
          return IFaceService.Stub.getDefaultImpl().revokeChallenge(param2IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setActiveUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().setActiveUser(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFeature(int param2Int1, int param2Int2, boolean param2Boolean, byte[] param2ArrayOfbyte, IFaceServiceReceiver param2IFaceServiceReceiver, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        try {
          parcel1.writeInt(param2Int1);
          try {
            boolean bool;
            parcel1.writeInt(param2Int2);
            if (param2Boolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            try {
              IBinder iBinder;
              parcel1.writeByteArray(param2ArrayOfbyte);
              if (param2IFaceServiceReceiver != null) {
                iBinder = param2IFaceServiceReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                parcel1.writeString(param2String);
                try {
                  if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                    IFaceService.Stub.getDefaultImpl().setFeature(param2Int1, param2Int2, param2Boolean, param2ArrayOfbyte, param2IFaceServiceReceiver, param2String);
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
      parcel2.recycle();
      parcel1.recycle();
      throw param2ArrayOfbyte;
    }
    
    public void startPreparedClient(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().startPreparedClient(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void userActivity() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().userActivity();
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */