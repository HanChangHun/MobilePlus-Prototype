package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PeriodicAdvertisingParameters> {
  public PeriodicAdvertisingParameters createFromParcel(Parcel paramParcel) {
    return new PeriodicAdvertisingParameters(paramParcel, null);
  }
  
  public PeriodicAdvertisingParameters[] newArray(int paramInt) {
    return new PeriodicAdvertisingParameters[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingParameters$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */