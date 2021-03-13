package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AtomicFormula.LongAtomicFormula> {
  public AtomicFormula.LongAtomicFormula createFromParcel(Parcel paramParcel) {
    return new AtomicFormula.LongAtomicFormula(paramParcel);
  }
  
  public AtomicFormula.LongAtomicFormula[] newArray(int paramInt) {
    return new AtomicFormula.LongAtomicFormula[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula$LongAtomicFormula$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */