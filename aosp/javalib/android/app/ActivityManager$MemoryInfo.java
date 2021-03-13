package android.app;

import android.os.Parcel;
import android.os.Parcelable;

public class MemoryInfo implements Parcelable {
  public static final Parcelable.Creator<MemoryInfo> CREATOR = new Parcelable.Creator<MemoryInfo>() {
      public ActivityManager.MemoryInfo createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.MemoryInfo(param2Parcel);
      }
      
      public ActivityManager.MemoryInfo[] newArray(int param2Int) {
        return new ActivityManager.MemoryInfo[param2Int];
      }
    };
  
  public long availMem;
  
  public long foregroundAppThreshold;
  
  public long hiddenAppThreshold;
  
  public boolean lowMemory;
  
  public long secondaryServerThreshold;
  
  public long threshold;
  
  public long totalMem;
  
  public long visibleAppThreshold;
  
  public MemoryInfo() {}
  
  private MemoryInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    boolean bool;
    this.availMem = paramParcel.readLong();
    this.totalMem = paramParcel.readLong();
    this.threshold = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.lowMemory = bool;
    this.hiddenAppThreshold = paramParcel.readLong();
    this.secondaryServerThreshold = paramParcel.readLong();
    this.visibleAppThreshold = paramParcel.readLong();
    this.foregroundAppThreshold = paramParcel.readLong();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.availMem);
    paramParcel.writeLong(this.totalMem);
    paramParcel.writeLong(this.threshold);
    paramParcel.writeInt(this.lowMemory);
    paramParcel.writeLong(this.hiddenAppThreshold);
    paramParcel.writeLong(this.secondaryServerThreshold);
    paramParcel.writeLong(this.visibleAppThreshold);
    paramParcel.writeLong(this.foregroundAppThreshold);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$MemoryInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */