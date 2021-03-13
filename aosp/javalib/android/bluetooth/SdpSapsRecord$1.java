package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator {
  public SdpSapsRecord createFromParcel(Parcel paramParcel) {
    return new SdpSapsRecord(paramParcel);
  }
  
  public SdpRecord[] newArray(int paramInt) {
    return new SdpRecord[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpSapsRecord$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */