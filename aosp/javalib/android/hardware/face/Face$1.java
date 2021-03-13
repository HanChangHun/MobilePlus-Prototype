package android.hardware.face;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Face> {
  public Face createFromParcel(Parcel paramParcel) {
    return new Face(paramParcel, null);
  }
  
  public Face[] newArray(int paramInt) {
    return new Face[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/Face$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */