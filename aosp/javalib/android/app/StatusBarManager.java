package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Pair;
import com.android.internal.statusbar.IStatusBarService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class StatusBarManager {
  public static final int CAMERA_LAUNCH_SOURCE_LIFT_TRIGGER = 2;
  
  public static final int CAMERA_LAUNCH_SOURCE_POWER_DOUBLE_TAP = 1;
  
  public static final int CAMERA_LAUNCH_SOURCE_WIGGLE = 0;
  
  public static final int DEFAULT_SETUP_DISABLE2_FLAGS = 16;
  
  public static final int DEFAULT_SETUP_DISABLE_FLAGS = 61145088;
  
  private static final int DEFAULT_SIM_LOCKED_DISABLED_FLAGS = 65536;
  
  public static final int DISABLE2_GLOBAL_ACTIONS = 8;
  
  public static final int DISABLE2_MASK = 31;
  
  public static final int DISABLE2_NONE = 0;
  
  public static final int DISABLE2_NOTIFICATION_SHADE = 4;
  
  public static final int DISABLE2_QUICK_SETTINGS = 1;
  
  public static final int DISABLE2_ROTATE_SUGGESTIONS = 16;
  
  public static final int DISABLE2_SYSTEM_ICONS = 2;
  
  public static final int DISABLE_BACK = 4194304;
  
  public static final int DISABLE_CLOCK = 8388608;
  
  public static final int DISABLE_EXPAND = 65536;
  
  public static final int DISABLE_HOME = 2097152;
  
  public static final int DISABLE_MASK = 67043328;
  
  @Deprecated
  public static final int DISABLE_NAVIGATION = 18874368;
  
  public static final int DISABLE_NONE = 0;
  
  public static final int DISABLE_NOTIFICATION_ALERTS = 262144;
  
  public static final int DISABLE_NOTIFICATION_ICONS = 131072;
  
  @Deprecated
  public static final int DISABLE_NOTIFICATION_TICKER = 524288;
  
  public static final int DISABLE_RECENT = 16777216;
  
  public static final int DISABLE_SEARCH = 33554432;
  
  public static final int DISABLE_SYSTEM_INFO = 1048576;
  
  public static final int NAVIGATION_HINT_BACK_ALT = 1;
  
  public static final int NAVIGATION_HINT_IME_SHOWN = 2;
  
  public static final int WINDOW_NAVIGATION_BAR = 2;
  
  public static final int WINDOW_STATE_HIDDEN = 2;
  
  public static final int WINDOW_STATE_HIDING = 1;
  
  public static final int WINDOW_STATE_SHOWING = 0;
  
  public static final int WINDOW_STATUS_BAR = 1;
  
  private Context mContext;
  
  private IStatusBarService mService;
  
  private IBinder mToken = (IBinder)new Binder();
  
  StatusBarManager(Context paramContext) {
    this.mContext = paramContext;
  }
  
  private IStatusBarService getService() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mService : Lcom/android/internal/statusbar/IStatusBarService;
    //   6: ifnonnull -> 35
    //   9: ldc 'statusbar'
    //   11: invokestatic getService : (Ljava/lang/String;)Landroid/os/IBinder;
    //   14: invokestatic asInterface : (Landroid/os/IBinder;)Lcom/android/internal/statusbar/IStatusBarService;
    //   17: astore_1
    //   18: aload_0
    //   19: aload_1
    //   20: putfield mService : Lcom/android/internal/statusbar/IStatusBarService;
    //   23: aload_1
    //   24: ifnonnull -> 35
    //   27: ldc 'StatusBarManager'
    //   29: ldc 'warning: no STATUS_BAR_SERVICE'
    //   31: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   34: pop
    //   35: aload_0
    //   36: getfield mService : Lcom/android/internal/statusbar/IStatusBarService;
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: areturn
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	44	finally
    //   27	35	44	finally
    //   35	40	44	finally
  }
  
  public static String windowStateToString(int paramInt) {
    return (paramInt == 1) ? "WINDOW_STATE_HIDING" : ((paramInt == 2) ? "WINDOW_STATE_HIDDEN" : ((paramInt == 0) ? "WINDOW_STATE_SHOWING" : "WINDOW_STATE_UNKNOWN"));
  }
  
  public void collapsePanels() {
    try {
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.collapsePanels(); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disable(int paramInt) {
    try {
      int i = Binder.getCallingUserHandle().getIdentifier();
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.disableForUser(paramInt, this.mToken, this.mContext.getPackageName(), i); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disable2(int paramInt) {
    try {
      int i = Binder.getCallingUserHandle().getIdentifier();
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.disable2ForUser(paramInt, this.mToken, this.mContext.getPackageName(), i); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void expandNotificationsPanel() {
    try {
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.expandNotificationsPanel(); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void expandSettingsPanel() {
    expandSettingsPanel(null);
  }
  
  public void expandSettingsPanel(String paramString) {
    try {
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.expandSettingsPanel(paramString); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public DisableInfo getDisableInfo() {
    try {
      int i = Binder.getCallingUserHandle().getIdentifier();
      IStatusBarService iStatusBarService = getService();
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      if (iStatusBarService != null)
        arrayOfInt = iStatusBarService.getDisableFlags(this.mToken, i); 
      return new DisableInfo(arrayOfInt[0], arrayOfInt[1]);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeIcon(String paramString) {
    try {
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.removeIcon(paramString); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setDisabledForSetup(boolean paramBoolean) {
    try {
      int i = Binder.getCallingUserHandle().getIdentifier();
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null) {
        byte b1 = 0;
        if (paramBoolean) {
          b2 = 61145088;
        } else {
          b2 = 0;
        } 
        iStatusBarService.disableForUser(b2, this.mToken, this.mContext.getPackageName(), i);
        byte b2 = b1;
        if (paramBoolean)
          b2 = 16; 
        iStatusBarService.disable2ForUser(b2, this.mToken, this.mContext.getPackageName(), i);
      } 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setDisabledForSimNetworkLock(boolean paramBoolean) {
    try {
      int i = Binder.getCallingUserHandle().getIdentifier();
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null) {
        boolean bool;
        if (paramBoolean) {
          bool = true;
        } else {
          bool = false;
        } 
        iStatusBarService.disableForUser(bool, this.mToken, this.mContext.getPackageName(), i);
      } 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setIcon(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    try {
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.setIcon(paramString1, this.mContext.getPackageName(), paramInt1, paramInt2, paramString2); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setIconVisibility(String paramString, boolean paramBoolean) {
    try {
      IStatusBarService iStatusBarService = getService();
      if (iStatusBarService != null)
        iStatusBarService.setIconVisibility(paramString, paramBoolean); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Disable2Flags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DisableFlags {}
  
  @SystemApi
  public static final class DisableInfo {
    private boolean mClock;
    
    private boolean mNavigateHome;
    
    private boolean mNotificationIcons;
    
    private boolean mNotificationPeeking;
    
    private boolean mRecents;
    
    private boolean mSearch;
    
    private boolean mStatusBarExpansion;
    
    private boolean mSystemIcons;
    
    public DisableInfo() {}
    
    public DisableInfo(int param1Int1, int param1Int2) {
      boolean bool2;
      boolean bool1 = true;
      if ((0x10000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mStatusBarExpansion = bool2;
      if ((0x200000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mNavigateHome = bool2;
      if ((0x40000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mNotificationPeeking = bool2;
      if ((0x1000000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mRecents = bool2;
      if ((0x2000000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mSearch = bool2;
      if ((0x100000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mSystemIcons = bool2;
      if ((0x800000 & param1Int1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mClock = bool2;
      if ((0x20000 & param1Int1) != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.mNotificationIcons = bool2;
    }
    
    public boolean areAllComponentsDisabled() {
      boolean bool;
      if (this.mStatusBarExpansion && this.mNavigateHome && this.mNotificationPeeking && this.mRecents && this.mSearch && this.mSystemIcons && this.mClock && this.mNotificationIcons) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @SystemApi
    public boolean areAllComponentsEnabled() {
      boolean bool;
      if (!this.mStatusBarExpansion && !this.mNavigateHome && !this.mNotificationPeeking && !this.mRecents && !this.mSearch && !this.mSystemIcons && !this.mClock && !this.mNotificationIcons) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean areNotificationIconsDisabled() {
      return this.mNotificationIcons;
    }
    
    public boolean areSystemIconsDisabled() {
      return this.mSystemIcons;
    }
    
    public boolean isClockDisabled() {
      return this.mClock;
    }
    
    @SystemApi
    public boolean isNavigateToHomeDisabled() {
      return this.mNavigateHome;
    }
    
    @SystemApi
    public boolean isNotificationPeekingDisabled() {
      return this.mNotificationPeeking;
    }
    
    @SystemApi
    public boolean isRecentsDisabled() {
      return this.mRecents;
    }
    
    @SystemApi
    public boolean isSearchDisabled() {
      return this.mSearch;
    }
    
    @SystemApi
    public boolean isStatusBarExpansionDisabled() {
      return this.mStatusBarExpansion;
    }
    
    public void setClockDisabled(boolean param1Boolean) {
      this.mClock = param1Boolean;
    }
    
    public void setDisableAll() {
      this.mStatusBarExpansion = true;
      this.mNavigateHome = true;
      this.mNotificationPeeking = true;
      this.mRecents = true;
      this.mSearch = true;
      this.mSystemIcons = true;
      this.mClock = true;
      this.mNotificationIcons = true;
    }
    
    public void setEnableAll() {
      this.mStatusBarExpansion = false;
      this.mNavigateHome = false;
      this.mNotificationPeeking = false;
      this.mRecents = false;
      this.mSearch = false;
      this.mSystemIcons = false;
      this.mClock = false;
      this.mNotificationIcons = false;
    }
    
    public void setNagivationHomeDisabled(boolean param1Boolean) {
      this.mNavigateHome = param1Boolean;
    }
    
    public void setNotificationIconsDisabled(boolean param1Boolean) {
      this.mNotificationIcons = param1Boolean;
    }
    
    public void setNotificationPeekingDisabled(boolean param1Boolean) {
      this.mNotificationPeeking = param1Boolean;
    }
    
    public void setRecentsDisabled(boolean param1Boolean) {
      this.mRecents = param1Boolean;
    }
    
    public void setSearchDisabled(boolean param1Boolean) {
      this.mSearch = param1Boolean;
    }
    
    public void setStatusBarExpansionDisabled(boolean param1Boolean) {
      this.mStatusBarExpansion = param1Boolean;
    }
    
    public void setSystemIconsDisabled(boolean param1Boolean) {
      this.mSystemIcons = param1Boolean;
    }
    
    public Pair<Integer, Integer> toFlags() {
      int i = 0;
      if (this.mStatusBarExpansion)
        i = 0x0 | 0x10000; 
      int j = i;
      if (this.mNavigateHome)
        j = i | 0x200000; 
      i = j;
      if (this.mNotificationPeeking)
        i = j | 0x40000; 
      j = i;
      if (this.mRecents)
        j = i | 0x1000000; 
      i = j;
      if (this.mSearch)
        i = j | 0x2000000; 
      j = i;
      if (this.mSystemIcons)
        j = i | 0x100000; 
      i = j;
      if (this.mClock)
        i = j | 0x800000; 
      j = i;
      if (this.mNotificationIcons)
        j = i | 0x20000; 
      return new Pair(Integer.valueOf(j), Integer.valueOf(0));
    }
    
    public String toString() {
      String str2;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("DisableInfo: ");
      stringBuilder.append(" mStatusBarExpansion=");
      boolean bool = this.mStatusBarExpansion;
      String str1 = "disabled";
      if (bool) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mNavigateHome=");
      if (this.mNavigateHome) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mNotificationPeeking=");
      if (this.mNotificationPeeking) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mRecents=");
      if (this.mRecents) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mSearch=");
      if (this.mSearch) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mSystemIcons=");
      if (this.mSystemIcons) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mClock=");
      if (this.mClock) {
        str2 = "disabled";
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      stringBuilder.append(" mNotificationIcons=");
      if (this.mNotificationIcons) {
        str2 = str1;
      } else {
        str2 = "enabled";
      } 
      stringBuilder.append(str2);
      return stringBuilder.toString();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface WindowType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface WindowVisibleState {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/StatusBarManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */