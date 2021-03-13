package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalTime;

public class UiModeManager {
  public static String ACTION_ENTER_CAR_MODE = "android.app.action.ENTER_CAR_MODE";
  
  @SystemApi
  public static final String ACTION_ENTER_CAR_MODE_PRIORITIZED = "android.app.action.ENTER_CAR_MODE_PRIORITIZED";
  
  public static String ACTION_ENTER_DESK_MODE;
  
  public static String ACTION_EXIT_CAR_MODE = "android.app.action.EXIT_CAR_MODE";
  
  @SystemApi
  public static final String ACTION_EXIT_CAR_MODE_PRIORITIZED = "android.app.action.EXIT_CAR_MODE_PRIORITIZED";
  
  public static String ACTION_EXIT_DESK_MODE;
  
  @SystemApi
  public static final int DEFAULT_PRIORITY = 0;
  
  public static final int DISABLE_CAR_MODE_ALL_PRIORITIES = 2;
  
  public static final int DISABLE_CAR_MODE_GO_HOME = 1;
  
  public static final int ENABLE_CAR_MODE_ALLOW_SLEEP = 2;
  
  public static final int ENABLE_CAR_MODE_GO_CAR_HOME = 1;
  
  @SystemApi
  public static final String EXTRA_CALLING_PACKAGE = "android.app.extra.CALLING_PACKAGE";
  
  @SystemApi
  public static final String EXTRA_PRIORITY = "android.app.extra.PRIORITY";
  
  public static final int MODE_NIGHT_AUTO = 0;
  
  public static final int MODE_NIGHT_CUSTOM = 3;
  
  public static final int MODE_NIGHT_NO = 1;
  
  public static final int MODE_NIGHT_YES = 2;
  
  private static final String TAG = "UiModeManager";
  
  private Context mContext;
  
  private IUiModeManager mService = IUiModeManager.Stub.asInterface(ServiceManager.getServiceOrThrow("uimode"));
  
  static {
    ACTION_ENTER_DESK_MODE = "android.app.action.ENTER_DESK_MODE";
    ACTION_EXIT_DESK_MODE = "android.app.action.EXIT_DESK_MODE";
  }
  
  UiModeManager() throws ServiceManager.ServiceNotFoundException {
    this(null);
  }
  
  UiModeManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
  }
  
  public void disableCarMode(int paramInt) {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        String str;
        if (this.mContext == null) {
          str = null;
        } else {
          str = this.mContext.getOpPackageName();
        } 
        iUiModeManager.disableCarModeByCallingPackage(paramInt, str);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void enableCarMode(int paramInt) {
    enableCarMode(0, paramInt);
  }
  
  @SystemApi
  public void enableCarMode(int paramInt1, int paramInt2) {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        String str;
        if (this.mContext == null) {
          str = null;
        } else {
          str = this.mContext.getOpPackageName();
        } 
        iUiModeManager.enableCarMode(paramInt2, paramInt1, str);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public int getCurrentModeType() {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return iUiModeManager.getCurrentModeType();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return 1;
  }
  
  public LocalTime getCustomNightModeEnd() {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return LocalTime.ofNanoOfDay(iUiModeManager.getCustomNightModeEnd() * 1000L);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return LocalTime.MIDNIGHT;
  }
  
  public LocalTime getCustomNightModeStart() {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return LocalTime.ofNanoOfDay(iUiModeManager.getCustomNightModeStart() * 1000L);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return LocalTime.MIDNIGHT;
  }
  
  public int getNightMode() {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return iUiModeManager.getNightMode();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return -1;
  }
  
  public boolean isNightModeLocked() {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return iUiModeManager.isNightModeLocked();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  public boolean isUiModeLocked() {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return iUiModeManager.isUiModeLocked();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return true;
  }
  
  public void setCustomNightModeEnd(LocalTime paramLocalTime) {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        iUiModeManager.setCustomNightModeEnd(paramLocalTime.toNanoOfDay() / 1000L);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setCustomNightModeStart(LocalTime paramLocalTime) {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        iUiModeManager.setCustomNightModeStart(paramLocalTime.toNanoOfDay() / 1000L);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setNightMode(int paramInt) {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        iUiModeManager.setNightMode(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean setNightModeActivated(boolean paramBoolean) {
    IUiModeManager iUiModeManager = this.mService;
    if (iUiModeManager != null)
      try {
        return iUiModeManager.setNightModeActivated(paramBoolean);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DisableCarMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EnableCarMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface NightMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiModeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */