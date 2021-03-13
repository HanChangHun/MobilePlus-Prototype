package android.app.blob;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BlobInfo> {
  public BlobInfo createFromParcel(Parcel paramParcel) {
    return new BlobInfo(paramParcel, null);
  }
  
  public BlobInfo[] newArray(int paramInt) {
    return new BlobInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */