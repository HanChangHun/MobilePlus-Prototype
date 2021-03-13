package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PeriodicAdvertisingReport> {
  public PeriodicAdvertisingReport createFromParcel(Parcel paramParcel) {
    return new PeriodicAdvertisingReport(paramParcel, null);
  }
  
  public PeriodicAdvertisingReport[] newArray(int paramInt) {
    return new PeriodicAdvertisingReport[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingReport$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */