package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentProviderResult> {
  public ContentProviderResult createFromParcel(Parcel paramParcel) {
    return new ContentProviderResult(paramParcel);
  }
  
  public ContentProviderResult[] newArray(int paramInt) {
    return new ContentProviderResult[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderResult$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */