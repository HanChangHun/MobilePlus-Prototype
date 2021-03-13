package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Rect> {
  public Rect createFromParcel(Parcel paramParcel) {
    Rect rect = new Rect();
    rect.readFromParcel(paramParcel);
    return rect;
  }
  
  public Rect[] newArray(int paramInt) {
    return new Rect[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Rect$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */