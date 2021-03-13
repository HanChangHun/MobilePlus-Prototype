package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LongParcelable> {
  public LongParcelable createFromParcel(Parcel paramParcel) {
    return new LongParcelable(paramParcel, null);
  }
  
  public LongParcelable[] newArray(int paramInt) {
    return new LongParcelable[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/LongParcelable$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */