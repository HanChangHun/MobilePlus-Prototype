package android.content.pm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.storage.StorageManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class Package implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Package>() {
      public PackageParser.Package createFromParcel(Parcel param2Parcel) {
        return new PackageParser.Package(param2Parcel);
      }
      
      public PackageParser.Package[] newArray(int param2Int) {
        return new PackageParser.Package[param2Int];
      }
    };
  
  public final ArrayList<PackageParser.Activity> activities;
  
  public ApplicationInfo applicationInfo = new ApplicationInfo();
  
  public String baseCodePath;
  
  public boolean baseHardwareAccelerated;
  
  public int baseRevisionCode;
  
  public ArrayList<Package> childPackages;
  
  public String codePath;
  
  public ArrayList<ConfigurationInfo> configPreferences;
  
  public boolean coreApp;
  
  public String cpuAbiOverride;
  
  public ArrayList<FeatureGroupInfo> featureGroups;
  
  public final ArrayList<String> implicitPermissions;
  
  public int installLocation;
  
  public final ArrayList<PackageParser.Instrumentation> instrumentation;
  
  public boolean isStub;
  
  public ArrayList<String> libraryNames;
  
  public ArrayList<String> mAdoptPermissions;
  
  public Bundle mAppMetaData;
  
  public int mCompileSdkVersion;
  
  public String mCompileSdkVersionCodename;
  
  public Object mExtras;
  
  public ArrayMap<String, ArraySet<PublicKey>> mKeySetMapping;
  
  public long[] mLastPackageUsageTimeInMills;
  
  public ArrayList<String> mOriginalPackages;
  
  public String mOverlayCategory;
  
  public boolean mOverlayIsStatic;
  
  public int mOverlayPriority;
  
  public String mOverlayTarget;
  
  public String mOverlayTargetName;
  
  public int mPreferredOrder;
  
  public String mRealPackage;
  
  public String mRequiredAccountType;
  
  public boolean mRequiredForAllUsers;
  
  public String mRestrictedAccountType;
  
  public String mSharedUserId;
  
  public int mSharedUserLabel;
  
  public PackageParser.SigningDetails mSigningDetails;
  
  public ArraySet<String> mUpgradeKeySets;
  
  public int mVersionCode;
  
  public int mVersionCodeMajor;
  
  public String mVersionName;
  
  public String manifestPackageName;
  
  public String packageName;
  
  public Package parentPackage;
  
  public final ArrayList<PackageParser.PermissionGroup> permissionGroups;
  
  public final ArrayList<PackageParser.Permission> permissions;
  
  public ArrayList<PackageParser.ActivityIntentInfo> preferredActivityFilters;
  
  public ArrayList<String> protectedBroadcasts;
  
  public final ArrayList<PackageParser.Provider> providers;
  
  public final ArrayList<PackageParser.Activity> receivers;
  
  public ArrayList<FeatureInfo> reqFeatures;
  
  public final ArrayList<String> requestedPermissions;
  
  public byte[] restrictUpdateHash;
  
  public final ArrayList<PackageParser.Service> services;
  
  public String[] splitCodePaths;
  
  public int[] splitFlags;
  
  public String[] splitNames;
  
  public int[] splitPrivateFlags;
  
  public int[] splitRevisionCodes;
  
  public String staticSharedLibName;
  
  public long staticSharedLibVersion;
  
  public boolean use32bitAbi;
  
  public ArrayList<String> usesLibraries;
  
  public String[] usesLibraryFiles;
  
  public ArrayList<SharedLibraryInfo> usesLibraryInfos;
  
  public ArrayList<String> usesOptionalLibraries;
  
  public ArrayList<String> usesStaticLibraries;
  
  public String[][] usesStaticLibrariesCertDigests;
  
  public long[] usesStaticLibrariesVersions;
  
  public boolean visibleToInstantApps;
  
  public String volumeUuid;
  
  public Package(Parcel paramParcel) {
    boolean bool1 = false;
    this.permissions = new ArrayList<>(0);
    this.permissionGroups = new ArrayList<>(0);
    this.activities = new ArrayList<>(0);
    this.receivers = new ArrayList<>(0);
    this.providers = new ArrayList<>(0);
    this.services = new ArrayList<>(0);
    this.instrumentation = new ArrayList<>(0);
    this.requestedPermissions = new ArrayList<>();
    this.implicitPermissions = new ArrayList<>();
    this.staticSharedLibName = null;
    this.staticSharedLibVersion = 0L;
    this.libraryNames = null;
    this.usesLibraries = null;
    this.usesStaticLibraries = null;
    this.usesStaticLibrariesVersions = null;
    this.usesStaticLibrariesCertDigests = null;
    this.usesOptionalLibraries = null;
    this.usesLibraryFiles = null;
    this.usesLibraryInfos = null;
    this.preferredActivityFilters = null;
    this.mOriginalPackages = null;
    this.mRealPackage = null;
    this.mAdoptPermissions = null;
    this.mAppMetaData = null;
    this.mSigningDetails = PackageParser.SigningDetails.UNKNOWN;
    this.mPreferredOrder = 0;
    this.mLastPackageUsageTimeInMills = new long[8];
    this.configPreferences = null;
    this.reqFeatures = null;
    this.featureGroups = null;
    ClassLoader classLoader = Object.class.getClassLoader();
    this.packageName = paramParcel.readString().intern();
    this.manifestPackageName = paramParcel.readString();
    this.splitNames = paramParcel.readStringArray();
    this.volumeUuid = paramParcel.readString();
    this.codePath = paramParcel.readString();
    this.baseCodePath = paramParcel.readString();
    this.splitCodePaths = paramParcel.readStringArray();
    this.baseRevisionCode = paramParcel.readInt();
    this.splitRevisionCodes = paramParcel.createIntArray();
    this.splitFlags = paramParcel.createIntArray();
    this.splitPrivateFlags = paramParcel.createIntArray();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.baseHardwareAccelerated = bool2;
    ApplicationInfo applicationInfo = (ApplicationInfo)paramParcel.readParcelable(classLoader);
    this.applicationInfo = applicationInfo;
    if (applicationInfo.permission != null) {
      applicationInfo = this.applicationInfo;
      applicationInfo.permission = applicationInfo.permission.intern();
    } 
    paramParcel.readParcelableList(this.permissions, classLoader);
    fixupOwner((List)this.permissions);
    paramParcel.readParcelableList(this.permissionGroups, classLoader);
    fixupOwner((List)this.permissionGroups);
    paramParcel.readParcelableList(this.activities, classLoader);
    fixupOwner((List)this.activities);
    paramParcel.readParcelableList(this.receivers, classLoader);
    fixupOwner((List)this.receivers);
    paramParcel.readParcelableList(this.providers, classLoader);
    fixupOwner((List)this.providers);
    paramParcel.readParcelableList(this.services, classLoader);
    fixupOwner((List)this.services);
    paramParcel.readParcelableList(this.instrumentation, classLoader);
    fixupOwner((List)this.instrumentation);
    paramParcel.readStringList(this.requestedPermissions);
    internStringArrayList(this.requestedPermissions);
    paramParcel.readStringList(this.implicitPermissions);
    internStringArrayList(this.implicitPermissions);
    ArrayList<String> arrayList2 = paramParcel.createStringArrayList();
    this.protectedBroadcasts = arrayList2;
    internStringArrayList(arrayList2);
    this.parentPackage = (Package)paramParcel.readParcelable(classLoader);
    arrayList2 = new ArrayList<>();
    this.childPackages = (ArrayList)arrayList2;
    paramParcel.readParcelableList(arrayList2, classLoader);
    if (this.childPackages.size() == 0)
      this.childPackages = null; 
    String str2 = paramParcel.readString();
    this.staticSharedLibName = str2;
    if (str2 != null)
      this.staticSharedLibName = str2.intern(); 
    this.staticSharedLibVersion = paramParcel.readLong();
    ArrayList<String> arrayList1 = paramParcel.createStringArrayList();
    this.libraryNames = arrayList1;
    internStringArrayList(arrayList1);
    arrayList1 = paramParcel.createStringArrayList();
    this.usesLibraries = arrayList1;
    internStringArrayList(arrayList1);
    arrayList1 = paramParcel.createStringArrayList();
    this.usesOptionalLibraries = arrayList1;
    internStringArrayList(arrayList1);
    this.usesLibraryFiles = paramParcel.readStringArray();
    this.usesLibraryInfos = paramParcel.createTypedArrayList(SharedLibraryInfo.CREATOR);
    int i = paramParcel.readInt();
    if (i > 0) {
      arrayList1 = new ArrayList<>(i);
      this.usesStaticLibraries = arrayList1;
      paramParcel.readStringList(arrayList1);
      internStringArrayList(this.usesStaticLibraries);
      long[] arrayOfLong = new long[i];
      this.usesStaticLibrariesVersions = arrayOfLong;
      paramParcel.readLongArray(arrayOfLong);
      this.usesStaticLibrariesCertDigests = new String[i][];
      for (byte b = 0; b < i; b++)
        this.usesStaticLibrariesCertDigests[b] = paramParcel.createStringArray(); 
    } 
    arrayList1 = new ArrayList<>();
    this.preferredActivityFilters = (ArrayList)arrayList1;
    paramParcel.readParcelableList(arrayList1, classLoader);
    if (this.preferredActivityFilters.size() == 0)
      this.preferredActivityFilters = null; 
    this.mOriginalPackages = paramParcel.createStringArrayList();
    this.mRealPackage = paramParcel.readString();
    this.mAdoptPermissions = paramParcel.createStringArrayList();
    this.mAppMetaData = paramParcel.readBundle();
    this.mVersionCode = paramParcel.readInt();
    this.mVersionCodeMajor = paramParcel.readInt();
    String str1 = paramParcel.readString();
    this.mVersionName = str1;
    if (str1 != null)
      this.mVersionName = str1.intern(); 
    str1 = paramParcel.readString();
    this.mSharedUserId = str1;
    if (str1 != null)
      this.mSharedUserId = str1.intern(); 
    this.mSharedUserLabel = paramParcel.readInt();
    this.mSigningDetails = (PackageParser.SigningDetails)paramParcel.readParcelable(classLoader);
    this.mPreferredOrder = paramParcel.readInt();
    ArrayList<ConfigurationInfo> arrayList = new ArrayList();
    this.configPreferences = arrayList;
    paramParcel.readParcelableList(arrayList, classLoader);
    if (this.configPreferences.size() == 0)
      this.configPreferences = null; 
    arrayList = new ArrayList<>();
    this.reqFeatures = (ArrayList)arrayList;
    paramParcel.readParcelableList(arrayList, classLoader);
    if (this.reqFeatures.size() == 0)
      this.reqFeatures = null; 
    arrayList = new ArrayList<>();
    this.featureGroups = (ArrayList)arrayList;
    paramParcel.readParcelableList(arrayList, classLoader);
    if (this.featureGroups.size() == 0)
      this.featureGroups = null; 
    this.installLocation = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.coreApp = bool2;
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mRequiredForAllUsers = bool2;
    this.mRestrictedAccountType = paramParcel.readString();
    this.mRequiredAccountType = paramParcel.readString();
    this.mOverlayTarget = paramParcel.readString();
    this.mOverlayTargetName = paramParcel.readString();
    this.mOverlayCategory = paramParcel.readString();
    this.mOverlayPriority = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mOverlayIsStatic = bool2;
    this.mCompileSdkVersion = paramParcel.readInt();
    this.mCompileSdkVersionCodename = paramParcel.readString();
    this.mUpgradeKeySets = paramParcel.readArraySet(classLoader);
    this.mKeySetMapping = readKeySetMapping(paramParcel);
    this.cpuAbiOverride = paramParcel.readString();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.use32bitAbi = bool2;
    this.restrictUpdateHash = paramParcel.createByteArray();
    boolean bool2 = bool1;
    if (paramParcel.readInt() == 1)
      bool2 = true; 
    this.visibleToInstantApps = bool2;
  }
  
  public Package(String paramString) {
    this.permissions = new ArrayList<>(0);
    this.permissionGroups = new ArrayList<>(0);
    this.activities = new ArrayList<>(0);
    this.receivers = new ArrayList<>(0);
    this.providers = new ArrayList<>(0);
    this.services = new ArrayList<>(0);
    this.instrumentation = new ArrayList<>(0);
    this.requestedPermissions = new ArrayList<>();
    this.implicitPermissions = new ArrayList<>();
    this.staticSharedLibName = null;
    this.staticSharedLibVersion = 0L;
    this.libraryNames = null;
    this.usesLibraries = null;
    this.usesStaticLibraries = null;
    this.usesStaticLibrariesVersions = null;
    this.usesStaticLibrariesCertDigests = null;
    this.usesOptionalLibraries = null;
    this.usesLibraryFiles = null;
    this.usesLibraryInfos = null;
    this.preferredActivityFilters = null;
    this.mOriginalPackages = null;
    this.mRealPackage = null;
    this.mAdoptPermissions = null;
    this.mAppMetaData = null;
    this.mSigningDetails = PackageParser.SigningDetails.UNKNOWN;
    this.mPreferredOrder = 0;
    this.mLastPackageUsageTimeInMills = new long[8];
    this.configPreferences = null;
    this.reqFeatures = null;
    this.featureGroups = null;
    this.packageName = paramString;
    this.manifestPackageName = paramString;
    this.applicationInfo.packageName = paramString;
    this.applicationInfo.uid = -1;
  }
  
  private static void internStringArrayList(List<String> paramList) {
    if (paramList != null) {
      int i = paramList.size();
      for (byte b = 0; b < i; b++)
        paramList.set(b, ((String)paramList.get(b)).intern()); 
    } 
  }
  
  private static ArrayMap<String, ArraySet<PublicKey>> readKeySetMapping(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i == -1)
      return null; 
    ArrayMap<String, ArraySet<PublicKey>> arrayMap = new ArrayMap();
    for (byte b = 0; b < i; b++) {
      String str = paramParcel.readString();
      int j = paramParcel.readInt();
      if (j == -1) {
        arrayMap.put(str, null);
      } else {
        ArraySet arraySet = new ArraySet(j);
        for (byte b1 = 0; b1 < j; b1++)
          arraySet.add(paramParcel.readSerializable()); 
        arrayMap.put(str, arraySet);
      } 
    } 
    return arrayMap;
  }
  
  private static void writeKeySetMapping(Parcel paramParcel, ArrayMap<String, ArraySet<PublicKey>> paramArrayMap) {
    if (paramArrayMap == null) {
      paramParcel.writeInt(-1);
      return;
    } 
    int i = paramArrayMap.size();
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++) {
      paramParcel.writeString((String)paramArrayMap.keyAt(b));
      ArraySet arraySet = (ArraySet)paramArrayMap.valueAt(b);
      if (arraySet == null) {
        paramParcel.writeInt(-1);
      } else {
        int j = arraySet.size();
        paramParcel.writeInt(j);
        for (byte b1 = 0; b1 < j; b1++)
          paramParcel.writeSerializable((Serializable)arraySet.valueAt(b1)); 
      } 
    } 
  }
  
  public boolean canHaveOatDir() {
    return true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void fixupOwner(List<? extends PackageParser.Component<?>> paramList) {
    if (paramList != null)
      for (PackageParser.Component<?> component : paramList) {
        component.owner = this;
        if (component instanceof PackageParser.Activity) {
          ((PackageParser.Activity)component).info.applicationInfo = this.applicationInfo;
          continue;
        } 
        if (component instanceof PackageParser.Service) {
          ((PackageParser.Service)component).info.applicationInfo = this.applicationInfo;
          continue;
        } 
        if (component instanceof PackageParser.Provider)
          ((PackageParser.Provider)component).info.applicationInfo = this.applicationInfo; 
      }  
  }
  
  public List<String> getAllCodePaths() {
    ArrayList<String> arrayList = new ArrayList();
    arrayList.add(this.baseCodePath);
    if (!ArrayUtils.isEmpty((Object[])this.splitCodePaths))
      Collections.addAll(arrayList, this.splitCodePaths); 
    return arrayList;
  }
  
  public List<String> getAllCodePathsExcludingResourceOnly() {
    ArrayList<String> arrayList = new ArrayList();
    if ((this.applicationInfo.flags & 0x4) != 0)
      arrayList.add(this.baseCodePath); 
    if (!ArrayUtils.isEmpty((Object[])this.splitCodePaths)) {
      byte b = 0;
      while (true) {
        String[] arrayOfString = this.splitCodePaths;
        if (b < arrayOfString.length) {
          if ((this.splitFlags[b] & 0x4) != 0)
            arrayList.add(arrayOfString[b]); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    return arrayList;
  }
  
  public List<String> getChildPackageNames() {
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList == null)
      return null; 
    int i = arrayList.size();
    arrayList = new ArrayList<>(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(((Package)this.childPackages.get(b)).packageName); 
    return (List)arrayList;
  }
  
  public long getLatestForegroundPackageUseTimeInMills() {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 2;
    long l = 0L;
    int i = arrayOfInt.length;
    for (byte b = 0; b < i; b++) {
      int j = arrayOfInt[b];
      l = Math.max(l, this.mLastPackageUsageTimeInMills[j]);
    } 
    return l;
  }
  
  public long getLatestPackageUseTimeInMills() {
    long l = 0L;
    long[] arrayOfLong = this.mLastPackageUsageTimeInMills;
    int i = arrayOfLong.length;
    for (byte b = 0; b < i; b++)
      l = Math.max(l, arrayOfLong[b]); 
    return l;
  }
  
  public long getLongVersionCode() {
    return PackageInfo.composeLongVersionCode(this.mVersionCodeMajor, this.mVersionCode);
  }
  
  public boolean hasChildPackage(String paramString) {
    byte b1;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      b1 = arrayList.size();
    } else {
      b1 = 0;
    } 
    for (byte b2 = 0; b2 < b1; b2++) {
      if (((Package)this.childPackages.get(b2)).packageName.equals(paramString))
        return true; 
    } 
    return false;
  }
  
  public boolean hasComponentClassName(String paramString) {
    int i;
    for (i = this.activities.size() - 1; i >= 0; i--) {
      if (paramString.equals(((PackageParser.Activity)this.activities.get(i)).className))
        return true; 
    } 
    for (i = this.receivers.size() - 1; i >= 0; i--) {
      if (paramString.equals(((PackageParser.Activity)this.receivers.get(i)).className))
        return true; 
    } 
    for (i = this.providers.size() - 1; i >= 0; i--) {
      if (paramString.equals(((PackageParser.Provider)this.providers.get(i)).className))
        return true; 
    } 
    for (i = this.services.size() - 1; i >= 0; i--) {
      if (paramString.equals(((PackageParser.Service)this.services.get(i)).className))
        return true; 
    } 
    for (i = this.instrumentation.size() - 1; i >= 0; i--) {
      if (paramString.equals(((PackageParser.Instrumentation)this.instrumentation.get(i)).className))
        return true; 
    } 
    return false;
  }
  
  public boolean isExternal() {
    return this.applicationInfo.isExternal();
  }
  
  public boolean isForwardLocked() {
    return false;
  }
  
  public boolean isLibrary() {
    return (this.staticSharedLibName != null || !ArrayUtils.isEmpty(this.libraryNames));
  }
  
  public boolean isMatch(int paramInt) {
    return ((0x100000 & paramInt) != 0) ? isSystem() : true;
  }
  
  public boolean isOdm() {
    return this.applicationInfo.isOdm();
  }
  
  public boolean isOem() {
    return this.applicationInfo.isOem();
  }
  
  public boolean isPrivileged() {
    return this.applicationInfo.isPrivilegedApp();
  }
  
  public boolean isProduct() {
    return this.applicationInfo.isProduct();
  }
  
  public boolean isSystem() {
    return this.applicationInfo.isSystemApp();
  }
  
  public boolean isSystemExt() {
    return this.applicationInfo.isSystemExt();
  }
  
  public boolean isUpdatedSystemApp() {
    return this.applicationInfo.isUpdatedSystemApp();
  }
  
  public boolean isVendor() {
    return this.applicationInfo.isVendor();
  }
  
  public void setApplicationInfoBaseCodePath(String paramString) {
    this.applicationInfo.setBaseCodePath(paramString);
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).applicationInfo.setBaseCodePath(paramString); 
    } 
  }
  
  @Deprecated
  public void setApplicationInfoBaseResourcePath(String paramString) {
    this.applicationInfo.setBaseResourcePath(paramString);
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).applicationInfo.setBaseResourcePath(paramString); 
    } 
  }
  
  public void setApplicationInfoCodePath(String paramString) {
    this.applicationInfo.setCodePath(paramString);
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).applicationInfo.setCodePath(paramString); 
    } 
  }
  
  public void setApplicationInfoFlags(int paramInt1, int paramInt2) {
    ApplicationInfo applicationInfo = this.applicationInfo;
    applicationInfo.flags = applicationInfo.flags & paramInt1 | paramInt1 & paramInt2;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).applicationInfo.flags = this.applicationInfo.flags & paramInt1 | paramInt1 & paramInt2; 
    } 
  }
  
  @Deprecated
  public void setApplicationInfoResourcePath(String paramString) {
    this.applicationInfo.setResourcePath(paramString);
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).applicationInfo.setResourcePath(paramString); 
    } 
  }
  
  public void setApplicationInfoSplitCodePaths(String[] paramArrayOfString) {
    this.applicationInfo.setSplitCodePaths(paramArrayOfString);
  }
  
  @Deprecated
  public void setApplicationInfoSplitResourcePaths(String[] paramArrayOfString) {
    this.applicationInfo.setSplitResourcePaths(paramArrayOfString);
  }
  
  public void setApplicationVolumeUuid(String paramString) {
    UUID uUID = StorageManager.convert(paramString);
    this.applicationInfo.volumeUuid = paramString;
    this.applicationInfo.storageUuid = uUID;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++) {
        ((Package)this.childPackages.get(b)).applicationInfo.volumeUuid = paramString;
        ((Package)this.childPackages.get(b)).applicationInfo.storageUuid = uUID;
      } 
    } 
  }
  
  public void setBaseCodePath(String paramString) {
    this.baseCodePath = paramString;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).baseCodePath = paramString; 
    } 
  }
  
  public void setCodePath(String paramString) {
    this.codePath = paramString;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).codePath = paramString; 
    } 
  }
  
  public void setPackageName(String paramString) {
    this.packageName = paramString;
    this.applicationInfo.packageName = paramString;
    int i;
    for (i = this.permissions.size() - 1; i >= 0; i--)
      ((PackageParser.Permission)this.permissions.get(i)).setPackageName(paramString); 
    for (i = this.permissionGroups.size() - 1; i >= 0; i--)
      ((PackageParser.PermissionGroup)this.permissionGroups.get(i)).setPackageName(paramString); 
    for (i = this.activities.size() - 1; i >= 0; i--)
      ((PackageParser.Activity)this.activities.get(i)).setPackageName(paramString); 
    for (i = this.receivers.size() - 1; i >= 0; i--)
      ((PackageParser.Activity)this.receivers.get(i)).setPackageName(paramString); 
    for (i = this.providers.size() - 1; i >= 0; i--)
      ((PackageParser.Provider)this.providers.get(i)).setPackageName(paramString); 
    for (i = this.services.size() - 1; i >= 0; i--)
      ((PackageParser.Service)this.services.get(i)).setPackageName(paramString); 
    for (i = this.instrumentation.size() - 1; i >= 0; i--)
      ((PackageParser.Instrumentation)this.instrumentation.get(i)).setPackageName(paramString); 
  }
  
  public void setSigningDetails(PackageParser.SigningDetails paramSigningDetails) {
    this.mSigningDetails = paramSigningDetails;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).mSigningDetails = paramSigningDetails; 
    } 
  }
  
  public void setSplitCodePaths(String[] paramArrayOfString) {
    this.splitCodePaths = paramArrayOfString;
  }
  
  public void setUse32bitAbi(boolean paramBoolean) {
    this.use32bitAbi = paramBoolean;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).use32bitAbi = paramBoolean; 
    } 
  }
  
  public void setVolumeUuid(String paramString) {
    this.volumeUuid = paramString;
    ArrayList<Package> arrayList = this.childPackages;
    if (arrayList != null) {
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Package)this.childPackages.get(b)).volumeUuid = paramString; 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Package{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.packageName);
    paramParcel.writeString(this.manifestPackageName);
    paramParcel.writeStringArray(this.splitNames);
    paramParcel.writeString(this.volumeUuid);
    paramParcel.writeString(this.codePath);
    paramParcel.writeString(this.baseCodePath);
    paramParcel.writeStringArray(this.splitCodePaths);
    paramParcel.writeInt(this.baseRevisionCode);
    paramParcel.writeIntArray(this.splitRevisionCodes);
    paramParcel.writeIntArray(this.splitFlags);
    paramParcel.writeIntArray(this.splitPrivateFlags);
    paramParcel.writeInt(this.baseHardwareAccelerated);
    paramParcel.writeParcelable(this.applicationInfo, paramInt);
    paramParcel.writeParcelableList(this.permissions, paramInt);
    paramParcel.writeParcelableList(this.permissionGroups, paramInt);
    paramParcel.writeParcelableList(this.activities, paramInt);
    paramParcel.writeParcelableList(this.receivers, paramInt);
    paramParcel.writeParcelableList(this.providers, paramInt);
    paramParcel.writeParcelableList(this.services, paramInt);
    paramParcel.writeParcelableList(this.instrumentation, paramInt);
    paramParcel.writeStringList(this.requestedPermissions);
    paramParcel.writeStringList(this.implicitPermissions);
    paramParcel.writeStringList(this.protectedBroadcasts);
    paramParcel.writeParcelable(this.parentPackage, paramInt);
    paramParcel.writeParcelableList(this.childPackages, paramInt);
    paramParcel.writeString(this.staticSharedLibName);
    paramParcel.writeLong(this.staticSharedLibVersion);
    paramParcel.writeStringList(this.libraryNames);
    paramParcel.writeStringList(this.usesLibraries);
    paramParcel.writeStringList(this.usesOptionalLibraries);
    paramParcel.writeStringArray(this.usesLibraryFiles);
    paramParcel.writeTypedList(this.usesLibraryInfos);
    if (ArrayUtils.isEmpty(this.usesStaticLibraries)) {
      paramParcel.writeInt(-1);
    } else {
      paramParcel.writeInt(this.usesStaticLibraries.size());
      paramParcel.writeStringList(this.usesStaticLibraries);
      paramParcel.writeLongArray(this.usesStaticLibrariesVersions);
      String[][] arrayOfString = this.usesStaticLibrariesCertDigests;
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        paramParcel.writeStringArray(arrayOfString[b]); 
    } 
    paramParcel.writeParcelableList(this.preferredActivityFilters, paramInt);
    paramParcel.writeStringList(this.mOriginalPackages);
    paramParcel.writeString(this.mRealPackage);
    paramParcel.writeStringList(this.mAdoptPermissions);
    paramParcel.writeBundle(this.mAppMetaData);
    paramParcel.writeInt(this.mVersionCode);
    paramParcel.writeInt(this.mVersionCodeMajor);
    paramParcel.writeString(this.mVersionName);
    paramParcel.writeString(this.mSharedUserId);
    paramParcel.writeInt(this.mSharedUserLabel);
    paramParcel.writeParcelable(this.mSigningDetails, paramInt);
    paramParcel.writeInt(this.mPreferredOrder);
    paramParcel.writeParcelableList(this.configPreferences, paramInt);
    paramParcel.writeParcelableList(this.reqFeatures, paramInt);
    paramParcel.writeParcelableList(this.featureGroups, paramInt);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeInt(this.coreApp);
    paramParcel.writeInt(this.mRequiredForAllUsers);
    paramParcel.writeString(this.mRestrictedAccountType);
    paramParcel.writeString(this.mRequiredAccountType);
    paramParcel.writeString(this.mOverlayTarget);
    paramParcel.writeString(this.mOverlayTargetName);
    paramParcel.writeString(this.mOverlayCategory);
    paramParcel.writeInt(this.mOverlayPriority);
    paramParcel.writeInt(this.mOverlayIsStatic);
    paramParcel.writeInt(this.mCompileSdkVersion);
    paramParcel.writeString(this.mCompileSdkVersionCodename);
    paramParcel.writeArraySet(this.mUpgradeKeySets);
    writeKeySetMapping(paramParcel, this.mKeySetMapping);
    paramParcel.writeString(this.cpuAbiOverride);
    paramParcel.writeInt(this.use32bitAbi);
    paramParcel.writeByteArray(this.restrictUpdateHash);
    paramParcel.writeInt(this.visibleToInstantApps);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Package.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */