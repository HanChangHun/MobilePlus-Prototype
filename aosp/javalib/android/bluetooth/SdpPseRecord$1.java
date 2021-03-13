package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator {
  public SdpPseRecord createFromParcel(Parcel paramParcel) {
    return new SdpPseRecord(paramParcel);
  }
  
  public SdpPseRecord[] newArray(int paramInt) {
    return new SdpPseRecord[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpPseRecord$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */