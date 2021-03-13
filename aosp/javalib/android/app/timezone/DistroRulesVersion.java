package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

public final class DistroRulesVersion implements Parcelable {
  public static final Parcelable.Creator<DistroRulesVersion> CREATOR = new Parcelable.Creator<DistroRulesVersion>() {
      public DistroRulesVersion createFromParcel(Parcel param1Parcel) {
        return new DistroRulesVersion(param1Parcel.readString(), param1Parcel.readInt());
      }
      
      public DistroRulesVersion[] newArray(int param1Int) {
        return new DistroRulesVersion[param1Int];
      }
    };
  
  private final int mRevision;
  
  private final String mRulesVersion;
  
  public DistroRulesVersion(String paramString, int paramInt) {
    this.mRulesVersion = Utils.validateRulesVersion("rulesVersion", paramString);
    this.mRevision = Utils.validateVersion("revision", paramInt);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return (this.mRevision != ((DistroRulesVersion)paramObject).mRevision) ? false : this.mRulesVersion.equals(((DistroRulesVersion)paramObject).mRulesVersion);
  }
  
  public int getRevision() {
    return this.mRevision;
  }
  
  public String getRulesVersion() {
    return this.mRulesVersion;
  }
  
  public int hashCode() {
    return this.mRulesVersion.hashCode() * 31 + this.mRevision;
  }
  
  public boolean isOlderThan(DistroRulesVersion paramDistroRulesVersion) {
    int i = this.mRulesVersion.compareTo(paramDistroRulesVersion.mRulesVersion);
    boolean bool = true;
    if (i < 0)
      return true; 
    if (i > 0)
      return false; 
    if (this.mRevision >= paramDistroRulesVersion.mRevision)
      bool = false; 
    return bool;
  }
  
  public String toDumpString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mRulesVersion);
    stringBuilder.append(",");
    stringBuilder.append(this.mRevision);
    return stringBuilder.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DistroRulesVersion{mRulesVersion='");
    stringBuilder.append(this.mRulesVersion);
    stringBuilder.append('\'');
    stringBuilder.append(", mRevision='");
    stringBuilder.append(this.mRevision);
    stringBuilder.append('\'');
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mRulesVersion);
    paramParcel.writeInt(this.mRevision);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/DistroRulesVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */