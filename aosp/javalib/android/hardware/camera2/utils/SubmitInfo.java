package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class SubmitInfo implements Parcelable {
  public static final Parcelable.Creator<SubmitInfo> CREATOR = new Parcelable.Creator<SubmitInfo>() {
      public SubmitInfo createFromParcel(Parcel param1Parcel) {
        return new SubmitInfo(param1Parcel);
      }
      
      public SubmitInfo[] newArray(int param1Int) {
        return new SubmitInfo[param1Int];
      }
    };
  
  private long mLastFrameNumber;
  
  private int mRequestId;
  
  public SubmitInfo() {
    this.mRequestId = -1;
    this.mLastFrameNumber = -1L;
  }
  
  public SubmitInfo(int paramInt, long paramLong) {
    this.mRequestId = paramInt;
    this.mLastFrameNumber = paramLong;
  }
  
  private SubmitInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getLastFrameNumber() {
    return this.mLastFrameNumber;
  }
  
  public int getRequestId() {
    return this.mRequestId;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.mRequestId = paramParcel.readInt();
    this.mLastFrameNumber = paramParcel.readLong();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRequestId);
    paramParcel.writeLong(this.mLastFrameNumber);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/SubmitInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */