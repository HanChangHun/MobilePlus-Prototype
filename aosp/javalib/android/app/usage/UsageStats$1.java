package android.app.usage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseIntArray;

class null implements Parcelable.Creator<UsageStats> {
  private void readBundleToEventMap(Bundle paramBundle, ArrayMap<String, Integer> paramArrayMap) {
    if (paramBundle != null)
      for (String str : paramBundle.keySet())
        paramArrayMap.put(str, Integer.valueOf(paramBundle.getInt(str)));  
  }
  
  private void readSparseIntArray(Parcel paramParcel, SparseIntArray paramSparseIntArray) {
    int i = paramParcel.readInt();
    for (byte b = 0; b < i; b++)
      paramSparseIntArray.put(paramParcel.readInt(), paramParcel.readInt()); 
  }
  
  public UsageStats createFromParcel(Parcel paramParcel) {
    UsageStats usageStats = new UsageStats();
    usageStats.mPackageName = paramParcel.readString();
    usageStats.mBeginTimeStamp = paramParcel.readLong();
    usageStats.mEndTimeStamp = paramParcel.readLong();
    usageStats.mLastTimeUsed = paramParcel.readLong();
    usageStats.mLastTimeVisible = paramParcel.readLong();
    usageStats.mLastTimeForegroundServiceUsed = paramParcel.readLong();
    usageStats.mTotalTimeInForeground = paramParcel.readLong();
    usageStats.mTotalTimeVisible = paramParcel.readLong();
    usageStats.mTotalTimeForegroundServiceUsed = paramParcel.readLong();
    usageStats.mLaunchCount = paramParcel.readInt();
    usageStats.mAppLaunchCount = paramParcel.readInt();
    usageStats.mLastEvent = paramParcel.readInt();
    Bundle bundle = paramParcel.readBundle();
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
    readSparseIntArray(paramParcel, usageStats.mActivities);
    readBundleToEventMap(paramParcel.readBundle(), usageStats.mForegroundServices);
    return usageStats;
  }
  
  public UsageStats[] newArray(int paramInt) {
    return new UsageStats[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/UsageStats$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */