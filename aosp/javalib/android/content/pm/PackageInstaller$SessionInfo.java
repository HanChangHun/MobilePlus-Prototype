package android.content.pm;

import android.annotation.SystemApi;
import android.app.AppGlobals;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SessionInfo implements Parcelable {
  public static final Parcelable.Creator<SessionInfo> CREATOR;
  
  public static final int INVALID_ID = -1;
  
  private static final int[] NO_SESSIONS = new int[0];
  
  public static final int STAGED_SESSION_ACTIVATION_FAILED = 2;
  
  public static final int STAGED_SESSION_NO_ERROR = 0;
  
  public static final int STAGED_SESSION_UNKNOWN = 3;
  
  public static final int STAGED_SESSION_VERIFICATION_FAILED = 1;
  
  public boolean active;
  
  public Bitmap appIcon;
  
  public CharSequence appLabel;
  
  public String appPackageName;
  
  public int autoRevokePermissionsMode;
  
  public int[] childSessionIds;
  
  public long createdMillis;
  
  public boolean forceQueryable;
  
  public String[] grantedRuntimePermissions;
  
  public int installFlags;
  
  public int installLocation;
  
  public int installReason;
  
  public String installerPackageName;
  
  public boolean isCommitted;
  
  public boolean isMultiPackage;
  
  public boolean isStaged;
  
  public boolean isStagedSessionApplied;
  
  public boolean isStagedSessionFailed;
  
  public boolean isStagedSessionReady;
  
  private int mStagedSessionErrorCode;
  
  private String mStagedSessionErrorMessage;
  
  public int mode;
  
  public int originatingUid;
  
  public Uri originatingUri;
  
  public int parentSessionId;
  
  public float progress;
  
  public Uri referrerUri;
  
  public String resolvedBaseCodePath;
  
  public int rollbackDataPolicy;
  
  public boolean sealed;
  
  public int sessionId;
  
  public long sizeBytes;
  
  public long updatedMillis;
  
  public int userId;
  
  public List<String> whitelistedRestrictedPermissions;
  
  static {
    CREATOR = new Parcelable.Creator<SessionInfo>() {
        public PackageInstaller.SessionInfo createFromParcel(Parcel param2Parcel) {
          return new PackageInstaller.SessionInfo(param2Parcel);
        }
        
        public PackageInstaller.SessionInfo[] newArray(int param2Int) {
          return new PackageInstaller.SessionInfo[param2Int];
        }
      };
  }
  
  public SessionInfo() {
    this.autoRevokePermissionsMode = 3;
    this.parentSessionId = -1;
    this.childSessionIds = NO_SESSIONS;
  }
  
  public SessionInfo(Parcel paramParcel) {
    boolean bool2;
    this.autoRevokePermissionsMode = 3;
    this.parentSessionId = -1;
    this.childSessionIds = NO_SESSIONS;
    this.sessionId = paramParcel.readInt();
    this.userId = paramParcel.readInt();
    this.installerPackageName = paramParcel.readString();
    this.resolvedBaseCodePath = paramParcel.readString();
    this.progress = paramParcel.readFloat();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.sealed = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.active = bool2;
    this.mode = paramParcel.readInt();
    this.installReason = paramParcel.readInt();
    this.sizeBytes = paramParcel.readLong();
    this.appPackageName = paramParcel.readString();
    this.appIcon = (Bitmap)paramParcel.readParcelable(null);
    this.appLabel = paramParcel.readString();
    this.installLocation = paramParcel.readInt();
    this.originatingUri = (Uri)paramParcel.readParcelable(null);
    this.originatingUid = paramParcel.readInt();
    this.referrerUri = (Uri)paramParcel.readParcelable(null);
    this.grantedRuntimePermissions = paramParcel.readStringArray();
    this.whitelistedRestrictedPermissions = paramParcel.createStringArrayList();
    this.autoRevokePermissionsMode = paramParcel.readInt();
    this.installFlags = paramParcel.readInt();
    this.isMultiPackage = paramParcel.readBoolean();
    this.isStaged = paramParcel.readBoolean();
    this.forceQueryable = paramParcel.readBoolean();
    this.parentSessionId = paramParcel.readInt();
    int[] arrayOfInt = paramParcel.createIntArray();
    this.childSessionIds = arrayOfInt;
    if (arrayOfInt == null)
      this.childSessionIds = NO_SESSIONS; 
    this.isStagedSessionApplied = paramParcel.readBoolean();
    this.isStagedSessionReady = paramParcel.readBoolean();
    this.isStagedSessionFailed = paramParcel.readBoolean();
    this.mStagedSessionErrorCode = paramParcel.readInt();
    this.mStagedSessionErrorMessage = paramParcel.readString();
    this.isCommitted = paramParcel.readBoolean();
    this.rollbackDataPolicy = paramParcel.readInt();
    this.createdMillis = paramParcel.readLong();
  }
  
  private void checkSessionIsStaged() {
    if (this.isStaged)
      return; 
    throw new IllegalStateException("Session is not marked as staged.");
  }
  
  public Intent createDetailsIntent() {
    Intent intent = new Intent("android.content.pm.action.SESSION_DETAILS");
    intent.putExtra("android.content.pm.extra.SESSION_ID", this.sessionId);
    intent.setPackage(this.installerPackageName);
    intent.setFlags(268435456);
    return intent;
  }
  
  public int describeContents() {
    return 0;
  }
  
  @SystemApi
  public boolean getAllocateAggressive() {
    boolean bool;
    if ((this.installFlags & 0x8000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  @Deprecated
  public boolean getAllowDowngrade() {
    return getRequestDowngrade();
  }
  
  public Bitmap getAppIcon() {
    if (this.appIcon == null)
      try {
        SessionInfo sessionInfo = AppGlobals.getPackageManager().getPackageInstaller().getSessionInfo(this.sessionId);
        if (sessionInfo != null) {
          Bitmap bitmap = sessionInfo.appIcon;
        } else {
          sessionInfo = null;
        } 
        this.appIcon = (Bitmap)sessionInfo;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return this.appIcon;
  }
  
  public CharSequence getAppLabel() {
    return this.appLabel;
  }
  
  public String getAppPackageName() {
    return this.appPackageName;
  }
  
  @SystemApi
  public int getAutoRevokePermissionsMode() {
    return this.autoRevokePermissionsMode;
  }
  
  public int[] getChildSessionIds() {
    return this.childSessionIds;
  }
  
  public long getCreatedMillis() {
    return this.createdMillis;
  }
  
  @Deprecated
  public Intent getDetailsIntent() {
    return createDetailsIntent();
  }
  
  @SystemApi
  public boolean getDontKillApp() {
    boolean bool;
    if ((this.installFlags & 0x1000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
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
  public String[] getGrantedRuntimePermissions() {
    return this.grantedRuntimePermissions;
  }
  
  @SystemApi
  public boolean getInstallAsFullApp(boolean paramBoolean) {
    if ((this.installFlags & 0x4000) != 0) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    return paramBoolean;
  }
  
  @SystemApi
  public boolean getInstallAsInstantApp(boolean paramBoolean) {
    if ((this.installFlags & 0x800) != 0) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    return paramBoolean;
  }
  
  @SystemApi
  public boolean getInstallAsVirtualPreload() {
    boolean bool;
    if ((this.installFlags & 0x10000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getInstallLocation() {
    return this.installLocation;
  }
  
  public int getInstallReason() {
    return this.installReason;
  }
  
  public String getInstallerPackageName() {
    return this.installerPackageName;
  }
  
  public int getMode() {
    return this.mode;
  }
  
  public int getOriginatingUid() {
    return this.originatingUid;
  }
  
  public Uri getOriginatingUri() {
    return this.originatingUri;
  }
  
  public int getParentSessionId() {
    return this.parentSessionId;
  }
  
  public float getProgress() {
    return this.progress;
  }
  
  public Uri getReferrerUri() {
    return this.referrerUri;
  }
  
  @SystemApi
  public boolean getRequestDowngrade() {
    boolean bool;
    if ((this.installFlags & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  public int getRollbackDataPolicy() {
    return this.rollbackDataPolicy;
  }
  
  public int getSessionId() {
    return this.sessionId;
  }
  
  public long getSize() {
    return this.sizeBytes;
  }
  
  public int getStagedSessionErrorCode() {
    checkSessionIsStaged();
    return this.mStagedSessionErrorCode;
  }
  
  public String getStagedSessionErrorMessage() {
    checkSessionIsStaged();
    return this.mStagedSessionErrorMessage;
  }
  
  public long getUpdatedMillis() {
    return this.updatedMillis;
  }
  
  public UserHandle getUser() {
    return new UserHandle(this.userId);
  }
  
  @SystemApi
  public Set<String> getWhitelistedRestrictedPermissions() {
    return (Set<String>)(((this.installFlags & 0x400000) != 0) ? PackageInstaller.SessionParams.RESTRICTED_PERMISSIONS_ALL : ((this.whitelistedRestrictedPermissions != null) ? new ArraySet(this.whitelistedRestrictedPermissions) : Collections.emptySet()));
  }
  
  public boolean hasParentSessionId() {
    boolean bool;
    if (this.parentSessionId != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  public boolean isCommitted() {
    return this.isCommitted;
  }
  
  public boolean isForceQueryable() {
    return this.forceQueryable;
  }
  
  public boolean isMultiPackage() {
    return this.isMultiPackage;
  }
  
  @Deprecated
  public boolean isOpen() {
    return isActive();
  }
  
  public boolean isSealed() {
    return this.sealed;
  }
  
  public boolean isStaged() {
    return this.isStaged;
  }
  
  public boolean isStagedSessionActive() {
    boolean bool;
    if (this.isStaged && this.isCommitted && !this.isStagedSessionApplied && !this.isStagedSessionFailed && !hasParentSessionId()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStagedSessionApplied() {
    checkSessionIsStaged();
    return this.isStagedSessionApplied;
  }
  
  public boolean isStagedSessionFailed() {
    checkSessionIsStaged();
    return this.isStagedSessionFailed;
  }
  
  public boolean isStagedSessionReady() {
    checkSessionIsStaged();
    return this.isStagedSessionReady;
  }
  
  public void setStagedSessionErrorCode(int paramInt, String paramString) {
    this.mStagedSessionErrorCode = paramInt;
    this.mStagedSessionErrorMessage = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.sessionId);
    paramParcel.writeInt(this.userId);
    paramParcel.writeString(this.installerPackageName);
    paramParcel.writeString(this.resolvedBaseCodePath);
    paramParcel.writeFloat(this.progress);
    paramParcel.writeInt(this.sealed);
    paramParcel.writeInt(this.active);
    paramParcel.writeInt(this.mode);
    paramParcel.writeInt(this.installReason);
    paramParcel.writeLong(this.sizeBytes);
    paramParcel.writeString(this.appPackageName);
    paramParcel.writeParcelable((Parcelable)this.appIcon, paramInt);
    CharSequence charSequence = this.appLabel;
    if (charSequence != null) {
      charSequence = charSequence.toString();
    } else {
      charSequence = null;
    } 
    paramParcel.writeString((String)charSequence);
    paramParcel.writeInt(this.installLocation);
    paramParcel.writeParcelable((Parcelable)this.originatingUri, paramInt);
    paramParcel.writeInt(this.originatingUid);
    paramParcel.writeParcelable((Parcelable)this.referrerUri, paramInt);
    paramParcel.writeStringArray(this.grantedRuntimePermissions);
    paramParcel.writeStringList(this.whitelistedRestrictedPermissions);
    paramParcel.writeInt(this.autoRevokePermissionsMode);
    paramParcel.writeInt(this.installFlags);
    paramParcel.writeBoolean(this.isMultiPackage);
    paramParcel.writeBoolean(this.isStaged);
    paramParcel.writeBoolean(this.forceQueryable);
    paramParcel.writeInt(this.parentSessionId);
    paramParcel.writeIntArray(this.childSessionIds);
    paramParcel.writeBoolean(this.isStagedSessionApplied);
    paramParcel.writeBoolean(this.isStagedSessionReady);
    paramParcel.writeBoolean(this.isStagedSessionFailed);
    paramParcel.writeInt(this.mStagedSessionErrorCode);
    paramParcel.writeString(this.mStagedSessionErrorMessage);
    paramParcel.writeBoolean(this.isCommitted);
    paramParcel.writeInt(this.rollbackDataPolicy);
    paramParcel.writeLong(this.createdMillis);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StagedSessionErrorCode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$SessionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */