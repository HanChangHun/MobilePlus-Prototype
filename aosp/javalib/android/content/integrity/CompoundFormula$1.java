package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CompoundFormula> {
  public CompoundFormula createFromParcel(Parcel paramParcel) {
    return new CompoundFormula(paramParcel);
  }
  
  public CompoundFormula[] newArray(int paramInt) {
    return new CompoundFormula[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/CompoundFormula$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */