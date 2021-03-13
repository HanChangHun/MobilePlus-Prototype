package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

class Proxy implements ICameraDeviceCallbacks {
  public static ICameraDeviceCallbacks sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.camera2.ICameraDeviceCallbacks";
  }
  
  public void onCaptureStarted(CaptureResultExtras paramCaptureResultExtras, long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
      if (paramCaptureResultExtras != null) {
        parcel.writeInt(1);
        paramCaptureResultExtras.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(3, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
        ICameraDeviceCallbacks.Stub.getDefaultImpl().onCaptureStarted(paramCaptureResultExtras, paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
      parcel.writeInt(paramInt);
      if (paramCaptureResultExtras != null) {
        parcel.writeInt(1);
        paramCaptureResultExtras.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
        ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceError(paramInt, paramCaptureResultExtras);
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
  
  public void onPrepared(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
        ICameraDeviceCallbacks.Stub.getDefaultImpl().onPrepared(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRepeatingRequestError(long paramLong, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
        ICameraDeviceCallbacks.Stub.getDefaultImpl().onRepeatingRequestError(paramLong, paramInt);
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
  
  public void onResultReceived(CameraMetadataNative paramCameraMetadataNative, CaptureResultExtras paramCaptureResultExtras, PhysicalCaptureResultInfo[] paramArrayOfPhysicalCaptureResultInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
      if (paramCameraMetadataNative != null) {
        parcel.writeInt(1);
        paramCameraMetadataNative.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramCaptureResultExtras != null) {
        parcel.writeInt(1);
        paramCaptureResultExtras.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeTypedArray((Parcelable[])paramArrayOfPhysicalCaptureResultInfo, 0);
      if (!this.mRemote.transact(4, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
        ICameraDeviceCallbacks.Stub.getDefaultImpl().onResultReceived(paramCameraMetadataNative, paramCaptureResultExtras, paramArrayOfPhysicalCaptureResultInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceCallbacks$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */