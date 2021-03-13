package android.app.prediction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppTargetEvent> {
  public AppTargetEvent createFromParcel(Parcel paramParcel) {
    return new AppTargetEvent(paramParcel, null);
  }
  
  public AppTargetEvent[] newArray(int paramInt) {
    return new AppTargetEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTargetEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */