package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ChangedPackages> {
  public ChangedPackages createFromParcel(Parcel paramParcel) {
    return new ChangedPackages(paramParcel);
  }
  
  public ChangedPackages[] newArray(int paramInt) {
    return new ChangedPackages[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ChangedPackages$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */