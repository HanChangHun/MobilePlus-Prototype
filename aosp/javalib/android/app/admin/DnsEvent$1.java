package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DnsEvent> {
  public DnsEvent createFromParcel(Parcel paramParcel) {
    return (paramParcel.readInt() != 1) ? null : new DnsEvent(paramParcel, null);
  }
  
  public DnsEvent[] newArray(int paramInt) {
    return new DnsEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DnsEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */