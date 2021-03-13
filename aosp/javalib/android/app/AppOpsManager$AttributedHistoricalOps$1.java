package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.AttributedHistoricalOps> {
  public AppOpsManager.AttributedHistoricalOps createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.AttributedHistoricalOps(paramParcel);
  }
  
  public AppOpsManager.AttributedHistoricalOps[] newArray(int paramInt) {
    return new AppOpsManager.AttributedHistoricalOps[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$AttributedHistoricalOps$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */