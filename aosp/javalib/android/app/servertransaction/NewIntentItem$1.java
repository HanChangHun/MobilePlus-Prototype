package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<NewIntentItem> {
  public NewIntentItem createFromParcel(Parcel paramParcel) {
    return new NewIntentItem(paramParcel, null);
  }
  
  public NewIntentItem[] newArray(int paramInt) {
    return new NewIntentItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/NewIntentItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */