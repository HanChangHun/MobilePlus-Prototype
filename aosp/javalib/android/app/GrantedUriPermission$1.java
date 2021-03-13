package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<GrantedUriPermission> {
  public GrantedUriPermission createFromParcel(Parcel paramParcel) {
    return new GrantedUriPermission(paramParcel, null);
  }
  
  public GrantedUriPermission[] newArray(int paramInt) {
    return new GrantedUriPermission[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/GrantedUriPermission$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */