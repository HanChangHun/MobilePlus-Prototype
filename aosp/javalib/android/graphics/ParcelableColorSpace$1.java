package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ParcelableColorSpace> {
  public ParcelableColorSpace createFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    return (i == -1) ? new ParcelableColorSpace(new ColorSpace.Rgb(paramParcel.readString(), paramParcel.createFloatArray(), paramParcel.createFloatArray(), new ColorSpace.Rgb.TransferParameters(paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readDouble()))) : new ParcelableColorSpace(ColorSpace.get(i));
  }
  
  public ParcelableColorSpace[] newArray(int paramInt) {
    return new ParcelableColorSpace[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ParcelableColorSpace$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */