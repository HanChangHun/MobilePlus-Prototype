package android.app.job;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<JobWorkItem> {
  public JobWorkItem createFromParcel(Parcel paramParcel) {
    return new JobWorkItem(paramParcel);
  }
  
  public JobWorkItem[] newArray(int paramInt) {
    return new JobWorkItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobWorkItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */