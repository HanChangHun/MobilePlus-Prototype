package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

class CallbackHandler extends Handler {
  public CallbackHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    try {
      IllegalArgumentException illegalArgumentException;
      Object[] arrayOfObject1;
      CaptureResultExtras captureResultExtras1;
      StringBuilder stringBuilder;
      Object[] arrayOfObject2;
      CameraMetadataNative cameraMetadataNative;
      long l1;
      long l2;
      switch (paramMessage.what) {
        default:
          illegalArgumentException = new IllegalArgumentException();
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Unknown callback message ");
          stringBuilder.append(paramMessage.what);
          this(stringBuilder.toString());
          throw illegalArgumentException;
        case 6:
          CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onRequestQueueEmpty();
          return;
        case 5:
          arrayOfObject2 = (Object[])paramMessage.obj;
          l1 = ((Long)arrayOfObject2[0]).longValue();
          i = ((Integer)arrayOfObject2[1]).intValue();
          CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onRepeatingRequestError(l1, i);
          return;
        case 4:
          i = paramMessage.arg1;
          CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onPrepared(i);
          return;
        case 3:
          arrayOfObject1 = (Object[])paramMessage.obj;
          cameraMetadataNative = (CameraMetadataNative)arrayOfObject1[0];
          captureResultExtras1 = (CaptureResultExtras)arrayOfObject1[1];
          CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onResultReceived(cameraMetadataNative, captureResultExtras1, new android.hardware.camera2.impl.PhysicalCaptureResultInfo[0]);
          return;
        case 2:
          l2 = paramMessage.arg2;
          l1 = paramMessage.arg1;
          captureResultExtras2 = (CaptureResultExtras)paramMessage.obj;
          CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onCaptureStarted(captureResultExtras2, (l2 & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL & l1);
          return;
        case 1:
          CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onDeviceIdle();
          return;
        case 0:
          break;
      } 
      int i = paramMessage.arg1;
      CaptureResultExtras captureResultExtras2 = (CaptureResultExtras)paramMessage.obj;
      CameraDeviceUserShim.CameraCallbackThread.access$000(CameraDeviceUserShim.CameraCallbackThread.this).onDeviceError(i, captureResultExtras2);
      return;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Received remote exception during camera callback ");
      stringBuilder.append(paramMessage.what);
      throw new IllegalStateException(stringBuilder.toString(), remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CameraDeviceUserShim$CameraCallbackThread$CallbackHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */