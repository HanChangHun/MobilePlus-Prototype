package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;

public final class AlarmClockInfo implements Parcelable {
  public static final Parcelable.Creator<AlarmClockInfo> CREATOR = new Parcelable.Creator<AlarmClockInfo>() {
      public AlarmManager.AlarmClockInfo createFromParcel(Parcel param2Parcel) {
        return new AlarmManager.AlarmClockInfo(param2Parcel);
      }
      
      public AlarmManager.AlarmClockInfo[] newArray(int param2Int) {
        return new AlarmManager.AlarmClockInfo[param2Int];
      }
    };
  
  private final PendingIntent mShowIntent;
  
  private final long mTriggerTime;
  
  public AlarmClockInfo(long paramLong, PendingIntent paramPendingIntent) {
    this.mTriggerTime = paramLong;
    this.mShowIntent = paramPendingIntent;
  }
  
  AlarmClockInfo(Parcel paramParcel) {
    this.mTriggerTime = paramParcel.readLong();
    this.mShowIntent = (PendingIntent)paramParcel.readParcelable(PendingIntent.class.getClassLoader());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1112396529665L, this.mTriggerTime);
    PendingIntent pendingIntent = this.mShowIntent;
    if (pendingIntent != null)
      pendingIntent.dumpDebug(paramProtoOutputStream, 1146756268034L); 
    paramProtoOutputStream.end(paramLong);
  }
  
  public PendingIntent getShowIntent() {
    return this.mShowIntent;
  }
  
  public long getTriggerTime() {
    return this.mTriggerTime;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mTriggerTime);
    paramParcel.writeParcelable(this.mShowIntent, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AlarmManager$AlarmClockInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */