package android.hardware.camera2.legacy;

import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

class CameraCallbackThread implements ICameraDeviceCallbacks {
  private static final int CAMERA_ERROR = 0;
  
  private static final int CAMERA_IDLE = 1;
  
  private static final int CAPTURE_STARTED = 2;
  
  private static final int PREPARED = 4;
  
  private static final int REPEATING_REQUEST_ERROR = 5;
  
  private static final int REQUEST_QUEUE_EMPTY = 6;
  
  private static final int RESULT_RECEIVED = 3;
  
  private final ICameraDeviceCallbacks mCallbacks;
  
  private Handler mHandler;
  
  private final HandlerThread mHandlerThread;
  
  public CameraCallbackThread(ICameraDeviceCallbacks paramICameraDeviceCallbacks) {
    this.mCallbacks = paramICameraDeviceCallbacks;
    HandlerThread handlerThread = new HandlerThread("LegacyCameraCallback");
    this.mHandlerThread = handlerThread;
    handlerThread.start();
  }
  
  private Handler getHandler() {
    if (this.mHandler == null)
      this.mHandler = new CallbackHandler(this.mHandlerThread.getLooper()); 
    return this.mHandler;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public void close() {
    this.mHandlerThread.quitSafely();
  }
  
  public void onCaptureStarted(CaptureResultExtras paramCaptureResultExtras, long paramLong) {
    Message message = getHandler().obtainMessage(2, (int)(paramLong & 0xFFFFFFFFL), (int)(0xFFFFFFFFL & paramLong >> 32L), paramCaptureResultExtras);
    getHandler().sendMessage(message);
  }
  
  public void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) {
    Message message = getHandler().obtainMessage(0, paramInt, 0, paramCaptureResultExtras);
    getHandler().sendMessage(message);
  }
  
  public void onDeviceIdle() {
    Message message = getHandler().obtainMessage(1);
    getHandler().sendMessage(message);
  }
  
  public void onPrepared(int paramInt) {
    Message message = getHandler().obtainMessage(4, paramInt, 0);
    getHandler().sendMessage(message);
  }
  
  public void onRepeatingRequestError(long paramLong, int paramInt) {
    Message message = getHandler().obtainMessage(5, new Object[] { Long.valueOf(paramLong), Integer.valueOf(paramInt) });
    getHandler().sendMessage(message);
  }
  
  public void onRequestQueueEmpty() {
    Message message = getHandler().obtainMessage(6, 0, 0);
    getHandler().sendMessage(message);
  }
  
  public void onResultReceived(CameraMetadataNative paramCameraMetadataNative, CaptureResultExtras paramCaptureResultExtras, PhysicalCaptureResultInfo[] paramArrayOfPhysicalCaptureResultInfo) {
    Message message = getHandler().obtainMessage(3, new Object[] { paramCameraMetadataNative, paramCaptureResultExtras });
    getHandler().sendMessage(message);
  }
  
  private class CallbackHandler extends Handler {
    public CallbackHandler(Looper param2Looper) {
      super(param2Looper);
    }
    
    public void handleMessage(Message param2Message) {
      try {
        IllegalArgumentException illegalArgumentException;
        Object[] arrayOfObject1;
        CaptureResultExtras captureResultExtras1;
        StringBuilder stringBuilder;
        Object[] arrayOfObject2;
        CameraMetadataNative cameraMetadataNative;
        long l1;
        long l2;
        switch (param2Message.what) {
          default:
            illegalArgumentException = new IllegalArgumentException();
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Unknown callback message ");
            stringBuilder.append(param2Message.what);
            this(stringBuilder.toString());
            throw illegalArgumentException;
          case 6:
            CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onRequestQueueEmpty();
            return;
          case 5:
            arrayOfObject2 = (Object[])param2Message.obj;
            l1 = ((Long)arrayOfObject2[0]).longValue();
            i = ((Integer)arrayOfObject2[1]).intValue();
            CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onRepeatingRequestError(l1, i);
            return;
          case 4:
            i = param2Message.arg1;
            CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onPrepared(i);
            return;
          case 3:
            arrayOfObject1 = (Object[])param2Message.obj;
            cameraMetadataNative = (CameraMetadataNative)arrayOfObject1[0];
            captureResultExtras1 = (CaptureResultExtras)arrayOfObject1[1];
            CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onResultReceived(cameraMetadataNative, captureResultExtras1, new PhysicalCaptureResultInfo[0]);
            return;
          case 2:
            l2 = param2Message.arg2;
            l1 = param2Message.arg1;
            captureResultExtras2 = (CaptureResultExtras)param2Message.obj;
            CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onCaptureStarted(captureResultExtras2, (l2 & 0xFFFFFFFFL) << 32L | 0xFFFFFFFFL & l1);
            return;
          case 1:
            CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onDeviceIdle();
            return;
          case 0:
            break;
        } 
        int i = param2Message.arg1;
        CaptureResultExtras captureResultExtras2 = (CaptureResultExtras)param2Message.obj;
        CameraDeviceUserShim.CameraCallbackThread.this.mCallbacks.onDeviceError(i, captureResultExtras2);
        return;
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Received remote exception during camera callback ");
        stringBuilder.append(param2Message.what);
        throw new IllegalStateException(stringBuilder.toString(), remoteException);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CameraDeviceUserShim$CameraCallbackThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */