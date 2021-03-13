package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ShortcutInfo> {
  public ShortcutInfo createFromParcel(Parcel paramParcel) {
    return new ShortcutInfo(paramParcel, null);
  }
  
  public ShortcutInfo[] newArray(int paramInt) {
    return new ShortcutInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */