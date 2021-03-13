package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DistroRulesVersion> {
  public DistroRulesVersion createFromParcel(Parcel paramParcel) {
    return new DistroRulesVersion(paramParcel.readString(), paramParcel.readInt());
  }
  
  public DistroRulesVersion[] newArray(int paramInt) {
    return new DistroRulesVersion[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/DistroRulesVersion$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */