package android.hardware.biometrics;

import android.security.identity.IdentityCredential;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public final class CryptoObject extends CryptoObject {
  public CryptoObject(IdentityCredential paramIdentityCredential) {
    super(paramIdentityCredential);
  }
  
  public CryptoObject(Signature paramSignature) {
    super(paramSignature);
  }
  
  public CryptoObject(Cipher paramCipher) {
    super(paramCipher);
  }
  
  public CryptoObject(Mac paramMac) {
    super(paramMac);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricPrompt$CryptoObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */