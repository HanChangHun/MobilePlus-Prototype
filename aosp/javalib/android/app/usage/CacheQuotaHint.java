package android.app.usage;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.Objects;

@SystemApi
public final class CacheQuotaHint implements Parcelable {
  public static final Parcelable.Creator<CacheQuotaHint> CREATOR = new Parcelable.Creator<CacheQuotaHint>() {
      public CacheQuotaHint createFromParcel(Parcel param1Parcel) {
        return (new CacheQuotaHint.Builder()).setVolumeUuid(param1Parcel.readString()).setUid(param1Parcel.readInt()).setQuota(param1Parcel.readLong()).setUsageStats((UsageStats)param1Parcel.readParcelable(UsageStats.class.getClassLoader())).build();
      }
      
      public CacheQuotaHint[] newArray(int param1Int) {
        return new CacheQuotaHint[param1Int];
      }
    };
  
  public static final long QUOTA_NOT_SET = -1L;
  
  private final long mQuota;
  
  private final int mUid;
  
  private final UsageStats mUsageStats;
  
  private final String mUuid;
  
  public CacheQuotaHint(Builder paramBuilder) {
    this.mUuid = paramBuilder.mUuid;
    this.mUid = paramBuilder.mUid;
    this.mUsageStats = paramBuilder.mUsageStats;
    this.mQuota = paramBuilder.mQuota;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof CacheQuotaHint;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      if (Objects.equals(this.mUuid, ((CacheQuotaHint)paramObject).mUuid) && Objects.equals(this.mUsageStats, ((CacheQuotaHint)paramObject).mUsageStats) && this.mUid == ((CacheQuotaHint)paramObject).mUid && this.mQuota == ((CacheQuotaHint)paramObject).mQuota)
        bool1 = true; 
      return bool1;
    } 
    return false;
  }
  
  public long getQuota() {
    return this.mQuota;
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  public UsageStats getUsageStats() {
    return this.mUsageStats;
  }
  
  public String getVolumeUuid() {
    return this.mUuid;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mUuid, Integer.valueOf(this.mUid), this.mUsageStats, Long.valueOf(this.mQuota) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mUuid);
    paramParcel.writeInt(this.mUid);
    paramParcel.writeLong(this.mQuota);
    paramParcel.writeParcelable(this.mUsageStats, 0);
  }
  
  public static final class Builder {
    private long mQuota;
    
    private int mUid;
    
    private UsageStats mUsageStats;
    
    private String mUuid;
    
    public Builder() {}
    
    public Builder(CacheQuotaHint param1CacheQuotaHint) {
      setVolumeUuid(param1CacheQuotaHint.getVolumeUuid());
      setUid(param1CacheQuotaHint.getUid());
      setUsageStats(param1CacheQuotaHint.getUsageStats());
      setQuota(param1CacheQuotaHint.getQuota());
    }
    
    public CacheQuotaHint build() {
      return new CacheQuotaHint(this);
    }
    
    public Builder setQuota(long param1Long) {
      boolean bool;
      if (param1Long >= -1L) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool);
      this.mQuota = param1Long;
      return this;
    }
    
    public Builder setUid(int param1Int) {
      Preconditions.checkArgumentNonnegative(param1Int, "Proposed uid was negative.");
      this.mUid = param1Int;
      return this;
    }
    
    public Builder setUsageStats(UsageStats param1UsageStats) {
      this.mUsageStats = param1UsageStats;
      return this;
    }
    
    public Builder setVolumeUuid(String param1String) {
      this.mUuid = param1String;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/CacheQuotaHint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */