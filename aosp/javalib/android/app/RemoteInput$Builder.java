package android.app;

import android.os.Bundle;
import android.util.ArraySet;

public final class Builder {
  private final ArraySet<String> mAllowedDataTypes = new ArraySet();
  
  private CharSequence[] mChoices;
  
  private int mEditChoicesBeforeSending = 0;
  
  private final Bundle mExtras = new Bundle();
  
  private int mFlags = 1;
  
  private CharSequence mLabel;
  
  private final String mResultKey;
  
  public Builder(String paramString) {
    if (paramString != null) {
      this.mResultKey = paramString;
      return;
    } 
    throw new IllegalArgumentException("Result key can't be null");
  }
  
  private void setFlag(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= paramInt;
    } else {
      this.mFlags &= paramInt;
    } 
  }
  
  public Builder addExtras(Bundle paramBundle) {
    if (paramBundle != null)
      this.mExtras.putAll(paramBundle); 
    return this;
  }
  
  public RemoteInput build() {
    return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mFlags, this.mEditChoicesBeforeSending, this.mExtras, this.mAllowedDataTypes, null);
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public Builder setAllowDataType(String paramString, boolean paramBoolean) {
    if (paramBoolean) {
      this.mAllowedDataTypes.add(paramString);
    } else {
      this.mAllowedDataTypes.remove(paramString);
    } 
    return this;
  }
  
  public Builder setAllowFreeFormInput(boolean paramBoolean) {
    setFlag(1, paramBoolean);
    return this;
  }
  
  public Builder setChoices(CharSequence[] paramArrayOfCharSequence) {
    if (paramArrayOfCharSequence == null) {
      this.mChoices = null;
    } else {
      this.mChoices = new CharSequence[paramArrayOfCharSequence.length];
      for (byte b = 0; b < paramArrayOfCharSequence.length; b++)
        this.mChoices[b] = Notification.safeCharSequence(paramArrayOfCharSequence[b]); 
    } 
    return this;
  }
  
  public Builder setEditChoicesBeforeSending(int paramInt) {
    this.mEditChoicesBeforeSending = paramInt;
    return this;
  }
  
  public Builder setLabel(CharSequence paramCharSequence) {
    this.mLabel = Notification.safeCharSequence(paramCharSequence);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteInput$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */