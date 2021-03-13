package android.app;

import android.annotation.SystemApi;
import android.os.Bundle;

@SystemApi
public class BroadcastOptions {
  static final String KEY_ALLOW_BACKGROUND_ACTIVITY_STARTS = "android:broadcast.allowBackgroundActivityStarts";
  
  static final String KEY_DONT_SEND_TO_RESTRICTED_APPS = "android:broadcast.dontSendToRestrictedApps";
  
  static final String KEY_MAX_MANIFEST_RECEIVER_API_LEVEL = "android:broadcast.maxManifestReceiverApiLevel";
  
  static final String KEY_MIN_MANIFEST_RECEIVER_API_LEVEL = "android:broadcast.minManifestReceiverApiLevel";
  
  static final String KEY_TEMPORARY_APP_WHITELIST_DURATION = "android:broadcast.temporaryAppWhitelistDuration";
  
  private boolean mAllowBackgroundActivityStarts;
  
  private boolean mDontSendToRestrictedApps = false;
  
  private int mMaxManifestReceiverApiLevel = 10000;
  
  private int mMinManifestReceiverApiLevel = 0;
  
  private long mTemporaryAppWhitelistDuration;
  
  private BroadcastOptions() {}
  
  public BroadcastOptions(Bundle paramBundle) {
    this.mTemporaryAppWhitelistDuration = paramBundle.getLong("android:broadcast.temporaryAppWhitelistDuration");
    this.mMinManifestReceiverApiLevel = paramBundle.getInt("android:broadcast.minManifestReceiverApiLevel", 0);
    this.mMaxManifestReceiverApiLevel = paramBundle.getInt("android:broadcast.maxManifestReceiverApiLevel", 10000);
    this.mDontSendToRestrictedApps = paramBundle.getBoolean("android:broadcast.dontSendToRestrictedApps", false);
    this.mAllowBackgroundActivityStarts = paramBundle.getBoolean("android:broadcast.allowBackgroundActivityStarts", false);
  }
  
  public static BroadcastOptions makeBasic() {
    return new BroadcastOptions();
  }
  
  public boolean allowsBackgroundActivityStarts() {
    return this.mAllowBackgroundActivityStarts;
  }
  
  public int getMaxManifestReceiverApiLevel() {
    return this.mMaxManifestReceiverApiLevel;
  }
  
  public int getMinManifestReceiverApiLevel() {
    return this.mMinManifestReceiverApiLevel;
  }
  
  public long getTemporaryAppWhitelistDuration() {
    return this.mTemporaryAppWhitelistDuration;
  }
  
  public boolean isDontSendToRestrictedApps() {
    return this.mDontSendToRestrictedApps;
  }
  
  public void setBackgroundActivityStartsAllowed(boolean paramBoolean) {
    this.mAllowBackgroundActivityStarts = paramBoolean;
  }
  
  public void setDontSendToRestrictedApps(boolean paramBoolean) {
    this.mDontSendToRestrictedApps = paramBoolean;
  }
  
  public void setMaxManifestReceiverApiLevel(int paramInt) {
    this.mMaxManifestReceiverApiLevel = paramInt;
  }
  
  public void setMinManifestReceiverApiLevel(int paramInt) {
    this.mMinManifestReceiverApiLevel = paramInt;
  }
  
  public void setTemporaryAppWhitelistDuration(long paramLong) {
    this.mTemporaryAppWhitelistDuration = paramLong;
  }
  
  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    long l = this.mTemporaryAppWhitelistDuration;
    if (l > 0L)
      bundle.putLong("android:broadcast.temporaryAppWhitelistDuration", l); 
    int i = this.mMinManifestReceiverApiLevel;
    if (i != 0)
      bundle.putInt("android:broadcast.minManifestReceiverApiLevel", i); 
    i = this.mMaxManifestReceiverApiLevel;
    if (i != 10000)
      bundle.putInt("android:broadcast.maxManifestReceiverApiLevel", i); 
    if (this.mDontSendToRestrictedApps)
      bundle.putBoolean("android:broadcast.dontSendToRestrictedApps", true); 
    if (this.mAllowBackgroundActivityStarts)
      bundle.putBoolean("android:broadcast.allowBackgroundActivityStarts", true); 
    if (bundle.isEmpty())
      bundle = null; 
    return bundle;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/BroadcastOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */