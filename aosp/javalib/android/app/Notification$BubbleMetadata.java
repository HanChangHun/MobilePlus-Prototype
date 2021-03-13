package android.app;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

public final class BubbleMetadata implements Parcelable {
  public static final Parcelable.Creator<BubbleMetadata> CREATOR = new Parcelable.Creator<BubbleMetadata>() {
      public Notification.BubbleMetadata createFromParcel(Parcel param2Parcel) {
        return new Notification.BubbleMetadata(param2Parcel);
      }
      
      public Notification.BubbleMetadata[] newArray(int param2Int) {
        return new Notification.BubbleMetadata[param2Int];
      }
    };
  
  public static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
  
  public static final int FLAG_SUPPRESS_NOTIFICATION = 2;
  
  private PendingIntent mDeleteIntent;
  
  private int mDesiredHeight;
  
  private int mDesiredHeightResId;
  
  private int mFlags;
  
  private Icon mIcon;
  
  private PendingIntent mPendingIntent;
  
  private String mShortcutId;
  
  private BubbleMetadata(PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, Icon paramIcon, int paramInt1, int paramInt2, String paramString) {
    this.mPendingIntent = paramPendingIntent1;
    this.mIcon = paramIcon;
    this.mDesiredHeight = paramInt1;
    this.mDesiredHeightResId = paramInt2;
    this.mDeleteIntent = paramPendingIntent2;
    this.mShortcutId = paramString;
  }
  
  private BubbleMetadata(Parcel paramParcel) {
    if (paramParcel.readInt() != 0)
      this.mPendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.mIcon = (Icon)Icon.CREATOR.createFromParcel(paramParcel); 
    this.mDesiredHeight = paramParcel.readInt();
    this.mFlags = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.mDeleteIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel); 
    this.mDesiredHeightResId = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.mShortcutId = paramParcel.readString8(); 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean getAutoExpandBubble() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  @Deprecated
  public Icon getBubbleIcon() {
    return this.mIcon;
  }
  
  @Deprecated
  public PendingIntent getBubbleIntent() {
    return this.mPendingIntent;
  }
  
  public PendingIntent getDeleteIntent() {
    return this.mDeleteIntent;
  }
  
  public int getDesiredHeight() {
    return this.mDesiredHeight;
  }
  
  public int getDesiredHeightResId() {
    return this.mDesiredHeightResId;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public Icon getIcon() {
    return this.mIcon;
  }
  
  public PendingIntent getIntent() {
    return this.mPendingIntent;
  }
  
  public String getShortcutId() {
    return this.mShortcutId;
  }
  
  public boolean isNotificationSuppressed() {
    boolean bool;
    if ((this.mFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setFlags(int paramInt) {
    this.mFlags = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mPendingIntent != null) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    paramParcel.writeInt(paramInt);
    PendingIntent pendingIntent2 = this.mPendingIntent;
    if (pendingIntent2 != null)
      pendingIntent2.writeToParcel(paramParcel, 0); 
    if (this.mIcon != null) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    paramParcel.writeInt(paramInt);
    Icon icon = this.mIcon;
    if (icon != null)
      icon.writeToParcel(paramParcel, 0); 
    paramParcel.writeInt(this.mDesiredHeight);
    paramParcel.writeInt(this.mFlags);
    if (this.mDeleteIntent != null) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    paramParcel.writeInt(paramInt);
    PendingIntent pendingIntent1 = this.mDeleteIntent;
    if (pendingIntent1 != null)
      pendingIntent1.writeToParcel(paramParcel, 0); 
    paramParcel.writeInt(this.mDesiredHeightResId);
    paramParcel.writeInt(TextUtils.isEmpty(this.mShortcutId) ^ true);
    if (!TextUtils.isEmpty(this.mShortcutId))
      paramParcel.writeString8(this.mShortcutId); 
  }
  
  public static final class Builder {
    private PendingIntent mDeleteIntent;
    
    private int mDesiredHeight;
    
    private int mDesiredHeightResId;
    
    private int mFlags;
    
    private Icon mIcon;
    
    private PendingIntent mPendingIntent;
    
    private String mShortcutId;
    
    @Deprecated
    public Builder() {}
    
    public Builder(PendingIntent param2PendingIntent, Icon param2Icon) {
      if (param2PendingIntent != null) {
        if (param2Icon != null) {
          if (param2Icon.getType() != 6 && param2Icon.getType() != 4)
            Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
          this.mPendingIntent = param2PendingIntent;
          this.mIcon = param2Icon;
          return;
        } 
        throw new NullPointerException("Bubbles require non-null icon");
      } 
      throw new NullPointerException("Bubble requires non-null pending intent");
    }
    
    public Builder(String param2String) {
      if (!TextUtils.isEmpty(param2String)) {
        this.mShortcutId = param2String;
        return;
      } 
      throw new NullPointerException("Bubble requires a non-null shortcut id");
    }
    
    public Notification.BubbleMetadata build() {
      if (this.mShortcutId != null || this.mPendingIntent != null) {
        if (this.mShortcutId != null || this.mIcon != null) {
          Notification.BubbleMetadata bubbleMetadata = new Notification.BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mShortcutId);
          bubbleMetadata.setFlags(this.mFlags);
          return bubbleMetadata;
        } 
        throw new NullPointerException("Must supply an icon or shortcut for the bubble");
      } 
      throw new NullPointerException("Must supply pending intent or shortcut to bubble");
    }
    
    @Deprecated
    public Builder createIntentBubble(PendingIntent param2PendingIntent, Icon param2Icon) {
      if (param2PendingIntent != null) {
        if (param2Icon != null) {
          if (param2Icon.getType() != 6 && param2Icon.getType() != 4)
            Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
          this.mShortcutId = null;
          this.mPendingIntent = param2PendingIntent;
          this.mIcon = param2Icon;
          return this;
        } 
        throw new IllegalArgumentException("Bubbles require non-null icon");
      } 
      throw new IllegalArgumentException("Bubble requires non-null pending intent");
    }
    
    @Deprecated
    public Builder createShortcutBubble(String param2String) {
      if (!TextUtils.isEmpty(param2String)) {
        this.mPendingIntent = null;
        this.mIcon = null;
      } 
      this.mShortcutId = param2String;
      return this;
    }
    
    public Builder setAutoExpandBubble(boolean param2Boolean) {
      setFlag(1, param2Boolean);
      return this;
    }
    
    public Builder setDeleteIntent(PendingIntent param2PendingIntent) {
      this.mDeleteIntent = param2PendingIntent;
      return this;
    }
    
    public Builder setDesiredHeight(int param2Int) {
      this.mDesiredHeight = Math.max(param2Int, 0);
      this.mDesiredHeightResId = 0;
      return this;
    }
    
    public Builder setDesiredHeightResId(int param2Int) {
      this.mDesiredHeightResId = param2Int;
      this.mDesiredHeight = 0;
      return this;
    }
    
    public Builder setFlag(int param2Int, boolean param2Boolean) {
      if (param2Boolean) {
        this.mFlags |= param2Int;
      } else {
        this.mFlags &= param2Int;
      } 
      return this;
    }
    
    public Builder setIcon(Icon param2Icon) {
      if (this.mShortcutId == null) {
        if (param2Icon != null) {
          if (param2Icon.getType() != 6 && param2Icon.getType() != 4)
            Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
          this.mIcon = param2Icon;
          return this;
        } 
        throw new NullPointerException("Bubbles require non-null icon");
      } 
      throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
    }
    
    public Builder setIntent(PendingIntent param2PendingIntent) {
      if (this.mShortcutId == null) {
        if (param2PendingIntent != null) {
          this.mPendingIntent = param2PendingIntent;
          return this;
        } 
        throw new NullPointerException("Bubble requires non-null pending intent");
      } 
      throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
    }
    
    public Builder setSuppressNotification(boolean param2Boolean) {
      setFlag(2, param2Boolean);
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$BubbleMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */