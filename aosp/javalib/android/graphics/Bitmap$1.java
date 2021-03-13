package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Bitmap> {
  public Bitmap createFromParcel(Parcel paramParcel) {
    Bitmap bitmap = Bitmap.access$000(paramParcel);
    if (bitmap != null)
      return bitmap; 
    throw new RuntimeException("Failed to unparcel Bitmap");
  }
  
  public Bitmap[] newArray(int paramInt) {
    return new Bitmap[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Bitmap$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */