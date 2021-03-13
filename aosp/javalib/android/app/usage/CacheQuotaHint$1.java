package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CacheQuotaHint> {
  public CacheQuotaHint createFromParcel(Parcel paramParcel) {
    return (new CacheQuotaHint.Builder()).setVolumeUuid(paramParcel.readString()).setUid(paramParcel.readInt()).setQuota(paramParcel.readLong()).setUsageStats((UsageStats)paramParcel.readParcelable(UsageStats.class.getClassLoader())).build();
  }
  
  public CacheQuotaHint[] newArray(int paramInt) {
    return new CacheQuotaHint[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/CacheQuotaHint$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */