package android.hardware.biometrics;

public abstract class AuthenticationCallback {
  public void onAuthenticationAcquired(int paramInt) {}
  
  public void onAuthenticationError(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationFailed() {}
  
  public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricAuthenticator$AuthenticationCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */