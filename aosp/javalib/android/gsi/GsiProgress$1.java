package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<GsiProgress> {
  public GsiProgress createFromParcel(Parcel paramParcel) {
    GsiProgress gsiProgress = new GsiProgress();
    gsiProgress.readFromParcel(paramParcel);
    return gsiProgress;
  }
  
  public GsiProgress[] newArray(int paramInt) {
    return new GsiProgress[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/GsiProgress$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */