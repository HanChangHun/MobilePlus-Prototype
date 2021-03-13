package android.app;

import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.Adjustment;
import android.service.notification.Condition;
import android.service.notification.IConditionProvider;
import android.service.notification.INotificationListener;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public abstract class Stub extends Binder implements INotificationManager {
  private static final String DESCRIPTOR = "android.app.INotificationManager";
  
  static final int TRANSACTION_addAutomaticZenRule = 121;
  
  static final int TRANSACTION_allowAssistantAdjustment = 21;
  
  static final int TRANSACTION_applyAdjustmentFromAssistant = 90;
  
  static final int TRANSACTION_applyAdjustmentsFromAssistant = 91;
  
  static final int TRANSACTION_applyEnqueuedAdjustmentFromAssistant = 89;
  
  static final int TRANSACTION_applyRestore = 128;
  
  static final int TRANSACTION_areBubblesAllowed = 26;
  
  static final int TRANSACTION_areChannelsBypassingDnd = 54;
  
  static final int TRANSACTION_areNotificationsEnabled = 18;
  
  static final int TRANSACTION_areNotificationsEnabledForPackage = 17;
  
  static final int TRANSACTION_canNotifyAsPackage = 132;
  
  static final int TRANSACTION_canShowBadge = 10;
  
  static final int TRANSACTION_cancelAllNotifications = 1;
  
  static final int TRANSACTION_cancelNotificationFromListener = 67;
  
  static final int TRANSACTION_cancelNotificationWithTag = 8;
  
  static final int TRANSACTION_cancelNotificationsFromListener = 68;
  
  static final int TRANSACTION_cancelToast = 5;
  
  static final int TRANSACTION_clearData = 2;
  
  static final int TRANSACTION_clearRequestedListenerHints = 78;
  
  static final int TRANSACTION_createConversationNotificationChannelForPackage = 40;
  
  static final int TRANSACTION_createNotificationChannelGroups = 28;
  
  static final int TRANSACTION_createNotificationChannels = 29;
  
  static final int TRANSACTION_createNotificationChannelsForPackage = 30;
  
  static final int TRANSACTION_deleteConversationNotificationChannels = 43;
  
  static final int TRANSACTION_deleteNotificationChannel = 42;
  
  static final int TRANSACTION_deleteNotificationChannelGroup = 49;
  
  static final int TRANSACTION_deleteNotificationHistoryItem = 58;
  
  static final int TRANSACTION_disallowAssistantAdjustment = 22;
  
  static final int TRANSACTION_enqueueNotificationWithTag = 7;
  
  static final int TRANSACTION_enqueueTextToast = 3;
  
  static final int TRANSACTION_enqueueToast = 4;
  
  static final int TRANSACTION_finishToken = 6;
  
  static final int TRANSACTION_getActiveNotifications = 60;
  
  static final int TRANSACTION_getActiveNotificationsFromListener = 76;
  
  static final int TRANSACTION_getActiveNotificationsWithAttribution = 61;
  
  static final int TRANSACTION_getAllowedAssistantAdjustments = 20;
  
  static final int TRANSACTION_getAllowedNotificationAssistant = 107;
  
  static final int TRANSACTION_getAllowedNotificationAssistantForUser = 106;
  
  static final int TRANSACTION_getAppActiveNotifications = 129;
  
  static final int TRANSACTION_getAppsBypassingDndCount = 55;
  
  static final int TRANSACTION_getAutomaticZenRule = 119;
  
  static final int TRANSACTION_getBackupPayload = 127;
  
  static final int TRANSACTION_getBlockedAppCount = 53;
  
  static final int TRANSACTION_getBlockedChannelCount = 48;
  
  static final int TRANSACTION_getBubblePreferenceForPackage = 27;
  
  static final int TRANSACTION_getConsolidatedNotificationPolicy = 110;
  
  static final int TRANSACTION_getConversationNotificationChannel = 39;
  
  static final int TRANSACTION_getConversations = 31;
  
  static final int TRANSACTION_getConversationsForPackage = 32;
  
  static final int TRANSACTION_getDeletedChannelCount = 47;
  
  static final int TRANSACTION_getEffectsSuppressor = 94;
  
  static final int TRANSACTION_getEnabledNotificationListenerPackages = 104;
  
  static final int TRANSACTION_getEnabledNotificationListeners = 105;
  
  static final int TRANSACTION_getHintsFromListener = 80;
  
  static final int TRANSACTION_getHistoricalNotifications = 62;
  
  static final int TRANSACTION_getHistoricalNotificationsWithAttribution = 63;
  
  static final int TRANSACTION_getInterruptionFilterFromListener = 82;
  
  static final int TRANSACTION_getNotificationChannel = 38;
  
  static final int TRANSACTION_getNotificationChannelForPackage = 41;
  
  static final int TRANSACTION_getNotificationChannelGroup = 50;
  
  static final int TRANSACTION_getNotificationChannelGroupForPackage = 34;
  
  static final int TRANSACTION_getNotificationChannelGroups = 51;
  
  static final int TRANSACTION_getNotificationChannelGroupsForPackage = 33;
  
  static final int TRANSACTION_getNotificationChannelGroupsFromPrivilegedListener = 88;
  
  static final int TRANSACTION_getNotificationChannels = 44;
  
  static final int TRANSACTION_getNotificationChannelsBypassingDnd = 56;
  
  static final int TRANSACTION_getNotificationChannelsForPackage = 45;
  
  static final int TRANSACTION_getNotificationChannelsFromPrivilegedListener = 87;
  
  static final int TRANSACTION_getNotificationDelegate = 131;
  
  static final int TRANSACTION_getNotificationHistory = 64;
  
  static final int TRANSACTION_getNotificationPolicy = 114;
  
  static final int TRANSACTION_getNumNotificationChannelsForPackage = 46;
  
  static final int TRANSACTION_getPackageImportance = 19;
  
  static final int TRANSACTION_getPopulatedNotificationChannelGroupForPackage = 35;
  
  static final int TRANSACTION_getPrivateNotificationsAllowed = 134;
  
  static final int TRANSACTION_getRuleInstanceCount = 125;
  
  static final int TRANSACTION_getSnoozedNotificationsFromListener = 77;
  
  static final int TRANSACTION_getZenMode = 108;
  
  static final int TRANSACTION_getZenModeConfig = 109;
  
  static final int TRANSACTION_getZenRules = 120;
  
  static final int TRANSACTION_hasSentValidMsg = 11;
  
  static final int TRANSACTION_hasUserDemotedInvalidMsgApp = 13;
  
  static final int TRANSACTION_isInInvalidMsgState = 12;
  
  static final int TRANSACTION_isNotificationAssistantAccessGranted = 99;
  
  static final int TRANSACTION_isNotificationListenerAccessGranted = 97;
  
  static final int TRANSACTION_isNotificationListenerAccessGrantedForUser = 98;
  
  static final int TRANSACTION_isNotificationPolicyAccessGranted = 113;
  
  static final int TRANSACTION_isNotificationPolicyAccessGrantedForPackage = 116;
  
  static final int TRANSACTION_isPackagePaused = 57;
  
  static final int TRANSACTION_isSystemConditionProviderEnabled = 96;
  
  static final int TRANSACTION_matchesCallFilter = 95;
  
  static final int TRANSACTION_notifyConditions = 112;
  
  static final int TRANSACTION_onlyHasDefaultChannel = 52;
  
  static final int TRANSACTION_pullStats = 135;
  
  static final int TRANSACTION_registerListener = 65;
  
  static final int TRANSACTION_removeAutomaticZenRule = 123;
  
  static final int TRANSACTION_removeAutomaticZenRules = 124;
  
  static final int TRANSACTION_requestBindListener = 71;
  
  static final int TRANSACTION_requestBindProvider = 73;
  
  static final int TRANSACTION_requestHintsFromListener = 79;
  
  static final int TRANSACTION_requestInterruptionFilterFromListener = 81;
  
  static final int TRANSACTION_requestUnbindListener = 72;
  
  static final int TRANSACTION_requestUnbindProvider = 74;
  
  static final int TRANSACTION_setAutomaticZenRuleState = 126;
  
  static final int TRANSACTION_setBubblesAllowed = 25;
  
  static final int TRANSACTION_setHideSilentStatusIcons = 24;
  
  static final int TRANSACTION_setInterruptionFilter = 84;
  
  static final int TRANSACTION_setInvalidMsgAppDemoted = 14;
  
  static final int TRANSACTION_setNotificationAssistantAccessGranted = 101;
  
  static final int TRANSACTION_setNotificationAssistantAccessGrantedForUser = 103;
  
  static final int TRANSACTION_setNotificationDelegate = 130;
  
  static final int TRANSACTION_setNotificationListenerAccessGranted = 100;
  
  static final int TRANSACTION_setNotificationListenerAccessGrantedForUser = 102;
  
  static final int TRANSACTION_setNotificationPolicy = 115;
  
  static final int TRANSACTION_setNotificationPolicyAccessGranted = 117;
  
  static final int TRANSACTION_setNotificationPolicyAccessGrantedForUser = 118;
  
  static final int TRANSACTION_setNotificationsEnabledForPackage = 15;
  
  static final int TRANSACTION_setNotificationsEnabledWithImportanceLockForPackage = 16;
  
  static final int TRANSACTION_setNotificationsShownFromListener = 75;
  
  static final int TRANSACTION_setOnNotificationPostedTrimFromListener = 83;
  
  static final int TRANSACTION_setPrivateNotificationsAllowed = 133;
  
  static final int TRANSACTION_setShowBadge = 9;
  
  static final int TRANSACTION_setZenMode = 111;
  
  static final int TRANSACTION_shouldHideSilentStatusIcons = 23;
  
  static final int TRANSACTION_silenceNotificationSound = 59;
  
  static final int TRANSACTION_snoozeNotificationUntilContextFromListener = 69;
  
  static final int TRANSACTION_snoozeNotificationUntilFromListener = 70;
  
  static final int TRANSACTION_unregisterListener = 66;
  
  static final int TRANSACTION_unsnoozeNotificationFromAssistant = 92;
  
  static final int TRANSACTION_unsnoozeNotificationFromSystemListener = 93;
  
  static final int TRANSACTION_updateAutomaticZenRule = 122;
  
  static final int TRANSACTION_updateNotificationChannelForPackage = 37;
  
  static final int TRANSACTION_updateNotificationChannelFromPrivilegedListener = 86;
  
  static final int TRANSACTION_updateNotificationChannelGroupForPackage = 36;
  
  static final int TRANSACTION_updateNotificationChannelGroupFromPrivilegedListener = 85;
  
  public Stub() {
    attachInterface(this, "android.app.INotificationManager");
  }
  
  public static INotificationManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.INotificationManager");
    return (iInterface != null && iInterface instanceof INotificationManager) ? (INotificationManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static INotificationManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 135:
        return "pullStats";
      case 134:
        return "getPrivateNotificationsAllowed";
      case 133:
        return "setPrivateNotificationsAllowed";
      case 132:
        return "canNotifyAsPackage";
      case 131:
        return "getNotificationDelegate";
      case 130:
        return "setNotificationDelegate";
      case 129:
        return "getAppActiveNotifications";
      case 128:
        return "applyRestore";
      case 127:
        return "getBackupPayload";
      case 126:
        return "setAutomaticZenRuleState";
      case 125:
        return "getRuleInstanceCount";
      case 124:
        return "removeAutomaticZenRules";
      case 123:
        return "removeAutomaticZenRule";
      case 122:
        return "updateAutomaticZenRule";
      case 121:
        return "addAutomaticZenRule";
      case 120:
        return "getZenRules";
      case 119:
        return "getAutomaticZenRule";
      case 118:
        return "setNotificationPolicyAccessGrantedForUser";
      case 117:
        return "setNotificationPolicyAccessGranted";
      case 116:
        return "isNotificationPolicyAccessGrantedForPackage";
      case 115:
        return "setNotificationPolicy";
      case 114:
        return "getNotificationPolicy";
      case 113:
        return "isNotificationPolicyAccessGranted";
      case 112:
        return "notifyConditions";
      case 111:
        return "setZenMode";
      case 110:
        return "getConsolidatedNotificationPolicy";
      case 109:
        return "getZenModeConfig";
      case 108:
        return "getZenMode";
      case 107:
        return "getAllowedNotificationAssistant";
      case 106:
        return "getAllowedNotificationAssistantForUser";
      case 105:
        return "getEnabledNotificationListeners";
      case 104:
        return "getEnabledNotificationListenerPackages";
      case 103:
        return "setNotificationAssistantAccessGrantedForUser";
      case 102:
        return "setNotificationListenerAccessGrantedForUser";
      case 101:
        return "setNotificationAssistantAccessGranted";
      case 100:
        return "setNotificationListenerAccessGranted";
      case 99:
        return "isNotificationAssistantAccessGranted";
      case 98:
        return "isNotificationListenerAccessGrantedForUser";
      case 97:
        return "isNotificationListenerAccessGranted";
      case 96:
        return "isSystemConditionProviderEnabled";
      case 95:
        return "matchesCallFilter";
      case 94:
        return "getEffectsSuppressor";
      case 93:
        return "unsnoozeNotificationFromSystemListener";
      case 92:
        return "unsnoozeNotificationFromAssistant";
      case 91:
        return "applyAdjustmentsFromAssistant";
      case 90:
        return "applyAdjustmentFromAssistant";
      case 89:
        return "applyEnqueuedAdjustmentFromAssistant";
      case 88:
        return "getNotificationChannelGroupsFromPrivilegedListener";
      case 87:
        return "getNotificationChannelsFromPrivilegedListener";
      case 86:
        return "updateNotificationChannelFromPrivilegedListener";
      case 85:
        return "updateNotificationChannelGroupFromPrivilegedListener";
      case 84:
        return "setInterruptionFilter";
      case 83:
        return "setOnNotificationPostedTrimFromListener";
      case 82:
        return "getInterruptionFilterFromListener";
      case 81:
        return "requestInterruptionFilterFromListener";
      case 80:
        return "getHintsFromListener";
      case 79:
        return "requestHintsFromListener";
      case 78:
        return "clearRequestedListenerHints";
      case 77:
        return "getSnoozedNotificationsFromListener";
      case 76:
        return "getActiveNotificationsFromListener";
      case 75:
        return "setNotificationsShownFromListener";
      case 74:
        return "requestUnbindProvider";
      case 73:
        return "requestBindProvider";
      case 72:
        return "requestUnbindListener";
      case 71:
        return "requestBindListener";
      case 70:
        return "snoozeNotificationUntilFromListener";
      case 69:
        return "snoozeNotificationUntilContextFromListener";
      case 68:
        return "cancelNotificationsFromListener";
      case 67:
        return "cancelNotificationFromListener";
      case 66:
        return "unregisterListener";
      case 65:
        return "registerListener";
      case 64:
        return "getNotificationHistory";
      case 63:
        return "getHistoricalNotificationsWithAttribution";
      case 62:
        return "getHistoricalNotifications";
      case 61:
        return "getActiveNotificationsWithAttribution";
      case 60:
        return "getActiveNotifications";
      case 59:
        return "silenceNotificationSound";
      case 58:
        return "deleteNotificationHistoryItem";
      case 57:
        return "isPackagePaused";
      case 56:
        return "getNotificationChannelsBypassingDnd";
      case 55:
        return "getAppsBypassingDndCount";
      case 54:
        return "areChannelsBypassingDnd";
      case 53:
        return "getBlockedAppCount";
      case 52:
        return "onlyHasDefaultChannel";
      case 51:
        return "getNotificationChannelGroups";
      case 50:
        return "getNotificationChannelGroup";
      case 49:
        return "deleteNotificationChannelGroup";
      case 48:
        return "getBlockedChannelCount";
      case 47:
        return "getDeletedChannelCount";
      case 46:
        return "getNumNotificationChannelsForPackage";
      case 45:
        return "getNotificationChannelsForPackage";
      case 44:
        return "getNotificationChannels";
      case 43:
        return "deleteConversationNotificationChannels";
      case 42:
        return "deleteNotificationChannel";
      case 41:
        return "getNotificationChannelForPackage";
      case 40:
        return "createConversationNotificationChannelForPackage";
      case 39:
        return "getConversationNotificationChannel";
      case 38:
        return "getNotificationChannel";
      case 37:
        return "updateNotificationChannelForPackage";
      case 36:
        return "updateNotificationChannelGroupForPackage";
      case 35:
        return "getPopulatedNotificationChannelGroupForPackage";
      case 34:
        return "getNotificationChannelGroupForPackage";
      case 33:
        return "getNotificationChannelGroupsForPackage";
      case 32:
        return "getConversationsForPackage";
      case 31:
        return "getConversations";
      case 30:
        return "createNotificationChannelsForPackage";
      case 29:
        return "createNotificationChannels";
      case 28:
        return "createNotificationChannelGroups";
      case 27:
        return "getBubblePreferenceForPackage";
      case 26:
        return "areBubblesAllowed";
      case 25:
        return "setBubblesAllowed";
      case 24:
        return "setHideSilentStatusIcons";
      case 23:
        return "shouldHideSilentStatusIcons";
      case 22:
        return "disallowAssistantAdjustment";
      case 21:
        return "allowAssistantAdjustment";
      case 20:
        return "getAllowedAssistantAdjustments";
      case 19:
        return "getPackageImportance";
      case 18:
        return "areNotificationsEnabled";
      case 17:
        return "areNotificationsEnabledForPackage";
      case 16:
        return "setNotificationsEnabledWithImportanceLockForPackage";
      case 15:
        return "setNotificationsEnabledForPackage";
      case 14:
        return "setInvalidMsgAppDemoted";
      case 13:
        return "hasUserDemotedInvalidMsgApp";
      case 12:
        return "isInInvalidMsgState";
      case 11:
        return "hasSentValidMsg";
      case 10:
        return "canShowBadge";
      case 9:
        return "setShowBadge";
      case 8:
        return "cancelNotificationWithTag";
      case 7:
        return "enqueueNotificationWithTag";
      case 6:
        return "finishToken";
      case 5:
        return "cancelToast";
      case 4:
        return "enqueueToast";
      case 3:
        return "enqueueTextToast";
      case 2:
        return "clearData";
      case 1:
        break;
    } 
    return "cancelAllNotifications";
  }
  
  public static boolean setDefaultImpl(INotificationManager paramINotificationManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramINotificationManager != null) {
        Proxy.sDefaultImpl = paramINotificationManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool10;
      int i5;
      boolean bool9;
      int i4;
      boolean bool8;
      int i3;
      boolean bool7;
      int i2;
      boolean bool6;
      int i1;
      boolean bool5;
      int n;
      boolean bool4;
      int m;
      boolean bool3;
      int k;
      boolean bool2;
      int j;
      boolean bool1;
      int i;
      String str2;
      ParceledListSlice parceledListSlice5;
      byte[] arrayOfByte;
      String str1;
      List<ZenModeConfig.ZenRule> list2;
      AutomaticZenRule automaticZenRule;
      NotificationManager.Policy policy;
      ZenModeConfig zenModeConfig;
      ComponentName componentName2;
      List<ComponentName> list1;
      ComponentName componentName1;
      ParceledListSlice parceledListSlice4;
      NotificationHistory notificationHistory;
      StatusBarNotification[] arrayOfStatusBarNotification;
      ParceledListSlice parceledListSlice3;
      NotificationChannelGroup notificationChannelGroup2;
      ParceledListSlice parceledListSlice2;
      NotificationChannel notificationChannel;
      NotificationChannelGroup notificationChannelGroup1;
      ParceledListSlice parceledListSlice1;
      List<String> list;
      long l;
      String str4;
      INotificationListener iNotificationListener1;
      String str3;
      INotificationListener iNotificationListener2;
      String str5;
      IBinder iBinder;
      String str6;
      String str7;
      boolean bool11 = false;
      boolean bool12 = false;
      boolean bool13 = false;
      boolean bool14 = false;
      boolean bool15 = false;
      boolean bool16 = false;
      boolean bool17 = false;
      boolean bool18 = false;
      boolean bool19 = false;
      boolean bool20 = false;
      boolean bool21 = false;
      boolean bool22 = false;
      boolean bool23 = false;
      boolean bool24 = false;
      boolean bool25 = false;
      boolean bool26 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 135:
          paramParcel1.enforceInterface("android.app.INotificationManager");
          l = paramParcel1.readLong();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          l = pullStats(l, paramInt1, bool26, new ArrayList<>());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 134:
          paramParcel1.enforceInterface("android.app.INotificationManager");
          bool10 = getPrivateNotificationsAllowed();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 133:
          paramParcel1.enforceInterface("android.app.INotificationManager");
          if (paramParcel1.readInt() != 0)
            bool26 = true; 
          setPrivateNotificationsAllowed(bool26);
          paramParcel2.writeNoException();
          return true;
        case 132:
          paramParcel1.enforceInterface("android.app.INotificationManager");
          bool10 = canNotifyAsPackage(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 131:
          paramParcel1.enforceInterface("android.app.INotificationManager");
          str2 = getNotificationDelegate(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 130:
          str2.enforceInterface("android.app.INotificationManager");
          setNotificationDelegate(str2.readString(), str2.readString());
          paramParcel2.writeNoException();
          return true;
        case 129:
          str2.enforceInterface("android.app.INotificationManager");
          parceledListSlice5 = getAppActiveNotifications(str2.readString(), str2.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice5 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice5.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 128:
          parceledListSlice5.enforceInterface("android.app.INotificationManager");
          applyRestore(parceledListSlice5.createByteArray(), parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          return true;
        case 127:
          parceledListSlice5.enforceInterface("android.app.INotificationManager");
          arrayOfByte = getBackupPayload(parceledListSlice5.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeByteArray(arrayOfByte);
          return true;
        case 126:
          arrayOfByte.enforceInterface("android.app.INotificationManager");
          str4 = arrayOfByte.readString();
          if (arrayOfByte.readInt() != 0) {
            Condition condition = (Condition)Condition.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            arrayOfByte = null;
          } 
          setAutomaticZenRuleState(str4, (Condition)arrayOfByte);
          paramParcel2.writeNoException();
          return true;
        case 125:
          arrayOfByte.enforceInterface("android.app.INotificationManager");
          if (arrayOfByte.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            arrayOfByte = null;
          } 
          i5 = getRuleInstanceCount((ComponentName)arrayOfByte);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          return true;
        case 124:
          arrayOfByte.enforceInterface("android.app.INotificationManager");
          bool9 = removeAutomaticZenRules(arrayOfByte.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 123:
          arrayOfByte.enforceInterface("android.app.INotificationManager");
          bool9 = removeAutomaticZenRule(arrayOfByte.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 122:
          arrayOfByte.enforceInterface("android.app.INotificationManager");
          str4 = arrayOfByte.readString();
          if (arrayOfByte.readInt() != 0) {
            AutomaticZenRule automaticZenRule1 = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            arrayOfByte = null;
          } 
          bool9 = updateAutomaticZenRule(str4, (AutomaticZenRule)arrayOfByte);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 121:
          arrayOfByte.enforceInterface("android.app.INotificationManager");
          if (arrayOfByte.readInt() != 0) {
            AutomaticZenRule automaticZenRule1 = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            arrayOfByte = null;
          } 
          str1 = addAutomaticZenRule((AutomaticZenRule)arrayOfByte);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 120:
          str1.enforceInterface("android.app.INotificationManager");
          list2 = getZenRules();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list2);
          return true;
        case 119:
          list2.enforceInterface("android.app.INotificationManager");
          automaticZenRule = getAutomaticZenRule(list2.readString());
          paramParcel2.writeNoException();
          if (automaticZenRule != null) {
            paramParcel2.writeInt(1);
            automaticZenRule.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 118:
          automaticZenRule.enforceInterface("android.app.INotificationManager");
          str4 = automaticZenRule.readString();
          i4 = automaticZenRule.readInt();
          bool26 = bool11;
          if (automaticZenRule.readInt() != 0)
            bool26 = true; 
          setNotificationPolicyAccessGrantedForUser(str4, i4, bool26);
          paramParcel2.writeNoException();
          return true;
        case 117:
          automaticZenRule.enforceInterface("android.app.INotificationManager");
          str4 = automaticZenRule.readString();
          bool26 = bool12;
          if (automaticZenRule.readInt() != 0)
            bool26 = true; 
          setNotificationPolicyAccessGranted(str4, bool26);
          paramParcel2.writeNoException();
          return true;
        case 116:
          automaticZenRule.enforceInterface("android.app.INotificationManager");
          bool8 = isNotificationPolicyAccessGrantedForPackage(automaticZenRule.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 115:
          automaticZenRule.enforceInterface("android.app.INotificationManager");
          str4 = automaticZenRule.readString();
          if (automaticZenRule.readInt() != 0) {
            NotificationManager.Policy policy1 = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel((Parcel)automaticZenRule);
          } else {
            automaticZenRule = null;
          } 
          setNotificationPolicy(str4, (NotificationManager.Policy)automaticZenRule);
          paramParcel2.writeNoException();
          return true;
        case 114:
          automaticZenRule.enforceInterface("android.app.INotificationManager");
          policy = getNotificationPolicy(automaticZenRule.readString());
          paramParcel2.writeNoException();
          if (policy != null) {
            paramParcel2.writeInt(1);
            policy.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 113:
          policy.enforceInterface("android.app.INotificationManager");
          bool8 = isNotificationPolicyAccessGranted(policy.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 112:
          policy.enforceInterface("android.app.INotificationManager");
          notifyConditions(policy.readString(), IConditionProvider.Stub.asInterface(policy.readStrongBinder()), (Condition[])policy.createTypedArray(Condition.CREATOR));
          return true;
        case 111:
          policy.enforceInterface("android.app.INotificationManager");
          i3 = policy.readInt();
          if (policy.readInt() != 0) {
            Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)policy);
          } else {
            paramParcel2 = null;
          } 
          setZenMode(i3, (Uri)paramParcel2, policy.readString());
          return true;
        case 110:
          policy.enforceInterface("android.app.INotificationManager");
          policy = getConsolidatedNotificationPolicy();
          paramParcel2.writeNoException();
          if (policy != null) {
            paramParcel2.writeInt(1);
            policy.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 109:
          policy.enforceInterface("android.app.INotificationManager");
          zenModeConfig = getZenModeConfig();
          paramParcel2.writeNoException();
          if (zenModeConfig != null) {
            paramParcel2.writeInt(1);
            zenModeConfig.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 108:
          zenModeConfig.enforceInterface("android.app.INotificationManager");
          i3 = getZenMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i3);
          return true;
        case 107:
          zenModeConfig.enforceInterface("android.app.INotificationManager");
          componentName2 = getAllowedNotificationAssistant();
          paramParcel2.writeNoException();
          if (componentName2 != null) {
            paramParcel2.writeInt(1);
            componentName2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 106:
          componentName2.enforceInterface("android.app.INotificationManager");
          componentName2 = getAllowedNotificationAssistantForUser(componentName2.readInt());
          paramParcel2.writeNoException();
          if (componentName2 != null) {
            paramParcel2.writeInt(1);
            componentName2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 105:
          componentName2.enforceInterface("android.app.INotificationManager");
          list1 = getEnabledNotificationListeners(componentName2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list1);
          return true;
        case 104:
          list1.enforceInterface("android.app.INotificationManager");
          list1 = (List)getEnabledNotificationListenerPackages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list1);
          return true;
        case 103:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str4 = null;
          } 
          i3 = list1.readInt();
          bool26 = bool13;
          if (list1.readInt() != 0)
            bool26 = true; 
          setNotificationAssistantAccessGrantedForUser((ComponentName)str4, i3, bool26);
          paramParcel2.writeNoException();
          return true;
        case 102:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str4 = null;
          } 
          i3 = list1.readInt();
          bool26 = bool14;
          if (list1.readInt() != 0)
            bool26 = true; 
          setNotificationListenerAccessGrantedForUser((ComponentName)str4, i3, bool26);
          paramParcel2.writeNoException();
          return true;
        case 101:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str4 = null;
          } 
          bool26 = bool15;
          if (list1.readInt() != 0)
            bool26 = true; 
          setNotificationAssistantAccessGranted((ComponentName)str4, bool26);
          paramParcel2.writeNoException();
          return true;
        case 100:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str4 = null;
          } 
          bool26 = bool16;
          if (list1.readInt() != 0)
            bool26 = true; 
          setNotificationListenerAccessGranted((ComponentName)str4, bool26);
          paramParcel2.writeNoException();
          return true;
        case 99:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            list1 = null;
          } 
          bool7 = isNotificationAssistantAccessGranted((ComponentName)list1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 98:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            str4 = null;
          } 
          bool7 = isNotificationListenerAccessGrantedForUser((ComponentName)str4, list1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 97:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list1);
          } else {
            list1 = null;
          } 
          bool7 = isNotificationListenerAccessGranted((ComponentName)list1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 96:
          list1.enforceInterface("android.app.INotificationManager");
          bool7 = isSystemConditionProviderEnabled(list1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 95:
          list1.enforceInterface("android.app.INotificationManager");
          if (list1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list1);
          } else {
            list1 = null;
          } 
          bool7 = matchesCallFilter((Bundle)list1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 94:
          list1.enforceInterface("android.app.INotificationManager");
          componentName1 = getEffectsSuppressor();
          paramParcel2.writeNoException();
          if (componentName1 != null) {
            paramParcel2.writeInt(1);
            componentName1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 93:
          componentName1.enforceInterface("android.app.INotificationManager");
          unsnoozeNotificationFromSystemListener(INotificationListener.Stub.asInterface(componentName1.readStrongBinder()), componentName1.readString());
          paramParcel2.writeNoException();
          return true;
        case 92:
          componentName1.enforceInterface("android.app.INotificationManager");
          unsnoozeNotificationFromAssistant(INotificationListener.Stub.asInterface(componentName1.readStrongBinder()), componentName1.readString());
          paramParcel2.writeNoException();
          return true;
        case 91:
          componentName1.enforceInterface("android.app.INotificationManager");
          applyAdjustmentsFromAssistant(INotificationListener.Stub.asInterface(componentName1.readStrongBinder()), componentName1.createTypedArrayList(Adjustment.CREATOR));
          paramParcel2.writeNoException();
          return true;
        case 90:
          componentName1.enforceInterface("android.app.INotificationManager");
          iNotificationListener1 = INotificationListener.Stub.asInterface(componentName1.readStrongBinder());
          if (componentName1.readInt() != 0) {
            Adjustment adjustment = (Adjustment)Adjustment.CREATOR.createFromParcel((Parcel)componentName1);
          } else {
            componentName1 = null;
          } 
          applyAdjustmentFromAssistant(iNotificationListener1, (Adjustment)componentName1);
          paramParcel2.writeNoException();
          return true;
        case 89:
          componentName1.enforceInterface("android.app.INotificationManager");
          iNotificationListener1 = INotificationListener.Stub.asInterface(componentName1.readStrongBinder());
          if (componentName1.readInt() != 0) {
            Adjustment adjustment = (Adjustment)Adjustment.CREATOR.createFromParcel((Parcel)componentName1);
          } else {
            componentName1 = null;
          } 
          applyEnqueuedAdjustmentFromAssistant(iNotificationListener1, (Adjustment)componentName1);
          paramParcel2.writeNoException();
          return true;
        case 88:
          componentName1.enforceInterface("android.app.INotificationManager");
          iNotificationListener2 = INotificationListener.Stub.asInterface(componentName1.readStrongBinder());
          str3 = componentName1.readString();
          if (componentName1.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)componentName1);
          } else {
            componentName1 = null;
          } 
          parceledListSlice4 = getNotificationChannelGroupsFromPrivilegedListener(iNotificationListener2, str3, (UserHandle)componentName1);
          paramParcel2.writeNoException();
          if (parceledListSlice4 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice4.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 87:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          iNotificationListener2 = INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder());
          str3 = parceledListSlice4.readString();
          if (parceledListSlice4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            parceledListSlice4 = null;
          } 
          parceledListSlice4 = getNotificationChannelsFromPrivilegedListener(iNotificationListener2, str3, (UserHandle)parceledListSlice4);
          paramParcel2.writeNoException();
          if (parceledListSlice4 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice4.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 86:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          iNotificationListener2 = INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder());
          str6 = parceledListSlice4.readString();
          if (parceledListSlice4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            str3 = null;
          } 
          if (parceledListSlice4.readInt() != 0) {
            NotificationChannel notificationChannel1 = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            parceledListSlice4 = null;
          } 
          updateNotificationChannelFromPrivilegedListener(iNotificationListener2, str6, (UserHandle)str3, (NotificationChannel)parceledListSlice4);
          paramParcel2.writeNoException();
          return true;
        case 85:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          iNotificationListener2 = INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder());
          str6 = parceledListSlice4.readString();
          if (parceledListSlice4.readInt() != 0) {
            UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            str3 = null;
          } 
          if (parceledListSlice4.readInt() != 0) {
            NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            parceledListSlice4 = null;
          } 
          updateNotificationChannelGroupFromPrivilegedListener(iNotificationListener2, str6, (UserHandle)str3, (NotificationChannelGroup)parceledListSlice4);
          paramParcel2.writeNoException();
          return true;
        case 84:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          setInterruptionFilter(parceledListSlice4.readString(), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 83:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          setOnNotificationPostedTrimFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 82:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          i2 = getInterruptionFilterFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 81:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          requestInterruptionFilterFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 80:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          i2 = getHintsFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i2);
          return true;
        case 79:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          requestHintsFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 78:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          clearRequestedListenerHints(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 77:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          parceledListSlice4 = getSnoozedNotificationsFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice4 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice4.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 76:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          parceledListSlice4 = getActiveNotificationsFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.createStringArray(), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice4 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice4.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 75:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          setNotificationsShownFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 74:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          requestUnbindProvider(IConditionProvider.Stub.asInterface(parceledListSlice4.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 73:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          if (parceledListSlice4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            parceledListSlice4 = null;
          } 
          requestBindProvider((ComponentName)parceledListSlice4);
          paramParcel2.writeNoException();
          return true;
        case 72:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          requestUnbindListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 71:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          if (parceledListSlice4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            parceledListSlice4 = null;
          } 
          requestBindListener((ComponentName)parceledListSlice4);
          paramParcel2.writeNoException();
          return true;
        case 70:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          snoozeNotificationUntilFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readString(), parceledListSlice4.readLong());
          paramParcel2.writeNoException();
          return true;
        case 69:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          snoozeNotificationUntilContextFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readString(), parceledListSlice4.readString());
          paramParcel2.writeNoException();
          return true;
        case 68:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          cancelNotificationsFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 67:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          cancelNotificationFromListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readString(), parceledListSlice4.readString(), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 66:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          unregisterListener(INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder()), parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 65:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          iNotificationListener2 = INotificationListener.Stub.asInterface(parceledListSlice4.readStrongBinder());
          if (parceledListSlice4.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)parceledListSlice4);
          } else {
            str3 = null;
          } 
          registerListener(iNotificationListener2, (ComponentName)str3, parceledListSlice4.readInt());
          paramParcel2.writeNoException();
          return true;
        case 64:
          parceledListSlice4.enforceInterface("android.app.INotificationManager");
          notificationHistory = getNotificationHistory(parceledListSlice4.readString(), parceledListSlice4.readString());
          paramParcel2.writeNoException();
          if (notificationHistory != null) {
            paramParcel2.writeInt(1);
            notificationHistory.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 63:
          notificationHistory.enforceInterface("android.app.INotificationManager");
          str3 = notificationHistory.readString();
          str5 = notificationHistory.readString();
          i2 = notificationHistory.readInt();
          bool26 = bool17;
          if (notificationHistory.readInt() != 0)
            bool26 = true; 
          arrayOfStatusBarNotification = getHistoricalNotificationsWithAttribution(str3, str5, i2, bool26);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfStatusBarNotification, 1);
          return true;
        case 62:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          str3 = arrayOfStatusBarNotification.readString();
          i2 = arrayOfStatusBarNotification.readInt();
          bool26 = bool18;
          if (arrayOfStatusBarNotification.readInt() != 0)
            bool26 = true; 
          arrayOfStatusBarNotification = getHistoricalNotifications(str3, i2, bool26);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfStatusBarNotification, 1);
          return true;
        case 61:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          arrayOfStatusBarNotification = getActiveNotificationsWithAttribution(arrayOfStatusBarNotification.readString(), arrayOfStatusBarNotification.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfStatusBarNotification, 1);
          return true;
        case 60:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          arrayOfStatusBarNotification = getActiveNotifications(arrayOfStatusBarNotification.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfStatusBarNotification, 1);
          return true;
        case 59:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          silenceNotificationSound();
          paramParcel2.writeNoException();
          return true;
        case 58:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          deleteNotificationHistoryItem(arrayOfStatusBarNotification.readString(), arrayOfStatusBarNotification.readInt(), arrayOfStatusBarNotification.readLong());
          paramParcel2.writeNoException();
          return true;
        case 57:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          bool6 = isPackagePaused(arrayOfStatusBarNotification.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 56:
          arrayOfStatusBarNotification.enforceInterface("android.app.INotificationManager");
          parceledListSlice3 = getNotificationChannelsBypassingDnd(arrayOfStatusBarNotification.readString(), arrayOfStatusBarNotification.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice3 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice3.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 55:
          parceledListSlice3.enforceInterface("android.app.INotificationManager");
          i1 = getAppsBypassingDndCount(parceledListSlice3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 54:
          parceledListSlice3.enforceInterface("android.app.INotificationManager");
          bool5 = areChannelsBypassingDnd();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 53:
          parceledListSlice3.enforceInterface("android.app.INotificationManager");
          n = getBlockedAppCount(parceledListSlice3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(n);
          return true;
        case 52:
          parceledListSlice3.enforceInterface("android.app.INotificationManager");
          bool4 = onlyHasDefaultChannel(parceledListSlice3.readString(), parceledListSlice3.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool4);
          return true;
        case 51:
          parceledListSlice3.enforceInterface("android.app.INotificationManager");
          parceledListSlice3 = getNotificationChannelGroups(parceledListSlice3.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice3 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice3.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 50:
          parceledListSlice3.enforceInterface("android.app.INotificationManager");
          notificationChannelGroup2 = getNotificationChannelGroup(parceledListSlice3.readString(), parceledListSlice3.readString());
          paramParcel2.writeNoException();
          if (notificationChannelGroup2 != null) {
            paramParcel2.writeInt(1);
            notificationChannelGroup2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 49:
          notificationChannelGroup2.enforceInterface("android.app.INotificationManager");
          deleteNotificationChannelGroup(notificationChannelGroup2.readString(), notificationChannelGroup2.readString());
          paramParcel2.writeNoException();
          return true;
        case 48:
          notificationChannelGroup2.enforceInterface("android.app.INotificationManager");
          m = getBlockedChannelCount(notificationChannelGroup2.readString(), notificationChannelGroup2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 47:
          notificationChannelGroup2.enforceInterface("android.app.INotificationManager");
          m = getDeletedChannelCount(notificationChannelGroup2.readString(), notificationChannelGroup2.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 46:
          notificationChannelGroup2.enforceInterface("android.app.INotificationManager");
          str3 = notificationChannelGroup2.readString();
          m = notificationChannelGroup2.readInt();
          bool26 = bool19;
          if (notificationChannelGroup2.readInt() != 0)
            bool26 = true; 
          m = getNumNotificationChannelsForPackage(str3, m, bool26);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 45:
          notificationChannelGroup2.enforceInterface("android.app.INotificationManager");
          str3 = notificationChannelGroup2.readString();
          m = notificationChannelGroup2.readInt();
          if (notificationChannelGroup2.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          parceledListSlice2 = getNotificationChannelsForPackage(str3, m, bool26);
          paramParcel2.writeNoException();
          if (parceledListSlice2 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 44:
          parceledListSlice2.enforceInterface("android.app.INotificationManager");
          parceledListSlice2 = getNotificationChannels(parceledListSlice2.readString(), parceledListSlice2.readString(), parceledListSlice2.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice2 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice2.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 43:
          parceledListSlice2.enforceInterface("android.app.INotificationManager");
          deleteConversationNotificationChannels(parceledListSlice2.readString(), parceledListSlice2.readInt(), parceledListSlice2.readString());
          paramParcel2.writeNoException();
          return true;
        case 42:
          parceledListSlice2.enforceInterface("android.app.INotificationManager");
          deleteNotificationChannel(parceledListSlice2.readString(), parceledListSlice2.readString());
          paramParcel2.writeNoException();
          return true;
        case 41:
          parceledListSlice2.enforceInterface("android.app.INotificationManager");
          str5 = parceledListSlice2.readString();
          m = parceledListSlice2.readInt();
          str3 = parceledListSlice2.readString();
          str6 = parceledListSlice2.readString();
          if (parceledListSlice2.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          notificationChannel = getNotificationChannelForPackage(str5, m, str3, str6, bool26);
          paramParcel2.writeNoException();
          if (notificationChannel != null) {
            paramParcel2.writeInt(1);
            notificationChannel.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 40:
          notificationChannel.enforceInterface("android.app.INotificationManager");
          str6 = notificationChannel.readString();
          m = notificationChannel.readInt();
          str5 = notificationChannel.readString();
          if (notificationChannel.readInt() != 0) {
            NotificationChannel notificationChannel1 = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel((Parcel)notificationChannel);
          } else {
            str3 = null;
          } 
          createConversationNotificationChannelForPackage(str6, m, str5, (NotificationChannel)str3, notificationChannel.readString());
          paramParcel2.writeNoException();
          return true;
        case 39:
          notificationChannel.enforceInterface("android.app.INotificationManager");
          str3 = notificationChannel.readString();
          m = notificationChannel.readInt();
          str6 = notificationChannel.readString();
          str5 = notificationChannel.readString();
          if (notificationChannel.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          notificationChannel = getConversationNotificationChannel(str3, m, str6, str5, bool26, notificationChannel.readString());
          paramParcel2.writeNoException();
          if (notificationChannel != null) {
            paramParcel2.writeInt(1);
            notificationChannel.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 38:
          notificationChannel.enforceInterface("android.app.INotificationManager");
          notificationChannel = getNotificationChannel(notificationChannel.readString(), notificationChannel.readInt(), notificationChannel.readString(), notificationChannel.readString());
          paramParcel2.writeNoException();
          if (notificationChannel != null) {
            paramParcel2.writeInt(1);
            notificationChannel.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 37:
          notificationChannel.enforceInterface("android.app.INotificationManager");
          str3 = notificationChannel.readString();
          m = notificationChannel.readInt();
          if (notificationChannel.readInt() != 0) {
            notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel((Parcel)notificationChannel);
          } else {
            notificationChannel = null;
          } 
          updateNotificationChannelForPackage(str3, m, notificationChannel);
          paramParcel2.writeNoException();
          return true;
        case 36:
          notificationChannel.enforceInterface("android.app.INotificationManager");
          str3 = notificationChannel.readString();
          m = notificationChannel.readInt();
          if (notificationChannel.readInt() != 0) {
            NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel((Parcel)notificationChannel);
          } else {
            notificationChannel = null;
          } 
          updateNotificationChannelGroupForPackage(str3, m, (NotificationChannelGroup)notificationChannel);
          paramParcel2.writeNoException();
          return true;
        case 35:
          notificationChannel.enforceInterface("android.app.INotificationManager");
          str5 = notificationChannel.readString();
          m = notificationChannel.readInt();
          str3 = notificationChannel.readString();
          if (notificationChannel.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          notificationChannelGroup1 = getPopulatedNotificationChannelGroupForPackage(str5, m, str3, bool26);
          paramParcel2.writeNoException();
          if (notificationChannelGroup1 != null) {
            paramParcel2.writeInt(1);
            notificationChannelGroup1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 34:
          notificationChannelGroup1.enforceInterface("android.app.INotificationManager");
          notificationChannelGroup1 = getNotificationChannelGroupForPackage(notificationChannelGroup1.readString(), notificationChannelGroup1.readString(), notificationChannelGroup1.readInt());
          paramParcel2.writeNoException();
          if (notificationChannelGroup1 != null) {
            paramParcel2.writeInt(1);
            notificationChannelGroup1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 33:
          notificationChannelGroup1.enforceInterface("android.app.INotificationManager");
          str3 = notificationChannelGroup1.readString();
          m = notificationChannelGroup1.readInt();
          if (notificationChannelGroup1.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          parceledListSlice1 = getNotificationChannelGroupsForPackage(str3, m, bool26);
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 32:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          parceledListSlice1 = getConversationsForPackage(parceledListSlice1.readString(), parceledListSlice1.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 31:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          if (parceledListSlice1.readInt() != 0) {
            bool26 = true;
          } else {
            bool26 = false;
          } 
          parceledListSlice1 = getConversations(bool26);
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 30:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          str3 = parceledListSlice1.readString();
          m = parceledListSlice1.readInt();
          if (parceledListSlice1.readInt() != 0) {
            parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            parceledListSlice1 = null;
          } 
          createNotificationChannelsForPackage(str3, m, parceledListSlice1);
          paramParcel2.writeNoException();
          return true;
        case 29:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          str3 = parceledListSlice1.readString();
          if (parceledListSlice1.readInt() != 0) {
            parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            parceledListSlice1 = null;
          } 
          createNotificationChannels(str3, parceledListSlice1);
          paramParcel2.writeNoException();
          return true;
        case 28:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          str3 = parceledListSlice1.readString();
          if (parceledListSlice1.readInt() != 0) {
            parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)parceledListSlice1);
          } else {
            parceledListSlice1 = null;
          } 
          createNotificationChannelGroups(str3, parceledListSlice1);
          paramParcel2.writeNoException();
          return true;
        case 27:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          m = getBubblePreferenceForPackage(parceledListSlice1.readString(), parceledListSlice1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 26:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          bool3 = areBubblesAllowed(parceledListSlice1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 25:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          setBubblesAllowed(parceledListSlice1.readString(), parceledListSlice1.readInt(), parceledListSlice1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 24:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          bool26 = bool20;
          if (parceledListSlice1.readInt() != 0)
            bool26 = true; 
          setHideSilentStatusIcons(bool26);
          paramParcel2.writeNoException();
          return true;
        case 23:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          bool3 = shouldHideSilentStatusIcons(parceledListSlice1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 22:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          disallowAssistantAdjustment(parceledListSlice1.readString());
          paramParcel2.writeNoException();
          return true;
        case 21:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          allowAssistantAdjustment(parceledListSlice1.readString());
          paramParcel2.writeNoException();
          return true;
        case 20:
          parceledListSlice1.enforceInterface("android.app.INotificationManager");
          list = getAllowedAssistantAdjustments(parceledListSlice1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 19:
          list.enforceInterface("android.app.INotificationManager");
          k = getPackageImportance(list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          return true;
        case 18:
          list.enforceInterface("android.app.INotificationManager");
          bool2 = areNotificationsEnabled(list.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 17:
          list.enforceInterface("android.app.INotificationManager");
          bool2 = areNotificationsEnabledForPackage(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 16:
          list.enforceInterface("android.app.INotificationManager");
          str3 = list.readString();
          j = list.readInt();
          bool26 = bool21;
          if (list.readInt() != 0)
            bool26 = true; 
          setNotificationsEnabledWithImportanceLockForPackage(str3, j, bool26);
          paramParcel2.writeNoException();
          return true;
        case 15:
          list.enforceInterface("android.app.INotificationManager");
          str3 = list.readString();
          j = list.readInt();
          bool26 = bool22;
          if (list.readInt() != 0)
            bool26 = true; 
          setNotificationsEnabledForPackage(str3, j, bool26);
          paramParcel2.writeNoException();
          return true;
        case 14:
          list.enforceInterface("android.app.INotificationManager");
          str3 = list.readString();
          j = list.readInt();
          bool26 = bool23;
          if (list.readInt() != 0)
            bool26 = true; 
          setInvalidMsgAppDemoted(str3, j, bool26);
          paramParcel2.writeNoException();
          return true;
        case 13:
          list.enforceInterface("android.app.INotificationManager");
          bool1 = hasUserDemotedInvalidMsgApp(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 12:
          list.enforceInterface("android.app.INotificationManager");
          bool1 = isInInvalidMsgState(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 11:
          list.enforceInterface("android.app.INotificationManager");
          bool1 = hasSentValidMsg(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 10:
          list.enforceInterface("android.app.INotificationManager");
          bool1 = canShowBadge(list.readString(), list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 9:
          list.enforceInterface("android.app.INotificationManager");
          str3 = list.readString();
          i = list.readInt();
          bool26 = bool24;
          if (list.readInt() != 0)
            bool26 = true; 
          setShowBadge(str3, i, bool26);
          paramParcel2.writeNoException();
          return true;
        case 8:
          list.enforceInterface("android.app.INotificationManager");
          cancelNotificationWithTag(list.readString(), list.readString(), list.readString(), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.app.INotificationManager");
          str6 = list.readString();
          str5 = list.readString();
          str7 = list.readString();
          i = list.readInt();
          if (list.readInt() != 0) {
            Notification notification = (Notification)Notification.CREATOR.createFromParcel((Parcel)list);
          } else {
            str3 = null;
          } 
          enqueueNotificationWithTag(str6, str5, str7, i, (Notification)str3, list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 6:
          list.enforceInterface("android.app.INotificationManager");
          finishToken(list.readString(), list.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.app.INotificationManager");
          cancelToast(list.readString(), list.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.app.INotificationManager");
          enqueueToast(list.readString(), list.readStrongBinder(), ITransientNotification.Stub.asInterface(list.readStrongBinder()), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.app.INotificationManager");
          str6 = list.readString();
          iBinder = list.readStrongBinder();
          if (list.readInt() != 0) {
            CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)list);
          } else {
            str3 = null;
          } 
          enqueueTextToast(str6, iBinder, str3, list.readInt(), list.readInt(), ITransientNotificationCallback.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 2:
          list.enforceInterface("android.app.INotificationManager");
          str3 = list.readString();
          i = list.readInt();
          bool26 = bool25;
          if (list.readInt() != 0)
            bool26 = true; 
          clearData(str3, i, bool26);
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.app.INotificationManager");
      cancelAllNotifications(list.readString(), list.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.INotificationManager");
    return true;
  }
  
  private static class Proxy implements INotificationManager {
    public static INotificationManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public String addAutomaticZenRule(AutomaticZenRule param2AutomaticZenRule) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2AutomaticZenRule != null) {
          parcel1.writeInt(1);
          param2AutomaticZenRule.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(121, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().addAutomaticZenRule(param2AutomaticZenRule); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void allowAssistantAdjustment(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().allowAssistantAdjustment(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void applyAdjustmentFromAssistant(INotificationListener param2INotificationListener, Adjustment param2Adjustment) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Adjustment != null) {
          parcel1.writeInt(1);
          param2Adjustment.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(90, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().applyAdjustmentFromAssistant(param2INotificationListener, param2Adjustment);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void applyAdjustmentsFromAssistant(INotificationListener param2INotificationListener, List<Adjustment> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeTypedList(param2List);
        if (!this.mRemote.transact(91, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().applyAdjustmentsFromAssistant(param2INotificationListener, param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void applyEnqueuedAdjustmentFromAssistant(INotificationListener param2INotificationListener, Adjustment param2Adjustment) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Adjustment != null) {
          parcel1.writeInt(1);
          param2Adjustment.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(89, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().applyEnqueuedAdjustmentFromAssistant(param2INotificationListener, param2Adjustment);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void applyRestore(byte[] param2ArrayOfbyte, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(128, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().applyRestore(param2ArrayOfbyte, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean areBubblesAllowed(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(26, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().areBubblesAllowed(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean areChannelsBypassingDnd() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(54, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().areChannelsBypassingDnd();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean areNotificationsEnabled(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().areNotificationsEnabled(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean areNotificationsEnabledForPackage(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().areNotificationsEnabledForPackage(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean canNotifyAsPackage(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(132, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().canNotifyAsPackage(param2String1, param2String2, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean canShowBadge(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().canShowBadge(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelAllNotifications(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().cancelAllNotifications(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelNotificationFromListener(INotificationListener param2INotificationListener, String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(67, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().cancelNotificationFromListener(param2INotificationListener, param2String1, param2String2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelNotificationWithTag(String param2String1, String param2String2, String param2String3, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().cancelNotificationWithTag(param2String1, param2String2, param2String3, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelNotificationsFromListener(INotificationListener param2INotificationListener, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(68, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().cancelNotificationsFromListener(param2INotificationListener, param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelToast(String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().cancelToast(param2String, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearData(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().clearData(param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearRequestedListenerHints(INotificationListener param2INotificationListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(78, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().clearRequestedListenerHints(param2INotificationListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void createConversationNotificationChannelForPackage(String param2String1, int param2Int, String param2String2, NotificationChannel param2NotificationChannel, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (param2NotificationChannel != null) {
          parcel1.writeInt(1);
          param2NotificationChannel.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().createConversationNotificationChannelForPackage(param2String1, param2Int, param2String2, param2NotificationChannel, param2String3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void createNotificationChannelGroups(String param2String, ParceledListSlice param2ParceledListSlice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().createNotificationChannelGroups(param2String, param2ParceledListSlice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void createNotificationChannels(String param2String, ParceledListSlice param2ParceledListSlice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().createNotificationChannels(param2String, param2ParceledListSlice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void createNotificationChannelsForPackage(String param2String, int param2Int, ParceledListSlice param2ParceledListSlice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().createNotificationChannelsForPackage(param2String, param2Int, param2ParceledListSlice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteConversationNotificationChannels(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().deleteConversationNotificationChannels(param2String1, param2Int, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteNotificationChannel(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().deleteNotificationChannel(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteNotificationChannelGroup(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().deleteNotificationChannelGroup(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteNotificationHistoryItem(String param2String, int param2Int, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(58, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().deleteNotificationHistoryItem(param2String, param2Int, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disallowAssistantAdjustment(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().disallowAssistantAdjustment(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enqueueNotificationWithTag(String param2String1, String param2String2, String param2String3, int param2Int1, Notification param2Notification, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeString(param2String2);
            try {
              parcel1.writeString(param2String3);
              try {
                parcel1.writeInt(param2Int1);
                if (param2Notification != null) {
                  parcel1.writeInt(1);
                  param2Notification.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                try {
                  parcel1.writeInt(param2Int2);
                  if (!this.mRemote.transact(7, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
                    INotificationManager.Stub.getDefaultImpl().enqueueNotificationWithTag(param2String1, param2String2, param2String3, param2Int1, param2Notification, param2Int2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String1;
    }
    
    public void enqueueTextToast(String param2String, IBinder param2IBinder, CharSequence param2CharSequence, int param2Int1, int param2Int2, ITransientNotificationCallback param2ITransientNotificationCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        try {
          parcel1.writeString(param2String);
          try {
            parcel1.writeStrongBinder(param2IBinder);
            if (param2CharSequence != null) {
              parcel1.writeInt(1);
              TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param2Int1);
              try {
                IBinder iBinder;
                parcel1.writeInt(param2Int2);
                if (param2ITransientNotificationCallback != null) {
                  iBinder = param2ITransientNotificationCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                try {
                  if (!this.mRemote.transact(3, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
                    INotificationManager.Stub.getDefaultImpl().enqueueTextToast(param2String, param2IBinder, param2CharSequence, param2Int1, param2Int2, param2ITransientNotificationCallback);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public void enqueueToast(String param2String, IBinder param2IBinder, ITransientNotification param2ITransientNotification, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        if (param2ITransientNotification != null) {
          iBinder = param2ITransientNotification.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().enqueueToast(param2String, param2IBinder, param2ITransientNotification, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishToken(String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().finishToken(param2String, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StatusBarNotification[] getActiveNotifications(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(60, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getActiveNotifications(param2String); 
        parcel2.readException();
        return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getActiveNotificationsFromListener(INotificationListener param2INotificationListener, String[] param2ArrayOfString, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeStringArray(param2ArrayOfString);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(76, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getActiveNotificationsFromListener(param2INotificationListener, param2ArrayOfString, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2INotificationListener = null;
        } 
        return (ParceledListSlice)param2INotificationListener;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StatusBarNotification[] getActiveNotificationsWithAttribution(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(61, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getActiveNotificationsWithAttribution(param2String1, param2String2); 
        parcel2.readException();
        return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getAllowedAssistantAdjustments(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getAllowedAssistantAdjustments(param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getAllowedNotificationAssistant() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(107, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          componentName = INotificationManager.Stub.getDefaultImpl().getAllowedNotificationAssistant();
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getAllowedNotificationAssistantForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(106, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          componentName = INotificationManager.Stub.getDefaultImpl().getAllowedNotificationAssistantForUser(param2Int);
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAppActiveNotifications(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(129, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getAppActiveNotifications(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getAppsBypassingDndCount(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Int = INotificationManager.Stub.getDefaultImpl().getAppsBypassingDndCount(param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public AutomaticZenRule getAutomaticZenRule(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(119, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getAutomaticZenRule(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          AutomaticZenRule automaticZenRule = (AutomaticZenRule)AutomaticZenRule.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (AutomaticZenRule)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getBackupPayload(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(127, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getBackupPayload(param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getBlockedAppCount(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Int = INotificationManager.Stub.getDefaultImpl().getBlockedAppCount(param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getBlockedChannelCount(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Int = INotificationManager.Stub.getDefaultImpl().getBlockedChannelCount(param2String, param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getBubblePreferenceForPackage(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Int = INotificationManager.Stub.getDefaultImpl().getBubblePreferenceForPackage(param2String, param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        NotificationManager.Policy policy;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(110, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          policy = INotificationManager.Stub.getDefaultImpl().getConsolidatedNotificationPolicy();
          return policy;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          policy = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel(parcel2);
        } else {
          policy = null;
        } 
        return policy;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationChannel getConversationNotificationChannel(String param2String1, int param2Int, String param2String2, String param2String3, boolean param2Boolean, String param2String4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeInt(param2Int);
            try {
              parcel1.writeString(param2String2);
              try {
                boolean bool;
                parcel1.writeString(param2String3);
                if (param2Boolean) {
                  bool = true;
                } else {
                  bool = false;
                } 
                parcel1.writeInt(bool);
                try {
                  parcel1.writeString(param2String4);
                  try {
                    if (!this.mRemote.transact(39, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
                      NotificationChannel notificationChannel = INotificationManager.Stub.getDefaultImpl().getConversationNotificationChannel(param2String1, param2Int, param2String2, param2String3, param2Boolean, param2String4);
                      parcel2.recycle();
                      parcel1.recycle();
                      return notificationChannel;
                    } 
                    parcel2.readException();
                    if (parcel2.readInt() != 0) {
                      NotificationChannel notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel2);
                    } else {
                      param2String1 = null;
                    } 
                    parcel2.recycle();
                    parcel1.recycle();
                    return (NotificationChannel)param2String1;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String1;
    }
    
    public ParceledListSlice getConversations(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = INotificationManager.Stub.getDefaultImpl().getConversations(param2Boolean);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getConversationsForPackage(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getConversationsForPackage(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getDeletedChannelCount(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Int = INotificationManager.Stub.getDefaultImpl().getDeletedChannelCount(param2String, param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getEffectsSuppressor() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(94, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          componentName = INotificationManager.Stub.getDefaultImpl().getEffectsSuppressor();
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(104, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getEnabledNotificationListenerPackages(); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ComponentName> getEnabledNotificationListeners(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(105, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getEnabledNotificationListeners(param2Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ComponentName.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getHintsFromListener(INotificationListener param2INotificationListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(80, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getHintsFromListener(param2INotificationListener); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StatusBarNotification[] getHistoricalNotifications(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(62, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getHistoricalNotifications(param2String, param2Int, param2Boolean); 
        parcel2.readException();
        return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String param2String1, String param2String2, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(63, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getHistoricalNotificationsWithAttribution(param2String1, param2String2, param2Int, param2Boolean); 
        parcel2.readException();
        return (StatusBarNotification[])parcel2.createTypedArray(StatusBarNotification.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.INotificationManager";
    }
    
    public int getInterruptionFilterFromListener(INotificationListener param2INotificationListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(82, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getInterruptionFilterFromListener(param2INotificationListener); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationChannel getNotificationChannel(String param2String1, int param2Int, String param2String2, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannel(param2String1, param2Int, param2String2, param2String3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationChannel notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (NotificationChannel)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationChannel getNotificationChannelForPackage(String param2String1, int param2Int, String param2String2, String param2String3, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelForPackage(param2String1, param2Int, param2String2, param2String3, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationChannel notificationChannel = (NotificationChannel)NotificationChannel.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (NotificationChannel)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationChannelGroup getNotificationChannelGroup(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(50, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroup(param2String1, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (NotificationChannelGroup)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationChannelGroup getNotificationChannelGroupForPackage(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroupForPackage(param2String1, param2String2, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (NotificationChannelGroup)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannelGroups(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroups(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannelGroupsForPackage(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroupsForPackage(param2String, param2Int, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener param2INotificationListener, String param2String, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(88, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelGroupsFromPrivilegedListener(param2INotificationListener, param2String, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2INotificationListener = null;
        } 
        return (ParceledListSlice)param2INotificationListener;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannels(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannels(param2String1, param2String2, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannelsBypassingDnd(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(56, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelsBypassingDnd(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannelsForPackage(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelsForPackage(param2String, param2Int, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener param2INotificationListener, String param2String, UserHandle param2UserHandle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(87, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationChannelsFromPrivilegedListener(param2INotificationListener, param2String, param2UserHandle); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2INotificationListener = null;
        } 
        return (ParceledListSlice)param2INotificationListener;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getNotificationDelegate(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(131, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2String = INotificationManager.Stub.getDefaultImpl().getNotificationDelegate(param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationHistory getNotificationHistory(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(64, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationHistory(param2String1, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationHistory notificationHistory = (NotificationHistory)NotificationHistory.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (NotificationHistory)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationManager.Policy getNotificationPolicy(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(114, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getNotificationPolicy(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationManager.Policy policy = (NotificationManager.Policy)NotificationManager.Policy.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (NotificationManager.Policy)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getNumNotificationChannelsForPackage(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Int = INotificationManager.Stub.getDefaultImpl().getNumNotificationChannelsForPackage(param2String, param2Int, param2Boolean);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPackageImportance(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getPackageImportance(param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String param2String1, int param2Int, String param2String2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getPopulatedNotificationChannelGroupForPackage(param2String1, param2Int, param2String2, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup)NotificationChannelGroup.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (NotificationChannelGroup)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getPrivateNotificationsAllowed() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(134, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().getPrivateNotificationsAllowed();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getRuleInstanceCount(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(125, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getRuleInstanceCount(param2ComponentName); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener param2INotificationListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(77, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getSnoozedNotificationsFromListener(param2INotificationListener, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2INotificationListener = null;
        } 
        return (ParceledListSlice)param2INotificationListener;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getZenMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(108, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getZenMode(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ZenModeConfig getZenModeConfig() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ZenModeConfig zenModeConfig;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(109, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          zenModeConfig = INotificationManager.Stub.getDefaultImpl().getZenModeConfig();
          return zenModeConfig;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          zenModeConfig = (ZenModeConfig)ZenModeConfig.CREATOR.createFromParcel(parcel2);
        } else {
          zenModeConfig = null;
        } 
        return zenModeConfig;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(120, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null)
          return INotificationManager.Stub.getDefaultImpl().getZenRules(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ZenModeConfig.ZenRule.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasSentValidMsg(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(11, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().hasSentValidMsg(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasUserDemotedInvalidMsgApp(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().hasUserDemotedInvalidMsgApp(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isInInvalidMsgState(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(12, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isInInvalidMsgState(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNotificationAssistantAccessGranted(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(99, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isNotificationAssistantAccessGranted(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNotificationListenerAccessGranted(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(97, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isNotificationListenerAccessGranted(param2ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNotificationListenerAccessGrantedForUser(ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(98, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isNotificationListenerAccessGrantedForUser(param2ComponentName, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNotificationPolicyAccessGranted(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(113, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isNotificationPolicyAccessGranted(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isNotificationPolicyAccessGrantedForPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(116, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isNotificationPolicyAccessGrantedForPackage(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isPackagePaused(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(57, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isPackagePaused(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isSystemConditionProviderEnabled(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(96, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().isSystemConditionProviderEnabled(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean matchesCallFilter(Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(95, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().matchesCallFilter(param2Bundle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyConditions(String param2String, IConditionProvider param2IConditionProvider, Condition[] param2ArrayOfCondition) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.INotificationManager");
        parcel.writeString(param2String);
        if (param2IConditionProvider != null) {
          iBinder = param2IConditionProvider.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeTypedArray((Parcelable[])param2ArrayOfCondition, 0);
        if (!this.mRemote.transact(112, parcel, null, 1) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().notifyConditions(param2String, param2IConditionProvider, param2ArrayOfCondition);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean onlyHasDefaultChannel(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(52, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().onlyHasDefaultChannel(param2String, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long pullStats(long param2Long, int param2Int, boolean param2Boolean, List<ParcelFileDescriptor> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeLong(param2Long);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(135, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          param2Long = INotificationManager.Stub.getDefaultImpl().pullStats(param2Long, param2Int, param2Boolean, param2List);
          return param2Long;
        } 
        parcel2.readException();
        param2Long = parcel2.readLong();
        return param2Long;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerListener(INotificationListener param2INotificationListener, ComponentName param2ComponentName, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(65, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().registerListener(param2INotificationListener, param2ComponentName, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeAutomaticZenRule(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(123, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().removeAutomaticZenRule(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeAutomaticZenRules(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(124, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().removeAutomaticZenRules(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestBindListener(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(71, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().requestBindListener(param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestBindProvider(ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(73, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().requestBindProvider(param2ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestHintsFromListener(INotificationListener param2INotificationListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(79, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().requestHintsFromListener(param2INotificationListener, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestInterruptionFilterFromListener(INotificationListener param2INotificationListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(81, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().requestInterruptionFilterFromListener(param2INotificationListener, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestUnbindListener(INotificationListener param2INotificationListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(72, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().requestUnbindListener(param2INotificationListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestUnbindProvider(IConditionProvider param2IConditionProvider) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2IConditionProvider != null) {
          iBinder = param2IConditionProvider.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(74, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().requestUnbindProvider(param2IConditionProvider);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAutomaticZenRuleState(String param2String, Condition param2Condition) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (param2Condition != null) {
          parcel1.writeInt(1);
          param2Condition.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(126, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setAutomaticZenRuleState(param2String, param2Condition);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBubblesAllowed(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setBubblesAllowed(param2String, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setHideSilentStatusIcons(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setHideSilentStatusIcons(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setInterruptionFilter(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(84, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setInterruptionFilter(param2String, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setInvalidMsgAppDemoted(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setInvalidMsgAppDemoted(param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationAssistantAccessGranted(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(101, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationAssistantAccessGranted(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationAssistantAccessGrantedForUser(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(103, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationAssistantAccessGrantedForUser(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationDelegate(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(130, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationDelegate(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationListenerAccessGranted(ComponentName param2ComponentName, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(100, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationListenerAccessGranted(param2ComponentName, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationListenerAccessGrantedForUser(ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        boolean bool = true;
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(102, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationListenerAccessGrantedForUser(param2ComponentName, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationPolicy(String param2String, NotificationManager.Policy param2Policy) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (param2Policy != null) {
          parcel1.writeInt(1);
          param2Policy.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(115, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationPolicy(param2String, param2Policy);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationPolicyAccessGranted(String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(117, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationPolicyAccessGranted(param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationPolicyAccessGrantedForUser(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(118, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationPolicyAccessGrantedForUser(param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationsEnabledForPackage(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationsEnabledForPackage(param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationsEnabledWithImportanceLockForPackage(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationsEnabledWithImportanceLockForPackage(param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setNotificationsShownFromListener(INotificationListener param2INotificationListener, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(75, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setNotificationsShownFromListener(param2INotificationListener, param2ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setOnNotificationPostedTrimFromListener(INotificationListener param2INotificationListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(83, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setOnNotificationPostedTrimFromListener(param2INotificationListener, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPrivateNotificationsAllowed(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(133, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setPrivateNotificationsAllowed(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setShowBadge(String param2String, int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setShowBadge(param2String, param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setZenMode(int param2Int, Uri param2Uri, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.INotificationManager");
        parcel.writeInt(param2Int);
        if (param2Uri != null) {
          parcel.writeInt(1);
          param2Uri.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param2String);
        if (!this.mRemote.transact(111, parcel, null, 1) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().setZenMode(param2Int, param2Uri, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean shouldHideSilentStatusIcons(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(23, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().shouldHideSilentStatusIcons(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void silenceNotificationSound() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (!this.mRemote.transact(59, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().silenceNotificationSound();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void snoozeNotificationUntilContextFromListener(INotificationListener param2INotificationListener, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(69, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().snoozeNotificationUntilContextFromListener(param2INotificationListener, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void snoozeNotificationUntilFromListener(INotificationListener param2INotificationListener, String param2String, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(70, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().snoozeNotificationUntilFromListener(param2INotificationListener, param2String, param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterListener(INotificationListener param2INotificationListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(66, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().unregisterListener(param2INotificationListener, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unsnoozeNotificationFromAssistant(INotificationListener param2INotificationListener, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(92, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().unsnoozeNotificationFromAssistant(param2INotificationListener, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unsnoozeNotificationFromSystemListener(INotificationListener param2INotificationListener, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(93, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().unsnoozeNotificationFromSystemListener(param2INotificationListener, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean updateAutomaticZenRule(String param2String, AutomaticZenRule param2AutomaticZenRule) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2AutomaticZenRule != null) {
          parcel1.writeInt(1);
          param2AutomaticZenRule.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(122, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          bool = INotificationManager.Stub.getDefaultImpl().updateAutomaticZenRule(param2String, param2AutomaticZenRule);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateNotificationChannelForPackage(String param2String, int param2Int, NotificationChannel param2NotificationChannel) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2NotificationChannel != null) {
          parcel1.writeInt(1);
          param2NotificationChannel.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().updateNotificationChannelForPackage(param2String, param2Int, param2NotificationChannel);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateNotificationChannelFromPrivilegedListener(INotificationListener param2INotificationListener, String param2String, UserHandle param2UserHandle, NotificationChannel param2NotificationChannel) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2NotificationChannel != null) {
          parcel1.writeInt(1);
          param2NotificationChannel.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(86, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().updateNotificationChannelFromPrivilegedListener(param2INotificationListener, param2String, param2UserHandle, param2NotificationChannel);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateNotificationChannelGroupForPackage(String param2String, int param2Int, NotificationChannelGroup param2NotificationChannelGroup) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (param2NotificationChannelGroup != null) {
          parcel1.writeInt(1);
          param2NotificationChannelGroup.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().updateNotificationChannelGroupForPackage(param2String, param2Int, param2NotificationChannelGroup);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener param2INotificationListener, String param2String, UserHandle param2UserHandle, NotificationChannelGroup param2NotificationChannelGroup) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.INotificationManager");
        if (param2INotificationListener != null) {
          iBinder = param2INotificationListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String);
        if (param2UserHandle != null) {
          parcel1.writeInt(1);
          param2UserHandle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2NotificationChannelGroup != null) {
          parcel1.writeInt(1);
          param2NotificationChannelGroup.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(85, parcel1, parcel2, 0) && INotificationManager.Stub.getDefaultImpl() != null) {
          INotificationManager.Stub.getDefaultImpl().updateNotificationChannelGroupFromPrivilegedListener(param2INotificationListener, param2String, param2UserHandle, param2NotificationChannelGroup);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/INotificationManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */