package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

public class GsiProgress implements Parcelable {
  public static final Parcelable.Creator<GsiProgress> CREATOR = new Parcelable.Creator<GsiProgress>() {
      public GsiProgress createFromParcel(Parcel param1Parcel) {
        GsiProgress gsiProgress = new GsiProgress();
        gsiProgress.readFromParcel(param1Parcel);
        return gsiProgress;
      }
      
      public GsiProgress[] newArray(int param1Int) {
        return new GsiProgress[param1Int];
      }
    };
  
  public long bytes_processed;
  
  public int status;
  
  public String step;
  
  public long total_bytes;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      this.step = paramParcel.readString();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.status = paramParcel.readInt();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.bytes_processed = paramParcel.readLong();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.total_bytes = paramParcel.readLong();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeString(this.step);
    paramParcel.writeInt(this.status);
    paramParcel.writeLong(this.bytes_processed);
    paramParcel.writeLong(this.total_bytes);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - i);
    paramParcel.setDataPosition(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/GsiProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */