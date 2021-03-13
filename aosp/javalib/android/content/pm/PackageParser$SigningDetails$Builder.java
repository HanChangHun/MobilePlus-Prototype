package android.content.pm;

import java.security.cert.CertificateException;

public class Builder {
  private Signature[] mPastSigningCertificates;
  
  private int mSignatureSchemeVersion = 0;
  
  private Signature[] mSignatures;
  
  private void checkInvariants() {
    if (this.mSignatures != null)
      return; 
    throw new IllegalStateException("SigningDetails requires the current signing certificates.");
  }
  
  public PackageParser.SigningDetails build() throws CertificateException {
    checkInvariants();
    return new PackageParser.SigningDetails(this.mSignatures, this.mSignatureSchemeVersion, this.mPastSigningCertificates);
  }
  
  public Builder setPastSigningCertificates(Signature[] paramArrayOfSignature) {
    this.mPastSigningCertificates = paramArrayOfSignature;
    return this;
  }
  
  public Builder setSignatureSchemeVersion(int paramInt) {
    this.mSignatureSchemeVersion = paramInt;
    return this;
  }
  
  public Builder setSignatures(Signature[] paramArrayOfSignature) {
    this.mSignatures = paramArrayOfSignature;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$SigningDetails$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */