package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppOpsManager.OpEventProxyInfo> {
  public AppOpsManager.OpEventProxyInfo createFromParcel(Parcel paramParcel) {
    return new AppOpsManager.OpEventProxyInfo(paramParcel);
  }
  
  public AppOpsManager.OpEventProxyInfo[] newArray(int paramInt) {
    return new AppOpsManager.OpEventProxyInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OpEventProxyInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */