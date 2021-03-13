package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WallpaperInfo> {
  public WallpaperInfo createFromParcel(Parcel paramParcel) {
    return new WallpaperInfo(paramParcel);
  }
  
  public WallpaperInfo[] newArray(int paramInt) {
    return new WallpaperInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */