package android.app.prediction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppPredictionContext> {
  public AppPredictionContext createFromParcel(Parcel paramParcel) {
    return new AppPredictionContext(paramParcel, null);
  }
  
  public AppPredictionContext[] newArray(int paramInt) {
    return new AppPredictionContext[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictionContext$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */