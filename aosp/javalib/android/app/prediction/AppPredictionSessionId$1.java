package android.app.prediction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppPredictionSessionId> {
  public AppPredictionSessionId createFromParcel(Parcel paramParcel) {
    return new AppPredictionSessionId(paramParcel, null);
  }
  
  public AppPredictionSessionId[] newArray(int paramInt) {
    return new AppPredictionSessionId[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictionSessionId$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */