package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

public final class EventStats implements Parcelable {
  public static final Parcelable.Creator<EventStats> CREATOR = new Parcelable.Creator<EventStats>() {
      public EventStats createFromParcel(Parcel param1Parcel) {
        EventStats eventStats = new EventStats();
        eventStats.mEventType = param1Parcel.readInt();
        eventStats.mBeginTimeStamp = param1Parcel.readLong();
        eventStats.mEndTimeStamp = param1Parcel.readLong();
        eventStats.mLastEventTime = param1Parcel.readLong();
        eventStats.mTotalTime = param1Parcel.readLong();
        eventStats.mCount = param1Parcel.readInt();
        return eventStats;
      }
      
      public EventStats[] newArray(int param1Int) {
        return new EventStats[param1Int];
      }
    };
  
  public long mBeginTimeStamp;
  
  public int mCount;
  
  public long mEndTimeStamp;
  
  public int mEventType;
  
  public long mLastEventTime;
  
  public long mTotalTime;
  
  public EventStats() {}
  
  public EventStats(EventStats paramEventStats) {
    this.mEventType = paramEventStats.mEventType;
    this.mBeginTimeStamp = paramEventStats.mBeginTimeStamp;
    this.mEndTimeStamp = paramEventStats.mEndTimeStamp;
    this.mLastEventTime = paramEventStats.mLastEventTime;
    this.mTotalTime = paramEventStats.mTotalTime;
    this.mCount = paramEventStats.mCount;
  }
  
  public void add(EventStats paramEventStats) {
    if (this.mEventType == paramEventStats.mEventType) {
      if (paramEventStats.mBeginTimeStamp > this.mBeginTimeStamp)
        this.mLastEventTime = Math.max(this.mLastEventTime, paramEventStats.mLastEventTime); 
      this.mBeginTimeStamp = Math.min(this.mBeginTimeStamp, paramEventStats.mBeginTimeStamp);
      this.mEndTimeStamp = Math.max(this.mEndTimeStamp, paramEventStats.mEndTimeStamp);
      this.mTotalTime += paramEventStats.mTotalTime;
      this.mCount += paramEventStats.mCount;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't merge EventStats for event #");
    stringBuilder.append(this.mEventType);
    stringBuilder.append(" with EventStats for event #");
    stringBuilder.append(paramEventStats.mEventType);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getCount() {
    return this.mCount;
  }
  
  public int getEventType() {
    return this.mEventType;
  }
  
  public long getFirstTimeStamp() {
    return this.mBeginTimeStamp;
  }
  
  public long getLastEventTime() {
    return this.mLastEventTime;
  }
  
  public long getLastTimeStamp() {
    return this.mEndTimeStamp;
  }
  
  public long getTotalTime() {
    return this.mTotalTime;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mEventType);
    paramParcel.writeLong(this.mBeginTimeStamp);
    paramParcel.writeLong(this.mEndTimeStamp);
    paramParcel.writeLong(this.mLastEventTime);
    paramParcel.writeLong(this.mTotalTime);
    paramParcel.writeInt(this.mCount);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/EventStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */