package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.HistoricalOp> {
  public AppOpsManager.HistoricalOp createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.HistoricalOp(paramParcel, null);
  }
  
  public AppOpsManager.HistoricalOp[] newArray(int paramInt) {
    return new AppOpsManager.HistoricalOp[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOp$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */