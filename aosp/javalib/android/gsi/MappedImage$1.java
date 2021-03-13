package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<MappedImage> {
  public MappedImage createFromParcel(Parcel paramParcel) {
    MappedImage mappedImage = new MappedImage();
    mappedImage.readFromParcel(paramParcel);
    return mappedImage;
  }
  
  public MappedImage[] newArray(int paramInt) {
    return new MappedImage[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/MappedImage$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */