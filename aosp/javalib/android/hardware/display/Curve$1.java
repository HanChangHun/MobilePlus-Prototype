package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Curve> {
  public Curve createFromParcel(Parcel paramParcel) {
    return new Curve(paramParcel.createFloatArray(), paramParcel.createFloatArray());
  }
  
  public Curve[] newArray(int paramInt) {
    return new Curve[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/Curve$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */