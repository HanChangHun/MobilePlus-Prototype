package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PictureInPictureParams> {
  public PictureInPictureParams createFromParcel(Parcel paramParcel) {
    return new PictureInPictureParams(paramParcel);
  }
  
  public PictureInPictureParams[] newArray(int paramInt) {
    return new PictureInPictureParams[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PictureInPictureParams$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */