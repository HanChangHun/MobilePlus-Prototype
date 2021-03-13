package android.hardware.face;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IFaceServiceReceiver {
  public static IFaceServiceReceiver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.face.IFaceServiceReceiver";
  }
  
  public void onAcquired(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onAcquired(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationFailed(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(4, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAuthenticationSucceeded(long paramLong, Face paramFace, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      boolean bool = false;
      if (paramFace != null) {
        parcel.writeInt(1);
        paramFace.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(3, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(paramLong, paramFace, paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEnrollResult(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onEnrollResult(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEnumerated(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(7, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onEnumerated(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onError(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(5, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onError(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onFeatureGet(boolean paramBoolean1, int paramInt, boolean paramBoolean2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      boolean bool1 = false;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      parcel.writeInt(paramInt);
      boolean bool2 = bool1;
      if (paramBoolean2)
        bool2 = true; 
      parcel.writeInt(bool2);
      if (!this.mRemote.transact(9, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureGet(paramBoolean1, paramInt, paramBoolean2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onFeatureSet(boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureSet(paramBoolean, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRemoved(long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(6, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
        IFaceServiceReceiver.Stub.getDefaultImpl().onRemoved(paramLong, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceServiceReceiver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */