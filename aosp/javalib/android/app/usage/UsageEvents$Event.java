package android.app.usage;

import android.annotation.SystemApi;
import android.content.res.Configuration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Event {
  public static final int ACTIVITY_DESTROYED = 24;
  
  public static final int ACTIVITY_PAUSED = 2;
  
  public static final int ACTIVITY_RESUMED = 1;
  
  public static final int ACTIVITY_STOPPED = 23;
  
  public static final int CHOOSER_ACTION = 9;
  
  public static final int CONFIGURATION_CHANGE = 5;
  
  public static final int CONTINUE_PREVIOUS_DAY = 4;
  
  public static final int CONTINUING_FOREGROUND_SERVICE = 21;
  
  public static final String DEVICE_EVENT_PACKAGE_NAME = "android";
  
  public static final int DEVICE_SHUTDOWN = 26;
  
  public static final int DEVICE_STARTUP = 27;
  
  public static final int END_OF_DAY = 3;
  
  public static final int FLAG_IS_PACKAGE_INSTANT_APP = 1;
  
  public static final int FLUSH_TO_DISK = 25;
  
  public static final int FOREGROUND_SERVICE_START = 19;
  
  public static final int FOREGROUND_SERVICE_STOP = 20;
  
  public static final int KEYGUARD_HIDDEN = 18;
  
  public static final int KEYGUARD_SHOWN = 17;
  
  public static final int LOCUS_ID_SET = 30;
  
  public static final int MAX_EVENT_TYPE = 30;
  
  @Deprecated
  public static final int MOVE_TO_BACKGROUND = 2;
  
  @Deprecated
  public static final int MOVE_TO_FOREGROUND = 1;
  
  public static final int NONE = 0;
  
  @SystemApi
  public static final int NOTIFICATION_INTERRUPTION = 12;
  
  @SystemApi
  public static final int NOTIFICATION_SEEN = 10;
  
  public static final int ROLLOVER_FOREGROUND_SERVICE = 22;
  
  public static final int SCREEN_INTERACTIVE = 15;
  
  public static final int SCREEN_NON_INTERACTIVE = 16;
  
  public static final int SHORTCUT_INVOCATION = 8;
  
  @SystemApi
  public static final int SLICE_PINNED = 14;
  
  @SystemApi
  public static final int SLICE_PINNED_PRIV = 13;
  
  public static final int STANDBY_BUCKET_CHANGED = 11;
  
  @SystemApi
  public static final int SYSTEM_INTERACTION = 6;
  
  private static final int UNASSIGNED_TOKEN = -1;
  
  public static final int USER_INTERACTION = 7;
  
  public static final int USER_STOPPED = 29;
  
  public static final int USER_UNLOCKED = 28;
  
  public static final int VALID_FLAG_BITS = 1;
  
  public String mAction;
  
  public int mBucketAndReason;
  
  public String mClass;
  
  public int mClassToken = -1;
  
  public Configuration mConfiguration;
  
  public String[] mContentAnnotations;
  
  public String mContentType;
  
  public int mEventType;
  
  public int mFlags;
  
  public int mInstanceId;
  
  public String mLocusId;
  
  public int mLocusIdToken = -1;
  
  public String mNotificationChannelId;
  
  public int mNotificationChannelIdToken = -1;
  
  public String mPackage;
  
  public int mPackageToken = -1;
  
  public String mShortcutId;
  
  public int mShortcutIdToken = -1;
  
  public String mTaskRootClass;
  
  public int mTaskRootClassToken = -1;
  
  public String mTaskRootPackage;
  
  public int mTaskRootPackageToken = -1;
  
  public long mTimeStamp;
  
  public Event() {}
  
  public Event(int paramInt, long paramLong) {
    this.mEventType = paramInt;
    this.mTimeStamp = paramLong;
  }
  
  public Event(Event paramEvent) {
    copyFrom(paramEvent);
  }
  
  private void copyFrom(Event paramEvent) {
    this.mPackage = paramEvent.mPackage;
    this.mClass = paramEvent.mClass;
    this.mInstanceId = paramEvent.mInstanceId;
    this.mTaskRootPackage = paramEvent.mTaskRootPackage;
    this.mTaskRootClass = paramEvent.mTaskRootClass;
    this.mTimeStamp = paramEvent.mTimeStamp;
    this.mEventType = paramEvent.mEventType;
    this.mConfiguration = paramEvent.mConfiguration;
    this.mShortcutId = paramEvent.mShortcutId;
    this.mAction = paramEvent.mAction;
    this.mContentType = paramEvent.mContentType;
    this.mContentAnnotations = paramEvent.mContentAnnotations;
    this.mFlags = paramEvent.mFlags;
    this.mBucketAndReason = paramEvent.mBucketAndReason;
    this.mNotificationChannelId = paramEvent.mNotificationChannelId;
    this.mLocusId = paramEvent.mLocusId;
  }
  
  public int getAppStandbyBucket() {
    return (this.mBucketAndReason & 0xFFFF0000) >>> 16;
  }
  
  public String getClassName() {
    return this.mClass;
  }
  
  public Configuration getConfiguration() {
    return this.mConfiguration;
  }
  
  public int getEventType() {
    return this.mEventType;
  }
  
  @SystemApi
  public int getInstanceId() {
    return this.mInstanceId;
  }
  
  public String getLocusId() {
    return this.mLocusId;
  }
  
  @SystemApi
  public String getNotificationChannelId() {
    return this.mNotificationChannelId;
  }
  
  public Event getObfuscatedIfInstantApp() {
    if (!isInstantApp())
      return this; 
    Event event = new Event(this);
    event.mPackage = "android.instant_app";
    event.mClass = "android.instant_class";
    return event;
  }
  
  public Event getObfuscatedNotificationEvent() {
    Event event = new Event(this);
    event.mNotificationChannelId = "unknown_channel_id";
    return event;
  }
  
  public String getPackageName() {
    return this.mPackage;
  }
  
  public String getShortcutId() {
    return this.mShortcutId;
  }
  
  public int getStandbyBucket() {
    return (this.mBucketAndReason & 0xFFFF0000) >>> 16;
  }
  
  public int getStandbyReason() {
    return this.mBucketAndReason & 0xFFFF;
  }
  
  @SystemApi
  public String getTaskRootClassName() {
    return this.mTaskRootClass;
  }
  
  @SystemApi
  public String getTaskRootPackageName() {
    return this.mTaskRootPackage;
  }
  
  public long getTimeStamp() {
    return this.mTimeStamp;
  }
  
  @SystemApi
  public boolean isInstantApp() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) != 1)
      bool = false; 
    return bool;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EventFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/UsageEvents$Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */