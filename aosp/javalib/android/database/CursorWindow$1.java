package android.database;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CursorWindow> {
  public CursorWindow createFromParcel(Parcel paramParcel) {
    return new CursorWindow(paramParcel, null);
  }
  
  public CursorWindow[] newArray(int paramInt) {
    return new CursorWindow[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CursorWindow$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */