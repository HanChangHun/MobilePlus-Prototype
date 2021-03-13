package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RecoverableSecurityException> {
  public RecoverableSecurityException createFromParcel(Parcel paramParcel) {
    return new RecoverableSecurityException(paramParcel);
  }
  
  public RecoverableSecurityException[] newArray(int paramInt) {
    return new RecoverableSecurityException[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RecoverableSecurityException$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */