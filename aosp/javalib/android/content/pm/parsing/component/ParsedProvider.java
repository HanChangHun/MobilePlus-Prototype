package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.PathPermission;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.text.TextUtils;

public class ParsedProvider extends ParsedMainComponent {
  public static final Parcelable.Creator<ParsedProvider> CREATOR = new Parcelable.Creator<ParsedProvider>() {
      public ParsedProvider createFromParcel(Parcel param1Parcel) {
        return new ParsedProvider(param1Parcel);
      }
      
      public ParsedProvider[] newArray(int param1Int) {
        return new ParsedProvider[param1Int];
      }
    };
  
  private String authority;
  
  boolean forceUriPermissions;
  
  boolean grantUriPermissions;
  
  int initOrder;
  
  boolean multiProcess;
  
  PathPermission[] pathPermissions;
  
  private String readPermission;
  
  boolean syncable;
  
  PatternMatcher[] uriPermissionPatterns;
  
  private String writePermission;
  
  public ParsedProvider() {}
  
  public ParsedProvider(ParsedProvider paramParsedProvider) {
    super(paramParsedProvider);
    this.authority = paramParsedProvider.authority;
    this.syncable = paramParsedProvider.syncable;
    this.readPermission = paramParsedProvider.readPermission;
    this.writePermission = paramParsedProvider.writePermission;
    this.grantUriPermissions = paramParsedProvider.grantUriPermissions;
    this.forceUriPermissions = paramParsedProvider.forceUriPermissions;
    this.multiProcess = paramParsedProvider.multiProcess;
    this.initOrder = paramParsedProvider.initOrder;
    this.uriPermissionPatterns = paramParsedProvider.uriPermissionPatterns;
    this.pathPermissions = paramParsedProvider.pathPermissions;
  }
  
  protected ParsedProvider(Parcel paramParcel) {
    super(paramParcel);
    this.authority = paramParcel.readString();
    this.syncable = paramParcel.readBoolean();
    this.readPermission = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    this.writePermission = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    this.grantUriPermissions = paramParcel.readBoolean();
    this.forceUriPermissions = paramParcel.readBoolean();
    this.multiProcess = paramParcel.readBoolean();
    this.initOrder = paramParcel.readInt();
    this.uriPermissionPatterns = (PatternMatcher[])paramParcel.createTypedArray(PatternMatcher.CREATOR);
    this.pathPermissions = (PathPermission[])paramParcel.createTypedArray(PathPermission.CREATOR);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getAuthority() {
    return this.authority;
  }
  
  public int getInitOrder() {
    return this.initOrder;
  }
  
  public PathPermission[] getPathPermissions() {
    return this.pathPermissions;
  }
  
  public String getReadPermission() {
    return this.readPermission;
  }
  
  public PatternMatcher[] getUriPermissionPatterns() {
    return this.uriPermissionPatterns;
  }
  
  public String getWritePermission() {
    return this.writePermission;
  }
  
  public boolean isForceUriPermissions() {
    return this.forceUriPermissions;
  }
  
  public boolean isGrantUriPermissions() {
    return this.grantUriPermissions;
  }
  
  public boolean isMultiProcess() {
    return this.multiProcess;
  }
  
  public boolean isSyncable() {
    return this.syncable;
  }
  
  public void setAuthority(String paramString) {
    this.authority = TextUtils.safeIntern(paramString);
  }
  
  public void setReadPermission(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    } else {
      paramString = paramString.intern();
    } 
    this.readPermission = paramString;
  }
  
  public void setSyncable(boolean paramBoolean) {
    this.syncable = paramBoolean;
  }
  
  public void setWritePermission(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    } else {
      paramString = paramString.intern();
    } 
    this.writePermission = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Provider{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    ComponentName.appendShortString(stringBuilder, getPackageName(), getName());
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.authority);
    paramParcel.writeBoolean(this.syncable);
    ParsingPackageImpl.sForInternedString.parcel(this.readPermission, paramParcel, paramInt);
    ParsingPackageImpl.sForInternedString.parcel(this.writePermission, paramParcel, paramInt);
    paramParcel.writeBoolean(this.grantUriPermissions);
    paramParcel.writeBoolean(this.forceUriPermissions);
    paramParcel.writeBoolean(this.multiProcess);
    paramParcel.writeInt(this.initOrder);
    paramParcel.writeTypedArray((Parcelable[])this.uriPermissionPatterns, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.pathPermissions, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */