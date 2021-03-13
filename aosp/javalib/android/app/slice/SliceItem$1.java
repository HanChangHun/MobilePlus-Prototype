package android.app.slice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SliceItem> {
  public SliceItem createFromParcel(Parcel paramParcel) {
    return new SliceItem(paramParcel);
  }
  
  public SliceItem[] newArray(int paramInt) {
    return new SliceItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */