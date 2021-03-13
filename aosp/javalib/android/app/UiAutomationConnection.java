package android.app;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.permission.IPermissionManager;
import android.util.Log;
import android.view.IWindowManager;
import android.view.InputEvent;
import android.view.SurfaceControl;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;
import android.view.accessibility.IAccessibilityManager;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import libcore.io.IoUtils;

public final class UiAutomationConnection extends IUiAutomationConnection.Stub {
  private static final int INITIAL_FROZEN_ROTATION_UNSPECIFIED = -1;
  
  private static final String TAG = "UiAutomationConnection";
  
  private final IAccessibilityManager mAccessibilityManager = IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
  
  private final IActivityManager mActivityManager = IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));
  
  private IAccessibilityServiceClient mClient;
  
  private int mInitialFrozenRotation = -1;
  
  private boolean mIsShutdown;
  
  private final Object mLock = new Object();
  
  private int mOwningUid;
  
  private final IPermissionManager mPermissionManager = IPermissionManager.Stub.asInterface(ServiceManager.getService("permissionmgr"));
  
  private final Binder mToken = new Binder();
  
  private final IWindowManager mWindowManager = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
  
  private boolean isConnectedLocked() {
    boolean bool;
    if (this.mClient != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void registerUiTestAutomationServiceLocked(IAccessibilityServiceClient paramIAccessibilityServiceClient, int paramInt) {
    IAccessibilityManager iAccessibilityManager = IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
    AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
    accessibilityServiceInfo.eventTypes = -1;
    accessibilityServiceInfo.feedbackType = 16;
    accessibilityServiceInfo.flags |= 0x10012;
    accessibilityServiceInfo.setCapabilities(15);
    try {
      iAccessibilityManager.registerUiTestAutomationService((IBinder)this.mToken, paramIAccessibilityServiceClient, accessibilityServiceInfo, paramInt);
      this.mClient = paramIAccessibilityServiceClient;
      return;
    } catch (RemoteException remoteException) {
      throw new IllegalStateException("Error while registering UiTestAutomationService.", remoteException);
    } 
  }
  
  private void restoreRotationStateLocked() {
    try {
      if (this.mInitialFrozenRotation != -1) {
        this.mWindowManager.freezeRotation(this.mInitialFrozenRotation);
      } else {
        this.mWindowManager.thawRotation();
      } 
    } catch (RemoteException remoteException) {}
  }
  
  private void storeRotationStateLocked() {
    try {
      if (this.mWindowManager.isRotationFrozen())
        this.mInitialFrozenRotation = this.mWindowManager.getDefaultDisplayRotation(); 
    } catch (RemoteException remoteException) {}
  }
  
  private void throwIfCalledByNotTrustedUidLocked() {
    int i = Binder.getCallingUid();
    int j = this.mOwningUid;
    if (i == j || j == 1000 || i == 0)
      return; 
    throw new SecurityException("Calling from not trusted UID!");
  }
  
  private void throwIfNotConnectedLocked() {
    if (isConnectedLocked())
      return; 
    throw new IllegalStateException("Not connected!");
  }
  
  private void throwIfShutdownLocked() {
    if (!this.mIsShutdown)
      return; 
    throw new IllegalStateException("Connection shutdown!");
  }
  
  private void unregisterUiTestAutomationServiceLocked() {
    IAccessibilityManager iAccessibilityManager = IAccessibilityManager.Stub.asInterface(ServiceManager.getService("accessibility"));
    try {
      iAccessibilityManager.unregisterUiTestAutomationService(this.mClient);
      this.mClient = null;
      return;
    } catch (RemoteException remoteException) {
      throw new IllegalStateException("Error while unregistering UiTestAutomationService", remoteException);
    } 
  }
  
  public void adoptShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        this.mActivityManager.startDelegateShellPermissionIdentity(paramInt, paramArrayOfString);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void clearWindowAnimationFrameStats() {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        SurfaceControl.clearAnimationFrameStats();
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public boolean clearWindowContentFrameStats(int paramInt) throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      int i = UserHandle.getCallingUserId();
      long l = Binder.clearCallingIdentity();
      try {
        IBinder iBinder = this.mAccessibilityManager.getWindowToken(paramInt, i);
        if (iBinder == null)
          return false; 
        return this.mWindowManager.clearWindowContentFrameStats(iBinder);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void connect(IAccessibilityServiceClient paramIAccessibilityServiceClient, int paramInt) {
    if (paramIAccessibilityServiceClient != null)
      synchronized (this.mLock) {
        throwIfShutdownLocked();
        if (!isConnectedLocked()) {
          this.mOwningUid = Binder.getCallingUid();
          registerUiTestAutomationServiceLocked(paramIAccessibilityServiceClient, paramInt);
          storeRotationStateLocked();
          return;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Already connected.");
        throw illegalStateException;
      }  
    throw new IllegalArgumentException("Client cannot be null!");
  }
  
  public void disconnect() {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      if (isConnectedLocked()) {
        this.mOwningUid = -1;
        unregisterUiTestAutomationServiceLocked();
        restoreRotationStateLocked();
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Already disconnected.");
      throw illegalStateException;
    } 
  }
  
  public void dropShellPermissionIdentity() throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        this.mActivityManager.stopDelegateShellPermissionIdentity();
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void executeShellCommand(final String readFromProcess, final ParcelFileDescriptor sink, final ParcelFileDescriptor source) throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      try {
        final Process process = Runtime.getRuntime().exec(readFromProcess);
        if (sink != null) {
          Thread thread = new Thread(new Repeater(process.getInputStream(), new FileOutputStream(sink.getFileDescriptor())));
          thread.start();
        } else {
          readFromProcess = null;
        } 
        if (source != null) {
          null = process.getOutputStream();
          null = new Thread(new Repeater(new FileInputStream(source.getFileDescriptor()), (OutputStream)null));
          null.start();
        } else {
          null = null;
        } 
        (new Thread(new Runnable() {
              public void run() {
                try {
                  if (writeToProcess != null)
                    writeToProcess.join(); 
                  if (readFromProcess != null)
                    readFromProcess.join(); 
                } catch (InterruptedException interruptedException) {
                  Log.e("UiAutomationConnection", "At least one of the threads was interrupted");
                } 
                IoUtils.closeQuietly((AutoCloseable)sink);
                IoUtils.closeQuietly((AutoCloseable)source);
                process.destroy();
              }
            })).start();
        return;
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error running shell command '");
        stringBuilder.append(readFromProcess);
        stringBuilder.append("'");
        throw new RuntimeException(stringBuilder.toString(), iOException);
      } 
    } 
  }
  
  public WindowAnimationFrameStats getWindowAnimationFrameStats() {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        WindowAnimationFrameStats windowAnimationFrameStats = new WindowAnimationFrameStats();
        this();
        SurfaceControl.getAnimationFrameStats(windowAnimationFrameStats);
        return windowAnimationFrameStats;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public WindowContentFrameStats getWindowContentFrameStats(int paramInt) throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      int i = UserHandle.getCallingUserId();
      long l = Binder.clearCallingIdentity();
      try {
        null = this.mAccessibilityManager.getWindowToken(paramInt, i);
        if (null == null)
          return null; 
        null = this.mWindowManager.getWindowContentFrameStats((IBinder)null);
        return (WindowContentFrameStats)null;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        this.mPermissionManager.grantRuntimePermission(paramString1, paramString2, paramInt);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public boolean injectInputEvent(InputEvent paramInputEvent, boolean paramBoolean) {
    synchronized (this.mLock) {
      boolean bool;
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      long l = Binder.clearCallingIdentity();
      try {
        paramBoolean = this.mWindowManager.injectInputAfterTransactionsApplied(paramInputEvent, bool);
        return paramBoolean;
      } catch (RemoteException remoteException) {
        return false;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        this.mPermissionManager.revokeRuntimePermission(paramString1, paramString2, paramInt, null);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public boolean setRotation(int paramInt) {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      if (paramInt == -2)
        try {
          this.mWindowManager.thawRotation();
          return true;
        } catch (RemoteException remoteException) {
          return false;
        } finally {
          Binder.restoreCallingIdentity(l);
        }  
      this.mWindowManager.freezeRotation(paramInt);
      Binder.restoreCallingIdentity(l);
      return true;
    } 
  }
  
  public void shutdown() {
    synchronized (this.mLock) {
      if (isConnectedLocked())
        throwIfCalledByNotTrustedUidLocked(); 
      throwIfShutdownLocked();
      this.mIsShutdown = true;
      if (isConnectedLocked())
        disconnect(); 
      return;
    } 
  }
  
  public void syncInputTransactions() {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      try {
        this.mWindowManager.syncInputTransactions();
      } catch (RemoteException remoteException) {}
      return;
    } 
  }
  
  public Bitmap takeScreenshot(Rect paramRect, int paramInt) {
    synchronized (this.mLock) {
      throwIfCalledByNotTrustedUidLocked();
      throwIfShutdownLocked();
      throwIfNotConnectedLocked();
      long l = Binder.clearCallingIdentity();
      try {
        return SurfaceControl.screenshot(paramRect, paramRect.width(), paramRect.height(), paramInt);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public class Repeater implements Runnable {
    private final InputStream readFrom;
    
    private final OutputStream writeTo;
    
    public Repeater(InputStream param1InputStream, OutputStream param1OutputStream) {
      this.readFrom = param1InputStream;
      this.writeTo = param1OutputStream;
    }
    
    public void run() {
      try {
        byte[] arrayOfByte = new byte[8192];
        while (true) {
          int i = this.readFrom.read(arrayOfByte);
          if (i < 0)
            break; 
          this.writeTo.write(arrayOfByte, 0, i);
          this.writeTo.flush();
        } 
      } catch (IOException iOException) {
        Log.w("UiAutomationConnection", "Error while reading/writing to streams");
      } finally {
        Exception exception;
      } 
      IoUtils.closeQuietly(this.readFrom);
      IoUtils.closeQuietly(this.writeTo);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiAutomationConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */