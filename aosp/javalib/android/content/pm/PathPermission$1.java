package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PathPermission> {
  public PathPermission createFromParcel(Parcel paramParcel) {
    return new PathPermission(paramParcel);
  }
  
  public PathPermission[] newArray(int paramInt) {
    return new PathPermission[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PathPermission$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */