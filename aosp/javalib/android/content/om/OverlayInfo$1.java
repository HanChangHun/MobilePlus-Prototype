package android.content.om;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<OverlayInfo> {
  public OverlayInfo createFromParcel(Parcel paramParcel) {
    return new OverlayInfo(paramParcel);
  }
  
  public OverlayInfo[] newArray(int paramInt) {
    return new OverlayInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/OverlayInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */