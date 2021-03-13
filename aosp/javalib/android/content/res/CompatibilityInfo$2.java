package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CompatibilityInfo> {
  public CompatibilityInfo createFromParcel(Parcel paramParcel) {
    return new CompatibilityInfo(paramParcel, null);
  }
  
  public CompatibilityInfo[] newArray(int paramInt) {
    return new CompatibilityInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/CompatibilityInfo$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */