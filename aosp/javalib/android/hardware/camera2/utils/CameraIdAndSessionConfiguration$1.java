package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CameraIdAndSessionConfiguration> {
  public CameraIdAndSessionConfiguration createFromParcel(Parcel paramParcel) {
    return new CameraIdAndSessionConfiguration(paramParcel, null);
  }
  
  public CameraIdAndSessionConfiguration[] newArray(int paramInt) {
    return new CameraIdAndSessionConfiguration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/CameraIdAndSessionConfiguration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */