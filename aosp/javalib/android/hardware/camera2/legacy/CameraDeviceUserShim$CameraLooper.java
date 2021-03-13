package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.os.ConditionVariable;
import android.os.Looper;
import android.os.ServiceSpecificException;
import android.util.Log;

class CameraLooper implements Runnable, AutoCloseable {
  private final Camera mCamera = Camera.openUninitialized();
  
  private final int mCameraId;
  
  private volatile int mInitErrors;
  
  private Looper mLooper;
  
  private final ConditionVariable mStartDone = new ConditionVariable();
  
  private final Thread mThread;
  
  public CameraLooper(int paramInt) {
    this.mCameraId = paramInt;
    Thread thread = new Thread(this, "LegacyCameraLooper");
    this.mThread = thread;
    thread.start();
  }
  
  public void close() {
    Looper looper = this.mLooper;
    if (looper == null)
      return; 
    looper.quitSafely();
    try {
      this.mThread.join();
      this.mLooper = null;
      return;
    } catch (InterruptedException interruptedException) {
      throw new AssertionError(interruptedException);
    } 
  }
  
  public Camera getCamera() {
    return this.mCamera;
  }
  
  public void run() {
    Looper.prepare();
    this.mLooper = Looper.myLooper();
    this.mInitErrors = this.mCamera.cameraInitUnspecified(this.mCameraId);
    this.mStartDone.open();
    Looper.loop();
  }
  
  public int waitForOpen(int paramInt) {
    if (this.mStartDone.block(paramInt))
      return this.mInitErrors; 
    Log.e("CameraDeviceUserShim", "waitForOpen - Camera failed to open after timeout of 5000 ms");
    try {
      this.mCamera.release();
    } catch (RuntimeException runtimeException) {
      Log.e("CameraDeviceUserShim", "connectBinderShim - Failed to release camera after timeout ", runtimeException);
    } 
    throw new ServiceSpecificException(10);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CameraDeviceUserShim$CameraLooper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */