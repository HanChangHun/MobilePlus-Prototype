package android.app.prediction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppTargetId> {
  public AppTargetId createFromParcel(Parcel paramParcel) {
    return new AppTargetId(paramParcel, null);
  }
  
  public AppTargetId[] newArray(int paramInt) {
    return new AppTargetId[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTargetId$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */