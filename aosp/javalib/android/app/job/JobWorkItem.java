package android.app.job;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public final class JobWorkItem implements Parcelable {
  public static final Parcelable.Creator<JobWorkItem> CREATOR = new Parcelable.Creator<JobWorkItem>() {
      public JobWorkItem createFromParcel(Parcel param1Parcel) {
        return new JobWorkItem(param1Parcel);
      }
      
      public JobWorkItem[] newArray(int param1Int) {
        return new JobWorkItem[param1Int];
      }
    };
  
  int mDeliveryCount;
  
  Object mGrants;
  
  final Intent mIntent;
  
  final long mNetworkDownloadBytes;
  
  final long mNetworkUploadBytes;
  
  int mWorkId;
  
  public JobWorkItem(Intent paramIntent) {
    this.mIntent = paramIntent;
    this.mNetworkDownloadBytes = -1L;
    this.mNetworkUploadBytes = -1L;
  }
  
  public JobWorkItem(Intent paramIntent, long paramLong1, long paramLong2) {
    this.mIntent = paramIntent;
    this.mNetworkDownloadBytes = paramLong1;
    this.mNetworkUploadBytes = paramLong2;
  }
  
  JobWorkItem(Parcel paramParcel) {
    if (paramParcel.readInt() != 0) {
      this.mIntent = (Intent)Intent.CREATOR.createFromParcel(paramParcel);
    } else {
      this.mIntent = null;
    } 
    this.mNetworkDownloadBytes = paramParcel.readLong();
    this.mNetworkUploadBytes = paramParcel.readLong();
    this.mDeliveryCount = paramParcel.readInt();
    this.mWorkId = paramParcel.readInt();
  }
  
  public void bumpDeliveryCount() {
    this.mDeliveryCount++;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getDeliveryCount() {
    return this.mDeliveryCount;
  }
  
  public long getEstimatedNetworkDownloadBytes() {
    return this.mNetworkDownloadBytes;
  }
  
  public long getEstimatedNetworkUploadBytes() {
    return this.mNetworkUploadBytes;
  }
  
  public Object getGrants() {
    return this.mGrants;
  }
  
  public Intent getIntent() {
    return this.mIntent;
  }
  
  public int getWorkId() {
    return this.mWorkId;
  }
  
  public void setGrants(Object paramObject) {
    this.mGrants = paramObject;
  }
  
  public void setWorkId(int paramInt) {
    this.mWorkId = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(64);
    stringBuilder.append("JobWorkItem{id=");
    stringBuilder.append(this.mWorkId);
    stringBuilder.append(" intent=");
    stringBuilder.append(this.mIntent);
    if (this.mNetworkDownloadBytes != -1L) {
      stringBuilder.append(" downloadBytes=");
      stringBuilder.append(this.mNetworkDownloadBytes);
    } 
    if (this.mNetworkUploadBytes != -1L) {
      stringBuilder.append(" uploadBytes=");
      stringBuilder.append(this.mNetworkUploadBytes);
    } 
    if (this.mDeliveryCount != 0) {
      stringBuilder.append(" dcount=");
      stringBuilder.append(this.mDeliveryCount);
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mIntent != null) {
      paramParcel.writeInt(1);
      this.mIntent.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeLong(this.mNetworkDownloadBytes);
    paramParcel.writeLong(this.mNetworkUploadBytes);
    paramParcel.writeInt(this.mDeliveryCount);
    paramParcel.writeInt(this.mWorkId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobWorkItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */