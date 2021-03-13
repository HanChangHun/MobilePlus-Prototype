package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DistroFormatVersion> {
  public DistroFormatVersion createFromParcel(Parcel paramParcel) {
    return new DistroFormatVersion(paramParcel.readInt(), paramParcel.readInt());
  }
  
  public DistroFormatVersion[] newArray(int paramInt) {
    return new DistroFormatVersion[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/DistroFormatVersion$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */