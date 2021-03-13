package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICameraDeviceCallbacks {
  private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceCallbacks";
  
  static final int TRANSACTION_onCaptureStarted = 3;
  
  static final int TRANSACTION_onDeviceError = 1;
  
  static final int TRANSACTION_onDeviceIdle = 2;
  
  static final int TRANSACTION_onPrepared = 5;
  
  static final int TRANSACTION_onRepeatingRequestError = 6;
  
  static final int TRANSACTION_onRequestQueueEmpty = 7;
  
  static final int TRANSACTION_onResultReceived = 4;
  
  public Stub() {
    attachInterface(this, "android.hardware.camera2.ICameraDeviceCallbacks");
  }
  
  public static ICameraDeviceCallbacks asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.camera2.ICameraDeviceCallbacks");
    return (iInterface != null && iInterface instanceof ICameraDeviceCallbacks) ? (ICameraDeviceCallbacks)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICameraDeviceCallbacks getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return "onRequestQueueEmpty";
      case 6:
        return "onRepeatingRequestError";
      case 5:
        return "onPrepared";
      case 4:
        return "onResultReceived";
      case 3:
        return "onCaptureStarted";
      case 2:
        return "onDeviceIdle";
      case 1:
        break;
    } 
    return "onDeviceError";
  }
  
  public static boolean setDefaultImpl(ICameraDeviceCallbacks paramICameraDeviceCallbacks) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICameraDeviceCallbacks != null) {
        Proxy.sDefaultImpl = paramICameraDeviceCallbacks;
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
      CaptureResultExtras captureResultExtras;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
          onRequestQueueEmpty();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
          onRepeatingRequestError(paramParcel1.readLong(), paramParcel1.readInt());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
          onPrepared(paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
          if (paramParcel1.readInt() != 0) {
            CameraMetadataNative cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            captureResultExtras = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(paramParcel1);
          } else {
            captureResultExtras = null;
          } 
          onResultReceived((CameraMetadataNative)paramParcel2, captureResultExtras, (PhysicalCaptureResultInfo[])paramParcel1.createTypedArray(PhysicalCaptureResultInfo.CREATOR));
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
          if (paramParcel1.readInt() != 0) {
            CaptureResultExtras captureResultExtras1 = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onCaptureStarted((CaptureResultExtras)paramParcel2, paramParcel1.readLong());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
          onDeviceIdle();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {
        CaptureResultExtras captureResultExtras1 = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onDeviceError(paramInt1, (CaptureResultExtras)paramParcel1);
      return true;
    } 
    paramParcel2.writeString("android.hardware.camera2.ICameraDeviceCallbacks");
    return true;
  }
  
  private static class Proxy implements ICameraDeviceCallbacks {
    public static ICameraDeviceCallbacks sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.camera2.ICameraDeviceCallbacks";
    }
    
    public void onCaptureStarted(CaptureResultExtras param2CaptureResultExtras, long param2Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (param2CaptureResultExtras != null) {
          parcel.writeInt(1);
          param2CaptureResultExtras.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeLong(param2Long);
        if (!this.mRemote.transact(3, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onCaptureStarted(param2CaptureResultExtras, param2Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeviceError(int param2Int, CaptureResultExtras param2CaptureResultExtras) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        parcel.writeInt(param2Int);
        if (param2CaptureResultExtras != null) {
          parcel.writeInt(1);
          param2CaptureResultExtras.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceError(param2Int, param2CaptureResultExtras);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeviceIdle() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (!this.mRemote.transact(2, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceIdle();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPrepared(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onPrepared(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRepeatingRequestError(long param2Long, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onRepeatingRequestError(param2Long, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRequestQueueEmpty() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (!this.mRemote.transact(7, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onRequestQueueEmpty();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onResultReceived(CameraMetadataNative param2CameraMetadataNative, CaptureResultExtras param2CaptureResultExtras, PhysicalCaptureResultInfo[] param2ArrayOfPhysicalCaptureResultInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (param2CameraMetadataNative != null) {
          parcel.writeInt(1);
          param2CameraMetadataNative.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2CaptureResultExtras != null) {
          parcel.writeInt(1);
          param2CaptureResultExtras.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeTypedArray((Parcelable[])param2ArrayOfPhysicalCaptureResultInfo, 0);
        if (!this.mRemote.transact(4, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onResultReceived(param2CameraMetadataNative, param2CaptureResultExtras, param2ArrayOfPhysicalCaptureResultInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceCallbacks$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */