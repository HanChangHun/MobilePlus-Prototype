package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class SyncStatusInfo implements Parcelable {
  public static final Parcelable.Creator<SyncStatusInfo> CREATOR = new Parcelable.Creator<SyncStatusInfo>() {
      public SyncStatusInfo createFromParcel(Parcel param1Parcel) {
        return new SyncStatusInfo(param1Parcel);
      }
      
      public SyncStatusInfo[] newArray(int param1Int) {
        return new SyncStatusInfo[param1Int];
      }
    };
  
  private static final int MAX_EVENT_COUNT = 10;
  
  private static final int SOURCE_COUNT = 6;
  
  private static final String TAG = "Sync";
  
  static final int VERSION = 6;
  
  public final int authorityId;
  
  public long initialFailureTime;
  
  public boolean initialize;
  
  public String lastFailureMesg;
  
  public int lastFailureSource;
  
  public long lastFailureTime;
  
  public int lastSuccessSource;
  
  public long lastSuccessTime;
  
  public long lastTodayResetTime;
  
  private final ArrayList<Long> mLastEventTimes;
  
  private final ArrayList<String> mLastEvents;
  
  public boolean pending;
  
  public final long[] perSourceLastFailureTimes;
  
  public final long[] perSourceLastSuccessTimes;
  
  private ArrayList<Long> periodicSyncTimes;
  
  public final Stats todayStats;
  
  public final Stats totalStats;
  
  public final Stats yesterdayStats;
  
  public SyncStatusInfo(int paramInt) {
    this.totalStats = new Stats();
    this.todayStats = new Stats();
    this.yesterdayStats = new Stats();
    this.perSourceLastSuccessTimes = new long[6];
    this.perSourceLastFailureTimes = new long[6];
    this.mLastEventTimes = new ArrayList<>();
    this.mLastEvents = new ArrayList<>();
    this.authorityId = paramInt;
  }
  
  public SyncStatusInfo(int paramInt, SyncStatusInfo paramSyncStatusInfo) {
    this.totalStats = new Stats();
    this.todayStats = new Stats();
    this.yesterdayStats = new Stats();
    this.perSourceLastSuccessTimes = new long[6];
    this.perSourceLastFailureTimes = new long[6];
    this.mLastEventTimes = new ArrayList<>();
    this.mLastEvents = new ArrayList<>();
    this.authorityId = paramInt;
    copyFrom(paramSyncStatusInfo);
  }
  
  public SyncStatusInfo(SyncStatusInfo paramSyncStatusInfo) {
    this.totalStats = new Stats();
    this.todayStats = new Stats();
    this.yesterdayStats = new Stats();
    this.perSourceLastSuccessTimes = new long[6];
    this.perSourceLastFailureTimes = new long[6];
    this.mLastEventTimes = new ArrayList<>();
    this.mLastEvents = new ArrayList<>();
    this.authorityId = paramSyncStatusInfo.authorityId;
    copyFrom(paramSyncStatusInfo);
  }
  
  public SyncStatusInfo(Parcel paramParcel) {
    boolean bool;
    this.totalStats = new Stats();
    this.todayStats = new Stats();
    this.yesterdayStats = new Stats();
    this.perSourceLastSuccessTimes = new long[6];
    this.perSourceLastFailureTimes = new long[6];
    this.mLastEventTimes = new ArrayList<>();
    this.mLastEvents = new ArrayList<>();
    int i = paramParcel.readInt();
    if (i != 6 && i != 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown version: ");
      stringBuilder.append(i);
      Log.w("SyncStatusInfo", stringBuilder.toString());
    } 
    this.authorityId = paramParcel.readInt();
    this.totalStats.totalElapsedTime = paramParcel.readLong();
    this.totalStats.numSyncs = paramParcel.readInt();
    this.totalStats.numSourcePoll = paramParcel.readInt();
    this.totalStats.numSourceOther = paramParcel.readInt();
    this.totalStats.numSourceLocal = paramParcel.readInt();
    this.totalStats.numSourceUser = paramParcel.readInt();
    this.lastSuccessTime = paramParcel.readLong();
    this.lastSuccessSource = paramParcel.readInt();
    this.lastFailureTime = paramParcel.readLong();
    this.lastFailureSource = paramParcel.readInt();
    this.lastFailureMesg = paramParcel.readString();
    this.initialFailureTime = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.pending = bool;
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.initialize = bool;
    if (i == 1) {
      this.periodicSyncTimes = null;
    } else {
      int j = paramParcel.readInt();
      if (j < 0) {
        this.periodicSyncTimes = null;
      } else {
        this.periodicSyncTimes = new ArrayList<>();
        for (byte b = 0; b < j; b++)
          this.periodicSyncTimes.add(Long.valueOf(paramParcel.readLong())); 
      } 
      if (i >= 3) {
        this.mLastEventTimes.clear();
        this.mLastEvents.clear();
        j = paramParcel.readInt();
        for (byte b = 0; b < j; b++) {
          this.mLastEventTimes.add(Long.valueOf(paramParcel.readLong()));
          this.mLastEvents.add(paramParcel.readString());
        } 
      } 
    } 
    if (i < 4) {
      Stats stats = this.totalStats;
      stats.numSourcePeriodic = stats.numSyncs - this.totalStats.numSourceLocal - this.totalStats.numSourcePoll - this.totalStats.numSourceOther - this.totalStats.numSourceUser;
      if (this.totalStats.numSourcePeriodic < 0)
        this.totalStats.numSourcePeriodic = 0; 
    } else {
      this.totalStats.numSourcePeriodic = paramParcel.readInt();
    } 
    if (i >= 5) {
      this.totalStats.numSourceFeed = paramParcel.readInt();
      this.totalStats.numFailures = paramParcel.readInt();
      this.totalStats.numCancels = paramParcel.readInt();
      this.lastTodayResetTime = paramParcel.readLong();
      this.todayStats.readFromParcel(paramParcel);
      this.yesterdayStats.readFromParcel(paramParcel);
    } 
    if (i >= 6) {
      paramParcel.readLongArray(this.perSourceLastSuccessTimes);
      paramParcel.readLongArray(this.perSourceLastFailureTimes);
    } 
  }
  
  private static boolean areSameDates(long paramLong1, long paramLong2) {
    GregorianCalendar gregorianCalendar1 = new GregorianCalendar();
    GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
    gregorianCalendar1.setTimeInMillis(paramLong1);
    gregorianCalendar2.setTimeInMillis(paramLong2);
    boolean bool = true;
    if (gregorianCalendar1.get(1) != gregorianCalendar2.get(1) || gregorianCalendar1.get(6) != gregorianCalendar2.get(6))
      bool = false; 
    return bool;
  }
  
  private static void copy(long[] paramArrayOflong1, long[] paramArrayOflong2) {
    System.arraycopy(paramArrayOflong2, 0, paramArrayOflong1, 0, paramArrayOflong1.length);
  }
  
  private void copyFrom(SyncStatusInfo paramSyncStatusInfo) {
    paramSyncStatusInfo.totalStats.copyTo(this.totalStats);
    paramSyncStatusInfo.todayStats.copyTo(this.todayStats);
    paramSyncStatusInfo.yesterdayStats.copyTo(this.yesterdayStats);
    this.lastTodayResetTime = paramSyncStatusInfo.lastTodayResetTime;
    this.lastSuccessTime = paramSyncStatusInfo.lastSuccessTime;
    this.lastSuccessSource = paramSyncStatusInfo.lastSuccessSource;
    this.lastFailureTime = paramSyncStatusInfo.lastFailureTime;
    this.lastFailureSource = paramSyncStatusInfo.lastFailureSource;
    this.lastFailureMesg = paramSyncStatusInfo.lastFailureMesg;
    this.initialFailureTime = paramSyncStatusInfo.initialFailureTime;
    this.pending = paramSyncStatusInfo.pending;
    this.initialize = paramSyncStatusInfo.initialize;
    if (paramSyncStatusInfo.periodicSyncTimes != null)
      this.periodicSyncTimes = new ArrayList<>(paramSyncStatusInfo.periodicSyncTimes); 
    this.mLastEventTimes.addAll(paramSyncStatusInfo.mLastEventTimes);
    this.mLastEvents.addAll(paramSyncStatusInfo.mLastEvents);
    copy(this.perSourceLastSuccessTimes, paramSyncStatusInfo.perSourceLastSuccessTimes);
    copy(this.perSourceLastFailureTimes, paramSyncStatusInfo.perSourceLastFailureTimes);
  }
  
  private void ensurePeriodicSyncTimeSize(int paramInt) {
    if (this.periodicSyncTimes == null)
      this.periodicSyncTimes = new ArrayList<>(0); 
    int i = paramInt + 1;
    if (this.periodicSyncTimes.size() < i)
      for (paramInt = this.periodicSyncTimes.size(); paramInt < i; paramInt++)
        this.periodicSyncTimes.add(Long.valueOf(0L));  
  }
  
  public void addEvent(String paramString) {
    if (this.mLastEventTimes.size() >= 10) {
      this.mLastEventTimes.remove(9);
      this.mLastEvents.remove(9);
    } 
    this.mLastEventTimes.add(0, Long.valueOf(System.currentTimeMillis()));
    this.mLastEvents.add(0, paramString);
  }
  
  public void addPeriodicSyncTime(long paramLong) {
    this.periodicSyncTimes = ArrayUtils.add(this.periodicSyncTimes, Long.valueOf(paramLong));
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getEvent(int paramInt) {
    return this.mLastEvents.get(paramInt);
  }
  
  public int getEventCount() {
    return this.mLastEventTimes.size();
  }
  
  public long getEventTime(int paramInt) {
    return ((Long)this.mLastEventTimes.get(paramInt)).longValue();
  }
  
  public int getLastFailureMesgAsInt(int paramInt) {
    int i = ContentResolver.syncErrorStringToInt(this.lastFailureMesg);
    if (i > 0)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown lastFailureMesg:");
    stringBuilder.append(this.lastFailureMesg);
    Log.d("Sync", stringBuilder.toString());
    return paramInt;
  }
  
  public long getPeriodicSyncTime(int paramInt) {
    ArrayList<Long> arrayList = this.periodicSyncTimes;
    return (arrayList != null && paramInt < arrayList.size()) ? ((Long)this.periodicSyncTimes.get(paramInt)).longValue() : 0L;
  }
  
  public int getPeriodicSyncTimesSize() {
    int i;
    ArrayList<Long> arrayList = this.periodicSyncTimes;
    if (arrayList == null) {
      i = 0;
    } else {
      i = arrayList.size();
    } 
    return i;
  }
  
  public void maybeResetTodayStats(boolean paramBoolean1, boolean paramBoolean2) {
    long l = System.currentTimeMillis();
    if (!paramBoolean2) {
      if (areSameDates(l, this.lastTodayResetTime))
        return; 
      if (l < this.lastTodayResetTime && !paramBoolean1)
        return; 
    } 
    this.lastTodayResetTime = l;
    this.todayStats.copyTo(this.yesterdayStats);
    this.todayStats.clear();
  }
  
  public void populateLastEventsInformation(ArrayList<Pair<Long, String>> paramArrayList) {
    this.mLastEventTimes.clear();
    this.mLastEvents.clear();
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++) {
      Pair pair = paramArrayList.get(b);
      this.mLastEventTimes.add((Long)pair.first);
      this.mLastEvents.add((String)pair.second);
    } 
  }
  
  public void removePeriodicSyncTime(int paramInt) {
    ArrayList<Long> arrayList = this.periodicSyncTimes;
    if (arrayList != null && paramInt < arrayList.size())
      this.periodicSyncTimes.remove(paramInt); 
  }
  
  public void setLastFailure(int paramInt, long paramLong, String paramString) {
    this.lastFailureTime = paramLong;
    this.lastFailureSource = paramInt;
    this.lastFailureMesg = paramString;
    if (this.initialFailureTime == 0L)
      this.initialFailureTime = paramLong; 
    if (paramInt >= 0) {
      long[] arrayOfLong = this.perSourceLastFailureTimes;
      if (paramInt < arrayOfLong.length)
        arrayOfLong[paramInt] = paramLong; 
    } 
  }
  
  public void setLastSuccess(int paramInt, long paramLong) {
    this.lastSuccessTime = paramLong;
    this.lastSuccessSource = paramInt;
    this.lastFailureTime = 0L;
    this.lastFailureSource = -1;
    this.lastFailureMesg = null;
    this.initialFailureTime = 0L;
    if (paramInt >= 0) {
      long[] arrayOfLong = this.perSourceLastSuccessTimes;
      if (paramInt < arrayOfLong.length)
        arrayOfLong[paramInt] = paramLong; 
    } 
  }
  
  public void setPeriodicSyncTime(int paramInt, long paramLong) {
    ensurePeriodicSyncTimeSize(paramInt);
    this.periodicSyncTimes.set(paramInt, Long.valueOf(paramLong));
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(6);
    paramParcel.writeInt(this.authorityId);
    paramParcel.writeLong(this.totalStats.totalElapsedTime);
    paramParcel.writeInt(this.totalStats.numSyncs);
    paramParcel.writeInt(this.totalStats.numSourcePoll);
    paramParcel.writeInt(this.totalStats.numSourceOther);
    paramParcel.writeInt(this.totalStats.numSourceLocal);
    paramParcel.writeInt(this.totalStats.numSourceUser);
    paramParcel.writeLong(this.lastSuccessTime);
    paramParcel.writeInt(this.lastSuccessSource);
    paramParcel.writeLong(this.lastFailureTime);
    paramParcel.writeInt(this.lastFailureSource);
    paramParcel.writeString(this.lastFailureMesg);
    paramParcel.writeLong(this.initialFailureTime);
    paramParcel.writeInt(this.pending);
    paramParcel.writeInt(this.initialize);
    ArrayList<Long> arrayList = this.periodicSyncTimes;
    if (arrayList != null) {
      paramParcel.writeInt(arrayList.size());
      Iterator<Long> iterator = this.periodicSyncTimes.iterator();
      while (iterator.hasNext())
        paramParcel.writeLong(((Long)iterator.next()).longValue()); 
    } else {
      paramParcel.writeInt(-1);
    } 
    paramParcel.writeInt(this.mLastEventTimes.size());
    for (paramInt = 0; paramInt < this.mLastEventTimes.size(); paramInt++) {
      paramParcel.writeLong(((Long)this.mLastEventTimes.get(paramInt)).longValue());
      paramParcel.writeString(this.mLastEvents.get(paramInt));
    } 
    paramParcel.writeInt(this.totalStats.numSourcePeriodic);
    paramParcel.writeInt(this.totalStats.numSourceFeed);
    paramParcel.writeInt(this.totalStats.numFailures);
    paramParcel.writeInt(this.totalStats.numCancels);
    paramParcel.writeLong(this.lastTodayResetTime);
    this.todayStats.writeToParcel(paramParcel);
    this.yesterdayStats.writeToParcel(paramParcel);
    paramParcel.writeLongArray(this.perSourceLastSuccessTimes);
    paramParcel.writeLongArray(this.perSourceLastFailureTimes);
  }
  
  public static class Stats {
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
    
    public void copyTo(Stats param1Stats) {
      param1Stats.totalElapsedTime = this.totalElapsedTime;
      param1Stats.numSyncs = this.numSyncs;
      param1Stats.numSourcePoll = this.numSourcePoll;
      param1Stats.numSourceOther = this.numSourceOther;
      param1Stats.numSourceLocal = this.numSourceLocal;
      param1Stats.numSourceUser = this.numSourceUser;
      param1Stats.numSourcePeriodic = this.numSourcePeriodic;
      param1Stats.numSourceFeed = this.numSourceFeed;
      param1Stats.numFailures = this.numFailures;
      param1Stats.numCancels = this.numCancels;
    }
    
    public void readFromParcel(Parcel param1Parcel) {
      this.totalElapsedTime = param1Parcel.readLong();
      this.numSyncs = param1Parcel.readInt();
      this.numSourcePoll = param1Parcel.readInt();
      this.numSourceOther = param1Parcel.readInt();
      this.numSourceLocal = param1Parcel.readInt();
      this.numSourceUser = param1Parcel.readInt();
      this.numSourcePeriodic = param1Parcel.readInt();
      this.numSourceFeed = param1Parcel.readInt();
      this.numFailures = param1Parcel.readInt();
      this.numCancels = param1Parcel.readInt();
    }
    
    public void writeToParcel(Parcel param1Parcel) {
      param1Parcel.writeLong(this.totalElapsedTime);
      param1Parcel.writeInt(this.numSyncs);
      param1Parcel.writeInt(this.numSourcePoll);
      param1Parcel.writeInt(this.numSourceOther);
      param1Parcel.writeInt(this.numSourceLocal);
      param1Parcel.writeInt(this.numSourceUser);
      param1Parcel.writeInt(this.numSourcePeriodic);
      param1Parcel.writeInt(this.numSourceFeed);
      param1Parcel.writeInt(this.numFailures);
      param1Parcel.writeInt(this.numCancels);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncStatusInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */