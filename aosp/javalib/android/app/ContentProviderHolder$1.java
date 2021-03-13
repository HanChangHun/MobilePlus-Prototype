package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentProviderHolder> {
  public ContentProviderHolder createFromParcel(Parcel paramParcel) {
    return new ContentProviderHolder(paramParcel, null);
  }
  
  public ContentProviderHolder[] newArray(int paramInt) {
    return new ContentProviderHolder[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ContentProviderHolder$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */