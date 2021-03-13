package android.app.prediction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppTarget> {
  public AppTarget createFromParcel(Parcel paramParcel) {
    return new AppTarget(paramParcel, null);
  }
  
  public AppTarget[] newArray(int paramInt) {
    return new AppTarget[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTarget$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */