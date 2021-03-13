package android.content;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class SyncRequest implements Parcelable {
  public static final Parcelable.Creator<SyncRequest> CREATOR = new Parcelable.Creator<SyncRequest>() {
      public SyncRequest createFromParcel(Parcel param1Parcel) {
        return new SyncRequest(param1Parcel);
      }
      
      public SyncRequest[] newArray(int param1Int) {
        return new SyncRequest[param1Int];
      }
    };
  
  private static final String TAG = "SyncRequest";
  
  private final Account mAccountToSync;
  
  private final String mAuthority;
  
  private final boolean mDisallowMetered;
  
  private final Bundle mExtras;
  
  private final boolean mIsAuthority;
  
  private final boolean mIsExpedited;
  
  private final boolean mIsPeriodic;
  
  private final long mSyncFlexTimeSecs;
  
  private final long mSyncRunTimeSecs;
  
  protected SyncRequest(Builder paramBuilder) {
    this.mSyncFlexTimeSecs = paramBuilder.mSyncFlexTimeSecs;
    this.mSyncRunTimeSecs = paramBuilder.mSyncRunTimeSecs;
    this.mAccountToSync = paramBuilder.mAccount;
    this.mAuthority = paramBuilder.mAuthority;
    int i = paramBuilder.mSyncType;
    boolean bool1 = false;
    if (i == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsPeriodic = bool2;
    boolean bool2 = bool1;
    if (paramBuilder.mSyncTarget == 2)
      bool2 = true; 
    this.mIsAuthority = bool2;
    this.mIsExpedited = paramBuilder.mExpedited;
    Bundle bundle = new Bundle(paramBuilder.mCustomExtras);
    this.mExtras = bundle;
    bundle.putAll(paramBuilder.mSyncConfigExtras);
    this.mDisallowMetered = paramBuilder.mDisallowMetered;
  }
  
  private SyncRequest(Parcel paramParcel) {
    boolean bool2;
    Bundle bundle = paramParcel.readBundle();
    boolean bool1 = true;
    this.mExtras = Bundle.setDefusable(bundle, true);
    this.mSyncFlexTimeSecs = paramParcel.readLong();
    this.mSyncRunTimeSecs = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsPeriodic = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mDisallowMetered = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsAuthority = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mIsExpedited = bool2;
    this.mAccountToSync = (Account)paramParcel.readParcelable(null);
    this.mAuthority = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Account getAccount() {
    return this.mAccountToSync;
  }
  
  public Bundle getBundle() {
    return this.mExtras;
  }
  
  public String getProvider() {
    return this.mAuthority;
  }
  
  public long getSyncFlexTime() {
    return this.mSyncFlexTimeSecs;
  }
  
  public long getSyncRunTime() {
    return this.mSyncRunTimeSecs;
  }
  
  public boolean isExpedited() {
    return this.mIsExpedited;
  }
  
  public boolean isPeriodic() {
    return this.mIsPeriodic;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBundle(this.mExtras);
    paramParcel.writeLong(this.mSyncFlexTimeSecs);
    paramParcel.writeLong(this.mSyncRunTimeSecs);
    paramParcel.writeInt(this.mIsPeriodic);
    paramParcel.writeInt(this.mDisallowMetered);
    paramParcel.writeInt(this.mIsAuthority);
    paramParcel.writeInt(this.mIsExpedited);
    paramParcel.writeParcelable((Parcelable)this.mAccountToSync, paramInt);
    paramParcel.writeString(this.mAuthority);
  }
  
  public static class Builder {
    private static final int SYNC_TARGET_ADAPTER = 2;
    
    private static final int SYNC_TARGET_UNKNOWN = 0;
    
    private static final int SYNC_TYPE_ONCE = 2;
    
    private static final int SYNC_TYPE_PERIODIC = 1;
    
    private static final int SYNC_TYPE_UNKNOWN = 0;
    
    private Account mAccount;
    
    private String mAuthority;
    
    private Bundle mCustomExtras;
    
    private boolean mDisallowMetered;
    
    private boolean mExpedited;
    
    private boolean mIgnoreBackoff;
    
    private boolean mIgnoreSettings;
    
    private boolean mIsManual;
    
    private boolean mNoRetry;
    
    private boolean mRequiresCharging;
    
    private Bundle mSyncConfigExtras;
    
    private long mSyncFlexTimeSecs;
    
    private long mSyncRunTimeSecs;
    
    private int mSyncTarget = 0;
    
    private int mSyncType = 0;
    
    private void setupInterval(long param1Long1, long param1Long2) {
      if (param1Long2 <= param1Long1) {
        this.mSyncRunTimeSecs = param1Long1;
        this.mSyncFlexTimeSecs = param1Long2;
        return;
      } 
      throw new IllegalArgumentException("Specified run time for the sync must be after the specified flex time.");
    }
    
    public SyncRequest build() {
      ContentResolver.validateSyncExtrasBundle(this.mCustomExtras);
      if (this.mCustomExtras == null)
        this.mCustomExtras = new Bundle(); 
      Bundle bundle = new Bundle();
      this.mSyncConfigExtras = bundle;
      if (this.mIgnoreBackoff)
        bundle.putBoolean("ignore_backoff", true); 
      if (this.mDisallowMetered)
        this.mSyncConfigExtras.putBoolean("allow_metered", true); 
      if (this.mRequiresCharging)
        this.mSyncConfigExtras.putBoolean("require_charging", true); 
      if (this.mIgnoreSettings)
        this.mSyncConfigExtras.putBoolean("ignore_settings", true); 
      if (this.mNoRetry)
        this.mSyncConfigExtras.putBoolean("do_not_retry", true); 
      if (this.mExpedited)
        this.mSyncConfigExtras.putBoolean("expedited", true); 
      if (this.mIsManual) {
        this.mSyncConfigExtras.putBoolean("ignore_backoff", true);
        this.mSyncConfigExtras.putBoolean("ignore_settings", true);
      } 
      if (this.mSyncType != 1 || (!ContentResolver.invalidPeriodicExtras(this.mCustomExtras) && !ContentResolver.invalidPeriodicExtras(this.mSyncConfigExtras))) {
        if (this.mSyncTarget != 0)
          return new SyncRequest(this); 
        throw new IllegalArgumentException("Must specify an adapter with setSyncAdapter(Account, String");
      } 
      throw new IllegalArgumentException("Illegal extras were set");
    }
    
    public Builder setDisallowMetered(boolean param1Boolean) {
      if (!this.mIgnoreSettings || !param1Boolean) {
        this.mDisallowMetered = param1Boolean;
        return this;
      } 
      throw new IllegalArgumentException("setDisallowMetered(true) after having specified that settings are ignored.");
    }
    
    public Builder setExpedited(boolean param1Boolean) {
      this.mExpedited = param1Boolean;
      return this;
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mCustomExtras = param1Bundle;
      return this;
    }
    
    public Builder setIgnoreBackoff(boolean param1Boolean) {
      this.mIgnoreBackoff = param1Boolean;
      return this;
    }
    
    public Builder setIgnoreSettings(boolean param1Boolean) {
      if (!this.mDisallowMetered || !param1Boolean) {
        this.mIgnoreSettings = param1Boolean;
        return this;
      } 
      throw new IllegalArgumentException("setIgnoreSettings(true) after having specified sync settings with this builder.");
    }
    
    public Builder setManual(boolean param1Boolean) {
      this.mIsManual = param1Boolean;
      return this;
    }
    
    public Builder setNoRetry(boolean param1Boolean) {
      this.mNoRetry = param1Boolean;
      return this;
    }
    
    public Builder setRequiresCharging(boolean param1Boolean) {
      this.mRequiresCharging = param1Boolean;
      return this;
    }
    
    public Builder setSyncAdapter(Account param1Account, String param1String) {
      if (this.mSyncTarget == 0) {
        if (param1String == null || param1String.length() != 0) {
          this.mSyncTarget = 2;
          this.mAccount = param1Account;
          this.mAuthority = param1String;
          return this;
        } 
        throw new IllegalArgumentException("Authority must be non-empty");
      } 
      throw new IllegalArgumentException("Sync target has already been defined.");
    }
    
    public Builder syncOnce() {
      if (this.mSyncType == 0) {
        this.mSyncType = 2;
        setupInterval(0L, 0L);
        return this;
      } 
      throw new IllegalArgumentException("Sync type has already been defined.");
    }
    
    public Builder syncPeriodic(long param1Long1, long param1Long2) {
      if (this.mSyncType == 0) {
        this.mSyncType = 1;
        setupInterval(param1Long1, param1Long2);
        return this;
      } 
      throw new IllegalArgumentException("Sync type has already been defined.");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */