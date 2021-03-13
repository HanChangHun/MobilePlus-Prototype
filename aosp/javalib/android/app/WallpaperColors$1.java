package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WallpaperColors> {
  public WallpaperColors createFromParcel(Parcel paramParcel) {
    return new WallpaperColors(paramParcel);
  }
  
  public WallpaperColors[] newArray(int paramInt) {
    return new WallpaperColors[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperColors$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */