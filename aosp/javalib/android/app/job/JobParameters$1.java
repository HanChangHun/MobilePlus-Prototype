package android.app.job;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<JobParameters> {
  public JobParameters createFromParcel(Parcel paramParcel) {
    return new JobParameters(paramParcel, null);
  }
  
  public JobParameters[] newArray(int paramInt) {
    return new JobParameters[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobParameters$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */