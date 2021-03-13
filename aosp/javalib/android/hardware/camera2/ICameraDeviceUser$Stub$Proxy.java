package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.view.Surface;

class Proxy implements ICameraDeviceUser {
  public static ICameraDeviceUser sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void beginConfigure() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().beginConfigure();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long cancelRequest(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().cancelRequest(paramInt); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CameraMetadataNative createDefaultRequest(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      CameraMetadataNative cameraMetadataNative;
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        cameraMetadataNative = ICameraDeviceUser.Stub.getDefaultImpl().createDefaultRequest(paramInt);
        return cameraMetadataNative;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
      } else {
        cameraMetadataNative = null;
      } 
      return cameraMetadataNative;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int createInputStream(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        paramInt1 = ICameraDeviceUser.Stub.getDefaultImpl().createInputStream(paramInt1, paramInt2, paramInt3);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int createStream(OutputConfiguration paramOutputConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (paramOutputConfiguration != null) {
        parcel1.writeInt(1);
        paramOutputConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().createStream(paramOutputConfiguration); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteStream(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().deleteStream(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disconnect() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().disconnect();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] endConfigure(int paramInt, CameraMetadataNative paramCameraMetadataNative) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (paramCameraMetadataNative != null) {
        parcel1.writeInt(1);
        paramCameraMetadataNative.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().endConfigure(paramInt, paramCameraMetadataNative); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finalizeOutputConfigurations(int paramInt, OutputConfiguration paramOutputConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (paramOutputConfiguration != null) {
        parcel1.writeInt(1);
        paramOutputConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().finalizeOutputConfigurations(paramInt, paramOutputConfiguration);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long flush() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().flush(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CameraMetadataNative getCameraInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      CameraMetadataNative cameraMetadataNative;
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        cameraMetadataNative = ICameraDeviceUser.Stub.getDefaultImpl().getCameraInfo();
        return cameraMetadataNative;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
      } else {
        cameraMetadataNative = null;
      } 
      return cameraMetadataNative;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getGlobalAudioRestriction() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().getGlobalAudioRestriction(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Surface getInputSurface() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Surface surface;
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        surface = ICameraDeviceUser.Stub.getDefaultImpl().getInputSurface();
        return surface;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        surface = (Surface)Surface.CREATOR.createFromParcel(parcel2);
      } else {
        surface = null;
      } 
      return surface;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.camera2.ICameraDeviceUser";
  }
  
  public boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      boolean bool = true;
      if (paramSessionConfiguration != null) {
        parcel1.writeInt(1);
        paramSessionConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        bool = ICameraDeviceUser.Stub.getDefaultImpl().isSessionConfigurationSupported(paramSessionConfiguration);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void prepare(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().prepare(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void prepare2(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().prepare2(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCameraAudioRestriction(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().setCameraAudioRestriction(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public SubmitInfo submitRequest(CaptureRequest paramCaptureRequest, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      boolean bool = true;
      if (paramCaptureRequest != null) {
        parcel1.writeInt(1);
        paramCaptureRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().submitRequest(paramCaptureRequest, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        SubmitInfo submitInfo = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramCaptureRequest = null;
      } 
      return (SubmitInfo)paramCaptureRequest;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public SubmitInfo submitRequestList(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeTypedArray((Parcelable[])paramArrayOfCaptureRequest, 0);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().submitRequestList(paramArrayOfCaptureRequest, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        SubmitInfo submitInfo = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramArrayOfCaptureRequest = null;
      } 
      return (SubmitInfo)paramArrayOfCaptureRequest;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks paramICameraDeviceCallbacks, int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (paramICameraDeviceCallbacks != null) {
        iBinder = paramICameraDeviceCallbacks.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
        return ICameraDeviceUser.Stub.getDefaultImpl().switchToOffline(paramICameraDeviceCallbacks, paramArrayOfint); 
      parcel2.readException();
      return ICameraOfflineSession.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void tearDown(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().tearDown(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateOutputConfiguration(int paramInt, OutputConfiguration paramOutputConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      parcel1.writeInt(paramInt);
      if (paramOutputConfiguration != null) {
        parcel1.writeInt(1);
        paramOutputConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().updateOutputConfiguration(paramInt, paramOutputConfiguration);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void waitUntilIdle() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
        ICameraDeviceUser.Stub.getDefaultImpl().waitUntilIdle();
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceUser$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */