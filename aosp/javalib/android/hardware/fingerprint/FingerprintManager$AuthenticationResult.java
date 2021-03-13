package android.hardware.fingerprint;

@Deprecated
public class AuthenticationResult {
  private FingerprintManager.CryptoObject mCryptoObject;
  
  private Fingerprint mFingerprint;
  
  private boolean mIsStrongBiometric;
  
  private int mUserId;
  
  public AuthenticationResult(FingerprintManager.CryptoObject paramCryptoObject, Fingerprint paramFingerprint, int paramInt, boolean paramBoolean) {
    this.mCryptoObject = paramCryptoObject;
    this.mFingerprint = paramFingerprint;
    this.mUserId = paramInt;
    this.mIsStrongBiometric = paramBoolean;
  }
  
  public FingerprintManager.CryptoObject getCryptoObject() {
    return this.mCryptoObject;
  }
  
  public Fingerprint getFingerprint() {
    return this.mFingerprint;
  }
  
  public int getUserId() {
    return this.mUserId;
  }
  
  public boolean isStrongBiometric() {
    return this.mIsStrongBiometric;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$AuthenticationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */