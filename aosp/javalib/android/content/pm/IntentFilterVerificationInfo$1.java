package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<IntentFilterVerificationInfo> {
  public IntentFilterVerificationInfo createFromParcel(Parcel paramParcel) {
    return new IntentFilterVerificationInfo(paramParcel);
  }
  
  public IntentFilterVerificationInfo[] newArray(int paramInt) {
    return new IntentFilterVerificationInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IntentFilterVerificationInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */