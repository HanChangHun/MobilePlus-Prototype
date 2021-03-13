package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SharedLibraryInfo> {
  public SharedLibraryInfo createFromParcel(Parcel paramParcel) {
    return new SharedLibraryInfo(paramParcel, null);
  }
  
  public SharedLibraryInfo[] newArray(int paramInt) {
    return new SharedLibraryInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SharedLibraryInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */