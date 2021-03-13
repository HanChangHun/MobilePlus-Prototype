package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Configuration> {
  public Configuration createFromParcel(Parcel paramParcel) {
    return new Configuration(paramParcel, null);
  }
  
  public Configuration[] newArray(int paramInt) {
    return new Configuration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Configuration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */