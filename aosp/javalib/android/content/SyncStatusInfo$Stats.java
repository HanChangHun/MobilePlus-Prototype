package android.content;

import android.os.Parcel;

public class Stats {
  public int numCancels;
  
  public int numFailures;
  
  public int numSourceFeed;
  
  public int numSourceLocal;
  
  public int numSourceOther;
  
  public int numSourcePeriodic;
  
  public int numSourcePoll;
  
  public int numSourceUser;
  
  public int numSyncs;
  
  public long totalElapsedTime;
  
  public void clear() {
    this.totalElapsedTime = 0L;
    this.numSyncs = 0;
    this.numSourcePoll = 0;
    this.numSourceOther = 0;
    this.numSourceLocal = 0;
    this.numSourceUser = 0;
    this.numSourcePeriodic = 0;
    this.numSourceFeed = 0;
    this.numFailures = 0;
    this.numCancels = 0;
  }
  
  public void copyTo(Stats paramStats) {
    paramStats.totalElapsedTime = this.totalElapsedTime;
    paramStats.numSyncs = this.numSyncs;
    paramStats.numSourcePoll = this.numSourcePoll;
    paramStats.numSourceOther = this.numSourceOther;
    paramStats.numSourceLocal = this.numSourceLocal;
    paramStats.numSourceUser = this.numSourceUser;
    paramStats.numSourcePeriodic = this.numSourcePeriodic;
    paramStats.numSourceFeed = this.numSourceFeed;
    paramStats.numFailures = this.numFailures;
    paramStats.numCancels = this.numCancels;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.totalElapsedTime = paramParcel.readLong();
    this.numSyncs = paramParcel.readInt();
    this.numSourcePoll = paramParcel.readInt();
    this.numSourceOther = paramParcel.readInt();
    this.numSourceLocal = paramParcel.readInt();
    this.numSourceUser = paramParcel.readInt();
    this.numSourcePeriodic = paramParcel.readInt();
    this.numSourceFeed = paramParcel.readInt();
    this.numFailures = paramParcel.readInt();
    this.numCancels = paramParcel.readInt();
  }
  
  public void writeToParcel(Parcel paramParcel) {
    paramParcel.writeLong(this.totalElapsedTime);
    paramParcel.writeInt(this.numSyncs);
    paramParcel.writeInt(this.numSourcePoll);
    paramParcel.writeInt(this.numSourceOther);
    paramParcel.writeInt(this.numSourceLocal);
    paramParcel.writeInt(this.numSourceUser);
    paramParcel.writeInt(this.numSourcePeriodic);
    paramParcel.writeInt(this.numSourceFeed);
    paramParcel.writeInt(this.numFailures);
    paramParcel.writeInt(this.numCancels);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncStatusInfo$Stats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */