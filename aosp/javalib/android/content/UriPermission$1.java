package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<UriPermission> {
  public UriPermission createFromParcel(Parcel paramParcel) {
    return new UriPermission(paramParcel);
  }
  
  public UriPermission[] newArray(int paramInt) {
    return new UriPermission[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/UriPermission$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */