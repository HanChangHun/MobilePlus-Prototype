package android.hardware.camera2.utils;

import android.hardware.camera2.params.SessionConfiguration;
import android.os.Parcel;
import android.os.Parcelable;

public class CameraIdAndSessionConfiguration implements Parcelable {
  public static final Parcelable.Creator<CameraIdAndSessionConfiguration> CREATOR = new Parcelable.Creator<CameraIdAndSessionConfiguration>() {
      public CameraIdAndSessionConfiguration createFromParcel(Parcel param1Parcel) {
        return new CameraIdAndSessionConfiguration(param1Parcel);
      }
      
      public CameraIdAndSessionConfiguration[] newArray(int param1Int) {
        return new CameraIdAndSessionConfiguration[param1Int];
      }
    };
  
  private String mCameraId;
  
  private SessionConfiguration mSessionConfiguration;
  
  private CameraIdAndSessionConfiguration(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public CameraIdAndSessionConfiguration(String paramString, SessionConfiguration paramSessionConfiguration) {
    this.mCameraId = paramString;
    this.mSessionConfiguration = paramSessionConfiguration;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getCameraId() {
    return this.mCameraId;
  }
  
  public SessionConfiguration getSessionConfiguration() {
    return this.mSessionConfiguration;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.mCameraId = paramParcel.readString();
    this.mSessionConfiguration = (SessionConfiguration)SessionConfiguration.CREATOR.createFromParcel(paramParcel);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mCameraId);
    this.mSessionConfiguration.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/CameraIdAndSessionConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */