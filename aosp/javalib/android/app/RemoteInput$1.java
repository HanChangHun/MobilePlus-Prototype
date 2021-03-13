package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RemoteInput> {
  public RemoteInput createFromParcel(Parcel paramParcel) {
    return new RemoteInput(paramParcel, null);
  }
  
  public RemoteInput[] newArray(int paramInt) {
    return new RemoteInput[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteInput$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */