package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentProviderOperation> {
  public ContentProviderOperation createFromParcel(Parcel paramParcel) {
    return new ContentProviderOperation(paramParcel, null);
  }
  
  public ContentProviderOperation[] newArray(int paramInt) {
    return new ContentProviderOperation[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderOperation$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */