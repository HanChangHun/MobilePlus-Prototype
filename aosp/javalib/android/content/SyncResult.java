package android.content;

import android.os.Parcel;
import android.os.Parcelable;

public final class SyncResult implements Parcelable {
  public static final SyncResult ALREADY_IN_PROGRESS = new SyncResult(true);
  
  public static final Parcelable.Creator<SyncResult> CREATOR = new Parcelable.Creator<SyncResult>() {
      public SyncResult createFromParcel(Parcel param1Parcel) {
        return new SyncResult(param1Parcel);
      }
      
      public SyncResult[] newArray(int param1Int) {
        return new SyncResult[param1Int];
      }
    };
  
  public boolean databaseError;
  
  public long delayUntil;
  
  public boolean fullSyncRequested;
  
  public boolean moreRecordsToGet;
  
  public boolean partialSyncUnavailable;
  
  public final SyncStats stats;
  
  public final boolean syncAlreadyInProgress;
  
  public boolean tooManyDeletions;
  
  public boolean tooManyRetries;
  
  public SyncResult() {
    this(false);
  }
  
  private SyncResult(Parcel paramParcel) {
    boolean bool2;
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.syncAlreadyInProgress = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.tooManyDeletions = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.tooManyRetries = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.databaseError = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.fullSyncRequested = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.partialSyncUnavailable = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.moreRecordsToGet = bool2;
    this.delayUntil = paramParcel.readLong();
    this.stats = new SyncStats(paramParcel);
  }
  
  private SyncResult(boolean paramBoolean) {
    this.syncAlreadyInProgress = paramBoolean;
    this.tooManyDeletions = false;
    this.tooManyRetries = false;
    this.fullSyncRequested = false;
    this.partialSyncUnavailable = false;
    this.moreRecordsToGet = false;
    this.delayUntil = 0L;
    this.stats = new SyncStats();
  }
  
  public void clear() {
    if (!this.syncAlreadyInProgress) {
      this.tooManyDeletions = false;
      this.tooManyRetries = false;
      this.databaseError = false;
      this.fullSyncRequested = false;
      this.partialSyncUnavailable = false;
      this.moreRecordsToGet = false;
      this.delayUntil = 0L;
      this.stats.clear();
      return;
    } 
    throw new UnsupportedOperationException("you are not allowed to clear the ALREADY_IN_PROGRESS SyncStats");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean hasError() {
    return (hasSoftError() || hasHardError());
  }
  
  public boolean hasHardError() {
    return (this.stats.numParseExceptions > 0L || this.stats.numConflictDetectedExceptions > 0L || this.stats.numAuthExceptions > 0L || this.tooManyDeletions || this.tooManyRetries || this.databaseError);
  }
  
  public boolean hasSoftError() {
    return (this.syncAlreadyInProgress || this.stats.numIoExceptions > 0L);
  }
  
  public boolean madeSomeProgress() {
    return ((this.stats.numDeletes > 0L && !this.tooManyDeletions) || this.stats.numInserts > 0L || this.stats.numUpdates > 0L);
  }
  
  public String toDebugString() {
    StringBuffer stringBuffer = new StringBuffer();
    if (this.fullSyncRequested)
      stringBuffer.append("f1"); 
    if (this.partialSyncUnavailable)
      stringBuffer.append("r1"); 
    if (hasHardError())
      stringBuffer.append("X1"); 
    if (this.stats.numParseExceptions > 0L) {
      stringBuffer.append("e");
      stringBuffer.append(this.stats.numParseExceptions);
    } 
    if (this.stats.numConflictDetectedExceptions > 0L) {
      stringBuffer.append("c");
      stringBuffer.append(this.stats.numConflictDetectedExceptions);
    } 
    if (this.stats.numAuthExceptions > 0L) {
      stringBuffer.append("a");
      stringBuffer.append(this.stats.numAuthExceptions);
    } 
    if (this.tooManyDeletions)
      stringBuffer.append("D1"); 
    if (this.tooManyRetries)
      stringBuffer.append("R1"); 
    if (this.databaseError)
      stringBuffer.append("b1"); 
    if (hasSoftError())
      stringBuffer.append("x1"); 
    if (this.syncAlreadyInProgress)
      stringBuffer.append("l1"); 
    if (this.stats.numIoExceptions > 0L) {
      stringBuffer.append("I");
      stringBuffer.append(this.stats.numIoExceptions);
    } 
    return stringBuffer.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SyncResult:");
    if (this.syncAlreadyInProgress) {
      stringBuilder.append(" syncAlreadyInProgress: ");
      stringBuilder.append(this.syncAlreadyInProgress);
    } 
    if (this.tooManyDeletions) {
      stringBuilder.append(" tooManyDeletions: ");
      stringBuilder.append(this.tooManyDeletions);
    } 
    if (this.tooManyRetries) {
      stringBuilder.append(" tooManyRetries: ");
      stringBuilder.append(this.tooManyRetries);
    } 
    if (this.databaseError) {
      stringBuilder.append(" databaseError: ");
      stringBuilder.append(this.databaseError);
    } 
    if (this.fullSyncRequested) {
      stringBuilder.append(" fullSyncRequested: ");
      stringBuilder.append(this.fullSyncRequested);
    } 
    if (this.partialSyncUnavailable) {
      stringBuilder.append(" partialSyncUnavailable: ");
      stringBuilder.append(this.partialSyncUnavailable);
    } 
    if (this.moreRecordsToGet) {
      stringBuilder.append(" moreRecordsToGet: ");
      stringBuilder.append(this.moreRecordsToGet);
    } 
    if (this.delayUntil > 0L) {
      stringBuilder.append(" delayUntil: ");
      stringBuilder.append(this.delayUntil);
    } 
    stringBuilder.append(this.stats);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.syncAlreadyInProgress);
    paramParcel.writeInt(this.tooManyDeletions);
    paramParcel.writeInt(this.tooManyRetries);
    paramParcel.writeInt(this.databaseError);
    paramParcel.writeInt(this.fullSyncRequested);
    paramParcel.writeInt(this.partialSyncUnavailable);
    paramParcel.writeInt(this.moreRecordsToGet);
    paramParcel.writeLong(this.delayUntil);
    this.stats.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */