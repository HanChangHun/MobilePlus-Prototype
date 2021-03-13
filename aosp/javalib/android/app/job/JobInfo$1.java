package android.app.job;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<JobInfo> {
  public JobInfo createFromParcel(Parcel paramParcel) {
    return new JobInfo(paramParcel, null);
  }
  
  public JobInfo[] newArray(int paramInt) {
    return new JobInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */