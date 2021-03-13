package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

public final class ExternalStorageStats implements Parcelable {
  public static final Parcelable.Creator<ExternalStorageStats> CREATOR = new Parcelable.Creator<ExternalStorageStats>() {
      public ExternalStorageStats createFromParcel(Parcel param1Parcel) {
        return new ExternalStorageStats(param1Parcel);
      }
      
      public ExternalStorageStats[] newArray(int param1Int) {
        return new ExternalStorageStats[param1Int];
      }
    };
  
  public long appBytes;
  
  public long audioBytes;
  
  public long imageBytes;
  
  public long obbBytes;
  
  public long totalBytes;
  
  public long videoBytes;
  
  public ExternalStorageStats() {}
  
  public ExternalStorageStats(Parcel paramParcel) {
    this.totalBytes = paramParcel.readLong();
    this.audioBytes = paramParcel.readLong();
    this.videoBytes = paramParcel.readLong();
    this.imageBytes = paramParcel.readLong();
    this.appBytes = paramParcel.readLong();
    this.obbBytes = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getAppBytes() {
    return this.appBytes;
  }
  
  public long getAudioBytes() {
    return this.audioBytes;
  }
  
  public long getImageBytes() {
    return this.imageBytes;
  }
  
  public long getObbBytes() {
    return this.obbBytes;
  }
  
  public long getTotalBytes() {
    return this.totalBytes;
  }
  
  public long getVideoBytes() {
    return this.videoBytes;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.totalBytes);
    paramParcel.writeLong(this.audioBytes);
    paramParcel.writeLong(this.videoBytes);
    paramParcel.writeLong(this.imageBytes);
    paramParcel.writeLong(this.appBytes);
    paramParcel.writeLong(this.obbBytes);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ExternalStorageStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */