package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WifiDisplayStatus> {
  public WifiDisplayStatus createFromParcel(Parcel paramParcel) {
    WifiDisplay wifiDisplay;
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      wifiDisplay = (WifiDisplay)WifiDisplay.CREATOR.createFromParcel(paramParcel);
    } else {
      wifiDisplay = null;
    } 
    WifiDisplay[] arrayOfWifiDisplay = (WifiDisplay[])WifiDisplay.CREATOR.newArray(paramParcel.readInt());
    for (byte b = 0; b < arrayOfWifiDisplay.length; b++)
      arrayOfWifiDisplay[b] = (WifiDisplay)WifiDisplay.CREATOR.createFromParcel(paramParcel); 
    return new WifiDisplayStatus(i, j, k, wifiDisplay, arrayOfWifiDisplay, (WifiDisplaySessionInfo)WifiDisplaySessionInfo.CREATOR.createFromParcel(paramParcel));
  }
  
  public WifiDisplayStatus[] newArray(int paramInt) {
    return new WifiDisplayStatus[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/WifiDisplayStatus$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */