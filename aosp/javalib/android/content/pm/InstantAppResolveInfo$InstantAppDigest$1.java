package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<InstantAppResolveInfo.InstantAppDigest> {
  public InstantAppResolveInfo.InstantAppDigest createFromParcel(Parcel paramParcel) {
    return paramParcel.readBoolean() ? InstantAppResolveInfo.InstantAppDigest.UNDEFINED : new InstantAppResolveInfo.InstantAppDigest(paramParcel);
  }
  
  public InstantAppResolveInfo.InstantAppDigest[] newArray(int paramInt) {
    return new InstantAppResolveInfo.InstantAppDigest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppResolveInfo$InstantAppDigest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */