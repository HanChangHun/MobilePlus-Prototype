package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class PackageInfoLite implements Parcelable {
  public static final Parcelable.Creator<PackageInfoLite> CREATOR = new Parcelable.Creator<PackageInfoLite>() {
      public PackageInfoLite createFromParcel(Parcel param1Parcel) {
        return new PackageInfoLite(param1Parcel);
      }
      
      public PackageInfoLite[] newArray(int param1Int) {
        return new PackageInfoLite[param1Int];
      }
    };
  
  public int baseRevisionCode;
  
  public boolean debuggable;
  
  public int installLocation;
  
  public boolean multiArch;
  
  public String packageName;
  
  public int recommendedInstallLocation;
  
  public String[] splitNames;
  
  public int[] splitRevisionCodes;
  
  public VerifierInfo[] verifiers;
  
  @Deprecated
  public int versionCode;
  
  public int versionCodeMajor;
  
  public PackageInfoLite() {}
  
  private PackageInfoLite(Parcel paramParcel) {
    boolean bool2;
    this.packageName = paramParcel.readString();
    this.splitNames = paramParcel.createStringArray();
    this.versionCode = paramParcel.readInt();
    this.versionCodeMajor = paramParcel.readInt();
    this.baseRevisionCode = paramParcel.readInt();
    this.splitRevisionCodes = paramParcel.createIntArray();
    this.recommendedInstallLocation = paramParcel.readInt();
    this.installLocation = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.multiArch = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.debuggable = bool2;
    i = paramParcel.readInt();
    if (i == 0) {
      this.verifiers = new VerifierInfo[0];
    } else {
      VerifierInfo[] arrayOfVerifierInfo = new VerifierInfo[i];
      this.verifiers = arrayOfVerifierInfo;
      paramParcel.readTypedArray((Object[])arrayOfVerifierInfo, VerifierInfo.CREATOR);
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getLongVersionCode() {
    return PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PackageInfoLite{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.packageName);
    paramParcel.writeStringArray(this.splitNames);
    paramParcel.writeInt(this.versionCode);
    paramParcel.writeInt(this.versionCodeMajor);
    paramParcel.writeInt(this.baseRevisionCode);
    paramParcel.writeIntArray(this.splitRevisionCodes);
    paramParcel.writeInt(this.recommendedInstallLocation);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeInt(this.multiArch);
    paramParcel.writeInt(this.debuggable);
    VerifierInfo[] arrayOfVerifierInfo = this.verifiers;
    if (arrayOfVerifierInfo == null || arrayOfVerifierInfo.length == 0) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(arrayOfVerifierInfo.length);
    paramParcel.writeTypedArray((Parcelable[])this.verifiers, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInfoLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */