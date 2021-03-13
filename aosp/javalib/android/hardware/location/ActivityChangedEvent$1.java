package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityChangedEvent> {
  public ActivityChangedEvent createFromParcel(Parcel paramParcel) {
    ActivityRecognitionEvent[] arrayOfActivityRecognitionEvent = new ActivityRecognitionEvent[paramParcel.readInt()];
    paramParcel.readTypedArray((Object[])arrayOfActivityRecognitionEvent, ActivityRecognitionEvent.CREATOR);
    return new ActivityChangedEvent(arrayOfActivityRecognitionEvent);
  }
  
  public ActivityChangedEvent[] newArray(int paramInt) {
    return new ActivityChangedEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ActivityChangedEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */