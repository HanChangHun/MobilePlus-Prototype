package android.content.pm;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PermissionInfo extends PackageItemInfo implements Parcelable {
  public static final Parcelable.Creator<PermissionInfo> CREATOR = new Parcelable.Creator<PermissionInfo>() {
      public PermissionInfo createFromParcel(Parcel param1Parcel) {
        return new PermissionInfo(param1Parcel);
      }
      
      public PermissionInfo[] newArray(int param1Int) {
        return new PermissionInfo[param1Int];
      }
    };
  
  public static final int FLAG_COSTS_MONEY = 1;
  
  public static final int FLAG_HARD_RESTRICTED = 4;
  
  public static final int FLAG_IMMUTABLY_RESTRICTED = 16;
  
  public static final int FLAG_INSTALLED = 1073741824;
  
  @SystemApi
  public static final int FLAG_REMOVED = 2;
  
  public static final int FLAG_SOFT_RESTRICTED = 8;
  
  public static final int PROTECTION_DANGEROUS = 1;
  
  public static final int PROTECTION_FLAG_APPOP = 64;
  
  @SystemApi
  public static final int PROTECTION_FLAG_APP_PREDICTOR = 2097152;
  
  @SystemApi
  public static final int PROTECTION_FLAG_COMPANION = 8388608;
  
  @SystemApi
  public static final int PROTECTION_FLAG_CONFIGURATOR = 524288;
  
  public static final int PROTECTION_FLAG_DEVELOPMENT = 32;
  
  @SystemApi
  public static final int PROTECTION_FLAG_DOCUMENTER = 262144;
  
  @SystemApi
  public static final int PROTECTION_FLAG_INCIDENT_REPORT_APPROVER = 1048576;
  
  public static final int PROTECTION_FLAG_INSTALLER = 256;
  
  public static final int PROTECTION_FLAG_INSTANT = 4096;
  
  @SystemApi
  public static final int PROTECTION_FLAG_OEM = 16384;
  
  public static final int PROTECTION_FLAG_PRE23 = 128;
  
  public static final int PROTECTION_FLAG_PREINSTALLED = 1024;
  
  public static final int PROTECTION_FLAG_PRIVILEGED = 16;
  
  @SystemApi
  public static final int PROTECTION_FLAG_RETAIL_DEMO = 16777216;
  
  public static final int PROTECTION_FLAG_RUNTIME_ONLY = 8192;
  
  public static final int PROTECTION_FLAG_SETUP = 2048;
  
  @Deprecated
  public static final int PROTECTION_FLAG_SYSTEM = 16;
  
  @SystemApi
  public static final int PROTECTION_FLAG_SYSTEM_TEXT_CLASSIFIER = 65536;
  
  public static final int PROTECTION_FLAG_VENDOR_PRIVILEGED = 32768;
  
  public static final int PROTECTION_FLAG_VERIFIER = 512;
  
  @SystemApi
  public static final int PROTECTION_FLAG_WELLBEING = 131072;
  
  @Deprecated
  public static final int PROTECTION_MASK_BASE = 15;
  
  @Deprecated
  public static final int PROTECTION_MASK_FLAGS = 65520;
  
  public static final int PROTECTION_NORMAL = 0;
  
  public static final int PROTECTION_SIGNATURE = 2;
  
  @Deprecated
  public static final int PROTECTION_SIGNATURE_OR_SYSTEM = 3;
  
  @SystemApi
  public final String backgroundPermission;
  
  public int descriptionRes;
  
  public int flags;
  
  public String group;
  
  public CharSequence nonLocalizedDescription;
  
  @Deprecated
  public int protectionLevel;
  
  @SystemApi
  public int requestRes;
  
  @Deprecated
  public PermissionInfo() {
    this((String)null);
  }
  
  @Deprecated
  public PermissionInfo(PermissionInfo paramPermissionInfo) {
    super(paramPermissionInfo);
    this.protectionLevel = paramPermissionInfo.protectionLevel;
    this.flags = paramPermissionInfo.flags;
    this.group = paramPermissionInfo.group;
    this.backgroundPermission = paramPermissionInfo.backgroundPermission;
    this.descriptionRes = paramPermissionInfo.descriptionRes;
    this.requestRes = paramPermissionInfo.requestRes;
    this.nonLocalizedDescription = paramPermissionInfo.nonLocalizedDescription;
  }
  
  private PermissionInfo(Parcel paramParcel) {
    super(paramParcel);
    this.protectionLevel = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    this.group = paramParcel.readString8();
    this.backgroundPermission = paramParcel.readString8();
    this.descriptionRes = paramParcel.readInt();
    this.requestRes = paramParcel.readInt();
    this.nonLocalizedDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
  }
  
  public PermissionInfo(String paramString) {
    this.backgroundPermission = paramString;
  }
  
  public static int fixProtectionLevel(int paramInt) {
    int i = paramInt;
    if (paramInt == 3)
      i = 18; 
    paramInt = i;
    if ((0x8000 & i) != 0) {
      paramInt = i;
      if ((i & 0x10) == 0)
        paramInt = i & 0xFFFF7FFF; 
    } 
    return paramInt;
  }
  
  public static String protectionToString(int paramInt) {
    String str1 = "????";
    int i = paramInt & 0xF;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i == 3)
            str1 = "signatureOrSystem"; 
        } else {
          str1 = "signature";
        } 
      } else {
        str1 = "dangerous";
      } 
    } else {
      str1 = "normal";
    } 
    String str2 = str1;
    if ((paramInt & 0x10) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|privileged");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((paramInt & 0x20) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|development");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((paramInt & 0x40) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|appop");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((paramInt & 0x80) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|pre23");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((paramInt & 0x100) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|installer");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((paramInt & 0x200) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|verifier");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((paramInt & 0x400) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|preinstalled");
      str2 = stringBuilder.toString();
    } 
    String str3 = str2;
    if ((paramInt & 0x800) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|setup");
      str3 = stringBuilder.toString();
    } 
    str1 = str3;
    if ((paramInt & 0x1000) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str3);
      stringBuilder.append("|instant");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((paramInt & 0x2000) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|runtime");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((paramInt & 0x4000) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|oem");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((0x8000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|vendorPrivileged");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((0x10000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|textClassifier");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((0x20000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|wellbeing");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((0x40000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|documenter");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((0x80000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|configurator");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((0x100000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|incidentReportApprover");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((0x200000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|appPredictor");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if ((0x800000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("|companion");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if ((0x1000000 & paramInt) != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("|retailDemo");
      str2 = stringBuilder.toString();
    } 
    return str2;
  }
  
  public int calculateFootprint() {
    int i = this.name.length();
    int j = i;
    if (this.nonLocalizedLabel != null)
      j = i + this.nonLocalizedLabel.length(); 
    CharSequence charSequence = this.nonLocalizedDescription;
    i = j;
    if (charSequence != null)
      i = j + charSequence.length(); 
    return i;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getProtection() {
    return this.protectionLevel & 0xF;
  }
  
  public int getProtectionFlags() {
    return this.protectionLevel & 0xFFFFFFF0;
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
  
  public boolean isHardRestricted() {
    boolean bool;
    if ((this.flags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRestricted() {
    return (isHardRestricted() || isSoftRestricted());
  }
  
  public boolean isRuntime() {
    int i = getProtection();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isSoftRestricted() {
    boolean bool;
    if ((this.flags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public CharSequence loadDescription(PackageManager paramPackageManager) {
    CharSequence charSequence = this.nonLocalizedDescription;
    if (charSequence != null)
      return charSequence; 
    if (this.descriptionRes != 0) {
      CharSequence charSequence1 = paramPackageManager.getText(this.packageName, this.descriptionRes, null);
      if (charSequence1 != null)
        return charSequence1; 
    } 
    return null;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PermissionInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.protectionLevel);
    paramParcel.writeInt(this.flags);
    paramParcel.writeString8(this.group);
    paramParcel.writeString8(this.backgroundPermission);
    paramParcel.writeInt(this.descriptionRes);
    paramParcel.writeInt(this.requestRes);
    TextUtils.writeToParcel(this.nonLocalizedDescription, paramParcel, paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Flags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Protection {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProtectionFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PermissionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */