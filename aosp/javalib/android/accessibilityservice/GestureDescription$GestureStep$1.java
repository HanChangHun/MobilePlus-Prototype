package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<GestureDescription.GestureStep> {
  public GestureDescription.GestureStep createFromParcel(Parcel paramParcel) {
    return new GestureDescription.GestureStep(paramParcel);
  }
  
  public GestureDescription.GestureStep[] newArray(int paramInt) {
    return new GestureDescription.GestureStep[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$GestureStep$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */