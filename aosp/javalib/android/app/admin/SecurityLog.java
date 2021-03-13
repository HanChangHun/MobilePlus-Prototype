package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.EventLog;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SecurityLog {
  public static final int LEVEL_ERROR = 3;
  
  public static final int LEVEL_INFO = 1;
  
  public static final int LEVEL_WARNING = 2;
  
  private static final String PROPERTY_LOGGING_ENABLED = "persist.logd.security";
  
  public static final int TAG_ADB_SHELL_CMD = 210002;
  
  public static final int TAG_ADB_SHELL_INTERACTIVE = 210001;
  
  public static final int TAG_APP_PROCESS_START = 210005;
  
  public static final int TAG_CAMERA_POLICY_SET = 210034;
  
  public static final int TAG_CERT_AUTHORITY_INSTALLED = 210029;
  
  public static final int TAG_CERT_AUTHORITY_REMOVED = 210030;
  
  public static final int TAG_CERT_VALIDATION_FAILURE = 210033;
  
  public static final int TAG_CRYPTO_SELF_TEST_COMPLETED = 210031;
  
  public static final int TAG_KEYGUARD_DISABLED_FEATURES_SET = 210021;
  
  public static final int TAG_KEYGUARD_DISMISSED = 210006;
  
  public static final int TAG_KEYGUARD_DISMISS_AUTH_ATTEMPT = 210007;
  
  public static final int TAG_KEYGUARD_SECURED = 210008;
  
  public static final int TAG_KEY_DESTRUCTION = 210026;
  
  public static final int TAG_KEY_GENERATED = 210024;
  
  public static final int TAG_KEY_IMPORT = 210025;
  
  public static final int TAG_KEY_INTEGRITY_VIOLATION = 210032;
  
  public static final int TAG_LOGGING_STARTED = 210011;
  
  public static final int TAG_LOGGING_STOPPED = 210012;
  
  public static final int TAG_LOG_BUFFER_SIZE_CRITICAL = 210015;
  
  public static final int TAG_MAX_PASSWORD_ATTEMPTS_SET = 210020;
  
  public static final int TAG_MAX_SCREEN_LOCK_TIMEOUT_SET = 210019;
  
  public static final int TAG_MEDIA_MOUNT = 210013;
  
  public static final int TAG_MEDIA_UNMOUNT = 210014;
  
  public static final int TAG_OS_SHUTDOWN = 210010;
  
  public static final int TAG_OS_STARTUP = 210009;
  
  public static final int TAG_PASSWORD_COMPLEXITY_SET = 210017;
  
  public static final int TAG_PASSWORD_EXPIRATION_SET = 210016;
  
  public static final int TAG_PASSWORD_HISTORY_LENGTH_SET = 210018;
  
  public static final int TAG_REMOTE_LOCK = 210022;
  
  public static final int TAG_SYNC_RECV_FILE = 210003;
  
  public static final int TAG_SYNC_SEND_FILE = 210004;
  
  public static final int TAG_USER_RESTRICTION_ADDED = 210027;
  
  public static final int TAG_USER_RESTRICTION_REMOVED = 210028;
  
  public static final int TAG_WIPE_FAILURE = 210023;
  
  public static boolean getLoggingEnabledProperty() {
    return SystemProperties.getBoolean("persist.logd.security", false);
  }
  
  public static native boolean isLoggingEnabled();
  
  public static native void readEvents(Collection<SecurityEvent> paramCollection) throws IOException;
  
  public static native void readEventsOnWrapping(long paramLong, Collection<SecurityEvent> paramCollection) throws IOException;
  
  public static native void readEventsSince(long paramLong, Collection<SecurityEvent> paramCollection) throws IOException;
  
  public static native void readPreviousEvents(Collection<SecurityEvent> paramCollection) throws IOException;
  
  public static void redactEvents(ArrayList<SecurityEvent> paramArrayList, int paramInt) {
    if (paramInt == -1)
      return; 
    int i = 0;
    byte b = 0;
    while (b < paramArrayList.size()) {
      SecurityEvent securityEvent = ((SecurityEvent)paramArrayList.get(b)).redact(paramInt);
      int j = i;
      if (securityEvent != null) {
        paramArrayList.set(i, securityEvent);
        j = i + 1;
      } 
      b++;
      i = j;
    } 
    for (paramInt = paramArrayList.size() - 1; paramInt >= i; paramInt--)
      paramArrayList.remove(paramInt); 
  }
  
  public static void setLoggingEnabledProperty(boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = "true";
    } else {
      str = "false";
    } 
    SystemProperties.set("persist.logd.security", str);
  }
  
  public static native int writeEvent(int paramInt, String paramString);
  
  public static native int writeEvent(int paramInt, Object... paramVarArgs);
  
  public static final class SecurityEvent implements Parcelable {
    public static final Parcelable.Creator<SecurityEvent> CREATOR = new Parcelable.Creator<SecurityEvent>() {
        public SecurityLog.SecurityEvent createFromParcel(Parcel param2Parcel) {
          return new SecurityLog.SecurityEvent(param2Parcel);
        }
        
        public SecurityLog.SecurityEvent[] newArray(int param2Int) {
          return new SecurityLog.SecurityEvent[param2Int];
        }
      };
    
    private EventLog.Event mEvent;
    
    private long mId;
    
    public SecurityEvent(long param1Long, byte[] param1ArrayOfbyte) {
      this.mId = param1Long;
      this.mEvent = EventLog.Event.fromBytes(param1ArrayOfbyte);
    }
    
    SecurityEvent(Parcel param1Parcel) {
      this(param1Parcel.readLong(), param1Parcel.createByteArray());
    }
    
    SecurityEvent(byte[] param1ArrayOfbyte) {
      this(0L, param1ArrayOfbyte);
    }
    
    private boolean getSuccess() {
      Object object = getData();
      boolean bool1 = false;
      if (object == null || !(object instanceof Object[]))
        return false; 
      object = object;
      boolean bool2 = bool1;
      if (object.length >= 1) {
        bool2 = bool1;
        if (object[0] instanceof Integer) {
          bool2 = bool1;
          if (((Integer)object[0]).intValue() != 0)
            bool2 = true; 
        } 
      } 
      return bool2;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (!this.mEvent.equals(((SecurityEvent)param1Object).mEvent) || this.mId != ((SecurityEvent)param1Object).mId)
        bool = false; 
      return bool;
    }
    
    public boolean eventEquals(SecurityEvent param1SecurityEvent) {
      boolean bool;
      if (param1SecurityEvent != null && this.mEvent.equals(param1SecurityEvent.mEvent)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Object getData() {
      return this.mEvent.getData();
    }
    
    public long getId() {
      return this.mId;
    }
    
    public int getIntegerData(int param1Int) {
      return ((Integer)((Object[])this.mEvent.getData())[param1Int]).intValue();
    }
    
    public int getLogLevel() {
      int i = getTag();
      byte b = 2;
      boolean bool = true;
      if (i != 210007)
        if (i != 210015) {
          switch (i) {
            default:
              switch (i) {
                default:
                  return 1;
                case 210033:
                  return 2;
                case 210030:
                case 210031:
                  if (getSuccess()) {
                    b = bool;
                  } else {
                    b = 3;
                  } 
                  return b;
                case 210032:
                  return 3;
                case 210029:
                  break;
              } 
              break;
            case 210023:
            
            case 210024:
            case 210025:
            case 210026:
              break;
          } 
        } else {
        
        }  
      if (getSuccess())
        b = 1; 
      return b;
    }
    
    public String getStringData(int param1Int) {
      return (String)((Object[])this.mEvent.getData())[param1Int];
    }
    
    public int getTag() {
      return this.mEvent.getTag();
    }
    
    public long getTimeNanos() {
      return this.mEvent.getTimeNanos();
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.mEvent, Long.valueOf(this.mId) });
    }
    
    public SecurityEvent redact(int param1Int) {
      int i;
      switch (getTag()) {
        default:
          i = -10000;
          break;
        case 210032:
          try {
            i = UserHandle.getUserId(getIntegerData(1));
          } catch (Exception exception) {
            return null;
          } 
          break;
        case 210029:
        case 210030:
          try {
            i = getIntegerData(2);
          } catch (Exception exception) {
            return null;
          } 
          break;
        case 210024:
        case 210025:
        case 210026:
          try {
            i = UserHandle.getUserId(getIntegerData(2));
          } catch (Exception exception) {
            return null;
          } 
          break;
        case 210013:
        case 210014:
          try {
            String str = getStringData(0);
            return new SecurityEvent(getId(), this.mEvent.withNewData(new Object[] { str, "" }).getBytes());
          } catch (Exception exception) {
            return null;
          } 
        case 210005:
          try {
            i = UserHandle.getUserId(getIntegerData(2));
          } catch (Exception exception) {
            return null;
          } 
          break;
        case 210002:
          return new SecurityEvent(getId(), this.mEvent.withNewData("").getBytes());
      } 
      return (i == -10000 || param1Int == i) ? this : null;
    }
    
    public void setId(long param1Long) {
      this.mId = param1Long;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.mId);
      param1Parcel.writeByteArray(this.mEvent.getBytes());
    }
  }
  
  class null implements Parcelable.Creator<SecurityEvent> {
    public SecurityLog.SecurityEvent createFromParcel(Parcel param1Parcel) {
      return new SecurityLog.SecurityEvent(param1Parcel);
    }
    
    public SecurityLog.SecurityEvent[] newArray(int param1Int) {
      return new SecurityLog.SecurityEvent[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SecurityLogLevel {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SecurityLogTag {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SecurityLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */