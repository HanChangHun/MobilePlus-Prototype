package android.hardware.face;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IFaceServiceReceiver {
  private static final String DESCRIPTOR = "android.hardware.face.IFaceServiceReceiver";
  
  static final int TRANSACTION_onAcquired = 2;
  
  static final int TRANSACTION_onAuthenticationFailed = 4;
  
  static final int TRANSACTION_onAuthenticationSucceeded = 3;
  
  static final int TRANSACTION_onEnrollResult = 1;
  
  static final int TRANSACTION_onEnumerated = 7;
  
  static final int TRANSACTION_onError = 5;
  
  static final int TRANSACTION_onFeatureGet = 9;
  
  static final int TRANSACTION_onFeatureSet = 8;
  
  static final int TRANSACTION_onRemoved = 6;
  
  public Stub() {
    attachInterface(this, "android.hardware.face.IFaceServiceReceiver");
  }
  
  public static IFaceServiceReceiver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.face.IFaceServiceReceiver");
    return (iInterface != null && iInterface instanceof IFaceServiceReceiver) ? (IFaceServiceReceiver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFaceServiceReceiver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 9:
        return "onFeatureGet";
      case 8:
        return "onFeatureSet";
      case 7:
        return "onEnumerated";
      case 6:
        return "onRemoved";
      case 5:
        return "onError";
      case 4:
        return "onAuthenticationFailed";
      case 3:
        return "onAuthenticationSucceeded";
      case 2:
        return "onAcquired";
      case 1:
        break;
    } 
    return "onEnrollResult";
  }
  
  public static boolean setDefaultImpl(IFaceServiceReceiver paramIFaceServiceReceiver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFaceServiceReceiver != null) {
        Proxy.sDefaultImpl = paramIFaceServiceReceiver;
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
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 9:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onFeatureGet(bool1, paramInt1, bool2);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          if (paramParcel1.readInt() != 0)
            bool1 = true; 
          onFeatureSet(bool1, paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          onEnumerated(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          onRemoved(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          onError(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          onAuthenticationFailed(paramParcel1.readLong());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          l = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            Face face = (Face)Face.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          onAuthenticationSucceeded(l, (Face)paramParcel2, paramInt1, bool1);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
          onAcquired(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
      onEnrollResult(paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.hardware.face.IFaceServiceReceiver");
    return true;
  }
  
  private static class Proxy implements IFaceServiceReceiver {
    public static IFaceServiceReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.face.IFaceServiceReceiver";
    }
    
    public void onAcquired(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onAcquired(param2Long, param2Int1, param2Int2);
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
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(4, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(long param2Long, Face param2Face, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        boolean bool = false;
        if (param2Face != null) {
          parcel.writeInt(1);
          param2Face.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Long, param2Face, param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnrollResult(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onEnrollResult(param2Long, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnumerated(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(7, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onEnumerated(param2Long, param2Int1, param2Int2);
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
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onError(param2Long, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFeatureGet(boolean param2Boolean1, int param2Int, boolean param2Boolean2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        boolean bool1 = false;
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        parcel.writeInt(param2Int);
        boolean bool2 = bool1;
        if (param2Boolean2)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(9, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureGet(param2Boolean1, param2Int, param2Boolean2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFeatureSet(boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureSet(param2Boolean, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoved(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(6, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onRemoved(param2Long, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceServiceReceiver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */