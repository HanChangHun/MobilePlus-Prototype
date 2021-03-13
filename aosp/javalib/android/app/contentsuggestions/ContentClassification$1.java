package android.app.contentsuggestions;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentClassification> {
  public ContentClassification createFromParcel(Parcel paramParcel) {
    return new ContentClassification(paramParcel.readString(), paramParcel.readBundle());
  }
  
  public ContentClassification[] newArray(int paramInt) {
    return new ContentClassification[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentClassification$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */