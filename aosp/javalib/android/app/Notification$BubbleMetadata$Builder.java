package android.app;

import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.Log;

public final class Builder {
  private PendingIntent mDeleteIntent;
  
  private int mDesiredHeight;
  
  private int mDesiredHeightResId;
  
  private int mFlags;
  
  private Icon mIcon;
  
  private PendingIntent mPendingIntent;
  
  private String mShortcutId;
  
  @Deprecated
  public Builder() {}
  
  public Builder(PendingIntent paramPendingIntent, Icon paramIcon) {
    if (paramPendingIntent != null) {
      if (paramIcon != null) {
        if (paramIcon.getType() != 6 && paramIcon.getType() != 4)
          Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
        this.mPendingIntent = paramPendingIntent;
        this.mIcon = paramIcon;
        return;
      } 
      throw new NullPointerException("Bubbles require non-null icon");
    } 
    throw new NullPointerException("Bubble requires non-null pending intent");
  }
  
  public Builder(String paramString) {
    if (!TextUtils.isEmpty(paramString)) {
      this.mShortcutId = paramString;
      return;
    } 
    throw new NullPointerException("Bubble requires a non-null shortcut id");
  }
  
  public Notification.BubbleMetadata build() {
    if (this.mShortcutId != null || this.mPendingIntent != null) {
      if (this.mShortcutId != null || this.mIcon != null) {
        Notification.BubbleMetadata bubbleMetadata = new Notification.BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mShortcutId, null);
        bubbleMetadata.setFlags(this.mFlags);
        return bubbleMetadata;
      } 
      throw new NullPointerException("Must supply an icon or shortcut for the bubble");
    } 
    throw new NullPointerException("Must supply pending intent or shortcut to bubble");
  }
  
  @Deprecated
  public Builder createIntentBubble(PendingIntent paramPendingIntent, Icon paramIcon) {
    if (paramPendingIntent != null) {
      if (paramIcon != null) {
        if (paramIcon.getType() != 6 && paramIcon.getType() != 4)
          Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
        this.mShortcutId = null;
        this.mPendingIntent = paramPendingIntent;
        this.mIcon = paramIcon;
        return this;
      } 
      throw new IllegalArgumentException("Bubbles require non-null icon");
    } 
    throw new IllegalArgumentException("Bubble requires non-null pending intent");
  }
  
  @Deprecated
  public Builder createShortcutBubble(String paramString) {
    if (!TextUtils.isEmpty(paramString)) {
      this.mPendingIntent = null;
      this.mIcon = null;
    } 
    this.mShortcutId = paramString;
    return this;
  }
  
  public Builder setAutoExpandBubble(boolean paramBoolean) {
    setFlag(1, paramBoolean);
    return this;
  }
  
  public Builder setDeleteIntent(PendingIntent paramPendingIntent) {
    this.mDeleteIntent = paramPendingIntent;
    return this;
  }
  
  public Builder setDesiredHeight(int paramInt) {
    this.mDesiredHeight = Math.max(paramInt, 0);
    this.mDesiredHeightResId = 0;
    return this;
  }
  
  public Builder setDesiredHeightResId(int paramInt) {
    this.mDesiredHeightResId = paramInt;
    this.mDesiredHeight = 0;
    return this;
  }
  
  public Builder setFlag(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= paramInt;
    } else {
      this.mFlags &= paramInt;
    } 
    return this;
  }
  
  public Builder setIcon(Icon paramIcon) {
    if (this.mShortcutId == null) {
      if (paramIcon != null) {
        if (paramIcon.getType() != 6 && paramIcon.getType() != 4)
          Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
        this.mIcon = paramIcon;
        return this;
      } 
      throw new NullPointerException("Bubbles require non-null icon");
    } 
    throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
  }
  
  public Builder setIntent(PendingIntent paramPendingIntent) {
    if (this.mShortcutId == null) {
      if (paramPendingIntent != null) {
        this.mPendingIntent = paramPendingIntent;
        return this;
      } 
      throw new NullPointerException("Bubble requires non-null pending intent");
    } 
    throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
  }
  
  public Builder setSuppressNotification(boolean paramBoolean) {
    setFlag(2, paramBoolean);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$BubbleMetadata$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */