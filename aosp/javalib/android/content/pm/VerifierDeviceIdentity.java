package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

public class VerifierDeviceIdentity implements Parcelable {
  public static final Parcelable.Creator<VerifierDeviceIdentity> CREATOR;
  
  private static final char[] ENCODE = new char[] { 
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
      'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', 
      '6', '7' };
  
  private static final int GROUP_SIZE = 4;
  
  private static final int LONG_SIZE = 13;
  
  private static final char SEPARATOR = '-';
  
  private final long mIdentity;
  
  private final String mIdentityString;
  
  static {
    CREATOR = new Parcelable.Creator<VerifierDeviceIdentity>() {
        public VerifierDeviceIdentity createFromParcel(Parcel param1Parcel) {
          return new VerifierDeviceIdentity(param1Parcel);
        }
        
        public VerifierDeviceIdentity[] newArray(int param1Int) {
          return new VerifierDeviceIdentity[param1Int];
        }
      };
  }
  
  public VerifierDeviceIdentity(long paramLong) {
    this.mIdentity = paramLong;
    this.mIdentityString = encodeBase32(paramLong);
  }
  
  private VerifierDeviceIdentity(Parcel paramParcel) {
    long l = paramParcel.readLong();
    this.mIdentity = l;
    this.mIdentityString = encodeBase32(l);
  }
  
  private static final long decodeBase32(byte[] paramArrayOfbyte) throws IllegalArgumentException {
    long l = 0L;
    byte b1 = 0;
    int i = paramArrayOfbyte.length;
    for (byte b2 = 0; b2 < i; b2++) {
      byte b = paramArrayOfbyte[b2];
      if (65 <= b && b <= 90) {
        b -= 65;
      } else if (50 <= b && b <= 55) {
        b -= 24;
      } else {
        if (b == 45)
          continue; 
        if (97 <= b && b <= 122) {
          b -= 97;
        } else if (b == 48) {
          b = 14;
        } else if (b == 49) {
          b = 8;
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("base base-32 character: ");
          stringBuilder.append(b);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } 
      l = l << 5L | b;
      if (++b1 == 1) {
        if ((b & 0xF) != b)
          throw new IllegalArgumentException("illegal start character; will overflow"); 
      } else if (b1 > 13) {
        throw new IllegalArgumentException("too long; should have 13 characters");
      } 
      continue;
    } 
    if (b1 == 13)
      return l; 
    throw new IllegalArgumentException("too short; should have 13 characters");
  }
  
  private static final String encodeBase32(long paramLong) {
    char[] arrayOfChar1 = ENCODE;
    char[] arrayOfChar2 = new char[16];
    int i = arrayOfChar2.length;
    for (byte b = 0; b < 13; b++) {
      int j = i;
      if (b > 0) {
        j = i;
        if (b % 4 == 1) {
          j = i - 1;
          arrayOfChar2[j] = (char)'-';
        } 
      } 
      int k = (int)(0x1FL & paramLong);
      paramLong >>>= 5L;
      i = j - 1;
      arrayOfChar2[i] = (char)arrayOfChar1[k];
    } 
    return String.valueOf(arrayOfChar2);
  }
  
  public static VerifierDeviceIdentity generate() {
    return generate(new SecureRandom());
  }
  
  static VerifierDeviceIdentity generate(Random paramRandom) {
    return new VerifierDeviceIdentity(paramRandom.nextLong());
  }
  
  public static VerifierDeviceIdentity parse(String paramString) throws IllegalArgumentException {
    try {
      byte[] arrayOfByte = paramString.getBytes("US-ASCII");
      return new VerifierDeviceIdentity(decodeBase32(arrayOfByte));
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new IllegalArgumentException("bad base-32 characters in input");
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof VerifierDeviceIdentity;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (this.mIdentity == ((VerifierDeviceIdentity)paramObject).mIdentity)
      bool1 = true; 
    return bool1;
  }
  
  public int hashCode() {
    return (int)this.mIdentity;
  }
  
  public String toString() {
    return this.mIdentityString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mIdentity);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/VerifierDeviceIdentity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */