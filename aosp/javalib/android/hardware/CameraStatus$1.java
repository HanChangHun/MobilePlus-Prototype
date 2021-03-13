package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CameraStatus> {
  public CameraStatus createFromParcel(Parcel paramParcel) {
    CameraStatus cameraStatus = new CameraStatus();
    cameraStatus.readFromParcel(paramParcel);
    return cameraStatus;
  }
  
  public CameraStatus[] newArray(int paramInt) {
    return new CameraStatus[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/CameraStatus$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */