package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContextHubMessage> {
  public ContextHubMessage createFromParcel(Parcel paramParcel) {
    return new ContextHubMessage(paramParcel, null);
  }
  
  public ContextHubMessage[] newArray(int paramInt) {
    return new ContextHubMessage[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubMessage$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */