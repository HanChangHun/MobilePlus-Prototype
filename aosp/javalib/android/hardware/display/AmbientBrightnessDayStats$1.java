package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AmbientBrightnessDayStats> {
  public AmbientBrightnessDayStats createFromParcel(Parcel paramParcel) {
    return new AmbientBrightnessDayStats(paramParcel, null);
  }
  
  public AmbientBrightnessDayStats[] newArray(int paramInt) {
    return new AmbientBrightnessDayStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/AmbientBrightnessDayStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */