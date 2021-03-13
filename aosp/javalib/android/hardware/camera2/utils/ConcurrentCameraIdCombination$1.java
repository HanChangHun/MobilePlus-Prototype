package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ConcurrentCameraIdCombination> {
  public ConcurrentCameraIdCombination createFromParcel(Parcel paramParcel) {
    return new ConcurrentCameraIdCombination(paramParcel, null);
  }
  
  public ConcurrentCameraIdCombination[] newArray(int paramInt) {
    return new ConcurrentCameraIdCombination[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/ConcurrentCameraIdCombination$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */