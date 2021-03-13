package android.content.pm;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SystemApi
public final class InstantAppResolveInfo implements Parcelable {
  public static final Parcelable.Creator<InstantAppResolveInfo> CREATOR;
  
  private static final byte[] EMPTY_DIGEST = new byte[0];
  
  private static final String SHA_ALGORITHM = "SHA-256";
  
  private final InstantAppDigest mDigest;
  
  private final Bundle mExtras;
  
  private final List<InstantAppIntentFilter> mFilters;
  
  private final String mPackageName;
  
  private final boolean mShouldLetInstallerDecide;
  
  private final long mVersionCode;
  
  static {
    CREATOR = new Parcelable.Creator<InstantAppResolveInfo>() {
        public InstantAppResolveInfo createFromParcel(Parcel param1Parcel) {
          return new InstantAppResolveInfo(param1Parcel);
        }
        
        public InstantAppResolveInfo[] newArray(int param1Int) {
          return new InstantAppResolveInfo[param1Int];
        }
      };
  }
  
  public InstantAppResolveInfo(InstantAppDigest paramInstantAppDigest, String paramString, List<InstantAppIntentFilter> paramList, int paramInt) {
    this(paramInstantAppDigest, paramString, paramList, paramInt, null);
  }
  
  public InstantAppResolveInfo(InstantAppDigest paramInstantAppDigest, String paramString, List<InstantAppIntentFilter> paramList, long paramLong, Bundle paramBundle) {
    this(paramInstantAppDigest, paramString, paramList, paramLong, paramBundle, false);
  }
  
  private InstantAppResolveInfo(InstantAppDigest paramInstantAppDigest, String paramString, List<InstantAppIntentFilter> paramList, long paramLong, Bundle paramBundle, boolean paramBoolean) {
    if ((paramString != null || paramList == null || paramList.size() == 0) && (paramString == null || (paramList != null && paramList.size() != 0))) {
      this.mDigest = paramInstantAppDigest;
      if (paramList != null) {
        ArrayList<InstantAppIntentFilter> arrayList = new ArrayList(paramList.size());
        this.mFilters = arrayList;
        arrayList.addAll(paramList);
      } else {
        this.mFilters = null;
      } 
      this.mPackageName = paramString;
      this.mVersionCode = paramLong;
      this.mExtras = paramBundle;
      this.mShouldLetInstallerDecide = paramBoolean;
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  public InstantAppResolveInfo(Bundle paramBundle) {
    this(InstantAppDigest.UNDEFINED, null, null, -1L, paramBundle, true);
  }
  
  InstantAppResolveInfo(Parcel paramParcel) {
    this.mShouldLetInstallerDecide = paramParcel.readBoolean();
    this.mExtras = paramParcel.readBundle();
    if (this.mShouldLetInstallerDecide) {
      this.mDigest = InstantAppDigest.UNDEFINED;
      this.mPackageName = null;
      this.mFilters = Collections.emptyList();
      this.mVersionCode = -1L;
    } else {
      this.mDigest = (InstantAppDigest)paramParcel.readParcelable(null);
      this.mPackageName = paramParcel.readString();
      ArrayList<InstantAppIntentFilter> arrayList = new ArrayList();
      this.mFilters = arrayList;
      paramParcel.readList(arrayList, null);
      this.mVersionCode = paramParcel.readLong();
    } 
  }
  
  public InstantAppResolveInfo(String paramString1, String paramString2, List<InstantAppIntentFilter> paramList) {
    this(new InstantAppDigest(paramString1), paramString2, paramList, -1L, null);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getDigestBytes() {
    byte[] arrayOfByte;
    if (this.mDigest.mDigestBytes.length > 0) {
      arrayOfByte = this.mDigest.getDigestBytes()[0];
    } else {
      arrayOfByte = EMPTY_DIGEST;
    } 
    return arrayOfByte;
  }
  
  public int getDigestPrefix() {
    return this.mDigest.getDigestPrefix()[0];
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public List<InstantAppIntentFilter> getIntentFilters() {
    return this.mFilters;
  }
  
  public long getLongVersionCode() {
    return this.mVersionCode;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  @Deprecated
  public int getVersionCode() {
    return (int)(this.mVersionCode & 0xFFFFFFFFFFFFFFFFL);
  }
  
  public boolean shouldLetInstallerDecide() {
    return this.mShouldLetInstallerDecide;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBoolean(this.mShouldLetInstallerDecide);
    paramParcel.writeBundle(this.mExtras);
    if (this.mShouldLetInstallerDecide)
      return; 
    paramParcel.writeParcelable(this.mDigest, paramInt);
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeList(this.mFilters);
    paramParcel.writeLong(this.mVersionCode);
  }
  
  @SystemApi
  public static final class InstantAppDigest implements Parcelable {
    public static final Parcelable.Creator<InstantAppDigest> CREATOR = new Parcelable.Creator<InstantAppDigest>() {
        public InstantAppResolveInfo.InstantAppDigest createFromParcel(Parcel param2Parcel) {
          return param2Parcel.readBoolean() ? InstantAppResolveInfo.InstantAppDigest.UNDEFINED : new InstantAppResolveInfo.InstantAppDigest(param2Parcel);
        }
        
        public InstantAppResolveInfo.InstantAppDigest[] newArray(int param2Int) {
          return new InstantAppResolveInfo.InstantAppDigest[param2Int];
        }
      };
    
    static final int DIGEST_MASK = -4096;
    
    public static final InstantAppDigest UNDEFINED = new InstantAppDigest(new byte[0][], new int[0]);
    
    private static Random sRandom = null;
    
    private final byte[][] mDigestBytes;
    
    private final int[] mDigestPrefix;
    
    private int[] mDigestPrefixSecure;
    
    static {
    
    }
    
    InstantAppDigest(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      if (i == -1) {
        this.mDigestBytes = null;
      } else {
        this.mDigestBytes = new byte[i][];
        for (byte b = 0; b < i; b++)
          this.mDigestBytes[b] = param1Parcel.createByteArray(); 
      } 
      this.mDigestPrefix = param1Parcel.createIntArray();
      this.mDigestPrefixSecure = param1Parcel.createIntArray();
    }
    
    public InstantAppDigest(String param1String) {
      this(param1String, -1);
    }
    
    public InstantAppDigest(String param1String, int param1Int) {
      if (param1String != null) {
        byte[][] arrayOfByte = generateDigest(param1String.toLowerCase(Locale.ENGLISH), param1Int);
        this.mDigestBytes = arrayOfByte;
        this.mDigestPrefix = new int[arrayOfByte.length];
        param1Int = 0;
        while (true) {
          byte[][] arrayOfByte1 = this.mDigestBytes;
          if (param1Int < arrayOfByte1.length) {
            int[] arrayOfInt = this.mDigestPrefix;
            byte b1 = arrayOfByte1[param1Int][0];
            byte b2 = arrayOfByte1[param1Int][1];
            byte b3 = arrayOfByte1[param1Int][2];
            arrayOfInt[param1Int] = ((arrayOfByte1[param1Int][3] & 0xFF) << 0 | (b1 & 0xFF) << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8) & 0xFFFFF000;
            param1Int++;
            continue;
          } 
          break;
        } 
        return;
      } 
      throw new IllegalArgumentException();
    }
    
    private InstantAppDigest(byte[][] param1ArrayOfbyte, int[] param1ArrayOfint) {
      this.mDigestPrefix = param1ArrayOfint;
      this.mDigestBytes = param1ArrayOfbyte;
    }
    
    private static byte[][] generateDigest(String param1String, int param1Int) {
      ArrayList<byte[]> arrayList = new ArrayList();
      try {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        if (param1Int <= 0) {
          arrayList.add(messageDigest.digest(param1String.getBytes()));
        } else {
          int i = param1String.lastIndexOf('.', param1String.lastIndexOf('.') - 1);
          if (i < 0) {
            arrayList.add(messageDigest.digest(param1String.getBytes()));
          } else {
            arrayList.add(messageDigest.digest(param1String.substring(i + 1, param1String.length()).getBytes()));
            for (byte b = 1; i >= 0 && b < param1Int; b++) {
              i = param1String.lastIndexOf('.', i - 1);
              arrayList.add(messageDigest.digest(param1String.substring(i + 1, param1String.length()).getBytes()));
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
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      boolean bool;
      if (this == UNDEFINED) {
        bool = true;
      } else {
        bool = false;
      } 
      param1Parcel.writeBoolean(bool);
      if (bool)
        return; 
      byte[][] arrayOfByte = this.mDigestBytes;
      if (arrayOfByte == null) {
        param1Parcel.writeInt(-1);
      } else {
        param1Parcel.writeInt(arrayOfByte.length);
        param1Int = 0;
        while (true) {
          arrayOfByte = this.mDigestBytes;
          if (param1Int < arrayOfByte.length) {
            param1Parcel.writeByteArray(arrayOfByte[param1Int]);
            param1Int++;
            continue;
          } 
          break;
        } 
      } 
      param1Parcel.writeIntArray(this.mDigestPrefix);
      param1Parcel.writeIntArray(this.mDigestPrefixSecure);
    }
  }
  
  class null implements Parcelable.Creator<InstantAppDigest> {
    public InstantAppResolveInfo.InstantAppDigest createFromParcel(Parcel param1Parcel) {
      return param1Parcel.readBoolean() ? InstantAppResolveInfo.InstantAppDigest.UNDEFINED : new InstantAppResolveInfo.InstantAppDigest(param1Parcel);
    }
    
    public InstantAppResolveInfo.InstantAppDigest[] newArray(int param1Int) {
      return new InstantAppResolveInfo.InstantAppDigest[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppResolveInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */