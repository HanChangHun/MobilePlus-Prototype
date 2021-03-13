package android.hardware.display;

import android.view.Display;

public final class DisplayPowerRequest {
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
  
  public DisplayPowerRequest(DisplayPowerRequest paramDisplayPowerRequest) {
    copyFrom(paramDisplayPowerRequest);
  }
  
  private boolean floatEquals(float paramFloat1, float paramFloat2) {
    return (paramFloat1 == paramFloat2 || (Float.isNaN(paramFloat1) && Float.isNaN(paramFloat2)));
  }
  
  public static String policyToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? Integer.toString(paramInt) : "VR") : "BRIGHT") : "DIM") : "DOZE") : "OFF";
  }
  
  public void copyFrom(DisplayPowerRequest paramDisplayPowerRequest) {
    this.policy = paramDisplayPowerRequest.policy;
    this.useProximitySensor = paramDisplayPowerRequest.useProximitySensor;
    this.screenBrightnessOverride = paramDisplayPowerRequest.screenBrightnessOverride;
    this.useAutoBrightness = paramDisplayPowerRequest.useAutoBrightness;
    this.screenAutoBrightnessAdjustmentOverride = paramDisplayPowerRequest.screenAutoBrightnessAdjustmentOverride;
    this.screenLowPowerBrightnessFactor = paramDisplayPowerRequest.screenLowPowerBrightnessFactor;
    this.blockScreenOn = paramDisplayPowerRequest.blockScreenOn;
    this.lowPowerMode = paramDisplayPowerRequest.lowPowerMode;
    this.boostScreenBrightness = paramDisplayPowerRequest.boostScreenBrightness;
    this.dozeScreenBrightness = paramDisplayPowerRequest.dozeScreenBrightness;
    this.dozeScreenState = paramDisplayPowerRequest.dozeScreenState;
  }
  
  public boolean equals(DisplayPowerRequest paramDisplayPowerRequest) {
    boolean bool;
    if (paramDisplayPowerRequest != null && this.policy == paramDisplayPowerRequest.policy && this.useProximitySensor == paramDisplayPowerRequest.useProximitySensor && floatEquals(this.screenBrightnessOverride, paramDisplayPowerRequest.screenBrightnessOverride) && this.useAutoBrightness == paramDisplayPowerRequest.useAutoBrightness && floatEquals(this.screenAutoBrightnessAdjustmentOverride, paramDisplayPowerRequest.screenAutoBrightnessAdjustmentOverride) && this.screenLowPowerBrightnessFactor == paramDisplayPowerRequest.screenLowPowerBrightnessFactor && this.blockScreenOn == paramDisplayPowerRequest.blockScreenOn && this.lowPowerMode == paramDisplayPowerRequest.lowPowerMode && this.boostScreenBrightness == paramDisplayPowerRequest.boostScreenBrightness && floatEquals(this.dozeScreenBrightness, paramDisplayPowerRequest.dozeScreenBrightness) && this.dozeScreenState == paramDisplayPowerRequest.dozeScreenState) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof DisplayPowerRequest && equals((DisplayPowerRequest)paramObject)) {
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerInternal$DisplayPowerRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */