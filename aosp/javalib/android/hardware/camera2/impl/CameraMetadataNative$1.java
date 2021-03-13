package android.hardware.camera2.impl;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CameraMetadataNative> {
  public CameraMetadataNative createFromParcel(Parcel paramParcel) {
    CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
    cameraMetadataNative.readFromParcel(paramParcel);
    return cameraMetadataNative;
  }
  
  public CameraMetadataNative[] newArray(int paramInt) {
    return new CameraMetadataNative[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraMetadataNative$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */