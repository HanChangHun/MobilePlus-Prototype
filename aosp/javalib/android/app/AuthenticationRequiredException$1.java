package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AuthenticationRequiredException> {
  public AuthenticationRequiredException createFromParcel(Parcel paramParcel) {
    return new AuthenticationRequiredException(paramParcel);
  }
  
  public AuthenticationRequiredException[] newArray(int paramInt) {
    return new AuthenticationRequiredException[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AuthenticationRequiredException$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */