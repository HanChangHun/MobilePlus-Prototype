package android.app.contentsuggestions;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentSelection> {
  public ContentSelection createFromParcel(Parcel paramParcel) {
    return new ContentSelection(paramParcel.readString(), paramParcel.readBundle());
  }
  
  public ContentSelection[] newArray(int paramInt) {
    return new ContentSelection[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentSelection$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */