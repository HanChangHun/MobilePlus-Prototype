package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CameraInfo> {
  public CameraInfo createFromParcel(Parcel paramParcel) {
    CameraInfo cameraInfo = new CameraInfo();
    cameraInfo.readFromParcel(paramParcel);
    return cameraInfo;
  }
  
  public CameraInfo[] newArray(int paramInt) {
    return new CameraInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/CameraInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */