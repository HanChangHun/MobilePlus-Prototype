package android.content.pm;

import android.annotation.SystemApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.IndentingPrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SessionParams implements Parcelable {
  public static final Parcelable.Creator<SessionParams> CREATOR;
  
  public static final int MAX_PACKAGE_NAME_LENGTH = 255;
  
  public static final int MODE_FULL_INSTALL = 1;
  
  public static final int MODE_INHERIT_EXISTING = 2;
  
  public static final int MODE_INVALID = -1;
  
  public static final Set<String> RESTRICTED_PERMISSIONS_ALL = (Set<String>)new ArraySet();
  
  public static final int UID_UNKNOWN = -1;
  
  public String abiOverride;
  
  public Bitmap appIcon;
  
  public long appIconLastModified = -1L;
  
  public String appLabel;
  
  public String appPackageName;
  
  public int autoRevokePermissionsMode = 3;
  
  public DataLoaderParams dataLoaderParams;
  
  public boolean forceQueryableOverride;
  
  public String[] grantedRuntimePermissions;
  
  public int installFlags = 4194304;
  
  public int installLocation = 1;
  
  public int installReason = 0;
  
  public String installerPackageName;
  
  public boolean isMultiPackage;
  
  public boolean isStaged;
  
  public int mode = -1;
  
  public int originatingUid = -1;
  
  public Uri originatingUri;
  
  public Uri referrerUri;
  
  public long requiredInstalledVersionCode = -1L;
  
  public int rollbackDataPolicy = 0;
  
  public long sizeBytes = -1L;
  
  public String volumeUuid;
  
  public List<String> whitelistedRestrictedPermissions;
  
  static {
    CREATOR = new Parcelable.Creator<SessionParams>() {
        public PackageInstaller.SessionParams createFromParcel(Parcel param2Parcel) {
          return new PackageInstaller.SessionParams(param2Parcel);
        }
        
        public PackageInstaller.SessionParams[] newArray(int param2Int) {
          return new PackageInstaller.SessionParams[param2Int];
        }
      };
  }
  
  public SessionParams(int paramInt) {
    this.mode = paramInt;
  }
  
  public SessionParams(Parcel paramParcel) {
    this.mode = paramParcel.readInt();
    this.installFlags = paramParcel.readInt();
    this.installLocation = paramParcel.readInt();
    this.installReason = paramParcel.readInt();
    this.sizeBytes = paramParcel.readLong();
    this.appPackageName = paramParcel.readString();
    this.appIcon = (Bitmap)paramParcel.readParcelable(null);
    this.appLabel = paramParcel.readString();
    this.originatingUri = (Uri)paramParcel.readParcelable(null);
    this.originatingUid = paramParcel.readInt();
    this.referrerUri = (Uri)paramParcel.readParcelable(null);
    this.abiOverride = paramParcel.readString();
    this.volumeUuid = paramParcel.readString();
    this.grantedRuntimePermissions = paramParcel.readStringArray();
    this.whitelistedRestrictedPermissions = paramParcel.createStringArrayList();
    this.autoRevokePermissionsMode = paramParcel.readInt();
    this.installerPackageName = paramParcel.readString();
    this.isMultiPackage = paramParcel.readBoolean();
    this.isStaged = paramParcel.readBoolean();
    this.forceQueryableOverride = paramParcel.readBoolean();
    this.requiredInstalledVersionCode = paramParcel.readLong();
    DataLoaderParamsParcel dataLoaderParamsParcel = (DataLoaderParamsParcel)paramParcel.readParcelable(DataLoaderParamsParcel.class.getClassLoader());
    if (dataLoaderParamsParcel != null)
      this.dataLoaderParams = new DataLoaderParams(dataLoaderParamsParcel); 
    this.rollbackDataPolicy = paramParcel.readInt();
  }
  
  public boolean areHiddenOptionsSet() {
    int i = this.installFlags;
    return ((0x11D880 & i) != i || this.abiOverride != null || this.volumeUuid != null);
  }
  
  public SessionParams copy() {
    SessionParams sessionParams = new SessionParams(this.mode);
    sessionParams.installFlags = this.installFlags;
    sessionParams.installLocation = this.installLocation;
    sessionParams.installReason = this.installReason;
    sessionParams.sizeBytes = this.sizeBytes;
    sessionParams.appPackageName = this.appPackageName;
    sessionParams.appIcon = this.appIcon;
    sessionParams.appLabel = this.appLabel;
    sessionParams.originatingUri = this.originatingUri;
    sessionParams.originatingUid = this.originatingUid;
    sessionParams.referrerUri = this.referrerUri;
    sessionParams.abiOverride = this.abiOverride;
    sessionParams.volumeUuid = this.volumeUuid;
    sessionParams.grantedRuntimePermissions = this.grantedRuntimePermissions;
    sessionParams.whitelistedRestrictedPermissions = this.whitelistedRestrictedPermissions;
    sessionParams.autoRevokePermissionsMode = this.autoRevokePermissionsMode;
    sessionParams.installerPackageName = this.installerPackageName;
    sessionParams.isMultiPackage = this.isMultiPackage;
    sessionParams.isStaged = this.isStaged;
    sessionParams.forceQueryableOverride = this.forceQueryableOverride;
    sessionParams.requiredInstalledVersionCode = this.requiredInstalledVersionCode;
    sessionParams.dataLoaderParams = this.dataLoaderParams;
    sessionParams.rollbackDataPolicy = this.rollbackDataPolicy;
    return sessionParams;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(IndentingPrintWriter paramIndentingPrintWriter) {
    boolean bool;
    paramIndentingPrintWriter.printPair("mode", Integer.valueOf(this.mode));
    paramIndentingPrintWriter.printHexPair("installFlags", this.installFlags);
    paramIndentingPrintWriter.printPair("installLocation", Integer.valueOf(this.installLocation));
    paramIndentingPrintWriter.printPair("sizeBytes", Long.valueOf(this.sizeBytes));
    paramIndentingPrintWriter.printPair("appPackageName", this.appPackageName);
    if (this.appIcon != null) {
      bool = true;
    } else {
      bool = false;
    } 
    paramIndentingPrintWriter.printPair("appIcon", Boolean.valueOf(bool));
    paramIndentingPrintWriter.printPair("appLabel", this.appLabel);
    paramIndentingPrintWriter.printPair("originatingUri", this.originatingUri);
    paramIndentingPrintWriter.printPair("originatingUid", Integer.valueOf(this.originatingUid));
    paramIndentingPrintWriter.printPair("referrerUri", this.referrerUri);
    paramIndentingPrintWriter.printPair("abiOverride", this.abiOverride);
    paramIndentingPrintWriter.printPair("volumeUuid", this.volumeUuid);
    paramIndentingPrintWriter.printPair("grantedRuntimePermissions", (Object[])this.grantedRuntimePermissions);
    paramIndentingPrintWriter.printPair("whitelistedRestrictedPermissions", this.whitelistedRestrictedPermissions);
    paramIndentingPrintWriter.printPair("autoRevokePermissions", Integer.valueOf(this.autoRevokePermissionsMode));
    paramIndentingPrintWriter.printPair("installerPackageName", this.installerPackageName);
    paramIndentingPrintWriter.printPair("isMultiPackage", Boolean.valueOf(this.isMultiPackage));
    paramIndentingPrintWriter.printPair("isStaged", Boolean.valueOf(this.isStaged));
    paramIndentingPrintWriter.printPair("forceQueryable", Boolean.valueOf(this.forceQueryableOverride));
    paramIndentingPrintWriter.printPair("requiredInstalledVersionCode", Long.valueOf(this.requiredInstalledVersionCode));
    paramIndentingPrintWriter.printPair("dataLoaderParams", this.dataLoaderParams);
    paramIndentingPrintWriter.printPair("rollbackDataPolicy", Integer.valueOf(this.rollbackDataPolicy));
    paramIndentingPrintWriter.println();
  }
  
  public boolean getEnableRollback() {
    boolean bool;
    if ((this.installFlags & 0x40000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  public void setAllocateAggressive(boolean paramBoolean) {
    if (paramBoolean) {
      this.installFlags |= 0x8000;
    } else {
      this.installFlags &= 0xFFFF7FFF;
    } 
  }
  
  @SystemApi
  @Deprecated
  public void setAllowDowngrade(boolean paramBoolean) {
    setRequestDowngrade(paramBoolean);
  }
  
  public void setAppIcon(Bitmap paramBitmap) {
    this.appIcon = paramBitmap;
  }
  
  public void setAppLabel(CharSequence paramCharSequence) {
    if (paramCharSequence != null) {
      paramCharSequence = paramCharSequence.toString();
    } else {
      paramCharSequence = null;
    } 
    this.appLabel = (String)paramCharSequence;
  }
  
  public void setAppPackageName(String paramString) {
    this.appPackageName = paramString;
  }
  
  public void setAutoRevokePermissionsMode(boolean paramBoolean) {
    this.autoRevokePermissionsMode = paramBoolean ^ true;
  }
  
  @SystemApi
  public void setDataLoaderParams(DataLoaderParams paramDataLoaderParams) {
    this.dataLoaderParams = paramDataLoaderParams;
  }
  
  @SystemApi
  public void setDontKillApp(boolean paramBoolean) {
    if (paramBoolean) {
      this.installFlags |= 0x1000;
    } else {
      this.installFlags &= 0xFFFFEFFF;
    } 
  }
  
  @SystemApi
  public void setEnableRollback(boolean paramBoolean) {
    if (paramBoolean) {
      this.installFlags |= 0x40000;
    } else {
      this.installFlags &= 0xFFFBFFFF;
    } 
    this.rollbackDataPolicy = 0;
  }
  
  @SystemApi
  public void setEnableRollback(boolean paramBoolean, int paramInt) {
    if (paramBoolean) {
      this.installFlags |= 0x40000;
    } else {
      this.installFlags &= 0xFFFBFFFF;
    } 
    this.rollbackDataPolicy = paramInt;
  }
  
  public void setForceQueryable() {
    this.forceQueryableOverride = true;
  }
  
  @SystemApi
  public void setGrantedRuntimePermissions(String[] paramArrayOfString) {
    this.installFlags |= 0x100;
    this.grantedRuntimePermissions = paramArrayOfString;
  }
  
  @SystemApi
  public void setInstallAsApex() {
    this.installFlags |= 0x20000;
  }
  
  @SystemApi
  public void setInstallAsInstantApp(boolean paramBoolean) {
    if (paramBoolean) {
      int i = this.installFlags | 0x800;
      this.installFlags = i;
      this.installFlags = i & 0xFFFFBFFF;
    } else {
      int i = this.installFlags & 0xFFFFF7FF;
      this.installFlags = i;
      this.installFlags = i | 0x4000;
    } 
  }
  
  @SystemApi
  public void setInstallAsVirtualPreload() {
    this.installFlags |= 0x10000;
  }
  
  public void setInstallFlagsForcePermissionPrompt() {
    this.installFlags |= 0x400;
  }
  
  public void setInstallLocation(int paramInt) {
    this.installLocation = paramInt;
  }
  
  public void setInstallReason(int paramInt) {
    this.installReason = paramInt;
  }
  
  public void setInstallerPackageName(String paramString) {
    this.installerPackageName = paramString;
  }
  
  public void setMultiPackage() {
    this.isMultiPackage = true;
  }
  
  public void setOriginatingUid(int paramInt) {
    this.originatingUid = paramInt;
  }
  
  public void setOriginatingUri(Uri paramUri) {
    this.originatingUri = paramUri;
  }
  
  public void setReferrerUri(Uri paramUri) {
    this.referrerUri = paramUri;
  }
  
  @SystemApi
  public void setRequestDowngrade(boolean paramBoolean) {
    if (paramBoolean) {
      this.installFlags |= 0x80;
    } else {
      this.installFlags &= 0xFFFFFF7F;
    } 
  }
  
  public void setRequiredInstalledVersionCode(long paramLong) {
    this.requiredInstalledVersionCode = paramLong;
  }
  
  public void setSize(long paramLong) {
    this.sizeBytes = paramLong;
  }
  
  @SystemApi
  public void setStaged() {
    this.isStaged = true;
  }
  
  public void setWhitelistedRestrictedPermissions(Set<String> paramSet) {
    Set<String> set = RESTRICTED_PERMISSIONS_ALL;
    Set set1 = null;
    if (paramSet == set) {
      this.installFlags |= 0x400000;
      this.whitelistedRestrictedPermissions = null;
    } else {
      this.installFlags &= 0xFFBFFFFF;
      if (paramSet != null) {
        ArrayList<String> arrayList = new ArrayList<>(paramSet);
      } else {
        paramSet = set1;
      } 
      this.whitelistedRestrictedPermissions = (List<String>)paramSet;
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mode);
    paramParcel.writeInt(this.installFlags);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeInt(this.installReason);
    paramParcel.writeLong(this.sizeBytes);
    paramParcel.writeString(this.appPackageName);
    paramParcel.writeParcelable((Parcelable)this.appIcon, paramInt);
    paramParcel.writeString(this.appLabel);
    paramParcel.writeParcelable((Parcelable)this.originatingUri, paramInt);
    paramParcel.writeInt(this.originatingUid);
    paramParcel.writeParcelable((Parcelable)this.referrerUri, paramInt);
    paramParcel.writeString(this.abiOverride);
    paramParcel.writeString(this.volumeUuid);
    paramParcel.writeStringArray(this.grantedRuntimePermissions);
    paramParcel.writeStringList(this.whitelistedRestrictedPermissions);
    paramParcel.writeInt(this.autoRevokePermissionsMode);
    paramParcel.writeString(this.installerPackageName);
    paramParcel.writeBoolean(this.isMultiPackage);
    paramParcel.writeBoolean(this.isStaged);
    paramParcel.writeBoolean(this.forceQueryableOverride);
    paramParcel.writeLong(this.requiredInstalledVersionCode);
    DataLoaderParams dataLoaderParams = this.dataLoaderParams;
    if (dataLoaderParams != null) {
      paramParcel.writeParcelable(dataLoaderParams.getData(), paramInt);
    } else {
      paramParcel.writeParcelable(null, paramInt);
    } 
    paramParcel.writeInt(this.rollbackDataPolicy);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$SessionParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */