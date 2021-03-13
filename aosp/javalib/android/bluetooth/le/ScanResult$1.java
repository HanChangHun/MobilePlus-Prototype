package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ScanResult> {
  public ScanResult createFromParcel(Parcel paramParcel) {
    return new ScanResult(paramParcel, null);
  }
  
  public ScanResult[] newArray(int paramInt) {
    return new ScanResult[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanResult$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */