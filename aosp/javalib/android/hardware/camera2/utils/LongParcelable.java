package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class LongParcelable implements Parcelable {
  public static final Parcelable.Creator<LongParcelable> CREATOR = new Parcelable.Creator<LongParcelable>() {
      public LongParcelable createFromParcel(Parcel param1Parcel) {
        return new LongParcelable(param1Parcel);
      }
      
      public LongParcelable[] newArray(int param1Int) {
        return new LongParcelable[param1Int];
      }
    };
  
  private long number;
  
  public LongParcelable() {
    this.number = 0L;
  }
  
  public LongParcelable(long paramLong) {
    this.number = paramLong;
  }
  
  private LongParcelable(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getNumber() {
    return this.number;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.number = paramParcel.readLong();
  }
  
  public void setNumber(long paramLong) {
    this.number = paramLong;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.number);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/LongParcelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */