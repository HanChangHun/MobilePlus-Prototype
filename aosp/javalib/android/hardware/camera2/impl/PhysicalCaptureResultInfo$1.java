package android.hardware.camera2.impl;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PhysicalCaptureResultInfo> {
  public PhysicalCaptureResultInfo createFromParcel(Parcel paramParcel) {
    return new PhysicalCaptureResultInfo(paramParcel, null);
  }
  
  public PhysicalCaptureResultInfo[] newArray(int paramInt) {
    return new PhysicalCaptureResultInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/PhysicalCaptureResultInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */