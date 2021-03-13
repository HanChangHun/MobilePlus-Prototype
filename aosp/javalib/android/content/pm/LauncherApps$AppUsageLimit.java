package android.content.pm;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class AppUsageLimit implements Parcelable {
  public static final Parcelable.Creator<AppUsageLimit> CREATOR = new Parcelable.Creator<AppUsageLimit>() {
      public LauncherApps.AppUsageLimit createFromParcel(Parcel param2Parcel) {
        return new LauncherApps.AppUsageLimit(param2Parcel);
      }
      
      public LauncherApps.AppUsageLimit[] newArray(int param2Int) {
        return new LauncherApps.AppUsageLimit[param2Int];
      }
    };
  
  private final long mTotalUsageLimit;
  
  private final long mUsageRemaining;
  
  public AppUsageLimit(long paramLong1, long paramLong2) {
    this.mTotalUsageLimit = paramLong1;
    this.mUsageRemaining = paramLong2;
  }
  
  private AppUsageLimit(Parcel paramParcel) {
    this.mTotalUsageLimit = paramParcel.readLong();
    this.mUsageRemaining = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getTotalUsageLimit() {
    return this.mTotalUsageLimit;
  }
  
  public long getUsageRemaining() {
    return this.mUsageRemaining;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mTotalUsageLimit);
    paramParcel.writeLong(this.mUsageRemaining);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$AppUsageLimit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */