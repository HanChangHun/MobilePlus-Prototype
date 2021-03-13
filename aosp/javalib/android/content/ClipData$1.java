package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ClipData> {
  public ClipData createFromParcel(Parcel paramParcel) {
    return new ClipData(paramParcel);
  }
  
  public ClipData[] newArray(int paramInt) {
    return new ClipData[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ClipData$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */