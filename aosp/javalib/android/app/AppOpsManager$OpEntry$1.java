package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.OpEntry> {
  public AppOpsManager.OpEntry createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.OpEntry(paramParcel);
  }
  
  public AppOpsManager.OpEntry[] newArray(int paramInt) {
    return new AppOpsManager.OpEntry[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OpEntry$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */