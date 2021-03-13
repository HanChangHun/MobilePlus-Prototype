package android.content;

import android.accounts.Account;
import android.os.Bundle;

public class Builder {
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
  
  private void setupInterval(long paramLong1, long paramLong2) {
    if (paramLong2 <= paramLong1) {
      this.mSyncRunTimeSecs = paramLong1;
      this.mSyncFlexTimeSecs = paramLong2;
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
  
  public Builder setDisallowMetered(boolean paramBoolean) {
    if (!this.mIgnoreSettings || !paramBoolean) {
      this.mDisallowMetered = paramBoolean;
      return this;
    } 
    throw new IllegalArgumentException("setDisallowMetered(true) after having specified that settings are ignored.");
  }
  
  public Builder setExpedited(boolean paramBoolean) {
    this.mExpedited = paramBoolean;
    return this;
  }
  
  public Builder setExtras(Bundle paramBundle) {
    this.mCustomExtras = paramBundle;
    return this;
  }
  
  public Builder setIgnoreBackoff(boolean paramBoolean) {
    this.mIgnoreBackoff = paramBoolean;
    return this;
  }
  
  public Builder setIgnoreSettings(boolean paramBoolean) {
    if (!this.mDisallowMetered || !paramBoolean) {
      this.mIgnoreSettings = paramBoolean;
      return this;
    } 
    throw new IllegalArgumentException("setIgnoreSettings(true) after having specified sync settings with this builder.");
  }
  
  public Builder setManual(boolean paramBoolean) {
    this.mIsManual = paramBoolean;
    return this;
  }
  
  public Builder setNoRetry(boolean paramBoolean) {
    this.mNoRetry = paramBoolean;
    return this;
  }
  
  public Builder setRequiresCharging(boolean paramBoolean) {
    this.mRequiresCharging = paramBoolean;
    return this;
  }
  
  public Builder setSyncAdapter(Account paramAccount, String paramString) {
    if (this.mSyncTarget == 0) {
      if (paramString == null || paramString.length() != 0) {
        this.mSyncTarget = 2;
        this.mAccount = paramAccount;
        this.mAuthority = paramString;
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
  
  public Builder syncPeriodic(long paramLong1, long paramLong2) {
    if (this.mSyncType == 0) {
      this.mSyncType = 1;
      setupInterval(paramLong1, paramLong2);
      return this;
    } 
    throw new IllegalArgumentException("Sync type has already been defined.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */