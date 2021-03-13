package android.content;

import android.os.Parcel;
import android.os.Parcelable;

public class ShortcutIconResource implements Parcelable {
  public static final Parcelable.Creator<ShortcutIconResource> CREATOR = new Parcelable.Creator<ShortcutIconResource>() {
      public Intent.ShortcutIconResource createFromParcel(Parcel param2Parcel) {
        Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
        shortcutIconResource.packageName = param2Parcel.readString8();
        shortcutIconResource.resourceName = param2Parcel.readString8();
        return shortcutIconResource;
      }
      
      public Intent.ShortcutIconResource[] newArray(int param2Int) {
        return new Intent.ShortcutIconResource[param2Int];
      }
    };
  
  public String packageName;
  
  public String resourceName;
  
  public static ShortcutIconResource fromContext(Context paramContext, int paramInt) {
    ShortcutIconResource shortcutIconResource = new ShortcutIconResource();
    shortcutIconResource.packageName = paramContext.getPackageName();
    shortcutIconResource.resourceName = paramContext.getResources().getResourceName(paramInt);
    return shortcutIconResource;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    return this.resourceName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString8(this.packageName);
    paramParcel.writeString8(this.resourceName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Intent$ShortcutIconResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */