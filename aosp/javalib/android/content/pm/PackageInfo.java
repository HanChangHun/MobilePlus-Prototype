package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class PackageInfo implements Parcelable {
  public static final Parcelable.Creator<PackageInfo> CREATOR = new Parcelable.Creator<PackageInfo>() {
      public PackageInfo createFromParcel(Parcel param1Parcel) {
        return new PackageInfo(param1Parcel);
      }
      
      public PackageInfo[] newArray(int param1Int) {
        return new PackageInfo[param1Int];
      }
    };
  
  public static final int INSTALL_LOCATION_AUTO = 0;
  
  public static final int INSTALL_LOCATION_INTERNAL_ONLY = 1;
  
  public static final int INSTALL_LOCATION_PREFER_EXTERNAL = 2;
  
  public static final int INSTALL_LOCATION_UNSPECIFIED = -1;
  
  public static final int REQUESTED_PERMISSION_GRANTED = 2;
  
  public static final int REQUESTED_PERMISSION_REQUIRED = 1;
  
  public ActivityInfo[] activities;
  
  public ApplicationInfo applicationInfo;
  
  public int baseRevisionCode;
  
  public int compileSdkVersion;
  
  public String compileSdkVersionCodename;
  
  public ConfigurationInfo[] configPreferences;
  
  public boolean coreApp;
  
  public FeatureGroupInfo[] featureGroups;
  
  public long firstInstallTime;
  
  public int[] gids;
  
  public int installLocation;
  
  public InstrumentationInfo[] instrumentation;
  
  public boolean isApex;
  
  public boolean isStub;
  
  public long lastUpdateTime;
  
  public boolean mOverlayIsStatic;
  
  public String overlayCategory;
  
  public int overlayPriority;
  
  public String overlayTarget;
  
  public String packageName;
  
  public PermissionInfo[] permissions;
  
  public ProviderInfo[] providers;
  
  public ActivityInfo[] receivers;
  
  public FeatureInfo[] reqFeatures;
  
  public String[] requestedPermissions;
  
  public int[] requestedPermissionsFlags;
  
  public String requiredAccountType;
  
  public boolean requiredForAllUsers;
  
  public String restrictedAccountType;
  
  public ServiceInfo[] services;
  
  public String sharedUserId;
  
  public int sharedUserLabel;
  
  @Deprecated
  public Signature[] signatures;
  
  public SigningInfo signingInfo;
  
  public String[] splitNames;
  
  public int[] splitRevisionCodes;
  
  public String targetOverlayableName;
  
  @Deprecated
  public int versionCode;
  
  public int versionCodeMajor;
  
  public String versionName;
  
  public PackageInfo() {
    this.installLocation = 1;
  }
  
  private PackageInfo(Parcel paramParcel) {
    boolean bool2;
    boolean bool1 = true;
    this.installLocation = 1;
    this.packageName = paramParcel.readString8();
    this.splitNames = paramParcel.createString8Array();
    this.versionCode = paramParcel.readInt();
    this.versionCodeMajor = paramParcel.readInt();
    this.versionName = paramParcel.readString8();
    this.baseRevisionCode = paramParcel.readInt();
    this.splitRevisionCodes = paramParcel.createIntArray();
    this.sharedUserId = paramParcel.readString8();
    this.sharedUserLabel = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(paramParcel); 
    this.firstInstallTime = paramParcel.readLong();
    this.lastUpdateTime = paramParcel.readLong();
    this.gids = paramParcel.createIntArray();
    this.activities = (ActivityInfo[])paramParcel.createTypedArray(ActivityInfo.CREATOR);
    this.receivers = (ActivityInfo[])paramParcel.createTypedArray(ActivityInfo.CREATOR);
    this.services = (ServiceInfo[])paramParcel.createTypedArray(ServiceInfo.CREATOR);
    this.providers = (ProviderInfo[])paramParcel.createTypedArray(ProviderInfo.CREATOR);
    this.instrumentation = (InstrumentationInfo[])paramParcel.createTypedArray(InstrumentationInfo.CREATOR);
    this.permissions = (PermissionInfo[])paramParcel.createTypedArray(PermissionInfo.CREATOR);
    this.requestedPermissions = paramParcel.createString8Array();
    this.requestedPermissionsFlags = paramParcel.createIntArray();
    this.signatures = (Signature[])paramParcel.createTypedArray(Signature.CREATOR);
    this.configPreferences = (ConfigurationInfo[])paramParcel.createTypedArray(ConfigurationInfo.CREATOR);
    this.reqFeatures = (FeatureInfo[])paramParcel.createTypedArray(FeatureInfo.CREATOR);
    this.featureGroups = (FeatureGroupInfo[])paramParcel.createTypedArray(FeatureGroupInfo.CREATOR);
    this.installLocation = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.isStub = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.coreApp = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.requiredForAllUsers = bool2;
    this.restrictedAccountType = paramParcel.readString8();
    this.requiredAccountType = paramParcel.readString8();
    this.overlayTarget = paramParcel.readString8();
    this.overlayCategory = paramParcel.readString8();
    this.overlayPriority = paramParcel.readInt();
    this.mOverlayIsStatic = paramParcel.readBoolean();
    this.compileSdkVersion = paramParcel.readInt();
    this.compileSdkVersionCodename = paramParcel.readString8();
    if (paramParcel.readInt() != 0)
      this.signingInfo = (SigningInfo)SigningInfo.CREATOR.createFromParcel(paramParcel); 
    this.isApex = paramParcel.readBoolean();
  }
  
  public static long composeLongVersionCode(int paramInt1, int paramInt2) {
    return paramInt1 << 32L | paramInt2 & 0xFFFFFFFFL;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getLongVersionCode() {
    return composeLongVersionCode(this.versionCodeMajor, this.versionCode);
  }
  
  public boolean isOverlayPackage() {
    boolean bool;
    if (this.overlayTarget != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStaticOverlayPackage() {
    boolean bool;
    if (this.overlayTarget != null && this.mOverlayIsStatic) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setLongVersionCode(long paramLong) {
    this.versionCodeMajor = (int)(paramLong >> 32L);
    this.versionCode = (int)paramLong;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PackageInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    boolean bool = paramParcel.allowSquashing();
    paramParcel.writeString8(this.packageName);
    paramParcel.writeString8Array(this.splitNames);
    paramParcel.writeInt(this.versionCode);
    paramParcel.writeInt(this.versionCodeMajor);
    paramParcel.writeString8(this.versionName);
    paramParcel.writeInt(this.baseRevisionCode);
    paramParcel.writeIntArray(this.splitRevisionCodes);
    paramParcel.writeString8(this.sharedUserId);
    paramParcel.writeInt(this.sharedUserLabel);
    if (this.applicationInfo != null) {
      paramParcel.writeInt(1);
      this.applicationInfo.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeLong(this.firstInstallTime);
    paramParcel.writeLong(this.lastUpdateTime);
    paramParcel.writeIntArray(this.gids);
    paramParcel.writeTypedArray((Parcelable[])this.activities, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.receivers, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.services, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.providers, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.instrumentation, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.permissions, paramInt);
    paramParcel.writeString8Array(this.requestedPermissions);
    paramParcel.writeIntArray(this.requestedPermissionsFlags);
    paramParcel.writeTypedArray((Parcelable[])this.signatures, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.configPreferences, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.reqFeatures, paramInt);
    paramParcel.writeTypedArray((Parcelable[])this.featureGroups, paramInt);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeInt(this.isStub);
    paramParcel.writeInt(this.coreApp);
    paramParcel.writeInt(this.requiredForAllUsers);
    paramParcel.writeString8(this.restrictedAccountType);
    paramParcel.writeString8(this.requiredAccountType);
    paramParcel.writeString8(this.overlayTarget);
    paramParcel.writeString8(this.overlayCategory);
    paramParcel.writeInt(this.overlayPriority);
    paramParcel.writeBoolean(this.mOverlayIsStatic);
    paramParcel.writeInt(this.compileSdkVersion);
    paramParcel.writeString8(this.compileSdkVersionCodename);
    if (this.signingInfo != null) {
      paramParcel.writeInt(1);
      this.signingInfo.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeBoolean(this.isApex);
    paramParcel.restoreAllowSquashing(bool);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */