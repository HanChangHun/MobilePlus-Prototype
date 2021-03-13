package android.telephony.data;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DataProfile> {
  public DataProfile createFromParcel(Parcel paramParcel) {
    return new DataProfile(paramParcel, null);
  }
  
  public DataProfile[] newArray(int paramInt) {
    return new DataProfile[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataProfile$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */