package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstantAppResolveInfo> {
  public InstantAppResolveInfo createFromParcel(Parcel paramParcel) {
    return new InstantAppResolveInfo(paramParcel);
  }
  
  public InstantAppResolveInfo[] newArray(int paramInt) {
    return new InstantAppResolveInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppResolveInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */