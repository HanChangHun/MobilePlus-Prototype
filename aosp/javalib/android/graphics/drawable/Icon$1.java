package android.graphics.drawable;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Icon> {
  public Icon createFromParcel(Parcel paramParcel) {
    return new Icon(paramParcel, null);
  }
  
  public Icon[] newArray(int paramInt) {
    return new Icon[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Icon$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */