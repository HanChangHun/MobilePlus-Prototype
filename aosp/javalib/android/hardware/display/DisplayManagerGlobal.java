package android.hardware.display;

import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.media.projection.IMediaProjection;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayAdjustments;
import android.view.DisplayInfo;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DisplayManagerGlobal {
  public static final String CACHE_KEY_DISPLAY_INFO_PROPERTY = "cache_key.display_info";
  
  private static final boolean DEBUG = false;
  
  public static final int EVENT_DISPLAY_ADDED = 1;
  
  public static final int EVENT_DISPLAY_CHANGED = 2;
  
  public static final int EVENT_DISPLAY_REMOVED = 3;
  
  private static final String TAG = "DisplayManager";
  
  private static final boolean USE_CACHE = false;
  
  private static DisplayManagerGlobal sInstance;
  
  private DisplayManagerCallback mCallback;
  
  private boolean mDispatchNativeCallbacks = false;
  
  private PropertyInvalidatedCache<Integer, DisplayInfo> mDisplayCache = new PropertyInvalidatedCache<Integer, DisplayInfo>(8, "cache_key.display_info") {
      protected DisplayInfo recompute(Integer param1Integer) {
        try {
          return DisplayManagerGlobal.this.mDm.getDisplayInfo(param1Integer.intValue());
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      }
    };
  
  private int[] mDisplayIdCache;
  
  private final SparseArray<DisplayInfo> mDisplayInfoCache = new SparseArray();
  
  private final ArrayList<DisplayListenerDelegate> mDisplayListeners = new ArrayList<>();
  
  private final IDisplayManager mDm;
  
  private final Object mLock = new Object();
  
  private final ColorSpace mWideColorSpace;
  
  private int mWifiDisplayScanNestCount;
  
  private DisplayManagerGlobal(IDisplayManager paramIDisplayManager) {
    this.mDm = paramIDisplayManager;
    try {
      this.mWideColorSpace = ColorSpace.get(ColorSpace.Named.values()[this.mDm.getPreferredWideGamutColorSpaceId()]);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private int findDisplayListenerLocked(DisplayManager.DisplayListener paramDisplayListener) {
    int i = this.mDisplayListeners.size();
    for (byte b = 0; b < i; b++) {
      if (((DisplayListenerDelegate)this.mDisplayListeners.get(b)).mListener == paramDisplayListener)
        return b; 
    } 
    return -1;
  }
  
  private DisplayInfo getDisplayInfoLocked(int paramInt) {
    DisplayInfo displayInfo = null;
    PropertyInvalidatedCache<Integer, DisplayInfo> propertyInvalidatedCache = this.mDisplayCache;
    if (propertyInvalidatedCache != null) {
      displayInfo = (DisplayInfo)propertyInvalidatedCache.query(Integer.valueOf(paramInt));
    } else {
      try {
        DisplayInfo displayInfo1 = this.mDm.getDisplayInfo(paramInt);
        displayInfo = displayInfo1;
      } catch (RemoteException remoteException) {
        remoteException.rethrowFromSystemServer();
      } 
    } 
    if (displayInfo == null)
      return null; 
    registerCallbackIfNeededLocked();
    return displayInfo;
  }
  
  public static DisplayManagerGlobal getInstance() {
    // Byte code:
    //   0: ldc android/hardware/display/DisplayManagerGlobal
    //   2: monitorenter
    //   3: getstatic android/hardware/display/DisplayManagerGlobal.sInstance : Landroid/hardware/display/DisplayManagerGlobal;
    //   6: ifnonnull -> 35
    //   9: ldc 'display'
    //   11: invokestatic getService : (Ljava/lang/String;)Landroid/os/IBinder;
    //   14: astore_0
    //   15: aload_0
    //   16: ifnull -> 35
    //   19: new android/hardware/display/DisplayManagerGlobal
    //   22: astore_1
    //   23: aload_1
    //   24: aload_0
    //   25: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/hardware/display/IDisplayManager;
    //   28: invokespecial <init> : (Landroid/hardware/display/IDisplayManager;)V
    //   31: aload_1
    //   32: putstatic android/hardware/display/DisplayManagerGlobal.sInstance : Landroid/hardware/display/DisplayManagerGlobal;
    //   35: getstatic android/hardware/display/DisplayManagerGlobal.sInstance : Landroid/hardware/display/DisplayManagerGlobal;
    //   38: astore_0
    //   39: ldc android/hardware/display/DisplayManagerGlobal
    //   41: monitorexit
    //   42: aload_0
    //   43: areturn
    //   44: astore_0
    //   45: ldc android/hardware/display/DisplayManagerGlobal
    //   47: monitorexit
    //   48: aload_0
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   3	15	44	finally
    //   19	35	44	finally
    //   35	42	44	finally
    //   45	48	44	finally
  }
  
  private static Looper getLooperForHandler(Handler paramHandler) {
    Looper looper1;
    if (paramHandler != null) {
      looper1 = paramHandler.getLooper();
    } else {
      looper1 = Looper.myLooper();
    } 
    Looper looper2 = looper1;
    if (looper1 == null)
      looper2 = Looper.getMainLooper(); 
    if (looper2 != null)
      return looper2; 
    throw new RuntimeException("Could not get Looper for the UI thread.");
  }
  
  private void handleDisplayEvent(int paramInt1, int paramInt2) {
    synchronized (this.mLock) {
      int i = this.mDisplayListeners.size();
      for (byte b = 0; b < i; b++)
        ((DisplayListenerDelegate)this.mDisplayListeners.get(b)).sendDisplayEvent(paramInt1, paramInt2); 
      if (paramInt2 == 2 && this.mDispatchNativeCallbacks && paramInt1 == 0) {
        DisplayInfo displayInfo = getDisplayInfoLocked(paramInt1);
        if (displayInfo != null)
          nSignalNativeCallbacks(displayInfo.getMode().getRefreshRate()); 
      } 
      return;
    } 
  }
  
  public static void invalidateLocalDisplayInfoCaches() {
    PropertyInvalidatedCache.invalidateCache("cache_key.display_info");
  }
  
  private static native void nSignalNativeCallbacks(float paramFloat);
  
  private void registerCallbackIfNeededLocked() {
    if (this.mCallback == null) {
      DisplayManagerCallback displayManagerCallback = new DisplayManagerCallback();
      this.mCallback = displayManagerCallback;
      try {
        this.mDm.registerCallback(displayManagerCallback);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  private void registerNativeChoreographerForRefreshRateCallbacks() {
    synchronized (this.mLock) {
      registerCallbackIfNeededLocked();
      this.mDispatchNativeCallbacks = true;
      DisplayInfo displayInfo = getDisplayInfoLocked(0);
      if (displayInfo != null)
        nSignalNativeCallbacks(displayInfo.getMode().getRefreshRate()); 
      return;
    } 
  }
  
  private void unregisterNativeChoreographerForRefreshRateCallbacks() {
    synchronized (this.mLock) {
      this.mDispatchNativeCallbacks = false;
      return;
    } 
  }
  
  public void connectWifiDisplay(String paramString) {
    if (paramString != null)
      try {
        this.mDm.connectWifiDisplay(paramString);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("deviceAddress must not be null");
  }
  
  public VirtualDisplay createVirtualDisplay(Context paramContext, MediaProjection paramMediaProjection, VirtualDisplayConfig paramVirtualDisplayConfig, VirtualDisplay.Callback paramCallback, Handler paramHandler) {
    VirtualDisplayCallback virtualDisplayCallback = new VirtualDisplayCallback(paramCallback, paramHandler);
    if (paramMediaProjection != null) {
      IMediaProjection iMediaProjection = paramMediaProjection.getProjection();
    } else {
      paramMediaProjection = null;
    } 
    try {
      int i = this.mDm.createVirtualDisplay(paramVirtualDisplayConfig, virtualDisplayCallback, (IMediaProjection)paramMediaProjection, paramContext.getPackageName());
      if (i < 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not create virtual display: ");
        stringBuilder.append(paramVirtualDisplayConfig.getName());
        Log.e("DisplayManager", stringBuilder.toString());
        return null;
      } 
      Display display = getRealDisplay(i);
      if (display == null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not obtain display info for newly created virtual display: ");
        stringBuilder.append(paramVirtualDisplayConfig.getName());
        Log.wtf("DisplayManager", stringBuilder.toString());
        try {
          this.mDm.releaseVirtualDisplay(virtualDisplayCallback);
          return null;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } 
      return new VirtualDisplay(this, (Display)remoteException, virtualDisplayCallback, paramVirtualDisplayConfig.getSurface());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disableLocalDisplayInfoCaches() {
    this.mDisplayCache = null;
  }
  
  public void disconnectWifiDisplay() {
    try {
      this.mDm.disconnectWifiDisplay();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void forgetWifiDisplay(String paramString) {
    if (paramString != null)
      try {
        this.mDm.forgetWifiDisplay(paramString);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("deviceAddress must not be null");
  }
  
  public List<AmbientBrightnessDayStats> getAmbientBrightnessStats() {
    try {
      ParceledListSlice parceledListSlice = this.mDm.getAmbientBrightnessStats();
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public BrightnessConfiguration getBrightnessConfigurationForUser(int paramInt) {
    try {
      return this.mDm.getBrightnessConfigurationForUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<BrightnessChangeEvent> getBrightnessEvents(String paramString) {
    try {
      ParceledListSlice parceledListSlice = this.mDm.getBrightnessEvents(paramString);
      return (parceledListSlice == null) ? Collections.emptyList() : parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Display getCompatibleDisplay(int paramInt, Resources paramResources) {
    DisplayInfo displayInfo = getDisplayInfo(paramInt);
    return (displayInfo == null) ? null : new Display(this, paramInt, displayInfo, paramResources);
  }
  
  public Display getCompatibleDisplay(int paramInt, DisplayAdjustments paramDisplayAdjustments) {
    DisplayInfo displayInfo = getDisplayInfo(paramInt);
    return (displayInfo == null) ? null : new Display(this, paramInt, displayInfo, paramDisplayAdjustments);
  }
  
  public BrightnessConfiguration getDefaultBrightnessConfiguration() {
    try {
      return this.mDm.getDefaultBrightnessConfiguration();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int[] getDisplayIds() {
    try {
      synchronized (this.mLock) {
        int[] arrayOfInt = this.mDm.getDisplayIds();
        registerCallbackIfNeededLocked();
        return arrayOfInt;
      } 
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public DisplayInfo getDisplayInfo(int paramInt) {
    synchronized (this.mLock) {
      return getDisplayInfoLocked(paramInt);
    } 
  }
  
  public Pair<float[], float[]> getMinimumBrightnessCurve() {
    try {
      Curve curve = this.mDm.getMinimumBrightnessCurve();
      return Pair.create(curve.getX(), curve.getY());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ColorSpace getPreferredWideGamutColorSpace() {
    return this.mWideColorSpace;
  }
  
  public Display getRealDisplay(int paramInt) {
    return getCompatibleDisplay(paramInt, DisplayAdjustments.DEFAULT_DISPLAY_ADJUSTMENTS);
  }
  
  public Point getStableDisplaySize() {
    try {
      return this.mDm.getStableDisplaySize();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public WifiDisplayStatus getWifiDisplayStatus() {
    try {
      return this.mDm.getWifiDisplayStatus();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isMinimalPostProcessingRequested(int paramInt) {
    try {
      return this.mDm.isMinimalPostProcessingRequested(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isUidPresentOnDisplay(int paramInt1, int paramInt2) {
    try {
      return this.mDm.isUidPresentOnDisplay(paramInt1, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void pauseWifiDisplay() {
    try {
      this.mDm.pauseWifiDisplay();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerDisplayListener(DisplayManager.DisplayListener paramDisplayListener, Handler paramHandler) {
    if (paramDisplayListener != null)
      synchronized (this.mLock) {
        if (findDisplayListenerLocked(paramDisplayListener) < 0) {
          Looper looper = getLooperForHandler(paramHandler);
          ArrayList<DisplayListenerDelegate> arrayList = this.mDisplayListeners;
          DisplayListenerDelegate displayListenerDelegate = new DisplayListenerDelegate();
          this(paramDisplayListener, looper);
          arrayList.add(displayListenerDelegate);
          registerCallbackIfNeededLocked();
        } 
        return;
      }  
    throw new IllegalArgumentException("listener must not be null");
  }
  
  public void releaseVirtualDisplay(IVirtualDisplayCallback paramIVirtualDisplayCallback) {
    try {
      this.mDm.releaseVirtualDisplay(paramIVirtualDisplayCallback);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void renameWifiDisplay(String paramString1, String paramString2) {
    if (paramString1 != null)
      try {
        this.mDm.renameWifiDisplay(paramString1, paramString2);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("deviceAddress must not be null");
  }
  
  public void requestColorMode(int paramInt1, int paramInt2) {
    try {
      this.mDm.requestColorMode(paramInt1, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resizeVirtualDisplay(IVirtualDisplayCallback paramIVirtualDisplayCallback, int paramInt1, int paramInt2, int paramInt3) {
    try {
      this.mDm.resizeVirtualDisplay(paramIVirtualDisplayCallback, paramInt1, paramInt2, paramInt3);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resumeWifiDisplay() {
    try {
      this.mDm.resumeWifiDisplay();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setBrightnessConfigurationForUser(BrightnessConfiguration paramBrightnessConfiguration, int paramInt, String paramString) {
    try {
      this.mDm.setBrightnessConfigurationForUser(paramBrightnessConfiguration, paramInt, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setTemporaryAutoBrightnessAdjustment(float paramFloat) {
    try {
      this.mDm.setTemporaryAutoBrightnessAdjustment(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setTemporaryBrightness(float paramFloat) {
    try {
      this.mDm.setTemporaryBrightness(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  void setVirtualDisplayState(IVirtualDisplayCallback paramIVirtualDisplayCallback, boolean paramBoolean) {
    try {
      this.mDm.setVirtualDisplayState(paramIVirtualDisplayCallback, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setVirtualDisplaySurface(IVirtualDisplayCallback paramIVirtualDisplayCallback, Surface paramSurface) {
    try {
      boolean bool;
      this.mDm.setVirtualDisplaySurface(paramIVirtualDisplayCallback, paramSurface);
      if (paramSurface != null) {
        bool = true;
      } else {
        bool = false;
      } 
      setVirtualDisplayState(paramIVirtualDisplayCallback, bool);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startWifiDisplayScan() {
    synchronized (this.mLock) {
      int i = this.mWifiDisplayScanNestCount;
      this.mWifiDisplayScanNestCount = i + 1;
      if (i == 0) {
        registerCallbackIfNeededLocked();
        try {
          this.mDm.startWifiDisplayScan();
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } 
      return;
    } 
  }
  
  public void stopWifiDisplayScan() {
    synchronized (this.mLock) {
      int i = this.mWifiDisplayScanNestCount - 1;
      this.mWifiDisplayScanNestCount = i;
      if (i == 0) {
        try {
          this.mDm.stopWifiDisplayScan();
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } else if (i < 0) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Wifi display scan nest count became negative: ");
        stringBuilder.append(this.mWifiDisplayScanNestCount);
        Log.wtf("DisplayManager", stringBuilder.toString());
        this.mWifiDisplayScanNestCount = 0;
      } 
      return;
    } 
  }
  
  public void unregisterDisplayListener(DisplayManager.DisplayListener paramDisplayListener) {
    if (paramDisplayListener != null)
      synchronized (this.mLock) {
        int i = findDisplayListenerLocked(paramDisplayListener);
        if (i >= 0) {
          ((DisplayListenerDelegate)this.mDisplayListeners.get(i)).clearEvents();
          this.mDisplayListeners.remove(i);
        } 
        return;
      }  
    throw new IllegalArgumentException("listener must not be null");
  }
  
  private static final class DisplayListenerDelegate extends Handler {
    public final DisplayManager.DisplayListener mListener;
    
    DisplayListenerDelegate(DisplayManager.DisplayListener param1DisplayListener, Looper param1Looper) {
      super(param1Looper, null, true);
      this.mListener = param1DisplayListener;
    }
    
    public void clearEvents() {
      removeCallbacksAndMessages(null);
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          if (i == 3)
            this.mListener.onDisplayRemoved(param1Message.arg1); 
        } else {
          this.mListener.onDisplayChanged(param1Message.arg1);
        } 
      } else {
        this.mListener.onDisplayAdded(param1Message.arg1);
      } 
    }
    
    public void sendDisplayEvent(int param1Int1, int param1Int2) {
      sendMessage(obtainMessage(param1Int2, param1Int1, 0));
    }
  }
  
  private final class DisplayManagerCallback extends IDisplayManagerCallback.Stub {
    private DisplayManagerCallback() {}
    
    public void onDisplayEvent(int param1Int1, int param1Int2) {
      DisplayManagerGlobal.this.handleDisplayEvent(param1Int1, param1Int2);
    }
  }
  
  private static final class VirtualDisplayCallback extends IVirtualDisplayCallback.Stub {
    private DisplayManagerGlobal.VirtualDisplayCallbackDelegate mDelegate;
    
    public VirtualDisplayCallback(VirtualDisplay.Callback param1Callback, Handler param1Handler) {
      if (param1Callback != null)
        this.mDelegate = new DisplayManagerGlobal.VirtualDisplayCallbackDelegate(param1Callback, param1Handler); 
    }
    
    public void onPaused() {
      DisplayManagerGlobal.VirtualDisplayCallbackDelegate virtualDisplayCallbackDelegate = this.mDelegate;
      if (virtualDisplayCallbackDelegate != null)
        virtualDisplayCallbackDelegate.sendEmptyMessage(0); 
    }
    
    public void onResumed() {
      DisplayManagerGlobal.VirtualDisplayCallbackDelegate virtualDisplayCallbackDelegate = this.mDelegate;
      if (virtualDisplayCallbackDelegate != null)
        virtualDisplayCallbackDelegate.sendEmptyMessage(1); 
    }
    
    public void onStopped() {
      DisplayManagerGlobal.VirtualDisplayCallbackDelegate virtualDisplayCallbackDelegate = this.mDelegate;
      if (virtualDisplayCallbackDelegate != null)
        virtualDisplayCallbackDelegate.sendEmptyMessage(2); 
    }
  }
  
  private static final class VirtualDisplayCallbackDelegate extends Handler {
    public static final int MSG_DISPLAY_PAUSED = 0;
    
    public static final int MSG_DISPLAY_RESUMED = 1;
    
    public static final int MSG_DISPLAY_STOPPED = 2;
    
    private final VirtualDisplay.Callback mCallback;
    
    public VirtualDisplayCallbackDelegate(VirtualDisplay.Callback param1Callback, Handler param1Handler) {
      super(looper, null, true);
      Looper looper;
      this.mCallback = param1Callback;
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 0) {
        if (i != 1) {
          if (i == 2)
            this.mCallback.onStopped(); 
        } else {
          this.mCallback.onResumed();
        } 
      } else {
        this.mCallback.onPaused();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */