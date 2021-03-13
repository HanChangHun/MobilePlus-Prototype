package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.HistoricalOps> {
  public AppOpsManager.HistoricalOps createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.HistoricalOps(paramParcel, null);
  }
  
  public AppOpsManager.HistoricalOps[] newArray(int paramInt) {
    return new AppOpsManager.HistoricalOps[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOps$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */