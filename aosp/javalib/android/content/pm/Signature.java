package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.ArrayUtils;
import java.io.ByteArrayInputStream;
import java.lang.ref.SoftReference;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class Signature implements Parcelable {
  public static final Parcelable.Creator<Signature> CREATOR = new Parcelable.Creator<Signature>() {
      public Signature createFromParcel(Parcel param1Parcel) {
        return new Signature(param1Parcel);
      }
      
      public Signature[] newArray(int param1Int) {
        return new Signature[param1Int];
      }
    };
  
  private Certificate[] mCertificateChain;
  
  private int mFlags;
  
  private int mHashCode;
  
  private boolean mHaveHashCode;
  
  private final byte[] mSignature;
  
  private SoftReference<String> mStringRef;
  
  public Signature(Signature paramSignature) {
    this.mSignature = (byte[])paramSignature.mSignature.clone();
    Certificate[] arrayOfCertificate = paramSignature.mCertificateChain;
    if (arrayOfCertificate != null && arrayOfCertificate.length > 1)
      this.mCertificateChain = Arrays.<Certificate>copyOfRange(arrayOfCertificate, 1, arrayOfCertificate.length); 
    this.mFlags = paramSignature.mFlags;
  }
  
  private Signature(Parcel paramParcel) {
    this.mSignature = paramParcel.createByteArray();
  }
  
  public Signature(String paramString) {
    byte[] arrayOfByte = paramString.getBytes();
    int i = arrayOfByte.length;
    if (i % 2 == 0) {
      byte[] arrayOfByte1 = new byte[i / 2];
      byte b = 0;
      int j = 0;
      while (j < i) {
        int k = j + 1;
        arrayOfByte1[b] = (byte)(byte)(parseHexDigit(arrayOfByte[j]) << 4 | parseHexDigit(arrayOfByte[k]));
        j = k + 1;
        b++;
      } 
      this.mSignature = arrayOfByte1;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("text size ");
    stringBuilder.append(i);
    stringBuilder.append(" is not even");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Signature(byte[] paramArrayOfbyte) {
    this.mSignature = (byte[])paramArrayOfbyte.clone();
    this.mCertificateChain = null;
  }
  
  public Signature(Certificate[] paramArrayOfCertificate) throws CertificateEncodingException {
    this.mSignature = paramArrayOfCertificate[0].getEncoded();
    if (paramArrayOfCertificate.length > 1)
      this.mCertificateChain = Arrays.<Certificate>copyOfRange(paramArrayOfCertificate, 1, paramArrayOfCertificate.length); 
  }
  
  public static boolean areEffectiveMatch(Signature paramSignature1, Signature paramSignature2) throws CertificateException {
    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    return bounce(certificateFactory, paramSignature1).equals(bounce(certificateFactory, paramSignature2));
  }
  
  public static boolean areEffectiveMatch(Signature[] paramArrayOfSignature1, Signature[] paramArrayOfSignature2) throws CertificateException {
    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    Signature[] arrayOfSignature = new Signature[paramArrayOfSignature1.length];
    byte b;
    for (b = 0; b < paramArrayOfSignature1.length; b++)
      arrayOfSignature[b] = bounce(certificateFactory, paramArrayOfSignature1[b]); 
    paramArrayOfSignature1 = new Signature[paramArrayOfSignature2.length];
    for (b = 0; b < paramArrayOfSignature2.length; b++)
      paramArrayOfSignature1[b] = bounce(certificateFactory, paramArrayOfSignature2[b]); 
    return areExactMatch(arrayOfSignature, paramArrayOfSignature1);
  }
  
  public static boolean areExactMatch(Signature[] paramArrayOfSignature1, Signature[] paramArrayOfSignature2) {
    boolean bool;
    if (paramArrayOfSignature1.length == paramArrayOfSignature2.length && ArrayUtils.containsAll((Object[])paramArrayOfSignature1, (Object[])paramArrayOfSignature2) && ArrayUtils.containsAll((Object[])paramArrayOfSignature2, (Object[])paramArrayOfSignature1)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static Signature bounce(CertificateFactory paramCertificateFactory, Signature paramSignature) throws CertificateException {
    Signature signature = new Signature(((X509Certificate)paramCertificateFactory.generateCertificate(new ByteArrayInputStream(paramSignature.mSignature))).getEncoded());
    if (Math.abs(signature.mSignature.length - paramSignature.mSignature.length) <= 2)
      return signature; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Bounced cert length looks fishy; before ");
    stringBuilder.append(paramSignature.mSignature.length);
    stringBuilder.append(", after ");
    stringBuilder.append(signature.mSignature.length);
    throw new CertificateException(stringBuilder.toString());
  }
  
  private static final int parseHexDigit(int paramInt) {
    if (48 <= paramInt && paramInt <= 57)
      return paramInt - 48; 
    if (97 <= paramInt && paramInt <= 102)
      return paramInt - 97 + 10; 
    if (65 <= paramInt && paramInt <= 70)
      return paramInt - 65 + 10; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid character ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" in hex string");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject != null)
      try {
        paramObject = paramObject;
        if (this != paramObject) {
          boolean bool1 = Arrays.equals(this.mSignature, ((Signature)paramObject).mSignature);
          return bool1 ? true : bool;
        } 
        return true;
      } catch (ClassCastException classCastException) {} 
    return false;
  }
  
  public Signature[] getChainSignatures() throws CertificateEncodingException {
    Certificate[] arrayOfCertificate = this.mCertificateChain;
    byte b1 = 0;
    if (arrayOfCertificate == null)
      return new Signature[] { this }; 
    Signature[] arrayOfSignature = new Signature[arrayOfCertificate.length + 1];
    arrayOfSignature[0] = this;
    byte b2 = 1;
    int i = arrayOfCertificate.length;
    while (b1 < i) {
      arrayOfSignature[b2] = new Signature(arrayOfCertificate[b1].getEncoded());
      b1++;
      b2++;
    } 
    return arrayOfSignature;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public PublicKey getPublicKey() throws CertificateException {
    return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(this.mSignature)).getPublicKey();
  }
  
  public int hashCode() {
    if (this.mHaveHashCode)
      return this.mHashCode; 
    int i = Arrays.hashCode(this.mSignature);
    this.mHashCode = i;
    this.mHaveHashCode = true;
    return i;
  }
  
  public void setFlags(int paramInt) {
    this.mFlags = paramInt;
  }
  
  public byte[] toByteArray() {
    byte[] arrayOfByte1 = this.mSignature;
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    return arrayOfByte2;
  }
  
  public char[] toChars() {
    return toChars(null, null);
  }
  
  public char[] toChars(char[] paramArrayOfchar, int[] paramArrayOfint) {
    byte[] arrayOfByte = this.mSignature;
    int i = arrayOfByte.length;
    int j = i * 2;
    if (paramArrayOfchar == null || j > paramArrayOfchar.length)
      paramArrayOfchar = new char[j]; 
    for (j = 0; j < i; j++) {
      byte b = arrayOfByte[j];
      int k = b >> 4 & 0xF;
      if (k >= 10) {
        k = k + 97 - 10;
      } else {
        k += 48;
      } 
      paramArrayOfchar[j * 2] = (char)(char)k;
      k = b & 0xF;
      if (k >= 10) {
        k = k + 97 - 10;
      } else {
        k += 48;
      } 
      paramArrayOfchar[j * 2 + 1] = (char)(char)k;
    } 
    if (paramArrayOfint != null)
      paramArrayOfint[0] = i; 
    return paramArrayOfchar;
  }
  
  public String toCharsString() {
    SoftReference<String> softReference = this.mStringRef;
    if (softReference == null) {
      softReference = null;
    } else {
      str = softReference.get();
    } 
    if (str != null)
      return str; 
    String str = new String(toChars());
    this.mStringRef = new SoftReference<>(str);
    return str;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeByteArray(this.mSignature);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/Signature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */