package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.PackageOps> {
  public AppOpsManager.PackageOps createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.PackageOps(paramParcel);
  }
  
  public AppOpsManager.PackageOps[] newArray(int paramInt) {
    return new AppOpsManager.PackageOps[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$PackageOps$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */