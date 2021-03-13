package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AutomaticZenRule> {
  public AutomaticZenRule createFromParcel(Parcel paramParcel) {
    return new AutomaticZenRule(paramParcel);
  }
  
  public AutomaticZenRule[] newArray(int paramInt) {
    return new AutomaticZenRule[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AutomaticZenRule$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */