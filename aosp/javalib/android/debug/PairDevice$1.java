package android.debug;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PairDevice> {
  public PairDevice createFromParcel(Parcel paramParcel) {
    return new PairDevice(paramParcel.readString(), paramParcel.readString(), paramParcel.readBoolean());
  }
  
  public PairDevice[] newArray(int paramInt) {
    return new PairDevice[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/debug/PairDevice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */