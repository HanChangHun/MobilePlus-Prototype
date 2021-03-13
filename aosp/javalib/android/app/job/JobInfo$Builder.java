package android.app.job;

import android.content.ClipData;
import android.content.ComponentName;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.TimeUtils;
import java.util.ArrayList;

public final class Builder {
  private int mBackoffPolicy = 1;
  
  private boolean mBackoffPolicySet = false;
  
  private ClipData mClipData;
  
  private int mClipGrantFlags;
  
  private int mConstraintFlags;
  
  private PersistableBundle mExtras = PersistableBundle.EMPTY;
  
  private int mFlags;
  
  private long mFlexMillis;
  
  private boolean mHasEarlyConstraint;
  
  private boolean mHasLateConstraint;
  
  private long mInitialBackoffMillis = 30000L;
  
  private long mIntervalMillis;
  
  private boolean mIsPeriodic;
  
  private boolean mIsPersisted;
  
  private final int mJobId;
  
  private final ComponentName mJobService;
  
  private long mMaxExecutionDelayMillis;
  
  private long mMinLatencyMillis;
  
  private long mNetworkDownloadBytes = -1L;
  
  private NetworkRequest mNetworkRequest;
  
  private long mNetworkUploadBytes = -1L;
  
  private int mPriority = 0;
  
  private Bundle mTransientExtras = Bundle.EMPTY;
  
  private long mTriggerContentMaxDelay = -1L;
  
  private long mTriggerContentUpdateDelay = -1L;
  
  private ArrayList<JobInfo.TriggerContentUri> mTriggerContentUris;
  
  public Builder(int paramInt, ComponentName paramComponentName) {
    this.mJobService = paramComponentName;
    this.mJobId = paramInt;
  }
  
  public Builder addTriggerContentUri(JobInfo.TriggerContentUri paramTriggerContentUri) {
    if (this.mTriggerContentUris == null)
      this.mTriggerContentUris = new ArrayList<>(); 
    this.mTriggerContentUris.add(paramTriggerContentUri);
    return this;
  }
  
  public JobInfo build() {
    if ((this.mNetworkDownloadBytes <= 0L && this.mNetworkUploadBytes <= 0L) || this.mNetworkRequest != null) {
      if (this.mIsPersisted) {
        NetworkRequest networkRequest = this.mNetworkRequest;
        if (networkRequest != null && networkRequest.networkCapabilities.getNetworkSpecifier() != null)
          throw new IllegalArgumentException("Network specifiers aren't supported for persistent jobs"); 
      } 
      if (this.mIsPeriodic)
        if (this.mMaxExecutionDelayMillis == 0L) {
          if (this.mMinLatencyMillis == 0L) {
            if (this.mTriggerContentUris != null)
              throw new IllegalArgumentException("Can't call addTriggerContentUri() on a periodic job"); 
          } else {
            throw new IllegalArgumentException("Can't call setMinimumLatency() on a periodic job");
          } 
        } else {
          throw new IllegalArgumentException("Can't call setOverrideDeadline() on a periodic job.");
        }  
      if (this.mIsPersisted)
        if (this.mTriggerContentUris == null) {
          if (this.mTransientExtras.isEmpty()) {
            if (this.mClipData != null)
              throw new IllegalArgumentException("Can't call setClipData() on a persisted job"); 
          } else {
            throw new IllegalArgumentException("Can't call setTransientExtras() on a persisted job");
          } 
        } else {
          throw new IllegalArgumentException("Can't call addTriggerContentUri() on a persisted job");
        }  
      if ((this.mFlags & 0x2) == 0 || !this.mHasEarlyConstraint) {
        if (!this.mBackoffPolicySet || (this.mConstraintFlags & 0x4) == 0)
          return new JobInfo(this, null); 
        throw new IllegalArgumentException("An idle mode job will not respect any back-off policy, so calling setBackoffCriteria with setRequiresDeviceIdle is an error.");
      } 
      throw new IllegalArgumentException("An important while foreground job cannot have a time delay");
    } 
    throw new IllegalArgumentException("Can't provide estimated network usage without requiring a network");
  }
  
  public Builder setBackoffCriteria(long paramLong, int paramInt) {
    long l1 = JobInfo.getMinBackoffMillis();
    long l2 = paramLong;
    if (paramLong < l1) {
      String str = JobInfo.access$2700();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested backoff ");
      stringBuilder.append(TimeUtils.formatDuration(paramLong));
      stringBuilder.append(" for job ");
      stringBuilder.append(this.mJobId);
      stringBuilder.append(" is too small; raising to ");
      stringBuilder.append(TimeUtils.formatDuration(l1));
      Log.w(str, stringBuilder.toString());
      l2 = l1;
    } 
    this.mBackoffPolicySet = true;
    this.mInitialBackoffMillis = l2;
    this.mBackoffPolicy = paramInt;
    return this;
  }
  
  public Builder setClipData(ClipData paramClipData, int paramInt) {
    this.mClipData = paramClipData;
    this.mClipGrantFlags = paramInt;
    return this;
  }
  
  public Builder setEstimatedNetworkBytes(long paramLong1, long paramLong2) {
    this.mNetworkDownloadBytes = paramLong1;
    this.mNetworkUploadBytes = paramLong2;
    return this;
  }
  
  public Builder setExtras(PersistableBundle paramPersistableBundle) {
    this.mExtras = paramPersistableBundle;
    return this;
  }
  
  public Builder setFlags(int paramInt) {
    this.mFlags = paramInt;
    return this;
  }
  
  public Builder setImportantWhileForeground(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x2;
    } else {
      this.mFlags &= 0xFFFFFFFD;
    } 
    return this;
  }
  
  public Builder setMinimumLatency(long paramLong) {
    this.mMinLatencyMillis = paramLong;
    this.mHasEarlyConstraint = true;
    return this;
  }
  
  public Builder setOverrideDeadline(long paramLong) {
    this.mMaxExecutionDelayMillis = paramLong;
    this.mHasLateConstraint = true;
    return this;
  }
  
  public Builder setPeriodic(long paramLong) {
    return setPeriodic(paramLong, paramLong);
  }
  
  public Builder setPeriodic(long paramLong1, long paramLong2) {
    long l1 = JobInfo.getMinPeriodMillis();
    long l2 = paramLong1;
    if (paramLong1 < l1) {
      String str = JobInfo.access$2700();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested interval ");
      stringBuilder.append(TimeUtils.formatDuration(paramLong1));
      stringBuilder.append(" for job ");
      stringBuilder.append(this.mJobId);
      stringBuilder.append(" is too small; raising to ");
      stringBuilder.append(TimeUtils.formatDuration(l1));
      Log.w(str, stringBuilder.toString());
      l2 = l1;
    } 
    l1 = Math.max(5L * l2 / 100L, JobInfo.getMinFlexMillis());
    paramLong1 = paramLong2;
    if (paramLong2 < l1) {
      String str = JobInfo.access$2700();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested flex ");
      stringBuilder.append(TimeUtils.formatDuration(paramLong2));
      stringBuilder.append(" for job ");
      stringBuilder.append(this.mJobId);
      stringBuilder.append(" is too small; raising to ");
      stringBuilder.append(TimeUtils.formatDuration(l1));
      Log.w(str, stringBuilder.toString());
      paramLong1 = l1;
    } 
    this.mIsPeriodic = true;
    this.mIntervalMillis = l2;
    this.mFlexMillis = paramLong1;
    this.mHasLateConstraint = true;
    this.mHasEarlyConstraint = true;
    return this;
  }
  
  public Builder setPersisted(boolean paramBoolean) {
    this.mIsPersisted = paramBoolean;
    return this;
  }
  
  public Builder setPrefetch(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x4;
    } else {
      this.mFlags &= 0xFFFFFFFB;
    } 
    return this;
  }
  
  public Builder setPriority(int paramInt) {
    this.mPriority = paramInt;
    return this;
  }
  
  public Builder setRequiredNetwork(NetworkRequest paramNetworkRequest) {
    this.mNetworkRequest = paramNetworkRequest;
    return this;
  }
  
  public Builder setRequiredNetworkType(int paramInt) {
    if (paramInt == 0)
      return setRequiredNetwork(null); 
    NetworkRequest.Builder builder = new NetworkRequest.Builder();
    builder.addCapability(12);
    builder.addCapability(16);
    builder.removeCapability(15);
    if (paramInt != 1)
      if (paramInt == 2) {
        builder.addCapability(11);
      } else if (paramInt == 3) {
        builder.addCapability(18);
      } else if (paramInt == 4) {
        builder.addTransportType(0);
      }  
    return setRequiredNetwork(builder.build());
  }
  
  public Builder setRequiresBatteryNotLow(boolean paramBoolean) {
    boolean bool;
    int i = this.mConstraintFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mConstraintFlags = i & 0xFFFFFFFD | bool;
    return this;
  }
  
  public Builder setRequiresCharging(boolean paramBoolean) {
    this.mConstraintFlags = this.mConstraintFlags & 0xFFFFFFFE | paramBoolean;
    return this;
  }
  
  public Builder setRequiresDeviceIdle(boolean paramBoolean) {
    boolean bool;
    int i = this.mConstraintFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mConstraintFlags = i & 0xFFFFFFFB | bool;
    return this;
  }
  
  public Builder setRequiresStorageNotLow(boolean paramBoolean) {
    boolean bool;
    int i = this.mConstraintFlags;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mConstraintFlags = i & 0xFFFFFFF7 | bool;
    return this;
  }
  
  public Builder setTransientExtras(Bundle paramBundle) {
    this.mTransientExtras = paramBundle;
    return this;
  }
  
  public Builder setTriggerContentMaxDelay(long paramLong) {
    this.mTriggerContentMaxDelay = paramLong;
    return this;
  }
  
  public Builder setTriggerContentUpdateDelay(long paramLong) {
    this.mTriggerContentUpdateDelay = paramLong;
    return this;
  }
  
  public String summarize() {
    String str;
    ComponentName componentName = this.mJobService;
    if (componentName != null) {
      str = componentName.flattenToShortString();
    } else {
      str = "null";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("JobInfo.Builder{job:");
    stringBuilder.append(this.mJobId);
    stringBuilder.append("/");
    stringBuilder.append(str);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobInfo$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */