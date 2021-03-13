package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ConnectEvent> {
  public ConnectEvent createFromParcel(Parcel paramParcel) {
    return (paramParcel.readInt() != 2) ? null : new ConnectEvent(paramParcel, null);
  }
  
  public ConnectEvent[] newArray(int paramInt) {
    return new ConnectEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/ConnectEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */