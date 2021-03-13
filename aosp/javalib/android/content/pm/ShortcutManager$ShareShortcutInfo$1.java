package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ShortcutManager.ShareShortcutInfo> {
  public ShortcutManager.ShareShortcutInfo createFromParcel(Parcel paramParcel) {
    return new ShortcutManager.ShareShortcutInfo(paramParcel, null);
  }
  
  public ShortcutManager.ShareShortcutInfo[] newArray(int paramInt) {
    return new ShortcutManager.ShareShortcutInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutManager$ShareShortcutInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */