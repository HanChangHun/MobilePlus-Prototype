package android.app.usage;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseIntArray;

public final class UsageStats implements Parcelable {
  public static final Parcelable.Creator<UsageStats> CREATOR = new Parcelable.Creator<UsageStats>() {
      private void readBundleToEventMap(Bundle param1Bundle, ArrayMap<String, Integer> param1ArrayMap) {
        if (param1Bundle != null)
          for (String str : param1Bundle.keySet())
            param1ArrayMap.put(str, Integer.valueOf(param1Bundle.getInt(str)));  
      }
      
      private void readSparseIntArray(Parcel param1Parcel, SparseIntArray param1SparseIntArray) {
        int i = param1Parcel.readInt();
        for (byte b = 0; b < i; b++)
          param1SparseIntArray.put(param1Parcel.readInt(), param1Parcel.readInt()); 
      }
      
      public UsageStats createFromParcel(Parcel param1Parcel) {
        UsageStats usageStats = new UsageStats();
        usageStats.mPackageName = param1Parcel.readString();
        usageStats.mBeginTimeStamp = param1Parcel.readLong();
        usageStats.mEndTimeStamp = param1Parcel.readLong();
        usageStats.mLastTimeUsed = param1Parcel.readLong();
        usageStats.mLastTimeVisible = param1Parcel.readLong();
        usageStats.mLastTimeForegroundServiceUsed = param1Parcel.readLong();
        usageStats.mTotalTimeInForeground = param1Parcel.readLong();
        usageStats.mTotalTimeVisible = param1Parcel.readLong();
        usageStats.mTotalTimeForegroundServiceUsed = param1Parcel.readLong();
        usageStats.mLaunchCount = param1Parcel.readInt();
        usageStats.mAppLaunchCount = param1Parcel.readInt();
        usageStats.mLastEvent = param1Parcel.readInt();
        Bundle bundle = param1Parcel.readBundle();
        if (bundle != null) {
          usageStats.mChooserCounts = new ArrayMap();
          for (String str : bundle.keySet()) {
            if (!usageStats.mChooserCounts.containsKey(str)) {
              ArrayMap arrayMap = new ArrayMap();
              usageStats.mChooserCounts.put(str, arrayMap);
            } 
            Bundle bundle1 = bundle.getBundle(str);
            if (bundle1 != null)
              for (String str1 : bundle1.keySet()) {
                int i = bundle1.getInt(str1);
                if (i > 0)
                  ((ArrayMap)usageStats.mChooserCounts.get(str)).put(str1, Integer.valueOf(i)); 
              }  
          } 
        } 
        readSparseIntArray(param1Parcel, usageStats.mActivities);
        readBundleToEventMap(param1Parcel.readBundle(), usageStats.mForegroundServices);
        return usageStats;
      }
      
      public UsageStats[] newArray(int param1Int) {
        return new UsageStats[param1Int];
      }
    };
  
  public SparseIntArray mActivities = new SparseIntArray();
  
  public int mAppLaunchCount;
  
  public long mBeginTimeStamp;
  
  public ArrayMap<String, ArrayMap<String, Integer>> mChooserCounts = new ArrayMap();
  
  public SparseArray<SparseIntArray> mChooserCountsObfuscated = new SparseArray();
  
  public long mEndTimeStamp;
  
  public ArrayMap<String, Integer> mForegroundServices = new ArrayMap();
  
  @Deprecated
  public int mLastEvent;
  
  public long mLastTimeForegroundServiceUsed;
  
  public long mLastTimeUsed;
  
  public long mLastTimeVisible;
  
  public int mLaunchCount;
  
  public String mPackageName;
  
  public int mPackageToken = -1;
  
  public long mTotalTimeForegroundServiceUsed;
  
  public long mTotalTimeInForeground;
  
  public long mTotalTimeVisible;
  
  public UsageStats() {}
  
  public UsageStats(UsageStats paramUsageStats) {
    this.mPackageName = paramUsageStats.mPackageName;
    this.mBeginTimeStamp = paramUsageStats.mBeginTimeStamp;
    this.mEndTimeStamp = paramUsageStats.mEndTimeStamp;
    this.mLastTimeUsed = paramUsageStats.mLastTimeUsed;
    this.mLastTimeVisible = paramUsageStats.mLastTimeVisible;
    this.mLastTimeForegroundServiceUsed = paramUsageStats.mLastTimeForegroundServiceUsed;
    this.mTotalTimeInForeground = paramUsageStats.mTotalTimeInForeground;
    this.mTotalTimeVisible = paramUsageStats.mTotalTimeVisible;
    this.mTotalTimeForegroundServiceUsed = paramUsageStats.mTotalTimeForegroundServiceUsed;
    this.mLaunchCount = paramUsageStats.mLaunchCount;
    this.mAppLaunchCount = paramUsageStats.mAppLaunchCount;
    this.mLastEvent = paramUsageStats.mLastEvent;
    this.mActivities = paramUsageStats.mActivities;
    this.mForegroundServices = paramUsageStats.mForegroundServices;
    this.mChooserCounts = paramUsageStats.mChooserCounts;
  }
  
  private boolean anyForegroundServiceStarted() {
    return this.mForegroundServices.isEmpty() ^ true;
  }
  
  private Bundle eventMapToBundle(ArrayMap<String, Integer> paramArrayMap) {
    Bundle bundle = new Bundle();
    int i = paramArrayMap.size();
    for (byte b = 0; b < i; b++)
      bundle.putInt((String)paramArrayMap.keyAt(b), ((Integer)paramArrayMap.valueAt(b)).intValue()); 
    return bundle;
  }
  
  private boolean hasForegroundActivity() {
    int i = this.mActivities.size();
    for (byte b = 0; b < i; b++) {
      if (this.mActivities.valueAt(b) == 1)
        return true; 
    } 
    return false;
  }
  
  private boolean hasVisibleActivity() {
    int i = this.mActivities.size();
    for (byte b = 0; b < i; b++) {
      int j = this.mActivities.valueAt(b);
      if (j == 1 || j == 2)
        return true; 
    } 
    return false;
  }
  
  private void incrementServiceTimeUsed(long paramLong) {
    long l = this.mLastTimeForegroundServiceUsed;
    if (paramLong > l) {
      this.mTotalTimeForegroundServiceUsed += paramLong - l;
      this.mLastTimeForegroundServiceUsed = paramLong;
    } 
  }
  
  private void incrementTimeUsed(long paramLong) {
    long l = this.mLastTimeUsed;
    if (paramLong > l) {
      this.mTotalTimeInForeground += paramLong - l;
      this.mLastTimeUsed = paramLong;
    } 
  }
  
  private void incrementTimeVisible(long paramLong) {
    long l = this.mLastTimeVisible;
    if (paramLong > l) {
      this.mTotalTimeVisible += paramLong - l;
      this.mLastTimeVisible = paramLong;
    } 
  }
  
  private void mergeEventMap(ArrayMap<String, Integer> paramArrayMap1, ArrayMap<String, Integer> paramArrayMap2) {
    int i = paramArrayMap2.size();
    for (byte b = 0; b < i; b++) {
      String str = (String)paramArrayMap2.keyAt(b);
      Integer integer = (Integer)paramArrayMap2.valueAt(b);
      if (paramArrayMap1.containsKey(str)) {
        paramArrayMap1.put(str, Integer.valueOf(Math.max(((Integer)paramArrayMap1.get(str)).intValue(), integer.intValue())));
      } else {
        paramArrayMap1.put(str, integer);
      } 
    } 
  }
  
  private void mergeEventMap(SparseIntArray paramSparseIntArray1, SparseIntArray paramSparseIntArray2) {
    int i = paramSparseIntArray2.size();
    for (byte b = 0; b < i; b++) {
      int j = paramSparseIntArray2.keyAt(b);
      int k = paramSparseIntArray2.valueAt(b);
      int m = paramSparseIntArray1.indexOfKey(j);
      if (m >= 0) {
        paramSparseIntArray1.put(j, Math.max(paramSparseIntArray1.valueAt(m), k));
      } else {
        paramSparseIntArray1.put(j, k);
      } 
    } 
  }
  
  private void updateActivity(String paramString, long paramLong, int paramInt1, int paramInt2) {
    if (paramInt1 != 1 && paramInt1 != 2 && paramInt1 != 23 && paramInt1 != 24)
      return; 
    int i = this.mActivities.indexOfKey(paramInt2);
    if (i >= 0) {
      i = this.mActivities.valueAt(i);
      if (i != 1) {
        if (i == 2)
          incrementTimeVisible(paramLong); 
      } else {
        incrementTimeUsed(paramLong);
        incrementTimeVisible(paramLong);
      } 
    } 
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 == 23 || paramInt1 == 24)
          this.mActivities.delete(paramInt2); 
      } else {
        if (!hasVisibleActivity())
          this.mLastTimeVisible = paramLong; 
        this.mActivities.put(paramInt2, paramInt1);
      } 
    } else {
      if (!hasVisibleActivity()) {
        this.mLastTimeUsed = paramLong;
        this.mLastTimeVisible = paramLong;
      } else if (!hasForegroundActivity()) {
        this.mLastTimeUsed = paramLong;
      } 
      this.mActivities.put(paramInt2, paramInt1);
    } 
  }
  
  private void updateForegroundService(String paramString, long paramLong, int paramInt) {
    if (paramInt != 20 && paramInt != 19)
      return; 
    Integer integer = (Integer)this.mForegroundServices.get(paramString);
    if (integer != null) {
      int i = integer.intValue();
      if (i == 19 || i == 21)
        incrementServiceTimeUsed(paramLong); 
    } 
    if (paramInt != 19) {
      if (paramInt == 20)
        this.mForegroundServices.remove(paramString); 
    } else {
      if (!anyForegroundServiceStarted())
        this.mLastTimeForegroundServiceUsed = paramLong; 
      this.mForegroundServices.put(paramString, Integer.valueOf(paramInt));
    } 
  }
  
  private void writeSparseIntArray(Parcel paramParcel, SparseIntArray paramSparseIntArray) {
    int i = paramSparseIntArray.size();
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++) {
      paramParcel.writeInt(paramSparseIntArray.keyAt(b));
      paramParcel.writeInt(paramSparseIntArray.valueAt(b));
    } 
  }
  
  public void add(UsageStats paramUsageStats) {
    if (this.mPackageName.equals(paramUsageStats.mPackageName)) {
      if (paramUsageStats.mBeginTimeStamp > this.mBeginTimeStamp) {
        mergeEventMap(this.mActivities, paramUsageStats.mActivities);
        mergeEventMap(this.mForegroundServices, paramUsageStats.mForegroundServices);
        this.mLastTimeUsed = Math.max(this.mLastTimeUsed, paramUsageStats.mLastTimeUsed);
        this.mLastTimeVisible = Math.max(this.mLastTimeVisible, paramUsageStats.mLastTimeVisible);
        this.mLastTimeForegroundServiceUsed = Math.max(this.mLastTimeForegroundServiceUsed, paramUsageStats.mLastTimeForegroundServiceUsed);
      } 
      this.mBeginTimeStamp = Math.min(this.mBeginTimeStamp, paramUsageStats.mBeginTimeStamp);
      this.mEndTimeStamp = Math.max(this.mEndTimeStamp, paramUsageStats.mEndTimeStamp);
      this.mTotalTimeInForeground += paramUsageStats.mTotalTimeInForeground;
      this.mTotalTimeVisible += paramUsageStats.mTotalTimeVisible;
      this.mTotalTimeForegroundServiceUsed += paramUsageStats.mTotalTimeForegroundServiceUsed;
      this.mLaunchCount += paramUsageStats.mLaunchCount;
      this.mAppLaunchCount += paramUsageStats.mAppLaunchCount;
      if (this.mChooserCounts == null) {
        this.mChooserCounts = paramUsageStats.mChooserCounts;
      } else {
        ArrayMap<String, ArrayMap<String, Integer>> arrayMap = paramUsageStats.mChooserCounts;
        if (arrayMap != null) {
          int i = arrayMap.size();
          for (byte b = 0; b < i; b++) {
            String str = (String)paramUsageStats.mChooserCounts.keyAt(b);
            ArrayMap arrayMap1 = (ArrayMap)paramUsageStats.mChooserCounts.valueAt(b);
            if (!this.mChooserCounts.containsKey(str) || this.mChooserCounts.get(str) == null) {
              this.mChooserCounts.put(str, arrayMap1);
            } else {
              int j = arrayMap1.size();
              for (byte b1 = 0; b1 < j; b1++) {
                String str1 = (String)arrayMap1.keyAt(b1);
                int k = ((Integer)arrayMap1.valueAt(b1)).intValue();
                int m = ((Integer)((ArrayMap)this.mChooserCounts.get(str)).getOrDefault(str1, Integer.valueOf(0))).intValue();
                ((ArrayMap)this.mChooserCounts.get(str)).put(str1, Integer.valueOf(m + k));
              } 
            } 
          } 
        } 
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't merge UsageStats for package '");
    stringBuilder.append(this.mPackageName);
    stringBuilder.append("' with UsageStats for package '");
    stringBuilder.append(paramUsageStats.mPackageName);
    stringBuilder.append("'.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int describeContents() {
    return 0;
  }
  
  @SystemApi
  public int getAppLaunchCount() {
    return this.mAppLaunchCount;
  }
  
  public long getFirstTimeStamp() {
    return this.mBeginTimeStamp;
  }
  
  public long getLastTimeForegroundServiceUsed() {
    return this.mLastTimeForegroundServiceUsed;
  }
  
  public long getLastTimeStamp() {
    return this.mEndTimeStamp;
  }
  
  public long getLastTimeUsed() {
    return this.mLastTimeUsed;
  }
  
  public long getLastTimeVisible() {
    return this.mLastTimeVisible;
  }
  
  public UsageStats getObfuscatedForInstantApp() {
    UsageStats usageStats = new UsageStats(this);
    usageStats.mPackageName = "android.instant_app";
    return usageStats;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public long getTotalTimeForegroundServiceUsed() {
    return this.mTotalTimeForegroundServiceUsed;
  }
  
  public long getTotalTimeInForeground() {
    return this.mTotalTimeInForeground;
  }
  
  public long getTotalTimeVisible() {
    return this.mTotalTimeVisible;
  }
  
  public void update(String paramString, long paramLong, int paramInt1, int paramInt2) {
    if (paramInt1 != 1 && paramInt1 != 2) {
      if (paramInt1 != 3) {
        if (paramInt1 != 7) {
          switch (paramInt1) {
            case 25:
            case 26:
              if (hasForegroundActivity())
                incrementTimeUsed(paramLong); 
              if (hasVisibleActivity())
                incrementTimeVisible(paramLong); 
              if (anyForegroundServiceStarted())
                incrementServiceTimeUsed(paramLong); 
              break;
            case 22:
              if (anyForegroundServiceStarted())
                incrementServiceTimeUsed(paramLong); 
              break;
            case 21:
              this.mLastTimeForegroundServiceUsed = paramLong;
              this.mForegroundServices.put(paramString, Integer.valueOf(paramInt1));
              break;
            case 19:
            case 20:
              updateForegroundService(paramString, paramLong, paramInt1);
              break;
            case 23:
            case 24:
              updateActivity(paramString, paramLong, paramInt1, paramInt2);
              break;
          } 
        } else {
          if (hasForegroundActivity()) {
            incrementTimeUsed(paramLong);
          } else {
            this.mLastTimeUsed = paramLong;
          } 
          if (hasVisibleActivity()) {
            incrementTimeVisible(paramLong);
          } else {
            this.mLastTimeVisible = paramLong;
          } 
        } 
      } else {
        if (hasForegroundActivity())
          incrementTimeUsed(paramLong); 
        if (hasVisibleActivity())
          incrementTimeVisible(paramLong); 
      } 
      this.mEndTimeStamp = paramLong;
      if (paramInt1 == 1)
        this.mLaunchCount++; 
      return;
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeLong(this.mBeginTimeStamp);
    paramParcel.writeLong(this.mEndTimeStamp);
    paramParcel.writeLong(this.mLastTimeUsed);
    paramParcel.writeLong(this.mLastTimeVisible);
    paramParcel.writeLong(this.mLastTimeForegroundServiceUsed);
    paramParcel.writeLong(this.mTotalTimeInForeground);
    paramParcel.writeLong(this.mTotalTimeVisible);
    paramParcel.writeLong(this.mTotalTimeForegroundServiceUsed);
    paramParcel.writeInt(this.mLaunchCount);
    paramParcel.writeInt(this.mAppLaunchCount);
    paramParcel.writeInt(this.mLastEvent);
    Bundle bundle = new Bundle();
    ArrayMap<String, ArrayMap<String, Integer>> arrayMap = this.mChooserCounts;
    if (arrayMap != null) {
      int i = arrayMap.size();
      for (paramInt = 0; paramInt < i; paramInt++) {
        String str = (String)this.mChooserCounts.keyAt(paramInt);
        arrayMap = (ArrayMap<String, ArrayMap<String, Integer>>)this.mChooserCounts.valueAt(paramInt);
        Bundle bundle1 = new Bundle();
        int j = arrayMap.size();
        for (byte b = 0; b < j; b++)
          bundle1.putInt((String)arrayMap.keyAt(b), ((Integer)arrayMap.valueAt(b)).intValue()); 
        bundle.putBundle(str, bundle1);
      } 
    } 
    paramParcel.writeBundle(bundle);
    writeSparseIntArray(paramParcel, this.mActivities);
    paramParcel.writeBundle(eventMapToBundle(this.mForegroundServices));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/UsageStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */