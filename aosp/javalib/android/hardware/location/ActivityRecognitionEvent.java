package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityRecognitionEvent implements Parcelable {
  public static final Parcelable.Creator<ActivityRecognitionEvent> CREATOR = new Parcelable.Creator<ActivityRecognitionEvent>() {
      public ActivityRecognitionEvent createFromParcel(Parcel param1Parcel) {
        return new ActivityRecognitionEvent(param1Parcel.readString(), param1Parcel.readInt(), param1Parcel.readLong());
      }
      
      public ActivityRecognitionEvent[] newArray(int param1Int) {
        return new ActivityRecognitionEvent[param1Int];
      }
    };
  
  private final String mActivity;
  
  private final int mEventType;
  
  private final long mTimestampNs;
  
  public ActivityRecognitionEvent(String paramString, int paramInt, long paramLong) {
    this.mActivity = paramString;
    this.mEventType = paramInt;
    this.mTimestampNs = paramLong;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getActivity() {
    return this.mActivity;
  }
  
  public int getEventType() {
    return this.mEventType;
  }
  
  public long getTimestampNs() {
    return this.mTimestampNs;
  }
  
  public String toString() {
    return String.format("Activity='%s', EventType=%s, TimestampNs=%s", new Object[] { this.mActivity, Integer.valueOf(this.mEventType), Long.valueOf(this.mTimestampNs) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mActivity);
    paramParcel.writeInt(this.mEventType);
    paramParcel.writeLong(this.mTimestampNs);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ActivityRecognitionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */