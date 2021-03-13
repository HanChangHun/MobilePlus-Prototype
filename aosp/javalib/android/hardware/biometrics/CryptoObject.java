package android.hardware.biometrics;

import android.security.identity.IdentityCredential;
import android.security.keystore.AndroidKeyStoreProvider;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public class CryptoObject {
  private final Object mCrypto;
  
  public CryptoObject(IdentityCredential paramIdentityCredential) {
    this.mCrypto = paramIdentityCredential;
  }
  
  public CryptoObject(Signature paramSignature) {
    this.mCrypto = paramSignature;
  }
  
  public CryptoObject(Cipher paramCipher) {
    this.mCrypto = paramCipher;
  }
  
  public CryptoObject(Mac paramMac) {
    this.mCrypto = paramMac;
  }
  
  public Cipher getCipher() {
    Object object = this.mCrypto;
    if (object instanceof Cipher) {
      object = object;
    } else {
      object = null;
    } 
    return (Cipher)object;
  }
  
  public IdentityCredential getIdentityCredential() {
    Object object = this.mCrypto;
    if (object instanceof IdentityCredential) {
      object = object;
    } else {
      object = null;
    } 
    return (IdentityCredential)object;
  }
  
  public Mac getMac() {
    Object object = this.mCrypto;
    if (object instanceof Mac) {
      object = object;
    } else {
      object = null;
    } 
    return (Mac)object;
  }
  
  public final long getOpId() {
    Object object = this.mCrypto;
    return (object == null) ? 0L : ((object instanceof IdentityCredential) ? ((IdentityCredential)object).getCredstoreOperationHandle() : AndroidKeyStoreProvider.getKeyStoreOperationHandle(object));
  }
  
  public Signature getSignature() {
    Object object = this.mCrypto;
    if (object instanceof Signature) {
      object = object;
    } else {
      object = null;
    } 
    return (Signature)object;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/CryptoObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */