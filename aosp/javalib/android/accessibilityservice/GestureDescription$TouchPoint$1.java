package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<GestureDescription.TouchPoint> {
  public GestureDescription.TouchPoint createFromParcel(Parcel paramParcel) {
    return new GestureDescription.TouchPoint(paramParcel);
  }
  
  public GestureDescription.TouchPoint[] newArray(int paramInt) {
    return new GestureDescription.TouchPoint[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$TouchPoint$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */