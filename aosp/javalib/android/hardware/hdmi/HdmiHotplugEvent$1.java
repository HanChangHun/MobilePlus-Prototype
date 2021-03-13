package android.hardware.hdmi;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<HdmiHotplugEvent> {
  public HdmiHotplugEvent createFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    byte b = paramParcel.readByte();
    boolean bool = true;
    if (b != 1)
      bool = false; 
    return new HdmiHotplugEvent(i, bool);
  }
  
  public HdmiHotplugEvent[] newArray(int paramInt) {
    return new HdmiHotplugEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiHotplugEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */