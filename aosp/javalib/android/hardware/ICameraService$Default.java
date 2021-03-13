package android.hardware;

import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.VendorTagDescriptor;
import android.hardware.camera2.params.VendorTagDescriptorCache;
import android.hardware.camera2.utils.CameraIdAndSessionConfiguration;
import android.hardware.camera2.utils.ConcurrentCameraIdCombination;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ICameraService {
  public CameraStatus[] addListener(ICameraServiceListener paramICameraServiceListener) throws RemoteException {
    return null;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public ICamera connect(ICameraClient paramICameraClient, int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks paramICameraDeviceCallbacks, String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException {
    return null;
  }
  
  public ICamera connectLegacy(ICameraClient paramICameraClient, int paramInt1, int paramInt2, String paramString, int paramInt3) throws RemoteException {
    return null;
  }
  
  public CameraMetadataNative getCameraCharacteristics(String paramString) throws RemoteException {
    return null;
  }
  
  public CameraInfo getCameraInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
    return null;
  }
  
  public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
    return null;
  }
  
  public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
    return null;
  }
  
  public String getLegacyParameters(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getNumberOfCameras(int paramInt) throws RemoteException {
    return 0;
  }
  
  public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] paramArrayOfCameraIdAndSessionConfiguration) throws RemoteException {
    return false;
  }
  
  public boolean isHiddenPhysicalCamera(String paramString) throws RemoteException {
    return false;
  }
  
  public void notifyDeviceStateChange(long paramLong) throws RemoteException {}
  
  public void notifySystemEvent(int paramInt, int[] paramArrayOfint) throws RemoteException {}
  
  public void removeListener(ICameraServiceListener paramICameraServiceListener) throws RemoteException {}
  
  public void setTorchMode(String paramString, boolean paramBoolean, IBinder paramIBinder) throws RemoteException {}
  
  public boolean supportsCameraApi(String paramString, int paramInt) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */