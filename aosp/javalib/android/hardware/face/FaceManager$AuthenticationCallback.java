package android.hardware.face;

import android.hardware.biometrics.BiometricAuthenticator;

public abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
  public void onAuthenticationAcquired(int paramInt) {}
  
  public void onAuthenticationError(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationFailed() {}
  
  public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence) {}
  
  public void onAuthenticationSucceeded(FaceManager.AuthenticationResult paramAuthenticationResult) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$AuthenticationCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */