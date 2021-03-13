package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AccessibilityGestureEvent> {
  public AccessibilityGestureEvent createFromParcel(Parcel paramParcel) {
    return new AccessibilityGestureEvent(paramParcel, null);
  }
  
  public AccessibilityGestureEvent[] newArray(int paramInt) {
    return new AccessibilityGestureEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityGestureEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */