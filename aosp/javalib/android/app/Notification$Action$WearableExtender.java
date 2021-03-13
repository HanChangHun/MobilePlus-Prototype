package android.app;

import android.os.Bundle;

public final class WearableExtender implements Notification.Action.Extender {
  private static final int DEFAULT_FLAGS = 1;
  
  private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
  
  private static final int FLAG_AVAILABLE_OFFLINE = 1;
  
  private static final int FLAG_HINT_DISPLAY_INLINE = 4;
  
  private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
  
  private static final String KEY_CANCEL_LABEL = "cancelLabel";
  
  private static final String KEY_CONFIRM_LABEL = "confirmLabel";
  
  private static final String KEY_FLAGS = "flags";
  
  private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
  
  private CharSequence mCancelLabel;
  
  private CharSequence mConfirmLabel;
  
  private int mFlags = 1;
  
  private CharSequence mInProgressLabel;
  
  public WearableExtender() {}
  
  public WearableExtender(Notification.Action paramAction) {
    Bundle bundle = paramAction.getExtras().getBundle("android.wearable.EXTENSIONS");
    if (bundle != null) {
      this.mFlags = bundle.getInt("flags", 1);
      this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
      this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
      this.mCancelLabel = bundle.getCharSequence("cancelLabel");
    } 
  }
  
  private void setFlag(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= paramInt;
    } else {
      this.mFlags &= paramInt;
    } 
  }
  
  public WearableExtender clone() {
    WearableExtender wearableExtender = new WearableExtender();
    wearableExtender.mFlags = this.mFlags;
    wearableExtender.mInProgressLabel = this.mInProgressLabel;
    wearableExtender.mConfirmLabel = this.mConfirmLabel;
    wearableExtender.mCancelLabel = this.mCancelLabel;
    return wearableExtender;
  }
  
  public Notification.Action.Builder extend(Notification.Action.Builder paramBuilder) {
    Bundle bundle = new Bundle();
    int i = this.mFlags;
    if (i != 1)
      bundle.putInt("flags", i); 
    CharSequence charSequence = this.mInProgressLabel;
    if (charSequence != null)
      bundle.putCharSequence("inProgressLabel", charSequence); 
    charSequence = this.mConfirmLabel;
    if (charSequence != null)
      bundle.putCharSequence("confirmLabel", charSequence); 
    charSequence = this.mCancelLabel;
    if (charSequence != null)
      bundle.putCharSequence("cancelLabel", charSequence); 
    paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
    return paramBuilder;
  }
  
  @Deprecated
  public CharSequence getCancelLabel() {
    return this.mCancelLabel;
  }
  
  @Deprecated
  public CharSequence getConfirmLabel() {
    return this.mConfirmLabel;
  }
  
  public boolean getHintDisplayActionInline() {
    boolean bool;
    if ((this.mFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean getHintLaunchesActivity() {
    boolean bool;
    if ((this.mFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public CharSequence getInProgressLabel() {
    return this.mInProgressLabel;
  }
  
  public boolean isAvailableOffline() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public WearableExtender setAvailableOffline(boolean paramBoolean) {
    setFlag(1, paramBoolean);
    return this;
  }
  
  @Deprecated
  public WearableExtender setCancelLabel(CharSequence paramCharSequence) {
    this.mCancelLabel = paramCharSequence;
    return this;
  }
  
  @Deprecated
  public WearableExtender setConfirmLabel(CharSequence paramCharSequence) {
    this.mConfirmLabel = paramCharSequence;
    return this;
  }
  
  public WearableExtender setHintDisplayActionInline(boolean paramBoolean) {
    setFlag(4, paramBoolean);
    return this;
  }
  
  public WearableExtender setHintLaunchesActivity(boolean paramBoolean) {
    setFlag(2, paramBoolean);
    return this;
  }
  
  @Deprecated
  public WearableExtender setInProgressLabel(CharSequence paramCharSequence) {
    this.mInProgressLabel = paramCharSequence;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$Action$WearableExtender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */