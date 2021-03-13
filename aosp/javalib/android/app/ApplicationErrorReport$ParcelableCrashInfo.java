package android.app;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableCrashInfo extends ApplicationErrorReport.CrashInfo implements Parcelable {
  public static final Parcelable.Creator<ParcelableCrashInfo> CREATOR = new Parcelable.Creator<ParcelableCrashInfo>() {
      public ApplicationErrorReport.ParcelableCrashInfo createFromParcel(Parcel param2Parcel) {
        return new ApplicationErrorReport.ParcelableCrashInfo(param2Parcel);
      }
      
      public ApplicationErrorReport.ParcelableCrashInfo[] newArray(int param2Int) {
        return new ApplicationErrorReport.ParcelableCrashInfo[param2Int];
      }
    };
  
  public ParcelableCrashInfo() {}
  
  public ParcelableCrashInfo(Parcel paramParcel) {
    super(paramParcel);
  }
  
  public ParcelableCrashInfo(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public int describeContents() {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$ParcelableCrashInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */