package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentValues> {
  public ContentValues createFromParcel(Parcel paramParcel) {
    return new ContentValues(paramParcel, null);
  }
  
  public ContentValues[] newArray(int paramInt) {
    return new ContentValues[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentValues$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */