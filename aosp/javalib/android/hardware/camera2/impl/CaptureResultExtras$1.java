package android.hardware.camera2.impl;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CaptureResultExtras> {
  public CaptureResultExtras createFromParcel(Parcel paramParcel) {
    return new CaptureResultExtras(paramParcel, null);
  }
  
  public CaptureResultExtras[] newArray(int paramInt) {
    return new CaptureResultExtras[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CaptureResultExtras$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */