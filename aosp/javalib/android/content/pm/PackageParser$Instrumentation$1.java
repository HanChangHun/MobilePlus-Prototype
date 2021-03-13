package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageParser.Instrumentation> {
  public PackageParser.Instrumentation createFromParcel(Parcel paramParcel) {
    return new PackageParser.Instrumentation(paramParcel, null);
  }
  
  public PackageParser.Instrumentation[] newArray(int paramInt) {
    return new PackageParser.Instrumentation[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Instrumentation$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */