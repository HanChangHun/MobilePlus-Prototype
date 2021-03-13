package android.app.assist;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AssistStructure> {
  public AssistStructure createFromParcel(Parcel paramParcel) {
    return new AssistStructure(paramParcel);
  }
  
  public AssistStructure[] newArray(int paramInt) {
    return new AssistStructure[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */