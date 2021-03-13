package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApplicationErrorReport.ParcelableCrashInfo> {
  public ApplicationErrorReport.ParcelableCrashInfo createFromParcel(Parcel paramParcel) {
    return new ApplicationErrorReport.ParcelableCrashInfo(paramParcel);
  }
  
  public ApplicationErrorReport.ParcelableCrashInfo[] newArray(int paramInt) {
    return new ApplicationErrorReport.ParcelableCrashInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$ParcelableCrashInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */