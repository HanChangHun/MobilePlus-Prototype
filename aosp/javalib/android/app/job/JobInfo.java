package android.app.job;

import android.content.ClipData;
import android.content.ComponentName;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class JobInfo implements Parcelable {
  public static final int BACKOFF_POLICY_EXPONENTIAL = 1;
  
  public static final int BACKOFF_POLICY_LINEAR = 0;
  
  public static final int CONSTRAINT_FLAG_BATTERY_NOT_LOW = 2;
  
  public static final int CONSTRAINT_FLAG_CHARGING = 1;
  
  public static final int CONSTRAINT_FLAG_DEVICE_IDLE = 4;
  
  public static final int CONSTRAINT_FLAG_STORAGE_NOT_LOW = 8;
  
  public static final Parcelable.Creator<JobInfo> CREATOR;
  
  public static final int DEFAULT_BACKOFF_POLICY = 1;
  
  public static final long DEFAULT_INITIAL_BACKOFF_MILLIS = 30000L;
  
  public static final int FLAG_EXEMPT_FROM_APP_STANDBY = 8;
  
  public static final int FLAG_IMPORTANT_WHILE_FOREGROUND = 2;
  
  public static final int FLAG_PREFETCH = 4;
  
  public static final int FLAG_WILL_BE_FOREGROUND = 1;
  
  public static final long MAX_BACKOFF_DELAY_MILLIS = 18000000L;
  
  public static final long MIN_BACKOFF_MILLIS = 10000L;
  
  private static final long MIN_FLEX_MILLIS = 300000L;
  
  private static final long MIN_PERIOD_MILLIS = 900000L;
  
  public static final int NETWORK_BYTES_UNKNOWN = -1;
  
  public static final int NETWORK_TYPE_ANY = 1;
  
  public static final int NETWORK_TYPE_CELLULAR = 4;
  
  @Deprecated
  public static final int NETWORK_TYPE_METERED = 4;
  
  public static final int NETWORK_TYPE_NONE = 0;
  
  public static final int NETWORK_TYPE_NOT_ROAMING = 3;
  
  public static final int NETWORK_TYPE_UNMETERED = 2;
  
  public static final int PRIORITY_ADJ_ALWAYS_RUNNING = -80;
  
  public static final int PRIORITY_ADJ_OFTEN_RUNNING = -40;
  
  public static final int PRIORITY_BOUND_FOREGROUND_SERVICE = 30;
  
  public static final int PRIORITY_DEFAULT = 0;
  
  public static final int PRIORITY_FOREGROUND_APP = 30;
  
  public static final int PRIORITY_FOREGROUND_SERVICE = 35;
  
  public static final int PRIORITY_SYNC_EXPEDITED = 10;
  
  public static final int PRIORITY_SYNC_INITIALIZATION = 20;
  
  public static final int PRIORITY_TOP_APP = 40;
  
  private static String TAG = "JobInfo";
  
  private final int backoffPolicy;
  
  private final ClipData clipData;
  
  private final int clipGrantFlags;
  
  private final int constraintFlags;
  
  private final PersistableBundle extras;
  
  private final int flags;
  
  private final long flexMillis;
  
  private final boolean hasEarlyConstraint;
  
  private final boolean hasLateConstraint;
  
  private final long initialBackoffMillis;
  
  private final long intervalMillis;
  
  private final boolean isPeriodic;
  
  private final boolean isPersisted;
  
  private final int jobId;
  
  private final long maxExecutionDelayMillis;
  
  private final long minLatencyMillis;
  
  private final long networkDownloadBytes;
  
  private final NetworkRequest networkRequest;
  
  private final long networkUploadBytes;
  
  private final int priority;
  
  private final ComponentName service;
  
  private final Bundle transientExtras;
  
  private final long triggerContentMaxDelay;
  
  private final long triggerContentUpdateDelay;
  
  private final TriggerContentUri[] triggerContentUris;
  
  static {
    CREATOR = new Parcelable.Creator<JobInfo>() {
        public JobInfo createFromParcel(Parcel param1Parcel) {
          return new JobInfo(param1Parcel);
        }
        
        public JobInfo[] newArray(int param1Int) {
          return new JobInfo[param1Int];
        }
      };
  }
  
  private JobInfo(Builder paramBuilder) {
    TriggerContentUri[] arrayOfTriggerContentUri;
    this.jobId = paramBuilder.mJobId;
    this.extras = paramBuilder.mExtras.deepCopy();
    this.transientExtras = paramBuilder.mTransientExtras.deepCopy();
    this.clipData = paramBuilder.mClipData;
    this.clipGrantFlags = paramBuilder.mClipGrantFlags;
    this.service = paramBuilder.mJobService;
    this.constraintFlags = paramBuilder.mConstraintFlags;
    if (paramBuilder.mTriggerContentUris != null) {
      arrayOfTriggerContentUri = (TriggerContentUri[])paramBuilder.mTriggerContentUris.toArray((Object[])new TriggerContentUri[paramBuilder.mTriggerContentUris.size()]);
    } else {
      arrayOfTriggerContentUri = null;
    } 
    this.triggerContentUris = arrayOfTriggerContentUri;
    this.triggerContentUpdateDelay = paramBuilder.mTriggerContentUpdateDelay;
    this.triggerContentMaxDelay = paramBuilder.mTriggerContentMaxDelay;
    this.networkRequest = paramBuilder.mNetworkRequest;
    this.networkDownloadBytes = paramBuilder.mNetworkDownloadBytes;
    this.networkUploadBytes = paramBuilder.mNetworkUploadBytes;
    this.minLatencyMillis = paramBuilder.mMinLatencyMillis;
    this.maxExecutionDelayMillis = paramBuilder.mMaxExecutionDelayMillis;
    this.isPeriodic = paramBuilder.mIsPeriodic;
    this.isPersisted = paramBuilder.mIsPersisted;
    this.intervalMillis = paramBuilder.mIntervalMillis;
    this.flexMillis = paramBuilder.mFlexMillis;
    this.initialBackoffMillis = paramBuilder.mInitialBackoffMillis;
    this.backoffPolicy = paramBuilder.mBackoffPolicy;
    this.hasEarlyConstraint = paramBuilder.mHasEarlyConstraint;
    this.hasLateConstraint = paramBuilder.mHasLateConstraint;
    this.priority = paramBuilder.mPriority;
    this.flags = paramBuilder.mFlags;
  }
  
  private JobInfo(Parcel paramParcel) {
    this.jobId = paramParcel.readInt();
    this.extras = paramParcel.readPersistableBundle();
    this.transientExtras = paramParcel.readBundle();
    int i = paramParcel.readInt();
    boolean bool1 = false;
    if (i != 0) {
      this.clipData = (ClipData)ClipData.CREATOR.createFromParcel(paramParcel);
      this.clipGrantFlags = paramParcel.readInt();
    } else {
      this.clipData = null;
      this.clipGrantFlags = 0;
    } 
    this.service = (ComponentName)paramParcel.readParcelable(null);
    this.constraintFlags = paramParcel.readInt();
    this.triggerContentUris = (TriggerContentUri[])paramParcel.createTypedArray(TriggerContentUri.CREATOR);
    this.triggerContentUpdateDelay = paramParcel.readLong();
    this.triggerContentMaxDelay = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      this.networkRequest = (NetworkRequest)NetworkRequest.CREATOR.createFromParcel(paramParcel);
    } else {
      this.networkRequest = null;
    } 
    this.networkDownloadBytes = paramParcel.readLong();
    this.networkUploadBytes = paramParcel.readLong();
    this.minLatencyMillis = paramParcel.readLong();
    this.maxExecutionDelayMillis = paramParcel.readLong();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.isPeriodic = bool2;
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.isPersisted = bool2;
    this.intervalMillis = paramParcel.readLong();
    this.flexMillis = paramParcel.readLong();
    this.initialBackoffMillis = paramParcel.readLong();
    this.backoffPolicy = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.hasEarlyConstraint = bool2;
    boolean bool2 = bool1;
    if (paramParcel.readInt() == 1)
      bool2 = true; 
    this.hasLateConstraint = bool2;
    this.priority = paramParcel.readInt();
    this.flags = paramParcel.readInt();
  }
  
  public static final long getMinBackoffMillis() {
    return 10000L;
  }
  
  public static final long getMinFlexMillis() {
    return 300000L;
  }
  
  public static final long getMinPeriodMillis() {
    return 900000L;
  }
  
  public static String getPriorityString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 10) {
        if (paramInt != 20) {
          if (paramInt != 30) {
            if (paramInt != 35) {
              if (paramInt != 40) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(paramInt);
                stringBuilder.append(" [UNKNOWN]");
                return stringBuilder.toString();
              } 
              return "40 [TOP_APP]";
            } 
            return "35 [FGS_APP]";
          } 
          return "30 [BFGS_APP]";
        } 
        return "20 [SYNC_INITIALIZATION]";
      } 
      return "10 [SYNC_EXPEDITED]";
    } 
    return "0 [DEFAULT]";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof JobInfo))
      return false; 
    paramObject = paramObject;
    return (this.jobId != ((JobInfo)paramObject).jobId) ? false : (!BaseBundle.kindofEquals((BaseBundle)this.extras, (BaseBundle)((JobInfo)paramObject).extras) ? false : (!BaseBundle.kindofEquals((BaseBundle)this.transientExtras, (BaseBundle)((JobInfo)paramObject).transientExtras) ? false : ((this.clipData != ((JobInfo)paramObject).clipData) ? false : ((this.clipGrantFlags != ((JobInfo)paramObject).clipGrantFlags) ? false : (!Objects.equals(this.service, ((JobInfo)paramObject).service) ? false : ((this.constraintFlags != ((JobInfo)paramObject).constraintFlags) ? false : (!Arrays.equals((Object[])this.triggerContentUris, (Object[])((JobInfo)paramObject).triggerContentUris) ? false : ((this.triggerContentUpdateDelay != ((JobInfo)paramObject).triggerContentUpdateDelay) ? false : ((this.triggerContentMaxDelay != ((JobInfo)paramObject).triggerContentMaxDelay) ? false : ((this.hasEarlyConstraint != ((JobInfo)paramObject).hasEarlyConstraint) ? false : ((this.hasLateConstraint != ((JobInfo)paramObject).hasLateConstraint) ? false : (!Objects.equals(this.networkRequest, ((JobInfo)paramObject).networkRequest) ? false : ((this.networkDownloadBytes != ((JobInfo)paramObject).networkDownloadBytes) ? false : ((this.networkUploadBytes != ((JobInfo)paramObject).networkUploadBytes) ? false : ((this.minLatencyMillis != ((JobInfo)paramObject).minLatencyMillis) ? false : ((this.maxExecutionDelayMillis != ((JobInfo)paramObject).maxExecutionDelayMillis) ? false : ((this.isPeriodic != ((JobInfo)paramObject).isPeriodic) ? false : ((this.isPersisted != ((JobInfo)paramObject).isPersisted) ? false : ((this.intervalMillis != ((JobInfo)paramObject).intervalMillis) ? false : ((this.flexMillis != ((JobInfo)paramObject).flexMillis) ? false : ((this.initialBackoffMillis != ((JobInfo)paramObject).initialBackoffMillis) ? false : ((this.backoffPolicy != ((JobInfo)paramObject).backoffPolicy) ? false : ((this.priority != ((JobInfo)paramObject).priority) ? false : (!(this.flags != ((JobInfo)paramObject).flags)))))))))))))))))))))))));
  }
  
  public int getBackoffPolicy() {
    return this.backoffPolicy;
  }
  
  public ClipData getClipData() {
    return this.clipData;
  }
  
  public int getClipGrantFlags() {
    return this.clipGrantFlags;
  }
  
  public int getConstraintFlags() {
    return this.constraintFlags;
  }
  
  public long getEstimatedNetworkDownloadBytes() {
    return this.networkDownloadBytes;
  }
  
  public long getEstimatedNetworkUploadBytes() {
    return this.networkUploadBytes;
  }
  
  public PersistableBundle getExtras() {
    return this.extras;
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public long getFlexMillis() {
    return this.flexMillis;
  }
  
  public int getId() {
    return this.jobId;
  }
  
  public long getInitialBackoffMillis() {
    return this.initialBackoffMillis;
  }
  
  public long getIntervalMillis() {
    return this.intervalMillis;
  }
  
  public long getMaxExecutionDelayMillis() {
    return this.maxExecutionDelayMillis;
  }
  
  public long getMinLatencyMillis() {
    return this.minLatencyMillis;
  }
  
  @Deprecated
  public int getNetworkType() {
    NetworkRequest networkRequest = this.networkRequest;
    return (networkRequest == null) ? 0 : (networkRequest.networkCapabilities.hasCapability(11) ? 2 : (this.networkRequest.networkCapabilities.hasCapability(18) ? 3 : (this.networkRequest.networkCapabilities.hasTransport(0) ? 4 : 1)));
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public NetworkRequest getRequiredNetwork() {
    return this.networkRequest;
  }
  
  public ComponentName getService() {
    return this.service;
  }
  
  public Bundle getTransientExtras() {
    return this.transientExtras;
  }
  
  public long getTriggerContentMaxDelay() {
    return this.triggerContentMaxDelay;
  }
  
  public long getTriggerContentUpdateDelay() {
    return this.triggerContentUpdateDelay;
  }
  
  public TriggerContentUri[] getTriggerContentUris() {
    return this.triggerContentUris;
  }
  
  public boolean hasEarlyConstraint() {
    return this.hasEarlyConstraint;
  }
  
  public boolean hasLateConstraint() {
    return this.hasLateConstraint;
  }
  
  public int hashCode() {
    int i = this.jobId;
    PersistableBundle persistableBundle = this.extras;
    int j = i;
    if (persistableBundle != null)
      j = i * 31 + persistableBundle.hashCode(); 
    Bundle bundle = this.transientExtras;
    i = j;
    if (bundle != null)
      i = j * 31 + bundle.hashCode(); 
    ClipData clipData = this.clipData;
    j = i;
    if (clipData != null)
      j = i * 31 + clipData.hashCode(); 
    i = j * 31 + this.clipGrantFlags;
    ComponentName componentName = this.service;
    j = i;
    if (componentName != null)
      j = i * 31 + componentName.hashCode(); 
    i = j * 31 + this.constraintFlags;
    TriggerContentUri[] arrayOfTriggerContentUri = this.triggerContentUris;
    j = i;
    if (arrayOfTriggerContentUri != null)
      j = i * 31 + Arrays.hashCode((Object[])arrayOfTriggerContentUri); 
    i = (((j * 31 + Long.hashCode(this.triggerContentUpdateDelay)) * 31 + Long.hashCode(this.triggerContentMaxDelay)) * 31 + Boolean.hashCode(this.hasEarlyConstraint)) * 31 + Boolean.hashCode(this.hasLateConstraint);
    NetworkRequest networkRequest = this.networkRequest;
    j = i;
    if (networkRequest != null)
      j = i * 31 + networkRequest.hashCode(); 
    return (((((((((((j * 31 + Long.hashCode(this.networkDownloadBytes)) * 31 + Long.hashCode(this.networkUploadBytes)) * 31 + Long.hashCode(this.minLatencyMillis)) * 31 + Long.hashCode(this.maxExecutionDelayMillis)) * 31 + Boolean.hashCode(this.isPeriodic)) * 31 + Boolean.hashCode(this.isPersisted)) * 31 + Long.hashCode(this.intervalMillis)) * 31 + Long.hashCode(this.flexMillis)) * 31 + Long.hashCode(this.initialBackoffMillis)) * 31 + this.backoffPolicy) * 31 + this.priority) * 31 + this.flags;
  }
  
  public boolean isExemptedFromAppStandby() {
    boolean bool;
    if ((this.flags & 0x8) != 0 && !isPeriodic()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isImportantWhileForeground() {
    boolean bool;
    if ((this.flags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isPeriodic() {
    return this.isPeriodic;
  }
  
  public boolean isPersisted() {
    return this.isPersisted;
  }
  
  public boolean isPrefetch() {
    boolean bool;
    if ((this.flags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRequireBatteryNotLow() {
    boolean bool;
    if ((this.constraintFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRequireCharging() {
    int i = this.constraintFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isRequireDeviceIdle() {
    boolean bool;
    if ((this.constraintFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRequireStorageNotLow() {
    boolean bool;
    if ((this.constraintFlags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(job:");
    stringBuilder.append(this.jobId);
    stringBuilder.append("/");
    stringBuilder.append(this.service.flattenToShortString());
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.jobId);
    paramParcel.writePersistableBundle(this.extras);
    paramParcel.writeBundle(this.transientExtras);
    if (this.clipData != null) {
      paramParcel.writeInt(1);
      this.clipData.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.clipGrantFlags);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeParcelable((Parcelable)this.service, paramInt);
    paramParcel.writeInt(this.constraintFlags);
    paramParcel.writeTypedArray((Parcelable[])this.triggerContentUris, paramInt);
    paramParcel.writeLong(this.triggerContentUpdateDelay);
    paramParcel.writeLong(this.triggerContentMaxDelay);
    if (this.networkRequest != null) {
      paramParcel.writeInt(1);
      this.networkRequest.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeLong(this.networkDownloadBytes);
    paramParcel.writeLong(this.networkUploadBytes);
    paramParcel.writeLong(this.minLatencyMillis);
    paramParcel.writeLong(this.maxExecutionDelayMillis);
    paramParcel.writeInt(this.isPeriodic);
    paramParcel.writeInt(this.isPersisted);
    paramParcel.writeLong(this.intervalMillis);
    paramParcel.writeLong(this.flexMillis);
    paramParcel.writeLong(this.initialBackoffMillis);
    paramParcel.writeInt(this.backoffPolicy);
    paramParcel.writeInt(this.hasEarlyConstraint);
    paramParcel.writeInt(this.hasLateConstraint);
    paramParcel.writeInt(this.priority);
    paramParcel.writeInt(this.flags);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BackoffPolicy {}
  
  public static final class Builder {
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
    
    public Builder(int param1Int, ComponentName param1ComponentName) {
      this.mJobService = param1ComponentName;
      this.mJobId = param1Int;
    }
    
    public Builder addTriggerContentUri(JobInfo.TriggerContentUri param1TriggerContentUri) {
      if (this.mTriggerContentUris == null)
        this.mTriggerContentUris = new ArrayList<>(); 
      this.mTriggerContentUris.add(param1TriggerContentUri);
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
            return new JobInfo(this); 
          throw new IllegalArgumentException("An idle mode job will not respect any back-off policy, so calling setBackoffCriteria with setRequiresDeviceIdle is an error.");
        } 
        throw new IllegalArgumentException("An important while foreground job cannot have a time delay");
      } 
      throw new IllegalArgumentException("Can't provide estimated network usage without requiring a network");
    }
    
    public Builder setBackoffCriteria(long param1Long, int param1Int) {
      long l1 = JobInfo.getMinBackoffMillis();
      long l2 = param1Long;
      if (param1Long < l1) {
        String str = JobInfo.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requested backoff ");
        stringBuilder.append(TimeUtils.formatDuration(param1Long));
        stringBuilder.append(" for job ");
        stringBuilder.append(this.mJobId);
        stringBuilder.append(" is too small; raising to ");
        stringBuilder.append(TimeUtils.formatDuration(l1));
        Log.w(str, stringBuilder.toString());
        l2 = l1;
      } 
      this.mBackoffPolicySet = true;
      this.mInitialBackoffMillis = l2;
      this.mBackoffPolicy = param1Int;
      return this;
    }
    
    public Builder setClipData(ClipData param1ClipData, int param1Int) {
      this.mClipData = param1ClipData;
      this.mClipGrantFlags = param1Int;
      return this;
    }
    
    public Builder setEstimatedNetworkBytes(long param1Long1, long param1Long2) {
      this.mNetworkDownloadBytes = param1Long1;
      this.mNetworkUploadBytes = param1Long2;
      return this;
    }
    
    public Builder setExtras(PersistableBundle param1PersistableBundle) {
      this.mExtras = param1PersistableBundle;
      return this;
    }
    
    public Builder setFlags(int param1Int) {
      this.mFlags = param1Int;
      return this;
    }
    
    public Builder setImportantWhileForeground(boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= 0x2;
      } else {
        this.mFlags &= 0xFFFFFFFD;
      } 
      return this;
    }
    
    public Builder setMinimumLatency(long param1Long) {
      this.mMinLatencyMillis = param1Long;
      this.mHasEarlyConstraint = true;
      return this;
    }
    
    public Builder setOverrideDeadline(long param1Long) {
      this.mMaxExecutionDelayMillis = param1Long;
      this.mHasLateConstraint = true;
      return this;
    }
    
    public Builder setPeriodic(long param1Long) {
      return setPeriodic(param1Long, param1Long);
    }
    
    public Builder setPeriodic(long param1Long1, long param1Long2) {
      long l1 = JobInfo.getMinPeriodMillis();
      long l2 = param1Long1;
      if (param1Long1 < l1) {
        String str = JobInfo.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requested interval ");
        stringBuilder.append(TimeUtils.formatDuration(param1Long1));
        stringBuilder.append(" for job ");
        stringBuilder.append(this.mJobId);
        stringBuilder.append(" is too small; raising to ");
        stringBuilder.append(TimeUtils.formatDuration(l1));
        Log.w(str, stringBuilder.toString());
        l2 = l1;
      } 
      l1 = Math.max(5L * l2 / 100L, JobInfo.getMinFlexMillis());
      param1Long1 = param1Long2;
      if (param1Long2 < l1) {
        String str = JobInfo.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requested flex ");
        stringBuilder.append(TimeUtils.formatDuration(param1Long2));
        stringBuilder.append(" for job ");
        stringBuilder.append(this.mJobId);
        stringBuilder.append(" is too small; raising to ");
        stringBuilder.append(TimeUtils.formatDuration(l1));
        Log.w(str, stringBuilder.toString());
        param1Long1 = l1;
      } 
      this.mIsPeriodic = true;
      this.mIntervalMillis = l2;
      this.mFlexMillis = param1Long1;
      this.mHasLateConstraint = true;
      this.mHasEarlyConstraint = true;
      return this;
    }
    
    public Builder setPersisted(boolean param1Boolean) {
      this.mIsPersisted = param1Boolean;
      return this;
    }
    
    public Builder setPrefetch(boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= 0x4;
      } else {
        this.mFlags &= 0xFFFFFFFB;
      } 
      return this;
    }
    
    public Builder setPriority(int param1Int) {
      this.mPriority = param1Int;
      return this;
    }
    
    public Builder setRequiredNetwork(NetworkRequest param1NetworkRequest) {
      this.mNetworkRequest = param1NetworkRequest;
      return this;
    }
    
    public Builder setRequiredNetworkType(int param1Int) {
      if (param1Int == 0)
        return setRequiredNetwork(null); 
      NetworkRequest.Builder builder = new NetworkRequest.Builder();
      builder.addCapability(12);
      builder.addCapability(16);
      builder.removeCapability(15);
      if (param1Int != 1)
        if (param1Int == 2) {
          builder.addCapability(11);
        } else if (param1Int == 3) {
          builder.addCapability(18);
        } else if (param1Int == 4) {
          builder.addTransportType(0);
        }  
      return setRequiredNetwork(builder.build());
    }
    
    public Builder setRequiresBatteryNotLow(boolean param1Boolean) {
      boolean bool;
      int i = this.mConstraintFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mConstraintFlags = i & 0xFFFFFFFD | bool;
      return this;
    }
    
    public Builder setRequiresCharging(boolean param1Boolean) {
      this.mConstraintFlags = this.mConstraintFlags & 0xFFFFFFFE | param1Boolean;
      return this;
    }
    
    public Builder setRequiresDeviceIdle(boolean param1Boolean) {
      boolean bool;
      int i = this.mConstraintFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mConstraintFlags = i & 0xFFFFFFFB | bool;
      return this;
    }
    
    public Builder setRequiresStorageNotLow(boolean param1Boolean) {
      boolean bool;
      int i = this.mConstraintFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mConstraintFlags = i & 0xFFFFFFF7 | bool;
      return this;
    }
    
    public Builder setTransientExtras(Bundle param1Bundle) {
      this.mTransientExtras = param1Bundle;
      return this;
    }
    
    public Builder setTriggerContentMaxDelay(long param1Long) {
      this.mTriggerContentMaxDelay = param1Long;
      return this;
    }
    
    public Builder setTriggerContentUpdateDelay(long param1Long) {
      this.mTriggerContentUpdateDelay = param1Long;
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
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface NetworkType {}
  
  public static final class TriggerContentUri implements Parcelable {
    public static final Parcelable.Creator<TriggerContentUri> CREATOR = new Parcelable.Creator<TriggerContentUri>() {
        public JobInfo.TriggerContentUri createFromParcel(Parcel param2Parcel) {
          return new JobInfo.TriggerContentUri(param2Parcel);
        }
        
        public JobInfo.TriggerContentUri[] newArray(int param2Int) {
          return new JobInfo.TriggerContentUri[param2Int];
        }
      };
    
    public static final int FLAG_NOTIFY_FOR_DESCENDANTS = 1;
    
    private final int mFlags;
    
    private final Uri mUri;
    
    public TriggerContentUri(Uri param1Uri, int param1Int) {
      Objects.requireNonNull(param1Uri);
      this.mUri = param1Uri;
      this.mFlags = param1Int;
    }
    
    private TriggerContentUri(Parcel param1Parcel) {
      this.mUri = (Uri)Uri.CREATOR.createFromParcel(param1Parcel);
      this.mFlags = param1Parcel.readInt();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof TriggerContentUri;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      bool = bool1;
      if (Objects.equals(((TriggerContentUri)param1Object).mUri, this.mUri)) {
        bool = bool1;
        if (((TriggerContentUri)param1Object).mFlags == this.mFlags)
          bool = true; 
      } 
      return bool;
    }
    
    public int getFlags() {
      return this.mFlags;
    }
    
    public Uri getUri() {
      return this.mUri;
    }
    
    public int hashCode() {
      int i;
      Uri uri = this.mUri;
      if (uri == null) {
        i = 0;
      } else {
        i = uri.hashCode();
      } 
      return i ^ this.mFlags;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      this.mUri.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.mFlags);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Flags {}
  }
  
  class null implements Parcelable.Creator<TriggerContentUri> {
    public JobInfo.TriggerContentUri createFromParcel(Parcel param1Parcel) {
      return new JobInfo.TriggerContentUri(param1Parcel);
    }
    
    public JobInfo.TriggerContentUri[] newArray(int param1Int) {
      return new JobInfo.TriggerContentUri[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Flags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */