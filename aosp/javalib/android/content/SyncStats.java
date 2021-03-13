package android.content;

import android.os.Parcel;
import android.os.Parcelable;

public class SyncStats implements Parcelable {
  public static final Parcelable.Creator<SyncStats> CREATOR = new Parcelable.Creator<SyncStats>() {
      public SyncStats createFromParcel(Parcel param1Parcel) {
        return new SyncStats(param1Parcel);
      }
      
      public SyncStats[] newArray(int param1Int) {
        return new SyncStats[param1Int];
      }
    };
  
  public long numAuthExceptions = 0L;
  
  public long numConflictDetectedExceptions = 0L;
  
  public long numDeletes = 0L;
  
  public long numEntries = 0L;
  
  public long numInserts = 0L;
  
  public long numIoExceptions = 0L;
  
  public long numParseExceptions = 0L;
  
  public long numSkippedEntries = 0L;
  
  public long numUpdates = 0L;
  
  public SyncStats() {}
  
  public SyncStats(Parcel paramParcel) {}
  
  public void clear() {
    this.numAuthExceptions = 0L;
    this.numIoExceptions = 0L;
    this.numParseExceptions = 0L;
    this.numConflictDetectedExceptions = 0L;
    this.numInserts = 0L;
    this.numUpdates = 0L;
    this.numDeletes = 0L;
    this.numEntries = 0L;
    this.numSkippedEntries = 0L;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" stats [");
    if (this.numAuthExceptions > 0L) {
      stringBuilder.append(" numAuthExceptions: ");
      stringBuilder.append(this.numAuthExceptions);
    } 
    if (this.numIoExceptions > 0L) {
      stringBuilder.append(" numIoExceptions: ");
      stringBuilder.append(this.numIoExceptions);
    } 
    if (this.numParseExceptions > 0L) {
      stringBuilder.append(" numParseExceptions: ");
      stringBuilder.append(this.numParseExceptions);
    } 
    if (this.numConflictDetectedExceptions > 0L) {
      stringBuilder.append(" numConflictDetectedExceptions: ");
      stringBuilder.append(this.numConflictDetectedExceptions);
    } 
    if (this.numInserts > 0L) {
      stringBuilder.append(" numInserts: ");
      stringBuilder.append(this.numInserts);
    } 
    if (this.numUpdates > 0L) {
      stringBuilder.append(" numUpdates: ");
      stringBuilder.append(this.numUpdates);
    } 
    if (this.numDeletes > 0L) {
      stringBuilder.append(" numDeletes: ");
      stringBuilder.append(this.numDeletes);
    } 
    if (this.numEntries > 0L) {
      stringBuilder.append(" numEntries: ");
      stringBuilder.append(this.numEntries);
    } 
    if (this.numSkippedEntries > 0L) {
      stringBuilder.append(" numSkippedEntries: ");
      stringBuilder.append(this.numSkippedEntries);
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.numAuthExceptions);
    paramParcel.writeLong(this.numIoExceptions);
    paramParcel.writeLong(this.numParseExceptions);
    paramParcel.writeLong(this.numConflictDetectedExceptions);
    paramParcel.writeLong(this.numInserts);
    paramParcel.writeLong(this.numUpdates);
    paramParcel.writeLong(this.numDeletes);
    paramParcel.writeLong(this.numEntries);
    paramParcel.writeLong(this.numSkippedEntries);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */