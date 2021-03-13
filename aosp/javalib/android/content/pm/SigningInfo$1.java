package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SigningInfo> {
  public SigningInfo createFromParcel(Parcel paramParcel) {
    return new SigningInfo(paramParcel, null);
  }
  
  public SigningInfo[] newArray(int paramInt) {
    return new SigningInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SigningInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */