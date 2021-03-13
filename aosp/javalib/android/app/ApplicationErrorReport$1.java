package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ApplicationErrorReport> {
  public ApplicationErrorReport createFromParcel(Parcel paramParcel) {
    return new ApplicationErrorReport(paramParcel);
  }
  
  public ApplicationErrorReport[] newArray(int paramInt) {
    return new ApplicationErrorReport[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */