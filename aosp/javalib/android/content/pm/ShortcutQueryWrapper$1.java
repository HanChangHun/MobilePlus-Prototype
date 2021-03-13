package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ShortcutQueryWrapper> {
  public ShortcutQueryWrapper createFromParcel(Parcel paramParcel) {
    return new ShortcutQueryWrapper(paramParcel);
  }
  
  public ShortcutQueryWrapper[] newArray(int paramInt) {
    return new ShortcutQueryWrapper[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutQueryWrapper$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */