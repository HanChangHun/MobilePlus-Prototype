package android.hardware.biometrics;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.concurrent.Executor;

public class Builder {
  private final Bundle mBundle = new Bundle();
  
  private Context mContext;
  
  private BiometricPrompt.ButtonInfo mNegativeButtonInfo;
  
  private BiometricPrompt.ButtonInfo mPositiveButtonInfo;
  
  public Builder(Context paramContext) {
    this.mContext = paramContext;
  }
  
  public BiometricPrompt build() {
    CharSequence charSequence1 = this.mBundle.getCharSequence("title");
    CharSequence charSequence2 = this.mBundle.getCharSequence("negative_text");
    Bundle bundle = this.mBundle;
    boolean bool = false;
    boolean bool1 = bundle.getBoolean("use_default_title", false);
    boolean bool2 = this.mBundle.getBoolean("allow_device_credential");
    int i = this.mBundle.getInt("authenticators_allowed", 0);
    if (bool2 || (0x8000 & i) != 0)
      bool = true; 
    if (!TextUtils.isEmpty(charSequence1) || bool1) {
      if (!TextUtils.isEmpty(charSequence2) || bool) {
        if (TextUtils.isEmpty(charSequence2) || !bool)
          return new BiometricPrompt(this.mContext, this.mBundle, this.mPositiveButtonInfo, this.mNegativeButtonInfo, null); 
        throw new IllegalArgumentException("Can't have both negative button behavior and device credential enabled");
      } 
      throw new IllegalArgumentException("Negative text must be set and non-empty");
    } 
    throw new IllegalArgumentException("Title must be set and non-empty");
  }
  
  public Builder setAllowedAuthenticators(int paramInt) {
    this.mBundle.putInt("authenticators_allowed", paramInt);
    return this;
  }
  
  public Builder setConfirmationRequired(boolean paramBoolean) {
    this.mBundle.putBoolean("require_confirmation", paramBoolean);
    return this;
  }
  
  public Builder setDescription(CharSequence paramCharSequence) {
    this.mBundle.putCharSequence("description", paramCharSequence);
    return this;
  }
  
  @Deprecated
  public Builder setDeviceCredentialAllowed(boolean paramBoolean) {
    this.mBundle.putBoolean("allow_device_credential", paramBoolean);
    return this;
  }
  
  public Builder setDisallowBiometricsIfPolicyExists(boolean paramBoolean) {
    this.mBundle.putBoolean("check_dpm", paramBoolean);
    return this;
  }
  
  public Builder setNegativeButton(CharSequence paramCharSequence, Executor paramExecutor, DialogInterface.OnClickListener paramOnClickListener) {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      if (paramExecutor != null) {
        if (paramOnClickListener != null) {
          this.mBundle.putCharSequence("negative_text", paramCharSequence);
          this.mNegativeButtonInfo = new BiometricPrompt.ButtonInfo(paramExecutor, paramOnClickListener);
          return this;
        } 
        throw new IllegalArgumentException("Listener must not be null");
      } 
      throw new IllegalArgumentException("Executor must not be null");
    } 
    throw new IllegalArgumentException("Text must be set and non-empty");
  }
  
  public Builder setReceiveSystemEvents(boolean paramBoolean) {
    this.mBundle.putBoolean("receive_system_events", paramBoolean);
    return this;
  }
  
  public Builder setSubtitle(CharSequence paramCharSequence) {
    this.mBundle.putCharSequence("subtitle", paramCharSequence);
    return this;
  }
  
  public Builder setTextForDeviceCredential(CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3) {
    if (paramCharSequence1 != null)
      this.mBundle.putCharSequence("device_credential_title", paramCharSequence1); 
    if (paramCharSequence2 != null)
      this.mBundle.putCharSequence("device_credential_subtitle", paramCharSequence2); 
    if (paramCharSequence3 != null)
      this.mBundle.putCharSequence("device_credential_description", paramCharSequence3); 
    return this;
  }
  
  public Builder setTitle(CharSequence paramCharSequence) {
    this.mBundle.putCharSequence("title", paramCharSequence);
    return this;
  }
  
  public Builder setUseDefaultTitle() {
    this.mBundle.putBoolean("use_default_title", true);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */