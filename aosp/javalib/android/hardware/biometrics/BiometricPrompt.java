package android.hardware.biometrics;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.security.identity.IdentityCredential;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.Signature;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public class BiometricPrompt implements BiometricAuthenticator, BiometricConstants {
  public static final int AUTHENTICATION_RESULT_TYPE_BIOMETRIC = 2;
  
  public static final int AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL = 1;
  
  public static final int DISMISSED_REASON_BIOMETRIC_CONFIRMED = 1;
  
  public static final int DISMISSED_REASON_BIOMETRIC_CONFIRM_NOT_REQUIRED = 4;
  
  public static final int DISMISSED_REASON_CREDENTIAL_CONFIRMED = 7;
  
  public static final int DISMISSED_REASON_ERROR = 5;
  
  public static final int DISMISSED_REASON_NEGATIVE = 2;
  
  public static final int DISMISSED_REASON_SERVER_REQUESTED = 6;
  
  public static final int DISMISSED_REASON_USER_CANCEL = 3;
  
  public static final String EXTRA_DISALLOW_BIOMETRICS_IF_POLICY_EXISTS = "check_dpm";
  
  public static final int HIDE_DIALOG_DELAY = 2000;
  
  public static final String KEY_ALLOW_DEVICE_CREDENTIAL = "allow_device_credential";
  
  public static final String KEY_AUTHENTICATORS_ALLOWED = "authenticators_allowed";
  
  public static final String KEY_DESCRIPTION = "description";
  
  public static final String KEY_DEVICE_CREDENTIAL_DESCRIPTION = "device_credential_description";
  
  public static final String KEY_DEVICE_CREDENTIAL_SUBTITLE = "device_credential_subtitle";
  
  public static final String KEY_DEVICE_CREDENTIAL_TITLE = "device_credential_title";
  
  public static final String KEY_NEGATIVE_TEXT = "negative_text";
  
  public static final String KEY_RECEIVE_SYSTEM_EVENTS = "receive_system_events";
  
  public static final String KEY_REQUIRE_CONFIRMATION = "require_confirmation";
  
  public static final String KEY_SUBTITLE = "subtitle";
  
  public static final String KEY_TITLE = "title";
  
  public static final String KEY_USE_DEFAULT_TITLE = "use_default_title";
  
  private static final String TAG = "BiometricPrompt";
  
  private AuthenticationCallback mAuthenticationCallback;
  
  private final IBiometricServiceReceiver mBiometricServiceReceiver = new IBiometricServiceReceiver.Stub() {
      public void onAcquired(int param1Int, String param1String) throws RemoteException {
        BiometricPrompt.this.mExecutor.execute(new _$$Lambda$BiometricPrompt$1$yfG83rs6eJM9CDMAlhftsvdKekY(this, param1Int, param1String));
      }
      
      public void onAuthenticationFailed() throws RemoteException {
        BiometricPrompt.this.mExecutor.execute(new _$$Lambda$BiometricPrompt$1$AAMJr_dQQ3dkiYxppvTx2AjuvRQ(this));
      }
      
      public void onAuthenticationSucceeded(int param1Int) throws RemoteException {
        BiometricPrompt.this.mExecutor.execute(new _$$Lambda$BiometricPrompt$1$12VeET6QSFQbES1tShxA0kvzReo(this, param1Int));
      }
      
      public void onDialogDismissed(int param1Int) throws RemoteException {
        if (param1Int == 1) {
          BiometricPrompt.this.mPositiveButtonInfo.executor.execute(new _$$Lambda$BiometricPrompt$1$Kmc1otRcCm0Akw6_6yK5trqAv78(this));
        } else if (param1Int == 2) {
          BiometricPrompt.this.mNegativeButtonInfo.executor.execute(new _$$Lambda$BiometricPrompt$1$G8c_A1luxVwjcfGpdSp4nNPnavM(this));
        } 
      }
      
      public void onError(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
        BiometricPrompt.this.mExecutor.execute(new _$$Lambda$BiometricPrompt$1$bOZpfrHjTYn0bHysdnjuVXTbZSk(this, param1Int1, param1Int2, param1Int3));
      }
      
      public void onSystemEvent(int param1Int) throws RemoteException {
        BiometricPrompt.this.mExecutor.execute(new _$$Lambda$BiometricPrompt$1$xoRi9oElCVJ5QflEmWoJGQ08mZ8(this, param1Int));
      }
    };
  
  private final Bundle mBundle;
  
  private final Context mContext;
  
  private CryptoObject mCryptoObject;
  
  private Executor mExecutor;
  
  private final ButtonInfo mNegativeButtonInfo;
  
  private final ButtonInfo mPositiveButtonInfo;
  
  private final IAuthService mService;
  
  private final IBinder mToken = (IBinder)new Binder();
  
  private BiometricPrompt(Context paramContext, Bundle paramBundle, ButtonInfo paramButtonInfo1, ButtonInfo paramButtonInfo2) {
    this.mContext = paramContext;
    this.mBundle = paramBundle;
    this.mPositiveButtonInfo = paramButtonInfo1;
    this.mNegativeButtonInfo = paramButtonInfo2;
    this.mService = IAuthService.Stub.asInterface(ServiceManager.getService("auth"));
  }
  
  private void authenticateInternal(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, Executor paramExecutor, AuthenticationCallback paramAuthenticationCallback, int paramInt) {
    try {
      if (paramCancellationSignal.isCanceled()) {
        Log.w("BiometricPrompt", "Authentication already canceled");
        return;
      } 
      OnAuthenticationCancelListener onAuthenticationCancelListener = new OnAuthenticationCancelListener();
      try {
        paramCancellationSignal.setOnCancelListener(onAuthenticationCancelListener);
        this.mCryptoObject = paramCryptoObject;
        try {
          Bundle bundle;
          long l;
          this.mExecutor = paramExecutor;
          this.mAuthenticationCallback = paramAuthenticationCallback;
          if (paramCryptoObject != null) {
            l = paramCryptoObject.getOpId();
          } else {
            l = 0L;
          } 
          if (paramCryptoObject != null) {
            bundle = new Bundle();
            this(this.mBundle);
            bundle.putInt("authenticators_allowed", this.mBundle.getInt("authenticators_allowed", 15));
          } else {
            bundle = this.mBundle;
          } 
          this.mService.authenticate(this.mToken, l, paramInt, this.mBiometricServiceReceiver, this.mContext.getOpPackageName(), bundle);
        } catch (RemoteException null) {}
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    Log.e("BiometricPrompt", "Remote exception while authenticating", (Throwable)remoteException);
    this.mExecutor.execute(new _$$Lambda$BiometricPrompt$Dk3E1C_ccte_BJOnzgPmi2l5r0I(this, paramAuthenticationCallback));
  }
  
  private void cancelAuthentication() {
    IAuthService iAuthService = this.mService;
    if (iAuthService != null)
      try {
        iAuthService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        Log.e("BiometricPrompt", "Unable to cancel authentication", (Throwable)remoteException);
      }  
  }
  
  public void authenticate(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, Executor paramExecutor, AuthenticationCallback paramAuthenticationCallback) {
    if (paramCryptoObject != null) {
      if (paramCancellationSignal != null) {
        if (paramExecutor != null) {
          if (paramAuthenticationCallback != null) {
            if ((this.mBundle.getInt("authenticators_allowed", 15) & 0xFF & 0xFFFFFFF0) == 0) {
              authenticateInternal(paramCryptoObject, paramCancellationSignal, paramExecutor, paramAuthenticationCallback, this.mContext.getUserId());
              return;
            } 
            throw new IllegalArgumentException("Only Strong biometrics supported with crypto");
          } 
          throw new IllegalArgumentException("Must supply a callback");
        } 
        throw new IllegalArgumentException("Must supply an executor");
      } 
      throw new IllegalArgumentException("Must supply a cancellation signal");
    } 
    throw new IllegalArgumentException("Must supply a crypto object");
  }
  
  public void authenticate(CancellationSignal paramCancellationSignal, Executor paramExecutor, AuthenticationCallback paramAuthenticationCallback) {
    if (paramCancellationSignal != null) {
      if (paramExecutor != null) {
        if (paramAuthenticationCallback != null) {
          authenticateInternal(null, paramCancellationSignal, paramExecutor, paramAuthenticationCallback, this.mContext.getUserId());
          return;
        } 
        throw new IllegalArgumentException("Must supply a callback");
      } 
      throw new IllegalArgumentException("Must supply an executor");
    } 
    throw new IllegalArgumentException("Must supply a cancellation signal");
  }
  
  public void authenticateUser(CancellationSignal paramCancellationSignal, Executor paramExecutor, AuthenticationCallback paramAuthenticationCallback, int paramInt) {
    if (paramCancellationSignal != null) {
      if (paramExecutor != null) {
        if (paramAuthenticationCallback != null) {
          authenticateInternal(null, paramCancellationSignal, paramExecutor, paramAuthenticationCallback, paramInt);
          return;
        } 
        throw new IllegalArgumentException("Must supply a callback");
      } 
      throw new IllegalArgumentException("Must supply an executor");
    } 
    throw new IllegalArgumentException("Must supply a cancellation signal");
  }
  
  public int getAllowedAuthenticators() {
    return this.mBundle.getInt("authenticators_allowed", 0);
  }
  
  public CharSequence getDescription() {
    return this.mBundle.getCharSequence("description");
  }
  
  public CharSequence getNegativeButtonText() {
    return this.mBundle.getCharSequence("negative_text");
  }
  
  public CharSequence getSubtitle() {
    return this.mBundle.getCharSequence("subtitle");
  }
  
  public CharSequence getTitle() {
    return this.mBundle.getCharSequence("title", "");
  }
  
  public boolean isConfirmationRequired() {
    return this.mBundle.getBoolean("require_confirmation", true);
  }
  
  public boolean shouldUseDefaultTitle() {
    return this.mBundle.getBoolean("use_default_title", false);
  }
  
  public static abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
    public void onAuthenticationAcquired(int param1Int) {}
    
    public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult param1AuthenticationResult) {}
    
    public void onSystemEvent(int param1Int) {}
  }
  
  public static class AuthenticationResult extends BiometricAuthenticator.AuthenticationResult {
    public AuthenticationResult(BiometricPrompt.CryptoObject param1CryptoObject, int param1Int) {
      super(param1CryptoObject, param1Int, null, 0);
    }
    
    public int getAuthenticationType() {
      return super.getAuthenticationType();
    }
    
    public BiometricPrompt.CryptoObject getCryptoObject() {
      return (BiometricPrompt.CryptoObject)super.getCryptoObject();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AuthenticationResultType {}
  
  public static class Builder {
    private final Bundle mBundle = new Bundle();
    
    private Context mContext;
    
    private BiometricPrompt.ButtonInfo mNegativeButtonInfo;
    
    private BiometricPrompt.ButtonInfo mPositiveButtonInfo;
    
    public Builder(Context param1Context) {
      this.mContext = param1Context;
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
            return new BiometricPrompt(this.mContext, this.mBundle, this.mPositiveButtonInfo, this.mNegativeButtonInfo); 
          throw new IllegalArgumentException("Can't have both negative button behavior and device credential enabled");
        } 
        throw new IllegalArgumentException("Negative text must be set and non-empty");
      } 
      throw new IllegalArgumentException("Title must be set and non-empty");
    }
    
    public Builder setAllowedAuthenticators(int param1Int) {
      this.mBundle.putInt("authenticators_allowed", param1Int);
      return this;
    }
    
    public Builder setConfirmationRequired(boolean param1Boolean) {
      this.mBundle.putBoolean("require_confirmation", param1Boolean);
      return this;
    }
    
    public Builder setDescription(CharSequence param1CharSequence) {
      this.mBundle.putCharSequence("description", param1CharSequence);
      return this;
    }
    
    @Deprecated
    public Builder setDeviceCredentialAllowed(boolean param1Boolean) {
      this.mBundle.putBoolean("allow_device_credential", param1Boolean);
      return this;
    }
    
    public Builder setDisallowBiometricsIfPolicyExists(boolean param1Boolean) {
      this.mBundle.putBoolean("check_dpm", param1Boolean);
      return this;
    }
    
    public Builder setNegativeButton(CharSequence param1CharSequence, Executor param1Executor, DialogInterface.OnClickListener param1OnClickListener) {
      if (!TextUtils.isEmpty(param1CharSequence)) {
        if (param1Executor != null) {
          if (param1OnClickListener != null) {
            this.mBundle.putCharSequence("negative_text", param1CharSequence);
            this.mNegativeButtonInfo = new BiometricPrompt.ButtonInfo(param1Executor, param1OnClickListener);
            return this;
          } 
          throw new IllegalArgumentException("Listener must not be null");
        } 
        throw new IllegalArgumentException("Executor must not be null");
      } 
      throw new IllegalArgumentException("Text must be set and non-empty");
    }
    
    public Builder setReceiveSystemEvents(boolean param1Boolean) {
      this.mBundle.putBoolean("receive_system_events", param1Boolean);
      return this;
    }
    
    public Builder setSubtitle(CharSequence param1CharSequence) {
      this.mBundle.putCharSequence("subtitle", param1CharSequence);
      return this;
    }
    
    public Builder setTextForDeviceCredential(CharSequence param1CharSequence1, CharSequence param1CharSequence2, CharSequence param1CharSequence3) {
      if (param1CharSequence1 != null)
        this.mBundle.putCharSequence("device_credential_title", param1CharSequence1); 
      if (param1CharSequence2 != null)
        this.mBundle.putCharSequence("device_credential_subtitle", param1CharSequence2); 
      if (param1CharSequence3 != null)
        this.mBundle.putCharSequence("device_credential_description", param1CharSequence3); 
      return this;
    }
    
    public Builder setTitle(CharSequence param1CharSequence) {
      this.mBundle.putCharSequence("title", param1CharSequence);
      return this;
    }
    
    public Builder setUseDefaultTitle() {
      this.mBundle.putBoolean("use_default_title", true);
      return this;
    }
  }
  
  private static class ButtonInfo {
    Executor executor;
    
    DialogInterface.OnClickListener listener;
    
    ButtonInfo(Executor param1Executor, DialogInterface.OnClickListener param1OnClickListener) {
      this.executor = param1Executor;
      this.listener = param1OnClickListener;
    }
  }
  
  public static final class CryptoObject extends CryptoObject {
    public CryptoObject(IdentityCredential param1IdentityCredential) {
      super(param1IdentityCredential);
    }
    
    public CryptoObject(Signature param1Signature) {
      super(param1Signature);
    }
    
    public CryptoObject(Cipher param1Cipher) {
      super(param1Cipher);
    }
    
    public CryptoObject(Mac param1Mac) {
      super(param1Mac);
    }
    
    public Cipher getCipher() {
      return super.getCipher();
    }
    
    public IdentityCredential getIdentityCredential() {
      return super.getIdentityCredential();
    }
    
    public Mac getMac() {
      return super.getMac();
    }
    
    public Signature getSignature() {
      return super.getSignature();
    }
  }
  
  private class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
    private OnAuthenticationCancelListener() {}
    
    public void onCancel() {
      BiometricPrompt.this.cancelAuthentication();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */