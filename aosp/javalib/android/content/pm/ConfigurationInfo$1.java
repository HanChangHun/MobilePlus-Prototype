package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ConfigurationInfo> {
  public ConfigurationInfo createFromParcel(Parcel paramParcel) {
    return new ConfigurationInfo(paramParcel, null);
  }
  
  public ConfigurationInfo[] newArray(int paramInt) {
    return new ConfigurationInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ConfigurationInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */