package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RemoteAction> {
  public RemoteAction createFromParcel(Parcel paramParcel) {
    return new RemoteAction(paramParcel);
  }
  
  public RemoteAction[] newArray(int paramInt) {
    return new RemoteAction[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteAction$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */