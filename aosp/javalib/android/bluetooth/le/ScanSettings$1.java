package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ScanSettings> {
  public ScanSettings createFromParcel(Parcel paramParcel) {
    return new ScanSettings(paramParcel, null);
  }
  
  public ScanSettings[] newArray(int paramInt) {
    return new ScanSettings[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanSettings$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */