package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ModuleInfo> {
  public ModuleInfo createFromParcel(Parcel paramParcel) {
    return new ModuleInfo(paramParcel, null);
  }
  
  public ModuleInfo[] newArray(int paramInt) {
    return new ModuleInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ModuleInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */