package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ResolveInfo> {
  public ResolveInfo createFromParcel(Parcel paramParcel) {
    return new ResolveInfo(paramParcel, null);
  }
  
  public ResolveInfo[] newArray(int paramInt) {
    return new ResolveInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ResolveInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */