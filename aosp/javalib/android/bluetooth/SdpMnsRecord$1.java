package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator {
  public SdpMnsRecord createFromParcel(Parcel paramParcel) {
    return new SdpMnsRecord(paramParcel);
  }
  
  public SdpMnsRecord[] newArray(int paramInt) {
    return new SdpMnsRecord[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpMnsRecord$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */