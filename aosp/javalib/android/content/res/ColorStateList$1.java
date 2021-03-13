package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ColorStateList> {
  public ColorStateList createFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    int[][] arrayOfInt = new int[i][];
    for (byte b = 0; b < i; b++)
      arrayOfInt[b] = paramParcel.createIntArray(); 
    return new ColorStateList(arrayOfInt, paramParcel.createIntArray());
  }
  
  public ColorStateList[] newArray(int paramInt) {
    return new ColorStateList[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ColorStateList$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */