package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ICameraDeviceCallbacks {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCaptureStarted(CaptureResultExtras paramCaptureResultExtras, long paramLong) throws RemoteException {}
  
  public void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) throws RemoteException {}
  
  public void onDeviceIdle() throws RemoteException {}
  
  public void onPrepared(int paramInt) throws RemoteException {}
  
  public void onRepeatingRequestError(long paramLong, int paramInt) throws RemoteException {}
  
  public void onRequestQueueEmpty() throws RemoteException {}
  
  public void onResultReceived(CameraMetadataNative paramCameraMetadataNative, CaptureResultExtras paramCaptureResultExtras, PhysicalCaptureResultInfo[] paramArrayOfPhysicalCaptureResultInfo) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceCallbacks$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */