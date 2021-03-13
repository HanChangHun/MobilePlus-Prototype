package android.app.slice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SliceSpec> {
  public SliceSpec createFromParcel(Parcel paramParcel) {
    return new SliceSpec(paramParcel);
  }
  
  public SliceSpec[] newArray(int paramInt) {
    return new SliceSpec[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceSpec$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */