package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<WindowConfiguration> {
  public WindowConfiguration createFromParcel(Parcel paramParcel) {
    return new WindowConfiguration(paramParcel, null);
  }
  
  public WindowConfiguration[] newArray(int paramInt) {
    return new WindowConfiguration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WindowConfiguration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */