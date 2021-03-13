package android.app.job;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<JobSnapshot> {
  public JobSnapshot createFromParcel(Parcel paramParcel) {
    return new JobSnapshot(paramParcel);
  }
  
  public JobSnapshot[] newArray(int paramInt) {
    return new JobSnapshot[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobSnapshot$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */