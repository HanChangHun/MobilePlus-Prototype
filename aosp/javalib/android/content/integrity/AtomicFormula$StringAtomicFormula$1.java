package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AtomicFormula.StringAtomicFormula> {
  public AtomicFormula.StringAtomicFormula createFromParcel(Parcel paramParcel) {
    return new AtomicFormula.StringAtomicFormula(paramParcel);
  }
  
  public AtomicFormula.StringAtomicFormula[] newArray(int paramInt) {
    return new AtomicFormula.StringAtomicFormula[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula$StringAtomicFormula$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */