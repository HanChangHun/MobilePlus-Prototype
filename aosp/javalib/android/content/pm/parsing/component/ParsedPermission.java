package android.content.pm.parsing.component;

import android.content.pm.PermissionInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ParsedPermission extends ParsedComponent {
  public static final Parcelable.Creator<ParsedPermission> CREATOR = new Parcelable.Creator<ParsedPermission>() {
      public ParsedPermission createFromParcel(Parcel param1Parcel) {
        return new ParsedPermission(param1Parcel);
      }
      
      public ParsedPermission[] newArray(int param1Int) {
        return new ParsedPermission[param1Int];
      }
    };
  
  String backgroundPermission;
  
  private String group;
  
  private ParsedPermissionGroup parsedPermissionGroup;
  
  int protectionLevel;
  
  int requestRes;
  
  boolean tree;
  
  public ParsedPermission() {}
  
  public ParsedPermission(ParsedPermission paramParsedPermission) {
    super(paramParsedPermission);
    this.backgroundPermission = paramParsedPermission.backgroundPermission;
    this.group = paramParsedPermission.group;
    this.requestRes = paramParsedPermission.requestRes;
    this.protectionLevel = paramParsedPermission.protectionLevel;
    this.tree = paramParsedPermission.tree;
    this.parsedPermissionGroup = paramParsedPermission.parsedPermissionGroup;
  }
  
  public ParsedPermission(ParsedPermission paramParsedPermission, PermissionInfo paramPermissionInfo, String paramString1, String paramString2) {
    this(paramParsedPermission);
    this.flags = paramPermissionInfo.flags;
    this.descriptionRes = paramPermissionInfo.descriptionRes;
    this.backgroundPermission = paramPermissionInfo.backgroundPermission;
    this.group = paramPermissionInfo.group;
    this.requestRes = paramPermissionInfo.requestRes;
    this.protectionLevel = paramPermissionInfo.protectionLevel;
    setName(paramString2);
    setPackageName(paramString1);
  }
  
  protected ParsedPermission(Parcel paramParcel) {
    super(paramParcel);
    ClassLoader classLoader = Object.class.getClassLoader();
    this.backgroundPermission = paramParcel.readString();
    this.group = paramParcel.readString();
    this.requestRes = paramParcel.readInt();
    this.protectionLevel = paramParcel.readInt();
    this.tree = paramParcel.readBoolean();
    this.parsedPermissionGroup = (ParsedPermissionGroup)paramParcel.readParcelable(classLoader);
  }
  
  public int calculateFootprint() {
    int i = getName().length();
    int j = i;
    if (getNonLocalizedLabel() != null)
      j = i + getNonLocalizedLabel().length(); 
    return j;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getBackgroundPermission() {
    return this.backgroundPermission;
  }
  
  public String getGroup() {
    return this.group;
  }
  
  public ParsedPermissionGroup getParsedPermissionGroup() {
    return this.parsedPermissionGroup;
  }
  
  public int getProtection() {
    return this.protectionLevel & 0xF;
  }
  
  public int getProtectionFlags() {
    return this.protectionLevel & 0xFFFFFFF0;
  }
  
  public int getProtectionLevel() {
    return this.protectionLevel;
  }
  
  public int getRequestRes() {
    return this.requestRes;
  }
  
  public boolean isAppOp() {
    boolean bool;
    if ((this.protectionLevel & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRuntime() {
    int i = getProtection();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isTree() {
    return this.tree;
  }
  
  public ParsedPermission setFlags(int paramInt) {
    this.flags = paramInt;
    return this;
  }
  
  public ParsedPermission setGroup(String paramString) {
    this.group = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsedPermission setParsedPermissionGroup(ParsedPermissionGroup paramParsedPermissionGroup) {
    this.parsedPermissionGroup = paramParsedPermissionGroup;
    return this;
  }
  
  public ParsedPermission setProtectionLevel(int paramInt) {
    this.protectionLevel = paramInt;
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Permission{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(getName());
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.backgroundPermission);
    paramParcel.writeString(this.group);
    paramParcel.writeInt(this.requestRes);
    paramParcel.writeInt(this.protectionLevel);
    paramParcel.writeBoolean(this.tree);
    paramParcel.writeParcelable(this.parsedPermissionGroup, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */