package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBiometricServiceReceiver {
  private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricServiceReceiver";
  
  static final int TRANSACTION_onAcquired = 4;
  
  static final int TRANSACTION_onAuthenticationFailed = 2;
  
  static final int TRANSACTION_onAuthenticationSucceeded = 1;
  
  static final int TRANSACTION_onDialogDismissed = 5;
  
  static final int TRANSACTION_onError = 3;
  
  static final int TRANSACTION_onSystemEvent = 6;
  
  public Stub() {
    attachInterface(this, "android.hardware.biometrics.IBiometricServiceReceiver");
  }
  
  public static IBiometricServiceReceiver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.biometrics.IBiometricServiceReceiver");
    return (iInterface != null && iInterface instanceof IBiometricServiceReceiver) ? (IBiometricServiceReceiver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBiometricServiceReceiver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 6:
        return "onSystemEvent";
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
  
  public static boolean setDefaultImpl(IBiometricServiceReceiver paramIBiometricServiceReceiver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBiometricServiceReceiver != null) {
        Proxy.sDefaultImpl = paramIBiometricServiceReceiver;
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
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
          onSystemEvent(paramParcel1.readInt());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
          onDialogDismissed(paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
          onAcquired(paramParcel1.readInt(), paramParcel1.readString());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
          onError(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
          onAuthenticationFailed();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
      onAuthenticationSucceeded(paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.hardware.biometrics.IBiometricServiceReceiver");
    return true;
  }
  
  private static class Proxy implements IBiometricServiceReceiver {
    public static IBiometricServiceReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.biometrics.IBiometricServiceReceiver";
    }
    
    public void onAcquired(int param2Int, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onAcquired(param2Int, param2String);
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
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDialogDismissed(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onDialogDismissed(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onError(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onError(param2Int1, param2Int2, param2Int3);
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
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onSystemEvent(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */