package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBiometricServiceReceiverInternal {
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
  
  public static IBiometricServiceReceiverInternal asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
    return (iInterface != null && iInterface instanceof IBiometricServiceReceiverInternal) ? (IBiometricServiceReceiverInternal)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBiometricServiceReceiverInternal getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBiometricServiceReceiverInternal != null) {
        Proxy.sDefaultImpl = paramIBiometricServiceReceiverInternal;
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
    byte[] arrayOfByte;
    if (paramInt1 != 1598968902) {
      boolean bool2;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onSystemEvent(paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onDeviceCredentialPressed();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onTryAgainPressed();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onDialogDismissed(paramParcel1.readInt(), paramParcel1.createByteArray());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onAcquired(paramParcel1.readInt(), paramParcel1.readString());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onError(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
          onAuthenticationFailed();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiverInternal");
      paramInt1 = paramParcel1.readInt();
      boolean bool1 = false;
      if (paramInt1 != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      arrayOfByte = paramParcel1.createByteArray();
      if (paramParcel1.readInt() != 0)
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiverInternal$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */