package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ContentProviderOperation.BackReference> {
  public ContentProviderOperation.BackReference createFromParcel(Parcel paramParcel) {
    return new ContentProviderOperation.BackReference(paramParcel);
  }
  
  public ContentProviderOperation.BackReference[] newArray(int paramInt) {
    return new ContentProviderOperation.BackReference[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderOperation$BackReference$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */