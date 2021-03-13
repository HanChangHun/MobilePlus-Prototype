package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.ClassLoaderCreator<StringParceledListSlice> {
  public StringParceledListSlice createFromParcel(Parcel paramParcel) {
    return new StringParceledListSlice(paramParcel, null, null);
  }
  
  public StringParceledListSlice createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader) {
    return new StringParceledListSlice(paramParcel, paramClassLoader, null);
  }
  
  public StringParceledListSlice[] newArray(int paramInt) {
    return new StringParceledListSlice[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/StringParceledListSlice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */