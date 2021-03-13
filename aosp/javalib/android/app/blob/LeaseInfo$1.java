package android.app.blob;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<LeaseInfo> {
  public LeaseInfo createFromParcel(Parcel paramParcel) {
    return new LeaseInfo(paramParcel, null);
  }
  
  public LeaseInfo[] newArray(int paramInt) {
    return new LeaseInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/LeaseInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */