package android.telephony.data;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DataCallResponse> {
  public DataCallResponse createFromParcel(Parcel paramParcel) {
    return new DataCallResponse(paramParcel);
  }
  
  public DataCallResponse[] newArray(int paramInt) {
    return new DataCallResponse[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataCallResponse$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */