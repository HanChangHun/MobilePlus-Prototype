package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SystemUpdatePolicy.ValidationFailedException> {
  public SystemUpdatePolicy.ValidationFailedException createFromParcel(Parcel paramParcel) {
    return new SystemUpdatePolicy.ValidationFailedException(paramParcel.readInt(), paramParcel.readString(), null);
  }
  
  public SystemUpdatePolicy.ValidationFailedException[] newArray(int paramInt) {
    return new SystemUpdatePolicy.ValidationFailedException[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdatePolicy$ValidationFailedException$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */