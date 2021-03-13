package android.app.usage;

import android.annotation.SystemApi;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

public final class UsageEvents implements Parcelable {
  public static final Parcelable.Creator<UsageEvents> CREATOR = new Parcelable.Creator<UsageEvents>() {
      public UsageEvents createFromParcel(Parcel param1Parcel) {
        return new UsageEvents(param1Parcel);
      }
      
      public UsageEvents[] newArray(int param1Int) {
        return new UsageEvents[param1Int];
      }
    };
  
  public static final int HIDE_LOCUS_EVENTS = 8;
  
  public static final int HIDE_SHORTCUT_EVENTS = 2;
  
  public static final String INSTANT_APP_CLASS_NAME = "android.instant_class";
  
  public static final String INSTANT_APP_PACKAGE_NAME = "android.instant_app";
  
  public static final String OBFUSCATED_NOTIFICATION_CHANNEL_ID = "unknown_channel_id";
  
  public static final int OBFUSCATE_INSTANT_APPS = 1;
  
  public static final int OBFUSCATE_NOTIFICATION_EVENTS = 4;
  
  public static final int SHOW_ALL_EVENT_DATA = 0;
  
  private final int mEventCount;
  
  private List<Event> mEventsToWrite = null;
  
  private final boolean mIncludeTaskRoots;
  
  private int mIndex = 0;
  
  private Parcel mParcel = null;
  
  private String[] mStringPool;
  
  UsageEvents() {
    this.mEventCount = 0;
    this.mIncludeTaskRoots = true;
  }
  
  public UsageEvents(Parcel paramParcel) {
    byte[] arrayOfByte = paramParcel.readBlob();
    paramParcel = Parcel.obtain();
    paramParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
    paramParcel.setDataPosition(0);
    this.mEventCount = paramParcel.readInt();
    this.mIndex = paramParcel.readInt();
    if (this.mEventCount > 0) {
      this.mStringPool = paramParcel.createStringArray();
      int i = paramParcel.readInt();
      int j = paramParcel.readInt();
      Parcel parcel = Parcel.obtain();
      this.mParcel = parcel;
      parcel.setDataPosition(0);
      this.mParcel.appendFrom(paramParcel, paramParcel.dataPosition(), i);
      paramParcel = this.mParcel;
      paramParcel.setDataSize(paramParcel.dataPosition());
      this.mParcel.setDataPosition(j);
    } 
    this.mIncludeTaskRoots = true;
  }
  
  public UsageEvents(List<Event> paramList, String[] paramArrayOfString) {
    this(paramList, paramArrayOfString, false);
  }
  
  public UsageEvents(List<Event> paramList, String[] paramArrayOfString, boolean paramBoolean) {
    this.mStringPool = paramArrayOfString;
    this.mEventCount = paramList.size();
    this.mEventsToWrite = paramList;
    this.mIncludeTaskRoots = paramBoolean;
  }
  
  private int findStringIndex(String paramString) {
    int i = Arrays.binarySearch((Object[])this.mStringPool, paramString);
    if (i >= 0)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("String '");
    stringBuilder.append(paramString);
    stringBuilder.append("' is not in the string pool");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void readEventFromParcel(Parcel paramParcel, Event paramEvent) {
    int i = paramParcel.readInt();
    if (i >= 0) {
      paramEvent.mPackage = this.mStringPool[i];
    } else {
      paramEvent.mPackage = null;
    } 
    i = paramParcel.readInt();
    if (i >= 0) {
      paramEvent.mClass = this.mStringPool[i];
    } else {
      paramEvent.mClass = null;
    } 
    paramEvent.mInstanceId = paramParcel.readInt();
    i = paramParcel.readInt();
    if (i >= 0) {
      paramEvent.mTaskRootPackage = this.mStringPool[i];
    } else {
      paramEvent.mTaskRootPackage = null;
    } 
    i = paramParcel.readInt();
    if (i >= 0) {
      paramEvent.mTaskRootClass = this.mStringPool[i];
    } else {
      paramEvent.mTaskRootClass = null;
    } 
    paramEvent.mEventType = paramParcel.readInt();
    paramEvent.mTimeStamp = paramParcel.readLong();
    paramEvent.mConfiguration = null;
    paramEvent.mShortcutId = null;
    paramEvent.mAction = null;
    paramEvent.mContentType = null;
    paramEvent.mContentAnnotations = null;
    paramEvent.mNotificationChannelId = null;
    paramEvent.mLocusId = null;
    i = paramEvent.mEventType;
    if (i != 5) {
      if (i != 30) {
        if (i != 8) {
          if (i != 9) {
            if (i != 11) {
              if (i == 12)
                paramEvent.mNotificationChannelId = paramParcel.readString(); 
            } else {
              paramEvent.mBucketAndReason = paramParcel.readInt();
            } 
          } else {
            paramEvent.mAction = paramParcel.readString();
            paramEvent.mContentType = paramParcel.readString();
            paramEvent.mContentAnnotations = paramParcel.createStringArray();
          } 
        } else {
          paramEvent.mShortcutId = paramParcel.readString();
        } 
      } else {
        paramEvent.mLocusId = paramParcel.readString();
      } 
    } else {
      paramEvent.mConfiguration = (Configuration)Configuration.CREATOR.createFromParcel(paramParcel);
    } 
    paramEvent.mFlags = paramParcel.readInt();
  }
  
  private void writeEventToParcel(Event paramEvent, Parcel paramParcel, int paramInt) {
    byte b1;
    byte b2;
    byte b3;
    if (paramEvent.mPackage != null) {
      i = findStringIndex(paramEvent.mPackage);
    } else {
      i = -1;
    } 
    if (paramEvent.mClass != null) {
      b1 = findStringIndex(paramEvent.mClass);
    } else {
      b1 = -1;
    } 
    if (this.mIncludeTaskRoots && paramEvent.mTaskRootPackage != null) {
      b2 = findStringIndex(paramEvent.mTaskRootPackage);
    } else {
      b2 = -1;
    } 
    if (this.mIncludeTaskRoots && paramEvent.mTaskRootClass != null) {
      b3 = findStringIndex(paramEvent.mTaskRootClass);
    } else {
      b3 = -1;
    } 
    paramParcel.writeInt(i);
    paramParcel.writeInt(b1);
    paramParcel.writeInt(paramEvent.mInstanceId);
    paramParcel.writeInt(b2);
    paramParcel.writeInt(b3);
    paramParcel.writeInt(paramEvent.mEventType);
    paramParcel.writeLong(paramEvent.mTimeStamp);
    int i = paramEvent.mEventType;
    if (i != 5) {
      if (i != 30) {
        if (i != 8) {
          if (i != 9) {
            if (i != 11) {
              if (i == 12)
                paramParcel.writeString(paramEvent.mNotificationChannelId); 
            } else {
              paramParcel.writeInt(paramEvent.mBucketAndReason);
            } 
          } else {
            paramParcel.writeString(paramEvent.mAction);
            paramParcel.writeString(paramEvent.mContentType);
            paramParcel.writeStringArray(paramEvent.mContentAnnotations);
          } 
        } else {
          paramParcel.writeString(paramEvent.mShortcutId);
        } 
      } else {
        paramParcel.writeString(paramEvent.mLocusId);
      } 
    } else {
      paramEvent.mConfiguration.writeToParcel(paramParcel, paramInt);
    } 
    paramParcel.writeInt(paramEvent.mFlags);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean getNextEvent(Event paramEvent) {
    int i = this.mIndex;
    if (i >= this.mEventCount)
      return false; 
    Parcel parcel = this.mParcel;
    if (parcel != null) {
      readEventFromParcel(parcel, paramEvent);
    } else {
      paramEvent.copyFrom(this.mEventsToWrite.get(i));
    } 
    i = this.mIndex + 1;
    this.mIndex = i;
    if (i >= this.mEventCount) {
      Parcel parcel1 = this.mParcel;
      if (parcel1 != null) {
        parcel1.recycle();
        this.mParcel = null;
      } 
    } 
    return true;
  }
  
  public boolean hasNextEvent() {
    boolean bool;
    if (this.mIndex < this.mEventCount) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void resetToStart() {
    this.mIndex = 0;
    Parcel parcel = this.mParcel;
    if (parcel != null)
      parcel.setDataPosition(0); 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    Parcel parcel = Parcel.obtain();
    parcel.writeInt(this.mEventCount);
    parcel.writeInt(this.mIndex);
    if (this.mEventCount > 0) {
      parcel.writeStringArray(this.mStringPool);
      if (this.mEventsToWrite != null) {
        Parcel parcel1 = Parcel.obtain();
        try {
          parcel1.setDataPosition(0);
          for (byte b = 0; b < this.mEventCount; b++)
            writeEventToParcel(this.mEventsToWrite.get(b), parcel1, paramInt); 
          paramInt = parcel1.dataPosition();
          parcel.writeInt(paramInt);
          parcel.writeInt(0);
          parcel.appendFrom(parcel1, 0, paramInt);
        } finally {
          parcel1.recycle();
        } 
      } else {
        Parcel parcel1 = this.mParcel;
        if (parcel1 != null) {
          parcel.writeInt(parcel1.dataSize());
          parcel.writeInt(this.mParcel.dataPosition());
          parcel1 = this.mParcel;
          parcel.appendFrom(parcel1, 0, parcel1.dataSize());
        } else {
          throw new IllegalStateException("Either mParcel or mEventsToWrite must not be null");
        } 
      } 
    } 
    paramParcel.writeBlob(parcel.marshall());
  }
  
  public static final class Event {
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
    
    public Event(int param1Int, long param1Long) {
      this.mEventType = param1Int;
      this.mTimeStamp = param1Long;
    }
    
    public Event(Event param1Event) {
      copyFrom(param1Event);
    }
    
    private void copyFrom(Event param1Event) {
      this.mPackage = param1Event.mPackage;
      this.mClass = param1Event.mClass;
      this.mInstanceId = param1Event.mInstanceId;
      this.mTaskRootPackage = param1Event.mTaskRootPackage;
      this.mTaskRootClass = param1Event.mTaskRootClass;
      this.mTimeStamp = param1Event.mTimeStamp;
      this.mEventType = param1Event.mEventType;
      this.mConfiguration = param1Event.mConfiguration;
      this.mShortcutId = param1Event.mShortcutId;
      this.mAction = param1Event.mAction;
      this.mContentType = param1Event.mContentType;
      this.mContentAnnotations = param1Event.mContentAnnotations;
      this.mFlags = param1Event.mFlags;
      this.mBucketAndReason = param1Event.mBucketAndReason;
      this.mNotificationChannelId = param1Event.mNotificationChannelId;
      this.mLocusId = param1Event.mLocusId;
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
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EventFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/UsageEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */