package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AccessibilityServiceInfo> {
  public AccessibilityServiceInfo createFromParcel(Parcel paramParcel) {
    AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
    AccessibilityServiceInfo.access$000(accessibilityServiceInfo, paramParcel);
    return accessibilityServiceInfo;
  }
  
  public AccessibilityServiceInfo[] newArray(int paramInt) {
    return new AccessibilityServiceInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityServiceInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */