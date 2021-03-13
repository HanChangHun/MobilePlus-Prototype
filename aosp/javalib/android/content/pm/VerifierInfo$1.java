package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<VerifierInfo> {
  public VerifierInfo createFromParcel(Parcel paramParcel) {
    return new VerifierInfo(paramParcel, null);
  }
  
  public VerifierInfo[] newArray(int paramInt) {
    return new VerifierInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/VerifierInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */