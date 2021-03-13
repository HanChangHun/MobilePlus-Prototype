package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class InstallSourceInfo implements Parcelable {
  public static final Parcelable.Creator<InstallSourceInfo> CREATOR = new Parcelable.Creator<InstallSourceInfo>() {
      public InstallSourceInfo createFromParcel(Parcel param1Parcel) {
        return new InstallSourceInfo(param1Parcel);
      }
      
      public InstallSourceInfo[] newArray(int param1Int) {
        return new InstallSourceInfo[param1Int];
      }
    };
  
  private final String mInitiatingPackageName;
  
  private final SigningInfo mInitiatingPackageSigningInfo;
  
  private final String mInstallingPackageName;
  
  private final String mOriginatingPackageName;
  
  private InstallSourceInfo(Parcel paramParcel) {
    this.mInitiatingPackageName = paramParcel.readString();
    this.mInitiatingPackageSigningInfo = (SigningInfo)paramParcel.readParcelable(SigningInfo.class.getClassLoader());
    this.mOriginatingPackageName = paramParcel.readString();
    this.mInstallingPackageName = paramParcel.readString();
  }
  
  public InstallSourceInfo(String paramString1, SigningInfo paramSigningInfo, String paramString2, String paramString3) {
    this.mInitiatingPackageName = paramString1;
    this.mInitiatingPackageSigningInfo = paramSigningInfo;
    this.mOriginatingPackageName = paramString2;
    this.mInstallingPackageName = paramString3;
  }
  
  public int describeContents() {
    int i;
    SigningInfo signingInfo = this.mInitiatingPackageSigningInfo;
    if (signingInfo == null) {
      i = 0;
    } else {
      i = signingInfo.describeContents();
    } 
    return i;
  }
  
  public String getInitiatingPackageName() {
    return this.mInitiatingPackageName;
  }
  
  public SigningInfo getInitiatingPackageSigningInfo() {
    return this.mInitiatingPackageSigningInfo;
  }
  
  public String getInstallingPackageName() {
    return this.mInstallingPackageName;
  }
  
  public String getOriginatingPackageName() {
    return this.mOriginatingPackageName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mInitiatingPackageName);
    paramParcel.writeParcelable(this.mInitiatingPackageSigningInfo, paramInt);
    paramParcel.writeString(this.mOriginatingPackageName);
    paramParcel.writeString(this.mInstallingPackageName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstallSourceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */