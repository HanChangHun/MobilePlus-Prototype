package android.app.usage;

import com.android.internal.util.Preconditions;

public final class Builder {
  private long mQuota;
  
  private int mUid;
  
  private UsageStats mUsageStats;
  
  private String mUuid;
  
  public Builder() {}
  
  public Builder(CacheQuotaHint paramCacheQuotaHint) {
    setVolumeUuid(paramCacheQuotaHint.getVolumeUuid());
    setUid(paramCacheQuotaHint.getUid());
    setUsageStats(paramCacheQuotaHint.getUsageStats());
    setQuota(paramCacheQuotaHint.getQuota());
  }
  
  public CacheQuotaHint build() {
    return new CacheQuotaHint(this);
  }
  
  public Builder setQuota(long paramLong) {
    boolean bool;
    if (paramLong >= -1L) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    this.mQuota = paramLong;
    return this;
  }
  
  public Builder setUid(int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt, "Proposed uid was negative.");
    this.mUid = paramInt;
    return this;
  }
  
  public Builder setUsageStats(UsageStats paramUsageStats) {
    this.mUsageStats = paramUsageStats;
    return this;
  }
  
  public Builder setVolumeUuid(String paramString) {
    this.mUuid = paramString;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/CacheQuotaHint$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */