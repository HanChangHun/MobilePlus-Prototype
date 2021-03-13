package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<OobData> {
  public OobData createFromParcel(Parcel paramParcel) {
    return new OobData(paramParcel, null);
  }
  
  public OobData[] newArray(int paramInt) {
    return new OobData[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/OobData$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */