package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ServiceInfo> {
  public ServiceInfo createFromParcel(Parcel paramParcel) {
    return new ServiceInfo(paramParcel, null);
  }
  
  public ServiceInfo[] newArray(int paramInt) {
    return new ServiceInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ServiceInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */