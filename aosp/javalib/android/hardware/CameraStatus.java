package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

public class CameraStatus implements Parcelable {
  public static final Parcelable.Creator<CameraStatus> CREATOR = new Parcelable.Creator<CameraStatus>() {
      public CameraStatus createFromParcel(Parcel param1Parcel) {
        CameraStatus cameraStatus = new CameraStatus();
        cameraStatus.readFromParcel(param1Parcel);
        return cameraStatus;
      }
      
      public CameraStatus[] newArray(int param1Int) {
        return new CameraStatus[param1Int];
      }
    };
  
  public String cameraId;
  
  public int status;
  
  public String[] unavailablePhysicalCameras;
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.cameraId = paramParcel.readString();
    this.status = paramParcel.readInt();
    this.unavailablePhysicalCameras = paramParcel.readStringArray();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.cameraId);
    paramParcel.writeInt(this.status);
    paramParcel.writeStringArray(this.unavailablePhysicalCameras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/CameraStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */