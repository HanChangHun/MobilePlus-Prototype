package android.app.usage;

import android.annotation.SystemApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.PowerWhitelistManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class UsageStatsManager {
  @SystemApi
  public static final String EXTRA_OBSERVER_ID = "android.app.usage.extra.OBSERVER_ID";
  
  @SystemApi
  public static final String EXTRA_TIME_LIMIT = "android.app.usage.extra.TIME_LIMIT";
  
  @SystemApi
  public static final String EXTRA_TIME_USED = "android.app.usage.extra.TIME_USED";
  
  public static final int INTERVAL_BEST = 4;
  
  public static final int INTERVAL_COUNT = 4;
  
  public static final int INTERVAL_DAILY = 0;
  
  public static final int INTERVAL_MONTHLY = 2;
  
  public static final int INTERVAL_WEEKLY = 1;
  
  public static final int INTERVAL_YEARLY = 3;
  
  public static final int REASON_MAIN_DEFAULT = 256;
  
  public static final int REASON_MAIN_FORCED_BY_SYSTEM = 1536;
  
  public static final int REASON_MAIN_FORCED_BY_USER = 1024;
  
  public static final int REASON_MAIN_MASK = 65280;
  
  public static final int REASON_MAIN_PREDICTED = 1280;
  
  public static final int REASON_MAIN_TIMEOUT = 512;
  
  public static final int REASON_MAIN_USAGE = 768;
  
  public static final int REASON_SUB_DEFAULT_APP_UPDATE = 1;
  
  public static final int REASON_SUB_DEFAULT_UNDEFINED = 0;
  
  public static final int REASON_SUB_FORCED_SYSTEM_FLAG_ABUSE = 2;
  
  public static final int REASON_SUB_FORCED_SYSTEM_FLAG_BACKGROUND_RESOURCE_USAGE = 1;
  
  public static final int REASON_SUB_FORCED_SYSTEM_FLAG_BUGGY = 4;
  
  public static final int REASON_SUB_FORCED_SYSTEM_FLAG_UNDEFINED = 0;
  
  public static final int REASON_SUB_MASK = 255;
  
  public static final int REASON_SUB_PREDICTED_RESTORED = 1;
  
  public static final int REASON_SUB_USAGE_ACTIVE_TIMEOUT = 7;
  
  public static final int REASON_SUB_USAGE_EXEMPTED_SYNC_SCHEDULED_DOZE = 12;
  
  public static final int REASON_SUB_USAGE_EXEMPTED_SYNC_SCHEDULED_NON_DOZE = 11;
  
  public static final int REASON_SUB_USAGE_EXEMPTED_SYNC_START = 13;
  
  public static final int REASON_SUB_USAGE_FOREGROUND_SERVICE_START = 15;
  
  public static final int REASON_SUB_USAGE_MOVE_TO_BACKGROUND = 5;
  
  public static final int REASON_SUB_USAGE_MOVE_TO_FOREGROUND = 4;
  
  public static final int REASON_SUB_USAGE_NOTIFICATION_SEEN = 2;
  
  public static final int REASON_SUB_USAGE_SLICE_PINNED = 9;
  
  public static final int REASON_SUB_USAGE_SLICE_PINNED_PRIV = 10;
  
  public static final int REASON_SUB_USAGE_SYNC_ADAPTER = 8;
  
  public static final int REASON_SUB_USAGE_SYSTEM_INTERACTION = 1;
  
  public static final int REASON_SUB_USAGE_SYSTEM_UPDATE = 6;
  
  public static final int REASON_SUB_USAGE_UNEXEMPTED_SYNC_SCHEDULED = 14;
  
  public static final int REASON_SUB_USAGE_USER_INTERACTION = 3;
  
  public static final int STANDBY_BUCKET_ACTIVE = 10;
  
  @SystemApi
  public static final int STANDBY_BUCKET_EXEMPTED = 5;
  
  public static final int STANDBY_BUCKET_FREQUENT = 30;
  
  @SystemApi
  public static final int STANDBY_BUCKET_NEVER = 50;
  
  public static final int STANDBY_BUCKET_RARE = 40;
  
  public static final int STANDBY_BUCKET_RESTRICTED = 45;
  
  public static final int STANDBY_BUCKET_WORKING_SET = 20;
  
  @SystemApi
  public static final int USAGE_SOURCE_CURRENT_ACTIVITY = 2;
  
  @SystemApi
  public static final int USAGE_SOURCE_TASK_ROOT_ACTIVITY = 1;
  
  private static final UsageEvents sEmptyResults = new UsageEvents();
  
  private final Context mContext;
  
  private final IUsageStatsManager mService;
  
  public UsageStatsManager(Context paramContext, IUsageStatsManager paramIUsageStatsManager) {
    this.mContext = paramContext;
    this.mService = paramIUsageStatsManager;
  }
  
  public static String reasonToString(int paramInt) {
    int i = paramInt & 0xFF;
    StringBuilder stringBuilder = new StringBuilder();
    paramInt = 0xFF00 & paramInt;
    if (paramInt != 256) {
      if (paramInt != 512) {
        if (paramInt != 768)
          if (paramInt != 1024) {
            if (paramInt != 1280) {
              if (paramInt == 1536) {
                stringBuilder.append("s");
                if (i > 0) {
                  stringBuilder.append("-");
                  stringBuilder.append(Integer.toBinaryString(i));
                } 
              } 
            } else {
              stringBuilder.append("p");
              if (i == 1)
                stringBuilder.append("-r"); 
            } 
          } else {
            stringBuilder.append("f");
          }  
        stringBuilder.append("u");
        switch (i) {
          default:
            return stringBuilder.toString();
          case 15:
            stringBuilder.append("-fss");
          case 14:
            stringBuilder.append("-uss");
          case 13:
            stringBuilder.append("-es");
          case 12:
            stringBuilder.append("-ed");
          case 11:
            stringBuilder.append("-en");
          case 10:
            stringBuilder.append("-lv");
          case 9:
            stringBuilder.append("-lp");
          case 8:
            stringBuilder.append("-sa");
          case 7:
            stringBuilder.append("-at");
          case 6:
            stringBuilder.append("-su");
          case 5:
            stringBuilder.append("-mb");
          case 4:
            stringBuilder.append("-mf");
          case 3:
            stringBuilder.append("-ui");
          case 2:
            stringBuilder.append("-ns");
          case 1:
            break;
        } 
        stringBuilder.append("-si");
      } 
      stringBuilder.append("t");
    } 
    stringBuilder.append("d");
    if (i != 1);
    stringBuilder.append("-au");
  }
  
  public static String usageSourceToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UNKNOWN(");
        stringBuilder.append(paramInt);
        stringBuilder.append(")");
        return stringBuilder.toString();
      } 
      return "CURRENT_ACTIVITY";
    } 
    return "TASK_ROOT_ACTIVITY";
  }
  
  public void forceUsageSourceSettingRead() {
    try {
      this.mService.forceUsageSourceSettingRead();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getAppStandbyBucket() {
    try {
      return this.mService.getAppStandbyBucket(this.mContext.getOpPackageName(), this.mContext.getOpPackageName(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      return 10;
    } 
  }
  
  @SystemApi
  public int getAppStandbyBucket(String paramString) {
    try {
      return this.mService.getAppStandbyBucket(paramString, this.mContext.getOpPackageName(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      return 10;
    } 
  }
  
  @SystemApi
  public Map<String, Integer> getAppStandbyBuckets() {
    try {
      List<AppStandbyInfo> list = this.mService.getAppStandbyBuckets(this.mContext.getOpPackageName(), this.mContext.getUserId()).getList();
      ArrayMap arrayMap = new ArrayMap();
      this();
      int i = list.size();
      for (byte b = 0; b < i; b++) {
        AppStandbyInfo appStandbyInfo = list.get(b);
        arrayMap.put(appStandbyInfo.mPackageName, Integer.valueOf(appStandbyInfo.mStandbyBucket));
      } 
      return (Map<String, Integer>)arrayMap;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public int getUsageSource() {
    try {
      return this.mService.getUsageSource();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isAppInactive(String paramString) {
    try {
      return this.mService.isAppInactive(paramString, this.mContext.getUserId(), this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  @SystemApi
  public void onCarrierPrivilegedAppsChanged() {
    try {
      this.mService.onCarrierPrivilegedAppsChanged();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Map<String, UsageStats> queryAndAggregateUsageStats(long paramLong1, long paramLong2) {
    List<UsageStats> list = queryUsageStats(4, paramLong1, paramLong2);
    if (list.isEmpty())
      return Collections.emptyMap(); 
    ArrayMap arrayMap = new ArrayMap();
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      UsageStats usageStats1 = list.get(b);
      UsageStats usageStats2 = (UsageStats)arrayMap.get(usageStats1.getPackageName());
      if (usageStats2 == null) {
        arrayMap.put(usageStats1.mPackageName, usageStats1);
      } else {
        usageStats2.add(usageStats1);
      } 
    } 
    return (Map<String, UsageStats>)arrayMap;
  }
  
  public List<ConfigurationStats> queryConfigurations(int paramInt, long paramLong1, long paramLong2) {
    try {
      ParceledListSlice parceledListSlice = this.mService.queryConfigurationStats(paramInt, paramLong1, paramLong2, this.mContext.getOpPackageName());
      if (parceledListSlice != null)
        return parceledListSlice.getList(); 
    } catch (RemoteException remoteException) {}
    return Collections.emptyList();
  }
  
  public List<EventStats> queryEventStats(int paramInt, long paramLong1, long paramLong2) {
    try {
      ParceledListSlice parceledListSlice = this.mService.queryEventStats(paramInt, paramLong1, paramLong2, this.mContext.getOpPackageName());
      if (parceledListSlice != null)
        return parceledListSlice.getList(); 
    } catch (RemoteException remoteException) {}
    return Collections.emptyList();
  }
  
  public UsageEvents queryEvents(long paramLong1, long paramLong2) {
    try {
      UsageEvents usageEvents = this.mService.queryEvents(paramLong1, paramLong2, this.mContext.getOpPackageName());
      if (usageEvents != null)
        return usageEvents; 
    } catch (RemoteException remoteException) {}
    return sEmptyResults;
  }
  
  public UsageEvents queryEventsForSelf(long paramLong1, long paramLong2) {
    try {
      UsageEvents usageEvents = this.mService.queryEventsForPackage(paramLong1, paramLong2, this.mContext.getOpPackageName());
      if (usageEvents != null)
        return usageEvents; 
    } catch (RemoteException remoteException) {}
    return sEmptyResults;
  }
  
  public List<UsageStats> queryUsageStats(int paramInt, long paramLong1, long paramLong2) {
    try {
      ParceledListSlice parceledListSlice = this.mService.queryUsageStats(paramInt, paramLong1, paramLong2, this.mContext.getOpPackageName());
      if (parceledListSlice != null)
        return parceledListSlice.getList(); 
    } catch (RemoteException remoteException) {}
    return Collections.emptyList();
  }
  
  @SystemApi
  public void registerAppUsageLimitObserver(int paramInt, String[] paramArrayOfString, Duration paramDuration1, Duration paramDuration2, PendingIntent paramPendingIntent) {
    try {
      this.mService.registerAppUsageLimitObserver(paramInt, paramArrayOfString, paramDuration1.toMillis(), paramDuration2.toMillis(), paramPendingIntent, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void registerAppUsageObserver(int paramInt, String[] paramArrayOfString, long paramLong, TimeUnit paramTimeUnit, PendingIntent paramPendingIntent) {
    try {
      this.mService.registerAppUsageObserver(paramInt, paramArrayOfString, paramTimeUnit.toMillis(paramLong), paramPendingIntent, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void registerUsageSessionObserver(int paramInt, String[] paramArrayOfString, Duration paramDuration1, Duration paramDuration2, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2) {
    try {
      this.mService.registerUsageSessionObserver(paramInt, paramArrayOfString, paramDuration1.toMillis(), paramDuration2.toMillis(), paramPendingIntent1, paramPendingIntent2, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportChooserSelection(String paramString1, int paramInt, String paramString2, String[] paramArrayOfString, String paramString3) {
    try {
      this.mService.reportChooserSelection(paramString1, paramInt, paramString2, paramArrayOfString, paramString3);
    } catch (RemoteException remoteException) {}
  }
  
  @SystemApi
  public void reportUsageStart(Activity paramActivity, String paramString) {
    try {
      this.mService.reportUsageStart(paramActivity.getActivityToken(), paramString, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void reportUsageStart(Activity paramActivity, String paramString, long paramLong) {
    try {
      this.mService.reportPastUsageStart(paramActivity.getActivityToken(), paramString, paramLong, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void reportUsageStop(Activity paramActivity, String paramString) {
    try {
      this.mService.reportUsageStop(paramActivity.getActivityToken(), paramString, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setAppInactive(String paramString, boolean paramBoolean) {
    try {
      this.mService.setAppInactive(paramString, paramBoolean, this.mContext.getUserId());
    } catch (RemoteException remoteException) {}
  }
  
  @SystemApi
  public void setAppStandbyBucket(String paramString, int paramInt) {
    try {
      this.mService.setAppStandbyBucket(paramString, paramInt, this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setAppStandbyBuckets(Map<String, Integer> paramMap) {
    if (paramMap == null)
      return; 
    ArrayList<AppStandbyInfo> arrayList = new ArrayList(paramMap.size());
    for (Map.Entry<String, Integer> entry : paramMap.entrySet())
      arrayList.add(new AppStandbyInfo((String)entry.getKey(), ((Integer)entry.getValue()).intValue())); 
    ParceledListSlice parceledListSlice = new ParceledListSlice(arrayList);
    try {
      this.mService.setAppStandbyBuckets(parceledListSlice, this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void unregisterAppUsageLimitObserver(int paramInt) {
    try {
      this.mService.unregisterAppUsageLimitObserver(paramInt, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void unregisterAppUsageObserver(int paramInt) {
    try {
      this.mService.unregisterAppUsageObserver(paramInt, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void unregisterUsageSessionObserver(int paramInt) {
    try {
      this.mService.unregisterUsageSessionObserver(paramInt, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public void whitelistAppTemporarily(String paramString, long paramLong, UserHandle paramUserHandle) {
    ((PowerWhitelistManager)this.mContext.getSystemService(PowerWhitelistManager.class)).whitelistAppTemporarily(paramString, paramLong);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StandbyBuckets {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SystemForcedReasons {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UsageSource {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/UsageStatsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */