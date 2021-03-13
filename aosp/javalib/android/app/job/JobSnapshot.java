package android.app.job;

import android.os.Parcel;
import android.os.Parcelable;

public class JobSnapshot implements Parcelable {
  public static final Parcelable.Creator<JobSnapshot> CREATOR = new Parcelable.Creator<JobSnapshot>() {
      public JobSnapshot createFromParcel(Parcel param1Parcel) {
        return new JobSnapshot(param1Parcel);
      }
      
      public JobSnapshot[] newArray(int param1Int) {
        return new JobSnapshot[param1Int];
      }
    };
  
  private final boolean mIsRunnable;
  
  private final JobInfo mJob;
  
  private final int mSatisfiedConstraints;
  
  public JobSnapshot(JobInfo paramJobInfo, int paramInt, boolean paramBoolean) {
    this.mJob = paramJobInfo;
    this.mSatisfiedConstraints = paramInt;
    this.mIsRunnable = paramBoolean;
  }
  
  public JobSnapshot(Parcel paramParcel) {
    this.mJob = (JobInfo)JobInfo.CREATOR.createFromParcel(paramParcel);
    this.mSatisfiedConstraints = paramParcel.readInt();
    this.mIsRunnable = paramParcel.readBoolean();
  }
  
  private boolean satisfied(int paramInt) {
    boolean bool;
    if ((this.mSatisfiedConstraints & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public JobInfo getJobInfo() {
    return this.mJob;
  }
  
  public boolean isBatteryNotLowSatisfied() {
    return (!this.mJob.isRequireBatteryNotLow() || satisfied(2));
  }
  
  public boolean isChargingSatisfied() {
    boolean bool = this.mJob.isRequireCharging();
    boolean bool1 = true;
    if (bool && !satisfied(1))
      bool1 = false; 
    return bool1;
  }
  
  public boolean isRequireDeviceIdleSatisfied() {
    return (!this.mJob.isRequireDeviceIdle() || satisfied(4));
  }
  
  public boolean isRequireStorageNotLowSatisfied() {
    return (!this.mJob.isRequireStorageNotLow() || satisfied(8));
  }
  
  public boolean isRunnable() {
    return this.mIsRunnable;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mJob.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.mSatisfiedConstraints);
    paramParcel.writeBoolean(this.mIsRunnable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobSnapshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */