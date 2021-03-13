package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ObbInfo> {
  public ObbInfo createFromParcel(Parcel paramParcel) {
    return new ObbInfo(paramParcel, null);
  }
  
  public ObbInfo[] newArray(int paramInt) {
    return new ObbInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ObbInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */