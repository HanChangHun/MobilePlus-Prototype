package android.hardware;

import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.VendorTagDescriptor;
import android.hardware.camera2.params.VendorTagDescriptorCache;
import android.hardware.camera2.utils.CameraIdAndSessionConfiguration;
import android.hardware.camera2.utils.ConcurrentCameraIdCombination;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

class Proxy implements ICameraService {
  public static ICameraService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public CameraStatus[] addListener(ICameraServiceListener paramICameraServiceListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (paramICameraServiceListener != null) {
        iBinder = paramICameraServiceListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().addListener(paramICameraServiceListener); 
      parcel2.readException();
      return (CameraStatus[])parcel2.createTypedArray(CameraStatus.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public ICamera connect(ICameraClient paramICameraClient, int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (paramICameraClient != null) {
        iBinder = paramICameraClient.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().connect(paramICameraClient, paramInt1, paramString, paramInt2, paramInt3); 
      parcel2.readException();
      return ICamera.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks paramICameraDeviceCallbacks, String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (paramICameraDeviceCallbacks != null) {
        iBinder = paramICameraDeviceCallbacks.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().connectDevice(paramICameraDeviceCallbacks, paramString1, paramString2, paramString3, paramInt); 
      parcel2.readException();
      return ICameraDeviceUser.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ICamera connectLegacy(ICameraClient paramICameraClient, int paramInt1, int paramInt2, String paramString, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (paramICameraClient != null) {
        iBinder = paramICameraClient.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().connectLegacy(paramICameraClient, paramInt1, paramInt2, paramString, paramInt3); 
      parcel2.readException();
      return ICamera.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CameraMetadataNative getCameraCharacteristics(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().getCameraCharacteristics(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CameraMetadataNative cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (CameraMetadataNative)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public CameraInfo getCameraInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      CameraInfo cameraInfo;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        cameraInfo = ICameraService.Stub.getDefaultImpl().getCameraInfo(paramInt);
        return cameraInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        cameraInfo = (CameraInfo)CameraInfo.CREATOR.createFromParcel(parcel2);
      } else {
        cameraInfo = null;
      } 
      return cameraInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      VendorTagDescriptorCache vendorTagDescriptorCache;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        vendorTagDescriptorCache = ICameraService.Stub.getDefaultImpl().getCameraVendorTagCache();
        return vendorTagDescriptorCache;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        vendorTagDescriptorCache = (VendorTagDescriptorCache)VendorTagDescriptorCache.CREATOR.createFromParcel(parcel2);
      } else {
        vendorTagDescriptorCache = null;
      } 
      return vendorTagDescriptorCache;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      VendorTagDescriptor vendorTagDescriptor;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        vendorTagDescriptor = ICameraService.Stub.getDefaultImpl().getCameraVendorTagDescriptor();
        return vendorTagDescriptor;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        vendorTagDescriptor = (VendorTagDescriptor)VendorTagDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        vendorTagDescriptor = null;
      } 
      return vendorTagDescriptor;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().getConcurrentCameraIds(); 
      parcel2.readException();
      return (ConcurrentCameraIdCombination[])parcel2.createTypedArray(ConcurrentCameraIdCombination.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.ICameraService";
  }
  
  public String getLegacyParameters(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
        return ICameraService.Stub.getDefaultImpl().getLegacyParameters(paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getNumberOfCameras(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        paramInt = ICameraService.Stub.getDefaultImpl().getNumberOfCameras(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] paramArrayOfCameraIdAndSessionConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      boolean bool = false;
      parcel1.writeTypedArray((Parcelable[])paramArrayOfCameraIdAndSessionConfiguration, 0);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        bool = ICameraService.Stub.getDefaultImpl().isConcurrentSessionConfigurationSupported(paramArrayOfCameraIdAndSessionConfiguration);
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
  
  public boolean isHiddenPhysicalCamera(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(15, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        bool = ICameraService.Stub.getDefaultImpl().isHiddenPhysicalCamera(paramString);
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
  
  public void notifyDeviceStateChange(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraService");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(18, parcel, null, 1) && ICameraService.Stub.getDefaultImpl() != null) {
        ICameraService.Stub.getDefaultImpl().notifyDeviceStateChange(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void notifySystemEvent(int paramInt, int[] paramArrayOfint) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraService");
      parcel.writeInt(paramInt);
      parcel.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(17, parcel, null, 1) && ICameraService.Stub.getDefaultImpl() != null) {
        ICameraService.Stub.getDefaultImpl().notifySystemEvent(paramInt, paramArrayOfint);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void removeListener(ICameraServiceListener paramICameraServiceListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      if (paramICameraServiceListener != null) {
        iBinder = paramICameraServiceListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        ICameraService.Stub.getDefaultImpl().removeListener(paramICameraServiceListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTorchMode(String paramString, boolean paramBoolean, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        ICameraService.Stub.getDefaultImpl().setTorchMode(paramString, paramBoolean, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean supportsCameraApi(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.ICameraService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(14, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
        bool = ICameraService.Stub.getDefaultImpl().supportsCameraApi(paramString, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */