package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.util.EventLog;
import java.util.Objects;

public final class SecurityEvent implements Parcelable {
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
  
  public SecurityEvent(long paramLong, byte[] paramArrayOfbyte) {
    this.mId = paramLong;
    this.mEvent = EventLog.Event.fromBytes(paramArrayOfbyte);
  }
  
  SecurityEvent(Parcel paramParcel) {
    this(paramParcel.readLong(), paramParcel.createByteArray());
  }
  
  SecurityEvent(byte[] paramArrayOfbyte) {
    this(0L, paramArrayOfbyte);
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
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!this.mEvent.equals(((SecurityEvent)paramObject).mEvent) || this.mId != ((SecurityEvent)paramObject).mId)
      bool = false; 
    return bool;
  }
  
  public boolean eventEquals(SecurityEvent paramSecurityEvent) {
    boolean bool;
    if (paramSecurityEvent != null && this.mEvent.equals(paramSecurityEvent.mEvent)) {
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
  
  public int getIntegerData(int paramInt) {
    return ((Integer)((Object[])this.mEvent.getData())[paramInt]).intValue();
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
  
  public String getStringData(int paramInt) {
    return (String)((Object[])this.mEvent.getData())[paramInt];
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
  
  public SecurityEvent redact(int paramInt) {
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
    return (i == -10000 || paramInt == i) ? this : null;
  }
  
  public void setId(long paramLong) {
    this.mId = paramLong;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mId);
    paramParcel.writeByteArray(this.mEvent.getBytes());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SecurityLog$SecurityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */