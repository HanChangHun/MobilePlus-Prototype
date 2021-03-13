package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class ActivityChangedEvent implements Parcelable {
  public static final Parcelable.Creator<ActivityChangedEvent> CREATOR = new Parcelable.Creator<ActivityChangedEvent>() {
      public ActivityChangedEvent createFromParcel(Parcel param1Parcel) {
        ActivityRecognitionEvent[] arrayOfActivityRecognitionEvent = new ActivityRecognitionEvent[param1Parcel.readInt()];
        param1Parcel.readTypedArray((Object[])arrayOfActivityRecognitionEvent, ActivityRecognitionEvent.CREATOR);
        return new ActivityChangedEvent(arrayOfActivityRecognitionEvent);
      }
      
      public ActivityChangedEvent[] newArray(int param1Int) {
        return new ActivityChangedEvent[param1Int];
      }
    };
  
  private final List<ActivityRecognitionEvent> mActivityRecognitionEvents;
  
  public ActivityChangedEvent(ActivityRecognitionEvent[] paramArrayOfActivityRecognitionEvent) {
    if (paramArrayOfActivityRecognitionEvent != null) {
      this.mActivityRecognitionEvents = Arrays.asList(paramArrayOfActivityRecognitionEvent);
      return;
    } 
    throw new InvalidParameterException("Parameter 'activityRecognitionEvents' must not be null.");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Iterable<ActivityRecognitionEvent> getActivityRecognitionEvents() {
    return this.mActivityRecognitionEvents;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("[ ActivityChangedEvent:");
    for (ActivityRecognitionEvent activityRecognitionEvent : this.mActivityRecognitionEvents) {
      stringBuilder.append("\n    ");
      stringBuilder.append(activityRecognitionEvent.toString());
    } 
    stringBuilder.append("\n]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    ActivityRecognitionEvent[] arrayOfActivityRecognitionEvent = this.mActivityRecognitionEvents.<ActivityRecognitionEvent>toArray(new ActivityRecognitionEvent[0]);
    paramParcel.writeInt(arrayOfActivityRecognitionEvent.length);
    paramParcel.writeTypedArray((Parcelable[])arrayOfActivityRecognitionEvent, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ActivityChangedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */