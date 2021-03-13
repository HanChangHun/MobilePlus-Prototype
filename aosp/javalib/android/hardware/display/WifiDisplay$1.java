package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WifiDisplay> {
  public WifiDisplay createFromParcel(Parcel paramParcel) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    if (paramParcel.readInt() != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel.readInt() != 0) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    return new WifiDisplay(str1, str2, str3, bool1, bool2, bool3);
  }
  
  public WifiDisplay[] newArray(int paramInt) {
    WifiDisplay[] arrayOfWifiDisplay;
    if (paramInt == 0) {
      arrayOfWifiDisplay = WifiDisplay.EMPTY_ARRAY;
    } else {
      arrayOfWifiDisplay = new WifiDisplay[paramInt];
    } 
    return arrayOfWifiDisplay;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/WifiDisplay$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */