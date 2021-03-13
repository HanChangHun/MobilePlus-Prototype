package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentCaptureOptions> {
  public ContentCaptureOptions createFromParcel(Parcel paramParcel) {
    boolean bool = paramParcel.readBoolean();
    int i = paramParcel.readInt();
    return bool ? new ContentCaptureOptions(i) : new ContentCaptureOptions(i, paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readArraySet(null));
  }
  
  public ContentCaptureOptions[] newArray(int paramInt) {
    return new ContentCaptureOptions[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentCaptureOptions$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */