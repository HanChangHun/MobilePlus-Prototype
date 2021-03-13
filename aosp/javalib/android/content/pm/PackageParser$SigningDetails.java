package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.PackageUtils;
import android.util.Slog;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import libcore.util.HexEncoding;

public final class SigningDetails implements Parcelable {
  public static final Parcelable.Creator<SigningDetails> CREATOR;
  
  private static final int PAST_CERT_EXISTS = 0;
  
  public static final SigningDetails UNKNOWN = new SigningDetails(null, 0, null, null);
  
  public final Signature[] pastSigningCertificates;
  
  public final ArraySet<PublicKey> publicKeys;
  
  public final int signatureSchemeVersion;
  
  public final Signature[] signatures;
  
  static {
    CREATOR = new Parcelable.Creator<SigningDetails>() {
        public PackageParser.SigningDetails createFromParcel(Parcel param2Parcel) {
          return param2Parcel.readBoolean() ? PackageParser.SigningDetails.UNKNOWN : new PackageParser.SigningDetails(param2Parcel);
        }
        
        public PackageParser.SigningDetails[] newArray(int param2Int) {
          return new PackageParser.SigningDetails[param2Int];
        }
      };
  }
  
  public SigningDetails(SigningDetails paramSigningDetails) {
    if (paramSigningDetails != null) {
      Signature[] arrayOfSignature2 = paramSigningDetails.signatures;
      if (arrayOfSignature2 != null) {
        this.signatures = (Signature[])arrayOfSignature2.clone();
      } else {
        this.signatures = null;
      } 
      this.signatureSchemeVersion = paramSigningDetails.signatureSchemeVersion;
      this.publicKeys = new ArraySet(paramSigningDetails.publicKeys);
      Signature[] arrayOfSignature1 = paramSigningDetails.pastSigningCertificates;
      if (arrayOfSignature1 != null) {
        this.pastSigningCertificates = (Signature[])arrayOfSignature1.clone();
      } else {
        this.pastSigningCertificates = null;
      } 
    } else {
      this.signatures = null;
      this.signatureSchemeVersion = 0;
      this.publicKeys = null;
      this.pastSigningCertificates = null;
    } 
  }
  
  protected SigningDetails(Parcel paramParcel) {
    ClassLoader classLoader = Object.class.getClassLoader();
    this.signatures = (Signature[])paramParcel.createTypedArray(Signature.CREATOR);
    this.signatureSchemeVersion = paramParcel.readInt();
    this.publicKeys = paramParcel.readArraySet(classLoader);
    this.pastSigningCertificates = (Signature[])paramParcel.createTypedArray(Signature.CREATOR);
  }
  
  public SigningDetails(Signature[] paramArrayOfSignature, int paramInt) throws CertificateException {
    this(paramArrayOfSignature, paramInt, null);
  }
  
  public SigningDetails(Signature[] paramArrayOfSignature1, int paramInt, ArraySet<PublicKey> paramArraySet, Signature[] paramArrayOfSignature2) {
    this.signatures = paramArrayOfSignature1;
    this.signatureSchemeVersion = paramInt;
    this.publicKeys = paramArraySet;
    this.pastSigningCertificates = paramArrayOfSignature2;
  }
  
  public SigningDetails(Signature[] paramArrayOfSignature1, int paramInt, Signature[] paramArrayOfSignature2) throws CertificateException {
    this(paramArrayOfSignature1, paramInt, PackageParser.toSigningKeys(paramArrayOfSignature1), paramArrayOfSignature2);
  }
  
  private SigningDetails getDescendantOrSelf(SigningDetails paramSigningDetails) {
    SigningDetails signingDetails;
    int m;
    if (hasAncestorOrSelf(paramSigningDetails)) {
      signingDetails = this;
    } else if (paramSigningDetails.hasAncestor(this)) {
      signingDetails = paramSigningDetails;
      paramSigningDetails = this;
    } else {
      return null;
    } 
    int i = signingDetails.pastSigningCertificates.length - 1;
    int j = paramSigningDetails.pastSigningCertificates.length - 1;
    while (i >= 0 && !signingDetails.pastSigningCertificates[i].equals(paramSigningDetails.pastSigningCertificates[j]))
      i--; 
    int k = i;
    if (i < 0)
      return null; 
    while (true) {
      i = k - 1;
      m = j - 1;
      if (i >= 0 && m >= 0) {
        k = i;
        j = m;
        if (!signingDetails.pastSigningCertificates[i].equals(paramSigningDetails.pastSigningCertificates[m]))
          break; 
        continue;
      } 
      break;
    } 
    return (i >= 0 && m >= 0) ? null : signingDetails;
  }
  
  private boolean hasCertificateInternal(Signature paramSignature, int paramInt) {
    SigningDetails signingDetails = UNKNOWN;
    boolean bool1 = false;
    if (this == signingDetails)
      return false; 
    if (hasPastSigningCertificates()) {
      byte b = 0;
      while (true) {
        Signature[] arrayOfSignature1 = this.pastSigningCertificates;
        if (b < arrayOfSignature1.length - 1) {
          if (arrayOfSignature1[b].equals(paramSignature) && (paramInt == 0 || (this.pastSigningCertificates[b].getFlags() & paramInt) == paramInt))
            return true; 
          b++;
          continue;
        } 
        break;
      } 
    } 
    Signature[] arrayOfSignature = this.signatures;
    boolean bool2 = bool1;
    if (arrayOfSignature.length == 1) {
      bool2 = bool1;
      if (arrayOfSignature[0].equals(paramSignature))
        bool2 = true; 
    } 
    return bool2;
  }
  
  private boolean hasSha256CertificateInternal(byte[] paramArrayOfbyte, int paramInt) {
    if (this == UNKNOWN)
      return false; 
    if (hasPastSigningCertificates()) {
      byte b = 0;
      while (true) {
        Signature[] arrayOfSignature1 = this.pastSigningCertificates;
        if (b < arrayOfSignature1.length - 1) {
          if (Arrays.equals(paramArrayOfbyte, PackageUtils.computeSha256DigestBytes(arrayOfSignature1[b].toByteArray())) && (paramInt == 0 || (this.pastSigningCertificates[b].getFlags() & paramInt) == paramInt))
            return true; 
          b++;
          continue;
        } 
        break;
      } 
    } 
    Signature[] arrayOfSignature = this.signatures;
    return (arrayOfSignature.length == 1) ? Arrays.equals(paramArrayOfbyte, PackageUtils.computeSha256DigestBytes(arrayOfSignature[0].toByteArray())) : false;
  }
  
  private SigningDetails mergeLineageWithAncestorOrSelf(SigningDetails paramSigningDetails) {
    int n;
    int i = this.pastSigningCertificates.length - 1;
    int j = paramSigningDetails.pastSigningCertificates.length - 1;
    if (i < 0 || j < 0)
      return this; 
    ArrayList<Signature> arrayList = new ArrayList();
    int k = 0;
    while (i >= 0 && !this.pastSigningCertificates[i].equals(paramSigningDetails.pastSigningCertificates[j])) {
      arrayList.add(new Signature(this.pastSigningCertificates[i]));
      i--;
    } 
    int m = i;
    if (i < 0)
      return this; 
    while (true) {
      Signature[] arrayOfSignature1 = this.pastSigningCertificates;
      n = m - 1;
      Signature signature1 = arrayOfSignature1[m];
      Signature[] arrayOfSignature2 = paramSigningDetails.pastSigningCertificates;
      m = j - 1;
      Signature signature3 = arrayOfSignature2[j];
      Signature signature2 = new Signature(signature1);
      j = signature1.getFlags() & signature3.getFlags();
      i = k;
      if (signature1.getFlags() != j) {
        i = 1;
        signature2.setFlags(j);
      } 
      arrayList.add(signature2);
      if (n < 0 || m < 0 || !this.pastSigningCertificates[n].equals(paramSigningDetails.pastSigningCertificates[m]))
        break; 
      j = m;
      m = n;
      k = i;
    } 
    j = m;
    if (n >= 0) {
      j = m;
      if (m >= 0)
        return this; 
    } 
    while (true) {
      m = n;
      if (j >= 0) {
        arrayList.add(new Signature(paramSigningDetails.pastSigningCertificates[j]));
        j--;
        continue;
      } 
      break;
    } 
    while (m >= 0) {
      arrayList.add(new Signature(this.pastSigningCertificates[m]));
      m--;
    } 
    if (arrayList.size() == this.pastSigningCertificates.length && i == 0)
      return this; 
    Collections.reverse(arrayList);
    try {
      Signature signature = new Signature();
      this(this.signatures[0]);
      i = this.signatureSchemeVersion;
      Signature[] arrayOfSignature = arrayList.<Signature>toArray(new Signature[0]);
      return new SigningDetails(new Signature[] { signature }, i, arrayOfSignature);
    } catch (CertificateException certificateException) {
      Slog.e("PackageParser", "Caught an exception creating the merged lineage: ", certificateException);
      return this;
    } 
  }
  
  public boolean checkCapability(SigningDetails paramSigningDetails, int paramInt) {
    SigningDetails signingDetails = UNKNOWN;
    if (this == signingDetails || paramSigningDetails == signingDetails)
      return false; 
    Signature[] arrayOfSignature = paramSigningDetails.signatures;
    return (arrayOfSignature.length > 1) ? signaturesMatchExactly(paramSigningDetails) : hasCertificate(arrayOfSignature[0], paramInt);
  }
  
  public boolean checkCapability(String paramString, int paramInt) {
    byte[] arrayOfByte;
    if (this == UNKNOWN)
      return false; 
    if (paramString == null) {
      arrayOfByte = null;
    } else {
      arrayOfByte = HexEncoding.decode(paramString, false);
    } 
    if (hasSha256Certificate(arrayOfByte, paramInt))
      return true; 
    String[] arrayOfString = PackageUtils.computeSignaturesSha256Digests(this.signatures);
    return PackageUtils.computeSignaturesSha256Digest(arrayOfString).equals(paramString);
  }
  
  public boolean checkCapabilityRecover(SigningDetails paramSigningDetails, int paramInt) throws CertificateException {
    SigningDetails signingDetails = UNKNOWN;
    if (paramSigningDetails == signingDetails || this == signingDetails)
      return false; 
    if (hasPastSigningCertificates() && paramSigningDetails.signatures.length == 1) {
      byte b = 0;
      while (true) {
        Signature[] arrayOfSignature = this.pastSigningCertificates;
        if (b < arrayOfSignature.length) {
          if (Signature.areEffectiveMatch(paramSigningDetails.signatures[0], arrayOfSignature[b]) && this.pastSigningCertificates[b].getFlags() == paramInt)
            return true; 
          b++;
          continue;
        } 
        return false;
      } 
    } 
    return Signature.areEffectiveMatch(paramSigningDetails.signatures, this.signatures);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof SigningDetails))
      return false; 
    paramObject = paramObject;
    if (this.signatureSchemeVersion != ((SigningDetails)paramObject).signatureSchemeVersion)
      return false; 
    if (!Signature.areExactMatch(this.signatures, ((SigningDetails)paramObject).signatures))
      return false; 
    ArraySet<PublicKey> arraySet = this.publicKeys;
    if (arraySet != null) {
      if (!arraySet.equals(((SigningDetails)paramObject).publicKeys))
        return false; 
    } else if (((SigningDetails)paramObject).publicKeys != null) {
      return false;
    } 
    if (!Arrays.equals((Object[])this.pastSigningCertificates, (Object[])((SigningDetails)paramObject).pastSigningCertificates))
      return false; 
    byte b = 0;
    while (true) {
      Signature[] arrayOfSignature = this.pastSigningCertificates;
      if (b < arrayOfSignature.length) {
        if (arrayOfSignature[b].getFlags() != ((SigningDetails)paramObject).pastSigningCertificates[b].getFlags())
          return false; 
        b++;
        continue;
      } 
      return true;
    } 
  }
  
  public boolean hasAncestor(SigningDetails paramSigningDetails) {
    SigningDetails signingDetails = UNKNOWN;
    if (this == signingDetails || paramSigningDetails == signingDetails)
      return false; 
    if (hasPastSigningCertificates() && paramSigningDetails.signatures.length == 1) {
      byte b = 0;
      while (true) {
        Signature[] arrayOfSignature = this.pastSigningCertificates;
        if (b < arrayOfSignature.length - 1) {
          if (arrayOfSignature[b].equals(paramSigningDetails.signatures[0]))
            return true; 
          b++;
          continue;
        } 
        break;
      } 
    } 
    return false;
  }
  
  public boolean hasAncestorOrSelf(SigningDetails paramSigningDetails) {
    SigningDetails signingDetails = UNKNOWN;
    if (this == signingDetails || paramSigningDetails == signingDetails)
      return false; 
    Signature[] arrayOfSignature = paramSigningDetails.signatures;
    return (arrayOfSignature.length > 1) ? signaturesMatchExactly(paramSigningDetails) : hasCertificate(arrayOfSignature[0]);
  }
  
  public boolean hasCertificate(Signature paramSignature) {
    return hasCertificateInternal(paramSignature, 0);
  }
  
  public boolean hasCertificate(Signature paramSignature, int paramInt) {
    return hasCertificateInternal(paramSignature, paramInt);
  }
  
  public boolean hasCertificate(byte[] paramArrayOfbyte) {
    return hasCertificate(new Signature(paramArrayOfbyte));
  }
  
  public boolean hasCommonAncestor(SigningDetails paramSigningDetails) {
    boolean bool;
    if (!hasPastSigningCertificates())
      return paramSigningDetails.hasAncestorOrSelf(this); 
    if (!paramSigningDetails.hasPastSigningCertificates())
      return hasAncestorOrSelf(paramSigningDetails); 
    if (getDescendantOrSelf(paramSigningDetails) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasPastSigningCertificates() {
    boolean bool;
    Signature[] arrayOfSignature = this.pastSigningCertificates;
    if (arrayOfSignature != null && arrayOfSignature.length > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasSha256Certificate(byte[] paramArrayOfbyte) {
    return hasSha256CertificateInternal(paramArrayOfbyte, 0);
  }
  
  public boolean hasSha256Certificate(byte[] paramArrayOfbyte, int paramInt) {
    return hasSha256CertificateInternal(paramArrayOfbyte, paramInt);
  }
  
  public boolean hasSignatures() {
    boolean bool;
    Signature[] arrayOfSignature = this.signatures;
    if (arrayOfSignature != null && arrayOfSignature.length > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    byte b;
    int i = Arrays.hashCode((Object[])this.signatures);
    int j = this.signatureSchemeVersion;
    ArraySet<PublicKey> arraySet = this.publicKeys;
    if (arraySet != null) {
      b = arraySet.hashCode();
    } else {
      b = 0;
    } 
    return ((i * 31 + j) * 31 + b) * 31 + Arrays.hashCode((Object[])this.pastSigningCertificates);
  }
  
  public SigningDetails mergeLineageWith(SigningDetails paramSigningDetails) {
    if (!hasPastSigningCertificates()) {
      if (!paramSigningDetails.hasPastSigningCertificates() || !paramSigningDetails.hasAncestorOrSelf(this))
        paramSigningDetails = this; 
      return paramSigningDetails;
    } 
    if (!paramSigningDetails.hasPastSigningCertificates())
      return this; 
    SigningDetails signingDetails = getDescendantOrSelf(paramSigningDetails);
    if (signingDetails == null)
      return this; 
    if (signingDetails == this) {
      paramSigningDetails = mergeLineageWithAncestorOrSelf(paramSigningDetails);
    } else {
      paramSigningDetails = paramSigningDetails.mergeLineageWithAncestorOrSelf(this);
    } 
    return paramSigningDetails;
  }
  
  public boolean signaturesMatchExactly(SigningDetails paramSigningDetails) {
    return Signature.areExactMatch(this.signatures, paramSigningDetails.signatures);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    boolean bool;
    if (UNKNOWN == this) {
      bool = true;
    } else {
      bool = false;
    } 
    paramParcel.writeBoolean(bool);
    if (bool)
      return; 
    paramParcel.writeTypedArray((Parcelable[])this.signatures, paramInt);
    paramParcel.writeInt(this.signatureSchemeVersion);
    paramParcel.writeArraySet(this.publicKeys);
    paramParcel.writeTypedArray((Parcelable[])this.pastSigningCertificates, paramInt);
  }
  
  public static class Builder {
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
    
    public Builder setPastSigningCertificates(Signature[] param2ArrayOfSignature) {
      this.mPastSigningCertificates = param2ArrayOfSignature;
      return this;
    }
    
    public Builder setSignatureSchemeVersion(int param2Int) {
      this.mSignatureSchemeVersion = param2Int;
      return this;
    }
    
    public Builder setSignatures(Signature[] param2ArrayOfSignature) {
      this.mSignatures = param2ArrayOfSignature;
      return this;
    }
  }
  
  public static @interface CertCapabilities {
    public static final int AUTH = 16;
    
    public static final int INSTALLED_DATA = 1;
    
    public static final int PERMISSION = 4;
    
    public static final int ROLLBACK = 8;
    
    public static final int SHARED_USER_ID = 2;
  }
  
  public static @interface SignatureSchemeVersion {
    public static final int JAR = 1;
    
    public static final int SIGNING_BLOCK_V2 = 2;
    
    public static final int SIGNING_BLOCK_V3 = 3;
    
    public static final int SIGNING_BLOCK_V4 = 4;
    
    public static final int UNKNOWN = 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$SigningDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */