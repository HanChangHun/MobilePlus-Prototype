package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PasswordMetrics> {
  public PasswordMetrics createFromParcel(Parcel paramParcel) {
    return new PasswordMetrics(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
  }
  
  public PasswordMetrics[] newArray(int paramInt) {
    return new PasswordMetrics[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordMetrics$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */