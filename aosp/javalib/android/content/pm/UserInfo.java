package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.DebugUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class UserInfo implements Parcelable {
  public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
      public UserInfo createFromParcel(Parcel param1Parcel) {
        return new UserInfo(param1Parcel);
      }
      
      public UserInfo[] newArray(int param1Int) {
        return new UserInfo[param1Int];
      }
    };
  
  public static final int FLAG_ADMIN = 2;
  
  @Deprecated
  public static final int FLAG_DEMO = 512;
  
  public static final int FLAG_DISABLED = 64;
  
  public static final int FLAG_EPHEMERAL = 256;
  
  public static final int FLAG_FULL = 1024;
  
  @Deprecated
  public static final int FLAG_GUEST = 4;
  
  public static final int FLAG_INITIALIZED = 16;
  
  @Deprecated
  public static final int FLAG_MANAGED_PROFILE = 32;
  
  public static final int FLAG_PRIMARY = 1;
  
  public static final int FLAG_PROFILE = 4096;
  
  public static final int FLAG_QUIET_MODE = 128;
  
  @Deprecated
  public static final int FLAG_RESTRICTED = 8;
  
  public static final int FLAG_SYSTEM = 2048;
  
  public static final int NO_PROFILE_GROUP_ID = -10000;
  
  public boolean convertedFromPreCreated;
  
  public long creationTime;
  
  public int flags;
  
  public boolean guestToRemove;
  
  public String iconPath;
  
  public int id;
  
  public String lastLoggedInFingerprint;
  
  public long lastLoggedInTime;
  
  public String name;
  
  public boolean partial;
  
  public boolean preCreated;
  
  public int profileBadge;
  
  public int profileGroupId;
  
  public int restrictedProfileParentId;
  
  public int serialNumber;
  
  public String userType;
  
  @Deprecated
  public UserInfo() {}
  
  public UserInfo(int paramInt1, String paramString, int paramInt2) {
    this(paramInt1, paramString, null, paramInt2);
  }
  
  public UserInfo(int paramInt1, String paramString1, String paramString2, int paramInt2) {
    this(paramInt1, paramString1, paramString2, paramInt2, getDefaultUserType(paramInt2));
  }
  
  public UserInfo(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3) {
    this.id = paramInt1;
    this.name = paramString1;
    this.flags = paramInt2;
    this.userType = paramString3;
    this.iconPath = paramString2;
    this.profileGroupId = -10000;
    this.restrictedProfileParentId = -10000;
  }
  
  public UserInfo(UserInfo paramUserInfo) {
    this.name = paramUserInfo.name;
    this.iconPath = paramUserInfo.iconPath;
    this.id = paramUserInfo.id;
    this.flags = paramUserInfo.flags;
    this.userType = paramUserInfo.userType;
    this.serialNumber = paramUserInfo.serialNumber;
    this.creationTime = paramUserInfo.creationTime;
    this.lastLoggedInTime = paramUserInfo.lastLoggedInTime;
    this.lastLoggedInFingerprint = paramUserInfo.lastLoggedInFingerprint;
    this.partial = paramUserInfo.partial;
    this.preCreated = paramUserInfo.preCreated;
    this.convertedFromPreCreated = paramUserInfo.convertedFromPreCreated;
    this.profileGroupId = paramUserInfo.profileGroupId;
    this.restrictedProfileParentId = paramUserInfo.restrictedProfileParentId;
    this.guestToRemove = paramUserInfo.guestToRemove;
    this.profileBadge = paramUserInfo.profileBadge;
  }
  
  private UserInfo(Parcel paramParcel) {
    this.id = paramParcel.readInt();
    this.name = paramParcel.readString8();
    this.iconPath = paramParcel.readString8();
    this.flags = paramParcel.readInt();
    this.userType = paramParcel.readString8();
    this.serialNumber = paramParcel.readInt();
    this.creationTime = paramParcel.readLong();
    this.lastLoggedInTime = paramParcel.readLong();
    this.lastLoggedInFingerprint = paramParcel.readString8();
    this.partial = paramParcel.readBoolean();
    this.preCreated = paramParcel.readBoolean();
    this.profileGroupId = paramParcel.readInt();
    this.guestToRemove = paramParcel.readBoolean();
    this.restrictedProfileParentId = paramParcel.readInt();
    this.profileBadge = paramParcel.readInt();
  }
  
  public static String flagsToString(int paramInt) {
    return DebugUtils.flagsToString(UserInfo.class, "FLAG_", paramInt);
  }
  
  public static String getDefaultUserType(int paramInt) {
    if ((paramInt & 0x800) == 0) {
      int i = paramInt & 0x22C;
      if (i != 0) {
        if (i != 4) {
          if (i != 8) {
            if (i != 32) {
              if (i == 512)
                return "android.os.usertype.full.DEMO"; 
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Cannot getDefaultUserType for flags ");
              stringBuilder1.append(Integer.toHexString(paramInt));
              stringBuilder1.append(" because it doesn't correspond to a valid user type.");
              throw new IllegalArgumentException(stringBuilder1.toString());
            } 
            return "android.os.usertype.profile.MANAGED";
          } 
          return "android.os.usertype.full.RESTRICTED";
        } 
        return "android.os.usertype.full.GUEST";
      } 
      return "android.os.usertype.full.SECONDARY";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Cannot getDefaultUserType for flags ");
    stringBuilder.append(Integer.toHexString(paramInt));
    stringBuilder.append(" because it corresponds to a SYSTEM user type.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static boolean isSystemOnly(int paramInt) {
    boolean bool;
    if (paramInt == 0 && UserManager.isSplitSystemUser()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean canHaveProfile() {
    boolean bool = isProfile();
    boolean bool1 = false;
    boolean bool2 = false;
    if (bool || isGuest() || isRestricted())
      return false; 
    if (UserManager.isSplitSystemUser() || UserManager.isHeadlessSystemUserMode()) {
      bool2 = bool1;
      if (this.id != 0)
        bool2 = true; 
      return bool2;
    } 
    if (this.id == 0)
      bool2 = true; 
    return bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public UserHandle getUserHandle() {
    return UserHandle.of(this.id);
  }
  
  public boolean isAdmin() {
    boolean bool;
    if ((this.flags & 0x2) == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDemo() {
    return UserManager.isUserTypeDemo(this.userType);
  }
  
  public boolean isEnabled() {
    boolean bool;
    if ((this.flags & 0x40) != 64) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isEphemeral() {
    boolean bool;
    if ((this.flags & 0x100) == 256) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isFull() {
    boolean bool;
    if ((this.flags & 0x400) == 1024) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isGuest() {
    return UserManager.isUserTypeGuest(this.userType);
  }
  
  public boolean isInitialized() {
    boolean bool;
    if ((this.flags & 0x10) == 16) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isManagedProfile() {
    return UserManager.isUserTypeManagedProfile(this.userType);
  }
  
  public boolean isPrimary() {
    int i = this.flags;
    boolean bool = true;
    if ((i & 0x1) != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isProfile() {
    boolean bool;
    if ((this.flags & 0x1000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isQuietModeEnabled() {
    boolean bool;
    if ((this.flags & 0x80) == 128) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRestricted() {
    return UserManager.isUserTypeRestricted(this.userType);
  }
  
  public boolean isSystemOnly() {
    return isSystemOnly(this.id);
  }
  
  public boolean supportsSwitchTo() {
    return (isEphemeral() && !isEnabled()) ? false : (this.preCreated ? false : (isProfile() ^ true));
  }
  
  public boolean supportsSwitchToByUser() {
    boolean bool;
    if ((!UserManager.isHeadlessSystemUserMode() || this.id != 0) && supportsSwitchTo()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toFullString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UserInfo[id=");
    stringBuilder.append(this.id);
    stringBuilder.append(", name=");
    stringBuilder.append(this.name);
    stringBuilder.append(", type=");
    stringBuilder.append(this.userType);
    stringBuilder.append(", flags=");
    stringBuilder.append(flagsToString(this.flags));
    boolean bool = this.preCreated;
    String str1 = "";
    if (bool) {
      str2 = " (pre-created)";
    } else {
      str2 = "";
    } 
    stringBuilder.append(str2);
    if (this.convertedFromPreCreated) {
      str2 = " (converted)";
    } else {
      str2 = "";
    } 
    stringBuilder.append(str2);
    String str2 = str1;
    if (this.partial)
      str2 = " (partial)"; 
    stringBuilder.append(str2);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UserInfo{");
    stringBuilder.append(this.id);
    stringBuilder.append(":");
    stringBuilder.append(this.name);
    stringBuilder.append(":");
    stringBuilder.append(Integer.toHexString(this.flags));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.id);
    paramParcel.writeString8(this.name);
    paramParcel.writeString8(this.iconPath);
    paramParcel.writeInt(this.flags);
    paramParcel.writeString8(this.userType);
    paramParcel.writeInt(this.serialNumber);
    paramParcel.writeLong(this.creationTime);
    paramParcel.writeLong(this.lastLoggedInTime);
    paramParcel.writeString8(this.lastLoggedInFingerprint);
    paramParcel.writeBoolean(this.partial);
    paramParcel.writeBoolean(this.preCreated);
    paramParcel.writeInt(this.profileGroupId);
    paramParcel.writeBoolean(this.guestToRemove);
    paramParcel.writeInt(this.restrictedProfileParentId);
    paramParcel.writeInt(this.profileBadge);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UserInfoFlag {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/UserInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */