package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ComponentName> {
  public ComponentName createFromParcel(Parcel paramParcel) {
    return new ComponentName(paramParcel);
  }
  
  public ComponentName[] newArray(int paramInt) {
    return new ComponentName[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ComponentName$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */