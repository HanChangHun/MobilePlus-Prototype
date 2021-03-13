package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;

public class Default implements ICameraDeviceUser {
  public IBinder asBinder() {
    return null;
  }
  
  public void beginConfigure() throws RemoteException {}
  
  public long cancelRequest(int paramInt) throws RemoteException {
    return 0L;
  }
  
  public CameraMetadataNative createDefaultRequest(int paramInt) throws RemoteException {
    return null;
  }
  
  public int createInputStream(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int createStream(OutputConfiguration paramOutputConfiguration) throws RemoteException {
    return 0;
  }
  
  public void deleteStream(int paramInt) throws RemoteException {}
  
  public void disconnect() throws RemoteException {}
  
  public int[] endConfigure(int paramInt, CameraMetadataNative paramCameraMetadataNative) throws RemoteException {
    return null;
  }
  
  public void finalizeOutputConfigurations(int paramInt, OutputConfiguration paramOutputConfiguration) throws RemoteException {}
  
  public long flush() throws RemoteException {
    return 0L;
  }
  
  public CameraMetadataNative getCameraInfo() throws RemoteException {
    return null;
  }
  
  public int getGlobalAudioRestriction() throws RemoteException {
    return 0;
  }
  
  public Surface getInputSurface() throws RemoteException {
    return null;
  }
  
  public boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) throws RemoteException {
    return false;
  }
  
  public void prepare(int paramInt) throws RemoteException {}
  
  public void prepare2(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setCameraAudioRestriction(int paramInt) throws RemoteException {}
  
  public SubmitInfo submitRequest(CaptureRequest paramCaptureRequest, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public SubmitInfo submitRequestList(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks paramICameraDeviceCallbacks, int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public void tearDown(int paramInt) throws RemoteException {}
  
  public void updateOutputConfiguration(int paramInt, OutputConfiguration paramOutputConfiguration) throws RemoteException {}
  
  public void waitUntilIdle() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceUser$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */