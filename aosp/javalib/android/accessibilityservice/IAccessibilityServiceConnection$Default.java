package android.accessibilityservice;

import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import java.util.List;

public class Default implements IAccessibilityServiceConnection {
  public IBinder asBinder() {
    return null;
  }
  
  public void disableSelf() throws RemoteException {}
  
  public void dispatchGesture(int paramInt1, ParceledListSlice paramParceledListSlice, int paramInt2) throws RemoteException {}
  
  public String[] findAccessibilityNodeInfoByAccessibilityId(int paramInt1, long paramLong1, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, int paramInt3, long paramLong2, Bundle paramBundle) throws RemoteException {
    return null;
  }
  
  public String[] findAccessibilityNodeInfosByText(int paramInt1, long paramLong1, String paramString, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    return null;
  }
  
  public String[] findAccessibilityNodeInfosByViewId(int paramInt1, long paramLong1, String paramString, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    return null;
  }
  
  public String[] findFocus(int paramInt1, long paramLong1, int paramInt2, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    return null;
  }
  
  public String[] focusSearch(int paramInt1, long paramLong1, int paramInt2, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    return null;
  }
  
  public float getMagnificationCenterX(int paramInt) throws RemoteException {
    return 0.0F;
  }
  
  public float getMagnificationCenterY(int paramInt) throws RemoteException {
    return 0.0F;
  }
  
  public Region getMagnificationRegion(int paramInt) throws RemoteException {
    return null;
  }
  
  public float getMagnificationScale(int paramInt) throws RemoteException {
    return 0.0F;
  }
  
  public IBinder getOverlayWindowToken(int paramInt) throws RemoteException {
    return null;
  }
  
  public AccessibilityServiceInfo getServiceInfo() throws RemoteException {
    return null;
  }
  
  public int getSoftKeyboardShowMode() throws RemoteException {
    return 0;
  }
  
  public List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException {
    return null;
  }
  
  public AccessibilityWindowInfo getWindow(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getWindowIdForLeashToken(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException {
    return null;
  }
  
  public boolean isAccessibilityButtonAvailable() throws RemoteException {
    return false;
  }
  
  public boolean isFingerprintGestureDetectionAvailable() throws RemoteException {
    return false;
  }
  
  public boolean performAccessibilityAction(int paramInt1, long paramLong1, int paramInt2, Bundle paramBundle, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    return false;
  }
  
  public boolean performGlobalAction(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean resetMagnification(int paramInt, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void sendGesture(int paramInt, ParceledListSlice paramParceledListSlice) throws RemoteException {}
  
  public void setGestureDetectionPassthroughRegion(int paramInt, Region paramRegion) throws RemoteException {}
  
  public void setMagnificationCallbackEnabled(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public boolean setMagnificationScaleAndCenter(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void setOnKeyEventResult(boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void setServiceInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) throws RemoteException {}
  
  public void setSoftKeyboardCallbackEnabled(boolean paramBoolean) throws RemoteException {}
  
  public boolean setSoftKeyboardShowMode(int paramInt) throws RemoteException {
    return false;
  }
  
  public void setTouchExplorationPassthroughRegion(int paramInt, Region paramRegion) throws RemoteException {}
  
  public boolean switchToInputMethod(String paramString) throws RemoteException {
    return false;
  }
  
  public void takeScreenshot(int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceConnection$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */