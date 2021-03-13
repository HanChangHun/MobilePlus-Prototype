package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Vr2dDisplayProperties> {
  public Vr2dDisplayProperties createFromParcel(Parcel paramParcel) {
    return new Vr2dDisplayProperties(paramParcel, null);
  }
  
  public Vr2dDisplayProperties[] newArray(int paramInt) {
    return new Vr2dDisplayProperties[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Vr2dDisplayProperties$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */