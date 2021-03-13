package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.view.Surface;

public class ICameraDeviceUserWrapper {
  private final ICameraDeviceUser mRemoteDevice;
  
  public ICameraDeviceUserWrapper(ICameraDeviceUser paramICameraDeviceUser) {
    if (paramICameraDeviceUser != null) {
      this.mRemoteDevice = paramICameraDeviceUser;
      return;
    } 
    throw new NullPointerException("Remote device may not be null");
  }
  
  public void beginConfigure() throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public long cancelRequest(int paramInt) throws CameraAccessException {
    try {
      return this.mRemoteDevice.cancelRequest(paramInt);
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public CameraMetadataNative createDefaultRequest(int paramInt) throws CameraAccessException {
    try {
      return this.mRemoteDevice.createDefaultRequest(paramInt);
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public int createInputStream(int paramInt1, int paramInt2, int paramInt3) throws CameraAccessException {
    try {
      return this.mRemoteDevice.createInputStream(paramInt1, paramInt2, paramInt3);
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public int createStream(OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    try {
      return this.mRemoteDevice.createStream(paramOutputConfiguration);
    } finally {
      paramOutputConfiguration = null;
      CameraManager.throwAsPublicException((Throwable)paramOutputConfiguration);
    } 
  }
  
  public void deleteStream(int paramInt) throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public void disconnect() {
    try {
      this.mRemoteDevice.disconnect();
    } catch (RemoteException remoteException) {}
  }
  
  public int[] endConfigure(int paramInt, CameraMetadataNative paramCameraMetadataNative) throws CameraAccessException {
    try {
      return iCameraDeviceUser.endConfigure(paramInt, paramCameraMetadataNative);
    } finally {
      paramCameraMetadataNative = null;
      CameraManager.throwAsPublicException((Throwable)paramCameraMetadataNative);
    } 
  }
  
  public void finalizeOutputConfigurations(int paramInt, OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    try {
      return;
    } finally {
      paramOutputConfiguration = null;
      CameraManager.throwAsPublicException((Throwable)paramOutputConfiguration);
    } 
  }
  
  public long flush() throws CameraAccessException {
    try {
      return this.mRemoteDevice.flush();
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public CameraMetadataNative getCameraInfo() throws CameraAccessException {
    try {
      return this.mRemoteDevice.getCameraInfo();
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public int getGlobalAudioRestriction() throws CameraAccessException {
    try {
      return this.mRemoteDevice.getGlobalAudioRestriction();
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public Surface getInputSurface() throws CameraAccessException {
    try {
      return this.mRemoteDevice.getInputSurface();
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) throws CameraAccessException {
    try {
      return this.mRemoteDevice.isSessionConfigurationSupported(paramSessionConfiguration);
    } catch (ServiceSpecificException serviceSpecificException) {
      if (serviceSpecificException.errorCode != 10) {
        if (serviceSpecificException.errorCode == 3)
          throw new IllegalArgumentException("Invalid session configuration"); 
        throw serviceSpecificException;
      } 
      throw new UnsupportedOperationException("Session configuration query not supported");
    } finally {
      paramSessionConfiguration = null;
      CameraManager.throwAsPublicException((Throwable)paramSessionConfiguration);
    } 
  }
  
  public void prepare(int paramInt) throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public void prepare2(int paramInt1, int paramInt2) throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public void setCameraAudioRestriction(int paramInt) throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public SubmitInfo submitRequest(CaptureRequest paramCaptureRequest, boolean paramBoolean) throws CameraAccessException {
    try {
      return this.mRemoteDevice.submitRequest(paramCaptureRequest, paramBoolean);
    } finally {
      paramCaptureRequest = null;
      CameraManager.throwAsPublicException((Throwable)paramCaptureRequest);
    } 
  }
  
  public SubmitInfo submitRequestList(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) throws CameraAccessException {
    try {
      return this.mRemoteDevice.submitRequestList(paramArrayOfCaptureRequest, paramBoolean);
    } finally {
      paramArrayOfCaptureRequest = null;
      CameraManager.throwAsPublicException((Throwable)paramArrayOfCaptureRequest);
    } 
  }
  
  public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks paramICameraDeviceCallbacks, int[] paramArrayOfint) throws CameraAccessException {
    try {
      return this.mRemoteDevice.switchToOffline(paramICameraDeviceCallbacks, paramArrayOfint);
    } finally {
      paramICameraDeviceCallbacks = null;
      CameraManager.throwAsPublicException((Throwable)paramICameraDeviceCallbacks);
    } 
  }
  
  public void tearDown(int paramInt) throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
  
  public void unlinkToDeath(IBinder.DeathRecipient paramDeathRecipient, int paramInt) {
    if (this.mRemoteDevice.asBinder() != null)
      this.mRemoteDevice.asBinder().unlinkToDeath(paramDeathRecipient, paramInt); 
  }
  
  public void updateOutputConfiguration(int paramInt, OutputConfiguration paramOutputConfiguration) throws CameraAccessException {
    try {
      return;
    } finally {
      paramOutputConfiguration = null;
      CameraManager.throwAsPublicException((Throwable)paramOutputConfiguration);
    } 
  }
  
  public void waitUntilIdle() throws CameraAccessException {
    try {
      return;
    } finally {
      Exception exception = null;
      CameraManager.throwAsPublicException(exception);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/ICameraDeviceUserWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */