package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DirectAction> {
  public DirectAction createFromParcel(Parcel paramParcel) {
    return new DirectAction(paramParcel, null);
  }
  
  public DirectAction[] newArray(int paramInt) {
    return new DirectAction[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DirectAction$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */