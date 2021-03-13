package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<FeatureInfo> {
  public FeatureInfo createFromParcel(Parcel paramParcel) {
    return new FeatureInfo(paramParcel, null);
  }
  
  public FeatureInfo[] newArray(int paramInt) {
    return new FeatureInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/FeatureInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */