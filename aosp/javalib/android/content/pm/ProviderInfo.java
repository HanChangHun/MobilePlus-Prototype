package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.util.Printer;

public final class ProviderInfo extends ComponentInfo implements Parcelable {
  public static final Parcelable.Creator<ProviderInfo> CREATOR = new Parcelable.Creator<ProviderInfo>() {
      public ProviderInfo createFromParcel(Parcel param1Parcel) {
        return new ProviderInfo(param1Parcel);
      }
      
      public ProviderInfo[] newArray(int param1Int) {
        return new ProviderInfo[param1Int];
      }
    };
  
  public static final int FLAG_SINGLE_USER = 1073741824;
  
  public static final int FLAG_VISIBLE_TO_INSTANT_APP = 1048576;
  
  public String authority = null;
  
  public int flags;
  
  public boolean forceUriPermissions;
  
  public boolean grantUriPermissions;
  
  public int initOrder;
  
  @Deprecated
  public boolean isSyncable;
  
  public boolean multiprocess;
  
  public PathPermission[] pathPermissions;
  
  public String readPermission = null;
  
  public PatternMatcher[] uriPermissionPatterns;
  
  public String writePermission = null;
  
  public ProviderInfo() {
    this.grantUriPermissions = false;
    this.forceUriPermissions = false;
    this.uriPermissionPatterns = null;
    this.pathPermissions = null;
    this.multiprocess = false;
    this.initOrder = 0;
    this.flags = 0;
    this.isSyncable = false;
  }
  
  public ProviderInfo(ProviderInfo paramProviderInfo) {
    super(paramProviderInfo);
    this.grantUriPermissions = false;
    this.forceUriPermissions = false;
    this.uriPermissionPatterns = null;
    this.pathPermissions = null;
    this.multiprocess = false;
    this.initOrder = 0;
    this.flags = 0;
    this.isSyncable = false;
    this.authority = paramProviderInfo.authority;
    this.readPermission = paramProviderInfo.readPermission;
    this.writePermission = paramProviderInfo.writePermission;
    this.grantUriPermissions = paramProviderInfo.grantUriPermissions;
    this.forceUriPermissions = paramProviderInfo.forceUriPermissions;
    this.uriPermissionPatterns = paramProviderInfo.uriPermissionPatterns;
    this.pathPermissions = paramProviderInfo.pathPermissions;
    this.multiprocess = paramProviderInfo.multiprocess;
    this.initOrder = paramProviderInfo.initOrder;
    this.flags = paramProviderInfo.flags;
    this.isSyncable = paramProviderInfo.isSyncable;
  }
  
  private ProviderInfo(Parcel paramParcel) {
    super(paramParcel);
    boolean bool1 = false;
    this.grantUriPermissions = false;
    this.forceUriPermissions = false;
    this.uriPermissionPatterns = null;
    this.pathPermissions = null;
    this.multiprocess = false;
    this.initOrder = 0;
    this.flags = 0;
    this.isSyncable = false;
    this.authority = paramParcel.readString8();
    this.readPermission = paramParcel.readString8();
    this.writePermission = paramParcel.readString8();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.grantUriPermissions = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.forceUriPermissions = bool2;
    this.uriPermissionPatterns = (PatternMatcher[])paramParcel.createTypedArray(PatternMatcher.CREATOR);
    this.pathPermissions = (PathPermission[])paramParcel.createTypedArray(PathPermission.CREATOR);
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.multiprocess = bool2;
    this.initOrder = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    boolean bool2 = bool1;
    if (paramParcel.readInt() != 0)
      bool2 = true; 
    this.isSyncable = bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    dump(paramPrinter, paramString, 3);
  }
  
  public void dump(Printer paramPrinter, String paramString, int paramInt) {
    dumpFront(paramPrinter, paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("authority=");
    stringBuilder.append(this.authority);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("flags=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    paramPrinter.println(stringBuilder.toString());
    dumpBack(paramPrinter, paramString, paramInt);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ContentProviderInfo{name=");
    stringBuilder.append(this.authority);
    stringBuilder.append(" className=");
    stringBuilder.append(this.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString8(this.authority);
    paramParcel.writeString8(this.readPermission);
    paramParcel.writeString8(this.writePermission);
    paramParcel.writeInt(this.grantUriPermissions);
    paramParcel.writeInt(this.forceUriPermissions);
    paramParcel.writeTypedArray((Parcelable[])this.uriPermissionPatterns, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.pathPermissions, paramInt);
    paramParcel.writeInt(this.multiprocess);
    paramParcel.writeInt(this.initOrder);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.isSyncable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ProviderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */