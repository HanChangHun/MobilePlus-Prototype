package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.ClassLoaderCreator<ParceledListSlice> {
  public ParceledListSlice createFromParcel(Parcel paramParcel) {
    return new ParceledListSlice<>(paramParcel, null, null);
  }
  
  public ParceledListSlice createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader) {
    return new ParceledListSlice<>(paramParcel, paramClassLoader, null);
  }
  
  public ParceledListSlice[] newArray(int paramInt) {
    return new ParceledListSlice[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ParceledListSlice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */