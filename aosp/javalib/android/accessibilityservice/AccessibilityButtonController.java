package android.accessibilityservice;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Slog;
import java.util.Objects;

public final class AccessibilityButtonController {
  private static final String LOG_TAG = "A11yButtonController";
  
  private ArrayMap<AccessibilityButtonCallback, Handler> mCallbacks;
  
  private final Object mLock;
  
  private final IAccessibilityServiceConnection mServiceConnection;
  
  AccessibilityButtonController(IAccessibilityServiceConnection paramIAccessibilityServiceConnection) {
    this.mServiceConnection = paramIAccessibilityServiceConnection;
    this.mLock = new Object();
  }
  
  void dispatchAccessibilityButtonAvailabilityChanged(boolean paramBoolean) {
    synchronized (this.mLock) {
      if (this.mCallbacks == null || this.mCallbacks.isEmpty()) {
        Slog.w("A11yButtonController", "Received accessibility button availability change with no callbacks!");
        return;
      } 
      ArrayMap arrayMap = new ArrayMap();
      this(this.mCallbacks);
      byte b = 0;
      int i = arrayMap.size();
      while (b < i) {
        null = arrayMap.keyAt(b);
        ((Handler)arrayMap.valueAt(b)).post(new _$$Lambda$AccessibilityButtonController$RskKrfcSyUz7I9Sqaziy1P990ZM(this, (AccessibilityButtonCallback)null, paramBoolean));
        b++;
      } 
      return;
    } 
  }
  
  void dispatchAccessibilityButtonClicked() {
    synchronized (this.mLock) {
      if (this.mCallbacks == null || this.mCallbacks.isEmpty()) {
        Slog.w("A11yButtonController", "Received accessibility button click with no callbacks!");
        return;
      } 
      ArrayMap arrayMap = new ArrayMap();
      this(this.mCallbacks);
      byte b = 0;
      int i = arrayMap.size();
      while (b < i) {
        null = arrayMap.keyAt(b);
        ((Handler)arrayMap.valueAt(b)).post(new _$$Lambda$AccessibilityButtonController$b_UAM9QJWcH4KQOC_odiN0t_boU(this, (AccessibilityButtonCallback)null));
        b++;
      } 
      return;
    } 
  }
  
  public boolean isAccessibilityButtonAvailable() {
    IAccessibilityServiceConnection iAccessibilityServiceConnection = this.mServiceConnection;
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.isAccessibilityButtonAvailable();
      } catch (RemoteException remoteException) {
        Slog.w("A11yButtonController", "Failed to get accessibility button availability.", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
        return false;
      }  
    return false;
  }
  
  public void registerAccessibilityButtonCallback(AccessibilityButtonCallback paramAccessibilityButtonCallback) {
    registerAccessibilityButtonCallback(paramAccessibilityButtonCallback, new Handler(Looper.getMainLooper()));
  }
  
  public void registerAccessibilityButtonCallback(AccessibilityButtonCallback paramAccessibilityButtonCallback, Handler paramHandler) {
    Objects.requireNonNull(paramAccessibilityButtonCallback);
    Objects.requireNonNull(paramHandler);
    synchronized (this.mLock) {
      if (this.mCallbacks == null) {
        ArrayMap<AccessibilityButtonCallback, Handler> arrayMap = new ArrayMap();
        this();
        this.mCallbacks = arrayMap;
      } 
      this.mCallbacks.put(paramAccessibilityButtonCallback, paramHandler);
      return;
    } 
  }
  
  public void unregisterAccessibilityButtonCallback(AccessibilityButtonCallback paramAccessibilityButtonCallback) {
    Objects.requireNonNull(paramAccessibilityButtonCallback);
    synchronized (this.mLock) {
      boolean bool;
      if (this.mCallbacks == null)
        return; 
      int i = this.mCallbacks.indexOfKey(paramAccessibilityButtonCallback);
      if (i >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool)
        this.mCallbacks.removeAt(i); 
      return;
    } 
  }
  
  public static abstract class AccessibilityButtonCallback {
    public void onAvailabilityChanged(AccessibilityButtonController param1AccessibilityButtonController, boolean param1Boolean) {}
    
    public void onClicked(AccessibilityButtonController param1AccessibilityButtonController) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityButtonController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */