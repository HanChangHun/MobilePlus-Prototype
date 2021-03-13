package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Intent.ShortcutIconResource> {
  public Intent.ShortcutIconResource createFromParcel(Parcel paramParcel) {
    Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
    shortcutIconResource.packageName = paramParcel.readString8();
    shortcutIconResource.resourceName = paramParcel.readString8();
    return shortcutIconResource;
  }
  
  public Intent.ShortcutIconResource[] newArray(int paramInt) {
    return new Intent.ShortcutIconResource[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Intent$ShortcutIconResource$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */