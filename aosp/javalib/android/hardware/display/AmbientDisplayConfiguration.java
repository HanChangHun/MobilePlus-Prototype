package android.hardware.display;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;

public class AmbientDisplayConfiguration {
  private final boolean mAlwaysOnByDefault;
  
  private final Context mContext;
  
  public AmbientDisplayConfiguration(Context paramContext) {
    this.mContext = paramContext;
    this.mAlwaysOnByDefault = paramContext.getResources().getBoolean(17891424);
  }
  
  private boolean alwaysOnDisplayAvailable() {
    return this.mContext.getResources().getBoolean(17891423);
  }
  
  private boolean alwaysOnDisplayDebuggingEnabled() {
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (SystemProperties.getBoolean("debug.doze.aod", false)) {
      bool2 = bool1;
      if (Build.IS_DEBUGGABLE)
        bool2 = true; 
    } 
    return bool2;
  }
  
  private boolean boolSetting(String paramString, int paramInt1, int paramInt2) {
    boolean bool;
    if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), paramString, paramInt2, paramInt1) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean boolSettingDefaultOff(String paramString, int paramInt) {
    return boolSetting(paramString, paramInt, 0);
  }
  
  private boolean boolSettingDefaultOn(String paramString, int paramInt) {
    return boolSetting(paramString, paramInt, 1);
  }
  
  private boolean pulseOnLongPressAvailable() {
    return TextUtils.isEmpty(longPressSensorType()) ^ true;
  }
  
  public boolean accessibilityInversionEnabled(int paramInt) {
    return boolSettingDefaultOff("accessibility_display_inversion_enabled", paramInt);
  }
  
  public boolean alwaysOnAvailable() {
    boolean bool;
    if ((alwaysOnDisplayDebuggingEnabled() || alwaysOnDisplayAvailable()) && ambientDisplayAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean alwaysOnAvailableForUser(int paramInt) {
    boolean bool;
    if (alwaysOnAvailable() && !accessibilityInversionEnabled(paramInt)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean alwaysOnEnabled(int paramInt) {
    boolean bool;
    if (boolSetting("doze_always_on", paramInt, this.mAlwaysOnByDefault) && alwaysOnAvailable() && !accessibilityInversionEnabled(paramInt)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean ambientDisplayAvailable() {
    return TextUtils.isEmpty(ambientDisplayComponent()) ^ true;
  }
  
  public String ambientDisplayComponent() {
    return this.mContext.getResources().getString(17039885);
  }
  
  public boolean doubleTapGestureEnabled(int paramInt) {
    boolean bool;
    if (boolSettingDefaultOn("doze_pulse_on_double_tap", paramInt) && doubleTapSensorAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean doubleTapSensorAvailable() {
    return TextUtils.isEmpty(doubleTapSensorType()) ^ true;
  }
  
  public String doubleTapSensorType() {
    return this.mContext.getResources().getString(17039886);
  }
  
  public boolean dozePickupSensorAvailable() {
    return this.mContext.getResources().getBoolean(17891425);
  }
  
  public boolean dozeSuppressed(int paramInt) {
    return boolSettingDefaultOff("suppress_doze", paramInt);
  }
  
  public boolean enabled(int paramInt) {
    return (pulseOnNotificationEnabled(paramInt) || pulseOnLongPressEnabled(paramInt) || alwaysOnEnabled(paramInt) || wakeLockScreenGestureEnabled(paramInt) || wakeDisplayGestureEnabled(paramInt) || pickupGestureEnabled(paramInt) || tapGestureEnabled(paramInt) || doubleTapGestureEnabled(paramInt));
  }
  
  public long getWakeLockScreenDebounce() {
    return this.mContext.getResources().getInteger(17694804);
  }
  
  public String longPressSensorType() {
    return this.mContext.getResources().getString(17039887);
  }
  
  public boolean pickupGestureEnabled(int paramInt) {
    boolean bool;
    if (boolSettingDefaultOn("doze_pulse_on_pick_up", paramInt) && dozePickupSensorAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean pulseOnLongPressEnabled(int paramInt) {
    boolean bool;
    if (pulseOnLongPressAvailable() && boolSettingDefaultOff("doze_pulse_on_long_press", paramInt)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean pulseOnNotificationAvailable() {
    return ambientDisplayAvailable();
  }
  
  public boolean pulseOnNotificationEnabled(int paramInt) {
    boolean bool;
    if (boolSettingDefaultOn("doze_enabled", paramInt) && pulseOnNotificationAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean tapGestureEnabled(int paramInt) {
    boolean bool;
    if (boolSettingDefaultOn("doze_tap_gesture", paramInt) && tapSensorAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean tapSensorAvailable() {
    return TextUtils.isEmpty(tapSensorType()) ^ true;
  }
  
  public String tapSensorType() {
    return this.mContext.getResources().getString(17039888);
  }
  
  public boolean wakeDisplayGestureEnabled(int paramInt) {
    boolean bool;
    if (boolSettingDefaultOn("doze_wake_display_gesture", paramInt) && wakeScreenGestureAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean wakeLockScreenGestureEnabled(int paramInt) {
    boolean bool;
    if (boolSettingDefaultOn("doze_wake_screen_gesture", paramInt) && wakeScreenGestureAvailable()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean wakeScreenGestureAvailable() {
    return this.mContext.getResources().getBoolean(17891427);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/AmbientDisplayConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */