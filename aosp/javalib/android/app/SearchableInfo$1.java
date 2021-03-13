package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SearchableInfo> {
  public SearchableInfo createFromParcel(Parcel paramParcel) {
    return new SearchableInfo(paramParcel);
  }
  
  public SearchableInfo[] newArray(int paramInt) {
    return new SearchableInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchableInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */