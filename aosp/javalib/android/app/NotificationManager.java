package android.app;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.os.UserHandle;
import android.service.notification.Condition;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.service.notification.ZenPolicy;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotificationManager {
  public static final String ACTION_APP_BLOCK_STATE_CHANGED = "android.app.action.APP_BLOCK_STATE_CHANGED";
  
  public static final String ACTION_AUTOMATIC_ZEN_RULE = "android.app.action.AUTOMATIC_ZEN_RULE";
  
  public static final String ACTION_AUTOMATIC_ZEN_RULE_STATUS_CHANGED = "android.app.action.AUTOMATIC_ZEN_RULE_STATUS_CHANGED";
  
  public static final String ACTION_EFFECTS_SUPPRESSOR_CHANGED = "android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED";
  
  public static final String ACTION_INTERRUPTION_FILTER_CHANGED = "android.app.action.INTERRUPTION_FILTER_CHANGED";
  
  public static final String ACTION_INTERRUPTION_FILTER_CHANGED_INTERNAL = "android.app.action.INTERRUPTION_FILTER_CHANGED_INTERNAL";
  
  public static final String ACTION_NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED = "android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED";
  
  public static final String ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED = "android.app.action.NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED";
  
  public static final String ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED = "android.app.action.NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED";
  
  public static final String ACTION_NOTIFICATION_POLICY_CHANGED = "android.app.action.NOTIFICATION_POLICY_CHANGED";
  
  public static final int AUTOMATIC_RULE_STATUS_DISABLED = 2;
  
  public static final int AUTOMATIC_RULE_STATUS_ENABLED = 1;
  
  public static final int AUTOMATIC_RULE_STATUS_REMOVED = 3;
  
  public static final int AUTOMATIC_RULE_STATUS_UNKNOWN = -1;
  
  public static final int BUBBLE_PREFERENCE_ALL = 1;
  
  public static final int BUBBLE_PREFERENCE_NONE = 0;
  
  public static final int BUBBLE_PREFERENCE_SELECTED = 2;
  
  public static final String EXTRA_AUTOMATIC_RULE_ID = "android.app.extra.AUTOMATIC_RULE_ID";
  
  public static final String EXTRA_AUTOMATIC_ZEN_RULE_ID = "android.app.extra.AUTOMATIC_ZEN_RULE_ID";
  
  public static final String EXTRA_AUTOMATIC_ZEN_RULE_STATUS = "android.app.extra.AUTOMATIC_ZEN_RULE_STATUS";
  
  public static final String EXTRA_BLOCKED_STATE = "android.app.extra.BLOCKED_STATE";
  
  public static final String EXTRA_NOTIFICATION_CHANNEL_GROUP_ID = "android.app.extra.NOTIFICATION_CHANNEL_GROUP_ID";
  
  public static final String EXTRA_NOTIFICATION_CHANNEL_ID = "android.app.extra.NOTIFICATION_CHANNEL_ID";
  
  public static final int IMPORTANCE_DEFAULT = 3;
  
  public static final int IMPORTANCE_HIGH = 4;
  
  public static final int IMPORTANCE_LOW = 2;
  
  public static final int IMPORTANCE_MAX = 5;
  
  public static final int IMPORTANCE_MIN = 1;
  
  public static final int IMPORTANCE_NONE = 0;
  
  public static final int IMPORTANCE_UNSPECIFIED = -1000;
  
  public static final int INTERRUPTION_FILTER_ALARMS = 4;
  
  public static final int INTERRUPTION_FILTER_ALL = 1;
  
  public static final int INTERRUPTION_FILTER_NONE = 3;
  
  public static final int INTERRUPTION_FILTER_PRIORITY = 2;
  
  public static final int INTERRUPTION_FILTER_UNKNOWN = 0;
  
  public static final String META_DATA_AUTOMATIC_RULE_TYPE = "android.service.zen.automatic.ruleType";
  
  public static final String META_DATA_RULE_INSTANCE_LIMIT = "android.service.zen.automatic.ruleInstanceLimit";
  
  private static String TAG = "NotificationManager";
  
  public static final int VISIBILITY_NO_OVERRIDE = -1000;
  
  private static boolean localLOGV = false;
  
  private static INotificationManager sService;
  
  private Context mContext;
  
  NotificationManager(Context paramContext, Handler paramHandler) {
    this.mContext = paramContext;
  }
  
  private static void checkRequired(String paramString, Object paramObject) {
    if (paramObject != null)
      return; 
    paramObject = new StringBuilder();
    paramObject.append(paramString);
    paramObject.append(" is required");
    throw new IllegalArgumentException(paramObject.toString());
  }
  
  private void fixLegacySmallIcon(Notification paramNotification, String paramString) {
    if (paramNotification.getSmallIcon() == null && paramNotification.icon != 0)
      paramNotification.setSmallIcon(Icon.createWithResource(paramString, paramNotification.icon)); 
  }
  
  private Notification fixNotification(Notification paramNotification) {
    String str = this.mContext.getPackageName();
    Notification.addFieldsFromContext(this.mContext, paramNotification);
    if (paramNotification.sound != null) {
      paramNotification.sound = paramNotification.sound.getCanonicalUri();
      if (StrictMode.vmFileUriExposureEnabled())
        paramNotification.sound.checkFileUriExposed("Notification.sound"); 
    } 
    fixLegacySmallIcon(paramNotification, str);
    if ((this.mContext.getApplicationInfo()).targetSdkVersion <= 22 || paramNotification.getSmallIcon() != null) {
      paramNotification.reduceImageSizes(this.mContext);
      return Notification.Builder.maybeCloneStrippedForDelivery(paramNotification);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid notification (no valid small icon): ");
    stringBuilder.append(paramNotification);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static NotificationManager from(Context paramContext) {
    return (NotificationManager)paramContext.getSystemService("notification");
  }
  
  public static INotificationManager getService() {
    INotificationManager iNotificationManager = sService;
    if (iNotificationManager != null)
      return iNotificationManager; 
    iNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    sService = iNotificationManager;
    return iNotificationManager;
  }
  
  public static int zenModeFromInterruptionFilter(int paramInt1, int paramInt2) {
    return (paramInt1 != 1) ? ((paramInt1 != 2) ? ((paramInt1 != 3) ? ((paramInt1 != 4) ? paramInt2 : 3) : 2) : 1) : 0;
  }
  
  public static int zenModeToInterruptionFilter(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? 0 : 4) : 3) : 2) : 1;
  }
  
  public String addAutomaticZenRule(AutomaticZenRule paramAutomaticZenRule) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.addAutomaticZenRule(paramAutomaticZenRule);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void allowAssistantAdjustment(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.allowAssistantAdjustment(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean areBubblesAllowed() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.areBubblesAllowed(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean areNotificationsEnabled() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.areNotificationsEnabled(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean areNotificationsPaused() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.isPackagePaused(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean canNotifyAsPackage(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.canNotifyAsPackage(this.mContext.getPackageName(), paramString, this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void cancel(int paramInt) {
    cancel(null, paramInt);
  }
  
  public void cancel(String paramString, int paramInt) {
    cancelAsUser(paramString, paramInt, this.mContext.getUser());
  }
  
  public void cancelAll() {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    if (localLOGV) {
      String str1 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(": cancelAll()");
      Log.v(str1, stringBuilder.toString());
    } 
    try {
      iNotificationManager.cancelAllNotifications(str, this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void cancelAsPackage(String paramString1, String paramString2, int paramInt) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.cancelNotificationWithTag(paramString1, this.mContext.getOpPackageName(), paramString2, paramInt, this.mContext.getUser().getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void cancelAsUser(String paramString, int paramInt, UserHandle paramUserHandle) {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    if (localLOGV) {
      String str1 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(": cancel(");
      stringBuilder.append(paramInt);
      stringBuilder.append(")");
      Log.v(str1, stringBuilder.toString());
    } 
    try {
      iNotificationManager.cancelNotificationWithTag(str, this.mContext.getOpPackageName(), paramString, paramInt, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void createNotificationChannel(NotificationChannel paramNotificationChannel) {
    createNotificationChannels(Arrays.asList(new NotificationChannel[] { paramNotificationChannel }));
  }
  
  public void createNotificationChannelGroup(NotificationChannelGroup paramNotificationChannelGroup) {
    createNotificationChannelGroups(Arrays.asList(new NotificationChannelGroup[] { paramNotificationChannelGroup }));
  }
  
  public void createNotificationChannelGroups(List<NotificationChannelGroup> paramList) {
    INotificationManager iNotificationManager = getService();
    try {
      String str = this.mContext.getPackageName();
      ParceledListSlice parceledListSlice = new ParceledListSlice();
      this(paramList);
      iNotificationManager.createNotificationChannelGroups(str, parceledListSlice);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void createNotificationChannels(List<NotificationChannel> paramList) {
    INotificationManager iNotificationManager = getService();
    try {
      String str = this.mContext.getPackageName();
      ParceledListSlice parceledListSlice = new ParceledListSlice();
      this(paramList);
      iNotificationManager.createNotificationChannels(str, parceledListSlice);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void deleteNotificationChannel(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.deleteNotificationChannel(this.mContext.getPackageName(), paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void deleteNotificationChannelGroup(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.deleteNotificationChannelGroup(this.mContext.getPackageName(), paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disallowAssistantAdjustment(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.disallowAssistantAdjustment(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public StatusBarNotification[] getActiveNotifications() {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    try {
      ParceledListSlice parceledListSlice = iNotificationManager.getAppActiveNotifications(str, this.mContext.getUserId());
      if (parceledListSlice != null) {
        List list = parceledListSlice.getList();
        return (StatusBarNotification[])list.toArray((Object[])new StatusBarNotification[list.size()]);
      } 
      return new StatusBarNotification[0];
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<String> getAllowedAssistantAdjustments() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getAllowedAssistantAdjustments(this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public ComponentName getAllowedNotificationAssistant() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getAllowedNotificationAssistant();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AutomaticZenRule getAutomaticZenRule(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getAutomaticZenRule(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Map<String, AutomaticZenRule> getAutomaticZenRules() {
    INotificationManager iNotificationManager1 = getService();
    INotificationManager iNotificationManager2 = iNotificationManager1;
    try {
      List<ZenModeConfig.ZenRule> list = iNotificationManager1.getZenRules();
      iNotificationManager2 = iNotificationManager1;
      HashMap<Object, Object> hashMap = new HashMap<>();
      iNotificationManager2 = iNotificationManager1;
      this();
      iNotificationManager2 = iNotificationManager1;
      Iterator<ZenModeConfig.ZenRule> iterator = list.iterator();
      while (true) {
        iNotificationManager2 = iNotificationManager1;
        if (iterator.hasNext()) {
          iNotificationManager2 = iNotificationManager1;
          ZenModeConfig.ZenRule zenRule = iterator.next();
          iNotificationManager2 = iNotificationManager1;
          String str1 = zenRule.id;
          iNotificationManager2 = iNotificationManager1;
          AutomaticZenRule automaticZenRule = new AutomaticZenRule();
          iNotificationManager2 = iNotificationManager1;
          String str2 = zenRule.name;
          iNotificationManager2 = iNotificationManager1;
          ComponentName componentName1 = zenRule.component;
          iNotificationManager2 = iNotificationManager1;
          ComponentName componentName2 = zenRule.configurationActivity;
          iNotificationManager2 = iNotificationManager1;
          Uri uri = zenRule.conditionId;
          iNotificationManager2 = iNotificationManager1;
          ZenPolicy zenPolicy = zenRule.zenPolicy;
          iNotificationManager2 = iNotificationManager1;
          int i = zenModeToInterruptionFilter(zenRule.zenMode);
          iNotificationManager2 = iNotificationManager1;
          boolean bool = zenRule.enabled;
          try {
            this(str2, componentName1, componentName2, uri, zenPolicy, i, bool, zenRule.creationTime);
            hashMap.put(str1, automaticZenRule);
            continue;
          } catch (RemoteException null) {
            break;
          } 
        } 
        return (Map)hashMap;
      } 
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  public Policy getConsolidatedNotificationPolicy() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getConsolidatedNotificationPolicy();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public final int getCurrentInterruptionFilter() {
    INotificationManager iNotificationManager = getService();
    try {
      return zenModeToInterruptionFilter(iNotificationManager.getZenMode());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ComponentName getEffectsSuppressor() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getEffectsSuppressor();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<String> getEnabledNotificationListenerPackages() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getEnabledNotificationListenerPackages();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ComponentName> getEnabledNotificationListeners(int paramInt) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getEnabledNotificationListeners(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getImportance() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getPackageImportance(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public NotificationChannel getNotificationChannel(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getNotificationChannel(this.mContext.getOpPackageName(), this.mContext.getUserId(), this.mContext.getPackageName(), paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public NotificationChannel getNotificationChannel(String paramString1, String paramString2) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getConversationNotificationChannel(this.mContext.getOpPackageName(), this.mContext.getUserId(), this.mContext.getPackageName(), paramString1, true, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public NotificationChannelGroup getNotificationChannelGroup(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getNotificationChannelGroup(this.mContext.getPackageName(), paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<NotificationChannelGroup> getNotificationChannelGroups() {
    INotificationManager iNotificationManager = getService();
    try {
      ParceledListSlice parceledListSlice = iNotificationManager.getNotificationChannelGroups(this.mContext.getPackageName());
      return (parceledListSlice != null) ? parceledListSlice.getList() : new ArrayList<>();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<NotificationChannel> getNotificationChannels() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getNotificationChannels(this.mContext.getOpPackageName(), this.mContext.getPackageName(), this.mContext.getUserId()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getNotificationDelegate() {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    try {
      return iNotificationManager.getNotificationDelegate(str);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Policy getNotificationPolicy() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getNotificationPolicy(this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getRuleInstanceCount(ComponentName paramComponentName) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getRuleInstanceCount(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getZenMode() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getZenMode();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ZenModeConfig getZenModeConfig() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.getZenModeConfig();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean isNotificationAssistantAccessGranted(ComponentName paramComponentName) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.isNotificationAssistantAccessGranted(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isNotificationListenerAccessGranted(ComponentName paramComponentName) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.isNotificationListenerAccessGranted(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isNotificationPolicyAccessGranted() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.isNotificationPolicyAccessGranted(this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isNotificationPolicyAccessGrantedForPackage(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.isNotificationPolicyAccessGrantedForPackage(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isSystemConditionProviderEnabled(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.isSystemConditionProviderEnabled(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean matchesCallFilter(Bundle paramBundle) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.matchesCallFilter(paramBundle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void notify(int paramInt, Notification paramNotification) {
    notify(null, paramInt, paramNotification);
  }
  
  public void notify(String paramString, int paramInt, Notification paramNotification) {
    notifyAsUser(paramString, paramInt, paramNotification, this.mContext.getUser());
  }
  
  public void notifyAsPackage(String paramString1, String paramString2, int paramInt, Notification paramNotification) {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    try {
      if (localLOGV) {
        String str1 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(str);
        stringBuilder.append(": notify(");
        stringBuilder.append(paramInt);
        stringBuilder.append(", ");
        stringBuilder.append(paramNotification);
        stringBuilder.append(")");
        Log.v(str1, stringBuilder.toString());
      } 
      iNotificationManager.enqueueNotificationWithTag(paramString1, str, paramString2, paramInt, fixNotification(paramNotification), this.mContext.getUser().getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void notifyAsUser(String paramString, int paramInt, Notification paramNotification, UserHandle paramUserHandle) {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    try {
      if (localLOGV) {
        String str1 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(str);
        stringBuilder.append(": notify(");
        stringBuilder.append(paramInt);
        stringBuilder.append(", ");
        stringBuilder.append(paramNotification);
        stringBuilder.append(")");
        Log.v(str1, stringBuilder.toString());
      } 
      iNotificationManager.enqueueNotificationWithTag(str, this.mContext.getOpPackageName(), paramString, paramInt, fixNotification(paramNotification), paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean removeAutomaticZenRule(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.removeAutomaticZenRule(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean removeAutomaticZenRules(String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.removeAutomaticZenRules(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setAutomaticZenRuleState(String paramString, Condition paramCondition) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setAutomaticZenRuleState(paramString, paramCondition);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public final void setInterruptionFilter(int paramInt) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setInterruptionFilter(this.mContext.getOpPackageName(), paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setNotificationAssistantAccessGranted(ComponentName paramComponentName, boolean paramBoolean) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setNotificationAssistantAccessGranted(paramComponentName, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setNotificationDelegate(String paramString) {
    INotificationManager iNotificationManager = getService();
    String str = this.mContext.getPackageName();
    if (localLOGV) {
      String str1 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(": cancelAll()");
      Log.v(str1, stringBuilder.toString());
    } 
    try {
      iNotificationManager.setNotificationDelegate(str, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setNotificationListenerAccessGranted(ComponentName paramComponentName, boolean paramBoolean) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setNotificationListenerAccessGranted(paramComponentName, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setNotificationListenerAccessGrantedForUser(ComponentName paramComponentName, int paramInt, boolean paramBoolean) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setNotificationListenerAccessGrantedForUser(paramComponentName, paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setNotificationPolicy(Policy paramPolicy) {
    checkRequired("policy", paramPolicy);
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setNotificationPolicy(this.mContext.getOpPackageName(), paramPolicy);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setNotificationPolicyAccessGranted(String paramString, boolean paramBoolean) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setNotificationPolicyAccessGranted(paramString, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setZenMode(int paramInt, Uri paramUri, String paramString) {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.setZenMode(paramInt, paramUri, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean shouldHideSilentStatusBarIcons() {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.shouldHideSilentStatusIcons(this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void silenceNotificationSound() {
    INotificationManager iNotificationManager = getService();
    try {
      iNotificationManager.silenceNotificationSound();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean updateAutomaticZenRule(String paramString, AutomaticZenRule paramAutomaticZenRule) {
    INotificationManager iNotificationManager = getService();
    try {
      return iNotificationManager.updateAutomaticZenRule(paramString, paramAutomaticZenRule);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AutomaticZenRuleStatus {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Importance {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InterruptionFilter {}
  
  public static class Policy implements Parcelable {
    public static final int[] ALL_PRIORITY_CATEGORIES = new int[] { 32, 64, 128, 1, 2, 4, 8, 16, 256 };
    
    private static final int[] ALL_SUPPRESSED_EFFECTS = new int[] { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
    
    public static final int CONVERSATION_SENDERS_ANYONE = 1;
    
    public static final int CONVERSATION_SENDERS_IMPORTANT = 2;
    
    public static final int CONVERSATION_SENDERS_NONE = 3;
    
    public static final int CONVERSATION_SENDERS_UNSET = -1;
    
    public static final Parcelable.Creator<Policy> CREATOR = new Parcelable.Creator<Policy>() {
        public NotificationManager.Policy createFromParcel(Parcel param2Parcel) {
          return new NotificationManager.Policy(param2Parcel);
        }
        
        public NotificationManager.Policy[] newArray(int param2Int) {
          return new NotificationManager.Policy[param2Int];
        }
      };
    
    public static final int PRIORITY_CATEGORY_ALARMS = 32;
    
    public static final int PRIORITY_CATEGORY_CALLS = 8;
    
    public static final int PRIORITY_CATEGORY_CONVERSATIONS = 256;
    
    public static final int PRIORITY_CATEGORY_EVENTS = 2;
    
    public static final int PRIORITY_CATEGORY_MEDIA = 64;
    
    public static final int PRIORITY_CATEGORY_MESSAGES = 4;
    
    public static final int PRIORITY_CATEGORY_REMINDERS = 1;
    
    public static final int PRIORITY_CATEGORY_REPEAT_CALLERS = 16;
    
    public static final int PRIORITY_CATEGORY_SYSTEM = 128;
    
    public static final int PRIORITY_SENDERS_ANY = 0;
    
    public static final int PRIORITY_SENDERS_CONTACTS = 1;
    
    public static final int PRIORITY_SENDERS_STARRED = 2;
    
    public static final int STATE_CHANNELS_BYPASSING_DND = 1;
    
    public static final int STATE_UNSET = -1;
    
    public static final int SUPPRESSED_EFFECTS_UNSET = -1;
    
    public static final int SUPPRESSED_EFFECT_AMBIENT = 128;
    
    public static final int SUPPRESSED_EFFECT_BADGE = 64;
    
    public static final int SUPPRESSED_EFFECT_FULL_SCREEN_INTENT = 4;
    
    public static final int SUPPRESSED_EFFECT_LIGHTS = 8;
    
    public static final int SUPPRESSED_EFFECT_NOTIFICATION_LIST = 256;
    
    public static final int SUPPRESSED_EFFECT_PEEK = 16;
    
    @Deprecated
    public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;
    
    @Deprecated
    public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;
    
    public static final int SUPPRESSED_EFFECT_STATUS_BAR = 32;
    
    public final int priorityCallSenders;
    
    public final int priorityCategories;
    
    public final int priorityConversationSenders;
    
    public final int priorityMessageSenders;
    
    public final int state;
    
    public final int suppressedVisualEffects;
    
    public Policy(int param1Int1, int param1Int2, int param1Int3) {
      this(param1Int1, param1Int2, param1Int3, -1, -1, -1);
    }
    
    public Policy(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      this(param1Int1, param1Int2, param1Int3, param1Int4, -1, -1);
    }
    
    public Policy(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      this(param1Int1, param1Int2, param1Int3, param1Int4, -1, param1Int5);
    }
    
    public Policy(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6) {
      this.priorityCategories = param1Int1;
      this.priorityCallSenders = param1Int2;
      this.priorityMessageSenders = param1Int3;
      this.suppressedVisualEffects = param1Int4;
      this.state = param1Int5;
      this.priorityConversationSenders = param1Int6;
    }
    
    public Policy(Parcel param1Parcel) {
      this(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
    }
    
    public static boolean areAllVisualEffectsSuppressed(int param1Int) {
      byte b = 0;
      while (true) {
        int[] arrayOfInt = ALL_SUPPRESSED_EFFECTS;
        if (b < arrayOfInt.length) {
          if ((param1Int & arrayOfInt[b]) == 0)
            return false; 
          b++;
          continue;
        } 
        return true;
      } 
    }
    
    private static void bitwiseToProtoEnum(ProtoOutputStream param1ProtoOutputStream, long param1Long, int param1Int) {
      byte b = 1;
      while (param1Int > 0) {
        if ((param1Int & 0x1) == 1)
          param1ProtoOutputStream.write(param1Long, b); 
        b++;
        param1Int >>>= 1;
      } 
    }
    
    public static String conversationSendersToString(int param1Int) {
      if (param1Int != -1) {
        if (param1Int != 1) {
          if (param1Int != 2) {
            if (param1Int != 3) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("invalidConversationType{");
              stringBuilder.append(param1Int);
              stringBuilder.append("}");
              return stringBuilder.toString();
            } 
            return "none";
          } 
          return "important";
        } 
        return "anyone";
      } 
      return "unset";
    }
    
    private static String effectToString(int param1Int) {
      if (param1Int != -1) {
        if (param1Int != 4) {
          if (param1Int != 8) {
            if (param1Int != 16) {
              if (param1Int != 32) {
                if (param1Int != 64) {
                  if (param1Int != 128) {
                    if (param1Int != 256) {
                      if (param1Int != 1) {
                        if (param1Int != 2) {
                          StringBuilder stringBuilder = new StringBuilder();
                          stringBuilder.append("UNKNOWN_");
                          stringBuilder.append(param1Int);
                          return stringBuilder.toString();
                        } 
                        return "SUPPRESSED_EFFECT_SCREEN_ON";
                      } 
                      return "SUPPRESSED_EFFECT_SCREEN_OFF";
                    } 
                    return "SUPPRESSED_EFFECT_NOTIFICATION_LIST";
                  } 
                  return "SUPPRESSED_EFFECT_AMBIENT";
                } 
                return "SUPPRESSED_EFFECT_BADGE";
              } 
              return "SUPPRESSED_EFFECT_STATUS_BAR";
            } 
            return "SUPPRESSED_EFFECT_PEEK";
          } 
          return "SUPPRESSED_EFFECT_LIGHTS";
        } 
        return "SUPPRESSED_EFFECT_FULL_SCREEN_INTENT";
      } 
      return "SUPPRESSED_EFFECTS_UNSET";
    }
    
    public static int getAllSuppressedVisualEffects() {
      int i = 0;
      byte b = 0;
      while (true) {
        int[] arrayOfInt = ALL_SUPPRESSED_EFFECTS;
        if (b < arrayOfInt.length) {
          i |= arrayOfInt[b];
          b++;
          continue;
        } 
        return i;
      } 
    }
    
    public static String priorityCategoriesToString(int param1Int) {
      if (param1Int == 0)
        return ""; 
      StringBuilder stringBuilder = new StringBuilder();
      int i = 0;
      int j = param1Int;
      param1Int = i;
      while (true) {
        int[] arrayOfInt = ALL_PRIORITY_CATEGORIES;
        if (param1Int < arrayOfInt.length) {
          i = arrayOfInt[param1Int];
          if ((j & i) != 0) {
            if (stringBuilder.length() > 0)
              stringBuilder.append(','); 
            stringBuilder.append(priorityCategoryToString(i));
          } 
          j &= i;
          param1Int++;
          continue;
        } 
        if (j != 0) {
          if (stringBuilder.length() > 0)
            stringBuilder.append(','); 
          stringBuilder.append("PRIORITY_CATEGORY_UNKNOWN_");
          stringBuilder.append(j);
        } 
        return stringBuilder.toString();
      } 
    }
    
    private static String priorityCategoryToString(int param1Int) {
      if (param1Int != 1) {
        if (param1Int != 2) {
          if (param1Int != 4) {
            if (param1Int != 8) {
              if (param1Int != 16) {
                if (param1Int != 32) {
                  if (param1Int != 64) {
                    if (param1Int != 128) {
                      if (param1Int != 256) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("PRIORITY_CATEGORY_UNKNOWN_");
                        stringBuilder.append(param1Int);
                        return stringBuilder.toString();
                      } 
                      return "PRIORITY_CATEGORY_CONVERSATIONS";
                    } 
                    return "PRIORITY_CATEGORY_SYSTEM";
                  } 
                  return "PRIORITY_CATEGORY_MEDIA";
                } 
                return "PRIORITY_CATEGORY_ALARMS";
              } 
              return "PRIORITY_CATEGORY_REPEAT_CALLERS";
            } 
            return "PRIORITY_CATEGORY_CALLS";
          } 
          return "PRIORITY_CATEGORY_MESSAGES";
        } 
        return "PRIORITY_CATEGORY_EVENTS";
      } 
      return "PRIORITY_CATEGORY_REMINDERS";
    }
    
    public static String prioritySendersToString(int param1Int) {
      if (param1Int != 0) {
        if (param1Int != 1) {
          if (param1Int != 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("PRIORITY_SENDERS_UNKNOWN_");
            stringBuilder.append(param1Int);
            return stringBuilder.toString();
          } 
          return "PRIORITY_SENDERS_STARRED";
        } 
        return "PRIORITY_SENDERS_CONTACTS";
      } 
      return "PRIORITY_SENDERS_ANY";
    }
    
    public static String suppressedEffectsToString(int param1Int) {
      if (param1Int <= 0)
        return ""; 
      StringBuilder stringBuilder = new StringBuilder();
      int i = 0;
      int j = param1Int;
      param1Int = i;
      while (true) {
        int[] arrayOfInt = ALL_SUPPRESSED_EFFECTS;
        if (param1Int < arrayOfInt.length) {
          i = arrayOfInt[param1Int];
          if ((j & i) != 0) {
            if (stringBuilder.length() > 0)
              stringBuilder.append(','); 
            stringBuilder.append(effectToString(i));
          } 
          j &= i;
          param1Int++;
          continue;
        } 
        if (j != 0) {
          if (stringBuilder.length() > 0)
            stringBuilder.append(','); 
          stringBuilder.append("UNKNOWN_");
          stringBuilder.append(j);
        } 
        return stringBuilder.toString();
      } 
    }
    
    private boolean suppressedVisualEffectsEqual(int param1Int1, int param1Int2) {
      boolean bool = true;
      if (param1Int1 == param1Int2)
        return true; 
      int i = param1Int1;
      if ((param1Int1 & 0x2) != 0)
        i = param1Int1 | 0x10; 
      param1Int1 = i;
      if ((i & 0x1) != 0)
        param1Int1 = i | 0x4 | 0x8 | 0x80; 
      i = param1Int2;
      if ((param1Int2 & 0x2) != 0)
        i = param1Int2 | 0x10; 
      param1Int2 = i;
      if ((i & 0x1) != 0)
        param1Int2 = i | 0x4 | 0x8 | 0x80; 
      if ((param1Int1 & 0x2) != (param1Int2 & 0x2)) {
        if ((param1Int1 & 0x2) != 0) {
          i = param1Int2;
        } else {
          i = param1Int1;
        } 
        if ((i & 0x10) == 0)
          return false; 
      } 
      if ((param1Int1 & 0x1) != (param1Int2 & 0x1)) {
        if ((param1Int1 & 0x1) != 0) {
          i = param1Int2;
        } else {
          i = param1Int1;
        } 
        if ((i & 0x4) == 0 || (i & 0x8) == 0 || (i & 0x80) == 0)
          return false; 
      } 
      if ((param1Int1 & 0xFFFFFFFD & 0xFFFFFFFE) != (param1Int2 & 0xFFFFFFFD & 0xFFFFFFFE))
        bool = false; 
      return bool;
    }
    
    private static int toggleEffects(int param1Int, int[] param1ArrayOfint, boolean param1Boolean) {
      for (byte b = 0; b < param1ArrayOfint.length; b++) {
        int i = param1ArrayOfint[b];
        if (param1Boolean) {
          param1Int |= i;
        } else {
          param1Int &= i;
        } 
      } 
      return param1Int;
    }
    
    public boolean allowAlarms() {
      boolean bool;
      if ((this.priorityCategories & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean allowCalls() {
      boolean bool;
      if ((this.priorityCategories & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int allowCallsFrom() {
      return this.priorityCallSenders;
    }
    
    public boolean allowConversations() {
      boolean bool;
      if ((this.priorityCategories & 0x100) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int allowConversationsFrom() {
      return this.priorityConversationSenders;
    }
    
    public boolean allowEvents() {
      boolean bool;
      if ((this.priorityCategories & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean allowMedia() {
      boolean bool;
      if ((this.priorityCategories & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean allowMessages() {
      boolean bool;
      if ((this.priorityCategories & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int allowMessagesFrom() {
      return this.priorityMessageSenders;
    }
    
    public boolean allowReminders() {
      int i = this.priorityCategories;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean allowRepeatCallers() {
      boolean bool;
      if ((this.priorityCategories & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean allowSystem() {
      boolean bool;
      if ((this.priorityCategories & 0x80) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Policy copy() {
      Parcel parcel = Parcel.obtain();
      try {
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        return new Policy(parcel);
      } finally {
        parcel.recycle();
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void dumpDebug(ProtoOutputStream param1ProtoOutputStream, long param1Long) {
      param1Long = param1ProtoOutputStream.start(param1Long);
      bitwiseToProtoEnum(param1ProtoOutputStream, 2259152797697L, this.priorityCategories);
      param1ProtoOutputStream.write(1159641169922L, this.priorityCallSenders);
      param1ProtoOutputStream.write(1159641169923L, this.priorityMessageSenders);
      bitwiseToProtoEnum(param1ProtoOutputStream, 2259152797700L, this.suppressedVisualEffects);
      param1ProtoOutputStream.end(param1Long);
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof Policy;
      boolean bool1 = false;
      if (!bool)
        return false; 
      if (param1Object == this)
        return true; 
      param1Object = param1Object;
      if (((Policy)param1Object).priorityCategories == this.priorityCategories && ((Policy)param1Object).priorityCallSenders == this.priorityCallSenders && ((Policy)param1Object).priorityMessageSenders == this.priorityMessageSenders && suppressedVisualEffectsEqual(this.suppressedVisualEffects, ((Policy)param1Object).suppressedVisualEffects) && ((Policy)param1Object).state == this.state && ((Policy)param1Object).priorityConversationSenders == this.priorityConversationSenders)
        bool1 = true; 
      return bool1;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(this.priorityCategories), Integer.valueOf(this.priorityCallSenders), Integer.valueOf(this.priorityMessageSenders), Integer.valueOf(this.suppressedVisualEffects), Integer.valueOf(this.state), Integer.valueOf(this.priorityConversationSenders) });
    }
    
    public boolean showAmbient() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x80) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean showBadges() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x40) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean showFullScreenIntents() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x4) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean showInNotificationList() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x100) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean showLights() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x8) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean showPeeking() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x10) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean showStatusBarIcons() {
      boolean bool;
      if ((this.suppressedVisualEffects & 0x20) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String toString() {
      String str;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("NotificationManager.Policy[priorityCategories=");
      stringBuilder.append(priorityCategoriesToString(this.priorityCategories));
      stringBuilder.append(",priorityCallSenders=");
      stringBuilder.append(prioritySendersToString(this.priorityCallSenders));
      stringBuilder.append(",priorityMessageSenders=");
      stringBuilder.append(prioritySendersToString(this.priorityMessageSenders));
      stringBuilder.append(",priorityConvSenders=");
      stringBuilder.append(conversationSendersToString(this.priorityConversationSenders));
      stringBuilder.append(",suppressedVisualEffects=");
      stringBuilder.append(suppressedEffectsToString(this.suppressedVisualEffects));
      stringBuilder.append(",areChannelsBypassingDnd=");
      if ((this.state & 0x1) != 0) {
        str = "true";
      } else {
        str = "false";
      } 
      stringBuilder.append(str);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.priorityCategories);
      param1Parcel.writeInt(this.priorityCallSenders);
      param1Parcel.writeInt(this.priorityMessageSenders);
      param1Parcel.writeInt(this.suppressedVisualEffects);
      param1Parcel.writeInt(this.state);
      param1Parcel.writeInt(this.priorityConversationSenders);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface ConversationSenders {}
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface PrioritySenders {}
  }
  
  class null implements Parcelable.Creator<Policy> {
    public NotificationManager.Policy createFromParcel(Parcel param1Parcel) {
      return new NotificationManager.Policy(param1Parcel);
    }
    
    public NotificationManager.Policy[] newArray(int param1Int) {
      return new NotificationManager.Policy[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ConversationSenders {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PrioritySenders {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */