package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<EventStats> {
  public EventStats createFromParcel(Parcel paramParcel) {
    EventStats eventStats = new EventStats();
    eventStats.mEventType = paramParcel.readInt();
    eventStats.mBeginTimeStamp = paramParcel.readLong();
    eventStats.mEndTimeStamp = paramParcel.readLong();
    eventStats.mLastEventTime = paramParcel.readLong();
    eventStats.mTotalTime = paramParcel.readLong();
    eventStats.mCount = paramParcel.readInt();
    return eventStats;
  }
  
  public EventStats[] newArray(int paramInt) {
    return new EventStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/EventStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */