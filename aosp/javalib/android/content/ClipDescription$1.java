package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ClipDescription> {
  public ClipDescription createFromParcel(Parcel paramParcel) {
    return new ClipDescription(paramParcel);
  }
  
  public ClipDescription[] newArray(int paramInt) {
    return new ClipDescription[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ClipDescription$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */