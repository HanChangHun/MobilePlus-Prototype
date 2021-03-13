package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

public final class StorageStats implements Parcelable {
  public static final Parcelable.Creator<StorageStats> CREATOR = new Parcelable.Creator<StorageStats>() {
      public StorageStats createFromParcel(Parcel param1Parcel) {
        return new StorageStats(param1Parcel);
      }
      
      public StorageStats[] newArray(int param1Int) {
        return new StorageStats[param1Int];
      }
    };
  
  public long cacheBytes;
  
  public long codeBytes;
  
  public long dataBytes;
  
  public StorageStats() {}
  
  public StorageStats(Parcel paramParcel) {
    this.codeBytes = paramParcel.readLong();
    this.dataBytes = paramParcel.readLong();
    this.cacheBytes = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getAppBytes() {
    return this.codeBytes;
  }
  
  public long getCacheBytes() {
    return this.cacheBytes;
  }
  
  @Deprecated
  public long getCodeBytes() {
    return getAppBytes();
  }
  
  public long getDataBytes() {
    return this.dataBytes;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.codeBytes);
    paramParcel.writeLong(this.dataBytes);
    paramParcel.writeLong(this.cacheBytes);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/StorageStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */