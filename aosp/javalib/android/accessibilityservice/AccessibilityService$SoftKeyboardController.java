package android.accessibilityservice;

import android.os.Handler;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.view.accessibility.AccessibilityInteractionClient;

public final class SoftKeyboardController {
  private ArrayMap<OnShowModeChangedListener, Handler> mListeners;
  
  private final Object mLock;
  
  private final AccessibilityService mService;
  
  SoftKeyboardController(AccessibilityService paramAccessibilityService, Object paramObject) {
    this.mService = paramAccessibilityService;
    this.mLock = paramObject;
  }
  
  private void setSoftKeyboardCallbackEnabled(boolean paramBoolean) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        iAccessibilityServiceConnection.setSoftKeyboardCallbackEnabled(paramBoolean);
      } catch (RemoteException remoteException) {
        throw new RuntimeException(remoteException);
      }  
  }
  
  public void addOnShowModeChangedListener(OnShowModeChangedListener paramOnShowModeChangedListener) {
    addOnShowModeChangedListener(paramOnShowModeChangedListener, null);
  }
  
  public void addOnShowModeChangedListener(OnShowModeChangedListener paramOnShowModeChangedListener, Handler paramHandler) {
    synchronized (this.mLock) {
      if (this.mListeners == null) {
        ArrayMap<OnShowModeChangedListener, Handler> arrayMap = new ArrayMap();
        this();
        this.mListeners = arrayMap;
      } 
      boolean bool = this.mListeners.isEmpty();
      this.mListeners.put(paramOnShowModeChangedListener, paramHandler);
      if (bool)
        setSoftKeyboardCallbackEnabled(true); 
      return;
    } 
  }
  
  void dispatchSoftKeyboardShowModeChanged(final int showMode) {
    synchronized (this.mLock) {
      if (this.mListeners == null || this.mListeners.isEmpty()) {
        Slog.w("AccessibilityService", "Received soft keyboard show mode changed callback with no listeners registered!");
        setSoftKeyboardCallbackEnabled(false);
        return;
      } 
      ArrayMap arrayMap = new ArrayMap();
      this(this.mListeners);
      byte b = 0;
      int i = arrayMap.size();
      while (b < i) {
        final OnShowModeChangedListener listener = (OnShowModeChangedListener)arrayMap.keyAt(b);
        null = arrayMap.valueAt(b);
        if (null != null) {
          null.post(new Runnable() {
                public void run() {
                  listener.onShowModeChanged(AccessibilityService.SoftKeyboardController.this, showMode);
                }
              });
        } else {
          onShowModeChangedListener.onShowModeChanged(this, showMode);
        } 
        b++;
      } 
      return;
    } 
  }
  
  public int getShowMode() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getSoftKeyboardShowMode();
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to set soft keyboard behavior", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return 0;
  }
  
  void onServiceConnected() {
    synchronized (this.mLock) {
      if (this.mListeners != null && !this.mListeners.isEmpty())
        setSoftKeyboardCallbackEnabled(true); 
      return;
    } 
  }
  
  public boolean removeOnShowModeChangedListener(OnShowModeChangedListener paramOnShowModeChangedListener) {
    if (this.mListeners == null)
      return false; 
    synchronized (this.mLock) {
      boolean bool;
      int i = this.mListeners.indexOfKey(paramOnShowModeChangedListener);
      if (i >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool)
        this.mListeners.removeAt(i); 
      if (bool && this.mListeners.isEmpty())
        setSoftKeyboardCallbackEnabled(false); 
      return bool;
    } 
  }
  
  public boolean setShowMode(int paramInt) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.setSoftKeyboardShowMode(paramInt);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to set soft keyboard behavior", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean switchToInputMethod(String paramString) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(AccessibilityService.access$000(this.mService));
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.switchToInputMethod(paramString);
      } catch (RemoteException remoteException) {
        throw new RuntimeException(remoteException);
      }  
    return false;
  }
  
  public static interface OnShowModeChangedListener {
    void onShowModeChanged(AccessibilityService.SoftKeyboardController param2SoftKeyboardController, int param2Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$SoftKeyboardController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */