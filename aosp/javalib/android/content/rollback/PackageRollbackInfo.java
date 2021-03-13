package android.content.rollback;

import android.annotation.SystemApi;
import android.content.pm.VersionedPackage;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.IntArray;
import android.util.SparseLongArray;
import java.util.ArrayList;

@SystemApi
public final class PackageRollbackInfo implements Parcelable {
  public static final Parcelable.Creator<PackageRollbackInfo> CREATOR = new Parcelable.Creator<PackageRollbackInfo>() {
      public PackageRollbackInfo createFromParcel(Parcel param1Parcel) {
        return new PackageRollbackInfo(param1Parcel);
      }
      
      public PackageRollbackInfo[] newArray(int param1Int) {
        return new PackageRollbackInfo[param1Int];
      }
    };
  
  private final SparseLongArray mCeSnapshotInodes;
  
  private final boolean mIsApex;
  
  private final boolean mIsApkInApex;
  
  private final IntArray mPendingBackups;
  
  private final ArrayList<RestoreInfo> mPendingRestores;
  
  private final int mRollbackDataPolicy;
  
  private final IntArray mSnapshottedUsers;
  
  private final VersionedPackage mVersionRolledBackFrom;
  
  private final VersionedPackage mVersionRolledBackTo;
  
  public PackageRollbackInfo(VersionedPackage paramVersionedPackage1, VersionedPackage paramVersionedPackage2, IntArray paramIntArray1, ArrayList<RestoreInfo> paramArrayList, boolean paramBoolean1, boolean paramBoolean2, IntArray paramIntArray2, SparseLongArray paramSparseLongArray) {
    this(paramVersionedPackage1, paramVersionedPackage2, paramIntArray1, paramArrayList, paramBoolean1, paramBoolean2, paramIntArray2, paramSparseLongArray, 0);
  }
  
  public PackageRollbackInfo(VersionedPackage paramVersionedPackage1, VersionedPackage paramVersionedPackage2, IntArray paramIntArray1, ArrayList<RestoreInfo> paramArrayList, boolean paramBoolean1, boolean paramBoolean2, IntArray paramIntArray2, SparseLongArray paramSparseLongArray, int paramInt) {
    this.mVersionRolledBackFrom = paramVersionedPackage1;
    this.mVersionRolledBackTo = paramVersionedPackage2;
    this.mPendingBackups = paramIntArray1;
    this.mPendingRestores = paramArrayList;
    this.mIsApex = paramBoolean1;
    this.mRollbackDataPolicy = paramInt;
    this.mIsApkInApex = paramBoolean2;
    this.mSnapshottedUsers = paramIntArray2;
    this.mCeSnapshotInodes = paramSparseLongArray;
  }
  
  private PackageRollbackInfo(Parcel paramParcel) {
    this.mVersionRolledBackFrom = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(paramParcel);
    this.mVersionRolledBackTo = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(paramParcel);
    this.mIsApex = paramParcel.readBoolean();
    this.mIsApkInApex = paramParcel.readBoolean();
    this.mPendingRestores = null;
    this.mPendingBackups = null;
    this.mSnapshottedUsers = null;
    this.mCeSnapshotInodes = null;
    this.mRollbackDataPolicy = 0;
  }
  
  public void addPendingBackup(int paramInt) {
    this.mPendingBackups.add(paramInt);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public SparseLongArray getCeSnapshotInodes() {
    return this.mCeSnapshotInodes;
  }
  
  public String getPackageName() {
    return this.mVersionRolledBackFrom.getPackageName();
  }
  
  public IntArray getPendingBackups() {
    return this.mPendingBackups;
  }
  
  public ArrayList<RestoreInfo> getPendingRestores() {
    return this.mPendingRestores;
  }
  
  public RestoreInfo getRestoreInfo(int paramInt) {
    for (RestoreInfo restoreInfo : this.mPendingRestores) {
      if (restoreInfo.userId == paramInt)
        return restoreInfo; 
    } 
    return null;
  }
  
  public int getRollbackDataPolicy() {
    return this.mRollbackDataPolicy;
  }
  
  public IntArray getSnapshottedUsers() {
    return this.mSnapshottedUsers;
  }
  
  public VersionedPackage getVersionRolledBackFrom() {
    return this.mVersionRolledBackFrom;
  }
  
  public VersionedPackage getVersionRolledBackTo() {
    return this.mVersionRolledBackTo;
  }
  
  public boolean isApex() {
    return this.mIsApex;
  }
  
  public boolean isApkInApex() {
    return this.mIsApkInApex;
  }
  
  public void putCeSnapshotInode(int paramInt, long paramLong) {
    this.mCeSnapshotInodes.put(paramInt, paramLong);
  }
  
  public void removePendingBackup(int paramInt) {
    paramInt = this.mPendingBackups.indexOf(paramInt);
    if (paramInt != -1)
      this.mPendingBackups.remove(paramInt); 
  }
  
  public void removePendingRestoreInfo(int paramInt) {
    removeRestoreInfo(getRestoreInfo(paramInt));
  }
  
  public void removeRestoreInfo(RestoreInfo paramRestoreInfo) {
    this.mPendingRestores.remove(paramRestoreInfo);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mVersionRolledBackFrom.writeToParcel(paramParcel, paramInt);
    this.mVersionRolledBackTo.writeToParcel(paramParcel, paramInt);
    paramParcel.writeBoolean(this.mIsApex);
    paramParcel.writeBoolean(this.mIsApkInApex);
  }
  
  public static class RestoreInfo {
    public final int appId;
    
    public final String seInfo;
    
    public final int userId;
    
    public RestoreInfo(int param1Int1, int param1Int2, String param1String) {
      this.userId = param1Int1;
      this.appId = param1Int2;
      this.seInfo = param1String;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/PackageRollbackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */