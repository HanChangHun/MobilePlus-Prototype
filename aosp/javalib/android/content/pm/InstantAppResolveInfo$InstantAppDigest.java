package android.content.pm;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

@SystemApi
public final class InstantAppDigest implements Parcelable {
  public static final Parcelable.Creator<InstantAppDigest> CREATOR;
  
  static final int DIGEST_MASK = -4096;
  
  public static final InstantAppDigest UNDEFINED = new InstantAppDigest(new byte[0][], new int[0]);
  
  private static Random sRandom = null;
  
  private final byte[][] mDigestBytes;
  
  private final int[] mDigestPrefix;
  
  private int[] mDigestPrefixSecure;
  
  static {
    try {
      sRandom = SecureRandom.getInstance("SHA1PRNG");
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      sRandom = new Random();
    } 
    CREATOR = new Parcelable.Creator<InstantAppDigest>() {
        public InstantAppResolveInfo.InstantAppDigest createFromParcel(Parcel param2Parcel) {
          return param2Parcel.readBoolean() ? InstantAppResolveInfo.InstantAppDigest.UNDEFINED : new InstantAppResolveInfo.InstantAppDigest(param2Parcel);
        }
        
        public InstantAppResolveInfo.InstantAppDigest[] newArray(int param2Int) {
          return new InstantAppResolveInfo.InstantAppDigest[param2Int];
        }
      };
  }
  
  InstantAppDigest(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i == -1) {
      this.mDigestBytes = null;
    } else {
      this.mDigestBytes = new byte[i][];
      for (byte b = 0; b < i; b++)
        this.mDigestBytes[b] = paramParcel.createByteArray(); 
    } 
    this.mDigestPrefix = paramParcel.createIntArray();
    this.mDigestPrefixSecure = paramParcel.createIntArray();
  }
  
  public InstantAppDigest(String paramString) {
    this(paramString, -1);
  }
  
  public InstantAppDigest(String paramString, int paramInt) {
    if (paramString != null) {
      byte[][] arrayOfByte = generateDigest(paramString.toLowerCase(Locale.ENGLISH), paramInt);
      this.mDigestBytes = arrayOfByte;
      this.mDigestPrefix = new int[arrayOfByte.length];
      paramInt = 0;
      while (true) {
        byte[][] arrayOfByte1 = this.mDigestBytes;
        if (paramInt < arrayOfByte1.length) {
          int[] arrayOfInt = this.mDigestPrefix;
          byte b1 = arrayOfByte1[paramInt][0];
          byte b2 = arrayOfByte1[paramInt][1];
          byte b3 = arrayOfByte1[paramInt][2];
          arrayOfInt[paramInt] = ((arrayOfByte1[paramInt][3] & 0xFF) << 0 | (b1 & 0xFF) << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8) & 0xFFFFF000;
          paramInt++;
          continue;
        } 
        break;
      } 
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  private InstantAppDigest(byte[][] paramArrayOfbyte, int[] paramArrayOfint) {
    this.mDigestPrefix = paramArrayOfint;
    this.mDigestBytes = paramArrayOfbyte;
  }
  
  private static byte[][] generateDigest(String paramString, int paramInt) {
    ArrayList<byte[]> arrayList = new ArrayList();
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      if (paramInt <= 0) {
        arrayList.add(messageDigest.digest(paramString.getBytes()));
      } else {
        int i = paramString.lastIndexOf('.', paramString.lastIndexOf('.') - 1);
        if (i < 0) {
          arrayList.add(messageDigest.digest(paramString.getBytes()));
        } else {
          arrayList.add(messageDigest.digest(paramString.substring(i + 1, paramString.length()).getBytes()));
          for (byte b = 1; i >= 0 && b < paramInt; b++) {
            i = paramString.lastIndexOf('.', i - 1);
            arrayList.add(messageDigest.digest(paramString.substring(i + 1, paramString.length()).getBytes()));
          } 
        } 
      } 
      return arrayList.<byte[]>toArray(new byte[arrayList.size()][]);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new IllegalStateException("could not find digest algorithm");
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[][] getDigestBytes() {
    return this.mDigestBytes;
  }
  
  public int[] getDigestPrefix() {
    return this.mDigestPrefix;
  }
  
  public int[] getDigestPrefixSecure() {
    if (this == UNDEFINED)
      return getDigestPrefix(); 
    if (this.mDigestPrefixSecure == null) {
      int i = (getDigestPrefix()).length;
      int j = i + 10 + sRandom.nextInt(10);
      this.mDigestPrefixSecure = Arrays.copyOf(getDigestPrefix(), j);
      while (i < j) {
        this.mDigestPrefixSecure[i] = sRandom.nextInt() & 0xFFFFF000;
        i++;
      } 
      Arrays.sort(this.mDigestPrefixSecure);
    } 
    return this.mDigestPrefixSecure;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    boolean bool;
    if (this == UNDEFINED) {
      bool = true;
    } else {
      bool = false;
    } 
    paramParcel.writeBoolean(bool);
    if (bool)
      return; 
    byte[][] arrayOfByte = this.mDigestBytes;
    if (arrayOfByte == null) {
      paramParcel.writeInt(-1);
    } else {
      paramParcel.writeInt(arrayOfByte.length);
      paramInt = 0;
      while (true) {
        arrayOfByte = this.mDigestBytes;
        if (paramInt < arrayOfByte.length) {
          paramParcel.writeByteArray(arrayOfByte[paramInt]);
          paramInt++;
          continue;
        } 
        break;
      } 
    } 
    paramParcel.writeIntArray(this.mDigestPrefix);
    paramParcel.writeIntArray(this.mDigestPrefixSecure);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppResolveInfo$InstantAppDigest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */