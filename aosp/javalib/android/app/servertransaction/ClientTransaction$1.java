package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ClientTransaction> {
  public ClientTransaction createFromParcel(Parcel paramParcel) {
    return new ClientTransaction(paramParcel, null);
  }
  
  public ClientTransaction[] newArray(int paramInt) {
    return new ClientTransaction[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ClientTransaction$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */