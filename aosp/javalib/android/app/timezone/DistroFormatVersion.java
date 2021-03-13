package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

public final class DistroFormatVersion implements Parcelable {
  public static final Parcelable.Creator<DistroFormatVersion> CREATOR = new Parcelable.Creator<DistroFormatVersion>() {
      public DistroFormatVersion createFromParcel(Parcel param1Parcel) {
        return new DistroFormatVersion(param1Parcel.readInt(), param1Parcel.readInt());
      }
      
      public DistroFormatVersion[] newArray(int param1Int) {
        return new DistroFormatVersion[param1Int];
      }
    };
  
  private final int mMajorVersion;
  
  private final int mMinorVersion;
  
  public DistroFormatVersion(int paramInt1, int paramInt2) {
    this.mMajorVersion = Utils.validateVersion("major", paramInt1);
    this.mMinorVersion = Utils.validateVersion("minor", paramInt2);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mMajorVersion != ((DistroFormatVersion)paramObject).mMajorVersion)
      return false; 
    if (this.mMinorVersion != ((DistroFormatVersion)paramObject).mMinorVersion)
      bool = false; 
    return bool;
  }
  
  public int getMajorVersion() {
    return this.mMajorVersion;
  }
  
  public int getMinorVersion() {
    return this.mMinorVersion;
  }
  
  public int hashCode() {
    return this.mMajorVersion * 31 + this.mMinorVersion;
  }
  
  public boolean supports(DistroFormatVersion paramDistroFormatVersion) {
    boolean bool;
    if (this.mMajorVersion == paramDistroFormatVersion.mMajorVersion && this.mMinorVersion <= paramDistroFormatVersion.mMinorVersion) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DistroFormatVersion{mMajorVersion=");
    stringBuilder.append(this.mMajorVersion);
    stringBuilder.append(", mMinorVersion=");
    stringBuilder.append(this.mMinorVersion);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mMajorVersion);
    paramParcel.writeInt(this.mMinorVersion);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/DistroFormatVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */