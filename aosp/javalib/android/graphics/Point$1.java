package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Point> {
  public Point createFromParcel(Parcel paramParcel) {
    Point point = new Point();
    point.readFromParcel(paramParcel);
    return point;
  }
  
  public Point[] newArray(int paramInt) {
    return new Point[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Point$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */