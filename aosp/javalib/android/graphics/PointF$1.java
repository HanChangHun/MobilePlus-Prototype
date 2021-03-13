package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PointF> {
  public PointF createFromParcel(Parcel paramParcel) {
    PointF pointF = new PointF();
    pointF.readFromParcel(paramParcel);
    return pointF;
  }
  
  public PointF[] newArray(int paramInt) {
    return new PointF[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PointF$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */