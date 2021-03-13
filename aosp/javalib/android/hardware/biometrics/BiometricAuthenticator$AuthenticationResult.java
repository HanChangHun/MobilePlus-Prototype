package android.hardware.biometrics;

public class AuthenticationResult {
  private int mAuthenticationType;
  
  private CryptoObject mCryptoObject;
  
  private BiometricAuthenticator.Identifier mIdentifier;
  
  private int mUserId;
  
  public AuthenticationResult() {}
  
  public AuthenticationResult(CryptoObject paramCryptoObject, int paramInt1, BiometricAuthenticator.Identifier paramIdentifier, int paramInt2) {
    this.mCryptoObject = paramCryptoObject;
    this.mAuthenticationType = paramInt1;
    this.mIdentifier = paramIdentifier;
    this.mUserId = paramInt2;
  }
  
  public int getAuthenticationType() {
    return this.mAuthenticationType;
  }
  
  public CryptoObject getCryptoObject() {
    return this.mCryptoObject;
  }
  
  public BiometricAuthenticator.Identifier getId() {
    return this.mIdentifier;
  }
  
  public int getUserId() {
    return this.mUserId;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricAuthenticator$AuthenticationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */