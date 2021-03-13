package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator {
  public SdpOppOpsRecord createFromParcel(Parcel paramParcel) {
    return new SdpOppOpsRecord(paramParcel);
  }
  
  public SdpOppOpsRecord[] newArray(int paramInt) {
    return new SdpOppOpsRecord[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpOppOpsRecord$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */