package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class VersionedPackage implements Parcelable {
  public static final Parcelable.Creator<VersionedPackage> CREATOR = new Parcelable.Creator<VersionedPackage>() {
      public VersionedPackage createFromParcel(Parcel param1Parcel) {
        return new VersionedPackage(param1Parcel);
      }
      
      public VersionedPackage[] newArray(int param1Int) {
        return new VersionedPackage[param1Int];
      }
    };
  
  private final String mPackageName;
  
  private final long mVersionCode;
  
  private VersionedPackage(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString8();
    this.mVersionCode = paramParcel.readLong();
  }
  
  public VersionedPackage(String paramString, int paramInt) {
    this.mPackageName = paramString;
    this.mVersionCode = paramInt;
  }
  
  public VersionedPackage(String paramString, long paramLong) {
    this.mPackageName = paramString;
    this.mVersionCode = paramLong;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof VersionedPackage && ((VersionedPackage)paramObject).mPackageName.equals(this.mPackageName) && ((VersionedPackage)paramObject).mVersionCode == this.mVersionCode) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public long getLongVersionCode() {
    return this.mVersionCode;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  @Deprecated
  public int getVersionCode() {
    return (int)(this.mVersionCode & 0x7FFFFFFFL);
  }
  
  public int hashCode() {
    return this.mPackageName.hashCode() * 31 + Long.hashCode(this.mVersionCode);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("VersionedPackage[");
    stringBuilder.append(this.mPackageName);
    stringBuilder.append("/");
    stringBuilder.append(this.mVersionCode);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString8(this.mPackageName);
    paramParcel.writeLong(this.mVersionCode);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface VersionCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/VersionedPackage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */