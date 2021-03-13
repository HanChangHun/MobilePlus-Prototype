package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Rule> {
  public Rule createFromParcel(Parcel paramParcel) {
    return new Rule(paramParcel);
  }
  
  public Rule[] newArray(int paramInt) {
    return new Rule[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/Rule$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */