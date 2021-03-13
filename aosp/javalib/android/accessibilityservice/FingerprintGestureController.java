package android.accessibilityservice;

import android.os.Handler;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;

public final class FingerprintGestureController {
  public static final int FINGERPRINT_GESTURE_SWIPE_DOWN = 8;
  
  public static final int FINGERPRINT_GESTURE_SWIPE_LEFT = 2;
  
  public static final int FINGERPRINT_GESTURE_SWIPE_RIGHT = 1;
  
  public static final int FINGERPRINT_GESTURE_SWIPE_UP = 4;
  
  private static final String LOG_TAG = "FingerprintGestureController";
  
  private final IAccessibilityServiceConnection mAccessibilityServiceConnection;
  
  private final ArrayMap<FingerprintGestureCallback, Handler> mCallbackHandlerMap = new ArrayMap(1);
  
  private final Object mLock = new Object();
  
  public FingerprintGestureController(IAccessibilityServiceConnection paramIAccessibilityServiceConnection) {
    this.mAccessibilityServiceConnection = paramIAccessibilityServiceConnection;
  }
  
  public boolean isGestureDetectionAvailable() {
    try {
      return this.mAccessibilityServiceConnection.isFingerprintGestureDetectionAvailable();
    } catch (RemoteException remoteException) {
      Log.w("FingerprintGestureController", "Failed to check if fingerprint gestures are active", (Throwable)remoteException);
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public void onGesture(int paramInt) {
    synchronized (this.mLock) {
      ArrayMap arrayMap = new ArrayMap();
      this(this.mCallbackHandlerMap);
      int i = arrayMap.size();
      for (byte b = 0; b < i; b++) {
        null = arrayMap.keyAt(b);
        Handler handler = (Handler)arrayMap.valueAt(b);
        if (handler != null) {
          handler.post(new _$$Lambda$FingerprintGestureController$BQjrQQom4K3C98FNiI0fi7SvHfY((FingerprintGestureCallback)null, paramInt));
        } else {
          null.onGestureDetected(paramInt);
        } 
      } 
      return;
    } 
  }
  
  public void onGestureDetectionActiveChanged(boolean paramBoolean) {
    synchronized (this.mLock) {
      ArrayMap arrayMap = new ArrayMap();
      this(this.mCallbackHandlerMap);
      int i = arrayMap.size();
      for (byte b = 0; b < i; b++) {
        FingerprintGestureCallback fingerprintGestureCallback = (FingerprintGestureCallback)arrayMap.keyAt(b);
        null = arrayMap.valueAt(b);
        if (null != null) {
          null.post(new _$$Lambda$FingerprintGestureController$M_ZApqp96G6ZF2WdWrGDJ8Qsfck(fingerprintGestureCallback, paramBoolean));
        } else {
          fingerprintGestureCallback.onGestureDetectionAvailabilityChanged(paramBoolean);
        } 
      } 
      return;
    } 
  }
  
  public void registerFingerprintGestureCallback(FingerprintGestureCallback paramFingerprintGestureCallback, Handler paramHandler) {
    synchronized (this.mLock) {
      this.mCallbackHandlerMap.put(paramFingerprintGestureCallback, paramHandler);
      return;
    } 
  }
  
  public void unregisterFingerprintGestureCallback(FingerprintGestureCallback paramFingerprintGestureCallback) {
    synchronized (this.mLock) {
      this.mCallbackHandlerMap.remove(paramFingerprintGestureCallback);
      return;
    } 
  }
  
  public static abstract class FingerprintGestureCallback {
    public void onGestureDetected(int param1Int) {}
    
    public void onGestureDetectionAvailabilityChanged(boolean param1Boolean) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/FingerprintGestureController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */