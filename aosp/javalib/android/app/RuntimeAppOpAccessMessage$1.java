package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RuntimeAppOpAccessMessage> {
  public RuntimeAppOpAccessMessage createFromParcel(Parcel paramParcel) {
    return new RuntimeAppOpAccessMessage(paramParcel);
  }
  
  public RuntimeAppOpAccessMessage[] newArray(int paramInt) {
    return new RuntimeAppOpAccessMessage[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RuntimeAppOpAccessMessage$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */