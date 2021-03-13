package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IFingerprintServiceReceiver {
  private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintServiceReceiver";
  
  static final int TRANSACTION_onAcquired = 2;
  
  static final int TRANSACTION_onAuthenticationFailed = 5;
  
  static final int TRANSACTION_onAuthenticationSucceeded = 3;
  
  static final int TRANSACTION_onEnrollResult = 1;
  
  static final int TRANSACTION_onEnumerated = 8;
  
  static final int TRANSACTION_onError = 6;
  
  static final int TRANSACTION_onFingerprintDetected = 4;
  
  static final int TRANSACTION_onRemoved = 7;
  
  public Stub() {
    attachInterface(this, "android.hardware.fingerprint.IFingerprintServiceReceiver");
  }
  
  public static IFingerprintServiceReceiver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
    return (iInterface != null && iInterface instanceof IFingerprintServiceReceiver) ? (IFingerprintServiceReceiver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFingerprintServiceReceiver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 8:
        return "onEnumerated";
      case 7:
        return "onRemoved";
      case 6:
        return "onError";
      case 5:
        return "onAuthenticationFailed";
      case 4:
        return "onFingerprintDetected";
      case 3:
        return "onAuthenticationSucceeded";
      case 2:
        return "onAcquired";
      case 1:
        break;
    } 
    return "onEnrollResult";
  }
  
  public static boolean setDefaultImpl(IFingerprintServiceReceiver paramIFingerprintServiceReceiver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFingerprintServiceReceiver != null) {
        Proxy.sDefaultImpl = paramIFingerprintServiceReceiver;
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
      long l;
      boolean bool = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          onEnumerated(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          onRemoved(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          onError(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          onAuthenticationFailed(paramParcel1.readLong());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          l = paramParcel1.readLong();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool = true; 
          onFingerprintDetected(l, paramInt1, bool);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          l = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            Fingerprint fingerprint = (Fingerprint)Fingerprint.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          onAuthenticationSucceeded(l, (Fingerprint)paramParcel2, paramInt1, bool);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
          onAcquired(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
      onEnrollResult(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.hardware.fingerprint.IFingerprintServiceReceiver");
    return true;
  }
  
  private static class Proxy implements IFingerprintServiceReceiver {
    public static IFingerprintServiceReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.fingerprint.IFingerprintServiceReceiver";
    }
    
    public void onAcquired(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onAcquired(param2Long, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationFailed(long param2Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(5, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(long param2Long, Fingerprint param2Fingerprint, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        boolean bool = false;
        if (param2Fingerprint != null) {
          parcel.writeInt(1);
          param2Fingerprint.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Long, param2Fingerprint, param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnrollResult(long param2Long, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnrollResult(param2Long, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnumerated(long param2Long, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(8, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnumerated(param2Long, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onError(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(6, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onError(param2Long, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFingerprintDetected(long param2Long, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(4, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onFingerprintDetected(param2Long, param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoved(long param2Long, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(7, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onRemoved(param2Long, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintServiceReceiver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */