package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageChangeEvent> {
  public PackageChangeEvent createFromParcel(Parcel paramParcel) {
    PackageChangeEvent packageChangeEvent = new PackageChangeEvent();
    packageChangeEvent.readFromParcel(paramParcel);
    return packageChangeEvent;
  }
  
  public PackageChangeEvent[] newArray(int paramInt) {
    return new PackageChangeEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageChangeEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */