package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContextHubInfo> {
  public ContextHubInfo createFromParcel(Parcel paramParcel) {
    return new ContextHubInfo(paramParcel, null);
  }
  
  public ContextHubInfo[] newArray(int paramInt) {
    return new ContextHubInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */