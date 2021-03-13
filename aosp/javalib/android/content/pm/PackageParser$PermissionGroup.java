package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class PermissionGroup extends PackageParser.Component<PackageParser.IntentInfo> implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<PermissionGroup>() {
      public PackageParser.PermissionGroup createFromParcel(Parcel param2Parcel) {
        return new PackageParser.PermissionGroup(param2Parcel);
      }
      
      public PackageParser.PermissionGroup[] newArray(int param2Int) {
        return new PackageParser.PermissionGroup[param2Int];
      }
    };
  
  public final PermissionGroupInfo info;
  
  public PermissionGroup(PackageParser.Package paramPackage, int paramInt1, int paramInt2, int paramInt3) {
    super(paramPackage);
    this.info = new PermissionGroupInfo(paramInt1, paramInt2, paramInt3);
  }
  
  public PermissionGroup(PackageParser.Package paramPackage, PermissionGroupInfo paramPermissionGroupInfo) {
    super(paramPackage);
    this.info = paramPermissionGroupInfo;
  }
  
  private PermissionGroup(Parcel paramParcel) {
    super(paramParcel);
    this.info = (PermissionGroupInfo)paramParcel.readParcelable(Object.class.getClassLoader());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void setPackageName(String paramString) {
    super.setPackageName(paramString);
    this.info.packageName = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PermissionGroup{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.info.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.info, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$PermissionGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */