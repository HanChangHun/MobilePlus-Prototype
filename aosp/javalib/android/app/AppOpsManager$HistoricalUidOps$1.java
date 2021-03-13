package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.HistoricalUidOps> {
  public AppOpsManager.HistoricalUidOps createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.HistoricalUidOps(paramParcel, null);
  }
  
  public AppOpsManager.HistoricalUidOps[] newArray(int paramInt) {
    return new AppOpsManager.HistoricalUidOps[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalUidOps$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */