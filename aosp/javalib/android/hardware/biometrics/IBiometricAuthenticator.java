package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBiometricAuthenticator extends IInterface {
  void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException;
  
  long getAuthenticatorId(int paramInt) throws RemoteException;
  
  boolean hasEnrolledTemplates(int paramInt, String paramString) throws RemoteException;
  
  boolean isHardwareDetected(String paramString) throws RemoteException;
  
  void prepareForAuthentication(boolean paramBoolean, IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException;
  
  void resetLockout(byte[] paramArrayOfbyte) throws RemoteException;
  
  void setActiveUser(int paramInt) throws RemoteException;
  
  void startPreparedClient(int paramInt) throws RemoteException;
  
  public static class Default implements IBiometricAuthenticator {
    public IBinder asBinder() {
      return null;
    }
    
    public void cancelAuthenticationFromService(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) throws RemoteException {}
    
    public long getAuthenticatorId(int param1Int) throws RemoteException {
      return 0L;
    }
    
    public boolean hasEnrolledTemplates(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public boolean isHardwareDetected(String param1String) throws RemoteException {
      return false;
    }
    
    public void prepareForAuthentication(boolean param1Boolean, IBinder param1IBinder, long param1Long, int param1Int1, IBiometricServiceReceiverInternal param1IBiometricServiceReceiverInternal, String param1String, int param1Int2, int param1Int3, int param1Int4, int param1Int5) throws RemoteException {}
    
    public void resetLockout(byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void setActiveUser(int param1Int) throws RemoteException {}
    
    public void startPreparedClient(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBiometricAuthenticator {
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
    
    public static IBiometricAuthenticator asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.biometrics.IBiometricAuthenticator");
      return (iInterface != null && iInterface instanceof IBiometricAuthenticator) ? (IBiometricAuthenticator)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBiometricAuthenticator getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBiometricAuthenticator param1IBiometricAuthenticator) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBiometricAuthenticator != null) {
          Proxy.sDefaultImpl = param1IBiometricAuthenticator;
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
        long l;
        IBinder iBinder;
        String str;
        int j;
        boolean bool1;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            l = getAuthenticatorId(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            setActiveUser(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            resetLockout(param1Parcel1.createByteArray());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            bool = hasEnrolledTemplates(param1Parcel1.readInt(), param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            bool = isHardwareDetected(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            iBinder = param1Parcel1.readStrongBinder();
            str = param1Parcel1.readString();
            i = param1Parcel1.readInt();
            param1Int2 = param1Parcel1.readInt();
            j = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            cancelAuthenticationFromService(iBinder, str, i, param1Int2, j, bool1);
            param1Parcel2.writeNoException();
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
            startPreparedClient(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricAuthenticator");
        if (param1Parcel1.readInt() != 0) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        prepareForAuthentication(bool1, param1Parcel1.readStrongBinder(), param1Parcel1.readLong(), param1Parcel1.readInt(), IBiometricServiceReceiverInternal.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.hardware.biometrics.IBiometricAuthenticator");
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
  
  private static class Proxy implements IBiometricAuthenticator {
    public static IBiometricAuthenticator sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancelAuthenticationFromService(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
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
                    if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
                      IBiometricAuthenticator.Stub.getDefaultImpl().cancelAuthenticationFromService(param1IBinder, param1String, param1Int1, param1Int2, param1Int3, param1Boolean);
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
    
    public long getAuthenticatorId(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null)
          return IBiometricAuthenticator.Stub.getDefaultImpl().getAuthenticatorId(param1Int); 
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
    
    public boolean hasEnrolledTemplates(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          bool = IBiometricAuthenticator.Stub.getDefaultImpl().hasEnrolledTemplates(param1Int, param1String);
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
    
    public boolean isHardwareDetected(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          bool = IBiometricAuthenticator.Stub.getDefaultImpl().isHardwareDetected(param1String);
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
    
    public void prepareForAuthentication(boolean param1Boolean, IBinder param1IBinder, long param1Long, int param1Int1, IBiometricServiceReceiverInternal param1IBiometricServiceReceiverInternal, String param1String, int param1Int2, int param1Int3, int param1Int4, int param1Int5) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeLong(param1Long);
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
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().prepareForAuthentication(param1Boolean, param1IBinder, param1Long, param1Int1, param1IBiometricServiceReceiverInternal, param1String, param1Int2, param1Int3, param1Int4, param1Int5);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().resetLockout(param1ArrayOfbyte);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().setActiveUser(param1Int);
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
        parcel1.writeInterfaceToken("android.hardware.biometrics.IBiometricAuthenticator");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBiometricAuthenticator.Stub.getDefaultImpl() != null) {
          IBiometricAuthenticator.Stub.getDefaultImpl().startPreparedClient(param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricAuthenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */