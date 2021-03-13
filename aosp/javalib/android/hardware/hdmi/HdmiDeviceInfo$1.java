package android.hardware.hdmi;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<HdmiDeviceInfo> {
  public HdmiDeviceInfo createFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    if (i != 0) {
      if (i != 1)
        return (i != 2) ? ((i != 100) ? null : HdmiDeviceInfo.INACTIVE_DEVICE) : new HdmiDeviceInfo(j, k); 
      i = paramParcel.readInt();
      return new HdmiDeviceInfo(j, k, paramParcel.readInt(), i);
    } 
    int m = paramParcel.readInt();
    int n = paramParcel.readInt();
    i = paramParcel.readInt();
    int i1 = paramParcel.readInt();
    return new HdmiDeviceInfo(m, j, k, n, i, paramParcel.readString(), i1);
  }
  
  public HdmiDeviceInfo[] newArray(int paramInt) {
    return new HdmiDeviceInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiDeviceInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */