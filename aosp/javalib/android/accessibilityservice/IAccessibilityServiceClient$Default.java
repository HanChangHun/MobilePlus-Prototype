package android.accessibilityservice;

import android.graphics.Region;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class Default implements IAccessibilityServiceClient {
  public IBinder asBinder() {
    return null;
  }
  
  public void clearAccessibilityCache() throws RemoteException {}
  
  public void init(IAccessibilityServiceConnection paramIAccessibilityServiceConnection, int paramInt, IBinder paramIBinder) throws RemoteException {}
  
  public void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) throws RemoteException {}
  
  public void onAccessibilityButtonClicked(int paramInt) throws RemoteException {}
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent, boolean paramBoolean) throws RemoteException {}
  
  public void onFingerprintCapturingGesturesChanged(boolean paramBoolean) throws RemoteException {}
  
  public void onFingerprintGesture(int paramInt) throws RemoteException {}
  
  public void onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) throws RemoteException {}
  
  public void onInterrupt() throws RemoteException {}
  
  public void onKeyEvent(KeyEvent paramKeyEvent, int paramInt) throws RemoteException {}
  
  public void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) throws RemoteException {}
  
  public void onPerformGestureResult(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onSoftKeyboardShowModeChanged(int paramInt) throws RemoteException {}
  
  public void onSystemActionsChanged() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceClient$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */