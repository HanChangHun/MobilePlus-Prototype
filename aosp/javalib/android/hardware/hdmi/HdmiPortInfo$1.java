package android.hardware.hdmi;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<HdmiPortInfo> {
  public HdmiPortInfo createFromParcel(Parcel paramParcel) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel.readInt() == 1) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    return new HdmiPortInfo(i, j, k, bool1, bool3, bool2);
  }
  
  public HdmiPortInfo[] newArray(int paramInt) {
    return new HdmiPortInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiPortInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */