package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.Provider> {
  public PackageParser.Provider createFromParcel(Parcel paramParcel) {
    return new PackageParser.Provider(paramParcel, null);
  }
  
  public PackageParser.Provider[] newArray(int paramInt) {
    return new PackageParser.Provider[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Provider$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */