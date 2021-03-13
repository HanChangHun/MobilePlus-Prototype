package android.content;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<Intent> {
  public Intent createFromParcel(Parcel paramParcel) {
    return new Intent(paramParcel);
  }
  
  public Intent[] newArray(int paramInt) {
    return new Intent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Intent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */