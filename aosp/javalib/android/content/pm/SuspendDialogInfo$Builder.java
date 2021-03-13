package android.content.pm;

import android.content.res.ResourceId;
import com.android.internal.util.Preconditions;

public final class Builder {
  private String mDialogMessage;
  
  private int mDialogMessageResId = 0;
  
  private int mIconResId = 0;
  
  private int mNeutralButtonAction = 0;
  
  private int mNeutralButtonTextResId = 0;
  
  private int mTitleResId = 0;
  
  public SuspendDialogInfo build() {
    return new SuspendDialogInfo(this);
  }
  
  public Builder setIcon(int paramInt) {
    Preconditions.checkArgument(ResourceId.isValid(paramInt), "Invalid resource id provided");
    this.mIconResId = paramInt;
    return this;
  }
  
  public Builder setMessage(int paramInt) {
    Preconditions.checkArgument(ResourceId.isValid(paramInt), "Invalid resource id provided");
    this.mDialogMessageResId = paramInt;
    return this;
  }
  
  public Builder setMessage(String paramString) {
    Preconditions.checkStringNotEmpty(paramString, "Message cannot be null or empty");
    this.mDialogMessage = paramString;
    return this;
  }
  
  public Builder setNeutralButtonAction(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 0)
      if (paramInt == 1) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    Preconditions.checkArgument(bool2, "Invalid button action");
    this.mNeutralButtonAction = paramInt;
    return this;
  }
  
  public Builder setNeutralButtonText(int paramInt) {
    Preconditions.checkArgument(ResourceId.isValid(paramInt), "Invalid resource id provided");
    this.mNeutralButtonTextResId = paramInt;
    return this;
  }
  
  public Builder setTitle(int paramInt) {
    Preconditions.checkArgument(ResourceId.isValid(paramInt), "Invalid resource id provided");
    this.mTitleResId = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SuspendDialogInfo$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */