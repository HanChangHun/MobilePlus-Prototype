package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<HardwareBuffer> {
  public HardwareBuffer createFromParcel(Parcel paramParcel) {
    long l = HardwareBuffer.access$000(paramParcel);
    return (l != 0L) ? new HardwareBuffer(l, null) : null;
  }
  
  public HardwareBuffer[] newArray(int paramInt) {
    return new HardwareBuffer[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/HardwareBuffer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */