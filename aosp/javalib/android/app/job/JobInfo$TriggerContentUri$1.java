package android.app.job;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<JobInfo.TriggerContentUri> {
  public JobInfo.TriggerContentUri createFromParcel(Parcel paramParcel) {
    return new JobInfo.TriggerContentUri(paramParcel, null);
  }
  
  public JobInfo.TriggerContentUri[] newArray(int paramInt) {
    return new JobInfo.TriggerContentUri[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobInfo$TriggerContentUri$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */