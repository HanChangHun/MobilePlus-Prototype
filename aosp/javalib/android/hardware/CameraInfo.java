package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

public class CameraInfo implements Parcelable {
  public static final Parcelable.Creator<CameraInfo> CREATOR = new Parcelable.Creator<CameraInfo>() {
      public CameraInfo createFromParcel(Parcel param1Parcel) {
        CameraInfo cameraInfo = new CameraInfo();
        cameraInfo.readFromParcel(param1Parcel);
        return cameraInfo;
      }
      
      public CameraInfo[] newArray(int param1Int) {
        return new CameraInfo[param1Int];
      }
    };
  
  public Camera.CameraInfo info = new Camera.CameraInfo();
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.info.facing = paramParcel.readInt();
    this.info.orientation = paramParcel.readInt();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.info.facing);
    paramParcel.writeInt(this.info.orientation);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/CameraInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */