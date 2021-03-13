package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SecurityLog.SecurityEvent> {
  public SecurityLog.SecurityEvent createFromParcel(Parcel paramParcel) {
    return new SecurityLog.SecurityEvent(paramParcel);
  }
  
  public SecurityLog.SecurityEvent[] newArray(int paramInt) {
    return new SecurityLog.SecurityEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SecurityLog$SecurityEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */