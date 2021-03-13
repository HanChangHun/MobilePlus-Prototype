package android.hardware.biometrics;

public class AuthenticationResult extends BiometricAuthenticator.AuthenticationResult {
  public AuthenticationResult(BiometricPrompt.CryptoObject paramCryptoObject, int paramInt) {
    super(paramCryptoObject, paramInt, null, 0);
  }
  
  public int getAuthenticationType() {
    return super.getAuthenticationType();
  }
  
  public BiometricPrompt.CryptoObject getCryptoObject() {
    return (BiometricPrompt.CryptoObject)super.getCryptoObject();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$AuthenticationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */