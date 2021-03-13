package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RulesState implements Parcelable {
  private static final byte BYTE_FALSE = 0;
  
  private static final byte BYTE_TRUE = 1;
  
  public static final Parcelable.Creator<RulesState> CREATOR = new Parcelable.Creator<RulesState>() {
      public RulesState createFromParcel(Parcel param1Parcel) {
        return RulesState.createFromParcel(param1Parcel);
      }
      
      public RulesState[] newArray(int param1Int) {
        return new RulesState[param1Int];
      }
    };
  
  public static final int DISTRO_STATUS_INSTALLED = 2;
  
  public static final int DISTRO_STATUS_NONE = 1;
  
  public static final int DISTRO_STATUS_UNKNOWN = 0;
  
  public static final int STAGED_OPERATION_INSTALL = 3;
  
  public static final int STAGED_OPERATION_NONE = 1;
  
  public static final int STAGED_OPERATION_UNINSTALL = 2;
  
  public static final int STAGED_OPERATION_UNKNOWN = 0;
  
  private final String mBaseRulesVersion;
  
  private final DistroFormatVersion mDistroFormatVersionSupported;
  
  private final int mDistroStatus;
  
  private final DistroRulesVersion mInstalledDistroRulesVersion;
  
  private final boolean mOperationInProgress;
  
  private final DistroRulesVersion mStagedDistroRulesVersion;
  
  private final int mStagedOperationType;
  
  public RulesState(String paramString, DistroFormatVersion paramDistroFormatVersion, boolean paramBoolean, int paramInt1, DistroRulesVersion paramDistroRulesVersion1, int paramInt2, DistroRulesVersion paramDistroRulesVersion2) {
    this.mBaseRulesVersion = Utils.validateRulesVersion("baseRulesVersion", paramString);
    this.mDistroFormatVersionSupported = Utils.<DistroFormatVersion>validateNotNull("distroFormatVersionSupported", paramDistroFormatVersion);
    this.mOperationInProgress = paramBoolean;
    if (!paramBoolean || paramInt1 == 0) {
      paramInt1 = validateStagedOperation(paramInt1);
      this.mStagedOperationType = paramInt1;
      boolean bool = true;
      if (paramInt1 == 3) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      this.mStagedDistroRulesVersion = Utils.<DistroRulesVersion>validateConditionalNull(paramBoolean, "stagedDistroRulesVersion", paramDistroRulesVersion1);
      paramInt1 = validateDistroStatus(paramInt2);
      this.mDistroStatus = paramInt1;
      if (paramInt1 == 2) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      this.mInstalledDistroRulesVersion = Utils.<DistroRulesVersion>validateConditionalNull(paramBoolean, "installedDistroRulesVersion", paramDistroRulesVersion2);
      return;
    } 
    throw new IllegalArgumentException("stagedOperationType != STAGED_OPERATION_UNKNOWN");
  }
  
  private static RulesState createFromParcel(Parcel paramParcel) {
    boolean bool;
    String str = paramParcel.readString();
    DistroFormatVersion distroFormatVersion = (DistroFormatVersion)paramParcel.readParcelable(null);
    if (paramParcel.readByte() == 1) {
      bool = true;
    } else {
      bool = false;
    } 
    return new RulesState(str, distroFormatVersion, bool, paramParcel.readByte(), (DistroRulesVersion)paramParcel.readParcelable(null), paramParcel.readByte(), (DistroRulesVersion)paramParcel.readParcelable(null));
  }
  
  private static int validateDistroStatus(int paramInt) {
    if (paramInt >= 0 && paramInt <= 2)
      return paramInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown distro status=");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static int validateStagedOperation(int paramInt) {
    if (paramInt >= 0 && paramInt <= 3)
      return paramInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown operation type=");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mOperationInProgress != ((RulesState)paramObject).mOperationInProgress)
      return false; 
    if (this.mStagedOperationType != ((RulesState)paramObject).mStagedOperationType)
      return false; 
    if (this.mDistroStatus != ((RulesState)paramObject).mDistroStatus)
      return false; 
    if (!this.mBaseRulesVersion.equals(((RulesState)paramObject).mBaseRulesVersion))
      return false; 
    if (!this.mDistroFormatVersionSupported.equals(((RulesState)paramObject).mDistroFormatVersionSupported))
      return false; 
    DistroRulesVersion distroRulesVersion = this.mStagedDistroRulesVersion;
    if ((distroRulesVersion != null) ? !distroRulesVersion.equals(((RulesState)paramObject).mStagedDistroRulesVersion) : (((RulesState)paramObject).mStagedDistroRulesVersion != null))
      return false; 
    distroRulesVersion = this.mInstalledDistroRulesVersion;
    if (distroRulesVersion != null) {
      bool = distroRulesVersion.equals(((RulesState)paramObject).mInstalledDistroRulesVersion);
    } else if (((RulesState)paramObject).mInstalledDistroRulesVersion != null) {
      bool = false;
    } 
    return bool;
  }
  
  public String getBaseRulesVersion() {
    return this.mBaseRulesVersion;
  }
  
  public int getDistroStatus() {
    return this.mDistroStatus;
  }
  
  public DistroRulesVersion getInstalledDistroRulesVersion() {
    return this.mInstalledDistroRulesVersion;
  }
  
  public DistroRulesVersion getStagedDistroRulesVersion() {
    return this.mStagedDistroRulesVersion;
  }
  
  public int getStagedOperationType() {
    return this.mStagedOperationType;
  }
  
  public int hashCode() {
    byte b;
    int i = this.mBaseRulesVersion.hashCode();
    int j = this.mDistroFormatVersionSupported.hashCode();
    boolean bool = this.mOperationInProgress;
    int k = this.mStagedOperationType;
    DistroRulesVersion distroRulesVersion = this.mStagedDistroRulesVersion;
    int m = 0;
    if (distroRulesVersion != null) {
      b = distroRulesVersion.hashCode();
    } else {
      b = 0;
    } 
    int n = this.mDistroStatus;
    distroRulesVersion = this.mInstalledDistroRulesVersion;
    if (distroRulesVersion != null)
      m = distroRulesVersion.hashCode(); 
    return (((((i * 31 + j) * 31 + bool) * 31 + k) * 31 + b) * 31 + n) * 31 + m;
  }
  
  public boolean isBaseVersionNewerThan(DistroRulesVersion paramDistroRulesVersion) {
    boolean bool;
    if (this.mBaseRulesVersion.compareTo(paramDistroRulesVersion.getRulesVersion()) > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDistroFormatVersionSupported(DistroFormatVersion paramDistroFormatVersion) {
    return this.mDistroFormatVersionSupported.supports(paramDistroFormatVersion);
  }
  
  public boolean isOperationInProgress() {
    return this.mOperationInProgress;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RulesState{mBaseRulesVersion='");
    stringBuilder.append(this.mBaseRulesVersion);
    stringBuilder.append('\'');
    stringBuilder.append(", mDistroFormatVersionSupported=");
    stringBuilder.append(this.mDistroFormatVersionSupported);
    stringBuilder.append(", mOperationInProgress=");
    stringBuilder.append(this.mOperationInProgress);
    stringBuilder.append(", mStagedOperationType=");
    stringBuilder.append(this.mStagedOperationType);
    stringBuilder.append(", mStagedDistroRulesVersion=");
    stringBuilder.append(this.mStagedDistroRulesVersion);
    stringBuilder.append(", mDistroStatus=");
    stringBuilder.append(this.mDistroStatus);
    stringBuilder.append(", mInstalledDistroRulesVersion=");
    stringBuilder.append(this.mInstalledDistroRulesVersion);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mBaseRulesVersion);
    paramParcel.writeParcelable(this.mDistroFormatVersionSupported, 0);
    paramParcel.writeByte(this.mOperationInProgress);
    paramParcel.writeByte((byte)this.mStagedOperationType);
    paramParcel.writeParcelable(this.mStagedDistroRulesVersion, 0);
    paramParcel.writeByte((byte)this.mDistroStatus);
    paramParcel.writeParcelable(this.mInstalledDistroRulesVersion, 0);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface DistroStatus {}
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface StagedOperationType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/RulesState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */