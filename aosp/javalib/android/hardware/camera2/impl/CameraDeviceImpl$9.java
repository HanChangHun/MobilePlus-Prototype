package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraOfflineSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.ToIntFunction;

class null implements Runnable {
  public void run() {
    try {
      ICameraOfflineSession iCameraOfflineSession = CameraDeviceImpl.access$000(CameraDeviceImpl.this).switchToOffline((ICameraDeviceCallbacks)CameraDeviceImpl.access$300(CameraDeviceImpl.this).getCallbacks(), Arrays.<Integer>stream((Integer[])offlineStreamIds.toArray((Object[])new Integer[offlineStreamIds.size()])).mapToInt((ToIntFunction)_$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray());
      CameraDeviceImpl.access$300(CameraDeviceImpl.this).setRemoteSession(iCameraOfflineSession);
    } catch (CameraAccessException cameraAccessException) {
      CameraDeviceImpl.access$300(CameraDeviceImpl.this).notifyFailedSwitch();
    } finally {
      Exception exception;
    } 
    CameraDeviceImpl.access$302(CameraDeviceImpl.this, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$9.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */