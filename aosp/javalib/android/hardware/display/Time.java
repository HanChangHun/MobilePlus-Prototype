package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.time.LocalTime;

public final class Time implements Parcelable {
  public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
      public Time createFromParcel(Parcel param1Parcel) {
        return new Time(param1Parcel);
      }
      
      public Time[] newArray(int param1Int) {
        return new Time[param1Int];
      }
    };
  
  private final int mHour;
  
  private final int mMinute;
  
  private final int mNano;
  
  private final int mSecond;
  
  public Time(Parcel paramParcel) {
    this.mHour = paramParcel.readInt();
    this.mMinute = paramParcel.readInt();
    this.mSecond = paramParcel.readInt();
    this.mNano = paramParcel.readInt();
  }
  
  public Time(LocalTime paramLocalTime) {
    this.mHour = paramLocalTime.getHour();
    this.mMinute = paramLocalTime.getMinute();
    this.mSecond = paramLocalTime.getSecond();
    this.mNano = paramLocalTime.getNano();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public LocalTime getLocalTime() {
    return LocalTime.of(this.mHour, this.mMinute, this.mSecond, this.mNano);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mHour);
    paramParcel.writeInt(this.mMinute);
    paramParcel.writeInt(this.mSecond);
    paramParcel.writeInt(this.mNano);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/Time.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */