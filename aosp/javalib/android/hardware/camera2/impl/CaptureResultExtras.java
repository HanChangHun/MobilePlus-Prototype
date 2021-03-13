package android.hardware.camera2.impl;

import android.os.Parcel;
import android.os.Parcelable;

public class CaptureResultExtras implements Parcelable {
  public static final Parcelable.Creator<CaptureResultExtras> CREATOR = new Parcelable.Creator<CaptureResultExtras>() {
      public CaptureResultExtras createFromParcel(Parcel param1Parcel) {
        return new CaptureResultExtras(param1Parcel);
      }
      
      public CaptureResultExtras[] newArray(int param1Int) {
        return new CaptureResultExtras[param1Int];
      }
    };
  
  private int afTriggerId;
  
  private String errorPhysicalCameraId;
  
  private int errorStreamId;
  
  private long frameNumber;
  
  private long lastCompletedRegularFrameNumber;
  
  private long lastCompletedReprocessFrameNumber;
  
  private long lastCompletedZslFrameNumber;
  
  private int partialResultCount;
  
  private int precaptureTriggerId;
  
  private int requestId;
  
  private int subsequenceId;
  
  public CaptureResultExtras(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, int paramInt6, String paramString, long paramLong2, long paramLong3, long paramLong4) {
    this.requestId = paramInt1;
    this.subsequenceId = paramInt2;
    this.afTriggerId = paramInt3;
    this.precaptureTriggerId = paramInt4;
    this.frameNumber = paramLong1;
    this.partialResultCount = paramInt5;
    this.errorStreamId = paramInt6;
    this.errorPhysicalCameraId = paramString;
    this.lastCompletedRegularFrameNumber = paramLong2;
    this.lastCompletedReprocessFrameNumber = paramLong3;
    this.lastCompletedZslFrameNumber = paramLong4;
  }
  
  private CaptureResultExtras(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getAfTriggerId() {
    return this.afTriggerId;
  }
  
  public String getErrorPhysicalCameraId() {
    return this.errorPhysicalCameraId;
  }
  
  public int getErrorStreamId() {
    return this.errorStreamId;
  }
  
  public long getFrameNumber() {
    return this.frameNumber;
  }
  
  public long getLastCompletedRegularFrameNumber() {
    return this.lastCompletedRegularFrameNumber;
  }
  
  public long getLastCompletedReprocessFrameNumber() {
    return this.lastCompletedReprocessFrameNumber;
  }
  
  public long getLastCompletedZslFrameNumber() {
    return this.lastCompletedZslFrameNumber;
  }
  
  public int getPartialResultCount() {
    return this.partialResultCount;
  }
  
  public int getPrecaptureTriggerId() {
    return this.precaptureTriggerId;
  }
  
  public int getRequestId() {
    return this.requestId;
  }
  
  public int getSubsequenceId() {
    return this.subsequenceId;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.requestId = paramParcel.readInt();
    this.subsequenceId = paramParcel.readInt();
    this.afTriggerId = paramParcel.readInt();
    this.precaptureTriggerId = paramParcel.readInt();
    this.frameNumber = paramParcel.readLong();
    this.partialResultCount = paramParcel.readInt();
    this.errorStreamId = paramParcel.readInt();
    if (paramParcel.readBoolean())
      this.errorPhysicalCameraId = paramParcel.readString(); 
    this.lastCompletedRegularFrameNumber = paramParcel.readLong();
    this.lastCompletedReprocessFrameNumber = paramParcel.readLong();
    this.lastCompletedZslFrameNumber = paramParcel.readLong();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.requestId);
    paramParcel.writeInt(this.subsequenceId);
    paramParcel.writeInt(this.afTriggerId);
    paramParcel.writeInt(this.precaptureTriggerId);
    paramParcel.writeLong(this.frameNumber);
    paramParcel.writeInt(this.partialResultCount);
    paramParcel.writeInt(this.errorStreamId);
    String str = this.errorPhysicalCameraId;
    if (str != null && !str.isEmpty()) {
      paramParcel.writeBoolean(true);
      paramParcel.writeString(this.errorPhysicalCameraId);
    } else {
      paramParcel.writeBoolean(false);
    } 
    paramParcel.writeLong(this.lastCompletedRegularFrameNumber);
    paramParcel.writeLong(this.lastCompletedReprocessFrameNumber);
    paramParcel.writeLong(this.lastCompletedZslFrameNumber);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CaptureResultExtras.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */