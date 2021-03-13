package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import java.util.Objects;

@Deprecated
public class PackageStats implements Parcelable {
  public static final Parcelable.Creator<PackageStats> CREATOR = new Parcelable.Creator<PackageStats>() {
      public PackageStats createFromParcel(Parcel param1Parcel) {
        return new PackageStats(param1Parcel);
      }
      
      public PackageStats[] newArray(int param1Int) {
        return new PackageStats[param1Int];
      }
    };
  
  public long cacheSize;
  
  public long codeSize;
  
  public long dataSize;
  
  public long externalCacheSize;
  
  public long externalCodeSize;
  
  public long externalDataSize;
  
  public long externalMediaSize;
  
  public long externalObbSize;
  
  public String packageName;
  
  public int userHandle;
  
  public PackageStats(PackageStats paramPackageStats) {
    this.packageName = paramPackageStats.packageName;
    this.userHandle = paramPackageStats.userHandle;
    this.codeSize = paramPackageStats.codeSize;
    this.dataSize = paramPackageStats.dataSize;
    this.cacheSize = paramPackageStats.cacheSize;
    this.externalCodeSize = paramPackageStats.externalCodeSize;
    this.externalDataSize = paramPackageStats.externalDataSize;
    this.externalCacheSize = paramPackageStats.externalCacheSize;
    this.externalMediaSize = paramPackageStats.externalMediaSize;
    this.externalObbSize = paramPackageStats.externalObbSize;
  }
  
  public PackageStats(Parcel paramParcel) {
    this.packageName = paramParcel.readString();
    this.userHandle = paramParcel.readInt();
    this.codeSize = paramParcel.readLong();
    this.dataSize = paramParcel.readLong();
    this.cacheSize = paramParcel.readLong();
    this.externalCodeSize = paramParcel.readLong();
    this.externalDataSize = paramParcel.readLong();
    this.externalCacheSize = paramParcel.readLong();
    this.externalMediaSize = paramParcel.readLong();
    this.externalObbSize = paramParcel.readLong();
  }
  
  public PackageStats(String paramString) {
    this.packageName = paramString;
    this.userHandle = UserHandle.myUserId();
  }
  
  public PackageStats(String paramString, int paramInt) {
    this.packageName = paramString;
    this.userHandle = paramInt;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof PackageStats;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (TextUtils.equals(this.packageName, ((PackageStats)paramObject).packageName)) {
      bool = bool1;
      if (this.userHandle == ((PackageStats)paramObject).userHandle) {
        bool = bool1;
        if (this.codeSize == ((PackageStats)paramObject).codeSize) {
          bool = bool1;
          if (this.dataSize == ((PackageStats)paramObject).dataSize) {
            bool = bool1;
            if (this.cacheSize == ((PackageStats)paramObject).cacheSize) {
              bool = bool1;
              if (this.externalCodeSize == ((PackageStats)paramObject).externalCodeSize) {
                bool = bool1;
                if (this.externalDataSize == ((PackageStats)paramObject).externalDataSize) {
                  bool = bool1;
                  if (this.externalCacheSize == ((PackageStats)paramObject).externalCacheSize) {
                    bool = bool1;
                    if (this.externalMediaSize == ((PackageStats)paramObject).externalMediaSize) {
                      bool = bool1;
                      if (this.externalObbSize == ((PackageStats)paramObject).externalObbSize)
                        bool = true; 
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
    } 
    return bool;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.packageName, Integer.valueOf(this.userHandle), Long.valueOf(this.codeSize), Long.valueOf(this.dataSize), Long.valueOf(this.cacheSize), Long.valueOf(this.externalCodeSize), Long.valueOf(this.externalDataSize), Long.valueOf(this.externalCacheSize), Long.valueOf(this.externalMediaSize), Long.valueOf(this.externalObbSize) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("PackageStats{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    if (this.codeSize != 0L) {
      stringBuilder.append(" code=");
      stringBuilder.append(this.codeSize);
    } 
    if (this.dataSize != 0L) {
      stringBuilder.append(" data=");
      stringBuilder.append(this.dataSize);
    } 
    if (this.cacheSize != 0L) {
      stringBuilder.append(" cache=");
      stringBuilder.append(this.cacheSize);
    } 
    if (this.externalCodeSize != 0L) {
      stringBuilder.append(" extCode=");
      stringBuilder.append(this.externalCodeSize);
    } 
    if (this.externalDataSize != 0L) {
      stringBuilder.append(" extData=");
      stringBuilder.append(this.externalDataSize);
    } 
    if (this.externalCacheSize != 0L) {
      stringBuilder.append(" extCache=");
      stringBuilder.append(this.externalCacheSize);
    } 
    if (this.externalMediaSize != 0L) {
      stringBuilder.append(" media=");
      stringBuilder.append(this.externalMediaSize);
    } 
    if (this.externalObbSize != 0L) {
      stringBuilder.append(" obb=");
      stringBuilder.append(this.externalObbSize);
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.packageName);
    paramParcel.writeInt(this.userHandle);
    paramParcel.writeLong(this.codeSize);
    paramParcel.writeLong(this.dataSize);
    paramParcel.writeLong(this.cacheSize);
    paramParcel.writeLong(this.externalCodeSize);
    paramParcel.writeLong(this.externalDataSize);
    paramParcel.writeLong(this.externalCacheSize);
    paramParcel.writeLong(this.externalMediaSize);
    paramParcel.writeLong(this.externalObbSize);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */