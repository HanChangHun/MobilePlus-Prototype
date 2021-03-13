package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Insets> {
  public Insets createFromParcel(Parcel paramParcel) {
    return new Insets(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), null);
  }
  
  public Insets[] newArray(int paramInt) {
    return new Insets[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Insets$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */