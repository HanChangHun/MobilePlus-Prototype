package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AtomicFormula.BooleanAtomicFormula> {
  public AtomicFormula.BooleanAtomicFormula createFromParcel(Parcel paramParcel) {
    return new AtomicFormula.BooleanAtomicFormula(paramParcel);
  }
  
  public AtomicFormula.BooleanAtomicFormula[] newArray(int paramInt) {
    return new AtomicFormula.BooleanAtomicFormula[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula$BooleanAtomicFormula$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */