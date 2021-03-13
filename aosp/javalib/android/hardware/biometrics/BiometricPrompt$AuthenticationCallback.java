package android.hardware.biometrics;

public abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
  public void onAuthenticationAcquired(int paramInt) {}
  
  public void onAuthenticationError(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationFailed() {}
  
  public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult paramAuthenticationResult) {}
  
  public void onSystemEvent(int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$AuthenticationCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */