package android.hardware.camera2.impl;

import android.os.Parcel;
import android.os.Parcelable;

public class PhysicalCaptureResultInfo implements Parcelable {
  public static final Parcelable.Creator<PhysicalCaptureResultInfo> CREATOR = new Parcelable.Creator<PhysicalCaptureResultInfo>() {
      public PhysicalCaptureResultInfo createFromParcel(Parcel param1Parcel) {
        return new PhysicalCaptureResultInfo(param1Parcel);
      }
      
      public PhysicalCaptureResultInfo[] newArray(int param1Int) {
        return new PhysicalCaptureResultInfo[param1Int];
      }
    };
  
  private String cameraId;
  
  private CameraMetadataNative cameraMetadata;
  
  private PhysicalCaptureResultInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public PhysicalCaptureResultInfo(String paramString, CameraMetadataNative paramCameraMetadataNative) {
    this.cameraId = paramString;
    this.cameraMetadata = paramCameraMetadataNative;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getCameraId() {
    return this.cameraId;
  }
  
  public CameraMetadataNative getCameraMetadata() {
    return this.cameraMetadata;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.cameraId = paramParcel.readString();
    CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
    this.cameraMetadata = cameraMetadataNative;
    cameraMetadataNative.readFromParcel(paramParcel);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.cameraId);
    this.cameraMetadata.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/PhysicalCaptureResultInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */