package android.app;

import android.annotation.SystemApi;
import android.os.Bundle;

@SystemApi
public final class TvExtender implements Notification.Extender {
  private static final String EXTRA_CHANNEL_ID = "channel_id";
  
  private static final String EXTRA_CONTENT_INTENT = "content_intent";
  
  private static final String EXTRA_DELETE_INTENT = "delete_intent";
  
  private static final String EXTRA_FLAGS = "flags";
  
  private static final String EXTRA_SUPPRESS_SHOW_OVER_APPS = "suppressShowOverApps";
  
  private static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
  
  private static final int FLAG_AVAILABLE_ON_TV = 1;
  
  private static final String TAG = "TvExtender";
  
  private String mChannelId;
  
  private PendingIntent mContentIntent;
  
  private PendingIntent mDeleteIntent;
  
  private int mFlags;
  
  private boolean mSuppressShowOverApps;
  
  public TvExtender() {
    this.mFlags = 1;
  }
  
  public TvExtender(Notification paramNotification) {
    Bundle bundle;
    if (paramNotification.extras == null) {
      paramNotification = null;
    } else {
      bundle = paramNotification.extras.getBundle("android.tv.EXTENSIONS");
    } 
    if (bundle != null) {
      this.mFlags = bundle.getInt("flags");
      this.mChannelId = bundle.getString("channel_id");
      this.mSuppressShowOverApps = bundle.getBoolean("suppressShowOverApps");
      this.mContentIntent = (PendingIntent)bundle.getParcelable("content_intent");
      this.mDeleteIntent = (PendingIntent)bundle.getParcelable("delete_intent");
    } 
  }
  
  public Notification.Builder extend(Notification.Builder paramBuilder) {
    Bundle bundle = new Bundle();
    bundle.putInt("flags", this.mFlags);
    bundle.putString("channel_id", this.mChannelId);
    bundle.putBoolean("suppressShowOverApps", this.mSuppressShowOverApps);
    PendingIntent pendingIntent = this.mContentIntent;
    if (pendingIntent != null)
      bundle.putParcelable("content_intent", pendingIntent); 
    pendingIntent = this.mDeleteIntent;
    if (pendingIntent != null)
      bundle.putParcelable("delete_intent", pendingIntent); 
    paramBuilder.getExtras().putBundle("android.tv.EXTENSIONS", bundle);
    return paramBuilder;
  }
  
  @Deprecated
  public String getChannel() {
    return this.mChannelId;
  }
  
  public String getChannelId() {
    return this.mChannelId;
  }
  
  public PendingIntent getContentIntent() {
    return this.mContentIntent;
  }
  
  public PendingIntent getDeleteIntent() {
    return this.mDeleteIntent;
  }
  
  public boolean getSuppressShowOverApps() {
    return this.mSuppressShowOverApps;
  }
  
  public boolean isAvailableOnTv() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public TvExtender setChannel(String paramString) {
    this.mChannelId = paramString;
    return this;
  }
  
  public TvExtender setChannelId(String paramString) {
    this.mChannelId = paramString;
    return this;
  }
  
  public TvExtender setContentIntent(PendingIntent paramPendingIntent) {
    this.mContentIntent = paramPendingIntent;
    return this;
  }
  
  public TvExtender setDeleteIntent(PendingIntent paramPendingIntent) {
    this.mDeleteIntent = paramPendingIntent;
    return this;
  }
  
  public TvExtender setSuppressShowOverApps(boolean paramBoolean) {
    this.mSuppressShowOverApps = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$TvExtender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */