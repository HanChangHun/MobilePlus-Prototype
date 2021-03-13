package android.hardware.display;

import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.IntArray;
import android.util.SparseArray;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.SurfaceControl;

public abstract class DisplayManagerInternal {
  public abstract DisplayInfo getDisplayInfo(int paramInt);
  
  public abstract Point getDisplayPosition(int paramInt);
  
  public abstract DisplayedContentSample getDisplayedContentSample(int paramInt, long paramLong1, long paramLong2);
  
  public abstract DisplayedContentSamplingAttributes getDisplayedContentSamplingAttributes(int paramInt);
  
  public abstract void getNonOverrideDisplayInfo(int paramInt, DisplayInfo paramDisplayInfo);
  
  public abstract void ignoreProximitySensorUntilChanged();
  
  public abstract void initPowerManagement(DisplayPowerCallbacks paramDisplayPowerCallbacks, Handler paramHandler, SensorManager paramSensorManager);
  
  public abstract boolean isProximitySensorAvailable();
  
  public abstract void onOverlayChanged();
  
  public abstract void performTraversal(SurfaceControl.Transaction paramTransaction);
  
  public abstract void persistBrightnessTrackerState();
  
  public abstract void registerDisplayTransactionListener(DisplayTransactionListener paramDisplayTransactionListener);
  
  public abstract boolean requestPowerState(DisplayPowerRequest paramDisplayPowerRequest, boolean paramBoolean);
  
  public abstract void setDisplayAccessUIDs(SparseArray<IntArray> paramSparseArray);
  
  public abstract void setDisplayInfoOverrideFromWindowManager(int paramInt, DisplayInfo paramDisplayInfo);
  
  public abstract void setDisplayOffsets(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void setDisplayProperties(int paramInt1, boolean paramBoolean1, float paramFloat, int paramInt2, boolean paramBoolean2, boolean paramBoolean3);
  
  public abstract void setDisplayScalingDisabled(int paramInt, boolean paramBoolean);
  
  public abstract boolean setDisplayedContentSamplingEnabled(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3);
  
  public abstract SurfaceControl.ScreenshotGraphicBuffer systemScreenshot(int paramInt);
  
  public abstract void unregisterDisplayTransactionListener(DisplayTransactionListener paramDisplayTransactionListener);
  
  public abstract SurfaceControl.ScreenshotGraphicBuffer userScreenshot(int paramInt);
  
  public static interface DisplayPowerCallbacks {
    void acquireSuspendBlocker();
    
    void onDisplayStateChange(int param1Int);
    
    void onProximityNegative();
    
    void onProximityPositive();
    
    void onStateChanged();
    
    void releaseSuspendBlocker();
  }
  
  public static final class DisplayPowerRequest {
    public static final int POLICY_BRIGHT = 3;
    
    public static final int POLICY_DIM = 2;
    
    public static final int POLICY_DOZE = 1;
    
    public static final int POLICY_OFF = 0;
    
    public static final int POLICY_VR = 4;
    
    public boolean blockScreenOn;
    
    public boolean boostScreenBrightness;
    
    public float dozeScreenBrightness;
    
    public int dozeScreenState;
    
    public boolean lowPowerMode;
    
    public int policy;
    
    public float screenAutoBrightnessAdjustmentOverride;
    
    public float screenBrightnessOverride;
    
    public float screenLowPowerBrightnessFactor;
    
    public boolean useAutoBrightness;
    
    public boolean useProximitySensor;
    
    public DisplayPowerRequest() {
      this.policy = 3;
      this.useProximitySensor = false;
      this.screenBrightnessOverride = Float.NaN;
      this.useAutoBrightness = false;
      this.screenAutoBrightnessAdjustmentOverride = Float.NaN;
      this.screenLowPowerBrightnessFactor = 0.5F;
      this.blockScreenOn = false;
      this.dozeScreenBrightness = Float.NaN;
      this.dozeScreenState = 0;
    }
    
    public DisplayPowerRequest(DisplayPowerRequest param1DisplayPowerRequest) {
      copyFrom(param1DisplayPowerRequest);
    }
    
    private boolean floatEquals(float param1Float1, float param1Float2) {
      return (param1Float1 == param1Float2 || (Float.isNaN(param1Float1) && Float.isNaN(param1Float2)));
    }
    
    public static String policyToString(int param1Int) {
      return (param1Int != 0) ? ((param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? Integer.toString(param1Int) : "VR") : "BRIGHT") : "DIM") : "DOZE") : "OFF";
    }
    
    public void copyFrom(DisplayPowerRequest param1DisplayPowerRequest) {
      this.policy = param1DisplayPowerRequest.policy;
      this.useProximitySensor = param1DisplayPowerRequest.useProximitySensor;
      this.screenBrightnessOverride = param1DisplayPowerRequest.screenBrightnessOverride;
      this.useAutoBrightness = param1DisplayPowerRequest.useAutoBrightness;
      this.screenAutoBrightnessAdjustmentOverride = param1DisplayPowerRequest.screenAutoBrightnessAdjustmentOverride;
      this.screenLowPowerBrightnessFactor = param1DisplayPowerRequest.screenLowPowerBrightnessFactor;
      this.blockScreenOn = param1DisplayPowerRequest.blockScreenOn;
      this.lowPowerMode = param1DisplayPowerRequest.lowPowerMode;
      this.boostScreenBrightness = param1DisplayPowerRequest.boostScreenBrightness;
      this.dozeScreenBrightness = param1DisplayPowerRequest.dozeScreenBrightness;
      this.dozeScreenState = param1DisplayPowerRequest.dozeScreenState;
    }
    
    public boolean equals(DisplayPowerRequest param1DisplayPowerRequest) {
      boolean bool;
      if (param1DisplayPowerRequest != null && this.policy == param1DisplayPowerRequest.policy && this.useProximitySensor == param1DisplayPowerRequest.useProximitySensor && floatEquals(this.screenBrightnessOverride, param1DisplayPowerRequest.screenBrightnessOverride) && this.useAutoBrightness == param1DisplayPowerRequest.useAutoBrightness && floatEquals(this.screenAutoBrightnessAdjustmentOverride, param1DisplayPowerRequest.screenAutoBrightnessAdjustmentOverride) && this.screenLowPowerBrightnessFactor == param1DisplayPowerRequest.screenLowPowerBrightnessFactor && this.blockScreenOn == param1DisplayPowerRequest.blockScreenOn && this.lowPowerMode == param1DisplayPowerRequest.lowPowerMode && this.boostScreenBrightness == param1DisplayPowerRequest.boostScreenBrightness && floatEquals(this.dozeScreenBrightness, param1DisplayPowerRequest.dozeScreenBrightness) && this.dozeScreenState == param1DisplayPowerRequest.dozeScreenState) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool;
      if (param1Object instanceof DisplayPowerRequest && equals((DisplayPowerRequest)param1Object)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      return 0;
    }
    
    public boolean isBrightOrDim() {
      int i = this.policy;
      return (i == 3 || i == 2);
    }
    
    public boolean isVr() {
      boolean bool;
      if (this.policy == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("policy=");
      stringBuilder.append(policyToString(this.policy));
      stringBuilder.append(", useProximitySensor=");
      stringBuilder.append(this.useProximitySensor);
      stringBuilder.append(", screenBrightnessOverride=");
      stringBuilder.append(this.screenBrightnessOverride);
      stringBuilder.append(", useAutoBrightness=");
      stringBuilder.append(this.useAutoBrightness);
      stringBuilder.append(", screenAutoBrightnessAdjustmentOverride=");
      stringBuilder.append(this.screenAutoBrightnessAdjustmentOverride);
      stringBuilder.append(", screenLowPowerBrightnessFactor=");
      stringBuilder.append(this.screenLowPowerBrightnessFactor);
      stringBuilder.append(", blockScreenOn=");
      stringBuilder.append(this.blockScreenOn);
      stringBuilder.append(", lowPowerMode=");
      stringBuilder.append(this.lowPowerMode);
      stringBuilder.append(", boostScreenBrightness=");
      stringBuilder.append(this.boostScreenBrightness);
      stringBuilder.append(", dozeScreenBrightness=");
      stringBuilder.append(this.dozeScreenBrightness);
      stringBuilder.append(", dozeScreenState=");
      stringBuilder.append(Display.stateToString(this.dozeScreenState));
      return stringBuilder.toString();
    }
  }
  
  public static interface DisplayTransactionListener {
    void onDisplayTransaction(SurfaceControl.Transaction param1Transaction);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */