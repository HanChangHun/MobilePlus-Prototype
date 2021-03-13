package android.accessibilityservice;

import android.content.Context;
import android.graphics.Region;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityInteractionClient;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;

public class IAccessibilityServiceClientWrapper extends IAccessibilityServiceClient.Stub implements HandlerCaller.Callback {
  private static final int DO_ACCESSIBILITY_BUTTON_AVAILABILITY_CHANGED = 13;
  
  private static final int DO_ACCESSIBILITY_BUTTON_CLICKED = 12;
  
  private static final int DO_CLEAR_ACCESSIBILITY_CACHE = 5;
  
  private static final int DO_GESTURE_COMPLETE = 9;
  
  private static final int DO_INIT = 1;
  
  private static final int DO_ON_ACCESSIBILITY_EVENT = 3;
  
  private static final int DO_ON_FINGERPRINT_ACTIVE_CHANGED = 10;
  
  private static final int DO_ON_FINGERPRINT_GESTURE = 11;
  
  private static final int DO_ON_GESTURE = 4;
  
  private static final int DO_ON_INTERRUPT = 2;
  
  private static final int DO_ON_KEY_EVENT = 6;
  
  private static final int DO_ON_MAGNIFICATION_CHANGED = 7;
  
  private static final int DO_ON_SOFT_KEYBOARD_SHOW_MODE_CHANGED = 8;
  
  private static final int DO_ON_SYSTEM_ACTIONS_CHANGED = 14;
  
  private final AccessibilityService.Callbacks mCallback;
  
  private final HandlerCaller mCaller;
  
  private int mConnectionId = -1;
  
  public IAccessibilityServiceClientWrapper(Context paramContext, Looper paramLooper, AccessibilityService.Callbacks paramCallbacks) {
    this.mCallback = paramCallbacks;
    this.mCaller = new HandlerCaller(paramContext, paramLooper, this, true);
  }
  
  public void clearAccessibilityCache() {
    Message message = this.mCaller.obtainMessage(5);
    this.mCaller.sendMessage(message);
  }
  
  public void executeMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    KeyEvent keyEvent;
    AccessibilityEvent accessibilityEvent;
    int i = paramMessage.what;
    boolean bool1 = false;
    boolean bool2 = false;
    int j = 0;
    boolean bool3 = false;
    switch (i) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown message type ");
        stringBuilder.append(paramMessage.what);
        Log.w("AccessibilityService", stringBuilder.toString());
        return;
      case 14:
        if (this.mConnectionId != -1)
          this.mCallback.onSystemActionsChanged(); 
        return;
      case 13:
        if (this.mConnectionId != -1) {
          if (paramMessage.arg1 != 0)
            bool3 = true; 
          this.mCallback.onAccessibilityButtonAvailabilityChanged(bool3);
        } 
        return;
      case 12:
        if (this.mConnectionId != -1)
          this.mCallback.onAccessibilityButtonClicked(paramMessage.arg1); 
        return;
      case 11:
        if (this.mConnectionId != -1)
          this.mCallback.onFingerprintGesture(paramMessage.arg1); 
        return;
      case 10:
        if (this.mConnectionId != -1) {
          AccessibilityService.Callbacks callbacks = this.mCallback;
          bool3 = bool1;
          if (paramMessage.arg1 == 1)
            bool3 = true; 
          callbacks.onFingerprintCapturingGesturesChanged(bool3);
        } 
        return;
      case 9:
        if (this.mConnectionId != -1) {
          bool3 = bool2;
          if (paramMessage.arg2 == 1)
            bool3 = true; 
          this.mCallback.onPerformGestureResult(paramMessage.arg1, bool3);
        } 
        return;
      case 8:
        if (this.mConnectionId != -1) {
          j = paramMessage.arg1;
          this.mCallback.onSoftKeyboardShowModeChanged(j);
        } 
        return;
      case 7:
        if (this.mConnectionId != -1) {
          null = (SomeArgs)paramMessage.obj;
          Region region = (Region)null.arg1;
          float f1 = ((Float)null.arg2).floatValue();
          float f2 = ((Float)null.arg3).floatValue();
          float f3 = ((Float)null.arg4).floatValue();
          j = null.argi1;
          null.recycle();
          this.mCallback.onMagnificationChanged(j, region, f1, f2, f3);
        } 
        return;
      case 6:
        keyEvent = (KeyEvent)((Message)null).obj;
        try {
          AccessibilityInteractionClient.getInstance();
          IAccessibilityServiceConnection iAccessibilityServiceConnection1 = AccessibilityInteractionClient.getConnection(this.mConnectionId);
          if (iAccessibilityServiceConnection1 != null) {
            bool3 = this.mCallback.onKeyEvent(keyEvent);
            j = ((Message)null).arg1;
            try {
              iAccessibilityServiceConnection1.setOnKeyEventResult(bool3, j);
            } catch (RemoteException remoteException) {}
          } 
          return;
        } finally {
          try {
            keyEvent.recycle();
          } catch (IllegalStateException illegalStateException1) {}
        } 
      case 5:
        AccessibilityInteractionClient.getInstance().clearCache();
        return;
      case 4:
        if (this.mConnectionId != -1)
          this.mCallback.onGesture((AccessibilityGestureEvent)paramMessage.obj); 
        return;
      case 3:
        accessibilityEvent = (AccessibilityEvent)paramMessage.obj;
        if (paramMessage.arg1 != 0)
          j = 1; 
        if (accessibilityEvent != null) {
          AccessibilityInteractionClient.getInstance().onAccessibilityEvent(accessibilityEvent);
          if (j != 0 && this.mConnectionId != -1)
            this.mCallback.onAccessibilityEvent(accessibilityEvent); 
          try {
            accessibilityEvent.recycle();
          } catch (IllegalStateException illegalStateException) {}
        } 
        return;
      case 2:
        if (this.mConnectionId != -1)
          this.mCallback.onInterrupt(); 
        return;
      case 1:
        break;
    } 
    this.mConnectionId = ((Message)illegalStateException).arg1;
    SomeArgs someArgs = (SomeArgs)((Message)illegalStateException).obj;
    IAccessibilityServiceConnection iAccessibilityServiceConnection = (IAccessibilityServiceConnection)someArgs.arg1;
    IBinder iBinder = (IBinder)someArgs.arg2;
    someArgs.recycle();
    if (iAccessibilityServiceConnection != null) {
      AccessibilityInteractionClient.getInstance();
      AccessibilityInteractionClient.addConnection(this.mConnectionId, iAccessibilityServiceConnection);
      this.mCallback.init(this.mConnectionId, iBinder);
      this.mCallback.onServiceConnected();
    } else {
      AccessibilityInteractionClient.getInstance();
      AccessibilityInteractionClient.removeConnection(this.mConnectionId);
      this.mConnectionId = -1;
      AccessibilityInteractionClient.getInstance().clearCache();
      this.mCallback.init(-1, null);
    } 
  }
  
  public void init(IAccessibilityServiceConnection paramIAccessibilityServiceConnection, int paramInt, IBinder paramIBinder) {
    Message message = this.mCaller.obtainMessageIOO(1, paramInt, paramIAccessibilityServiceConnection, paramIBinder);
    this.mCaller.sendMessage(message);
  }
  
  public void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) {
    HandlerCaller handlerCaller = this.mCaller;
    Message message = handlerCaller.obtainMessageI(13, paramBoolean);
    this.mCaller.sendMessage(message);
  }
  
  public void onAccessibilityButtonClicked(int paramInt) {
    Message message = this.mCaller.obtainMessageI(12, paramInt);
    this.mCaller.sendMessage(message);
  }
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent, boolean paramBoolean) {
    Message message = this.mCaller.obtainMessageBO(3, paramBoolean, paramAccessibilityEvent);
    this.mCaller.sendMessage(message);
  }
  
  public void onFingerprintCapturingGesturesChanged(boolean paramBoolean) {
    HandlerCaller handlerCaller = this.mCaller;
    handlerCaller.sendMessage(handlerCaller.obtainMessageI(10, paramBoolean));
  }
  
  public void onFingerprintGesture(int paramInt) {
    HandlerCaller handlerCaller = this.mCaller;
    handlerCaller.sendMessage(handlerCaller.obtainMessageI(11, paramInt));
  }
  
  public void onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) {
    Message message = this.mCaller.obtainMessageO(4, paramAccessibilityGestureEvent);
    this.mCaller.sendMessage(message);
  }
  
  public void onInterrupt() {
    Message message = this.mCaller.obtainMessage(2);
    this.mCaller.sendMessage(message);
  }
  
  public void onKeyEvent(KeyEvent paramKeyEvent, int paramInt) {
    Message message = this.mCaller.obtainMessageIO(6, paramInt, paramKeyEvent);
    this.mCaller.sendMessage(message);
  }
  
  public void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) {
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = paramRegion;
    someArgs.arg2 = Float.valueOf(paramFloat1);
    someArgs.arg3 = Float.valueOf(paramFloat2);
    someArgs.arg4 = Float.valueOf(paramFloat3);
    someArgs.argi1 = paramInt;
    Message message = this.mCaller.obtainMessageO(7, someArgs);
    this.mCaller.sendMessage(message);
  }
  
  public void onPerformGestureResult(int paramInt, boolean paramBoolean) {
    HandlerCaller handlerCaller = this.mCaller;
    Message message = handlerCaller.obtainMessageII(9, paramInt, paramBoolean);
    this.mCaller.sendMessage(message);
  }
  
  public void onSoftKeyboardShowModeChanged(int paramInt) {
    Message message = this.mCaller.obtainMessageI(8, paramInt);
    this.mCaller.sendMessage(message);
  }
  
  public void onSystemActionsChanged() {
    HandlerCaller handlerCaller = this.mCaller;
    handlerCaller.sendMessage(handlerCaller.obtainMessage(14));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService$IAccessibilityServiceClientWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */