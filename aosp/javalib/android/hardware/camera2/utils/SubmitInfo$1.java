package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SubmitInfo> {
  public SubmitInfo createFromParcel(Parcel paramParcel) {
    return new SubmitInfo(paramParcel, null);
  }
  
  public SubmitInfo[] newArray(int paramInt) {
    return new SubmitInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/SubmitInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */