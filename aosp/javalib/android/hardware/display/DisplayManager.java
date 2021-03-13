package android.hardware.display;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Display;
import android.view.Surface;
import java.util.ArrayList;
import java.util.List;

public final class DisplayManager {
  public static final String ACTION_WIFI_DISPLAY_STATUS_CHANGED = "android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED";
  
  private static final boolean DEBUG = false;
  
  public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  
  public static final String EXTRA_WIFI_DISPLAY_STATUS = "android.hardware.display.extra.WIFI_DISPLAY_STATUS";
  
  private static final String TAG = "DisplayManager";
  
  public static final int VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR = 16;
  
  public static final int VIRTUAL_DISPLAY_FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD = 32;
  
  public static final int VIRTUAL_DISPLAY_FLAG_DESTROY_CONTENT_ON_REMOVAL = 256;
  
  public static final int VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY = 8;
  
  public static final int VIRTUAL_DISPLAY_FLAG_PRESENTATION = 2;
  
  public static final int VIRTUAL_DISPLAY_FLAG_PUBLIC = 1;
  
  public static final int VIRTUAL_DISPLAY_FLAG_ROTATES_WITH_CONTENT = 128;
  
  public static final int VIRTUAL_DISPLAY_FLAG_SECURE = 4;
  
  public static final int VIRTUAL_DISPLAY_FLAG_SHOULD_SHOW_SYSTEM_DECORATIONS = 512;
  
  public static final int VIRTUAL_DISPLAY_FLAG_SUPPORTS_TOUCH = 64;
  
  public static final int VIRTUAL_DISPLAY_FLAG_TRUSTED = 1024;
  
  private final Context mContext;
  
  private final SparseArray<Display> mDisplays = new SparseArray();
  
  private final DisplayManagerGlobal mGlobal;
  
  private final Object mLock = new Object();
  
  private final ArrayList<Display> mTempDisplays = new ArrayList<>();
  
  public DisplayManager(Context paramContext) {
    this.mContext = paramContext;
    this.mGlobal = DisplayManagerGlobal.getInstance();
  }
  
  private void addAllDisplaysLocked(ArrayList<Display> paramArrayList, int[] paramArrayOfint) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      Display display = getOrCreateDisplayLocked(paramArrayOfint[b], true);
      if (display != null)
        paramArrayList.add(display); 
    } 
  }
  
  private void addPresentationDisplaysLocked(ArrayList<Display> paramArrayList, int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      Display display = getOrCreateDisplayLocked(paramArrayOfint[b], true);
      if (display != null && (display.getFlags() & 0x8) != 0 && display.getType() == paramInt)
        paramArrayList.add(display); 
    } 
  }
  
  private Display getOrCreateDisplayLocked(int paramInt, boolean paramBoolean) {
    Display display2;
    Display display1 = (Display)this.mDisplays.get(paramInt);
    if (display1 == null) {
      Resources resources;
      if (this.mContext.getDisplayId() == paramInt) {
        resources = this.mContext.getResources();
      } else {
        resources = null;
      } 
      display1 = this.mGlobal.getCompatibleDisplay(paramInt, resources);
      display2 = display1;
      if (display1 != null) {
        this.mDisplays.put(paramInt, display1);
        display2 = display1;
      } 
    } else {
      display2 = display1;
      if (!paramBoolean) {
        display2 = display1;
        if (!display1.isValid())
          display2 = null; 
      } 
    } 
    return display2;
  }
  
  public void connectWifiDisplay(String paramString) {
    this.mGlobal.connectWifiDisplay(paramString);
  }
  
  public VirtualDisplay createVirtualDisplay(MediaProjection paramMediaProjection, VirtualDisplayConfig paramVirtualDisplayConfig, VirtualDisplay.Callback paramCallback, Handler paramHandler) {
    return this.mGlobal.createVirtualDisplay(this.mContext, paramMediaProjection, paramVirtualDisplayConfig, paramCallback, paramHandler);
  }
  
  public VirtualDisplay createVirtualDisplay(MediaProjection paramMediaProjection, String paramString1, int paramInt1, int paramInt2, int paramInt3, Surface paramSurface, int paramInt4, VirtualDisplay.Callback paramCallback, Handler paramHandler, String paramString2) {
    VirtualDisplayConfig.Builder builder = new VirtualDisplayConfig.Builder(paramString1, paramInt1, paramInt2, paramInt3);
    builder.setFlags(paramInt4);
    if (paramString2 != null)
      builder.setUniqueId(paramString2); 
    if (paramSurface != null)
      builder.setSurface(paramSurface); 
    return createVirtualDisplay(paramMediaProjection, builder.build(), paramCallback, paramHandler);
  }
  
  public VirtualDisplay createVirtualDisplay(String paramString, int paramInt1, int paramInt2, int paramInt3, Surface paramSurface, int paramInt4) {
    return createVirtualDisplay(paramString, paramInt1, paramInt2, paramInt3, paramSurface, paramInt4, null, null);
  }
  
  public VirtualDisplay createVirtualDisplay(String paramString, int paramInt1, int paramInt2, int paramInt3, Surface paramSurface, int paramInt4, VirtualDisplay.Callback paramCallback, Handler paramHandler) {
    VirtualDisplayConfig.Builder builder = new VirtualDisplayConfig.Builder(paramString, paramInt1, paramInt2, paramInt3);
    builder.setFlags(paramInt4);
    if (paramSurface != null)
      builder.setSurface(paramSurface); 
    return createVirtualDisplay(null, builder.build(), paramCallback, paramHandler);
  }
  
  public void disconnectWifiDisplay() {
    this.mGlobal.disconnectWifiDisplay();
  }
  
  public void forgetWifiDisplay(String paramString) {
    this.mGlobal.forgetWifiDisplay(paramString);
  }
  
  @SystemApi
  public List<AmbientBrightnessDayStats> getAmbientBrightnessStats() {
    return this.mGlobal.getAmbientBrightnessStats();
  }
  
  @SystemApi
  public BrightnessConfiguration getBrightnessConfiguration() {
    return getBrightnessConfigurationForUser(this.mContext.getUserId());
  }
  
  public BrightnessConfiguration getBrightnessConfigurationForUser(int paramInt) {
    return this.mGlobal.getBrightnessConfigurationForUser(paramInt);
  }
  
  @SystemApi
  public List<BrightnessChangeEvent> getBrightnessEvents() {
    return this.mGlobal.getBrightnessEvents(this.mContext.getOpPackageName());
  }
  
  @SystemApi
  public BrightnessConfiguration getDefaultBrightnessConfiguration() {
    return this.mGlobal.getDefaultBrightnessConfiguration();
  }
  
  public Display getDisplay(int paramInt) {
    synchronized (this.mLock) {
      return getOrCreateDisplayLocked(paramInt, false);
    } 
  }
  
  public Display[] getDisplays() {
    return getDisplays(null);
  }
  
  public Display[] getDisplays(String paramString) {
    Display[] arrayOfDisplay;
    int[] arrayOfInt = this.mGlobal.getDisplayIds();
    Object object = this.mLock;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    if (paramString == null) {
      try {
        addAllDisplaysLocked(this.mTempDisplays, arrayOfInt);
        arrayOfDisplay = this.mTempDisplays.<Display>toArray(new Display[this.mTempDisplays.size()]);
      } finally {
        this.mTempDisplays.clear();
      } 
    } else {
      if (paramString.equals("android.hardware.display.category.PRESENTATION")) {
        addPresentationDisplaysLocked(this.mTempDisplays, arrayOfInt, 3);
        addPresentationDisplaysLocked(this.mTempDisplays, arrayOfInt, 2);
        addPresentationDisplaysLocked(this.mTempDisplays, arrayOfInt, 4);
        addPresentationDisplaysLocked(this.mTempDisplays, arrayOfInt, 5);
      } 
      arrayOfDisplay = this.mTempDisplays.<Display>toArray(new Display[this.mTempDisplays.size()]);
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    throw arrayOfDisplay;
  }
  
  @SystemApi
  public Pair<float[], float[]> getMinimumBrightnessCurve() {
    return this.mGlobal.getMinimumBrightnessCurve();
  }
  
  @SystemApi
  public Point getStableDisplaySize() {
    return this.mGlobal.getStableDisplaySize();
  }
  
  public WifiDisplayStatus getWifiDisplayStatus() {
    return this.mGlobal.getWifiDisplayStatus();
  }
  
  public boolean isMinimalPostProcessingRequested(int paramInt) {
    return this.mGlobal.isMinimalPostProcessingRequested(paramInt);
  }
  
  public void pauseWifiDisplay() {
    this.mGlobal.pauseWifiDisplay();
  }
  
  public void registerDisplayListener(DisplayListener paramDisplayListener, Handler paramHandler) {
    this.mGlobal.registerDisplayListener(paramDisplayListener, paramHandler);
  }
  
  public void renameWifiDisplay(String paramString1, String paramString2) {
    this.mGlobal.renameWifiDisplay(paramString1, paramString2);
  }
  
  public void resumeWifiDisplay() {
    this.mGlobal.resumeWifiDisplay();
  }
  
  @SystemApi
  public void setBrightnessConfiguration(BrightnessConfiguration paramBrightnessConfiguration) {
    setBrightnessConfigurationForUser(paramBrightnessConfiguration, this.mContext.getUserId(), this.mContext.getPackageName());
  }
  
  public void setBrightnessConfigurationForUser(BrightnessConfiguration paramBrightnessConfiguration, int paramInt, String paramString) {
    this.mGlobal.setBrightnessConfigurationForUser(paramBrightnessConfiguration, paramInt, paramString);
  }
  
  @SystemApi
  public void setSaturationLevel(float paramFloat) {
    if (paramFloat >= 0.0F && paramFloat <= 1.0F) {
      ((ColorDisplayManager)this.mContext.getSystemService(ColorDisplayManager.class)).setSaturationLevel(Math.round(100.0F * paramFloat));
      return;
    } 
    throw new IllegalArgumentException("Saturation level must be between 0 and 1");
  }
  
  public void setTemporaryAutoBrightnessAdjustment(float paramFloat) {
    this.mGlobal.setTemporaryAutoBrightnessAdjustment(paramFloat);
  }
  
  public void setTemporaryBrightness(float paramFloat) {
    this.mGlobal.setTemporaryBrightness(paramFloat);
  }
  
  public void startWifiDisplayScan() {
    this.mGlobal.startWifiDisplayScan();
  }
  
  public void stopWifiDisplayScan() {
    this.mGlobal.stopWifiDisplayScan();
  }
  
  public void unregisterDisplayListener(DisplayListener paramDisplayListener) {
    this.mGlobal.unregisterDisplayListener(paramDisplayListener);
  }
  
  public static interface DeviceConfig {
    public static final String KEY_HIGH_REFRESH_RATE_BLACKLIST = "high_refresh_rate_blacklist";
    
    public static final String KEY_PEAK_REFRESH_RATE_AMBIENT_BRIGHTNESS_THRESHOLDS = "peak_refresh_rate_ambient_thresholds";
    
    public static final String KEY_PEAK_REFRESH_RATE_DEFAULT = "peak_refresh_rate_default";
    
    public static final String KEY_PEAK_REFRESH_RATE_DISPLAY_BRIGHTNESS_THRESHOLDS = "peak_refresh_rate_brightness_thresholds";
    
    public static final String KEY_REFRESH_RATE_IN_ZONE = "refresh_rate_in_zone";
  }
  
  public static interface DisplayListener {
    void onDisplayAdded(int param1Int);
    
    void onDisplayChanged(int param1Int);
    
    void onDisplayRemoved(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */