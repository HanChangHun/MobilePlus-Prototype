package android.app;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.accessibilityservice.IAccessibilityServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.DisplayManagerGlobal;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import libcore.io.IoUtils;

public final class UiAutomation {
  private static final int CONNECTION_ID_UNDEFINED = -1;
  
  private static final long CONNECT_TIMEOUT_MILLIS = 5000L;
  
  private static final boolean DEBUG = false;
  
  public static final int FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES = 1;
  
  private static final String LOG_TAG = UiAutomation.class.getSimpleName();
  
  public static final int ROTATION_FREEZE_0 = 0;
  
  public static final int ROTATION_FREEZE_180 = 2;
  
  public static final int ROTATION_FREEZE_270 = 3;
  
  public static final int ROTATION_FREEZE_90 = 1;
  
  public static final int ROTATION_FREEZE_CURRENT = -1;
  
  public static final int ROTATION_UNFREEZE = -2;
  
  private IAccessibilityServiceClient mClient;
  
  private int mConnectionId = -1;
  
  private final ArrayList<AccessibilityEvent> mEventQueue = new ArrayList<>();
  
  private int mFlags;
  
  private boolean mIsConnecting;
  
  private boolean mIsDestroyed;
  
  private long mLastEventTimeMillis;
  
  private final Handler mLocalCallbackHandler;
  
  private final Object mLock = new Object();
  
  private OnAccessibilityEventListener mOnAccessibilityEventListener;
  
  private HandlerThread mRemoteCallbackThread;
  
  private final IUiAutomationConnection mUiAutomationConnection;
  
  private boolean mWaitingForEventDelivery;
  
  public UiAutomation(Looper paramLooper, IUiAutomationConnection paramIUiAutomationConnection) {
    if (paramLooper != null) {
      if (paramIUiAutomationConnection != null) {
        this.mLocalCallbackHandler = new Handler(paramLooper);
        this.mUiAutomationConnection = paramIUiAutomationConnection;
        return;
      } 
      throw new IllegalArgumentException("Connection cannot be null!");
    } 
    throw new IllegalArgumentException("Looper cannot be null!");
  }
  
  private boolean isConnectedLocked() {
    boolean bool;
    if (this.mConnectionId != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void throwIfConnectedLocked() {
    if (this.mConnectionId == -1)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UiAutomation not connected, ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void throwIfNotConnectedLocked() {
    if (isConnectedLocked())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UiAutomation not connected, ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void warnIfBetterCommand(String paramString) {
    if (paramString.startsWith("pm grant ")) {
      Log.w(LOG_TAG, "UiAutomation.grantRuntimePermission() is more robust and should be used instead of 'pm grant'");
    } else if (paramString.startsWith("pm revoke ")) {
      Log.w(LOG_TAG, "UiAutomation.revokeRuntimePermission() is more robust and should be used instead of 'pm revoke'");
    } 
  }
  
  public void adoptShellPermissionIdentity() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.adoptShellPermissionIdentity(Process.myUid(), null);
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error executing adopting shell permission identity!", (Throwable)remoteException);
      } 
      return;
    } 
  }
  
  public void adoptShellPermissionIdentity(String... paramVarArgs) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.adoptShellPermissionIdentity(Process.myUid(), paramVarArgs);
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error executing adopting shell permission identity!", (Throwable)remoteException);
      } 
      return;
    } 
  }
  
  public void clearWindowAnimationFrameStats() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.clearWindowAnimationFrameStats();
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error clearing window animation frame stats!", (Throwable)remoteException);
      } 
      return;
    } 
  }
  
  public boolean clearWindowContentFrameStats(int paramInt) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        return this.mUiAutomationConnection.clearWindowContentFrameStats(paramInt);
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error clearing window content frame stats!", (Throwable)remoteException);
        return false;
      } 
    } 
  }
  
  public void connect() {
    connect(0);
  }
  
  public void connect(int paramInt) {
    synchronized (this.mLock) {
      throwIfConnectedLocked();
      if (this.mIsConnecting)
        return; 
      this.mIsConnecting = true;
      HandlerThread handlerThread = new HandlerThread();
      this("UiAutomation");
      this.mRemoteCallbackThread = handlerThread;
      handlerThread.start();
      IAccessibilityServiceClientImpl iAccessibilityServiceClientImpl = new IAccessibilityServiceClientImpl();
      this(this, this.mRemoteCallbackThread.getLooper());
      this.mClient = (IAccessibilityServiceClient)iAccessibilityServiceClientImpl;
      try {
        this.mUiAutomationConnection.connect((IAccessibilityServiceClient)iAccessibilityServiceClientImpl, paramInt);
        this.mFlags = paramInt;
        synchronized (this.mLock) {
          long l = SystemClock.uptimeMillis();
          try {
            while (true) {
              boolean bool = isConnectedLocked();
              if (bool)
                return; 
              long l1 = SystemClock.uptimeMillis();
              l1 = 5000L - l1 - l;
              if (l1 > 0L) {
                try {
                  this.mLock.wait(l1);
                } catch (InterruptedException interruptedException) {}
                continue;
              } 
              RuntimeException runtimeException = new RuntimeException();
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Error while connecting ");
              stringBuilder.append(this);
              this(stringBuilder.toString());
              throw runtimeException;
            } 
          } finally {
            this.mIsConnecting = false;
          } 
        } 
      } catch (RemoteException remoteException) {
        null = new StringBuilder();
        null.append("Error while connecting ");
        null.append(this);
        throw new RuntimeException(null.toString(), remoteException);
      } 
    } 
  }
  
  public void destroy() {
    disconnect();
    this.mIsDestroyed = true;
  }
  
  public void disconnect() {
    synchronized (this.mLock) {
      if (!this.mIsConnecting) {
        throwIfNotConnectedLocked();
        this.mConnectionId = -1;
        try {
          this.mUiAutomationConnection.disconnect();
          this.mRemoteCallbackThread.quit();
          this.mRemoteCallbackThread = null;
          return;
        } catch (RemoteException remoteException) {
          null = new RuntimeException();
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("Error while disconnecting ");
          stringBuilder1.append(this);
          super(stringBuilder1.toString(), (Throwable)remoteException);
          throw null;
        } finally {}
        this.mRemoteCallbackThread.quit();
        this.mRemoteCallbackThread = null;
        throw null;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Cannot call disconnect() while connecting ");
      stringBuilder.append(this);
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  public void dropShellPermissionIdentity() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.dropShellPermissionIdentity();
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error executing dropping shell permission identity!", (Throwable)remoteException);
      } 
      return;
    } 
  }
  
  public AccessibilityEvent executeAndWaitForEvent(Runnable paramRunnable, AccessibilityEventFilter paramAccessibilityEventFilter, long paramLong) throws TimeoutException {
    Object object = this.mLock;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    try {
      throwIfNotConnectedLocked();
      this.mEventQueue.clear();
      this.mWaitingForEventDelivery = true;
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      long l1 = SystemClock.uptimeMillis();
      paramRunnable.run();
      object = new ArrayList();
      long l2 = l1;
      try {
        long l = SystemClock.uptimeMillis();
        while (true) {
          l2 = l1;
          ArrayList<AccessibilityEvent> arrayList = new ArrayList();
          l2 = l1;
          this();
          l2 = l1;
          object1 = this.mLock;
          l2 = l1;
          /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
          try {
            arrayList.addAll(this.mEventQueue);
            this.mEventQueue.clear();
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
            while (true) {
              l2 = l1;
              boolean bool = arrayList.isEmpty();
              if (!bool) {
                try {
                  object1 = arrayList.remove(0);
                  l2 = object1.getEventTime();
                  if (l2 < l1)
                    continue; 
                } finally {
                  arrayList = null;
                } 
                continue;
              } 
              try {
                l2 = SystemClock.uptimeMillis();
                l2 = paramLong - l2 - l;
                if (l2 > 0L)
                  try {
                  
                  } finally {
                    arrayList = null;
                  }  
                TimeoutException timeoutException = new TimeoutException();
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Expected event not received within: ");
                stringBuilder.append(paramLong);
                stringBuilder.append(" ms among: ");
                stringBuilder.append(object);
                this(stringBuilder.toString());
                throw timeoutException;
              } finally {
                arrayList = null;
              } 
            } 
          } finally {
            arrayList = null;
          } 
          break;
        } 
      } finally {
        paramRunnable = null;
      } 
      int i = object.size();
      for (byte b = 0; b < i; b++)
        ((AccessibilityEvent)object.get(b)).recycle(); 
    } finally {
      paramRunnable = null;
    } 
  }
  
  public ParcelFileDescriptor executeShellCommand(String paramString) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      warnIfBetterCommand(paramString);
      ParcelFileDescriptor parcelFileDescriptor1 = null;
      ParcelFileDescriptor parcelFileDescriptor2 = null;
      ParcelFileDescriptor parcelFileDescriptor3 = null;
      ParcelFileDescriptor parcelFileDescriptor4 = null;
      null = null;
      try {
        ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor6 = arrayOfParcelFileDescriptor[0];
        ParcelFileDescriptor parcelFileDescriptor5 = arrayOfParcelFileDescriptor[1];
        null = parcelFileDescriptor5;
        parcelFileDescriptor2 = parcelFileDescriptor6;
        parcelFileDescriptor3 = parcelFileDescriptor5;
        parcelFileDescriptor1 = parcelFileDescriptor6;
        parcelFileDescriptor4 = parcelFileDescriptor5;
        this.mUiAutomationConnection.executeShellCommand(paramString, parcelFileDescriptor5, null);
        parcelFileDescriptor2 = parcelFileDescriptor6;
        parcelFileDescriptor3 = parcelFileDescriptor5;
      } catch (IOException iOException) {
        null = parcelFileDescriptor4;
        Log.e(LOG_TAG, "Error executing shell command!", iOException);
        parcelFileDescriptor3 = parcelFileDescriptor4;
        parcelFileDescriptor2 = parcelFileDescriptor1;
      } catch (RemoteException remoteException) {
        null = parcelFileDescriptor3;
        Log.e(LOG_TAG, "Error executing shell command!", (Throwable)remoteException);
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)parcelFileDescriptor3);
      return parcelFileDescriptor2;
    } 
  }
  
  public ParcelFileDescriptor[] executeShellCommandRw(String paramString) {
    synchronized (this.mLock) {
      ParcelFileDescriptor parcelFileDescriptor11;
      throwIfNotConnectedLocked();
      warnIfBetterCommand(paramString);
      ParcelFileDescriptor parcelFileDescriptor1 = null;
      ParcelFileDescriptor parcelFileDescriptor2 = null;
      ParcelFileDescriptor parcelFileDescriptor3 = null;
      ParcelFileDescriptor parcelFileDescriptor4 = null;
      ParcelFileDescriptor parcelFileDescriptor5 = null;
      ParcelFileDescriptor parcelFileDescriptor6 = null;
      ParcelFileDescriptor parcelFileDescriptor7 = null;
      ParcelFileDescriptor parcelFileDescriptor8 = null;
      ParcelFileDescriptor parcelFileDescriptor9 = null;
      ParcelFileDescriptor[] arrayOfParcelFileDescriptor1 = null;
      null = parcelFileDescriptor8;
      ParcelFileDescriptor parcelFileDescriptor10 = parcelFileDescriptor6;
      ParcelFileDescriptor[] arrayOfParcelFileDescriptor2 = arrayOfParcelFileDescriptor1;
      ParcelFileDescriptor parcelFileDescriptor12 = parcelFileDescriptor7;
      ParcelFileDescriptor parcelFileDescriptor13 = parcelFileDescriptor9;
      try {
        ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor16 = arrayOfParcelFileDescriptor[0];
        ParcelFileDescriptor parcelFileDescriptor15 = arrayOfParcelFileDescriptor[1];
        parcelFileDescriptor5 = parcelFileDescriptor15;
        null = parcelFileDescriptor8;
        parcelFileDescriptor2 = parcelFileDescriptor16;
        parcelFileDescriptor3 = parcelFileDescriptor15;
        parcelFileDescriptor10 = parcelFileDescriptor6;
        arrayOfParcelFileDescriptor2 = arrayOfParcelFileDescriptor1;
        parcelFileDescriptor1 = parcelFileDescriptor16;
        parcelFileDescriptor4 = parcelFileDescriptor15;
        parcelFileDescriptor12 = parcelFileDescriptor7;
        parcelFileDescriptor13 = parcelFileDescriptor9;
        arrayOfParcelFileDescriptor1 = ParcelFileDescriptor.createPipe();
        parcelFileDescriptor8 = arrayOfParcelFileDescriptor1[0];
        ParcelFileDescriptor parcelFileDescriptor14 = arrayOfParcelFileDescriptor1[1];
        parcelFileDescriptor5 = parcelFileDescriptor15;
        null = parcelFileDescriptor8;
        parcelFileDescriptor2 = parcelFileDescriptor16;
        parcelFileDescriptor3 = parcelFileDescriptor15;
        parcelFileDescriptor10 = parcelFileDescriptor8;
        parcelFileDescriptor11 = parcelFileDescriptor14;
        parcelFileDescriptor1 = parcelFileDescriptor16;
        parcelFileDescriptor4 = parcelFileDescriptor15;
        parcelFileDescriptor12 = parcelFileDescriptor8;
        parcelFileDescriptor13 = parcelFileDescriptor14;
        this.mUiAutomationConnection.executeShellCommand(paramString, parcelFileDescriptor15, parcelFileDescriptor8);
        parcelFileDescriptor1 = parcelFileDescriptor16;
        parcelFileDescriptor3 = parcelFileDescriptor15;
        parcelFileDescriptor10 = parcelFileDescriptor8;
        parcelFileDescriptor11 = parcelFileDescriptor14;
      } catch (IOException iOException) {
        parcelFileDescriptor5 = parcelFileDescriptor4;
        null = parcelFileDescriptor12;
        Log.e(LOG_TAG, "Error executing shell command!", iOException);
        parcelFileDescriptor11 = parcelFileDescriptor13;
        parcelFileDescriptor10 = parcelFileDescriptor12;
        parcelFileDescriptor3 = parcelFileDescriptor4;
      } catch (RemoteException remoteException) {
        parcelFileDescriptor5 = parcelFileDescriptor3;
        null = parcelFileDescriptor10;
        Log.e(LOG_TAG, "Error executing shell command!", (Throwable)remoteException);
        parcelFileDescriptor1 = parcelFileDescriptor2;
      } finally {}
      IoUtils.closeQuietly((AutoCloseable)parcelFileDescriptor3);
      IoUtils.closeQuietly((AutoCloseable)parcelFileDescriptor10);
      return new ParcelFileDescriptor[] { parcelFileDescriptor1, parcelFileDescriptor11 };
    } 
  }
  
  public AccessibilityNodeInfo findFocus(int paramInt) {
    return AccessibilityInteractionClient.getInstance().findFocus(this.mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, paramInt);
  }
  
  public int getConnectionId() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      return this.mConnectionId;
    } 
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public AccessibilityNodeInfo getRootInActiveWindow() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      int i = this.mConnectionId;
      return AccessibilityInteractionClient.getInstance().getRootInActiveWindow(i);
    } 
  }
  
  public final AccessibilityServiceInfo getServiceInfo() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.getServiceInfo();
        } catch (RemoteException null) {
          Log.w(LOG_TAG, "Error while getting AccessibilityServiceInfo", (Throwable)null);
        }  
      return null;
    } 
  }
  
  public WindowAnimationFrameStats getWindowAnimationFrameStats() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        return this.mUiAutomationConnection.getWindowAnimationFrameStats();
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error getting window animation frame stats!", (Throwable)remoteException);
        return null;
      } 
    } 
  }
  
  public WindowContentFrameStats getWindowContentFrameStats(int paramInt) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        return this.mUiAutomationConnection.getWindowContentFrameStats(paramInt);
      } catch (RemoteException null) {
        Log.e(LOG_TAG, "Error getting window content frame stats!", (Throwable)null);
        return null;
      } 
    } 
  }
  
  public List<AccessibilityWindowInfo> getWindows() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      int i = this.mConnectionId;
      return AccessibilityInteractionClient.getInstance().getWindows(i);
    } 
  }
  
  public SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      int i = this.mConnectionId;
      return AccessibilityInteractionClient.getInstance().getWindowsOnAllDisplays(i);
    } 
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2) {
    grantRuntimePermissionAsUser(paramString1, paramString2, Process.myUserHandle());
  }
  
  @Deprecated
  public boolean grantRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle) {
    grantRuntimePermissionAsUser(paramString1, paramString2, paramUserHandle);
    return true;
  }
  
  public void grantRuntimePermissionAsUser(String paramString1, String paramString2, UserHandle paramUserHandle) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.grantRuntimePermission(paramString1, paramString2, paramUserHandle.getIdentifier());
        return;
      } catch (Exception exception) {
        throw new SecurityException("Error granting runtime permission", exception);
      } 
    } 
  }
  
  public boolean injectInputEvent(InputEvent paramInputEvent, boolean paramBoolean) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        return this.mUiAutomationConnection.injectInputEvent(paramInputEvent, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error while injecting input event!", (Throwable)remoteException);
        return false;
      } 
    } 
  }
  
  public boolean isDestroyed() {
    return this.mIsDestroyed;
  }
  
  public final boolean performGlobalAction(int paramInt) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          return iAccessibilityServiceConnection.performGlobalAction(paramInt);
        } catch (RemoteException null) {
          Log.w(LOG_TAG, "Error while calling performGlobalAction", (Throwable)null);
        }  
      return false;
    } 
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2) {
    revokeRuntimePermissionAsUser(paramString1, paramString2, Process.myUserHandle());
  }
  
  @Deprecated
  public boolean revokeRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle) {
    revokeRuntimePermissionAsUser(paramString1, paramString2, paramUserHandle);
    return true;
  }
  
  public void revokeRuntimePermissionAsUser(String paramString1, String paramString2, UserHandle paramUserHandle) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.revokeRuntimePermission(paramString1, paramString2, paramUserHandle.getIdentifier());
        return;
      } catch (Exception exception) {
        throw new SecurityException("Error granting runtime permission", exception);
      } 
    } 
  }
  
  public void setOnAccessibilityEventListener(OnAccessibilityEventListener paramOnAccessibilityEventListener) {
    synchronized (this.mLock) {
      this.mOnAccessibilityEventListener = paramOnAccessibilityEventListener;
      return;
    } 
  }
  
  public boolean setRotation(int paramInt) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      if (paramInt == -2 || paramInt == -1 || paramInt == 0 || paramInt == 1 || paramInt == 2 || paramInt == 3)
        try {
          this.mUiAutomationConnection.setRotation(paramInt);
          return true;
        } catch (RemoteException null) {
          Log.e(LOG_TAG, "Error while setting rotation!", (Throwable)null);
          return false;
        }  
      throw new IllegalArgumentException("Invalid rotation.");
    } 
  }
  
  public void setRunAsMonkey(boolean paramBoolean) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        ActivityManager.getService().setUserIsMonkey(paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Error while setting run as monkey!", (Throwable)remoteException);
      } 
      return;
    } 
  }
  
  public final void setServiceInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      AccessibilityInteractionClient.getInstance().clearCache();
      AccessibilityInteractionClient.getInstance();
      IAccessibilityServiceConnection iAccessibilityServiceConnection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
      if (iAccessibilityServiceConnection != null)
        try {
          iAccessibilityServiceConnection.setServiceInfo(paramAccessibilityServiceInfo);
        } catch (RemoteException remoteException) {
          Log.w(LOG_TAG, "Error while setting AccessibilityServiceInfo", (Throwable)remoteException);
        }  
      return;
    } 
  }
  
  public void syncInputTransactions() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      try {
        this.mUiAutomationConnection.syncInputTransactions();
      } catch (RemoteException null) {
        Log.e(LOG_TAG, "Error while syncing input transactions!", (Throwable)null);
      } 
      return;
    } 
  }
  
  public Bitmap takeScreenshot() {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      Display display = DisplayManagerGlobal.getInstance().getRealDisplay(0);
      null = new Point();
      display.getRealSize((Point)null);
      int i = display.getRotation();
      try {
        IUiAutomationConnection iUiAutomationConnection = this.mUiAutomationConnection;
        Rect rect = new Rect();
        this(0, 0, ((Point)null).x, ((Point)null).y);
        null = iUiAutomationConnection.takeScreenshot(rect, i);
        if (null == null)
          return null; 
        null.setHasAlpha(false);
        return (Bitmap)null;
      } catch (RemoteException null) {
        Log.e(LOG_TAG, "Error while taking screnshot!", (Throwable)null);
        return null;
      } 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UiAutomation@");
    stringBuilder.append(Integer.toHexString(hashCode()));
    stringBuilder.append("[id=");
    stringBuilder.append(this.mConnectionId);
    stringBuilder.append(", flags=");
    stringBuilder.append(this.mFlags);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void waitForIdle(long paramLong1, long paramLong2) throws TimeoutException {
    synchronized (this.mLock) {
      throwIfNotConnectedLocked();
      long l1 = SystemClock.uptimeMillis();
      long l2 = l1;
      if (this.mLastEventTimeMillis <= 0L) {
        this.mLastEventTimeMillis = l1;
        l2 = l1;
      } 
      while (true) {
        l1 = SystemClock.uptimeMillis();
        if (paramLong2 - l1 - l2 > 0L) {
          l1 = paramLong1 - l1 - this.mLastEventTimeMillis;
          if (l1 <= 0L)
            return; 
          try {
            this.mLock.wait(l1);
          } catch (InterruptedException interruptedException) {}
          continue;
        } 
        TimeoutException timeoutException = new TimeoutException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("No idle state with idle timeout: ");
        stringBuilder.append(paramLong1);
        stringBuilder.append(" within global timeout: ");
        stringBuilder.append(paramLong2);
        this(stringBuilder.toString());
        throw timeoutException;
      } 
    } 
  }
  
  public static interface AccessibilityEventFilter {
    boolean accept(AccessibilityEvent param1AccessibilityEvent);
  }
  
  private class IAccessibilityServiceClientImpl extends AccessibilityService.IAccessibilityServiceClientWrapper {
    public IAccessibilityServiceClientImpl(Looper param1Looper) {
      super(null, param1Looper, new AccessibilityService.Callbacks(UiAutomation.this) {
            public void init(int param2Int, IBinder param2IBinder) {
              synchronized (UiAutomation.this.mLock) {
                UiAutomation.access$102(UiAutomation.this, param2Int);
                UiAutomation.this.mLock.notifyAll();
                if (Build.IS_DEBUGGABLE) {
                  String str = UiAutomation.LOG_TAG;
                  null = new StringBuilder();
                  null.append("Init ");
                  null.append(UiAutomation.this);
                  Log.v(str, null.toString());
                } 
                return;
              } 
            }
            
            public void onAccessibilityButtonAvailabilityChanged(boolean param2Boolean) {}
            
            public void onAccessibilityButtonClicked(int param2Int) {}
            
            public void onAccessibilityEvent(AccessibilityEvent param2AccessibilityEvent) {
              synchronized (UiAutomation.this.mLock) {
                UiAutomation.access$302(UiAutomation.this, param2AccessibilityEvent.getEventTime());
                if (UiAutomation.this.mWaitingForEventDelivery)
                  UiAutomation.this.mEventQueue.add(AccessibilityEvent.obtain(param2AccessibilityEvent)); 
                UiAutomation.this.mLock.notifyAll();
                UiAutomation.OnAccessibilityEventListener onAccessibilityEventListener = UiAutomation.this.mOnAccessibilityEventListener;
                if (onAccessibilityEventListener != null)
                  UiAutomation.this.mLocalCallbackHandler.sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$GnVtsLTLDH5bZdtLeTd6cfwpgcs.INSTANCE, onAccessibilityEventListener, AccessibilityEvent.obtain(param2AccessibilityEvent))); 
                return;
              } 
            }
            
            public void onFingerprintCapturingGesturesChanged(boolean param2Boolean) {}
            
            public void onFingerprintGesture(int param2Int) {}
            
            public boolean onGesture(AccessibilityGestureEvent param2AccessibilityGestureEvent) {
              return false;
            }
            
            public void onInterrupt() {}
            
            public boolean onKeyEvent(KeyEvent param2KeyEvent) {
              return false;
            }
            
            public void onMagnificationChanged(int param2Int, Region param2Region, float param2Float1, float param2Float2, float param2Float3) {}
            
            public void onPerformGestureResult(int param2Int, boolean param2Boolean) {}
            
            public void onServiceConnected() {}
            
            public void onSoftKeyboardShowModeChanged(int param2Int) {}
            
            public void onSystemActionsChanged() {}
          });
    }
  }
  
  class null implements AccessibilityService.Callbacks {
    public void init(int param1Int, IBinder param1IBinder) {
      synchronized (this$0.mLock) {
        UiAutomation.access$102(this$0, param1Int);
        this$0.mLock.notifyAll();
        if (Build.IS_DEBUGGABLE) {
          String str = UiAutomation.LOG_TAG;
          null = new StringBuilder();
          null.append("Init ");
          null.append(this$0);
          Log.v(str, null.toString());
        } 
        return;
      } 
    }
    
    public void onAccessibilityButtonAvailabilityChanged(boolean param1Boolean) {}
    
    public void onAccessibilityButtonClicked(int param1Int) {}
    
    public void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent) {
      synchronized (this$0.mLock) {
        UiAutomation.access$302(this$0, param1AccessibilityEvent.getEventTime());
        if (this$0.mWaitingForEventDelivery)
          this$0.mEventQueue.add(AccessibilityEvent.obtain(param1AccessibilityEvent)); 
        this$0.mLock.notifyAll();
        UiAutomation.OnAccessibilityEventListener onAccessibilityEventListener = this$0.mOnAccessibilityEventListener;
        if (onAccessibilityEventListener != null)
          this$0.mLocalCallbackHandler.sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$GnVtsLTLDH5bZdtLeTd6cfwpgcs.INSTANCE, onAccessibilityEventListener, AccessibilityEvent.obtain(param1AccessibilityEvent))); 
        return;
      } 
    }
    
    public void onFingerprintCapturingGesturesChanged(boolean param1Boolean) {}
    
    public void onFingerprintGesture(int param1Int) {}
    
    public boolean onGesture(AccessibilityGestureEvent param1AccessibilityGestureEvent) {
      return false;
    }
    
    public void onInterrupt() {}
    
    public boolean onKeyEvent(KeyEvent param1KeyEvent) {
      return false;
    }
    
    public void onMagnificationChanged(int param1Int, Region param1Region, float param1Float1, float param1Float2, float param1Float3) {}
    
    public void onPerformGestureResult(int param1Int, boolean param1Boolean) {}
    
    public void onServiceConnected() {}
    
    public void onSoftKeyboardShowModeChanged(int param1Int) {}
    
    public void onSystemActionsChanged() {}
  }
  
  public static interface OnAccessibilityEventListener {
    void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiAutomation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */