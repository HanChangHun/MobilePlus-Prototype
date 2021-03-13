package android.accessibilityservice;

import android.graphics.Region;
import android.os.Handler;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.view.accessibility.AccessibilityInteractionClient;

public final class MagnificationController {
  private final int mDisplayId;
  
  private ArrayMap<OnMagnificationChangedListener, Handler> mListeners;
  
  private final Object mLock;
  
  private final AccessibilityService mService;
  
  MagnificationController(AccessibilityService paramAccessibilityService, Object paramObject, int paramInt) {
    this.mService = paramAccessibilityService;
    this.mLock = paramObject;
    this.mDisplayId = paramInt;
  }
  
  private void setMagnificationCallbackEnabled(boolean paramBoolean) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        iAccessibilityServiceConnection.setMagnificationCallbackEnabled(this.mDisplayId, paramBoolean);
      } catch (RemoteException remoteException) {
        throw new RuntimeException(remoteException);
      }  
  }
  
  public void addListener(OnMagnificationChangedListener paramOnMagnificationChangedListener) {
    addListener(paramOnMagnificationChangedListener, null);
  }
  
  public void addListener(OnMagnificationChangedListener paramOnMagnificationChangedListener, Handler paramHandler) {
    synchronized (this.mLock) {
      if (this.mListeners == null) {
        ArrayMap<OnMagnificationChangedListener, Handler> arrayMap = new ArrayMap();
        this();
        this.mListeners = arrayMap;
      } 
      boolean bool = this.mListeners.isEmpty();
      this.mListeners.put(paramOnMagnificationChangedListener, paramHandler);
      if (bool)
        setMagnificationCallbackEnabled(true); 
      return;
    } 
  }
  
  void dispatchMagnificationChanged(final Region region, final float scale, final float centerX, final float centerY) {
    synchronized (this.mLock) {
      if (this.mListeners == null || this.mListeners.isEmpty()) {
        Slog.d("AccessibilityService", "Received magnification changed callback with no listeners registered!");
        setMagnificationCallbackEnabled(false);
        return;
      } 
      ArrayMap arrayMap = new ArrayMap();
      this(this.mListeners);
      int i = arrayMap.size();
      for (byte b = 0; b < i; b++) {
        null = arrayMap.keyAt(b);
        Handler handler = (Handler)arrayMap.valueAt(b);
        if (handler != null) {
          handler.post(new Runnable() {
                public void run() {
                  listener.onMagnificationChanged(AccessibilityService.MagnificationController.this, region, scale, centerX, centerY);
                }
              });
        } else {
          null.onMagnificationChanged(this, region, scale, centerX, centerY);
        } 
      } 
      return;
    } 
  }
  
  public float getCenterX() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getMagnificationCenterX(this.mDisplayId);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to obtain center X", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return 0.0F;
  }
  
  public float getCenterY() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getMagnificationCenterY(this.mDisplayId);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to obtain center Y", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return 0.0F;
  }
  
  public Region getMagnificationRegion() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getMagnificationRegion(this.mDisplayId);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to obtain magnified region", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return Region.obtain();
  }
  
  public float getScale() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getMagnificationScale(this.mDisplayId);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to obtain scale", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return 1.0F;
  }
  
  void onServiceConnectedLocked() {
    ArrayMap<OnMagnificationChangedListener, Handler> arrayMap = this.mListeners;
    if (arrayMap != null && !arrayMap.isEmpty())
      setMagnificationCallbackEnabled(true); 
  }
  
  public boolean removeListener(OnMagnificationChangedListener paramOnMagnificationChangedListener) {
    if (this.mListeners == null)
      return false; 
    synchronized (this.mLock) {
      boolean bool;
      int i = this.mListeners.indexOfKey(paramOnMagnificationChangedListener);
      if (i >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool)
        this.mListeners.removeAt(i); 
      if (bool && this.mListeners.isEmpty())
        setMagnificationCallbackEnabled(false); 
      return bool;
    } 
  }
  
  public boolean reset(boolean paramBoolean) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.resetMagnification(this.mDisplayId, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to reset", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean setCenter(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.setMagnificationScaleAndCenter(this.mDisplayId, Float.NaN, paramFloat1, paramFloat2, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to set center", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean setScale(float paramFloat, boolean paramBoolean) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.setMagnificationScaleAndCenter(this.mDisplayId, paramFloat, Float.NaN, Float.NaN, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to set scale", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public static interface OnMagnificationChangedListener {
    void onMagnificationChanged(AccessibilityService.MagnificationController param2MagnificationController, Region param2Region, float param2Float1, float param2Float2, float param2Float3);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$MagnificationController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */