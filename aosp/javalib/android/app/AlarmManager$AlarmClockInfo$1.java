package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AlarmManager.AlarmClockInfo> {
  public AlarmManager.AlarmClockInfo createFromParcel(Parcel paramParcel) {
    return new AlarmManager.AlarmClockInfo(paramParcel);
  }
  
  public AlarmManager.AlarmClockInfo[] newArray(int paramInt) {
    return new AlarmManager.AlarmClockInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AlarmManager$AlarmClockInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */