package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ActivityRecognitionEvent> {
  public ActivityRecognitionEvent createFromParcel(Parcel paramParcel) {
    return new ActivityRecognitionEvent(paramParcel.readString(), paramParcel.readInt(), paramParcel.readLong());
  }
  
  public ActivityRecognitionEvent[] newArray(int paramInt) {
    return new ActivityRecognitionEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ActivityRecognitionEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */