package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncAdapterType> {
  public SyncAdapterType createFromParcel(Parcel paramParcel) {
    return new SyncAdapterType(paramParcel);
  }
  
  public SyncAdapterType[] newArray(int paramInt) {
    return new SyncAdapterType[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncAdapterType$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */