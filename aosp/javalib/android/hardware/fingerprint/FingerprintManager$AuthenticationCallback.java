package android.hardware.fingerprint;

import android.hardware.biometrics.BiometricAuthenticator;

@Deprecated
public abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
  public void onAuthenticationAcquired(int paramInt) {}
  
  public void onAuthenticationError(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationFailed() {}
  
  public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult paramAuthenticationResult) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$AuthenticationCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */