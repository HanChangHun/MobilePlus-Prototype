package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ServiceStartArgs> {
  public ServiceStartArgs createFromParcel(Parcel paramParcel) {
    return new ServiceStartArgs(paramParcel);
  }
  
  public ServiceStartArgs[] newArray(int paramInt) {
    return new ServiceStartArgs[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ServiceStartArgs$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */