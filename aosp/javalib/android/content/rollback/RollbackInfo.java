package android.content.rollback;

import android.annotation.SystemApi;
import android.content.pm.VersionedPackage;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

@SystemApi
public final class RollbackInfo implements Parcelable {
  public static final Parcelable.Creator<RollbackInfo> CREATOR = new Parcelable.Creator<RollbackInfo>() {
      public RollbackInfo createFromParcel(Parcel param1Parcel) {
        return new RollbackInfo(param1Parcel);
      }
      
      public RollbackInfo[] newArray(int param1Int) {
        return new RollbackInfo[param1Int];
      }
    };
  
  private final List<VersionedPackage> mCausePackages;
  
  private int mCommittedSessionId;
  
  private final boolean mIsStaged;
  
  private final List<PackageRollbackInfo> mPackages;
  
  private final int mRollbackId;
  
  public RollbackInfo(int paramInt1, List<PackageRollbackInfo> paramList, boolean paramBoolean, List<VersionedPackage> paramList1, int paramInt2) {
    this.mRollbackId = paramInt1;
    this.mPackages = paramList;
    this.mIsStaged = paramBoolean;
    this.mCausePackages = paramList1;
    this.mCommittedSessionId = paramInt2;
  }
  
  private RollbackInfo(Parcel paramParcel) {
    this.mRollbackId = paramParcel.readInt();
    this.mPackages = paramParcel.createTypedArrayList(PackageRollbackInfo.CREATOR);
    this.mIsStaged = paramParcel.readBoolean();
    this.mCausePackages = paramParcel.createTypedArrayList(VersionedPackage.CREATOR);
    this.mCommittedSessionId = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<VersionedPackage> getCausePackages() {
    return this.mCausePackages;
  }
  
  public int getCommittedSessionId() {
    return this.mCommittedSessionId;
  }
  
  public List<PackageRollbackInfo> getPackages() {
    return this.mPackages;
  }
  
  public int getRollbackId() {
    return this.mRollbackId;
  }
  
  public boolean isStaged() {
    return this.mIsStaged;
  }
  
  public void setCommittedSessionId(int paramInt) {
    this.mCommittedSessionId = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRollbackId);
    paramParcel.writeTypedList(this.mPackages);
    paramParcel.writeBoolean(this.mIsStaged);
    paramParcel.writeTypedList(this.mCausePackages);
    paramParcel.writeInt(this.mCommittedSessionId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/RollbackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */