package android.hardware.display;

import android.os.RemoteException;

class ColorDisplayManagerInternal {
  private static ColorDisplayManagerInternal sInstance;
  
  private final IColorDisplayManager mCdm;
  
  private ColorDisplayManagerInternal(IColorDisplayManager paramIColorDisplayManager) {
    this.mCdm = paramIColorDisplayManager;
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
  
  boolean setAppSaturationLevel(String paramString, int paramInt) {
    try {
      return this.mCdm.setAppSaturationLevel(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  void setColorMode(int paramInt) {
    try {
      this.mCdm.setColorMode(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setDisplayWhiteBalanceEnabled(boolean paramBoolean) {
    try {
      return this.mCdm.setDisplayWhiteBalanceEnabled(paramBoolean);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setNightDisplayActivated(boolean paramBoolean) {
    try {
      return this.mCdm.setNightDisplayActivated(paramBoolean);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setNightDisplayAutoMode(int paramInt) {
    try {
      return this.mCdm.setNightDisplayAutoMode(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setNightDisplayColorTemperature(int paramInt) {
    try {
      return this.mCdm.setNightDisplayColorTemperature(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setNightDisplayCustomEndTime(Time paramTime) {
    try {
      return this.mCdm.setNightDisplayCustomEndTime(paramTime);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setNightDisplayCustomStartTime(Time paramTime) {
    try {
      return this.mCdm.setNightDisplayCustomStartTime(paramTime);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  boolean setSaturationLevel(int paramInt) {
    try {
      return this.mCdm.setSaturationLevel(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/ColorDisplayManager$ColorDisplayManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */