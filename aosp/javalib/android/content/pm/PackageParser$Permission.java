package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class Permission extends PackageParser.Component<PackageParser.IntentInfo> implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Permission>() {
      public PackageParser.Permission createFromParcel(Parcel param2Parcel) {
        return new PackageParser.Permission(param2Parcel);
      }
      
      public PackageParser.Permission[] newArray(int param2Int) {
        return new PackageParser.Permission[param2Int];
      }
    };
  
  public PackageParser.PermissionGroup group;
  
  public final PermissionInfo info;
  
  public boolean tree;
  
  public Permission(PackageParser.Package paramPackage, PermissionInfo paramPermissionInfo) {
    super(paramPackage);
    this.info = paramPermissionInfo;
  }
  
  public Permission(PackageParser.Package paramPackage, String paramString) {
    super(paramPackage);
    this.info = new PermissionInfo(paramString);
  }
  
  private Permission(Parcel paramParcel) {
    super(paramParcel);
    ClassLoader classLoader = Object.class.getClassLoader();
    PermissionInfo permissionInfo = (PermissionInfo)paramParcel.readParcelable(classLoader);
    this.info = permissionInfo;
    if (permissionInfo.group != null) {
      permissionInfo = this.info;
      permissionInfo.group = permissionInfo.group.intern();
    } 
    int i = paramParcel.readInt();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    this.tree = bool;
    this.group = (PackageParser.PermissionGroup)paramParcel.readParcelable(classLoader);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean isAppOp() {
    return this.info.isAppOp();
  }
  
  public void setPackageName(String paramString) {
    super.setPackageName(paramString);
    this.info.packageName = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Permission{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.info.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.info, paramInt);
    paramParcel.writeInt(this.tree);
    paramParcel.writeParcelable(this.group, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Permission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */