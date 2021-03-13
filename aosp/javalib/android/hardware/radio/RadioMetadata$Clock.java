package android.hardware.radio;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class Clock implements Parcelable {
  public static final Parcelable.Creator<Clock> CREATOR = new Parcelable.Creator<Clock>() {
      public RadioMetadata.Clock createFromParcel(Parcel param2Parcel) {
        return new RadioMetadata.Clock(param2Parcel);
      }
      
      public RadioMetadata.Clock[] newArray(int param2Int) {
        return new RadioMetadata.Clock[param2Int];
      }
    };
  
  private final int mTimezoneOffsetMinutes;
  
  private final long mUtcEpochSeconds;
  
  public Clock(long paramLong, int paramInt) {
    this.mUtcEpochSeconds = paramLong;
    this.mTimezoneOffsetMinutes = paramInt;
  }
  
  private Clock(Parcel paramParcel) {
    this.mUtcEpochSeconds = paramParcel.readLong();
    this.mTimezoneOffsetMinutes = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getTimezoneOffsetMinutes() {
    return this.mTimezoneOffsetMinutes;
  }
  
  public long getUtcEpochSeconds() {
    return this.mUtcEpochSeconds;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mUtcEpochSeconds);
    paramParcel.writeInt(this.mTimezoneOffsetMinutes);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioMetadata$Clock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */