package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RectF> {
  public RectF createFromParcel(Parcel paramParcel) {
    RectF rectF = new RectF();
    rectF.readFromParcel(paramParcel);
    return rectF;
  }
  
  public RectF[] newArray(int paramInt) {
    return new RectF[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RectF$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */