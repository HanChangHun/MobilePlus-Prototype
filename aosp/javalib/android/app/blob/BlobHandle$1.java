package android.app.blob;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BlobHandle> {
  public BlobHandle createFromParcel(Parcel paramParcel) {
    return new BlobHandle(paramParcel, null);
  }
  
  public BlobHandle[] newArray(int paramInt) {
    return new BlobHandle[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobHandle$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */