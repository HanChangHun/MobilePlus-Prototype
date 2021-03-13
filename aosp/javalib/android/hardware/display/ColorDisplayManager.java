package android.hardware.display;

import android.annotation.SystemApi;
import android.content.ContentResolver;
import android.content.Context;
import android.metrics.LogMaker;
import android.os.RemoteException;
import android.provider.Settings;
import com.android.internal.logging.MetricsLogger;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalTime;

@SystemApi
public final class ColorDisplayManager {
  @SystemApi
  public static final int AUTO_MODE_CUSTOM_TIME = 1;
  
  @SystemApi
  public static final int AUTO_MODE_DISABLED = 0;
  
  @SystemApi
  public static final int AUTO_MODE_TWILIGHT = 2;
  
  @SystemApi
  public static final int CAPABILITY_HARDWARE_ACCELERATION_GLOBAL = 2;
  
  @SystemApi
  public static final int CAPABILITY_HARDWARE_ACCELERATION_PER_APP = 4;
  
  @SystemApi
  public static final int CAPABILITY_NONE = 0;
  
  @SystemApi
  public static final int CAPABILITY_PROTECTED_CONTENT = 1;
  
  public static final int COLOR_MODE_AUTOMATIC = 3;
  
  public static final int COLOR_MODE_BOOSTED = 1;
  
  public static final int COLOR_MODE_NATURAL = 0;
  
  public static final int COLOR_MODE_SATURATED = 2;
  
  public static final int VENDOR_COLOR_MODE_RANGE_MAX = 511;
  
  public static final int VENDOR_COLOR_MODE_RANGE_MIN = 256;
  
  private final ColorDisplayManagerInternal mManager = ColorDisplayManagerInternal.getInstance();
  
  private MetricsLogger mMetricsLogger;
  
  public static boolean areAccessibilityTransformsEnabled(Context paramContext) {
    ContentResolver contentResolver = paramContext.getContentResolver();
    boolean bool = false;
    if (Settings.Secure.getInt(contentResolver, "accessibility_display_inversion_enabled", 0) == 1 || Settings.Secure.getInt(contentResolver, "accessibility_display_daltonizer_enabled", 0) == 1)
      bool = true; 
    return bool;
  }
  
  public static int getMaximumColorTemperature(Context paramContext) {
    return paramContext.getResources().getInteger(17694861);
  }
  
  private MetricsLogger getMetricsLogger() {
    if (this.mMetricsLogger == null)
      this.mMetricsLogger = new MetricsLogger(); 
    return this.mMetricsLogger;
  }
  
  public static int getMinimumColorTemperature(Context paramContext) {
    return paramContext.getResources().getInteger(17694862);
  }
  
  public static boolean isColorTransformAccelerated(Context paramContext) {
    return paramContext.getResources().getBoolean(17891516);
  }
  
  public static boolean isDisplayWhiteBalanceAvailable(Context paramContext) {
    return paramContext.getResources().getBoolean(17891418);
  }
  
  public static boolean isNightDisplayAvailable(Context paramContext) {
    return paramContext.getResources().getBoolean(17891494);
  }
  
  public int getColorMode() {
    return this.mManager.getColorMode();
  }
  
  @SystemApi
  public int getNightDisplayAutoMode() {
    return this.mManager.getNightDisplayAutoMode();
  }
  
  public int getNightDisplayAutoModeRaw() {
    return this.mManager.getNightDisplayAutoModeRaw();
  }
  
  public int getNightDisplayColorTemperature() {
    return this.mManager.getNightDisplayColorTemperature();
  }
  
  public LocalTime getNightDisplayCustomEndTime() {
    return this.mManager.getNightDisplayCustomEndTime().getLocalTime();
  }
  
  public LocalTime getNightDisplayCustomStartTime() {
    return this.mManager.getNightDisplayCustomStartTime().getLocalTime();
  }
  
  @SystemApi
  public int getTransformCapabilities() {
    return this.mManager.getTransformCapabilities();
  }
  
  public boolean isDeviceColorManaged() {
    return this.mManager.isDeviceColorManaged();
  }
  
  public boolean isDisplayWhiteBalanceEnabled() {
    return this.mManager.isDisplayWhiteBalanceEnabled();
  }
  
  public boolean isNightDisplayActivated() {
    return this.mManager.isNightDisplayActivated();
  }
  
  public boolean isSaturationActivated() {
    return this.mManager.isSaturationActivated();
  }
  
  @SystemApi
  public boolean setAppSaturationLevel(String paramString, int paramInt) {
    return this.mManager.setAppSaturationLevel(paramString, paramInt);
  }
  
  public void setColorMode(int paramInt) {
    this.mManager.setColorMode(paramInt);
  }
  
  public boolean setDisplayWhiteBalanceEnabled(boolean paramBoolean) {
    return this.mManager.setDisplayWhiteBalanceEnabled(paramBoolean);
  }
  
  public boolean setNightDisplayActivated(boolean paramBoolean) {
    return this.mManager.setNightDisplayActivated(paramBoolean);
  }
  
  @SystemApi
  public boolean setNightDisplayAutoMode(int paramInt) {
    if (paramInt == 0 || paramInt == 1 || paramInt == 2) {
      if (this.mManager.getNightDisplayAutoMode() != paramInt)
        getMetricsLogger().write((new LogMaker(1309)).setType(4).setSubtype(paramInt)); 
      return this.mManager.setNightDisplayAutoMode(paramInt);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid autoMode: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean setNightDisplayColorTemperature(int paramInt) {
    return this.mManager.setNightDisplayColorTemperature(paramInt);
  }
  
  @SystemApi
  public boolean setNightDisplayCustomEndTime(LocalTime paramLocalTime) {
    if (paramLocalTime != null) {
      getMetricsLogger().write((new LogMaker(1310)).setType(4).setSubtype(1));
      return this.mManager.setNightDisplayCustomEndTime(new Time(paramLocalTime));
    } 
    throw new IllegalArgumentException("endTime cannot be null");
  }
  
  @SystemApi
  public boolean setNightDisplayCustomStartTime(LocalTime paramLocalTime) {
    if (paramLocalTime != null) {
      getMetricsLogger().write((new LogMaker(1310)).setType(4).setSubtype(0));
      return this.mManager.setNightDisplayCustomStartTime(new Time(paramLocalTime));
    } 
    throw new IllegalArgumentException("startTime cannot be null");
  }
  
  @SystemApi
  public boolean setSaturationLevel(int paramInt) {
    return this.mManager.setSaturationLevel(paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AutoMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CapabilityType {}
  
  private static class ColorDisplayManagerInternal {
    private static ColorDisplayManagerInternal sInstance;
    
    private final IColorDisplayManager mCdm;
    
    private ColorDisplayManagerInternal(IColorDisplayManager param1IColorDisplayManager) {
      this.mCdm = param1IColorDisplayManager;
    }
    
    public static ColorDisplayManagerInternal getInstance() {
      // Byte code:
      //   0: ldc android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal
      //   2: monitorenter
      //   3: getstatic android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal.sInstance : Landroid/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal;
      //   6: astore_0
      //   7: aload_0
      //   8: ifnonnull -> 48
      //   11: ldc 'color_display'
      //   13: invokestatic getServiceOrThrow : (Ljava/lang/String;)Landroid/os/IBinder;
      //   16: astore_0
      //   17: new android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal
      //   20: astore_1
      //   21: aload_1
      //   22: aload_0
      //   23: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/hardware/display/IColorDisplayManager;
      //   26: invokespecial <init> : (Landroid/hardware/display/IColorDisplayManager;)V
      //   29: aload_1
      //   30: putstatic android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal.sInstance : Landroid/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal;
      //   33: goto -> 48
      //   36: astore_1
      //   37: new java/lang/IllegalStateException
      //   40: astore_0
      //   41: aload_0
      //   42: aload_1
      //   43: invokespecial <init> : (Ljava/lang/Throwable;)V
      //   46: aload_0
      //   47: athrow
      //   48: getstatic android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal.sInstance : Landroid/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal;
      //   51: astore_0
      //   52: ldc android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal
      //   54: monitorexit
      //   55: aload_0
      //   56: areturn
      //   57: astore_0
      //   58: ldc android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal
      //   60: monitorexit
      //   61: aload_0
      //   62: athrow
      // Exception table:
      //   from	to	target	type
      //   3	7	57	finally
      //   11	33	36	android/os/ServiceManager$ServiceNotFoundException
      //   11	33	57	finally
      //   37	48	57	finally
      //   48	55	57	finally
      //   58	61	57	finally
    }
    
    int getColorMode() {
      try {
        return this.mCdm.getColorMode();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    int getNightDisplayAutoMode() {
      try {
        return this.mCdm.getNightDisplayAutoMode();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    int getNightDisplayAutoModeRaw() {
      try {
        return this.mCdm.getNightDisplayAutoModeRaw();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    int getNightDisplayColorTemperature() {
      try {
        return this.mCdm.getNightDisplayColorTemperature();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    Time getNightDisplayCustomEndTime() {
      try {
        return this.mCdm.getNightDisplayCustomEndTime();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    Time getNightDisplayCustomStartTime() {
      try {
        return this.mCdm.getNightDisplayCustomStartTime();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    int getTransformCapabilities() {
      try {
        return this.mCdm.getTransformCapabilities();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean isDeviceColorManaged() {
      try {
        return this.mCdm.isDeviceColorManaged();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean isDisplayWhiteBalanceEnabled() {
      try {
        return this.mCdm.isDisplayWhiteBalanceEnabled();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean isNightDisplayActivated() {
      try {
        return this.mCdm.isNightDisplayActivated();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean isSaturationActivated() {
      try {
        return this.mCdm.isSaturationActivated();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setAppSaturationLevel(String param1String, int param1Int) {
      try {
        return this.mCdm.setAppSaturationLevel(param1String, param1Int);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    void setColorMode(int param1Int) {
      try {
        this.mCdm.setColorMode(param1Int);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setDisplayWhiteBalanceEnabled(boolean param1Boolean) {
      try {
        return this.mCdm.setDisplayWhiteBalanceEnabled(param1Boolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setNightDisplayActivated(boolean param1Boolean) {
      try {
        return this.mCdm.setNightDisplayActivated(param1Boolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setNightDisplayAutoMode(int param1Int) {
      try {
        return this.mCdm.setNightDisplayAutoMode(param1Int);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setNightDisplayColorTemperature(int param1Int) {
      try {
        return this.mCdm.setNightDisplayColorTemperature(param1Int);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setNightDisplayCustomEndTime(Time param1Time) {
      try {
        return this.mCdm.setNightDisplayCustomEndTime(param1Time);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setNightDisplayCustomStartTime(Time param1Time) {
      try {
        return this.mCdm.setNightDisplayCustomStartTime(param1Time);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    boolean setSaturationLevel(int param1Int) {
      try {
        return this.mCdm.setSaturationLevel(param1Int);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ColorMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/ColorDisplayManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */