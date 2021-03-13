package android.app;

import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.Adjustment;
import android.service.notification.Condition;
import android.service.notification.IConditionProvider;
import android.service.notification.INotificationListener;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import java.util.List;

public class Default implements INotificationManager {
  public String addAutomaticZenRule(AutomaticZenRule paramAutomaticZenRule) throws RemoteException {
    return null;
  }
  
  public void allowAssistantAdjustment(String paramString) throws RemoteException {}
  
  public void applyAdjustmentFromAssistant(INotificationListener paramINotificationListener, Adjustment paramAdjustment) throws RemoteException {}
  
  public void applyAdjustmentsFromAssistant(INotificationListener paramINotificationListener, List<Adjustment> paramList) throws RemoteException {}
  
  public void applyEnqueuedAdjustmentFromAssistant(INotificationListener paramINotificationListener, Adjustment paramAdjustment) throws RemoteException {}
  
  public void applyRestore(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {}
  
  public boolean areBubblesAllowed(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean areChannelsBypassingDnd() throws RemoteException {
    return false;
  }
  
  public boolean areNotificationsEnabled(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean areNotificationsEnabledForPackage(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public boolean canNotifyAsPackage(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean canShowBadge(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void cancelAllNotifications(String paramString, int paramInt) throws RemoteException {}
  
  public void cancelNotificationFromListener(INotificationListener paramINotificationListener, String paramString1, String paramString2, int paramInt) throws RemoteException {}
  
  public void cancelNotificationWithTag(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void cancelNotificationsFromListener(INotificationListener paramINotificationListener, String[] paramArrayOfString) throws RemoteException {}
  
  public void cancelToast(String paramString, IBinder paramIBinder) throws RemoteException {}
  
  public void clearData(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void clearRequestedListenerHints(INotificationListener paramINotificationListener) throws RemoteException {}
  
  public void createConversationNotificationChannelForPackage(String paramString1, int paramInt, String paramString2, NotificationChannel paramNotificationChannel, String paramString3) throws RemoteException {}
  
  public void createNotificationChannelGroups(String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {}
  
  public void createNotificationChannels(String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {}
  
  public void createNotificationChannelsForPackage(String paramString, int paramInt, ParceledListSlice paramParceledListSlice) throws RemoteException {}
  
  public void deleteConversationNotificationChannels(String paramString1, int paramInt, String paramString2) throws RemoteException {}
  
  public void deleteNotificationChannel(String paramString1, String paramString2) throws RemoteException {}
  
  public void deleteNotificationChannelGroup(String paramString1, String paramString2) throws RemoteException {}
  
  public void deleteNotificationHistoryItem(String paramString, int paramInt, long paramLong) throws RemoteException {}
  
  public void disallowAssistantAdjustment(String paramString) throws RemoteException {}
  
  public void enqueueNotificationWithTag(String paramString1, String paramString2, String paramString3, int paramInt1, Notification paramNotification, int paramInt2) throws RemoteException {}
  
  public void enqueueTextToast(String paramString, IBinder paramIBinder, CharSequence paramCharSequence, int paramInt1, int paramInt2, ITransientNotificationCallback paramITransientNotificationCallback) throws RemoteException {}
  
  public void enqueueToast(String paramString, IBinder paramIBinder, ITransientNotification paramITransientNotification, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void finishToken(String paramString, IBinder paramIBinder) throws RemoteException {}
  
  public StatusBarNotification[] getActiveNotifications(String paramString) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getActiveNotificationsFromListener(INotificationListener paramINotificationListener, String[] paramArrayOfString, int paramInt) throws RemoteException {
    return null;
  }
  
  public StatusBarNotification[] getActiveNotificationsWithAttribution(String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public List<String> getAllowedAssistantAdjustments(String paramString) throws RemoteException {
    return null;
  }
  
  public ComponentName getAllowedNotificationAssistant() throws RemoteException {
    return null;
  }
  
  public ComponentName getAllowedNotificationAssistantForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getAppActiveNotifications(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getAppsBypassingDndCount(int paramInt) throws RemoteException {
    return 0;
  }
  
  public AutomaticZenRule getAutomaticZenRule(String paramString) throws RemoteException {
    return null;
  }
  
  public byte[] getBackupPayload(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getBlockedAppCount(int paramInt) throws RemoteException {
    return 0;
  }
  
  public int getBlockedChannelCount(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public int getBubblePreferenceForPackage(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
    return null;
  }
  
  public NotificationChannel getConversationNotificationChannel(String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean, String paramString4) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getConversations(boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getConversationsForPackage(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getDeletedChannelCount(String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public ComponentName getEffectsSuppressor() throws RemoteException {
    return null;
  }
  
  public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
    return null;
  }
  
  public List<ComponentName> getEnabledNotificationListeners(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getHintsFromListener(INotificationListener paramINotificationListener) throws RemoteException {
    return 0;
  }
  
  public StatusBarNotification[] getHistoricalNotifications(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String paramString1, String paramString2, int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public int getInterruptionFilterFromListener(INotificationListener paramINotificationListener) throws RemoteException {
    return 0;
  }
  
  public NotificationChannel getNotificationChannel(String paramString1, int paramInt, String paramString2, String paramString3) throws RemoteException {
    return null;
  }
  
  public NotificationChannel getNotificationChannelForPackage(String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public NotificationChannelGroup getNotificationChannelGroup(String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public NotificationChannelGroup getNotificationChannelGroupForPackage(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannelGroups(String paramString) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannelGroupsForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannels(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannelsBypassingDnd(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannelsForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle) throws RemoteException {
    return null;
  }
  
  public String getNotificationDelegate(String paramString) throws RemoteException {
    return null;
  }
  
  public NotificationHistory getNotificationHistory(String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public NotificationManager.Policy getNotificationPolicy(String paramString) throws RemoteException {
    return null;
  }
  
  public int getNumNotificationChannelsForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public int getPackageImportance(String paramString) throws RemoteException {
    return 0;
  }
  
  public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String paramString1, int paramInt, String paramString2, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public boolean getPrivateNotificationsAllowed() throws RemoteException {
    return false;
  }
  
  public int getRuleInstanceCount(ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getZenMode() throws RemoteException {
    return 0;
  }
  
  public ZenModeConfig getZenModeConfig() throws RemoteException {
    return null;
  }
  
  public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
    return null;
  }
  
  public boolean hasSentValidMsg(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean hasUserDemotedInvalidMsgApp(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isInInvalidMsgState(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isNotificationAssistantAccessGranted(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isNotificationListenerAccessGranted(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isNotificationListenerAccessGrantedForUser(ComponentName paramComponentName, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isNotificationPolicyAccessGranted(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isNotificationPolicyAccessGrantedForPackage(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isPackagePaused(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isSystemConditionProviderEnabled(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean matchesCallFilter(Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public void notifyConditions(String paramString, IConditionProvider paramIConditionProvider, Condition[] paramArrayOfCondition) throws RemoteException {}
  
  public boolean onlyHasDefaultChannel(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public long pullStats(long paramLong, int paramInt, boolean paramBoolean, List<ParcelFileDescriptor> paramList) throws RemoteException {
    return 0L;
  }
  
  public void registerListener(INotificationListener paramINotificationListener, ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public boolean removeAutomaticZenRule(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean removeAutomaticZenRules(String paramString) throws RemoteException {
    return false;
  }
  
  public void requestBindListener(ComponentName paramComponentName) throws RemoteException {}
  
  public void requestBindProvider(ComponentName paramComponentName) throws RemoteException {}
  
  public void requestHintsFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {}
  
  public void requestInterruptionFilterFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {}
  
  public void requestUnbindListener(INotificationListener paramINotificationListener) throws RemoteException {}
  
  public void requestUnbindProvider(IConditionProvider paramIConditionProvider) throws RemoteException {}
  
  public void setAutomaticZenRuleState(String paramString, Condition paramCondition) throws RemoteException {}
  
  public void setBubblesAllowed(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setHideSilentStatusIcons(boolean paramBoolean) throws RemoteException {}
  
  public void setInterruptionFilter(String paramString, int paramInt) throws RemoteException {}
  
  public void setInvalidMsgAppDemoted(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationAssistantAccessGranted(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationAssistantAccessGrantedForUser(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationDelegate(String paramString1, String paramString2) throws RemoteException {}
  
  public void setNotificationListenerAccessGranted(ComponentName paramComponentName, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationListenerAccessGrantedForUser(ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationPolicy(String paramString, NotificationManager.Policy paramPolicy) throws RemoteException {}
  
  public void setNotificationPolicyAccessGranted(String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationPolicyAccessGrantedForUser(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationsEnabledForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationsEnabledWithImportanceLockForPackage(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setNotificationsShownFromListener(INotificationListener paramINotificationListener, String[] paramArrayOfString) throws RemoteException {}
  
  public void setOnNotificationPostedTrimFromListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {}
  
  public void setPrivateNotificationsAllowed(boolean paramBoolean) throws RemoteException {}
  
  public void setShowBadge(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setZenMode(int paramInt, Uri paramUri, String paramString) throws RemoteException {}
  
  public boolean shouldHideSilentStatusIcons(String paramString) throws RemoteException {
    return false;
  }
  
  public void silenceNotificationSound() throws RemoteException {}
  
  public void snoozeNotificationUntilContextFromListener(INotificationListener paramINotificationListener, String paramString1, String paramString2) throws RemoteException {}
  
  public void snoozeNotificationUntilFromListener(INotificationListener paramINotificationListener, String paramString, long paramLong) throws RemoteException {}
  
  public void unregisterListener(INotificationListener paramINotificationListener, int paramInt) throws RemoteException {}
  
  public void unsnoozeNotificationFromAssistant(INotificationListener paramINotificationListener, String paramString) throws RemoteException {}
  
  public void unsnoozeNotificationFromSystemListener(INotificationListener paramINotificationListener, String paramString) throws RemoteException {}
  
  public boolean updateAutomaticZenRule(String paramString, AutomaticZenRule paramAutomaticZenRule) throws RemoteException {
    return false;
  }
  
  public void updateNotificationChannelForPackage(String paramString, int paramInt, NotificationChannel paramNotificationChannel) throws RemoteException {}
  
  public void updateNotificationChannelFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle, NotificationChannel paramNotificationChannel) throws RemoteException {}
  
  public void updateNotificationChannelGroupForPackage(String paramString, int paramInt, NotificationChannelGroup paramNotificationChannelGroup) throws RemoteException {}
  
  public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener paramINotificationListener, String paramString, UserHandle paramUserHandle, NotificationChannelGroup paramNotificationChannelGroup) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/INotificationManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */