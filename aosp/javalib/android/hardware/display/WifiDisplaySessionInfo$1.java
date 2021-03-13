package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WifiDisplaySessionInfo> {
  public WifiDisplaySessionInfo createFromParcel(Parcel paramParcel) {
    boolean bool;
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return new WifiDisplaySessionInfo(bool, paramParcel.readInt(), paramParcel.readString(), paramParcel.readString(), paramParcel.readString());
  }
  
  public WifiDisplaySessionInfo[] newArray(int paramInt) {
    return new WifiDisplaySessionInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/WifiDisplaySessionInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */