package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AvbPublicKey> {
  public AvbPublicKey createFromParcel(Parcel paramParcel) {
    AvbPublicKey avbPublicKey = new AvbPublicKey();
    avbPublicKey.readFromParcel(paramParcel);
    return avbPublicKey;
  }
  
  public AvbPublicKey[] newArray(int paramInt) {
    return new AvbPublicKey[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/AvbPublicKey$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */