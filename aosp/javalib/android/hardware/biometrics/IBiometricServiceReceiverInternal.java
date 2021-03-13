package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBiometricServiceReceiverInternal extends IInterface {
  void onAcquired(int paramInt, String paramString) throws RemoteException;
  
  void onAuthenticationFailed() throws RemoteException;
  
  void onAuthenticationSucceeded(boolean paramBoolean1, byte[] paramArrayOfbyte, boolean paramBoolean2) throws RemoteException;
  
  void onDeviceCredentialPressed() throws RemoteException;
  
  void onDialogDismissed(int paramInt, byte[] paramArrayOfbyte) throws RemoteException;
  
  void onError(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException;
  
  void onSystemEvent(int paramInt) throws RemoteException;
  
  void onTryAgainPressed() throws RemoteException;
  
  public static class Default implements IBiometricServiceReceiverInternal {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAcquired(int param1Int, String param1String) throws RemoteException {}
    
    public void onAuthenticationFailed() throws RemoteException {}
    
    public void onAuthenticationSucceeded(boolean param1Boolean1, byte[] param1ArrayOfbyte, boolean param1Boolean2) throws RemoteException {}
    
    public void onDeviceCredentialPressed() throws RemoteException {}
    
    public void onDialogDismissed(int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void onError(int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {}
    
    public void onSystemEvent(int param1Int) throws RemoteException {}
    
    public void onTryAgainPressed() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBiometricServiceReceiverInternal {
    private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricServiceReceiverInternal";
    
    static final int TRANSACTION_onAcquired = 4;
    
    static final int TRANSACTION_onAuthenticationFailed = 2;
    
    static final int TRANSACTION_onAuthenticationSucceeded = 1;
    
    static final int TRANSACTION_onDeviceCredentialPressed = 7;
    
    static final int TRANSACTION_onDialogDismissed = 5;
    
    static final int TRANSACTION_onError = 3;
    
    static final int TRANSACTION_onSystemEvent = 8;
    
    static final int TRANSACTION_onTryAgainPressed = 6;
    
    public Stub() {
      attachInterface(this, "android.hardware.biometrics.IBiometricServiceReceiverInternal");
    }
    
    public static IBiometricServiceReceiverInternal asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      return (iInterface != null && iInterface instanceof IBiometricServiceReceiverInternal) ? (IBiometricServiceReceiverInternal)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBiometricServiceReceiverInternal getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 8:
          return "onSystemEvent";
        case 7:
          return "onDeviceCredentialPressed";
        case 6:
          return "onTryAgainPressed";
        case 5:
          return "onDialogDismissed";
        case 4:
          return "onAcquired";
        case 3:
          return "onError";
        case 2:
          return "onAuthenticationFailed";
        case 1:
          break;
      } 
      return "onAuthenticationSucceeded";
    }
    
    public static boolean setDefaultImpl(IBiometricServiceReceiverInternal param1IBiometricServiceReceiverInternal) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBiometricServiceReceiverInternal != null) {
          Proxy.sDefaultImpl = param1IBiometricServiceReceiverInternal;
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
      byte[] arrayOfByte;
      if (param1Int1 != 1598968902) {
        boolean bool2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onSystemEvent(param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onDeviceCredentialPressed();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onTryAgainPressed();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onDialogDismissed(param1Parcel1.readInt(), param1Parcel1.createByteArray());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onAcquired(param1Parcel1.readInt(), param1Parcel1.readString());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onError(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
            onAuthenticationFailed();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        param1Int1 = param1Parcel1.readInt();
        boolean bool1 = false;
        if (param1Int1 != 0) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        arrayOfByte = param1Parcel1.createByteArray();
        if (param1Parcel1.readInt() != 0)
          bool1 = true; 
        onAuthenticationSucceeded(bool2, arrayOfByte, bool1);
        return true;
      } 
      arrayOfByte.writeString("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      return true;
    }
    
    private static class Proxy implements IBiometricServiceReceiverInternal {
      public static IBiometricServiceReceiverInternal sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.biometrics.IBiometricServiceReceiverInternal";
      }
      
      public void onAcquired(int param2Int, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          parcel.writeInt(param2Int);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAcquired(param2Int, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationFailed() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAuthenticationFailed();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationSucceeded(boolean param2Boolean1, byte[] param2ArrayOfbyte, boolean param2Boolean2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          boolean bool1 = false;
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel.writeInt(bool2);
          parcel.writeByteArray(param2ArrayOfbyte);
          boolean bool2 = bool1;
          if (param2Boolean2)
            bool2 = true; 
          parcel.writeInt(bool2);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Boolean1, param2ArrayOfbyte, param2Boolean2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onDeviceCredentialPressed() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          if (!this.mRemote.transact(7, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onDeviceCredentialPressed();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onDialogDismissed(int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          parcel.writeInt(param2Int);
          parcel.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onDialogDismissed(param2Int, param2ArrayOfbyte);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onError(int param2Int1, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          parcel.writeInt(param2Int4);
          if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onError(param2Int1, param2Int2, param2Int3, param2Int4);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onSystemEvent(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(8, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onSystemEvent(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onTryAgainPressed() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onTryAgainPressed();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBiometricServiceReceiverInternal {
    public static IBiometricServiceReceiverInternal sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.biometrics.IBiometricServiceReceiverInternal";
    }
    
    public void onAcquired(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAcquired(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationFailed() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAuthenticationFailed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(boolean param1Boolean1, byte[] param1ArrayOfbyte, boolean param1Boolean2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        boolean bool1 = false;
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        parcel.writeByteArray(param1ArrayOfbyte);
        boolean bool2 = bool1;
        if (param1Boolean2)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onAuthenticationSucceeded(param1Boolean1, param1ArrayOfbyte, param1Boolean2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeviceCredentialPressed() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        if (!this.mRemote.transact(7, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onDeviceCredentialPressed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDialogDismissed(int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        parcel.writeInt(param1Int);
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onDialogDismissed(param1Int, param1ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onError(int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        parcel.writeInt(param1Int4);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onError(param1Int1, param1Int2, param1Int3, param1Int4);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSystemEvent(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onSystemEvent(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTryAgainPressed() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiverInternal");
        if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiverInternal.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiverInternal.Stub.getDefaultImpl().onTryAgainPressed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiverInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */