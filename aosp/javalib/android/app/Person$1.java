package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Person> {
  public Person createFromParcel(Parcel paramParcel) {
    return new Person(paramParcel, null);
  }
  
  public Person[] newArray(int paramInt) {
    return new Person[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Person$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */