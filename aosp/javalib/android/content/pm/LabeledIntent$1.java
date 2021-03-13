package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LabeledIntent> {
  public LabeledIntent createFromParcel(Parcel paramParcel) {
    return new LabeledIntent(paramParcel);
  }
  
  public LabeledIntent[] newArray(int paramInt) {
    return new LabeledIntent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LabeledIntent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */