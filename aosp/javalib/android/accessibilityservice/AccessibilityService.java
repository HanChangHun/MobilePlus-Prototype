package android.accessibilityservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.graphics.ColorSpace;
import android.graphics.ParcelableColorSpace;
import android.graphics.Region;
import android.hardware.HardwareBuffer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class AccessibilityService extends Service {
  public static final int ACCESSIBILITY_TAKE_SCREENSHOT_REQUEST_INTERVAL_TIMES_MS = 1000;
  
  public static final int ERROR_TAKE_SCREENSHOT_INTERNAL_ERROR = 1;
  
  public static final int ERROR_TAKE_SCREENSHOT_INTERVAL_TIME_SHORT = 3;
  
  public static final int ERROR_TAKE_SCREENSHOT_INVALID_DISPLAY = 4;
  
  public static final int ERROR_TAKE_SCREENSHOT_NO_ACCESSIBILITY_ACCESS = 2;
  
  public static final int GESTURE_2_FINGER_DOUBLE_TAP = 20;
  
  public static final int GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD = 40;
  
  public static final int GESTURE_2_FINGER_SINGLE_TAP = 19;
  
  public static final int GESTURE_2_FINGER_SWIPE_DOWN = 26;
  
  public static final int GESTURE_2_FINGER_SWIPE_LEFT = 27;
  
  public static final int GESTURE_2_FINGER_SWIPE_RIGHT = 28;
  
  public static final int GESTURE_2_FINGER_SWIPE_UP = 25;
  
  public static final int GESTURE_2_FINGER_TRIPLE_TAP = 21;
  
  public static final int GESTURE_3_FINGER_DOUBLE_TAP = 23;
  
  public static final int GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD = 41;
  
  public static final int GESTURE_3_FINGER_SINGLE_TAP = 22;
  
  public static final int GESTURE_3_FINGER_SWIPE_DOWN = 30;
  
  public static final int GESTURE_3_FINGER_SWIPE_LEFT = 31;
  
  public static final int GESTURE_3_FINGER_SWIPE_RIGHT = 32;
  
  public static final int GESTURE_3_FINGER_SWIPE_UP = 29;
  
  public static final int GESTURE_3_FINGER_TRIPLE_TAP = 24;
  
  public static final int GESTURE_4_FINGER_DOUBLE_TAP = 38;
  
  public static final int GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD = 42;
  
  public static final int GESTURE_4_FINGER_SINGLE_TAP = 37;
  
  public static final int GESTURE_4_FINGER_SWIPE_DOWN = 34;
  
  public static final int GESTURE_4_FINGER_SWIPE_LEFT = 35;
  
  public static final int GESTURE_4_FINGER_SWIPE_RIGHT = 36;
  
  public static final int GESTURE_4_FINGER_SWIPE_UP = 33;
  
  public static final int GESTURE_4_FINGER_TRIPLE_TAP = 39;
  
  public static final int GESTURE_DOUBLE_TAP = 17;
  
  public static final int GESTURE_DOUBLE_TAP_AND_HOLD = 18;
  
  public static final int GESTURE_SWIPE_DOWN = 2;
  
  public static final int GESTURE_SWIPE_DOWN_AND_LEFT = 15;
  
  public static final int GESTURE_SWIPE_DOWN_AND_RIGHT = 16;
  
  public static final int GESTURE_SWIPE_DOWN_AND_UP = 8;
  
  public static final int GESTURE_SWIPE_LEFT = 3;
  
  public static final int GESTURE_SWIPE_LEFT_AND_DOWN = 10;
  
  public static final int GESTURE_SWIPE_LEFT_AND_RIGHT = 5;
  
  public static final int GESTURE_SWIPE_LEFT_AND_UP = 9;
  
  public static final int GESTURE_SWIPE_RIGHT = 4;
  
  public static final int GESTURE_SWIPE_RIGHT_AND_DOWN = 12;
  
  public static final int GESTURE_SWIPE_RIGHT_AND_LEFT = 6;
  
  public static final int GESTURE_SWIPE_RIGHT_AND_UP = 11;
  
  public static final int GESTURE_SWIPE_UP = 1;
  
  public static final int GESTURE_SWIPE_UP_AND_DOWN = 7;
  
  public static final int GESTURE_SWIPE_UP_AND_LEFT = 13;
  
  public static final int GESTURE_SWIPE_UP_AND_RIGHT = 14;
  
  public static final int GLOBAL_ACTION_ACCESSIBILITY_ALL_APPS = 14;
  
  public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON = 11;
  
  public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON_CHOOSER = 12;
  
  public static final int GLOBAL_ACTION_ACCESSIBILITY_SHORTCUT = 13;
  
  public static final int GLOBAL_ACTION_BACK = 1;
  
  public static final int GLOBAL_ACTION_HOME = 2;
  
  public static final int GLOBAL_ACTION_KEYCODE_HEADSETHOOK = 10;
  
  public static final int GLOBAL_ACTION_LOCK_SCREEN = 8;
  
  public static final int GLOBAL_ACTION_NOTIFICATIONS = 4;
  
  public static final int GLOBAL_ACTION_POWER_DIALOG = 6;
  
  public static final int GLOBAL_ACTION_QUICK_SETTINGS = 5;
  
  public static final int GLOBAL_ACTION_RECENTS = 3;
  
  public static final int GLOBAL_ACTION_TAKE_SCREENSHOT = 9;
  
  public static final int GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN = 7;
  
  public static final String KEY_ACCESSIBILITY_SCREENSHOT_COLORSPACE = "screenshot_colorSpace";
  
  public static final String KEY_ACCESSIBILITY_SCREENSHOT_HARDWAREBUFFER = "screenshot_hardwareBuffer";
  
  public static final String KEY_ACCESSIBILITY_SCREENSHOT_STATUS = "screenshot_status";
  
  public static final String KEY_ACCESSIBILITY_SCREENSHOT_TIMESTAMP = "screenshot_timestamp";
  
  private static final String LOG_TAG = "AccessibilityService";
  
  public static final String SERVICE_INTERFACE = "android.accessibilityservice.AccessibilityService";
  
  public static final String SERVICE_META_DATA = "android.accessibilityservice";
  
  public static final int SHOW_MODE_AUTO = 0;
  
  public static final int SHOW_MODE_HARD_KEYBOARD_ORIGINAL_VALUE = 536870912;
  
  public static final int SHOW_MODE_HARD_KEYBOARD_OVERRIDDEN = 1073741824;
  
  public static final int SHOW_MODE_HIDDEN = 1;
  
  public static final int SHOW_MODE_IGNORE_HARD_KEYBOARD = 2;
  
  public static final int SHOW_MODE_MASK = 3;
  
  public static final int TAKE_SCREENSHOT_SUCCESS = 0;
  
  private final SparseArray<AccessibilityButtonController> mAccessibilityButtonControllers = new SparseArray(0);
  
  private int mConnectionId = -1;
  
  private FingerprintGestureController mFingerprintGestureController;
  
  private SparseArray<GestureResultCallbackInfo> mGestureStatusCallbackInfos;
  
  private int mGestureStatusCallbackSequence;
  
  private AccessibilityServiceInfo mInfo;
  
  private final Object mLock = new Object();
  
  private final SparseArray<MagnificationController> mMagnificationControllers = new SparseArray(0);
  
  private SoftKeyboardController mSoftKeyboardController;
  
  private WindowManager mWindowManager;
  
  private IBinder mWindowToken;
  
  private void dispatchServiceConnected() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLock : Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: iconst_0
    //   8: istore_2
    //   9: iload_2
    //   10: aload_0
    //   11: getfield mMagnificationControllers : Landroid/util/SparseArray;
    //   14: invokevirtual size : ()I
    //   17: if_icmpge -> 40
    //   20: aload_0
    //   21: getfield mMagnificationControllers : Landroid/util/SparseArray;
    //   24: iload_2
    //   25: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   28: checkcast android/accessibilityservice/AccessibilityService$MagnificationController
    //   31: invokevirtual onServiceConnectedLocked : ()V
    //   34: iinc #2, 1
    //   37: goto -> 9
    //   40: aload_1
    //   41: monitorexit
    //   42: aload_0
    //   43: getfield mSoftKeyboardController : Landroid/accessibilityservice/AccessibilityService$SoftKeyboardController;
    //   46: astore_1
    //   47: aload_1
    //   48: ifnull -> 55
    //   51: aload_1
    //   52: invokevirtual onServiceConnected : ()V
    //   55: aload_0
    //   56: invokevirtual onServiceConnected : ()V
    //   59: return
    //   60: astore_3
    //   61: aload_1
    //   62: monitorexit
    //   63: aload_3
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   9	34	60	finally
    //   40	42	60	finally
    //   61	63	60	finally
  }
  
  private void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) {
    getAccessibilityButtonController().dispatchAccessibilityButtonAvailabilityChanged(paramBoolean);
  }
  
  private void onAccessibilityButtonClicked(int paramInt) {
    getAccessibilityButtonController(paramInt).dispatchAccessibilityButtonClicked();
  }
  
  private void onFingerprintCapturingGesturesChanged(boolean paramBoolean) {
    getFingerprintGestureController().onGestureDetectionActiveChanged(paramBoolean);
  }
  
  private void onFingerprintGesture(int paramInt) {
    getFingerprintGestureController().onGesture(paramInt);
  }
  
  private void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) {
    synchronized (this.mLock) {
      MagnificationController magnificationController = (MagnificationController)this.mMagnificationControllers.get(paramInt);
      if (magnificationController != null)
        magnificationController.dispatchMagnificationChanged(paramRegion, paramFloat1, paramFloat2, paramFloat3); 
      return;
    } 
  }
  
  private void onSoftKeyboardShowModeChanged(int paramInt) {
    SoftKeyboardController softKeyboardController = this.mSoftKeyboardController;
    if (softKeyboardController != null)
      softKeyboardController.dispatchSoftKeyboardShowModeChanged(paramInt); 
  }
  
  private void sendScreenshotFailure(int paramInt, Executor paramExecutor, TakeScreenshotCallback paramTakeScreenshotCallback) {
    paramExecutor.execute(new _$$Lambda$AccessibilityService$QPuf76fmbA3YdpMRuW3aotBDLhc(paramTakeScreenshotCallback, paramInt));
  }
  
  private void sendScreenshotSuccess(ScreenshotResult paramScreenshotResult, Executor paramExecutor, TakeScreenshotCallback paramTakeScreenshotCallback) {
    paramExecutor.execute(new _$$Lambda$AccessibilityService$Tu7BpkB1mA_cnSrnZqwCCw4inNQ(paramTakeScreenshotCallback, paramScreenshotResult));
  }
  
  private void sendServiceInfo() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    AccessibilityServiceInfo accessibilityServiceInfo = this.mInfo;
    if (accessibilityServiceInfo != null && iAccessibilityServiceConnection != null)
      try {
        iAccessibilityServiceConnection.setServiceInfo(accessibilityServiceInfo);
        this.mInfo = null;
        AccessibilityInteractionClient.getInstance().clearCache();
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Error while setting AccessibilityServiceInfo", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
  }
  
  private void setDefaultTokenInternal(Context paramContext, int paramInt) {
    WindowManagerImpl windowManagerImpl = (WindowManagerImpl)paramContext.getSystemService("window");
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    paramContext = null;
    if (iAccessibilityServiceConnection != null) {
      Object object = this.mLock;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      try {
        IBinder iBinder2 = iAccessibilityServiceConnection.getOverlayWindowToken(paramInt);
        IBinder iBinder1 = iBinder2;
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Failed to get window token", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      } finally {}
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      windowManagerImpl.setDefaultToken((IBinder)paramContext);
    } 
  }
  
  public Context createDisplayContext(Display paramDisplay) {
    Context context = super.createDisplayContext(paramDisplay);
    setDefaultTokenInternal(context, paramDisplay.getDisplayId());
    return context;
  }
  
  public final void disableSelf() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection != null)
      try {
        iAccessibilityServiceConnection.disableSelf();
      } catch (RemoteException remoteException) {
        throw new RuntimeException(remoteException);
      }  
  }
  
  public final boolean dispatchGesture(GestureDescription paramGestureDescription, GestureResultCallback paramGestureResultCallback, Handler paramHandler) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection == null)
      return false; 
    List<GestureDescription.GestureStep> list = GestureDescription.MotionEventGenerator.getGestureStepsFromGestureDescription(paramGestureDescription, 16);
    try {
      synchronized (this.mLock) {
        this.mGestureStatusCallbackSequence++;
        if (paramGestureResultCallback != null) {
          if (this.mGestureStatusCallbackInfos == null) {
            SparseArray<GestureResultCallbackInfo> sparseArray = new SparseArray();
            this();
            this.mGestureStatusCallbackInfos = sparseArray;
          } 
          GestureResultCallbackInfo gestureResultCallbackInfo = new GestureResultCallbackInfo();
          this(paramGestureDescription, paramGestureResultCallback, paramHandler);
          this.mGestureStatusCallbackInfos.put(this.mGestureStatusCallbackSequence, gestureResultCallbackInfo);
        } 
        int i = this.mGestureStatusCallbackSequence;
        ParceledListSlice parceledListSlice = new ParceledListSlice();
        this(list);
        iAccessibilityServiceConnection.dispatchGesture(i, parceledListSlice, paramGestureDescription.getDisplayId());
        return true;
      } 
    } catch (RemoteException remoteException) {
      throw new RuntimeException(remoteException);
    } 
  }
  
  public AccessibilityNodeInfo findFocus(int paramInt) {
    return AccessibilityInteractionClient.getInstance().findFocus(this.mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, paramInt);
  }
  
  public final AccessibilityButtonController getAccessibilityButtonController() {
    return getAccessibilityButtonController(0);
  }
  
  public final AccessibilityButtonController getAccessibilityButtonController(int paramInt) {
    synchronized (this.mLock) {
      AccessibilityButtonController accessibilityButtonController1 = (AccessibilityButtonController)this.mAccessibilityButtonControllers.get(paramInt);
      AccessibilityButtonController accessibilityButtonController2 = accessibilityButtonController1;
      if (accessibilityButtonController1 == null) {
        accessibilityButtonController2 = new AccessibilityButtonController();
        AccessibilityInteractionClient.getInstance();
        this(AccessibilityInteractionClient.getConnection(this.mConnectionId));
        this.mAccessibilityButtonControllers.put(paramInt, accessibilityButtonController2);
      } 
      return accessibilityButtonController2;
    } 
  }
  
  public final FingerprintGestureController getFingerprintGestureController() {
    if (this.mFingerprintGestureController == null) {
      AccessibilityInteractionClient.getInstance();
      this.mFingerprintGestureController = new FingerprintGestureController(AccessibilityInteractionClient.getConnection(this.mConnectionId));
    } 
    return this.mFingerprintGestureController;
  }
  
  public final MagnificationController getMagnificationController() {
    return getMagnificationController(0);
  }
  
  public final MagnificationController getMagnificationController(int paramInt) {
    synchronized (this.mLock) {
      MagnificationController magnificationController1 = (MagnificationController)this.mMagnificationControllers.get(paramInt);
      MagnificationController magnificationController2 = magnificationController1;
      if (magnificationController1 == null) {
        magnificationController2 = new MagnificationController();
        this(this, this.mLock, paramInt);
        this.mMagnificationControllers.put(paramInt, magnificationController2);
      } 
      return magnificationController2;
    } 
  }
  
  public AccessibilityNodeInfo getRootInActiveWindow() {
    return AccessibilityInteractionClient.getInstance().getRootInActiveWindow(this.mConnectionId);
  }
  
  public final AccessibilityServiceInfo getServiceInfo() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getServiceInfo();
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Error while getting AccessibilityServiceInfo", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public final SoftKeyboardController getSoftKeyboardController() {
    synchronized (this.mLock) {
      if (this.mSoftKeyboardController == null) {
        SoftKeyboardController softKeyboardController = new SoftKeyboardController();
        this(this, this.mLock);
        this.mSoftKeyboardController = softKeyboardController;
      } 
      return this.mSoftKeyboardController;
    } 
  }
  
  public final List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.getSystemActions();
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Error while calling getSystemActions", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return Collections.emptyList();
  }
  
  public Object getSystemService(String paramString) {
    if (getBaseContext() != null) {
      if ("window".equals(paramString)) {
        if (this.mWindowManager == null)
          this.mWindowManager = (WindowManager)getBaseContext().getSystemService(paramString); 
        return this.mWindowManager;
      } 
      return super.getSystemService(paramString);
    } 
    throw new IllegalStateException("System services not available to Activities before onCreate()");
  }
  
  public List<AccessibilityWindowInfo> getWindows() {
    return AccessibilityInteractionClient.getInstance().getWindows(this.mConnectionId);
  }
  
  public final SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
    return AccessibilityInteractionClient.getInstance().getWindowsOnAllDisplays(this.mConnectionId);
  }
  
  public abstract void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent);
  
  public final IBinder onBind(Intent paramIntent) {
    return (IBinder)new IAccessibilityServiceClientWrapper((Context)this, getMainLooper(), new Callbacks() {
          public void init(int param1Int, IBinder param1IBinder) {
            AccessibilityService.access$002(AccessibilityService.this, param1Int);
            AccessibilityService.access$202(AccessibilityService.this, param1IBinder);
            ((WindowManagerImpl)AccessibilityService.this.getSystemService("window")).setDefaultToken(param1IBinder);
          }
          
          public void onAccessibilityButtonAvailabilityChanged(boolean param1Boolean) {
            AccessibilityService.this.onAccessibilityButtonAvailabilityChanged(param1Boolean);
          }
          
          public void onAccessibilityButtonClicked(int param1Int) {
            AccessibilityService.this.onAccessibilityButtonClicked(param1Int);
          }
          
          public void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent) {
            AccessibilityService.this.onAccessibilityEvent(param1AccessibilityEvent);
          }
          
          public void onFingerprintCapturingGesturesChanged(boolean param1Boolean) {
            AccessibilityService.this.onFingerprintCapturingGesturesChanged(param1Boolean);
          }
          
          public void onFingerprintGesture(int param1Int) {
            AccessibilityService.this.onFingerprintGesture(param1Int);
          }
          
          public boolean onGesture(AccessibilityGestureEvent param1AccessibilityGestureEvent) {
            return AccessibilityService.this.onGesture(param1AccessibilityGestureEvent);
          }
          
          public void onInterrupt() {
            AccessibilityService.this.onInterrupt();
          }
          
          public boolean onKeyEvent(KeyEvent param1KeyEvent) {
            return AccessibilityService.this.onKeyEvent(param1KeyEvent);
          }
          
          public void onMagnificationChanged(int param1Int, Region param1Region, float param1Float1, float param1Float2, float param1Float3) {
            AccessibilityService.this.onMagnificationChanged(param1Int, param1Region, param1Float1, param1Float2, param1Float3);
          }
          
          public void onPerformGestureResult(int param1Int, boolean param1Boolean) {
            AccessibilityService.this.onPerformGestureResult(param1Int, param1Boolean);
          }
          
          public void onServiceConnected() {
            AccessibilityService.this.dispatchServiceConnected();
          }
          
          public void onSoftKeyboardShowModeChanged(int param1Int) {
            AccessibilityService.this.onSoftKeyboardShowModeChanged(param1Int);
          }
          
          public void onSystemActionsChanged() {
            AccessibilityService.this.onSystemActionsChanged();
          }
        });
  }
  
  @Deprecated
  protected boolean onGesture(int paramInt) {
    return false;
  }
  
  public boolean onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) {
    if (paramAccessibilityGestureEvent.getDisplayId() == 0)
      onGesture(paramAccessibilityGestureEvent.getGestureId()); 
    return false;
  }
  
  public abstract void onInterrupt();
  
  protected boolean onKeyEvent(KeyEvent paramKeyEvent) {
    return false;
  }
  
  void onPerformGestureResult(int paramInt, final boolean completedSuccessfully) {
    if (this.mGestureStatusCallbackInfos == null)
      return; 
    synchronized (this.mLock) {
      final GestureResultCallbackInfo finalCallbackInfo = (GestureResultCallbackInfo)this.mGestureStatusCallbackInfos.get(paramInt);
      this.mGestureStatusCallbackInfos.remove(paramInt);
      if (gestureResultCallbackInfo != null && gestureResultCallbackInfo.gestureDescription != null && gestureResultCallbackInfo.callback != null) {
        if (gestureResultCallbackInfo.handler != null) {
          gestureResultCallbackInfo.handler.post(new Runnable() {
                public void run() {
                  if (completedSuccessfully) {
                    finalCallbackInfo.callback.onCompleted(finalCallbackInfo.gestureDescription);
                  } else {
                    finalCallbackInfo.callback.onCancelled(finalCallbackInfo.gestureDescription);
                  } 
                }
              });
          return;
        } 
        if (completedSuccessfully) {
          gestureResultCallbackInfo.callback.onCompleted(gestureResultCallbackInfo.gestureDescription);
        } else {
          gestureResultCallbackInfo.callback.onCancelled(gestureResultCallbackInfo.gestureDescription);
        } 
      } 
      return;
    } 
  }
  
  protected void onServiceConnected() {}
  
  public void onSystemActionsChanged() {}
  
  public final boolean performGlobalAction(int paramInt) {
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection != null)
      try {
        return iAccessibilityServiceConnection.performGlobalAction(paramInt);
      } catch (RemoteException remoteException) {
        Log.w("AccessibilityService", "Error while calling performGlobalAction", (Throwable)remoteException);
        remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public void setGestureDetectionPassthroughRegion(int paramInt, Region paramRegion) {
    Preconditions.checkNotNull(paramRegion, "region cannot be null");
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection != null)
      try {
        iAccessibilityServiceConnection.setGestureDetectionPassthroughRegion(paramInt, paramRegion);
      } catch (RemoteException remoteException) {
        throw new RuntimeException(remoteException);
      }  
  }
  
  public final void setServiceInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    this.mInfo = paramAccessibilityServiceInfo;
    sendServiceInfo();
  }
  
  public void setTouchExplorationPassthroughRegion(int paramInt, Region paramRegion) {
    Preconditions.checkNotNull(paramRegion, "region cannot be null");
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection != null)
      try {
        iAccessibilityServiceConnection.setTouchExplorationPassthroughRegion(paramInt, paramRegion);
      } catch (RemoteException remoteException) {
        throw new RuntimeException(remoteException);
      }  
  }
  
  public void takeScreenshot(int paramInt, Executor paramExecutor, TakeScreenshotCallback paramTakeScreenshotCallback) {
    Preconditions.checkNotNull(paramExecutor, "executor cannot be null");
    Preconditions.checkNotNull(paramTakeScreenshotCallback, "callback cannot be null");
    AccessibilityInteractionClient.getInstance();
    IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
    if (iAccessibilityServiceConnection == null) {
      sendScreenshotFailure(1, paramExecutor, paramTakeScreenshotCallback);
      return;
    } 
    try {
      RemoteCallback remoteCallback = new RemoteCallback();
      _$$Lambda$AccessibilityService$q1syDPtLKDyMLkI7cIJLVQqmK_Y _$$Lambda$AccessibilityService$q1syDPtLKDyMLkI7cIJLVQqmK_Y = new _$$Lambda$AccessibilityService$q1syDPtLKDyMLkI7cIJLVQqmK_Y();
      this(this, paramExecutor, paramTakeScreenshotCallback);
      this(_$$Lambda$AccessibilityService$q1syDPtLKDyMLkI7cIJLVQqmK_Y);
      iAccessibilityServiceConnection.takeScreenshot(paramInt, remoteCallback);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException(remoteException);
    } 
  }
  
  public static interface Callbacks {
    void init(int param1Int, IBinder param1IBinder);
    
    void onAccessibilityButtonAvailabilityChanged(boolean param1Boolean);
    
    void onAccessibilityButtonClicked(int param1Int);
    
    void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent);
    
    void onFingerprintCapturingGesturesChanged(boolean param1Boolean);
    
    void onFingerprintGesture(int param1Int);
    
    boolean onGesture(AccessibilityGestureEvent param1AccessibilityGestureEvent);
    
    void onInterrupt();
    
    boolean onKeyEvent(KeyEvent param1KeyEvent);
    
    void onMagnificationChanged(int param1Int, Region param1Region, float param1Float1, float param1Float2, float param1Float3);
    
    void onPerformGestureResult(int param1Int, boolean param1Boolean);
    
    void onServiceConnected();
    
    void onSoftKeyboardShowModeChanged(int param1Int);
    
    void onSystemActionsChanged();
  }
  
  public static abstract class GestureResultCallback {
    public void onCancelled(GestureDescription param1GestureDescription) {}
    
    public void onCompleted(GestureDescription param1GestureDescription) {}
  }
  
  private static class GestureResultCallbackInfo {
    AccessibilityService.GestureResultCallback callback;
    
    GestureDescription gestureDescription;
    
    Handler handler;
    
    GestureResultCallbackInfo(GestureDescription param1GestureDescription, AccessibilityService.GestureResultCallback param1GestureResultCallback, Handler param1Handler) {
      this.gestureDescription = param1GestureDescription;
      this.callback = param1GestureResultCallback;
      this.handler = param1Handler;
    }
  }
  
  public static class IAccessibilityServiceClientWrapper extends IAccessibilityServiceClient.Stub implements HandlerCaller.Callback {
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
    
    public IAccessibilityServiceClientWrapper(Context param1Context, Looper param1Looper, AccessibilityService.Callbacks param1Callbacks) {
      this.mCallback = param1Callbacks;
      this.mCaller = new HandlerCaller(param1Context, param1Looper, this, true);
    }
    
    public void clearAccessibilityCache() {
      Message message = this.mCaller.obtainMessage(5);
      this.mCaller.sendMessage(message);
    }
    
    public void executeMessage(Message param1Message) {
      StringBuilder stringBuilder;
      KeyEvent keyEvent;
      AccessibilityEvent accessibilityEvent;
      int i = param1Message.what;
      boolean bool1 = false;
      boolean bool2 = false;
      int j = 0;
      boolean bool3 = false;
      switch (i) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown message type ");
          stringBuilder.append(param1Message.what);
          Log.w("AccessibilityService", stringBuilder.toString());
          return;
        case 14:
          if (this.mConnectionId != -1)
            this.mCallback.onSystemActionsChanged(); 
          return;
        case 13:
          if (this.mConnectionId != -1) {
            if (param1Message.arg1 != 0)
              bool3 = true; 
            this.mCallback.onAccessibilityButtonAvailabilityChanged(bool3);
          } 
          return;
        case 12:
          if (this.mConnectionId != -1)
            this.mCallback.onAccessibilityButtonClicked(param1Message.arg1); 
          return;
        case 11:
          if (this.mConnectionId != -1)
            this.mCallback.onFingerprintGesture(param1Message.arg1); 
          return;
        case 10:
          if (this.mConnectionId != -1) {
            AccessibilityService.Callbacks callbacks = this.mCallback;
            bool3 = bool1;
            if (param1Message.arg1 == 1)
              bool3 = true; 
            callbacks.onFingerprintCapturingGesturesChanged(bool3);
          } 
          return;
        case 9:
          if (this.mConnectionId != -1) {
            bool3 = bool2;
            if (param1Message.arg2 == 1)
              bool3 = true; 
            this.mCallback.onPerformGestureResult(param1Message.arg1, bool3);
          } 
          return;
        case 8:
          if (this.mConnectionId != -1) {
            j = param1Message.arg1;
            this.mCallback.onSoftKeyboardShowModeChanged(j);
          } 
          return;
        case 7:
          if (this.mConnectionId != -1) {
            null = (SomeArgs)param1Message.obj;
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
            this.mCallback.onGesture((AccessibilityGestureEvent)param1Message.obj); 
          return;
        case 3:
          accessibilityEvent = (AccessibilityEvent)param1Message.obj;
          if (param1Message.arg1 != 0)
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
    
    public void init(IAccessibilityServiceConnection param1IAccessibilityServiceConnection, int param1Int, IBinder param1IBinder) {
      Message message = this.mCaller.obtainMessageIOO(1, param1Int, param1IAccessibilityServiceConnection, param1IBinder);
      this.mCaller.sendMessage(message);
    }
    
    public void onAccessibilityButtonAvailabilityChanged(boolean param1Boolean) {
      HandlerCaller handlerCaller = this.mCaller;
      Message message = handlerCaller.obtainMessageI(13, param1Boolean);
      this.mCaller.sendMessage(message);
    }
    
    public void onAccessibilityButtonClicked(int param1Int) {
      Message message = this.mCaller.obtainMessageI(12, param1Int);
      this.mCaller.sendMessage(message);
    }
    
    public void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent, boolean param1Boolean) {
      Message message = this.mCaller.obtainMessageBO(3, param1Boolean, param1AccessibilityEvent);
      this.mCaller.sendMessage(message);
    }
    
    public void onFingerprintCapturingGesturesChanged(boolean param1Boolean) {
      HandlerCaller handlerCaller = this.mCaller;
      handlerCaller.sendMessage(handlerCaller.obtainMessageI(10, param1Boolean));
    }
    
    public void onFingerprintGesture(int param1Int) {
      HandlerCaller handlerCaller = this.mCaller;
      handlerCaller.sendMessage(handlerCaller.obtainMessageI(11, param1Int));
    }
    
    public void onGesture(AccessibilityGestureEvent param1AccessibilityGestureEvent) {
      Message message = this.mCaller.obtainMessageO(4, param1AccessibilityGestureEvent);
      this.mCaller.sendMessage(message);
    }
    
    public void onInterrupt() {
      Message message = this.mCaller.obtainMessage(2);
      this.mCaller.sendMessage(message);
    }
    
    public void onKeyEvent(KeyEvent param1KeyEvent, int param1Int) {
      Message message = this.mCaller.obtainMessageIO(6, param1Int, param1KeyEvent);
      this.mCaller.sendMessage(message);
    }
    
    public void onMagnificationChanged(int param1Int, Region param1Region, float param1Float1, float param1Float2, float param1Float3) {
      SomeArgs someArgs = SomeArgs.obtain();
      someArgs.arg1 = param1Region;
      someArgs.arg2 = Float.valueOf(param1Float1);
      someArgs.arg3 = Float.valueOf(param1Float2);
      someArgs.arg4 = Float.valueOf(param1Float3);
      someArgs.argi1 = param1Int;
      Message message = this.mCaller.obtainMessageO(7, someArgs);
      this.mCaller.sendMessage(message);
    }
    
    public void onPerformGestureResult(int param1Int, boolean param1Boolean) {
      HandlerCaller handlerCaller = this.mCaller;
      Message message = handlerCaller.obtainMessageII(9, param1Int, param1Boolean);
      this.mCaller.sendMessage(message);
    }
    
    public void onSoftKeyboardShowModeChanged(int param1Int) {
      Message message = this.mCaller.obtainMessageI(8, param1Int);
      this.mCaller.sendMessage(message);
    }
    
    public void onSystemActionsChanged() {
      HandlerCaller handlerCaller = this.mCaller;
      handlerCaller.sendMessage(handlerCaller.obtainMessage(14));
    }
  }
  
  public static final class MagnificationController {
    private final int mDisplayId;
    
    private ArrayMap<OnMagnificationChangedListener, Handler> mListeners;
    
    private final Object mLock;
    
    private final AccessibilityService mService;
    
    MagnificationController(AccessibilityService param1AccessibilityService, Object param1Object, int param1Int) {
      this.mService = param1AccessibilityService;
      this.mLock = param1Object;
      this.mDisplayId = param1Int;
    }
    
    private void setMagnificationCallbackEnabled(boolean param1Boolean) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          iAccessibilityServiceConnection.setMagnificationCallbackEnabled(this.mDisplayId, param1Boolean);
        } catch (RemoteException remoteException) {
          throw new RuntimeException(remoteException);
        }  
    }
    
    public void addListener(OnMagnificationChangedListener param1OnMagnificationChangedListener) {
      addListener(param1OnMagnificationChangedListener, null);
    }
    
    public void addListener(OnMagnificationChangedListener param1OnMagnificationChangedListener, Handler param1Handler) {
      synchronized (this.mLock) {
        if (this.mListeners == null) {
          ArrayMap<OnMagnificationChangedListener, Handler> arrayMap = new ArrayMap();
          this();
          this.mListeners = arrayMap;
        } 
        boolean bool = this.mListeners.isEmpty();
        this.mListeners.put(param1OnMagnificationChangedListener, param1Handler);
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
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
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
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
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
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
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
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
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
    
    public boolean removeListener(OnMagnificationChangedListener param1OnMagnificationChangedListener) {
      if (this.mListeners == null)
        return false; 
      synchronized (this.mLock) {
        boolean bool;
        int i = this.mListeners.indexOfKey(param1OnMagnificationChangedListener);
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
    
    public boolean reset(boolean param1Boolean) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.resetMagnification(this.mDisplayId, param1Boolean);
        } catch (RemoteException remoteException) {
          Log.w("AccessibilityService", "Failed to reset", (Throwable)remoteException);
          remoteException.rethrowFromSystemServer();
        }  
      return false;
    }
    
    public boolean setCenter(float param1Float1, float param1Float2, boolean param1Boolean) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.setMagnificationScaleAndCenter(this.mDisplayId, Float.NaN, param1Float1, param1Float2, param1Boolean);
        } catch (RemoteException remoteException) {
          Log.w("AccessibilityService", "Failed to set center", (Throwable)remoteException);
          remoteException.rethrowFromSystemServer();
        }  
      return false;
    }
    
    public boolean setScale(float param1Float, boolean param1Boolean) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.setMagnificationScaleAndCenter(this.mDisplayId, param1Float, Float.NaN, Float.NaN, param1Boolean);
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
  
  class null implements Runnable {
    public void run() {
      listener.onMagnificationChanged(this.this$0, region, scale, centerX, centerY);
    }
  }
  
  public static interface OnMagnificationChangedListener {
    void onMagnificationChanged(AccessibilityService.MagnificationController param1MagnificationController, Region param1Region, float param1Float1, float param1Float2, float param1Float3);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ScreenshotErrorCode {}
  
  public static final class ScreenshotResult {
    private final ColorSpace mColorSpace;
    
    private final HardwareBuffer mHardwareBuffer;
    
    private final long mTimestamp;
    
    private ScreenshotResult(HardwareBuffer param1HardwareBuffer, ColorSpace param1ColorSpace, long param1Long) {
      Preconditions.checkNotNull(param1HardwareBuffer, "hardwareBuffer cannot be null");
      Preconditions.checkNotNull(param1ColorSpace, "colorSpace cannot be null");
      this.mHardwareBuffer = param1HardwareBuffer;
      this.mColorSpace = param1ColorSpace;
      this.mTimestamp = param1Long;
    }
    
    public ColorSpace getColorSpace() {
      return this.mColorSpace;
    }
    
    public HardwareBuffer getHardwareBuffer() {
      return this.mHardwareBuffer;
    }
    
    public long getTimestamp() {
      return this.mTimestamp;
    }
  }
  
  public static final class SoftKeyboardController {
    private ArrayMap<OnShowModeChangedListener, Handler> mListeners;
    
    private final Object mLock;
    
    private final AccessibilityService mService;
    
    SoftKeyboardController(AccessibilityService param1AccessibilityService, Object param1Object) {
      this.mService = param1AccessibilityService;
      this.mLock = param1Object;
    }
    
    private void setSoftKeyboardCallbackEnabled(boolean param1Boolean) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          iAccessibilityServiceConnection.setSoftKeyboardCallbackEnabled(param1Boolean);
        } catch (RemoteException remoteException) {
          throw new RuntimeException(remoteException);
        }  
    }
    
    public void addOnShowModeChangedListener(OnShowModeChangedListener param1OnShowModeChangedListener) {
      addOnShowModeChangedListener(param1OnShowModeChangedListener, null);
    }
    
    public void addOnShowModeChangedListener(OnShowModeChangedListener param1OnShowModeChangedListener, Handler param1Handler) {
      synchronized (this.mLock) {
        if (this.mListeners == null) {
          ArrayMap<OnShowModeChangedListener, Handler> arrayMap = new ArrayMap();
          this();
          this.mListeners = arrayMap;
        } 
        boolean bool = this.mListeners.isEmpty();
        this.mListeners.put(param1OnShowModeChangedListener, param1Handler);
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
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
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
    
    public boolean removeOnShowModeChangedListener(OnShowModeChangedListener param1OnShowModeChangedListener) {
      if (this.mListeners == null)
        return false; 
      synchronized (this.mLock) {
        boolean bool;
        int i = this.mListeners.indexOfKey(param1OnShowModeChangedListener);
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
    
    public boolean setShowMode(int param1Int) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.setSoftKeyboardShowMode(param1Int);
        } catch (RemoteException remoteException) {
          Log.w("AccessibilityService", "Failed to set soft keyboard behavior", (Throwable)remoteException);
          remoteException.rethrowFromSystemServer();
        }  
      return false;
    }
    
    public boolean switchToInputMethod(String param1String) {
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.switchToInputMethod(param1String);
        } catch (RemoteException remoteException) {
          throw new RuntimeException(remoteException);
        }  
      return false;
    }
    
    public static interface OnShowModeChangedListener {
      void onShowModeChanged(AccessibilityService.SoftKeyboardController param2SoftKeyboardController, int param2Int);
    }
  }
  
  class null implements Runnable {
    public void run() {
      listener.onShowModeChanged(this.this$0, showMode);
    }
  }
  
  public static interface OnShowModeChangedListener {
    void onShowModeChanged(AccessibilityService.SoftKeyboardController param1SoftKeyboardController, int param1Int);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SoftKeyboardShowMode {}
  
  public static interface TakeScreenshotCallback {
    void onFailure(int param1Int);
    
    void onSuccess(AccessibilityService.ScreenshotResult param1ScreenshotResult);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */